package com.alura.dyno.maths;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

class MatrixDUnitTest {
    private static final double PRECISION = 0.0000001f;

    @Test void testRows() {
        MatrixD m = new MatrixD(4,3);
        assertEquals(4, m.rows());
    }
    @Test void testCols() {
        MatrixD m = new MatrixD(4,3);
        assertEquals(3, m.cols());
    }
    @Test void testCount() {
        MatrixD m = new MatrixD(4,3);
        assertEquals(12, m.count());
    }
    @Test void testIsSquare() {
        MatrixD m1 = new MatrixD(4,3);
        MatrixD m2 = new MatrixD(4,4);

        assertEquals(false, m1.isSquare());
        assertEquals(true, m2.isSquare());
    }

    @Test void testGetDiagonal() {
        double[] mData = new double[] {-1, -2,  3, -2,
                2,  3,  1,  3,
                -2,  0,  1,  0,
                2,  4, -1,  3};
        MatrixD m = new MatrixD(4, 4, mData);

        double[] expectedData = new double[]{-1, 3, 1, 3};
        MatrixD result = m.getDiagonal();

        assertArrayEquals(expectedData, result.x_ij);
    }

    @Test void testGetRange() {
        double[] m_lhsData = new double[] {-1, -2,  3,-2,
                2,  3,  1, 3,
                -2,  0,  2, 0,
                2,  3,  1, 3};
        MatrixD m_lhs = new MatrixD( 4, 4, m_lhsData);

        double[] expectedDataA =  new double[]{3,  1,
                0,  2};
        MatrixD resultA = m_lhs.getRange(1, 2,1,2);

        assertArrayEquals(expectedDataA, resultA.x_ij);
    }
    @Test void testSetRange() {
        double[] mData = new double[] {-1, -2,  3, -2,
                2,  3,  1,  3,
                -2,  0,  2,  0,
                2,  3,  1,  3};
        MatrixD m = new MatrixD(4, 4, mData);

        double[] setData = new double[] {2,
                -1,
                3};
        MatrixD set = new MatrixD(3,1, setData);
        m.setRange(set, 1, 2);

        double[] expectedData = new double[] {-1, -2,  3, -2,
                2,  3,  2,  3,
                -2,  0, -1,  0,
                2,  3,  3,  3};

        assertArrayEquals(expectedData, m.x_ij);
    }
    @Test void testAddRange() {
        double[] mData = new double[] {-1, -2,  3, -2,
                2,  3,  1,  3,
                -2,  0,  2,  0,
                2,  3,  1,  3};
        MatrixD m = new MatrixD(4, 4, mData);

        double[] addData = new double[] { 2,
                                       -1,
                                        3};
        MatrixD add = new MatrixD(3,1, addData);
        m.addRange(add, 1, 2);

        double[] expectedData = new double[] {-1, -2,  3, -2,
                2,  3,  3,  3,
                -2,  0,  1,  0,
                2,  3,  4,  3};
        assertArrayEquals(expectedData, m.x_ij);
    }
    @Test void testSubRange() {
        double[] mData = new double[] {-1, -2,  3, -2,
                2,  3,  1,  3,
                -2,  0,  2,  0,
                2,  3,  1,  3};
        MatrixD m = new MatrixD(4, 4, mData);

        double[] subData = new double[] { 2,
                -1,
                3};
        MatrixD sub = new MatrixD(3,1, subData);
        m.subRange(sub, 1, 2);

        double[] expectedData = new double[] {-1, -2,  3, -2,
                2,  3, -1,  3,
                -2,  0,  3,  0,
                2,  3, -2,  3};
        assertArrayEquals(expectedData, m.x_ij);
    }
    @Test void testMultRange() {
        float c = 2.0f;
        double[] mData = new double[] {-1, -2,  3, -2,
                2,  3,  1,  3,
                -2,  0,  2,  0,
                2,  3, -1,  3};
        MatrixD m = new MatrixD(4, 4, mData);

        double[] expectedData = new double[] {-1, -2,  3, -2,
                2,  3,  2,  3,
                -2,  0,  4,  0,
                2,  3, -2,  3};
        m.multRange(c, 1, 3, 2, 2);

        assertArrayEquals(expectedData, m.x_ij);
    }
    @Test void testDivRange() {
        float c = 2.0f;
        double[] mData = new double[] {-1, -2,  3, -2,
                2,  3,  1,  3,
                -2,  0,  2,  0,
                2,  3, -1,  3};
        MatrixD m = new MatrixD(4, 4, mData);

        double[] expectedData = new double[] {-1, -2,  3, -2,
                2,  3,  0.5f,  3,
                -2,  0,  1,  0,
                2,  3, -0.5f,  3};
        m.divRange(c, 1, 3, 2, 2);

        assertArrayEquals(expectedData, m.x_ij);
    }

