package com.alura.dyno.math.linalg;

import com.alura.dyno.math.graphics.GraphicMatrix;
import com.alura.dyno.math.graphics.Vector2;
import com.alura.dyno.math.graphics.Vector3;
import com.alura.dyno.math.graphics.Vector4;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import static org.junit.jupiter.api.Assertions.*;

class FloatVectorTest {

    private static final float DELTA = 0.000001f;

    //Test norm2()
    @Test void testNorm2_1() {
        Vector2 v = new Vector2(-1.0f, 0.0f);
        assertEquals(v.norm2(), 1.0f, DELTA);

    }
    @Test void testNorm2_2() {
        Vector3 v = new Vector3(-1.0f, 0.0f, 1.0f);
        assertEquals(v.norm2(), Math.sqrt(2.0d), DELTA);

    }
    @Test void testNorm2_3() {
        Vector4 v = new Vector4(-1.0f, 0.0f, 1.0f, 2.0f);
        assertEquals(v.norm2(), Math.sqrt(6.0d), DELTA);

    }
    @Test void testNorm2_4() {
        Vector2 v = new Vector2(-1.0f, -1.0f);
        assertEquals(v.norm2(), Math.sqrt(2.0d), DELTA);
    }
    @Test void testNorm2_5() {
        Vector3 v = new Vector3(-1.0f, -1.0f, -1.0f);
        assertEquals(v.norm2(), Math.sqrt(3.0d), DELTA);
    }
    @Test void testNorm2_6() {
        Vector4 v = new Vector4(-1.0f, -1.0f, -1.0f, -1.0f);
        assertEquals(v.norm2(), 2.0f, DELTA);
    }
    @Test void testNorm2_7() {
        Vector2 v = new Vector2( 0.0f, 0.0f);
        assertEquals(v.norm2(), 0.0f, DELTA);
    }
    @Test void testNorm2_8() {
        Vector3 v = new Vector3(0.0f, 0.0f, 0.0f);
        assertEquals(v.norm2(), 0.0f, DELTA);
    }
    @Test void testNorm2_9() {
        Vector4 v = new Vector4(0.0f, 0.0f, 0.0f, 0.0f);
        assertEquals(v.norm2(), 0.0f, DELTA);
    }

    //Test length()
    @Test void testLength_1() {
        Vector2 v1 = new Vector2();
        assertEquals(v1.length(), 2);
    }
    @Test void testLength_2() {
        Vector3 v2 = new Vector3();
        assertEquals(v2.length(), 3);
    }
    @Test void testLength_3() {
        Vector4 v3 = new Vector4();
        assertEquals(v3.length(), 4);
    }

    //Test toArray()
    @Test void testToArray_1() {
        Vector2 v = new Vector2(-1.0f, 0.0f);
        assertArrayEquals(v.toArray(), new float[]{-1.0f, 0.0f});
    }
    @Test void testToArray_2() {
        Vector3 v = new Vector3(-1.0f, 0.0f, 1.0f);
        assertArrayEquals(v.toArray(), new float[]{-1.0f, 0.0f, 1.0f});
    }
    @Test void testToArray_3() {
        Vector4 v = new Vector4(-1.0f, 0.0f, 1.0f, 2.0f);
        assertArrayEquals(v.toArray(), new float[]{-1.0f, 0.0f, 1.0f, 2.0f});
    }

    //Test plus()
    @Test void testPlus_1() {
        Vector2 v1 = new Vector2(-1.0f, 2.0f);
        Vector2 v2 = new Vector2(-1.0f, 1.0f);

        Vector2 expected = new Vector2(-2.0f, 3.0f);
        assertArrayEquals(v1.plus(v2).toArray(), expected.toArray());
    }
    @Test void testPlus_2() {
        Vector3 v1 = new Vector3(-1.0f, 2.0f, -2.0f);
        Vector3 v2 = new Vector3(-1.0f, 3.0f, 4.0f);

        Vector3 expected = new Vector3(-2.0f, 5.0f, 2.0f);
        assertArrayEquals(v1.plus(v2).toArray(), expected.toArray());
    }
    @Test void testPlus_3() {
        Vector4 v1 = new Vector4(-1.0f, 2.0f, -2.0f, 4.0f);
        Vector4 v2 = new Vector4(-1.0f, 1.0f, 3.0f, 4.0f);

        Vector4 expected = new Vector4(-2.0f, 3.0f, 1.0f, 8.0f);
        assertArrayEquals(v1.plus(v2).toArray(), expected.toArray());
    }

