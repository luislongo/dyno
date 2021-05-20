package com.alura.dyno.math.linalg;

import java.util.Random;

public class DenseDoubleMatrixFactory {
    public DenseDoubleMatrixFactory() {}

    public DenseDoubleMatrix random(int rows, int cols) {
        DenseDoubleMatrix m = new DenseDoubleMatrix(rows, cols);

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                m.data.setQuick(row, col, (new Random()).nextDouble());
            }
        }

        return m;
    }
    public DenseDoubleMatrix identity(int size) {
        DenseDoubleMatrix m = new DenseDoubleMatrix(size, size);

        for(int i = 0; i < size; i++) {
            m.data.setQuick(i, i,1.0d);
        }

        return m;
    }
}