    @Test void testGetCell() {
        double[] m_lhsData = new double[] {-1, -2,  3,-2,
                2,  3,  1, 3,
                -2,  0,  2, 0,
                2,  3,  1, 3};
        MatrixD m_lhs = new MatrixD( 4, 4, m_lhsData);

        double[] expectedDataA =  new double[]{3,  1,
                0,  2};
        MatrixD resultA = m_lhs.getRange(1, 2,1,2);

        assertArrayEquals(expectedDataA, resultA.x_ij);
    }
    @Test void testSetCell() {
        double[] mData = new double[] {-1, -2,  3, -2,
                2,  3,  1,  3,
                -2,  0,  2,  0,
                2,  3,  1,  3};
        MatrixD m = new MatrixD(4, 4, mData);

        double[] setData = new double[] {2,
                -1,
                3};
        MatrixD set = new MatrixD(3,1, setData);
        m.setRange(set, 1, 2);

        double[] expectedData = new double[] {-1, -2,  3, -2,
                2,  3,  2,  3,
                -2,  0, -1,  0,
                2,  3,  3,  3};
        assertArrayEquals(expectedData, m.x_ij);
    }
    @Test void testAddCell() {
        double[] mData = new double[] {-1, -2,  3, -2,
                2,  3,  1,  3,
                -2,  0,  2,  0,
                2,  3,  1,  3};
        MatrixD m = new MatrixD(4, 4, mData);

        double[] addData = new double[] { 2,
                -1,
                3};
        MatrixD add = new MatrixD(3,1, addData);
        m.addRange(add, 1, 2);

        double[] expectedData = new double[] {-1, -2,  3, -2,
                2,  3,  3,  3,
                -2,  0,  1,  0,
                2,  3,  4,  3};
        assertArrayEquals(expectedData, m.x_ij);
    }
    @Test void testSubCell() {
        double[] mData = new double[] {-1, -2,  3, -2,
                2,  3,  1,  3,
                -2,  0,  2,  0,
                2,  3,  1,  3};
        MatrixD m = new MatrixD(4, 4, mData);

        double[] subData = new double[] { 2,
                -1,
                3};
        MatrixD sub = new MatrixD(3,1, subData);
        m.subRange(sub, 1, 2);

        double[] expectedData = new double[] {-1, -2,  3, -2,
                2,  3, -1,  3,
                -2,  0,  3,  0,
                2,  3, -2,  3};
        assertArrayEquals(expectedData, m.x_ij);
    }
    @Test void testMultCell() {
        float c = 2.0f;
        double[] mData = new double[] {-1, -2,  3, -2,
                2,  3,  1,  3,
                -2,  0,  2,  0,
                2,  3, -1,  3};
        MatrixD m = new MatrixD(4, 4, mData);

        double[] expectedData = new double[] {-1, -2,  3, -2,
                2,  3,  2,  3,
                -2,  0,  4,  0,
                2,  3, -2,  3};
        m.multRange(c, 1, 3, 2, 2);

        assertArrayEquals(expectedData, m.x_ij);
    }
    @Test void testDivCell() {
        float c = 2.0f;
        double[] mData = new double[] {-1, -2,  3, -2,
                2,  3,  1,  3,
                -2,  0,  2,  0,
                2,  3, -1,  3};
        MatrixD m = new MatrixD(4, 4, mData);

        double[] expectedData = new double[] {-1, -2,  3, -2,
                2,  3,  0.5f,  3,
                -2,  0,  1,  0,
                2,  3, -0.5f,  3};
        m.divRange(c, 1, 3, 2, 2);

        assertArrayEquals(expectedData, m.x_ij);
    }
    @Test void getIndex() {
        MatrixD m1 = new MatrixD(2,3);

        assertEquals(0, m1.getIndex(0,0));
        assertEquals(1, m1.getIndex(0,1));
        assertEquals(3, m1.getIndex(1,0));
        assertEquals(4, m1.getIndex(1,1));
        assertEquals(4, m1.getIndex(1,1));
    }

