package com.netnumeri.server.finance.ssa;

import Jama.Matrix

public class SSAData {

      List<Double> timeSeries;    //the original time series
      int L;                        //window length
      double[][] inclosureMatrix; // matrix attachment
      Matrix[] X; // elementary matrix singular value decomposition
      Matrix[] groupX; // the resulting matrix for each of the groups
      Matrix[] V; //the main components of singular value decomposition
      List<Double> reconstructionList;// number of recovered
      List<Double> SMA; // Moving Average
      List<Double> cov; //averaging diagonal covariance
      List<Double> eigenValueList;  // eigenvalues
      List<Double> lgEigenValue; // logarithms of eigenvalues
      List<Double> sqrtEigenValue;// roots of the eigenvalues
     List eigenVectors; //eigenvectors
      List<Double> percentList; // interest sobtvennyh numbers
      List<Double> accruePercentList;// accrued interest eigenvalues


    public SSAData() {
        timeSeries = new ArrayList<Double>();
        L = 2;
    }

}