    //Test minus()
    @Test void testMinus_1() {
        Vector2 v1 = new Vector2(-1.0f, 2.0f);
        Vector2 v2 = new Vector2(-1.0f, 1.0f);

        Vector2 expected = new Vector2(0.0f, 1.0f);
        assertArrayEquals(v1.minus(v2).toArray(), expected.toArray());
    }
    @Test void testMinus_2() {
        Vector3 v1 = new Vector3(-1.0f, 2.0f, -2.0f);
        Vector3 v2 = new Vector3(-1.0f, 3.0f, 4.0f);

        Vector3 expected = new Vector3(0.0f, -1.0f, -6.0f);
        assertArrayEquals(v1.minus(v2).toArray(), expected.toArray());
    }
    @Test void testMinus_3() {
        Vector4 v1 = new Vector4(-1.0f, 2.0f, -2.0f, 4.0f);
        Vector4 v2 = new Vector4(-1.0f, 1.0f, 3.0f, 4.0f);

        Vector4 expected = new Vector4(0.0f, 1.0f, -5.0f, 0.0f);
        assertArrayEquals(v1.minus(v2).toArray(), expected.toArray());
    }

    //Test multiply by constant
    @Test void testMultiply_1() {
        Vector2 v = new Vector2(-1.0f, 2.0f);
        float c = 0.0f;

        Vector2 expected = new Vector2(0.0f);
        assertArrayEquals(v.multiply(c).toArray(), expected.toArray(), DELTA);
    }
    @Test void testMultiply_2() {
        Vector3 v = new Vector3(-1.0f, 2.0f, -2.0f);
        float c = 0.0f;

        Vector3 expected = new Vector3(0.0f);
        assertArrayEquals(v.multiply(c).toArray(), expected.toArray(), DELTA);
    }
    @Test void testMultiply_3() {
        Vector4 v = new Vector4(-1.0f, 2.0f, -2.0f, 4.0f);
        float c = 0.0f;

        Vector4 expected = new Vector4(0.0f);
        assertArrayEquals(v.multiply(c).toArray(), expected.toArray(), DELTA);
    }
    @Test void testMultiply_4() {
        Vector2 v = new Vector2(-1.0f, 2.0f);
        float c = 1.0f;

        assertArrayEquals(v.clone().multiply(c).toArray(), v.toArray(), DELTA);
    }
    @Test void testMultiply_5() {
        Vector3 v = new Vector3(-1.0f, 2.0f, -2.0f);
        float c = 1.0f;

        assertArrayEquals(v.clone().multiply(c).toArray(), v.toArray(), DELTA);
    }
    @Test void testMultiply_6() {
        Vector4 v = new Vector4(-1.0f, 2.0f, -2.0f, 4.0f);
        float c = 1.0f;

        assertArrayEquals(v.clone().multiply(c).toArray(), v.toArray(), DELTA);
    }
    @Test void testMultiply_7() {
        Vector2 v = new Vector2(-1.0f, 2.0f);
        float c = -1.0f;

        Vector2 expected = new Vector2(1.0f, -2.0f);
        assertArrayEquals(v.multiply(c).toArray(), expected.toArray(), DELTA);
    }
    @Test void testMultiply_8() {
        Vector3 v = new Vector3(-1.0f, 2.0f, -2.0f);
        float c = -1.0f;

        Vector3 expected = new Vector3(1.0f, -2.0f, 2.0f);
        assertArrayEquals(v.multiply(c).toArray(), expected.toArray(), DELTA);
    }
    @Test void testMultiply_9() {
        Vector4 v = new Vector4(-1.0f, 2.0f, -2.0f, 4.0f);
        float c = -1.0f;

        Vector4 expected = new Vector4(1.0f, -2.0f, 2.0f, -4.0f);
        assertArrayEquals(v.multiply(c).toArray(), expected.toArray(), DELTA);
    }

