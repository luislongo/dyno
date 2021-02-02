package com.alura.dyno.maths;

import org.junit.jupiter.api.Test;

import static com.alura.dyno.maths.MatrixF.isColIndexValid;
import static com.alura.dyno.maths.MatrixF.isRowIndexValid;
import static com.alura.dyno.maths.MatrixF.isSubMatrixDimensionValid;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

class StaticMatrixFUnitTest {
    private static final float PRECISION = 0.00001f;

    @Test void testTranspose() {
            float[] mData = new float[] {-1, -2,  3,
                    2,  3,  1};
            MatrixF m = new MatrixF( 2, 3, mData);

            float[] expectedData = new float[] {-1,  2,
                    -2,  3,
                    3,  1};
            MatrixF expected  = new MatrixF(3, 2, expectedData);
            MatrixF result = MatrixF.transpose(m);

            assertEquals(expected.rows(), result.rows());
            assertEquals(expected.cols(), result.cols());
            assertArrayEquals(expected.x_ij, result.x_ij);
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
        MatrixF result = MatrixF.add(m_lhs, m_rhs);

        assertArrayEquals(expectedData, result.x_ij);
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
        MatrixF result = MatrixF.sub(m_lhs, m_rhs);

        assertArrayEquals(expectedData, result.x_ij);
    }
    @Test void testMultC() {
        float c = -2.0f;
        float[] m_lhsData = new float[] {-1, -2,  3,
                2,  3,  1,
                -2,  0,  2};
        MatrixF m_lhs = new MatrixF( 3, 3, m_lhsData);

        float[] expectedData = new float[]{ 2,  4, -6,
                -4, -6, -2,
                4,  0, -4};
        MatrixF result = MatrixF.mult(m_lhs, c);

        assertArrayEquals(expectedData, result.x_ij, PRECISION);
    }
    @Test void testMultM() {
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
        MatrixF result = MatrixF.mult(m_lhs, m_rhs);

        assertArrayEquals(expectResultData, result.x_ij);
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
        MatrixF result = MatrixF.div(m_lhs, c);

        assertArrayEquals(expectedData, result.x_ij, PRECISION);
    }

    @Test void testIsSquareMatrix() {
        MatrixF m_sq = new MatrixF(3, 3);
        MatrixF m_nsq = new MatrixF( 2, 3);

        assertEquals(true, m_sq.isSquare());
        assertEquals(false, m_nsq.isSquare());
    }
    @Test void testIsSameDimensions() {
        MatrixF m1 = new MatrixF(3, 4);
        MatrixF m2 = new MatrixF(3, 4);
        MatrixF m3 = new MatrixF(4, 4);
        MatrixF m4 = new MatrixF(3, 5);
        MatrixF m5 = new MatrixF(2, 3);

        assertEquals(true, MatrixF.isSameDimensions(m1, m2));
        assertEquals(false, MatrixF.isSameDimensions(m1, m3));
        assertEquals(false, MatrixF.isSameDimensions(m1, m4));
        assertEquals(false, MatrixF.isSameDimensions(m1, m5));
    }
    @Test void testIsMultiplicationDimension() {
        MatrixF m1 = new MatrixF(3, 4);
        MatrixF m2 = new MatrixF(4, 4);
        MatrixF m3 = new MatrixF(4, 5);
        MatrixF m4 = new MatrixF(3, 5);
        MatrixF m5 = new MatrixF(3, 4);

        assertEquals(true, MatrixF.isMultiplicationDimension(m1, m2));
        assertEquals(true, MatrixF.isMultiplicationDimension(m1, m3));
        assertEquals(false, MatrixF.isMultiplicationDimension(m1, m4));
        assertEquals(false, MatrixF.isMultiplicationDimension(m1, m5));
    }
    @Test void testIsRowIndexValid() {
        MatrixF m = new MatrixF(2, 3);

        assertEquals(false, isRowIndexValid(m, -1));
        assertEquals(true, isRowIndexValid(m, 1));
        assertEquals(false, isRowIndexValid(m, 2));
    }
    @Test void testIsColumnIndexValid() {
        MatrixF m = new MatrixF(2, 3);

        assertEquals(false, isColIndexValid(m, -1));
    assertEquals(true, isColIndexValid(m, 2));
    assertEquals(false, isColIndexValid(m, 3));
}
    @Test void testIsSubMatrixDimensionValid() {
    MatrixF m = new MatrixF(2, 3);

    assertEquals(false, isSubMatrixDimensionValid(m, 1, 0,0, 1));
    assertEquals(false, isSubMatrixDimensionValid(m, -1, 0,0, 1));
    assertEquals(false, isSubMatrixDimensionValid(m, 0, 4,0, 1));
    assertEquals(false, isSubMatrixDimensionValid(m, 3, 4,0, 1));
    assertEquals(true, isSubMatrixDimensionValid(m, 0, 0,0, 1));

    assertEquals(false, isSubMatrixDimensionValid(m,  0, 1, 1, 0));
    assertEquals(false, isSubMatrixDimensionValid(m,  0, 1,-1, 0));
    assertEquals(false, isSubMatrixDimensionValid(m,  0, 1, 0, 4));
    assertEquals(false, isSubMatrixDimensionValid(m,  0, 1, 3, 4));
    assertEquals(true, isSubMatrixDimensionValid(m,  0, 1, 0, 0));

    assertEquals(true, isSubMatrixDimensionValid(m,  0, 1, 0, 2));
    assertEquals(true, isSubMatrixDimensionValid(m,  1, 1, 1, 2));
}
    @Test void testIsRowDimensionValid() {
    MatrixF m = new MatrixF(3,2);

    MatrixF line1 = new MatrixF(1, 2);
    MatrixF line2 = new MatrixF(1, 3);
    MatrixF line3 = new MatrixF(2, 2);
    MatrixF line4 = new MatrixF(2, 3);

    assertEquals(true, MatrixF.isRowDimensionValid(m, line1));
    assertEquals(false, MatrixF.isRowDimensionValid(m, line2));
    assertEquals(false, MatrixF.isRowDimensionValid(m, line3));
    assertEquals(false, MatrixF.isRowDimensionValid(m, line4));
}
    @Test void testIsColDimensionValid() {
    MatrixF m = new MatrixF(2,3);

    MatrixF line1 = new MatrixF(2, 1);
    MatrixF line2 = new MatrixF(3, 1);
    MatrixF line3 = new MatrixF(2, 2);
    MatrixF line4 = new MatrixF(3, 2);

    assertEquals(true, MatrixF.isColDimensionValid(m, line1));
    assertEquals(false, MatrixF.isColDimensionValid(m, line2));
    assertEquals(false, MatrixF.isColDimensionValid(m, line3));
    assertEquals(false, MatrixF.isColDimensionValid(m, line4));
}
}