package com.alura.dyno.math.linalg;

import cern.colt.matrix.tdouble.DoubleFactory1D;
import cern.colt.matrix.tdouble.DoubleFactory2D;
import cern.colt.matrix.tdouble.DoubleMatrix1D;
import cern.colt.matrix.tdouble.DoubleMatrix2D;
import cern.colt.matrix.tdouble.impl.DenseDoubleMatrix1D;
import cern.colt.matrix.tdouble.impl.DenseDoubleMatrix2D;

public class DoubleMatrixFactory {
    private DoubleFactory1D factory1D;
    private DoubleFactory2D factory2D;

    public DoubleMatrixFactory() {
        factory2D = DoubleFactory2D.dense;
        factory1D = DoubleFactory1D.dense;
    }
    public DoubleMatrix makeDiagonal(DoubleVectorN v) {
        return new DoubleMatrix((DenseDoubleMatrix2D) factory2D.diagonal(v.data));
    }
    public DoubleMatrix makeDiagonal(int i, DoubleVectorN v) {
        int patchSize = (i < 0)? -i : i;

        DoubleMatrix2D reduced = factory2D.diagonal(v.data);
        DoubleMatrix2D hPatch = factory2D.make(patchSize, v.length());
        DoubleMatrix2D vPatch = factory2D.make(v.length(), patchSize);
        DoubleMatrix2D cPatch = factory2D.make(patchSize, patchSize);

        DoubleMatrix2D data;
        if(i >= 0) {
            data = factory2D.compose(new DoubleMatrix2D[][] {
                    {vPatch, reduced},
                    {cPatch, hPatch}});
        } else {
            data = factory2D.compose(new DoubleMatrix2D[][] {
                    {hPatch,  cPatch},
                    {reduced, vPatch }});
        }

        return new DoubleMatrix(data.toArray());
    }
    public DoubleMatrix kronecker(DoubleMatrix lhs, DoubleMatrix rhs) {
        DoubleMatrix C = new DoubleMatrix(lhs.rows() * rhs.rows(), lhs.cols() * rhs.cols());

        for (int i = 0; i < lhs.rows(); i++) {
            for (int k = 0; k < rhs.rows(); k++) {
                for (int j = 0; j < lhs.cols(); j++) {
                    for (int l = 0; l < rhs.cols(); l++)
                    {
                        C.setCell(i + l + 1, j + k + 1, lhs.getCell(i,j) * rhs.getCell(k, l));
                    }
                }
            }
        }
        return C;
    }
    public static DoubleMatrix kronecker(DoubleVectorN lhs, DoubleVectorN rhs) {
        DoubleMatrix C = new DoubleMatrix(1, lhs.length() * rhs.length());

        for (int j = 0; j < lhs.length(); j++) {
            for (int l = 0; l < rhs.length(); l++) {
                C.setCell(l + 1, j + 1, lhs.get(j) * rhs.get(l));
            }
        }

        return C;
    }
}
