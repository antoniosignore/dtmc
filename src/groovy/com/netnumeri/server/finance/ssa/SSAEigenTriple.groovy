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
}