    //Test multiply by matrix
    @Test void testMultiply_10() {
        Vector2 v = new Vector2(-1.0f, 2.0f);
        GraphicMatrix m = new GraphicMatrix(new float[]
                {0.0f, 0.0f, 0.0f, 0.0f,
                 0.0f, 0.0f, 0.0f, 0.0f,
                 0.0f, 0.0f, 0.0f, 0.0f,
                 0.0f, 0.0f, 0.0f, 0.0f});

        Vector2 expected = new Vector2(0.0f, 0.0f);
        assertArrayEquals(v.multiply(m, 3.0f, -2.0f).toArray(), expected.toArray(), DELTA);
    }
    @Test void testMultiply_11() {
        Vector3 v = new Vector3(-1.0f, 2.0f, 3.0f);
        GraphicMatrix m = new GraphicMatrix(new float[]
                {0.0f, 0.0f, 0.0f, 0.0f,
                        0.0f, 0.0f, 0.0f, 0.0f,
                        0.0f, 0.0f, 0.0f, 0.0f,
                        0.0f, 0.0f, 0.0f, 0.0f});

        Vector3 expected = new Vector3(0.0f, 0.0f, 0.0f);
        assertArrayEquals(v.multiply(m, -2.0f).toArray(), expected.toArray(), DELTA);
    }
    @Test void testMultiply_12() {
        Vector4 v = new Vector4(-1.0f, 2.0f, 3.0f, -2.0f);
        GraphicMatrix m = new GraphicMatrix(new float[]
                {0.0f, 0.0f, 0.0f, 0.0f,
                        0.0f, 0.0f, 0.0f, 0.0f,
                        0.0f, 0.0f, 0.0f, 0.0f,
                        0.0f, 0.0f, 0.0f, 0.0f});

        Vector4 expected = new Vector4(0.0f, 0.0f,0.0f,0.0f);
        assertArrayEquals(v.multiply(m).toArray(), expected.toArray(), DELTA);
    }
    @Test void testMultiply_13() {
        Vector2 v = new Vector2(-1.0f, 2.0f);
        GraphicMatrix m = Algebra.graphicMatrixBuilder().identity();

        assertArrayEquals(v.clone().multiply(m, 0.0f,0.0f).toArray(), v.toArray(), DELTA);
    }
    @Test void testMultiply_14() {
        Vector3 v = new Vector3(-1.0f, 2.0f, 3.0f);
        GraphicMatrix m = Algebra.graphicMatrixBuilder().identity();

        assertArrayEquals(v.clone().multiply(m, 0.0f).toArray(), v.toArray(), DELTA);
    }
    @Test void testMultiply_15() {
        Vector4 v = new Vector4(-1.0f, 2.0f, 3.0f, 0.0f);
        GraphicMatrix m = Algebra.graphicMatrixBuilder().identity();

        assertArrayEquals(v.clone().multiply(m).toArray(), v.toArray(), DELTA);
    }
    @Test void testMultiply_16() {
        Vector2 v = new Vector2(-1.0f, 2.0f);
        GraphicMatrix m = new GraphicMatrix(new float[]
                { 2.0f, -1.0f,  4.0f, -3.0f,
                        3.0f, -2.0f,  3.0f, -3.0f,
                        -2.0f,  3.0f, -2.0f, -2.0f,
                        4.0f,  5.0f,  3.0f,  2.0f});

        Vector2 expected = new Vector2(14, 8);
        assertArrayEquals(v.clone().multiply(m, 3.0f, -2.0f).toArray(), expected.toArray(), DELTA);
    }
    @Test void testMultiply_17() {
        Vector3 v = new Vector3(-1.0f, 2.0f, 3.0f);
        GraphicMatrix m = new GraphicMatrix(new float[]
                { 2.0f, -1.0f,  4.0f, -3.0f,
                        3.0f, -2.0f,  3.0f, -3.0f,
                        -2.0f,  3.0f, -2.0f, -2.0f,
                        4.0f,  5.0f,  3.0f,  2.0f});

        Vector3 expected = new Vector3(14, 8, 6);
        assertArrayEquals(v.clone().multiply(m, -2.0f).toArray(), expected.toArray(), DELTA);
    }
    @Test void testMultiply_18() {
        Vector4 v = new Vector4(-1.0f, 2.0f, 3.0f,-2.0f);
        GraphicMatrix m = new GraphicMatrix(new float[]
                { 2.0f, -1.0f,  4.0f, -3.0f,
                        3.0f, -2.0f,  3.0f, -3.0f,
                        -2.0f,  3.0f, -2.0f, -2.0f,
                        4.0f,  5.0f,  3.0f,  2.0f});

        Vector4 expected = new Vector4(14, 8, 6, 11);
        assertArrayEquals(v.clone().multiply(m).toArray(), expected.toArray(), DELTA);
    }