    @Test void testGetRow() {
        double[] mData = new double[] {-1, -2,  3, -2,
                2,  3,  1,  3,
                -2,  0,  1,  0,
                2,  3, -1,  3};
        MatrixD m = new MatrixD(4, 4, mData);

        double[] expectedData = new double[] { -2,  0,  1,  0};
        MatrixD expected = new MatrixD(1, 4, expectedData);

        MatrixD result = m.getRow(2);

        assertEquals(expected.rows(), result.rows());
        assertEquals(expected.cols(), result.cols());
        assertArrayEquals(expectedData, result.x_ij);
    }
    @Test void testSetRow() {
        double[] mData = new double[] {-1, -2,  3, -2,
                2,  3,  1,  3,
                -2,  0,  1,  0,
                2,  3, -1,  3};
        MatrixD m = new MatrixD(4, 4, mData);

        double[] setData = new double[] { 4,  2,  -2,  3};
        MatrixD set = new MatrixD(1, 4, setData);

        m.setRow(set, 1);

        double[] expectedData = new double[] {-1, -2,  3, -2,
                4,  2,  -2,  3,
                -2,  0,  1,  0,
                2,  3, -1,  3};

        assertArrayEquals(expectedData, m.x_ij);
    }
    @Test void testAddToRow() {
        double[] mData = new double[] {-1, -2,  3, -2,
                2,  3,  1,  3,
                -2,  0,  1,  0,
                2,  3, -1,  3};
        MatrixD m = new MatrixD(4, 4, mData);

        double[] addData = new double[] { 4,  2,  -2,  3};
        MatrixD add = new MatrixD(1, 4, addData);

        m.addRow(add, 1);

        double[] expectedData = new double[] {-1, -2,  3, -2,
                6,5,-1,6,
                -2,  0,  1,  0,
                2,  3, -1,  3};

        assertArrayEquals(expectedData, m.x_ij);
    }
    @Test void testSubFromRow() {
        double[] mData = new double[] {-1, -2,  3, -2,
                2,  3,  1,  3,
                -2,  0,  1,  0,
                2,  3, -1,  3};
        MatrixD m = new MatrixD(4, 4, mData);

        double[] subData = new double[] { 4,  2,  -2,  3};
        MatrixD sub = new MatrixD(1, 4, subData);

        m.subRow(sub, 1);

        double[] expectedData = new double[] {-1, -2,  3, -2,
                -2, 1, 3, 0,
                -2,  0,  1,  0,
                2,  3, -1,  3};

        assertArrayEquals(expectedData, m.x_ij);
    }
    @Test void testMultiplyRow() {
        float c = -2.0f;
        double[] mData = new double[] {-1, -2,  3, -2,
                2,  3,  1,  3,
                -2,  0,  1,  0,
                2,  3, -1,  3};
        MatrixD m = new MatrixD(4, 4, mData);

        double[] expectedData = new double[] {-1, -2,  3, -2,
                -4, -6, -2, -6,
                -2,  0,  1,  0,
                2,  3, -1,  3};
        m.multRow(1, c);

        assertArrayEquals(expectedData, m.x_ij);
    }
    @Test void testDivideRow() {
        float c = -2.0f;
        double[] mData = new double[] {-1, -2,  3, -2,
                2,  3,  1,  3,
                -2,  0,  1,  0,
                2,  3, -1,  3};
        MatrixD m = new MatrixD(4, 4, mData);

        double[] expectedData = new double[] {-1, -2,  3, -2,
                -1, -1.5f, -0.5f, -1.5f,
                -2,  0,  1,  0,
                2,  3, -1,  3};
        m.divRow(1, c);

        assertArrayEquals(expectedData, m.x_ij);
    }
    @Test void testSwapRows() {
        double[] mData = new double[] {-1, -2,  3, -2,
                2,  3,  1,  3,
                -2,  0,  1,  0,
                2,  3, -1,  3};
        MatrixD m = new MatrixD(4, 4, mData);

        double[] expectedData = new double[] {-1, -2,  3, -2,
                2,  3, -1,  3,
                -2,  0,  1,  0,
                2,  3,  1,  3,};
        m.swapRows(1, 3);

        assertArrayEquals(expectedData, m.x_ij);
    }

