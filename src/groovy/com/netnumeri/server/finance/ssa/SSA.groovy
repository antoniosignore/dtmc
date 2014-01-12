package com.netnumeri.server.finance.ssa

import Jama.*

public class SSA {
    private Integer N;
    private Integer L;
    private Integer K;
    private Matrix T = null;
    private Matrix pcaMatrix = null;

    public List<SSAEigenTriple> triples = new ArrayList<SSAEigenTriple>()

    public SSA(double[] s, int window) {
        N = s.size();
        L = window
        K = N - L + 1;
        T = new Matrix(K, L);

        for (int i = 0; i < K; i++) {
            for (int k = 0; k < L; k++) {
                T.set(i, k, s[i + k]);
            }
        }

        SingularValueDecomposition svd = T.svd();

        Matrix D = svd.getS();
        Matrix U = svd.getU();
        Matrix V = svd.getV();
        Matrix VTranspose = V.transpose();

//        Matrix reconstructed = U.times(D).times(VTranspose)
//        reconstructed.print(4, 2)
//        Assert.assertEquals(T, reconstructed)

        double[] singularValues = svd.singularValues;

        for (int i = 0; i < singularValues.length; i++) {
            SSAEigenTriple triple = new SSAEigenTriple()
            double singularValue = singularValues[i];
            triple.singularValue = Math.sqrt(singularValue)
            Matrix columnMatrix = getColumnMatrix(U, i)
            triple.U = columnMatrix     // EOF
            Matrix rowMatrix = getRowMatrix(VTranspose, i)
            triple.Vt = rowMatrix
            triples.add(triple);
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