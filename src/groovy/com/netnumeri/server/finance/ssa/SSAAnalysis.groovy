package com.netnumeri.server.finance.ssa

public class SSAAnalysis {

    private SSAData data = new SSAData();

    public SSAAnalysis(List<Double> s, Integer window) {

        println "pippo 1000"
        data.setTimeSeries(s);

        println "pippo 1001"
        data.setL(window);

        println "pippo 1002"
        SpectrumAnalysis.inclosure(data);

        println "pippo 1003"
        SpectrumAnalysis.singularDecomposition(data);

        println "pippo 1004"
        SpectrumAnalysis.setMovingAvarege(data);

        println "pippo 1005"
        SpectrumAnalysis.averagedCovariance(data);

        println "pippo 1006"
        SpectrumAnalysis.functionEigenValue(data);
    }

    List<Double> getEigenComponent(int i) {
        List<Integer> g = new ArrayList<Integer>();
        g.add(i);
        SpectrumAnalysis.grouping(g, data);
        SpectrumAnalysis.diagonalAveraging(data);
        List<Double> reconstructionList = data.getReconstructionList();
        return reconstructionList
    }

    List<Double> getEigenComponents(List<Integer> components) {
        SpectrumAnalysis.grouping(components, data);
        SpectrumAnalysis.diagonalAveraging(data);
        List<Double> reconstructionList = data.getReconstructionList();
        return reconstructionList
    }

    public int getWindow() {
        return data.getL()
    }

}