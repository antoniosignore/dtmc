package com.netnumeri.server.finance.ssa

import Jama.*

public class SSA {
    private Integer N;
    private Integer L;
    private Integer K;
    private Matrix Y = null;
    List eigenVectors;
    Matrix[] V; //the main components of singular value decomposition

    public List<SSAEigenTriple> triples = new ArrayList<SSAEigenTriple>()

    public SSA(double[] s, int window) {

        N = s.size();
        L = window
        K = N - L + 1;
        Y = new Matrix(N, L);

        for (int i = 0; i < N; i++) {
            for (int k = 0; k < L; k++) {
                Y.set(i, k, getVal(s, i, k));
            }
        }

        println "Trajectory matrix Y"
        Y.print(4, 7)

        Matrix Ytranspose = Y.transpose()
        println "Trajectory matrix Xt"
        Ytranspose.print(4, 7)

        Matrix C = Ytranspose.times(Y).times(1/N)
        println "C = Yt * Y / N"
        C.print(4, 7)

        EigenvalueDecomposition decomposition = C.eig()
        Matrix RHO = decomposition.getV();
        println "RHO eigenvectors"
        RHO.print(4,7)

        Matrix eigenvalue = decomposition.getD();
        println "eigenvalue"
        eigenvalue.print(4,7)

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
        println "eigenvalueList = $eigenvalueList"

        Matrix PC = Y.times(RHO)
        println "PC Matrix"
        PC.print(4,7)

//        PC'*PC/N
        Matrix ZCovariance = PC.transpose().times(PC).times(1/N)
        println "ZCovariance"
        ZCovariance.print(4, 7)


        int size = RHO.getColumnDimension();
        Matrix[] U = new Matrix[size];

        for (int j = 0; j < size; j++) {
            double[][] uVec = new double[size][1];
            for (int k = 0; k < size; k++) {
                uVec[k][0] = RHO.get(k, RHO.getColumnDimension() - j - 1);
//                series.add(uVec[k][0]);
            }
            U[j] = new Matrix(uVec);
        }

        Map map = new HashMap()
        int jj = 0;

        for (int x = PC.columnDimension-1; x >=0; x--){
            SSAItem item = new SSAItem()

            Double[] pc = getColumn(PC, x)
            println pc

            Matrix Z = buildPCReconstructionMatrix(pc, PC.columnDimension)
            println "reconstructionMatrix Z"
            Z.print (4,7)

            Matrix reconstructed = Z.times(U[jj]).times(1/4)
            println "reconstructed"
            reconstructed.print (4,7)

            item.reconstructionMatrix = Z
            item.reconstructed = reconstructed

            map.put(eigenvalueList[jj++], item)
        }

    }

