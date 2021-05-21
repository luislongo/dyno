package com.alura.dyno.math.linalg;

import java.util.Random;

public class DenseDoubleMatrixFactory {
    public DenseDoubleMatrixFactory() {}

    public DoubleMatrix random(int rows, int cols) {
        DoubleMatrix m = new DoubleMatrix(rows, cols);

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                m.data.setQuick(row, col, (new Random()).nextDouble());
            }
        }

        return m;
    }
    public DoubleMatrix identity(int size) {
        DoubleMatrix m = new DoubleMatrix(size, size);

        for(int i = 0; i < size; i++) {
            m.data.setQuick(i, i,1.0d);
        }

        return m;
    }
}