    //Test divide by constant
    @Test void testDivide_1() {
        final Vector2 v = new Vector2(-1.0f, 2.0f);
        final float c = 0.0f;

        assertThrows(IllegalArgumentException.class, new Executable() {
            @Override
            public void execute() throws IllegalArgumentException {
                v.divide(c);
            }
        });
    }
    @Test void testDivide_2() {
        final Vector3 v = new Vector3(-1.0f, 2.0f, 3.0f);
        final float c = 0.0f;

        assertThrows(IllegalArgumentException.class, new Executable() {
            @Override
            public void execute() throws IllegalArgumentException {
                v.divide(c);
            }
        });
    }
    @Test void testDivide_3() {
        final Vector4 v = new Vector4(-1.0f, 2.0f, 3.0f,-2.0f);
        final float c = 0.0f;

        assertThrows(IllegalArgumentException.class, new Executable() {
            @Override
            public void execute() throws IllegalArgumentException {
                v.divide(c);
            }
        });
    }
    @Test void testDivide_4() {
        Vector2 v = new Vector2(-1.0f, 2.0f);
        float c = 2.0f;

        Vector2 expected = new Vector2(-0.5f, 1.0f);
        assertArrayEquals(v.divide(c).toArray(), expected.toArray(), DELTA);
    }
    @Test void testDivide_5() {
        Vector3 v = new Vector3(-1.0f, 2.0f, 3.0f);
        float c = 2.0f;

        Vector3 expected = new Vector3(-0.5f, 1.0f, 1.5f);
        assertArrayEquals(v.divide(c).toArray(), expected.toArray(), DELTA);
    }
    @Test void testDivide_6() {
        Vector4 v = new Vector4(-1.0f, 2.0f, 3.0f,-2.0f);
        float c = 2.0f;

        Vector4 expected = new Vector4(-0.5f, 1.0f, 1.5f, -1.0f);
        assertArrayEquals(v.divide(c).toArray(), expected.toArray(), DELTA);
    }
    @Test void testDivide_7() {
        Vector2 v = new Vector2(-1.0f, 2.0f);
        float c = -2.0f;

        Vector2 expected = new Vector2(0.5f, -1.0f);
        assertArrayEquals(v.divide(c).toArray(), expected.toArray(), DELTA);
    }
    @Test void testDivide_8() {
        Vector3 v = new Vector3(-1.0f, 2.0f, 3.0f);
        float c = -2.0f;

        Vector3 expected = new Vector3(0.5f, -1.0f, -1.5f);
        assertArrayEquals(v.divide(c).toArray(), expected.toArray(), DELTA);
    }
    @Test void testDivide_9() {
        Vector4 v = new Vector4(-1.0f, 2.0f, 3.0f,-2.0f);
        float c = -2.0f;

        Vector4 expected = new Vector4(0.5f, -1.0f, -1.5f, 1.0f);
        assertArrayEquals(v.divide(c).toArray(), expected.toArray(), DELTA);
    }

    //Test straight product
    @Test void testStraightProduct_1() {
        Vector2 v1 = new Vector2(-1.0f, 2.0f);
        Vector2 v2 = new Vector2(0.0f, 0.0f);

        Vector2 expected = new Vector2(0.0f);
        assertArrayEquals(v1.straightProduct(v2).toArray(), expected.toArray(), DELTA);
    }
    @Test void testStraightProduct_2() {
        Vector3 v1 = new Vector3(-1.0f, 2.0f, 3.0f);
        Vector3 v2 = new Vector3(0.0f, 0.0f, 0.0f);

        Vector3 expected = new Vector3(0.0f);
        assertArrayEquals(v1.straightProduct(v2).toArray(), expected.toArray(), DELTA);
    }
    @Test void testStraightProduct_3() {
        Vector4 v1 = new Vector4(-1.0f, 2.0f, 3.0f,-2.0f);
        Vector4 v2 = new Vector4(0.0f, 0.0f, 0.0f, 0.0f);

        Vector4 expected = new Vector4(0.0f);
        assertArrayEquals(v1.straightProduct(v2).toArray(), expected.toArray(), DELTA);
    }
    @Test void testStraightProduct_4() {
        Vector2 v1 = new Vector2(-1.0f, 2.0f);
        Vector2 v2 = new Vector2(1.0f, 1.0f);

        assertArrayEquals(v1.clone().straightProduct(v2).toArray(), v1.toArray(), DELTA);
    }
    @Test void testStraightProduct_5() {
        Vector3 v1 = new Vector3(-1.0f, 2.0f, 3.0f);
        Vector3 v2 = new Vector3(1.0f, 1.0f, 1.0f);

        assertArrayEquals(v1.clone().straightProduct(v2).toArray(), v1.toArray(), DELTA);
    }
    @Test void testStraightProduct_6() {
        Vector4 v1 = new Vector4(-1.0f, 2.0f, 3.0f,-2.0f);
        Vector4 v2 = new Vector4(1.0f, 1.0f, 1.0f, 1.0f);

        assertArrayEquals(v1.clone().straightProduct(v2).toArray(), v1.toArray(), DELTA);
    }
    @Test void testStraightProduct_7() {
        Vector2 v1 = new Vector2(-1.0f, 2.0f);
        Vector2 v2 = new Vector2(-3.0f, 4.0f);

        Vector2 expected = new Vector2(3.0f, 8.0f);
        assertArrayEquals(v1.straightProduct(v2).toArray(), expected.toArray(), DELTA);
    }
    @Test void testStraightProduct_8() {
        Vector3 v1 = new Vector3(-1.0f, 2.0f, 3.0f);
        Vector3 v2 = new Vector3(-3.0f, 2.0f, 5.0f);

        Vector3 expected = new Vector3(3.0f, 4.0f, 15.0f);
        assertArrayEquals(v1.straightProduct(v2).toArray(), expected.toArray(), DELTA);
    }
    @Test void testStraightProduct_9() {
        Vector4 v1 = new Vector4(-1.0f, 2.0f, 3.0f,-2.0f);
        Vector4 v2 = new Vector4(4.0f, -3.0f, 2.0f, -6.0f);

        Vector4 expected = new Vector4(-4.0f, -6.0f, 6.0f, 12.0f);
        assertArrayEquals(v1.straightProduct(v2).toArray(), v1.toArray(), DELTA);
    }

