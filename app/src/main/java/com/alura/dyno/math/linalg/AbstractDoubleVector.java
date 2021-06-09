package com.alura.dyno.math.linalg;

import androidx.annotation.Nullable;

import org.jetbrains.annotations.NotNull;

import java.util.Arrays;

import cern.colt.Sorting;
import cern.colt.function.tdouble.DoubleDoubleFunction;
import cern.colt.function.tdouble.DoubleDoubleProcedure;
import cern.colt.function.tdouble.DoubleFunction;
import cern.colt.function.tdouble.DoubleProcedure;
import cern.colt.function.tint.IntComparator;
import cern.colt.matrix.tdouble.DoubleMatrix1D;
import cern.colt.matrix.tdouble.impl.DenseDoubleMatrix1D;
import cern.colt.matrix.tdouble.impl.DenseDoubleMatrix2D;

import static cern.jet.math.tdouble.DoubleFunctions.div;
import static cern.jet.math.tdouble.DoubleFunctions.minus;
import static cern.jet.math.tdouble.DoubleFunctions.mult;
import static cern.jet.math.tdouble.DoubleFunctions.plus;

public abstract class AbstractDoubleVector<DIM extends AbstractDoubleVector> implements Cloneable {
    protected DenseDoubleMatrix1D data;

    public AbstractDoubleVector(int size) {
        data = new DenseDoubleMatrix1D(size);
    }
    public AbstractDoubleVector(int size, double value) {
        data = new DenseDoubleMatrix1D(size);
        data.assign(value);
    }
    public AbstractDoubleVector(double... values) {
        data = new DenseDoubleMatrix1D(values);
    }
    public AbstractDoubleVector(DIM other) {
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

    public final double get(int i) {
        return data.get(i);
    }
    public DIM getRange(int i0, int i1) {
        return this.like(this.data.viewPart(i0,i1 - i0 + 1));
    }
    public DIM set(int i, double value) {
        data.set(i, value);

        return (DIM) this;
    }
    public DIM setRange(int i0, int i1, double... values) {
        data.viewPart(i0, i1).assign(values);

        return (DIM) this;
    }
    public DIM setRange(int i0, int i1, DoubleVectorN values) {
        data.viewPart(i0,i1).assign(values.data);
        return (DIM) this;
    }
    public DIM setValues(double[] x_i) {
        data.assign(x_i);
        return (DIM) this;
    }

    public DIM normalize() {
        double norm2 = norm2();

        if(norm2 != 0) {
            divide(norm2());
        }

        return (DIM) this;
    }
    public DIM plus(@NotNull AbstractDoubleVector v_rhs) {
        data.assign(v_rhs.data, plus);
        return (DIM) this;
    }
    public DIM minus(@NotNull AbstractDoubleVector v_rhs) {
        data.assign(v_rhs.data, minus);
        return (DIM) this;
    }
    public DIM multiply(double c) {
        data.assign(mult(c));
        return (DIM) this;
    }
    public DIM divide(double c) {
        if(c != 0) {
            data.assign(div(c));
            return (DIM) this;
        } else {
            throw new IllegalArgumentException();
        }
    }
    public DIM straightProduct(@NotNull AbstractDoubleVector v_rhs) {
        data.assign(v_rhs.data, mult);
        return (DIM) this;
    }
    public DIM straightDivision(@NotNull AbstractDoubleVector v_rhs) {
        data.assign(v_rhs.data, div);
        return (DIM) this;
    }
    public DIM applyFunction(DoubleFunction proc) {
        data.assign(proc);
        return (DIM) this;
    }
    public double dotProduct(@NotNull AbstractDoubleVector v_rhs) {
        return data.zDotProduct(v_rhs.data);
    }
    public DIM multiply(@NotNull DoubleMatrix m_lhs) {
        this.data.assign(Algebra.denseDoubleAlgebra().mult(m_lhs.data, this.data));
        return (DIM) this;
    }
    public int[] sort() {
        int[] idx = new int[(int) data.size()];
        for(int i = 0; i < idx.length; i++) {
            idx[i] = i;
        }

        Sorting.parallelQuickSort(idx, 0, idx.length, new IntComparator() {
            @Override
            public int compare(int i, int i1) {
                if(data.getQuick(i) < data.getQuick(i1)) {
                    return -1;
                };
                if(data.getQuick(i) == data.getQuick(i1)) {
                    return 0;
                };
                if(data.getQuick(i) > data.getQuick(i1)) {
                    return 1;
                };

                throw new RuntimeException("Comparison has failed");
            }
        });

        data.assign(data.viewSelection(idx));
        return idx;
    }

    public abstract DIM clone();
    public abstract DIM like(DoubleMatrix1D data);

    @Override public boolean equals(@Nullable Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof AbstractFloatVector)) {
            return false;
        }
        AbstractDoubleVector fv = (AbstractDoubleVector) obj;
        return Arrays.equals(this.toArray(), fv.toArray());
    }
    @NotNull @Override public final String toString() {
        return data.toString();
    }
}
