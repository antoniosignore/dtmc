package com.netnumeri.server.finance.ssa

public class SSAAnalysis {

    private SSAData data = new SSAData();

    public SSAAnalysis(List<Double> s, Integer window) {

        data.setTimeSeries(s);

        data.setL(window);

        SpectrumAnalysis.inclosure(data);
        SpectrumAnalysis.singularDecomposition(data);
        SpectrumAnalysis.setMovingAvarege(data);
        SpectrumAnalysis.averagedCovariance(data);
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