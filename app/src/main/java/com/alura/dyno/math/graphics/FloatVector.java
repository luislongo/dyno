package com.alura.dyno.math.graphics;

import androidx.annotation.Nullable;

import com.alura.dyno.math.linalg.Algebra;

import org.jetbrains.annotations.NotNull;

import java.util.Arrays;

import cern.colt.matrix.tfloat.FloatMatrix1D;
import cern.colt.matrix.tfloat.impl.DenseFloatMatrix1D;

import static cern.jet.math.tfloat.FloatFunctions.div;
import static cern.jet.math.tfloat.FloatFunctions.minus;
import static cern.jet.math.tfloat.FloatFunctions.mult;
import static cern.jet.math.tfloat.FloatFunctions.plus;

public abstract class FloatVector<DIM extends FloatVector> {
    protected FloatMatrix1D data;

    public FloatVector(int size) {
        data = new DenseFloatMatrix1D(size);
    }
    public FloatVector(int size, float value) {
        data = new DenseFloatMatrix1D(size).assign(value);
    }
    public FloatVector(float[] x_i) {
        data = new DenseFloatMatrix1D(x_i);
    }

    public final float norm2() {
        return Algebra.denseFloat().norm2(data);
    }
    public final int length() {
        return (int) data.size();
    }
    public final float[] toArray() {
        return data.toArray();
    }

    protected final float getX_(int i) {
        return data.get(i);
    }
    protected final DIM setX_(int i, float value) {
        data.set(i, value);

        return (DIM) this;
    }
    protected final DIM setValues(float[] x_i) {
        data.assign(x_i);

        return (DIM) this;
    }

    public DIM plus(@NotNull DIM v_rhs) {
        data.assign(v_rhs.data, plus);
        return (DIM) this;
    }
    public DIM minus(@NotNull DIM v_rhs) {
        data.assign(v_rhs.data, minus);
        return (DIM) this;
    }
    public DIM multiply(float c) {
        data.assign(mult(c));
        return (DIM) this;
    }
    public DIM divide(float c) {
        if(c != 0) {
            data.assign(div(c));
            return (DIM) this;
        } else {
            throw new IllegalArgumentException();
        }
    }
    public DIM straightProduct(@NotNull DIM v_rhs) {
        data.assign(v_rhs.data, mult);
        return (DIM) this;
    }
    public float dotProduct(DIM v_rhs) {
        return data.zDotProduct(v_rhs.data);
    }

    protected DIM multiply(@NotNull FloatMatrix m_lhs) {
        this.data.assign(Algebra.denseFloat().mult(m_lhs.getData(), this.data));
        return (DIM) this;
    }
    public DIM normalize() {
        float norm2 = norm2();

        if(norm2 != 0) {
            divide(norm2());
        }

        return (DIM) this;
    }

    public abstract DIM clone();

    @Override public boolean equals(@Nullable Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof FloatVector)) {
            return false;
        }
        FloatVector fv = (FloatVector) obj;

        return Arrays.equals(this.toArray(), fv.toArray());
    }
    @NotNull @Override public final String toString() {
        return data.toString();
    }
}
