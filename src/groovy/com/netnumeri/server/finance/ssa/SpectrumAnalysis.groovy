package com.netnumeri.server.finance.ssa;

import Jama.EigenvalueDecomposition;
import Jama.Matrix

public class SpectrumAnalysis {

    /**
     * translation of the original time series into a sequence of multi-dimensional
     * vectors
     */
    public static void inclosure(SSAData data) {
        int L = data.getL(); //window
        int K = data.getTimeSeries().size() - L + 1; //the number of vectors attachments
        double[][] inclosureMatrix = new double[L][K]; //trajectory matrix
        for (int i = 1; i <= K; i++) {
            int num = 0;
            for (int j = i - 1; j <= i + L - 2; j++) {
                inclosureMatrix[num][i - 1] = data.getTimeSeries().get(j);
                num++;
            }
        }
        data.setInclosureMatrix(inclosureMatrix);
    }

    /**
     * singular value decomposition
     *
     */
    public static void singularDecomposition(SSAData data) {
        double[][] inclosureMatrix = data.getInclosureMatrix();
        double[][] transp = transpositionMatrix(inclosureMatrix);
        Matrix S = new Matrix(inclosureMatrix).times(new Matrix(transp));
        EigenvalueDecomposition decomposition = new EigenvalueDecomposition(S);
        Matrix eigenvalue = decomposition.getD();   //matrix with eigenvalues
        Matrix eigenvec = decomposition.getV();     //matrix of eigenvectors

        eigenvec.print(4,3)

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
        data.setEigenValueList(eigenvalueList);
        double sumValueList = 0;
        List<Double> percentList;
        List<Double> accruePercentList;
        for (int i = 0; i < data.getEigenValueList().size(); i++) {
            sumValueList = sumValueList + data.getEigenValueList().get(i);
        }
        //formation percent eigenvalues ​​and accrued interest
        percentList = new ArrayList<Double>();
        accruePercentList = new ArrayList<Double>();
        double accruePercent = 0;
        for (int i = 0; i < data.getEigenValueList().size(); i++) {
            percentList.add(data.getEigenValueList().get(i) / sumValueList * 100);
            accruePercent += percentList.get(i);
            accruePercentList.add(accruePercent);
        }
        data.setAccruePercentList(accruePercentList);
        data.setPercentList(percentList);

        int size = eigenvec.getColumnDimension();
        Matrix[] V = new Matrix[size];
        Matrix[] U = new Matrix[size];
        Matrix[] X = new Matrix[size]; //elementary matrix singular value decomposition
        ArrayList listSeries = new ArrayList();
        for (int j = 0; j < eigenvec.getColumnDimension(); j++) {
            double[][] uVec = new double[size][1];
            ArrayList series = new ArrayList();
            for (int k = 0; k < eigenvec.getRowDimension(); k++) {
                /*
                 * vectors must match the number of its own (!), so
                 * starting with the last eigenvector
                 */
                uVec[k][0] = eigenvec.get(k, eigenvec.getColumnDimension() - j - 1);
                series.add(uVec[k][0]);
            }
            listSeries.add(series);
            U[j] = new Matrix(uVec);
            V[j] = new Matrix(transp).times(U[j]);
        }
        data.setEigenVectors(listSeries);
        for (int i = 0; i < V.length; i++) {
            for (int j = 0; j < V[i].getRowDimension(); j++) {
                for (int k = 0; k < V[i].getColumnDimension(); k++) {
                    double val = V[i].get(j, k) / Math.sqrt(eigenvalueList.get(i));
                    V[i].set(j, k, val);
                }
            }
        }
        data.setV(V);
        for (int i = 0; i < X.length; i++) {
            X[i] = U[i].times(V[i].transpose());
            for (int j = 0; j < X[i].getRowDimension(); j++) {
                for (int k = 0; k < X[i].getColumnDimension(); k++) {
                    double val = X[i].get(j, k) * Math.sqrt(eigenvalueList.get(i));
                    X[i].set(j, k, val);
                }
            }
        }
        data.setX(X);
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
    public static void diagonalAveraging(SSAData data) {
        int L;
        int K;
        int N;
        List<List> list = new ArrayList<List>();
        for (int i = 0; i < data.getGroupX().length; i++) {
            if (data.getGroupX()[i].getRowDimension() < data.getGroupX()[i].getColumnDimension()) {
                L = data.getGroupX()[i].getRowDimension();
                K = data.getGroupX()[i].getColumnDimension();
            } else {
                K = data.getGroupX()[i].getRowDimension();
                L = data.getGroupX()[i].getColumnDimension();
            }
            N = data.getGroupX()[i].getRowDimension() + data.getGroupX()[i].getColumnDimension() - 1;
            List series = new ArrayList();
            double element;
            for (int k = 0; k <= N - 1; k++) {
                element = 0;
                if (k >= 0 && k < L - 1) {
                    for (int m = 0; m <= k; m++) {
                        if (data.getGroupX()[i].getRowDimension() <= data.getGroupX()[i].getColumnDimension()) {
                            element += data.getGroupX()[i].get(m, k - m);
                        } else if (data.getGroupX()[i].getRowDimension() > data.getGroupX()[i].getColumnDimension()) {
                            element += data.getGroupX()[i].get(k - m, m);
                        }
                    }
                    element = element * (1.0 / (k + 1));
                    series.add(element);
                }
                if (k >= L - 1 && k < K - 1) {
                    for (int m = 0; m <= L - 2; m++) {
                        if (data.getGroupX()[i].getRowDimension() <= data.getGroupX()[i].getColumnDimension()) {
                            element += data.getGroupX()[i].get(m, k - m);
                        } else if (data.getGroupX()[i].getRowDimension() > data.getGroupX()[i].getColumnDimension()) {
                            element += data.getGroupX()[i].get(k - m, m);
                        }
                    }
                    element = element * (1.0 / L);
                    series.add(element);
                }
                if (k >= K - 1 && k < N) {
                    for (int m = k - K + 1; m <= N - K; m++) {
                        if (data.getGroupX()[i].getRowDimension() <= data.getGroupX()[i].getColumnDimension()) {
                            element += data.getGroupX()[i].get(m, k - m);
                        } else if (data.getGroupX()[i].getRowDimension() > data.getGroupX()[i].getColumnDimension()) {
                            element += data.getGroupX()[i].get(k - m, m);
                        }
                    }
                    element = element * (1.0 / (N - k));
                    series.add(element);
                }
            }
            list.add(series);
        }
        double sum;
        //Summing the series and get the original series
        List<Double> reconstructionList = new ArrayList<Double>();
        for (int j = 0; j < list.get(0).size(); j++) {
            sum = 0;
            for (int i = 0; i < list.size(); i++) {
                sum += (Double) list.get(i).get(j);
            }
            reconstructionList.add(sum);
        }
        data.setReconstructionList(reconstructionList);
        println "reconstructionList = $reconstructionList"

    }

    private static double[][] transpositionMatrix(double[][] matrix) {
        double[][] transpMatrix = new double[matrix[0].length][matrix.length];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                transpMatrix[j][i] = matrix[i][j];
            }
        }
        return transpMatrix;
    }

    public static void setMovingAvarege(SSAData data) {
        List<Double> SMA = new ArrayList<Double>();
        int m = data.getTimeSeries().size() - data.getL() + 1;
        for (int i = 0; i < data.getL(); i++) {
            double sum = 0;
            double avg = 0;
            for (int j = i; j < m + i; j++) {
                sum += data.getTimeSeries().get(j);
            }
            avg = sum / m;
            SMA.add(avg);
            data.setSMA(SMA);
        }
    }

    /**
     * diagonal covariance matrix averaging
     * (with respect to the secondary diagonal)
     */
    public static void averagedCovariance(SSAData data) {
        double avg;
        double K = data.getTimeSeries().size() - data.getL() + 1; //the number of vectors attachments
        List<Double> covarianceList = new ArrayList<Double>();
        double[][] transp = transpositionMatrix(data.getInclosureMatrix());
        Matrix S = new Matrix(data.getInclosureMatrix()).times(new Matrix(transp));
        S = S.times(1.0 / K); //covariance matrix
        int size = S.getColumnDimension();
        int N = size + size - 1;
        int n;
        for (int k = 0; k < N; k++) {
            if ((k % 2) == 0) {
                if (k >= 0 && k < size) {
                    avg = 0;
                    n = 0;
                    for (int m = 0; m <= k; m++) {
                        avg += S.get(m, size - 1 - (k - m));
                        n++;
                    }
                    avg = avg / (n);
                    covarianceList.add(avg);
                }
                if (k >= size && k < N) {
                    avg = 0;
                    n = 0;
                    for (int m = k - size + 1; m <= N - size; m++) {
                        avg += S.get(m, size - 1 - (k - m));
                        n++;
                    }
                    avg = avg / (n);
                    covarianceList.add(avg);
                }
            }
        }
        data.setCov(covarianceList);
    }

    /**
     * forming functions of the eigenvalues
     *
     * @param data data for analysis
     */
    public static void functionEigenValue(SSAData data) {
        List<Double> lgList = new ArrayList<Double>();
        List<Double> sqrtList = new ArrayList<Double>();
        for (int i = 0; i < data.getEigenValueList().size(); i++) {
            lgList.add((Double) Math.log(data.getEigenValueList().get(i)));
            sqrtList.add(Math.sqrt(data.getEigenValueList().get(i)));
        }
        data.setLgEigenValue(lgList);
        data.setSqrtEigenValue(sqrtList);
    }

}
