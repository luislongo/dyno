package com.alura.dyno.math.graphics;

import com.alura.dyno.math.linalg.Algebra;

import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

class FloatMatrixTest {
    private static final float DELTA = 0.00001f;

    @Test
    void testRows_1() {
        GraphicMatrix m = new GraphicMatrix();
        assertEquals(4, m.rows());
    }
    @Test void testCols_1() {
        GraphicMatrix m = new GraphicMatrix();
        assertEquals(4, m.rows());
    }
    @Test void testCount_1() {
        GraphicMatrix m = new GraphicMatrix();
        assertEquals(4, m.rows());
    }
    @Test void testIsSquare() {
        GraphicMatrix m = new GraphicMatrix();
        assertEquals(true, m.isSquare());
    }

    //Test multiply
    @Test void testPreMultiply_1() {
        GraphicMatrix m1 = new GraphicMatrix(0.0f);
        GraphicMatrix m2 = new GraphicMatrix(new float[]{
                -7.0f, -6.0f, -5.0f, -4.0f,
                -3.0f, -2.0f, -1.0f,  0.0f,
                 1.0f,  2.0f,  3.0f,  4.0f,
                 5.0f,  6.0f,  7.0f,  8.0f
        });
        GraphicMatrix expected = new GraphicMatrix(0.0f);

        assertArrayEquals(expected.toArray(), m1.preMultiply(m2).toArray(), DELTA);
    }
    @Test void testPreMultiply_2() {
        GraphicMatrix m1 = Algebra.graphicMatrixFactory().identity();
        GraphicMatrix m2 = new GraphicMatrix(new float[]{
                -7.0f, -6.0f, -5.0f, -4.0f,
                -3.0f, -2.0f, -1.0f,  0.0f,
                1.0f,  2.0f,  3.0f,  4.0f,
                5.0f,  6.0f,  7.0f,  8.0f
        });
        GraphicMatrix expected = m2.clone();

        assertArrayEquals(expected.toArray(), m1.preMultiply(m2).toArray(), DELTA);
    }
    @Test void testPreMultiply_3() {
        GraphicMatrix m1 = new GraphicMatrix(new float[]{
                -7.0f, -6.0f, -5.0f, -4.0f,
                -3.0f, -2.0f, -1.0f, 0.0f,
                1.0f,  2.0f,  3.0f, 4.0f,
                5.0f,  6.0f,  7.0f, 8.0f
        });
        GraphicMatrix m2 = new GraphicMatrix(new float[]{
                -7.0f, -6.0f, -5.0f, -4.0f,
                -3.0f, -2.0f, -1.0f, 0.0f,
                1.0f,  2.0f,  3.0f, 4.0f,
                5.0f,  6.0f,  7.0f, 8.0f
        });
        GraphicMatrix expected = new GraphicMatrix(new float[] {
                42.0f, 20.0f, -2.0f, -24.0f,
                26.0f, 20.0f, 14.0f, 8.0f,
                10.0f, 20.0f, 30.0f, 40.0f,
                -6.0f, 20.0f, 46.0f, 72.0f
        });

        assertArrayEquals(expected.toArray(), m1.preMultiply(m2).toArray(), DELTA);
    }
    @Test void testPreMultiply_4() {
        GraphicMatrix m1 = new GraphicMatrix(new float[]{
                -7.0f, -6.0f, -5.0f, -4.0f,
                -3.0f, -2.0f, -1.0f,  0.0f,
                1.0f,  2.0f,  3.0f,  4.0f,
                5.0f,  6.0f,  7.0f,  8.0f
        });
        GraphicMatrix m2 = new GraphicMatrix(new float[]{
                 8.0f,  7.0f,  6.0f,  5.0f,
                 4.0f,  3.0f,  2.0f,  1.0f,
                 0.0f, -1.0f, -2.0f, -3.0f,
                -4.0f, -5.0f, -6.0f, -7.0f
        });
        GraphicMatrix expected = new GraphicMatrix(new float[] {
                -46.0f, -20.0f,   6.0f,  32.0f,
                -30.0f, -20.0f, -10.0f,   0.0f,
                -14.0f, -20.0f, -26.0f, -32.0f,
                  2.0f, -20.0f, -42.0f, -64.0f
        });

        assertArrayEquals(expected.toArray(), m1.preMultiply(m2).toArray(), DELTA);
    }
    @Test void testPostMultiply_1() {
        GraphicMatrix m1 = new GraphicMatrix(0.0f);
        GraphicMatrix m2 = new GraphicMatrix(new float[]{
                -7.0f, -6.0f, -5.0f, -4.0f,
                -3.0f, -2.0f, -1.0f, 0.0f,
                 1.0f,  2.0f,  3.0f, 4.0f,
                 5.0f,  6.0f,  7.0f, 8.0f
        });
        GraphicMatrix expected = new GraphicMatrix(0.0f);

        assertArrayEquals(expected.toArray(), m1.postMultiply(m2).toArray(), DELTA);
    }
    @Test void testPostMultiply_2() {
        GraphicMatrix m1 = Algebra.graphicMatrixFactory().identity();
        GraphicMatrix m2 = new GraphicMatrix(new float[]{
                -7.0f, -6.0f, -5.0f, -4.0f,
                -3.0f, -2.0f, -1.0f,  0.0f,
                1.0f,  2.0f,  3.0f,  4.0f,
                5.0f,  6.0f,  7.0f,  8.0f
        });
        GraphicMatrix expected = m2.clone();

        assertArrayEquals(expected.toArray(), m1.postMultiply(m2).toArray(), DELTA);
    }
    @Test void testPostMultiply_3() {
        GraphicMatrix m1 = new GraphicMatrix(new float[]{
                -7.0f, -6.0f, -5.0f, -4.0f,
                -3.0f, -2.0f, -1.0f, 0.0f,
                1.0f,  2.0f,  3.0f, 4.0f,
                5.0f,  6.0f,  7.0f, 8.0f
        });
        GraphicMatrix m2 = new GraphicMatrix(new float[]{
                -7.0f, -6.0f, -5.0f, -4.0f,
                -3.0f, -2.0f, -1.0f, 0.0f,
                1.0f,  2.0f,  3.0f, 4.0f,
                5.0f,  6.0f,  7.0f, 8.0f
        });
        GraphicMatrix expected = new GraphicMatrix(new float[] {
                42.0f, 20.0f, -2.0f, -24.0f,
                26.0f, 20.0f, 14.0f, 8.0f,
                10.0f, 20.0f, 30.0f, 40.0f,
                -6.0f, 20.0f, 46.0f, 72.0f
        });

        assertArrayEquals(expected.toArray(), m1.postMultiply(m2).toArray(), DELTA);
    }
    @Test void testPostMultiply_4() {
        GraphicMatrix m1 = new GraphicMatrix(new float[]{
                -7.0f, -6.0f, -5.0f, -4.0f,
                -3.0f, -2.0f, -1.0f,  0.0f,
                 1.0f,  2.0f,  3.0f,  4.0f,
                 5.0f,  6.0f,  7.0f,  8.0f
        });
        GraphicMatrix m2 = new GraphicMatrix(new float[]{
                 8.0f,  7.0f,  6.0f,  5.0f,
                 4.0f,  3.0f,  2.0f,  1.0f,
                 0.0f, -1.0f, -2.0f, -3.0f,
                -4.0f, -5.0f, -6.0f, -7.0f
        });
        GraphicMatrix expected = new GraphicMatrix(new float[] {
                -64.0f, -42.0f, -20.0f,   2.0f,
                -32.0f, -26.0f, -20.0f, -14.0f,
                  0.0f, -10.0f, -20.0f, -30.0f,
                 32.0f,   6.0f, -20.0f, -46.0f
        });

        assertArrayEquals(expected.toArray(), m1.postMultiply(m2).toArray(), DELTA);
    }
    @Test void testPrePostMultiply_1() {
        GraphicMatrix m1 = new GraphicMatrix(new float[]{
                -7.0f, -6.0f, -5.0f, -4.0f,
                -3.0f, -2.0f, -1.0f,  0.0f,
                1.0f,  2.0f,  3.0f,  4.0f,
                5.0f,  6.0f,  7.0f,  8.0f
        });
        GraphicMatrix m2 = new GraphicMatrix(new float[]{
                8.0f,  7.0f,  6.0f,  5.0f,
                4.0f,  3.0f,  2.0f,  1.0f,
                0.0f, -1.0f, -2.0f, -3.0f,
                -4.0f, -5.0f, -6.0f, -7.0f
        });

        assertArrayEquals(m1.clone().preMultiply(m2).toArray(), m2.clone().postMultiply(m1).toArray(), DELTA);
    }

