package com.alura.dyno.math.linalg;

import java.util.Arrays;
import java.util.List;

import cern.colt.Sorting;
import cern.colt.function.tdouble.DoubleComparator;
import cern.colt.function.tint.IntComparator;
import cern.colt.matrix.tdouble.DoubleMatrix1D;

public class DoubleVectorN extends AbstractDoubleVector<DoubleVectorN> {
    public DoubleVectorN(double... values) {
        super(values);
    }
    public DoubleVectorN(int length, double value) {
        super(length, value);
    }
    public DoubleVectorN(DoubleVectorN other) {
        super(other.data.toArray());
    }
    public DoubleVectorN(DoubleMatrix1D data) {
        super(data.toArray());
    }

    @Override public DoubleVectorN clone() {
        return new DoubleVectorN(this);
    }
    @Override public DoubleVectorN like(DoubleMatrix1D data) {
        return new DoubleVectorN(data);
    }

    public static DoubleVectorN intRange(int start, int end) {
        int length = end - start + 1;
        double[] values = new double[end - start + 1];

        for(int i = 0; i < length; i++) {
            values[i] = start + i;
        }

        return new DoubleVectorN(values);
    }
    public static DoubleVectorN reorder(DoubleVectorN data, int[] order) {
        return new DoubleVectorN(data.data.viewSelection(order).toArray());
    }
}
