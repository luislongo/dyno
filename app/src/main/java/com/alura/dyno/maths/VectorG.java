package com.alura.dyno.maths;

import cern.colt.matrix.tfloat.algo.DenseFloatAlgebra;
import cern.colt.matrix.tfloat.impl.DenseFloatMatrix1D;

import static cern.jet.math.tfloat.FloatFunctions.minus;
import static cern.jet.math.tfloat.FloatFunctions.mult;
import static cern.jet.math.tfloat.FloatFunctions.plus;

public abstract class VectorG<T extends VectorG> extends DenseFloatMatrix1D {
    private static final DenseFloatAlgebra fun = new DenseFloatAlgebra();

    public VectorG(int n) {
        super(n);
    }
    public VectorG(int n, float[] floats) {
        this(n);
        assign(floats);
    }
    public VectorG(T v_other) {
        this(v_other.size, v_other.toArray());
    }

    protected void setValues(float[] floats) {
        assign(floats);
    }

    public float length() { return fun.norm2(this); }
    public void normalize() {
        super.normalize();
    }

    public T plus(T v_other) {
        assign(v_other, plus);

        return (T) this;
    }
    public T minus(T v_right) {
        assign(v_right, minus);

        return (T) this;
    }
    public T multiply(float c) {
        assign(mult(c));

        return (T) this;
    }
    public T multiply(T v_other) {
        assign(v_other, mult);

        return (T) this;
    }
    public T divide(float c) {
        return multiply(1.0f/c);
    }
}