    //Test invert
    @Test void testInvert_1() {
        GraphicMatrix m1 = Algebra.graphicMatrixFactory().identity();

        GraphicMatrix expected = Algebra.graphicMatrixFactory().identity();
        assertArrayEquals(m1.toArray(), expected.toArray(), DELTA);
    }
    @Test void testInvert_2() {
        GraphicMatrix m = new GraphicMatrix(new float[]{
                -7.0f, -6.0f, -5.0f, -4.0f,
                -3.0f, -2.0f, -1.0f,  2.0f,
                1.0f,  3.0f,  3.0f,  4.0f,
                2.0f,  2.0f,  7.0f,  8.0f
        });

        GraphicMatrix expected = m.clone();
        assertArrayEquals(expected.toArray(), m.invert().invert().toArray(), DELTA);
    }
    @Test void testInvert_3() {
        GraphicMatrix m1 = new GraphicMatrix(new float[]{
                 7.0f, -6.0f, -5.0f, -4.0f,
                -2.0f,  2.0f, -1.0f,  2.0f,
                 1.0f,  3.0f,  2.0f,  4.0f,
                 4.0f,  2.0f,  7.0f,  1.0f
        });
        GraphicMatrix m2 = m1.clone().invert();

        GraphicMatrix expected = Algebra.graphicMatrixFactory().identity();
        assertArrayEquals(expected.toArray(), m1.clone().preMultiply(m2).toArray(), DELTA);
        assertArrayEquals(expected.toArray(), m1.clone().postMultiply(m2).toArray(), DELTA);
    }

