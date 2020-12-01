package com.alura.dyno.maths;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MatrixFUnitTest {
    private static final float PRECISION = 0.00001f;

    @Test void testRows() {
        MatrixF m = new MatrixF(4,3);
        assertEquals(4, m.rows());
    }
    @Test void testCols() {
        MatrixF m = new MatrixF(4,3);
        assertEquals(3, m.cols());
    }
    @Test void testCount() {
        MatrixF m = new MatrixF(4,3);
        assertEquals(12, m.count());
    }
    @Test void testIsSquare() {
        MatrixF m1 = new MatrixF(4,3);
        MatrixF m2 = new MatrixF(4,4);

        assertEquals(false, m1.isSquare());
        assertEquals(true, m2.isSquare());
    }

    @Test void testGetDiagonal() {
        float[] mData = new float[] {-1, -2,  3, -2,
                2,  3,  1,  3,
                -2,  0,  1,  0,
                2,  4, -1,  3};
        MatrixF m = new MatrixF(4, 4, mData);

        float[] expectedData = new float[]{-1, 3, 1, 3};
        MatrixF result = m.getDiagonal();

        assertArrayEquals(expectedData, result.x_ij);
    }

    @Test void testGetRange() {
        float[] m_lhsData = new float[] {-1, -2,  3,-2,
                2,  3,  1, 3,
                -2,  0,  2, 0,
                2,  3,  1, 3};
        MatrixF m_lhs = new MatrixF( 4, 4, m_lhsData);

        float[] expectedDataA =  new float[]{3,  1,
                0,  2};
        MatrixF resultA = m_lhs.getRange(1, 2,1,2);

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
        m.setRange(set, 1, 2);

        float[] expectedData = new float[] {-1, -2,  3, -2,
                2,  3,  2,  3,
                -2,  0, -1,  0,
                2,  3,  3,  3};

        assertArrayEquals(expectedData, m.x_ij);
    }
    @Test void testAddRange() {
        float[] mData = new float[] {-1, -2,  3, -2,
                2,  3,  1,  3,
                -2,  0,  2,  0,
                2,  3,  1,  3};
        MatrixF m = new MatrixF(4, 4, mData);

        float[] addData = new float[] { 2,
                                       -1,
                                        3};
        MatrixF add = new MatrixF(3,1, addData);
        m.addRange(add, 1, 2);

        float[] expectedData = new float[] {-1, -2,  3, -2,
                2,  3,  3,  3,
                -2,  0,  1,  0,
                2,  3,  4,  3};
        assertArrayEquals(expectedData, m.x_ij);
    }
    @Test void testSubRange() {
        float[] mData = new float[] {-1, -2,  3, -2,
                2,  3,  1,  3,
                -2,  0,  2,  0,
                2,  3,  1,  3};
        MatrixF m = new MatrixF(4, 4, mData);

        float[] subData = new float[] { 2,
                -1,
                3};
        MatrixF sub = new MatrixF(3,1, subData);
        m.subRange(sub, 1, 2);

        float[] expectedData = new float[] {-1, -2,  3, -2,
                2,  3, -1,  3,
                -2,  0,  3,  0,
                2,  3, -2,  3};
        assertArrayEquals(expectedData, m.x_ij);
    }
    @Test void testMultRange() {
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
        m.multRange(c, 1, 3, 2, 2);

        assertArrayEquals(expectedData, m.x_ij);
    }
    @Test void testDivRange() {
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
        m.divRange(c, 1, 3, 2, 2);

        assertArrayEquals(expectedData, m.x_ij);
    }

    @Test void testGetCell() {
        float[] m_lhsData = new float[] {-1, -2,  3,-2,
                2,  3,  1, 3,
                -2,  0,  2, 0,
                2,  3,  1, 3};
        MatrixF m_lhs = new MatrixF( 4, 4, m_lhsData);

        float[] expectedDataA =  new float[]{3,  1,
                0,  2};
        MatrixF resultA = m_lhs.getRange(1, 2,1,2);

        assertArrayEquals(expectedDataA, resultA.x_ij);
    }
    @Test void testSetCell() {
        float[] mData = new float[] {-1, -2,  3, -2,
                2,  3,  1,  3,
                -2,  0,  2,  0,
                2,  3,  1,  3};
        MatrixF m = new MatrixF(4, 4, mData);

        float[] setData = new float[] {2,
                -1,
                3};
        MatrixF set = new MatrixF(3,1, setData);
        m.setRange(set, 1, 2);

        float[] expectedData = new float[] {-1, -2,  3, -2,
                2,  3,  2,  3,
                -2,  0, -1,  0,
                2,  3,  3,  3};
        assertArrayEquals(expectedData, m.x_ij);
    }
    @Test void testAddCell() {
        float[] mData = new float[] {-1, -2,  3, -2,
                2,  3,  1,  3,
                -2,  0,  2,  0,
                2,  3,  1,  3};
        MatrixF m = new MatrixF(4, 4, mData);

        float[] addData = new float[] { 2,
                -1,
                3};
        MatrixF add = new MatrixF(3,1, addData);
        m.addRange(add, 1, 2);

        float[] expectedData = new float[] {-1, -2,  3, -2,
                2,  3,  3,  3,
                -2,  0,  1,  0,
                2,  3,  4,  3};
        assertArrayEquals(expectedData, m.x_ij);
    }
    @Test void testSubCell() {
        float[] mData = new float[] {-1, -2,  3, -2,
                2,  3,  1,  3,
                -2,  0,  2,  0,
                2,  3,  1,  3};
        MatrixF m = new MatrixF(4, 4, mData);

        float[] subData = new float[] { 2,
                -1,
                3};
        MatrixF sub = new MatrixF(3,1, subData);
        m.subRange(sub, 1, 2);

        float[] expectedData = new float[] {-1, -2,  3, -2,
                2,  3, -1,  3,
                -2,  0,  3,  0,
                2,  3, -2,  3};
        assertArrayEquals(expectedData, m.x_ij);
    }
    @Test void testMultCell() {
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
        m.multRange(c, 1, 3, 2, 2);

        assertArrayEquals(expectedData, m.x_ij);
    }
    @Test void testDivCell() {
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
        m.divRange(c, 1, 3, 2, 2);

        assertArrayEquals(expectedData, m.x_ij);
    }
    @Test void getIndex() {
        MatrixF m1 = new MatrixF(2,3);

        assertEquals(0, m1.getIndex(0,0));
        assertEquals(1, m1.getIndex(0,1));
        assertEquals(3, m1.getIndex(1,0));
        assertEquals(4, m1.getIndex(1,1));
        assertEquals(4, m1.getIndex(1,1));
    }

    @Test void testGetRow() {
        float[] mData = new float[] {-1, -2,  3, -2,
                2,  3,  1,  3,
                -2,  0,  1,  0,
                2,  3, -1,  3};
        MatrixF m = new MatrixF(4, 4, mData);

        float[] expectedData = new float[] { -2,  0,  1,  0};
        MatrixF expected = new MatrixF(1, 4, expectedData);

        MatrixF result = m.getRow(2);

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

        m.setRow(set, 1);

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

        m.addRow(add, 1);

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

        m.subRow(sub, 1);

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
        m.multRow(1, c);

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
        m.divRow(1, c);

        assertArrayEquals(expectedData, m.x_ij);
    }
    @Test void testSwapRows() {
        float[] mData = new float[] {-1, -2,  3, -2,
                2,  3,  1,  3,
                -2,  0,  1,  0,
                2,  3, -1,  3};
        MatrixF m = new MatrixF(4, 4, mData);

        float[] expectedData = new float[] {-1, -2,  3, -2,
                2,  3, -1,  3,
                -2,  0,  1,  0,
                2,  3,  1,  3,};
        m.swapRows(1, 3);

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

        MatrixF result = m.getCol(2);

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

        m.setCol(set, 1);

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

        m.addCol(add, 1);

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

        m.subCol(sub, 1);

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
        m.multCol(1, c);

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
        m.divCol( 1, c);

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
        m.swapCols(1, 3);

        assertArrayEquals(expectedData, m.x_ij);
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
        m_lhs.add(m_rhs);

        assertArrayEquals(expectedData, m_lhs.x_ij);
    }
    @Test void testSub() {
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
        m_lhs.sub(m_rhs);

        assertArrayEquals(expectedData, m_lhs.x_ij);
    }
    @Test void testMult() {
        float c = -2.0f;
        float[] m_lhsData = new float[] {-1, -2,  3,
                2,  3,  1,
                -2,  0,  2};
        MatrixF m_lhs = new MatrixF( 3, 3, m_lhsData);

        float[] expectedData = new float[]{ 2,  4, -6,
                -4, -6, -2,
                4,  0, -4};
        m_lhs.mult(-2.0f);

        assertArrayEquals(expectedData, m_lhs.x_ij, PRECISION);
    }
    @Test void testDiv() {
        float c = -2.0f;
        float[] m_lhsData = new float[] {-1, -2,  3,
                2,  3,  1,
                -2,  0,  2};
        MatrixF m_lhs = new MatrixF( 3, 3, m_lhsData);

        float[] expectedData = new float[]{ 0.5f,  1.0f, -1.5f,
                -1.0f, -1.5f, -0.5f,
                1.0f,  0.0F, -1.0f};
        m_lhs.div(c);

        assertArrayEquals(expectedData, m_lhs.x_ij, PRECISION);
    }
    @Test void preMult() {
        float[] m_lhsData = new float[]
                {1, -2, 5,
                -3, 4, 0};
        MatrixF m_lhs = new MatrixF( 2, 3, m_lhsData);

        float[] m_rhsData = new float[]
                {3,  2,
                 0, -2,
                 4,  3};
        MatrixF m_rhs = new MatrixF( 3, 2, m_rhsData);

        float[] expectedData = new float[]
                { -3,  2, 15,
                   6, -8,  0,
                  -5,  4, 20};
        m_lhs.preMult(m_rhs);

        assertArrayEquals(expectedData, m_lhs.x_ij);
    }
    @Test void testPostMult() {
        float[] m_lhsData = new float[]
                {1, -3,
                        -2,  4,
                        5,  0};
        MatrixF m_lhs = new MatrixF( 3, 2, m_lhsData);

        float[] m_rhsData = new float[]
                {3,  0, 4,
                        2, -2, 3};
        MatrixF m_rhs = new MatrixF( 2, 3, m_rhsData);

        float[] expectedData = new float[]
                { -3,  6,  -5,
                        2, -8,   4,
                        15,  0,  20};
        m_lhs.postMult(m_rhs);

        assertArrayEquals(expectedData, m_lhs.x_ij);
    }
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
}