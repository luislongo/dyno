package com.alura.dyno.maths.linalg;

import cern.colt.matrix.tfloat.FloatMatrix2D;
import cern.colt.matrix.tfloat.algo.DenseFloatAlgebra;
import cern.colt.matrix.tfloat.impl.DenseFloatMatrix2D;

public abstract class FloatMatrix<DIM extends FloatMatrix> {
    protected FloatMatrix2D data;

    public FloatMatrix(int nrOfRows, int nrOfCols) {
        this.data = new DenseFloatMatrix2D(nrOfRows, nrOfCols);
    }
    public FloatMatrix(int nrOfRows, int nrOfCols, float value) {
        this(nrOfRows, nrOfCols);
        data.assign(value);
    }
    public FloatMatrix(int nrOfRows, int nrOfCols, float[] values) {
        this(nrOfRows, nrOfCols);
        data.assign(values);
    }
    public FloatMatrix(FloatMatrix other) {
        this.data = new DenseFloatMatrix2D(other.data.toArray());
    }

    public int rows() {
        return data.rows();
    }
    public int cols() {
        return data.columns();
    }
    public int count() {
        return (int) data.size();
    }
    public boolean isSquare() {
        return (rows() == cols());
    }
    public FloatMatrix2D getData() {
        return this.data;
    }

    public DIM invert() {
        DenseFloatAlgebra fun = Algebra.denseFloat();
        this.data.assign(fun.inverse(data));

        return (DIM) this;
    }
    public DIM transpose() {
        DenseFloatAlgebra fun = Algebra.denseFloat();
        this.data.assign(fun.transpose(data));

        return (DIM) this;
    }
    public DIM preMultiply(DIM m_lhs) {
        DenseFloatAlgebra fun = Algebra.denseFloat();
        this.data.assign(fun.mult(m_lhs.data, this.data));

        return (DIM) this;
    }
    public DIM postMultiply(DIM m_rhs) {
        DenseFloatAlgebra fun = Algebra.denseFloat();
        this.data.assign(fun.mult(this.data, m_rhs.data));

        return (DIM) this;
    }

    public abstract DIM clone();

    @Override public String toString() {
        return data.toString();
    }
    public float[][] toArray2D() {
        return this.data.toArray();
    }

}