    @Test void testGetCol() {
        double[] mData = new double[] {-1, -2,  3, -2,
                2,  3,  1,  3,
                -2,  0,  1,  0,
                2,  3, -1,  3};
        MatrixD m = new MatrixD(4, 4, mData);

        double[] expectedData = new double[] {3, 1, 1, -1};
        MatrixD expected = new MatrixD(4, 1, expectedData);

        MatrixD result = m.getCol(2);

        assertEquals(expected.rows(), result.rows());
        assertEquals(expected.cols(), result.cols());
        assertArrayEquals(expected.x_ij, result.x_ij);
    }
    @Test void testSetCol() {
        double[] mData = new double[] {-1, -2,  3, -2,
                2,  3,  1,  3,
                -2,  0,  1,  0,
                2,  3, -1,  3};
        MatrixD m = new MatrixD(4, 4, mData);

        double[] setData = new double[] { 4,  1,  -2,  5};
        MatrixD set = new MatrixD(4, 1, setData);

        m.setCol(set, 1);

        double[] expectedData = new double[] {-1,  4,  3, -2,
                2,  1,  1,  3,
                -2, -2,  1,  0,
                2,  5, -1,  3};

        assertArrayEquals(expectedData, m.x_ij);
    }
    @Test void testAddToCol() {
        double[] mData = new double[] {-1, -2,  3, -2,
                2,  3,  1,  3,
                -2,  0,  1,  0,
                2,  3, -1,  3};
        MatrixD m = new MatrixD(4, 4, mData);

        double[] addData = new double[] { 4,  2,  -2,  3};
        MatrixD add = new MatrixD(4, 1, addData);

        m.addCol(add, 1);

        double[] expectedData = new double[] {-1, 2,  3, -2,
                2,  5,  1,  3,
                -2,  -2,  1,  0,
                2,  6, -1,  3};

        assertArrayEquals(expectedData, m.x_ij);
    }
    @Test void testSubFromCol() {
        double[] mData = new double[] {-1, -2,  3, -2,
                2,  3,  1,  3,
                -2,  0,  1,  0,
                2,  3, -1,  3};
        MatrixD m = new MatrixD(4, 4, mData);

        double[] subData = new double[] { 4,  2,  -2,  3};
        MatrixD sub = new MatrixD(4, 1, subData);

        m.subCol(sub, 1);

        double[] expectedData = new double[] {-1, -6,  3, -2,
                2,  1,  1,  3,
                -2,  2,  1,  0,
                2,  0, -1,  3};

        assertArrayEquals(expectedData, m.x_ij);
    }
    @Test void testMultiplyCol() {
        float c = -2.0f;
        double[] mData = new double[] {-1, -2,  3, -2,
                2,  3,  1,  3,
                -2,  0,  1,  0,
                2,  4, -1,  3};
        MatrixD m = new MatrixD(4, 4, mData);

        double[] expectedData = new double[] {-1, 4,  3, -2,
                2,  -6,  1,  3,
                -2,  0,  1,  0,
                2,  -8, -1,  3};
        m.multCol(1, c);

        assertArrayEquals(expectedData, m.x_ij, PRECISION);
    }
    @Test void testDivideCol() {
        float c = -2.0f;
        double[] mData = new double[] {-1, -2,  3, -2,
                2,  3,  1,  3,
                -2,  0,  1,  0,
                2,  4, -1,  3};
        MatrixD m = new MatrixD(4, 4, mData);

        double[] expectedData = new double[] {-1, 1,  3, -2,
                2,  -1.5f,  1,  3,
                -2,  0,  1,  0,
                2,  -2, -1,  3};
        m.divCol( 1, c);

        assertArrayEquals(expectedData, m.x_ij, PRECISION);
    }
    @Test void testSwapCols() {
        double[] mData = new double[] {-1, -2,  3,  2,
                3,  3, -2,  4,
                -2,  4,  1, -2,
                2,  3, -1,  2 };
        MatrixD m = new MatrixD(4, 4, mData);

        double[] expectedData = new double[] {-1,  2,  3, -2,
                3,  4, -2,  3,
                -2, -2,  1,  4,
                2,  2, -1,  3 };
        m.swapCols(1, 3);

        assertArrayEquals(expectedData, m.x_ij);
    }