    def Matrix buildPCReconstructionMatrix(Double[] doubles, int columns) {
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

    def getColumn(Matrix matrix, int column) {
        Double[] result = new Double[matrix.rowDimension];
        for (int i = 0; i < matrix.rowDimension; i++){
            result[i] = matrix.get(i,column)
        }
        return result
    }

    private double getVal(double[] s, int i, int k) {
        try {
            double x = s[i + k]
            return x
        } catch (Throwable x) {
            return 0
        }
    }

    /**
     * recovery time series (group stage)
     */
    public static void grouping(List<Integer> model, SSAData data) {
        Matrix[] grouX = new Matrix[1];
        for (int j = 0; j < model.size(); j++) {
            Integer unselect = model.get(j);
            if (j == 0) {
                grouX[0] = data.getX()[unselect];
            } else {
                grouX[0] = grouX[0].plus(data.getX()[unselect]);
            }
        }
        data.setGroupX(grouX);
    }

    /**
     * recovery time series (step diagonal averaging)
     */
//    public static void diagonalAveraging(SSAData data) {
//        int L;
//        int K;
//        int N;
//        List<List> list = new ArrayList<List>();
//        for (int i = 0; i < data.getGroupX().length; i++) {
//            if (data.getGroupX()[i].getRowDimension() < data.getGroupX()[i].getColumnDimension()) {
//                L = data.getGroupX()[i].getRowDimension();
//                K = data.getGroupX()[i].getColumnDimension();
//            } else {
//                K = data.getGroupX()[i].getRowDimension();
//                L = data.getGroupX()[i].getColumnDimension();
//            }
//            N = data.getGroupX()[i].getRowDimension() + data.getGroupX()[i].getColumnDimension() - 1;
//            List series = new ArrayList();
//            double element;
//            for (int k = 0; k <= N - 1; k++) {
//                element = 0;
//                if (k >= 0 && k < L - 1) {
//                    for (int m = 0; m <= k; m++) {
//                        if (data.getGroupX()[i].getRowDimension() <= data.getGroupX()[i].getColumnDimension()) {
//                            element += data.getGroupX()[i].get(m, k - m);
//                        } else if (data.getGroupX()[i].getRowDimension() > data.getGroupX()[i].getColumnDimension()) {
//                            element += data.getGroupX()[i].get(k - m, m);
//                        }
//                    }
//                    element = element * (1.0 / (k + 1));
//                    series.add(element);
//                }
//                if (k >= L - 1 && k < K - 1) {
//                    for (int m = 0; m <= L - 2; m++) {
//                        if (data.getGroupX()[i].getRowDimension() <= data.getGroupX()[i].getColumnDimension()) {
//                            element += data.getGroupX()[i].get(m, k - m);
//                        } else if (data.getGroupX()[i].getRowDimension() > data.getGroupX()[i].getColumnDimension()) {
//                            element += data.getGroupX()[i].get(k - m, m);
//                        }
//                    }
//                    element = element * (1.0 / L);
//                    series.add(element);
//                }
//                if (k >= K - 1 && k < N) {
//                    for (int m = k - K + 1; m <= N - K; m++) {
//                        if (data.getGroupX()[i].getRowDimension() <= data.getGroupX()[i].getColumnDimension()) {
//                            element += data.getGroupX()[i].get(m, k - m);
//                        } else if (data.getGroupX()[i].getRowDimension() > data.getGroupX()[i].getColumnDimension()) {
//                            element += data.getGroupX()[i].get(k - m, m);
//                        }
//                    }
//                    element = element * (1.0 / (N - k));
//                    series.add(element);
//                }
//            }
//            list.add(series);
//        }
//        double sum;
//        //Summing the series and get the original series
//        List<Double> reconstructionList = new ArrayList<Double>();
//        for (int j = 0; j < list.get(0).size(); j++) {
//            sum = 0;
//            for (int i = 0; i < list.size(); i++) {
//                sum += (Double) list.get(i).get(j);
//            }
//            reconstructionList.add(sum);
//        }
//        data.setReconstructionList(reconstructionList);
//    }


    public Matrix rebuild(Matrix matrix) {
        Matrix rebuiltSerie = new Matrix(1, K)
        for (int i = 0; i < K; i++) {
            rebuiltSerie.set(0, i, diagonalAverage(matrix, i))
        }
        rebuiltSerie
    }

    double[] getEigenComponent(int i) {
        return rebuild (triples.get(i).getTriple()).rowPackedCopy
    }

    static double diagonalAverage(Matrix matrix, int k) {
        int rightBound = matrix.getColumnDimension() - 1
        int i = k
        int j = 0
        int noElements = 0;
        double sum = 0;
        while (i >= 0 && j >= 0 && j <= rightBound) {
            sum = sum + matrix.get(i, j)
            i--
            j++
            noElements = noElements + 1
        }
        return sum / noElements
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

    public static void main(String[] args) {

        double[] input = new double[20]
        input[0] = (1.0135518)
        input[1] =(-0.7113242 as Double)
        input[2] =(-0.3906069 as Double)
        input[3] =(1.565203)
        input[4] =(0.0439317)
        input[5] =(-1.1656093 as Double)
        input[6] =(1.0701692)
        input[7] =(1.0825379)
        input[8] =(-1.2239744 as Double)
        input[9] =(-0.0321446 as Double)
        input[10] =(1.1815997)
        input[11] =(-1.4969448 as Double)
        input[12] =(-0.7455299 as Double)
        input[13] =(1.0973884)
        input[14] =(-0.2188716 as Double)
        input[15] =(-1.0719573 as Double)
        input[16] =(0.9922009)
        input[17] =(0.4374216)
        input[18] =(-1.6880219 as Double)
        input[19] =(0.2609807 as Double)



        /*
        1.0135518
  - 0.7113242
  - 0.3906069
    1.565203
    0.0439317
  - 1.1656093
    1.0701692
    1.0825379
  - 1.2239745
  - 0.0321446
    1.1815997
  - 1.4969448
  - 0.7455299
    1.0973884
  - 0.2188716
  - 1.0719573
    0.9922009
    0.4374216
  - 1.6880219
    0.2609807
         */

//        double[] input = new double[8]
//        input[0] = 6.3
//        input[1] = 6.8
//        input[2] =7.1
//        input[3] =7.5
//        input[4] =7.2
//        input[5] =7.7
//        input[6] =8.0
//        input[7] =7.7

        SSA analysis = new  SSA(input, 4)


//        double[] component = analysis.getEigenComponent(0)
//
//        println "component = $component"

    }

}