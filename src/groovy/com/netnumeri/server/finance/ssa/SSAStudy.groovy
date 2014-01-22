package com.netnumeri.server.finance.ssa

import Jama.EigenvalueDecomposition
import Jama.Matrix
import com.netnumeri.server.finance.beans.FinConstants
import com.netnumeri.server.finance.beans.TimeSeries
import com.netnumeri.server.finance.finpojo.asset.Stock
import com.netnumeri.server.finance.ta.Indicator
import com.netnumeri.server.finance.ta.SSAComponentsIndicator
import com.netnumeri.server.finance.ta.SSASeriesPredictionIndicator
import org.apache.commons.io.FileUtils


class SSAStudy {

//    public static study(Stock stock, int window) {
//
//        TimeSeries closes = stock.getSeries(FinConstants.CLOSE);
//
//        SSAAnalysis analysis = new SSAAnalysis(closes.convertToList(), window)
//
//        def components = [0]
//        Indicator ssa0 = new SSAComponentsIndicator(closes, "SSA-0", analysis, components);
//        stock.indicators.put(ssa0.name, ssa0)
//
//        components = [1]
//        Indicator ssa1 = new SSAComponentsIndicator(closes, "SSA-1", analysis, components);
//        stock.indicators.put(ssa1.name, ssa1)
//
//        components = [0, 1]
//        Indicator ssa01 = new SSAComponentsIndicator(closes, "SSA-01", analysis, components);
//        stock.indicators.put(ssa01.name, ssa01)
//
//        components = [2]
//        Indicator ssa2 = new SSAComponentsIndicator(closes, "SSA-2", analysis, components);
//        stock.indicators.put(ssa2.name, ssa2)
//
//        components = [3]
//        Indicator ssa3 = new SSAComponentsIndicator(closes, "SSA-3", analysis, components);
//        stock.indicators.put(ssa3.name, ssa3)
//
//        components = [0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14]
//        Indicator ssa05 = new SSAComponentsIndicator(closes, "SSA-05", analysis, components);
//        stock.indicators.put(ssa05.name, ssa05)
//
//        Indicator ssa1Predict = new SSASeriesPredictionIndicator(closes, "SSA-0-predict", window, 3, components, 16);
//        stock.indicators.put(ssa1Predict.name, ssa1Predict)
//
//    }

    public static List<SSAItem> analyze(double[] s, int window) {

        boolean debug = false

        Integer N;
        Integer L;
        Integer K;

        N = s.size();
        L = window
        K = N - L + 1;
        Matrix Y = new Matrix(N, L);

        for (int i = 0; i < N; i++) {
            for (int k = 0; k < L; k++) {
                Y.set(i, k, getVal(s, i, k));
            }
        }


        if (debug) {
            println "Trajectory matrix Y"
            Y.print(4, 7)
        }

        Matrix Ytranspose = Y.transpose()

        if (debug) {
        println "Trajectory matrix Xt"
        Ytranspose.print(4, 7)
        }

        Matrix C = Ytranspose.times(Y).times(1/N)
        if (debug) {
        println "C = Yt * Y / N"
        C.print(4, 7)
        }
        EigenvalueDecomposition decomposition = C.eig()
        Matrix RHO = decomposition.getV();

        if (debug) {
        println "RHO eigenvectors"
        RHO.print(4,7)
        }
        Matrix eigenvalue = decomposition.getD();
        if (debug) {
        println "eigenvalue"
        eigenvalue.print(4,7)
        }
        List<Double> eigenvalueList = new ArrayList<Double>();
        //form the set of eigenvalues​​, standing on the diagonal
        for (int i = 0; i < eigenvalue.getRowDimension(); i++) {
            for (int j = 0; j < eigenvalue.getRowDimension(); j++) {
                if (i == j) {
                    eigenvalueList.add(eigenvalue.get(i, j));
                }
            }
        }
        Comparator comparator = Collections.reverseOrder();
        Collections.sort(eigenvalueList, comparator);
//        println "eigenvalueList = $eigenvalueList"

        Matrix PC = Y.times(RHO)
        if (debug) {
        println "PC Matrix"
        PC.print(4,7)
        }
//        PC'*PC/N
        Matrix ZCovariance = PC.transpose().times(PC).times(1/N)
        if (debug) {
        println "ZCovariance"
        ZCovariance.print(4, 7)
        }

        int size = RHO.getColumnDimension();
        Matrix[] U = new Matrix[size];

        for (int j = 0; j < size; j++) {
            double[][] uVec = new double[size][1];
            for (int k = 0; k < size; k++) {
                uVec[k][0] = RHO.get(k, RHO.getColumnDimension() - j - 1);
            }
            U[j] = new Matrix(uVec);
        }

        List<SSAItem> itemList = new ArrayList<SSAItem>()
        int jj = 0;

        for (int x = PC.columnDimension-1; x >=0; x--){
            SSAItem item = new SSAItem()

            Double[] pc = getColumn(PC, x)
//            println pc

            Matrix Z = buildPCReconstructionMatrix(pc, PC.columnDimension)
            if (debug) {
            println "reconstructionMatrix Z"
            Z.print (4,7)
            }

            Matrix reconstructed = Z.times(U[jj]).times(1/4)
//            if (debug) {
                println "reconstructed"
                reconstructed.print (4,7)
//            }

            item.reconstructionMatrix = Z
            item.reconstructed = reconstructed

            itemList.add(item)
        }

        return itemList

    }

    static def Matrix buildPCReconstructionMatrix(Double[] doubles, int columns) {
        Matrix Z = new Matrix (doubles.size(), columns)
        for (int j = 0; j < columns; j++)
            for (int i = 0; i < doubles.size(); i++){
                try {
                    Z.set(i+j,j, doubles[i])
                } catch (Throwable ex) {
                }
            }
        return Z
    }

    static double[] getColumn(Matrix matrix, int column) {
        double[] result = new double[matrix.rowDimension];
        for (int i = 0; i < matrix.rowDimension; i++){
            result[i] = matrix.get(i,column)
        }
        return result
    }

    static private double getVal(double[] s, int i, int k) {
        try {
            double x = s[i + k]
            return x
        } catch (Throwable x) {
            return 0
        }
    }

    static Matrix getRowMatrix(Matrix from, int column) {
        Matrix res = new Matrix(1, from.getColumnDimension())
        for (int index = 0; index < res.getColumnDimension(); index++) {
            res.set(0, index, from.get(column, index))
        }
        return res
    }

    static Matrix getColumnMatrix(Matrix from, int column) {
        Matrix res = new Matrix(from.getRowDimension(), 1)
        for (int index = 0; index < res.getRowDimension(); index++) {
            res.set(index, 0, from.get(index, column))
        }
        return res
    }



}
