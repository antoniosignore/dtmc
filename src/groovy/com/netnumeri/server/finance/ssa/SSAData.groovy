package com.netnumeri.server.finance.ssa;

import Jama.Matrix

public class SSAData {

    private List<Double> timeSeries;    //the original time series
    private int L;                        //window length
    private double[][] inclosureMatrix; // matrix attachment
    private Matrix[] X; // elementary matrix singular value decomposition
    private Matrix[] groupX; // the resulting matrix for each of the groups
    private Matrix[] V; //the main components of singular value decomposition
    private List<Double> reconstructionList;// number of recovered
    private List<Double> SMA; // Moving Average
    private List<Double> cov; //averaging diagonal covariance
    private List<Double> eigenValueList;  // eigenvalues
    private List<Double> lgEigenValue; // logarithms of eigenvalues
    private List<Double> sqrtEigenValue;// roots of the eigenvalues
    private List eigenVectors; //eigenvectors
    private List<Double> percentList; // interest sobtvennyh numbers
    private List<Double> accruePercentList;// accrued interest eigenvalues


    public SSAData() {
        timeSeries = new ArrayList<Double>();
        L = 2;
    }

    public List getEigenVectors() {
        return eigenVectors;
    }

    public void setEigenVectors(List eigenVectors) {
        this.eigenVectors = eigenVectors;
    }

    public Matrix[] getV() {
        return V;
    }

    public void setV(Matrix[] V) {
        this.V = V;
    }

    public List<Double> getTimeSeries() {
        return timeSeries;
    }

    public void setTimeSeries(List<Double> timeSeries) {
        this.timeSeries = timeSeries;
    }

    public int getL() {
        return L;
    }

    public void setL(int L) {
        this.L = L;
    }

    public double[][] getInclosureMatrix() {
        return inclosureMatrix;
    }

    public void setInclosureMatrix(double[][] matrix) {
        inclosureMatrix = matrix;
    }

    public Matrix[] getX() {
        return X;
    }

    public void setX(Matrix[] X) {
        this.X = X;
    }

    public List<Double> getReconstructionList() {
        return reconstructionList;
    }

    public void setReconstructionList(List<Double> reconstructionList) {
        this.reconstructionList = reconstructionList;
    }

    public List<Double> getSMA() {
        return SMA;
    }

    public void setSMA(List<Double> SMA) {
        this.SMA = SMA;
    }

    public List<Double> getCov() {
        return cov;
    }

    public void setCov(List<Double> cov) {
        this.cov = cov;
    }

    public void setLgEigenValue(List<Double> lgEigenValue) {
        this.lgEigenValue = lgEigenValue;
    }

    public List<Double> getLgEigenValue() {
        return lgEigenValue;
    }

    public void setSqrtEigenValue(List<Double> sqrtEigenValue) {
        this.sqrtEigenValue = sqrtEigenValue;
    }

    public List<Double> getSqrtEigenValue() {
        return sqrtEigenValue;
    }

    public List<Double> getEigenValueList() {
        return eigenValueList;
    }

    public void setEigenValueList(List<Double> eigenValueList) {
        this.eigenValueList = eigenValueList;
    }

    public List<Double> getAccruePercentList() {
        return accruePercentList;
    }

    public void setAccruePercentList(List<Double> accruePercentList) {
        this.accruePercentList = accruePercentList;
    }

    public List<Double> getPercentList() {
        return percentList;
    }

    public void setPercentList(List<Double> percentList) {
        this.percentList = percentList;
    }


    public Matrix[] getGroupX() {
        return groupX;
    }

    public void setGroupX(Matrix[] groupX) {
        this.groupX = groupX;
    }
}