    //Test transpose
    @Test void testTranspose_1() {
        GraphicMatrix m = Algebra.graphicMatrixFactory().identity();
        m.transpose();

        assertArrayEquals(Algebra.graphicMatrixFactory().identity().toArray(), m.toArray(), DELTA);
    }
    @Test void testTranspose_2() {
        GraphicMatrix m1 = new GraphicMatrix(new float[]{
                -7.0f, -6.0f, -5.0f, -4.0f,
                -3.0f, -2.0f, -1.0f,  2.0f,
                1.0f,  3.0f,  3.0f,  4.0f,
                2.0f,  2.0f,  7.0f,  8.0f
        });
        GraphicMatrix m2 = m1.clone().transpose().transpose();

        assertArrayEquals(m2.toArray(), m1.toArray(), DELTA);
    }
    @Test void testTranspose_3() {
        GraphicMatrix m1 = new GraphicMatrix(new float[]{
                -7.0f, -6.0f, -5.0f, -4.0f,
                -3.0f, -2.0f, -1.0f,  2.0f,
                1.0f,  3.0f,  3.0f,  4.0f,
                2.0f,  2.0f,  7.0f,  8.0f
        });
        GraphicMatrix expected = new GraphicMatrix(new float[] {
                -7.0f, -3.0f, 1.0f, 2.0f,
                -6.0f, -2.0f, 3.0f, 2.0f,
                -5.0f, -1.0f, 3.0f, 7.0f,
                -4.0f,  2.0f, 4.0f, 8.0f
        });

        assertArrayEquals(expected.toArray(), m1.transpose().toArray(), DELTA);
    }

    //Test clone
    @Test void testClone_1() {
        GraphicMatrix m1 = new GraphicMatrix(new float[]{
                -7.0f, -6.0f, -5.0f, -4.0f,
                -3.0f, -2.0f, -1.0f,  2.0f,
                1.0f,  3.0f,  3.0f,  4.0f,
                2.0f,  2.0f,  7.0f,  8.0f
        });
        GraphicMatrix m2 = m1.clone();

        assertArrayEquals(m1.toArray(), m2.toArray(), DELTA);
    }
}