package com.alura.dyno.math.linalg;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

class DoubleMatrixFactoryTest {

    @Test public void testMakeDiagonal_1() {
        DoubleMatrix actual = Algebra.doubleMatrixFactory().makeDiagonal(new DoubleVectorN(
                1,2,3,4,5,6
        ));
        DoubleMatrix expected = new DoubleMatrix(new double[][] {
                {1, 0, 0, 0, 0, 0},
                {0, 2, 0, 0, 0, 0},
                {0, 0, 3, 0, 0, 0},
                {0, 0, 0, 4, 0, 0},
                {0, 0, 0, 0, 5, 0},
                {0, 0, 0, 0, 0, 6}});

        assertArrayEquals(expected.toArray2D(), actual.toArray2D());
    }
    @Test public void testMakeDiagonal_2() {
        DoubleMatrix actual = Algebra.doubleMatrixFactory().makeDiagonal(1, new DoubleVectorN(
                2,3,4,5,6
        ));
        DoubleMatrix expected = new DoubleMatrix(new double[][] {
                {0, 0, 0, 0, 0, 0},
                {0, 2, 0, 0, 0, 0},
                {0, 0, 3, 0, 0, 0},
                {0, 0, 0, 4, 0, 0},
                {0, 0, 0, 0, 5, 0},
                {0, 0, 0, 0, 0, 6}});

        assertArrayEquals(expected.toArray2D(), actual.toArray2D());
    }
    @Test public void testMakeDiagonal_3() {
        DoubleMatrix actual = Algebra.doubleMatrixFactory().makeDiagonal(-3, new DoubleVectorN(
                4,5,6
        ));
        DoubleMatrix expected = new DoubleMatrix(new double[][] {
                {0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0},
                {0, 0, 0, 4, 0, 0},
                {0, 0, 0, 0, 5, 0},
                {0, 0, 0, 0, 0, 6}});

        assertArrayEquals(expected.toArray2D(), actual.toArray2D());
    }

}