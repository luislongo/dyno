package com.alura.dyno.math.linalg;

import androidx.annotation.Nullable;

import org.jetbrains.annotations.NotNull;

import java.util.Arrays;

import cern.colt.matrix.tdouble.DoubleMatrix1D;
import cern.colt.matrix.tdouble.impl.DenseDoubleMatrix1D;
import cern.colt.matrix.tfloat.impl.DenseFloatMatrix1D;

import static cern.jet.math.tdouble.DoubleFunctions.div;
import static cern.jet.math.tdouble.DoubleFunctions.minus;
import static cern.jet.math.tdouble.DoubleFunctions.mult;
import static cern.jet.math.tdouble.DoubleFunctions.plus;


public class DoubleVector {
    protected DoubleMatrix1D data;

    public DoubleVector(int size) {
        data = new DenseDoubleMatrix1D(size);
    }
    public DoubleVector(int size, double value) {
        data = new DenseDoubleMatrix1D(size).assign(value);
    }
    public DoubleVector(double... values) {
        data = new DenseDoubleMatrix1D(values);
    }
    public DoubleVector(DoubleVector other) {
        this(other.data.toArray());
    }

    public final double norm2() {
        return Algebra.denseDoubleAlgebra().norm2(data);
    }
    public final int length() {
        return (int) data.size();
    }
    public final double[] toArray() {
        return data.toArray();
    }

    protected final double get(int i) {
        return data.get(i);
    }
    protected final DoubleVector set(int i, double value) {
        data.set(i, value);

        return (DoubleVector) this;
    }
    protected final DoubleVector setValues(double[] x_i) {
        data.assign(x_i);
        return (DoubleVector) this;
    }

    public DoubleVector normalize() {
        double norm2 = norm2();

        if(norm2 != 0) {
            divide(norm2());
        }

        return this;
    }
    public DoubleVector plus(@NotNull DoubleVector v_rhs) {
        data.assign(v_rhs.data, plus);
        return this;
    }
    public DoubleVector minus(@NotNull DoubleVector v_rhs) {
        data.assign(v_rhs.data, minus);
        return this;
    }
    public DoubleVector multiply(double c) {
        data.assign(mult(c));
        return this;
    }
    public DoubleVector divide(double c) {
        if(c != 0) {
            data.assign(div(c));
            return this;
        } else {
            throw new IllegalArgumentException();
        }
    }
    public DoubleVector straightProduct(@NotNull DoubleVector v_rhs) {
        data.assign(v_rhs.data, mult);
        return this;
    }
    public DoubleVector straightDivision(@NotNull DoubleVector v_rhs) {
        data.assign(v_rhs.data, div);
        return this;
    }
    public double dotProduct(@NotNull DoubleVector v_rhs) {
        return data.zDotProduct(v_rhs.data);
    }
    protected DoubleVector multiply(@NotNull DoubleMatrix m_lhs) {
        this.data.assign(Algebra.denseDoubleAlgebra().mult(m_lhs.data, this.data));
        return this;
    }

    public DoubleVector clone() {
        return new DoubleVector(this);
    }

    @Override public boolean equals(@Nullable Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof AbstractFloatVector)) {
            return false;
        }
        DoubleVector fv = (DoubleVector) obj;
        return Arrays.equals(this.toArray(), fv.toArray());
    }
    @NotNull @Override public final String toString() {
        return data.toString();
    }
}