    @Test void testAdd() {
        double[] m_lhsData = new double[] {-1, -2,  3,
                2,  3,  1,
                -2,  0,  2};
        MatrixD m_lhs = new MatrixD( 3, 3, m_lhsData);

        double[] m_rhsData = new double[] {-2, -3, -2,
                1,  0,  3,
                -1,  1,  1};
        MatrixD m_rhs = new MatrixD( 3, 3, m_rhsData);

        double[] expectedData = new double[]{-3, -5,  1,
                3,  3,  4,
                -3,  1,  3};
        m_lhs.add(m_rhs);

        assertArrayEquals(expectedData, m_lhs.x_ij);
    }
    @Test void testSub() {
        double[] m_lhsData = new double[] {-1, -2,  3,
                2,  3,  1,
                -2,  0,  2};
        MatrixD m_lhs = new MatrixD( 3, 3, m_lhsData);

        double[] m_rhsData = new double[] {-2, -3, -2,
                1,  0,  3,
                -1,  1,  1};
        MatrixD m_rhs = new MatrixD( 3, 3, m_rhsData);

        double[] expectedData = new double[]{ 1,  1,  5,
                1,  3, -2,
                -1, -1,  1};
        m_lhs.sub(m_rhs);

        assertArrayEquals(expectedData, m_lhs.x_ij);
    }
    @Test void testMult() {
        float c = -2.0f;
        double[] m_lhsData = new double[] {-1, -2,  3,
                2,  3,  1,
                -2,  0,  2};
        MatrixD m_lhs = new MatrixD( 3, 3, m_lhsData);

        double[] expectedData = new double[]{ 2,  4, -6,
                -4, -6, -2,
                4,  0, -4};
        m_lhs.mult(-2.0f);

        assertArrayEquals(expectedData, m_lhs.x_ij, PRECISION);
    }
    @Test void testDiv() {
        float c = -2.0f;
        double[] m_lhsData = new double[] {-1, -2,  3,
                2,  3,  1,
                -2,  0,  2};
        MatrixD m_lhs = new MatrixD( 3, 3, m_lhsData);

        double[] expectedData = new double[]{ 0.5f,  1.0f, -1.5f,
                -1.0f, -1.5f, -0.5f,
                1.0f,  0.0F, -1.0f};
        m_lhs.div(c);

        assertArrayEquals(expectedData, m_lhs.x_ij, PRECISION);
    }
    @Test void preMult() {
        double[] m_lhsData = new double[]
                {1, -2, 5,
                -3, 4, 0};
        MatrixD m_lhs = new MatrixD( 2, 3, m_lhsData);

        double[] m_rhsData = new double[]
                {3,  2,
                 0, -2,
                 4,  3};
        MatrixD m_rhs = new MatrixD( 3, 2, m_rhsData);

        double[] expectedData = new double[]
                { -3,  2, 15,
                   6, -8,  0,
                  -5,  4, 20};
        m_lhs.preMult(m_rhs);

        assertArrayEquals(expectedData, m_lhs.x_ij);
    }
    @Test void testPostMult() {
        double[] m_lhsData = new double[]
                {1, -3,
                        -2,  4,
                        5,  0};
        MatrixD m_lhs = new MatrixD( 3, 2, m_lhsData);

        double[] m_rhsData = new double[]
                {3,  0, 4,
                        2, -2, 3};
        MatrixD m_rhs = new MatrixD( 2, 3, m_rhsData);

        double[] expectedData = new double[]
                { -3,  6,  -5,
                        2, -8,   4,
                        15,  0,  20};
        m_lhs.postMult(m_rhs);

        assertArrayEquals(expectedData, m_lhs.x_ij);
    }
    @Test void testTranspose() {
        double[] mData = new double[] {-1, -2,  3,
                2,  3,  1};
        MatrixD m = new MatrixD( 2, 3, mData);
        m.transpose();

        double[] expectedData = new double[] {-1,  2,
                -2,  3,
                3,  1};
        MatrixD expected  = new MatrixD(3, 2, expectedData);

        assertEquals(expected.rows(), m.rows());
        assertEquals(expected.cols(), m.cols());
        assertArrayEquals(expected.x_ij, m.x_ij);
    }
}