package com.alura.dyno.maths;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MatrixFTest {
    private static final float PRECISION = 0.00001f;

    @Test void testTranspose() {
        float[] mData = new float[] {-1, -2,  3,
                                      2,  3,  1};
        MatrixF m = new MatrixF( 2, 3, mData);
        m.transpose();

        float[] expectedData = new float[] {-1,  2,
                                            -2,  3,
                                             3,  1};
        MatrixF expected  = new MatrixF(3, 2, expectedData);

        assertEquals(expected.rows(), m.rows());
        assertEquals(expected.cols(), m.cols());
        assertArrayEquals(expected.x_ij, m.x_ij);
    }
    @Test void testAdd() {
        float[] m_lhsData = new float[] {-1, -2,  3,
                2,  3,  1,
                -2,  0,  2};
        MatrixF m_lhs = new MatrixF( 3, 3, m_lhsData);

        float[] m_rhsData = new float[] {-2, -3, -2,
                1,  0,  3,
                -1,  1,  1};
        MatrixF m_rhs = new MatrixF( 3, 3, m_rhsData);

        float[] expectedData = new float[]{-3, -5,  1,
                3,  3,  4,
                -3,  1,  3};
        MatrixF result = MMath.add(m_lhs, m_rhs);

        assertArrayEquals(expectedData, result.x_ij);
    }
    @Test void testSubtract() {
        float[] m_lhsData = new float[] {-1, -2,  3,
                2,  3,  1,
                -2,  0,  2};
        MatrixF m_lhs = new MatrixF( 3, 3, m_lhsData);

        float[] m_rhsData = new float[] {-2, -3, -2,
                1,  0,  3,
                -1,  1,  1};
        MatrixF m_rhs = new MatrixF( 3, 3, m_rhsData);

        float[] expectedData = new float[]{ 1,  1,  5,
                1,  3, -2,
                -1, -1,  1};
        MatrixF result = MMath.subFromRange(m_lhs, m_rhs);

        assertArrayEquals(expectedData, result.x_ij);
    }
    @Test void testMultiplyC() {
        float c = -2.0f;
        float[] m_lhsData = new float[] {-1, -2,  3,
                2,  3,  1,
                -2,  0,  2};
        MatrixF m_lhs = new MatrixF( 3, 3, m_lhsData);

        float[] expectedData = new float[]{ 2,  4, -6,
                -4, -6, -2,
                4,  0, -4};
        MatrixF result = MMath.multiply(m_lhs, c);

        assertArrayEquals(expectedData, result.x_ij, PRECISION);
    }
    @Test void testMultiplyM() {
        float[] m_lhsData = new float[] {-1, -2,  3,
                2,  3,  1,
                -2,  0,  2};
        MatrixF m_lhs = new MatrixF( 3, 3, m_lhsData);

        float[] m_rhsData = new float[] {-2, -3, -2,
                1,  0,  3,
                -1,  1,  1};
        MatrixF m_rhs = new MatrixF( 3, 3, m_rhsData);

        float[] expectResultData = new float[] {-3,  6, -1,
                -2, -5,  6,
                2,  8,  6};
        MatrixF result = MMath.multiply(m_lhs, m_rhs);

        assertArrayEquals(expectResultData, result.x_ij);
    }
    @Test void testDivide() {
        float c = -2.0f;
        float[] m_lhsData = new float[] {-1, -2,  3,
                2,  3,  1,
                -2,  0,  2};
        MatrixF m_lhs = new MatrixF( 3, 3, m_lhsData);

        float[] expectedData = new float[]{ 0.5f,  1.0f, -1.5f,
                -1.0f, -1.5f, -0.5f,
                1.0f,  0.0F, -1.0f};
        MatrixF result = MMath.divide(m_lhs, c);

        assertArrayEquals(expectedData, result.x_ij, PRECISION);
    }

    @Test void testAddTo() {
        float[] m_lhsData = new float[] {-1, -2,  3,
                2,  3,  1,
                -2,  0,  2};
        MatrixF m_lhs = new MatrixF( 3, 3, m_lhsData);

        float[] m_rhsData = new float[] {-2, -3, -2,
                1,  0,  3,
                -1,  1,  1};
        MatrixF m_rhs = new MatrixF( 3, 3, m_rhsData);

        float[] expectedData = new float[]{-3, -5,  1,
                3,  3,  4,
                -3,  1,  3};
        MMath.addTo(m_lhs, m_rhs);

        assertArrayEquals(expectedData, m_lhs.x_ij);
    }
    @Test void testSubtractFrom() {
        float[] m_lhsData = new float[] {-1, -2,  3,
                2,  3,  1,
                -2,  0,  2};
        MatrixF m_lhs = new MatrixF( 3, 3, m_lhsData);

        float[] m_rhsData = new float[] {-2, -3, -2,
                1,  0,  3,
                -1,  1,  1};
        MatrixF m_rhs = new MatrixF( 3, 3, m_rhsData);

        float[] expectedData = new float[]{ 1,  1,  5,
                1,  3, -2,
                -1, -1,  1};
        MMath.subFrom(m_lhs, m_rhs);

        assertArrayEquals(expectedData, m_lhs.x_ij);
    }

    @Test void testGetRange() {
        float[] m_lhsData = new float[] {-1, -2,  3,-2,
                2,  3,  1, 3,
                -2,  0,  2, 0,
                2,  3,  1, 3};
        MatrixF m_lhs = new MatrixF( 4, 4, m_lhsData);

        float[] expectedDataA =  new float[]{3,  1,
                0,  2};
        MatrixF resultA = MMath.getRange(m_lhs, 1, 2,1,2);

        assertArrayEquals(expectedDataA, resultA.x_ij);
    }
    @Test void testSetRange() {
        float[] mData = new float[] {-1, -2,  3, -2,
                2,  3,  1,  3,
                -2,  0,  2,  0,
                2,  3,  1,  3};
        MatrixF m = new MatrixF(4, 4, mData);

        float[] setData = new float[] {2,
                -1,
                3};
        MatrixF set = new MatrixF(3,1, setData);
        MMath.setRange(m, set, 1, 2);

        float[] expectedData = new float[] {-1, -2,  3, -2,
                2,  3,  2,  3,
                -2,  0, -1,  0,
                2,  3,  3,  3};
        assertArrayEquals(expectedData, m.x_ij);
    }
    @Test void testAddToRange() {
        float[] mData = new float[] {-1, -2,  3, -2,
                2,  3,  1,  3,
                -2,  0,  2,  0,
                2,  3,  1,  3};
        MatrixF m = new MatrixF(4, 4, mData);

        float[] addToData = new float[] { 2,
                -1,
                3};
        MatrixF addTo = new MatrixF(3,1, addToData);
        MMath.addToRange(m, addTo, 1, 2);

        float[] expectedData = new float[] {-1, -2,  3, -2,
                2,  3,  3,  3,
                -2,  0,  1,  0,
                2,  3,  4,  3};
        assertArrayEquals(expectedData, m.x_ij);
    }
    @Test void testSubFromRange() {
        float[] mData = new float[] {-1, -2,  3, -2,
                2,  3,  1,  3,
                -2,  0,  2,  0,
                2,  3,  1,  3};
        MatrixF m = new MatrixF(4, 4, mData);

        float[] subFromData = new float[] { 2,
                -1,
                3};
        MatrixF subFrom = new MatrixF(3,1, subFromData);
        MMath.subFromRange(m, subFrom, 1, 2);

        float[] expectedData = new float[] {-1, -2,  3, -2,
                2,  3, -1,  3,
                -2,  0,  3,  0,
                2,  3, -2,  3};
        assertArrayEquals(expectedData, m.x_ij);
    }
    @Test void testMultiplyRange() {
        float c = 2.0f;
        float[] mData = new float[] {-1, -2,  3, -2,
                2,  3,  1,  3,
                -2,  0,  2,  0,
                2,  3, -1,  3};
        MatrixF m = new MatrixF(4, 4, mData);

        float[] expectedData = new float[] {-1, -2,  3, -2,
                2,  3,  2,  3,
                -2,  0,  4,  0,
                2,  3, -2,  3};
        MMath.multiplyRange(m, c, 1, 3, 2, 2);

        assertArrayEquals(expectedData, m.x_ij);
    }
    @Test void testDivideRange() {
        float c = 2.0f;
        float[] mData = new float[] {-1, -2,  3, -2,
                2,  3,  1,  3,
                -2,  0,  2,  0,
                2,  3, -1,  3};
        MatrixF m = new MatrixF(4, 4, mData);

        float[] expectedData = new float[] {-1, -2,  3, -2,
                2,  3,  0.5f,  3,
                -2,  0,  1,  0,
                2,  3, -0.5f,  3};
        MMath.divideRange(m, c, 1, 3, 2, 2);

        assertArrayEquals(expectedData, m.x_ij);
    }

    @Test void testGetRow() {
        float[] mData = new float[] {-1, -2,  3, -2,
                2,  3,  1,  3,
                -2,  0,  1,  0,
                2,  3, -1,  3};
        MatrixF m = new MatrixF(4, 4, mData);

        float[] expectedData = new float[] { -2,  0,  1,  0};
        MatrixF expected = new MatrixF(1, 4, expectedData);

        MatrixF result = MMath.getRow(m, 2);

        assertEquals(expected.rows(), result.rows());
        assertEquals(expected.cols(), result.cols());
        assertArrayEquals(expectedData, result.x_ij);
    }
    @Test void testSetRow() {
        float[] mData = new float[] {-1, -2,  3, -2,
                2,  3,  1,  3,
                -2,  0,  1,  0,
                2,  3, -1,  3};
        MatrixF m = new MatrixF(4, 4, mData);

        float[] setData = new float[] { 4,  2,  -2,  3};
        MatrixF set = new MatrixF(1, 4, setData);

        MMath.setRow(m, set, 1);

        float[] expectedData = new float[] {-1, -2,  3, -2,
                4,  2,  -2,  3,
                -2,  0,  1,  0,
                2,  3, -1,  3};

        assertArrayEquals(expectedData, m.x_ij);
    }
    @Test void testAddToRow() {
        float[] mData = new float[] {-1, -2,  3, -2,
                2,  3,  1,  3,
                -2,  0,  1,  0,
                2,  3, -1,  3};
        MatrixF m = new MatrixF(4, 4, mData);

        float[] addData = new float[] { 4,  2,  -2,  3};
        MatrixF add = new MatrixF(1, 4, addData);

        MMath.addToRow(m, add, 1);

        float[] expectedData = new float[] {-1, -2,  3, -2,
                6,5,-1,6,
                -2,  0,  1,  0,
                2,  3, -1,  3};

        assertArrayEquals(expectedData, m.x_ij);
    }
    @Test void testSubFromRow() {
        float[] mData = new float[] {-1, -2,  3, -2,
                2,  3,  1,  3,
                -2,  0,  1,  0,
                2,  3, -1,  3};
        MatrixF m = new MatrixF(4, 4, mData);

        float[] subData = new float[] { 4,  2,  -2,  3};
        MatrixF sub = new MatrixF(1, 4, subData);

        MMath.subFromRow(m, sub, 1);

        float[] expectedData = new float[] {-1, -2,  3, -2,
                -2, 1, 3, 0,
                -2,  0,  1,  0,
                2,  3, -1,  3};

        assertArrayEquals(expectedData, m.x_ij);
    }
    @Test void testMultiplyRow() {
        float c = -2.0f;
        float[] mData = new float[] {-1, -2,  3, -2,
                2,  3,  1,  3,
                -2,  0,  1,  0,
                2,  3, -1,  3};
        MatrixF m = new MatrixF(4, 4, mData);

        float[] expectedData = new float[] {-1, -2,  3, -2,
                -4, -6, -2, -6,
                -2,  0,  1,  0,
                2,  3, -1,  3};
        MMath.multiplyRow(m, 1, c);

        assertArrayEquals(expectedData, m.x_ij);
    }
    @Test void testDivideRow() {
        float c = -2.0f;
        float[] mData = new float[] {-1, -2,  3, -2,
                2,  3,  1,  3,
                -2,  0,  1,  0,
                2,  3, -1,  3};
        MatrixF m = new MatrixF(4, 4, mData);

        float[] expectedData = new float[] {-1, -2,  3, -2,
                -1, -1.5f, -0.5f, -1.5f,
                -2,  0,  1,  0,
                2,  3, -1,  3};
        MMath.divideRow(m, 1, c);

        assertArrayEquals(expectedData, m.x_ij);
    }
    @Test void testSwapLines() {
        float[] mData = new float[] {-1, -2,  3, -2,
                2,  3,  1,  3,
                -2,  0,  1,  0,
                2,  3, -1,  3};
        MatrixF m = new MatrixF(4, 4, mData);

        float[] expectedData = new float[] {-1, -2,  3, -2,
                2,  3, -1,  3,
                -2,  0,  1,  0,
                2,  3,  1,  3,};
        MMath.swapLines(m, 1, 3);

        assertArrayEquals(expectedData, m.x_ij);
    }

    @Test void testGetCol() {
        float[] mData = new float[] {-1, -2,  3, -2,
                2,  3,  1,  3,
                -2,  0,  1,  0,
                2,  3, -1,  3};
        MatrixF m = new MatrixF(4, 4, mData);

        float[] expectedData = new float[] {3, 1, 1, -1};
        MatrixF expected = new MatrixF(4, 1, expectedData);

        MatrixF result = MMath.getCol(m, 2);

        assertEquals(expected.rows(), result.rows());
        assertEquals(expected.cols(), result.cols());
        assertArrayEquals(expected.x_ij, result.x_ij);
    }
    @Test void testSetCol() {
        float[] mData = new float[] {-1, -2,  3, -2,
                2,  3,  1,  3,
                -2,  0,  1,  0,
                2,  3, -1,  3};
        MatrixF m = new MatrixF(4, 4, mData);

        float[] setData = new float[] { 4,  1,  -2,  5};
        MatrixF set = new MatrixF(4, 1, setData);

        MMath.setCol(m, set, 1);

        float[] expectedData = new float[] {-1,  4,  3, -2,
                2,  1,  1,  3,
                -2, -2,  1,  0,
                2,  5, -1,  3};

        assertArrayEquals(expectedData, m.x_ij);
    }
    @Test void testAddToCol() {
        float[] mData = new float[] {-1, -2,  3, -2,
                2,  3,  1,  3,
                -2,  0,  1,  0,
                2,  3, -1,  3};
        MatrixF m = new MatrixF(4, 4, mData);

        float[] addData = new float[] { 4,  2,  -2,  3};
        MatrixF add = new MatrixF(4, 1, addData);

        MMath.addToCol(m, add, 1);

        float[] expectedData = new float[] {-1, 2,  3, -2,
                2,  5,  1,  3,
                -2,  -2,  1,  0,
                2,  6, -1,  3};

        assertArrayEquals(expectedData, m.x_ij);
    }
    @Test void testSubFromCol() {
        float[] mData = new float[] {-1, -2,  3, -2,
                2,  3,  1,  3,
                -2,  0,  1,  0,
                2,  3, -1,  3};
        MatrixF m = new MatrixF(4, 4, mData);

        float[] subData = new float[] { 4,  2,  -2,  3};
        MatrixF sub = new MatrixF(4, 1, subData);

        MMath.subFromCol(m, sub, 1);

        float[] expectedData = new float[] {-1, -6,  3, -2,
                2,  1,  1,  3,
                -2,  2,  1,  0,
                2,  0, -1,  3};

        assertArrayEquals(expectedData, m.x_ij);
    }
    @Test void testMultiplyCol() {
        float c = -2.0f;
        float[] mData = new float[] {-1, -2,  3, -2,
                2,  3,  1,  3,
                -2,  0,  1,  0,
                2,  4, -1,  3};
        MatrixF m = new MatrixF(4, 4, mData);

        float[] expectedData = new float[] {-1, 4,  3, -2,
                2,  -6,  1,  3,
                -2,  0,  1,  0,
                2,  -8, -1,  3};
        MMath.multiplyCol(m, 1, c);

        assertArrayEquals(expectedData, m.x_ij, PRECISION);
    }
    @Test void testDivideCol() {
        float c = -2.0f;
        float[] mData = new float[] {-1, -2,  3, -2,
                2,  3,  1,  3,
                -2,  0,  1,  0,
                2,  4, -1,  3};
        MatrixF m = new MatrixF(4, 4, mData);

        float[] expectedData = new float[] {-1, 1,  3, -2,
                2,  -1.5f,  1,  3,
                -2,  0,  1,  0,
                2,  -2, -1,  3};
        MMath.divideCol(m, 1, c);

        assertArrayEquals(expectedData, m.x_ij, PRECISION);
    }
    @Test void testSwapCols() {
        float[] mData = new float[] {-1, -2,  3,  2,
                3,  3, -2,  4,
                -2,  4,  1, -2,
                2,  3, -1,  2 };
        MatrixF m = new MatrixF(4, 4, mData);

        float[] expectedData = new float[] {-1,  2,  3, -2,
                3,  4, -2,  3,
                -2, -2,  1,  4,
                2,  2, -1,  3 };
        MMath.swapCols(m, 1, 3);

        assertArrayEquals(expectedData, m.x_ij);
    }

    @Test void testGetDiagonal() {
        float[] mData = new float[] {-1, -2,  3, -2,
                2,  3,  1,  3,
                -2,  0,  1,  0,
                2,  4, -1,  3};
        MatrixF m = new MatrixF(4, 4, mData);

        float[] expectedData = new float[]{-1, 3, 1, 3};
        MatrixF result = MMath.getDiagonal(m);

        assertArrayEquals(expectedData, result.x_ij);
    }
}