    @Test void testCross_1() {
        Vector3 x = new Vector3(1.0f, 0.0f, 0.0f);
        Vector3 y = new Vector3(0.0f, 1.0f, 0.0f);

        Vector3 expected = new Vector3(0.0f, 0.0f, 1.0f);
        assertArrayEquals(expected.toArray(), x.cross(y).toArray(), DELTA);
    }
    @Test void testCross_2() {
        Vector3 x = new Vector3(1.0f, 0.0f, 0.0f);
        Vector3 z = new Vector3(0.0f, 0.0f, 1.0f);

        Vector3 expected = new Vector3(0.0f, 1.0f, 0.0f);
        assertArrayEquals(expected.toArray(), z.cross(x).toArray(), DELTA);
    }
    @Test void testCross_3() {
        Vector3 y = new Vector3(0.0f, 1.0f, 0.0f);
        Vector3 z = new Vector3(0.0f, 0.0f, 1.0f);

        Vector3 expected = new Vector3(1.0f, 0.0f, 0.0f);
        assertArrayEquals(expected.toArray(), y.cross(z).toArray(), DELTA);
    }

    //Test normalize()
    @Test void testNormalize_1() {
        Vector2 v = new Vector2(-3.0f, 3.0f);
        v.normalize();

        assertEquals(1.0f, v.norm2());
    }
    @Test void testNormalize_2() {
        Vector3 v = new Vector3(2.0f, -3.0f, 2.0f);
        v.normalize();

        assertEquals(1.0f, v.norm2());
    }
    @Test void testNormalize_3() {
        Vector4 v = new Vector4(2.0f, 3.0f, -1.0f, -3.0f);
        v.normalize();

        assertEquals(1.0f, v.norm2());
    }
    @Test void testNormalize_4() {
        Vector2 v = new Vector2(0.0f);
        v.normalize();

        assertEquals(0.0f, v.norm2());
    }
    @Test void testNormalize_5() {
        Vector3 v = new Vector3(0.0f);
        v.normalize();

        assertEquals(0.0f, v.norm2());
    }
    @Test void testNormalize_6() {
        Vector4 v = new Vector4(0.0f);
        v.normalize();

        assertEquals(0.0f, v.norm2());
    }

    //Test clone()
    @Test void testClone_1() {
        Vector2 v = new Vector2((float) Math.random(),(float) Math.random());
        assertArrayEquals(v.toArray(), v.clone().toArray(), DELTA);
    }
    @Test void testClone_2() {
        Vector3 v = new Vector3((float) Math.random(),(float) Math.random(), (float) Math.random());
        assertArrayEquals(v.toArray(), v.clone().toArray(), DELTA);
    }
    @Test void testClone_3() {
        Vector4 v = new Vector4((float) Math.random(),(float) Math.random(), (float) Math.random(), (float) Math.random());
        assertArrayEquals(v.toArray(), v.clone().toArray(), DELTA);
    }
}