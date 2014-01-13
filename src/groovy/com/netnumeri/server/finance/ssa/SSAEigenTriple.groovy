package com.netnumeri.server.finance.ssa

import Jama.Matrix

class SSAEigenTriple {
    double singularValue
    Matrix U
    Matrix Vt

    public Matrix getTriple() {
        Matrix times = U.times(Math.sqrt(singularValue)).times(Vt)
        return times
    }

    @Override
    public String toString() {
        return "SSAEigenTriple{" +
                "\tsingularValue=" + singularValue +
                "\t, U=" + U.print(3,2) +
                "\t, Vt=" + Vt.print(3,2) +
                '}';
    }
}
