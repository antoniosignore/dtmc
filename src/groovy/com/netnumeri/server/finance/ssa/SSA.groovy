package com.netnumeri.server.finance.ssa

import Jama.*

public class SSA {
    private Integer N;
    private Integer L;
    private Integer K;
    private Matrix T = null;
    List eigenVectors;
    Matrix[] V; //the main components of singular value decomposition

    public List<SSAEigenTriple> triples = new ArrayList<SSAEigenTriple>()

    public SSA(double[] s, int window) {
        N = s.size();
        L = window
        K = N - L + 1;
        T = new Matrix(L, K);

        for (int i = 0; i < L; i++) {
            for (int k = 0; k < K; k++) {
                T.set(i, k, s[i + k]);
            }
        }

        println "Trajectory matrix"
        T.print(4, 2)


        SingularValueDecomposition svd = T.svd()

//        double[] values = svd.getSingularValues()
//        println "values = $values"
//
//        Matrix s1 = svd.getS()
//        s1.print(4,3)
//
//        Matrix u = svd.getU()
//        u.print(4,3)
//
//        Matrix v = svd.getV()
//        v.print(4,3)
//


        Matrix TTranspose = T.transpose()

        Matrix S = T.times(TTranspose)

        println "S Matrix"
        S.print(4, 2)

        EigenvalueDecomposition decomposition = S.eig()

        Matrix eigenvalue = decomposition.getD();   //matrix with eigenvalues
        Matrix eigenvec = decomposition.getV();     //matrix of eigenvectors

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
        /*
         * eigenvalues ​​must be in descending order, so
         * sort them in reverse order (original values ​​in ascending
         * order)
         */
        Collections.sort(eigenvalueList, comparator);

        println "eigenvalueList = $eigenvalueList"
        println "eigenvectors"
        eigenvec.print(4,3)

        int size = eigenvec.getColumnDimension();
        Matrix[] V = new Matrix[size];
        Matrix[] U = new Matrix[size];
        Matrix[] X = new Matrix[size];
        ArrayList listSeries = new ArrayList();

        for (int j = 0; j < eigenvec.getColumnDimension(); j++) {
            double[][] uVec = new double[size][1];
            ArrayList series = new ArrayList();
            for (int k = 0; k < eigenvec.getRowDimension(); k++) {

                uVec[k][0] = eigenvec.get(k, eigenvec.getColumnDimension() - j - 1);
                series.add(uVec[k][0]);
            }
            listSeries.add(series);
            U[j] = new Matrix(uVec);
            V[j] = S.times(U[j]);
        }

        eigenVectors = listSeries

        for (int i = 0; i < V.length; i++) {
            for (int j = 0; j < V[i].getRowDimension(); j++) {
                for (int k = 0; k < V[i].getColumnDimension(); k++) {
                    double val = V[i].get(j, k) / Math.sqrt(eigenvalueList.get(i));
                    V[i].set(j, k, val);
                }
            }
        }

        for (int i = 0; i < X.length; i++) {
            X[i] = U[i].times(V[i].transpose());
            for (int j = 0; j < X[i].getRowDimension(); j++) {
                for (int k = 0; k < X[i].getColumnDimension(); k++) {
                    double val = X[i].get(j, k) * Math.sqrt(eigenvalueList.get(i));
                    X[i].set(j, k, val);
                }
            }
        }

        println "U Matrices"
        U.each {
            it.print(4, 3)
        }

//        println "V Matrices V.radice(lambda)"
//        V.each {
//            it.print(4, 3)
//        }

        println "Elementary SVD Matrices"
        X.each {
            it.print(4, 3)
            println "RANK: "+ it.rank()
        }



    }

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

        SSA analysis = new SSA(input, 4)


        double[] component = analysis.getEigenComponent(0)

        println "component = $component"

    }

}