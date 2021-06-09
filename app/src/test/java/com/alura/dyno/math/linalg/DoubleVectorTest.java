package com.alura.dyno.math.linalg;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import static org.junit.jupiter.api.Assertions.*;

class DoubleVectorTest {
    private static final double DELTA = 0.0000000000000000001f;

    //Test norm2()
    @Test void testNorm2_1() {
        AbstractDoubleVector v = new DoubleVectorN(-1.0d, 0.0d);
        Assertions.assertEquals(v.norm2(), 1.0d, DELTA);

    }
    @Test void testNorm2_2() {
        AbstractDoubleVector v = new DoubleVectorN(-1.0d, 0.0d, 1.0d);
        Assertions.assertEquals(v.norm2(), Math.sqrt(2.0d), DELTA);

    }
    @Test void testNorm2_3() {
        AbstractDoubleVector v = new DoubleVectorN(-1.0d, 0.0d, 1.0d, 2.0d);
        Assertions.assertEquals(v.norm2(), Math.sqrt(6.0d), DELTA);

    }
    @Test void testNorm2_4() {
        AbstractDoubleVector v = new DoubleVectorN(-1.0d, -1.0d);
        Assertions.assertEquals(v.norm2(), Math.sqrt(2.0d), DELTA);
    }
    @Test void testNorm2_5() {
        AbstractDoubleVector v = new DoubleVectorN(-1.0d, -1.0d, -1.0d);
        Assertions.assertEquals(v.norm2(), Math.sqrt(3.0d), DELTA);
    }
    @Test void testNorm2_6() {
        AbstractDoubleVector v = new DoubleVectorN(-1.0d, -1.0d, -1.0d, -1.0d);
        Assertions.assertEquals(v.norm2(), 2.0d, DELTA);
    }
    @Test void testNorm2_7() {
        AbstractDoubleVector v = new DoubleVectorN( 0.0d, 0.0d);
        Assertions.assertEquals(v.norm2(), 0.0d, DELTA);
    }
    @Test void testNorm2_8() {
        AbstractDoubleVector v = new DoubleVectorN(0.0d, 0.0d, 0.0d);
        Assertions.assertEquals(v.norm2(), 0.0d, DELTA);
    }
    @Test void testNorm2_9() {
        AbstractDoubleVector v = new DoubleVectorN(0.0d, 0.0d, 0.0d, 0.0d);
        Assertions.assertEquals(v.norm2(), 0.0d, DELTA);
    }

    //Test length()
    @Test void testLength_1() {
        AbstractDoubleVector v1 = new DoubleVectorN(2, 0.0d);
        Assertions.assertEquals(v1.length(), 2);
    }
    @Test void testLength_2() {
        AbstractDoubleVector v2 = new DoubleVectorN(3, 0.0d);
        Assertions.assertEquals(v2.length(), 3);
    }
    @Test void testLength_3() {
        AbstractDoubleVector v3 = new DoubleVectorN(4, 0.0d);
        Assertions.assertEquals(v3.length(), 4);
    }

    //Test toArray()
    @Test void testToArray_1() {
        AbstractDoubleVector v = new DoubleVectorN(-1.0d, 0.0d);
        Assertions.assertArrayEquals(v.toArray(), new double[]{-1.0d, 0.0d});
    }
    @Test void testToArray_2() {
        AbstractDoubleVector v = new DoubleVectorN(-1.0d, 0.0d, 1.0d);
        Assertions.assertArrayEquals(v.toArray(), new double[]{-1.0d, 0.0d, 1.0d});
    }
    @Test void testToArray_3() {
        AbstractDoubleVector v = new DoubleVectorN(-1.0d, 0.0d, 1.0d, 2.0d);
        Assertions.assertArrayEquals(v.toArray(), new double[]{-1.0d, 0.0d, 1.0d, 2.0d});
    }

    //Test plus()
    @Test void testPlus_1() {
        AbstractDoubleVector v1 = new DoubleVectorN(-1.0d, 2.0d);
        AbstractDoubleVector v2 = new DoubleVectorN(-1.0d, 1.0d);

        AbstractDoubleVector expected = new DoubleVectorN(-2.0d, 3.0d);
        Assertions.assertArrayEquals(v1.plus(v2).toArray(), expected.toArray());
    }
    @Test void testPlus_2() {
        AbstractDoubleVector v1 = new DoubleVectorN(-1.0d, 2.0d, -2.0d);
        AbstractDoubleVector v2 = new DoubleVectorN(-1.0d, 3.0d, 4.0d);

        AbstractDoubleVector expected = new DoubleVectorN(-2.0d, 5.0d, 2.0d);
        Assertions.assertArrayEquals(v1.plus(v2).toArray(), expected.toArray());
    }
    @Test void testPlus_3() {
        AbstractDoubleVector v1 = new DoubleVectorN(-1.0d, 2.0d, -2.0d, 4.0d);
        AbstractDoubleVector v2 = new DoubleVectorN(-1.0d, 1.0d, 3.0d, 4.0d);

        AbstractDoubleVector expected = new DoubleVectorN(-2.0d, 3.0d, 1.0d, 8.0d);
        Assertions.assertArrayEquals(v1.plus(v2).toArray(), expected.toArray());
    }

    //Test minus()
    @Test void testMinus_1() {
        AbstractDoubleVector v1 = new DoubleVectorN(-1.0d, 2.0d);
        AbstractDoubleVector v2 = new DoubleVectorN(-1.0d, 1.0d);

        AbstractDoubleVector expected = new DoubleVectorN(0.0d, 1.0d);
        Assertions.assertArrayEquals(v1.minus(v2).toArray(), expected.toArray());
    }
    @Test void testMinus_2() {
        AbstractDoubleVector v1 = new DoubleVectorN(-1.0d, 2.0d, -2.0d);
        AbstractDoubleVector v2 = new DoubleVectorN(-1.0d, 3.0d, 4.0d);

        AbstractDoubleVector expected = new DoubleVectorN(0.0d, -1.0d, -6.0d);
        Assertions.assertArrayEquals(v1.minus(v2).toArray(), expected.toArray());
    }
    @Test void testMinus_3() {
        AbstractDoubleVector v1 = new DoubleVectorN(-1.0d, 2.0d, -2.0d, 4.0d);
        AbstractDoubleVector v2 = new DoubleVectorN(-1.0d, 1.0d, 3.0d, 4.0d);

        AbstractDoubleVector expected = new DoubleVectorN(0.0d, 1.0d, -5.0d, 0.0d);
        Assertions.assertArrayEquals(v1.minus(v2).toArray(), expected.toArray());
    }

    //Test multiply by constant
    @Test void testMultiply_1() {
        AbstractDoubleVector v = new DoubleVectorN(-1.0d, 2.0d);
        double c = 0.0d;

        AbstractDoubleVector expected = new DoubleVectorN(2, 0.0d);
        assertArrayEquals(v.multiply(c).toArray(), expected.toArray(), DELTA);
    }
    @Test void testMultiply_2() {
        AbstractDoubleVector v = new DoubleVectorN(-1.0d, 2.0d, -2.0d);
        double c = 0.0d;

        AbstractDoubleVector expected = new DoubleVectorN(3, 0.0d);
        Assertions.assertArrayEquals(v.multiply(c).toArray(), expected.toArray(), DELTA);
    }
    @Test void testMultiply_3() {
        AbstractDoubleVector v = new DoubleVectorN(-1.0d, 2.0d, -2.0d, 4.0d);
        double c = 0.0d;

        AbstractDoubleVector expected = new DoubleVectorN(4, 0.0d);
        Assertions.assertArrayEquals(v.multiply(c).toArray(), expected.toArray(), DELTA);
    }
    @Test void testMultiply_4() {
        AbstractDoubleVector v = new DoubleVectorN(-1.0d, 2.0d);
        double c = 1.0d;

        Assertions.assertArrayEquals(v.clone().multiply(c).toArray(), v.toArray(), DELTA);
    }
    @Test void testMultiply_5() {
        AbstractDoubleVector v = new DoubleVectorN(-1.0d, 2.0d, -2.0d);
        double c = 1.0d;

        Assertions.assertArrayEquals(v.clone().multiply(c).toArray(), v.toArray(), DELTA);
    }
    @Test void testMultiply_6() {
        AbstractDoubleVector v = new DoubleVectorN(-1.0d, 2.0d, -2.0d, 4.0d);
        double c = 1.0d;

        Assertions.assertArrayEquals(v.clone().multiply(c).toArray(), v.toArray(), DELTA);
    }
    @Test void testMultiply_7() {
        AbstractDoubleVector v = new DoubleVectorN(-1.0d, 2.0d);
        double c = -1.0d;

        AbstractDoubleVector expected = new DoubleVectorN(1.0d, -2.0d);
        Assertions.assertArrayEquals(v.multiply(c).toArray(), expected.toArray(), DELTA);
    }
    @Test void testMultiply_8() {
        AbstractDoubleVector v = new DoubleVectorN(-1.0d, 2.0d, -2.0d);
        double c = -1.0d;

        AbstractDoubleVector expected = new DoubleVectorN(1.0d, -2.0d, 2.0d);
        Assertions.assertArrayEquals(v.multiply(c).toArray(), expected.toArray(), DELTA);
    }
    @Test void testMultiply_9() {
        AbstractDoubleVector v = new DoubleVectorN(-1.0d, 2.0d, -2.0d, 4.0d);
        double c = -1.0d;

        AbstractDoubleVector expected = new DoubleVectorN(1.0d, -2.0d, 2.0d, -4.0d);
        Assertions.assertArrayEquals(v.multiply(c).toArray(), expected.toArray(), DELTA);
    }

    //Test multiply by matrix
    @Test void testMultiply_12() {
        AbstractDoubleVector v = new DoubleVectorN(-1.0d, 2.0d, 3.0d, -2.0d);
        DoubleMatrix m = new DoubleMatrix(new double[][]{
                {0.0d, 0.0d, 0.0d, 0.0d},
                {0.0d, 0.0d, 0.0d, 0.0d},
                {0.0d, 0.0d, 0.0d, 0.0d},
                {0.0d, 0.0d, 0.0d, 0.0d}});

        AbstractDoubleVector expected = new DoubleVectorN(0.0d, 0.0d,0.0d,0.0d);
        Assertions.assertArrayEquals(v.multiply(m).toArray(), expected.toArray(), DELTA);
    }
    @Test void testMultiply_15() {
        AbstractDoubleVector v = new DoubleVectorN(-1.0d, 2.0d, 3.0d, 0.0d);
        DoubleMatrix m = DoubleMatrix.identity(4);

        Assertions.assertArrayEquals(v.clone().multiply(m).toArray(), v.toArray(), DELTA);
    }
    @Test void testMultiply_18() {
        AbstractDoubleVector v = new DoubleVectorN(-1.0d, 2.0d, 3.0d,-2.0d);
        DoubleMatrix m = new DoubleMatrix(new double[][]{
                {2.0d, -1.0d,  4.0d, -3.0d},
                {3.0d, -2.0d,   3.0d, -3.0d},
                {-2.0d,  3.0d, -2.0d, -2.0d},
                {4.0d,  5.0d,  3.0d,  2.0d}});

        AbstractDoubleVector expected = new DoubleVectorN(14, 8, 6, 11);
        Assertions.assertArrayEquals(v.clone().multiply(m).toArray(), expected.toArray(), DELTA);
    }

    //Test divide by constant
    @Test void testDivide_1() {
        final AbstractDoubleVector v = new DoubleVectorN(-1.0d, 2.0d);
        final double c = 0.0d;

        assertThrows(IllegalArgumentException.class, new Executable() {
            @Override
            public void execute() throws IllegalArgumentException {
                v.divide(c);
            }
        });
    }
    @Test void testDivide_2() {
        final AbstractDoubleVector v = new DoubleVectorN(-1.0d, 2.0d, 3.0d);
        final double c = 0.0d;

        assertThrows(IllegalArgumentException.class, new Executable() {
            @Override
            public void execute() throws IllegalArgumentException {
                v.divide(c);
            }
        });
    }
    @Test void testDivide_3() {
        final AbstractDoubleVector v = new DoubleVectorN(-1.0d, 2.0d, 3.0d,-2.0d);
        final double c = 0.0d;

        assertThrows(IllegalArgumentException.class, new Executable() {
            @Override
            public void execute() throws IllegalArgumentException {
                v.divide(c);
            }
        });
    }
    @Test void testDivide_4() {
        AbstractDoubleVector v = new DoubleVectorN(-1.0d, 2.0d);
        double c = 2.0d;

        AbstractDoubleVector expected = new DoubleVectorN(-0.5f, 1.0d);
        Assertions.assertArrayEquals(v.divide(c).toArray(), expected.toArray(), DELTA);
    }
    @Test void testDivide_5() {
        AbstractDoubleVector v = new DoubleVectorN(-1.0d, 2.0d, 3.0d);
        double c = 2.0d;

        AbstractDoubleVector expected = new DoubleVectorN(-0.5f, 1.0d, 1.5f);
        Assertions.assertArrayEquals(v.divide(c).toArray(), expected.toArray(), DELTA);
    }
    @Test void testDivide_6() {
        AbstractDoubleVector v = new DoubleVectorN(-1.0d, 2.0d, 3.0d,-2.0d);
        double c = 2.0d;

        AbstractDoubleVector expected = new DoubleVectorN(-0.5f, 1.0d, 1.5f, -1.0d);
        Assertions.assertArrayEquals(v.divide(c).toArray(), expected.toArray(), DELTA);
    }
    @Test void testDivide_7() {
        AbstractDoubleVector v = new DoubleVectorN(-1.0d, 2.0d);
        double c = -2.0d;

        AbstractDoubleVector expected = new DoubleVectorN(0.5f, -1.0d);
        Assertions.assertArrayEquals(v.divide(c).toArray(), expected.toArray(), DELTA);
    }
    @Test void testDivide_8() {
        AbstractDoubleVector v = new DoubleVectorN(-1.0d, 2.0d, 3.0d);
        double c = -2.0d;

        AbstractDoubleVector expected = new DoubleVectorN(0.5f, -1.0d, -1.5f);
        Assertions.assertArrayEquals(v.divide(c).toArray(), expected.toArray(), DELTA);
    }
    @Test void testDivide_9() {
        AbstractDoubleVector v = new DoubleVectorN(-1.0d, 2.0d, 3.0d,-2.0d);
        double c = -2.0d;

        AbstractDoubleVector expected = new DoubleVectorN(0.5f, -1.0d, -1.5f, 1.0d);
        Assertions.assertArrayEquals(v.divide(c).toArray(), expected.toArray(), DELTA);
    }

    //Test straight product
    @Test void testStraightProduct_1() {
        AbstractDoubleVector v1 = new DoubleVectorN(-1.0d, 2.0d);
        AbstractDoubleVector v2 = new DoubleVectorN(0.0d, 0.0d);

        AbstractDoubleVector expected = new DoubleVectorN(2, 0.0d);
        Assertions.assertArrayEquals(v1.straightProduct(v2).toArray(), expected.toArray(), DELTA);
    }
    @Test void testStraightProduct_2() {
        AbstractDoubleVector v1 = new DoubleVectorN(-1.0d, 2.0d, 3.0d);
        AbstractDoubleVector v2 = new DoubleVectorN(0.0d, 0.0d, 0.0d);

        AbstractDoubleVector expected = new DoubleVectorN(3,0.0d);
        Assertions.assertArrayEquals(v1.straightProduct(v2).toArray(), expected.toArray(), DELTA);
    }
    @Test void testStraightProduct_3() {
        AbstractDoubleVector v1 = new DoubleVectorN(-1.0d, 2.0d, 3.0d,-2.0d);
        AbstractDoubleVector v2 = new DoubleVectorN(0.0d, 0.0d, 0.0d, 0.0d);

        AbstractDoubleVector expected = new DoubleVectorN(4, 0.0d);
        Assertions.assertArrayEquals(v1.straightProduct(v2).toArray(), expected.toArray(), DELTA);
    }
    @Test void testStraightProduct_4() {
        AbstractDoubleVector v1 = new DoubleVectorN(-1.0d, 2.0d);
        AbstractDoubleVector v2 = new DoubleVectorN(1.0d, 1.0d);

        Assertions.assertArrayEquals(v1.clone().straightProduct(v2).toArray(), v1.toArray(), DELTA);
    }
    @Test void testStraightProduct_5() {
        AbstractDoubleVector v1 = new DoubleVectorN(-1.0d, 2.0d, 3.0d);
        AbstractDoubleVector v2 = new DoubleVectorN(1.0d, 1.0d, 1.0d);

        Assertions.assertArrayEquals(v1.clone().straightProduct(v2).toArray(), v1.toArray(), DELTA);
    }
    @Test void testStraightProduct_6() {
        AbstractDoubleVector v1 = new DoubleVectorN(-1.0d, 2.0d, 3.0d,-2.0d);
        AbstractDoubleVector v2 = new DoubleVectorN(1.0d, 1.0d, 1.0d, 1.0d);

        Assertions.assertArrayEquals(v1.clone().straightProduct(v2).toArray(), v1.toArray(), DELTA);
    }
    @Test void testStraightProduct_7() {
        AbstractDoubleVector v1 = new DoubleVectorN(-1.0d, 2.0d);
        AbstractDoubleVector v2 = new DoubleVectorN(-3.0d, 4.0d);

        AbstractDoubleVector expected = new DoubleVectorN(3.0d, 8.0d);
        Assertions.assertArrayEquals(v1.straightProduct(v2).toArray(), expected.toArray(), DELTA);
    }
    @Test void testStraightProduct_8() {
        AbstractDoubleVector v1 = new DoubleVectorN(-1.0d, 2.0d, 3.0d);
        AbstractDoubleVector v2 = new DoubleVectorN(-3.0d, 2.0d, 5.0d);

        AbstractDoubleVector expected = new DoubleVectorN(3.0d, 4.0d, 15.0d);
        Assertions.assertArrayEquals(v1.straightProduct(v2).toArray(), expected.toArray(), DELTA);
    }
    @Test void testStraightProduct_9() {
        AbstractDoubleVector v1 = new DoubleVectorN(-1.0d, 2.0d, 3.0d,-2.0d);
        AbstractDoubleVector v2 = new DoubleVectorN(4.0d, -3.0d, 2.0d, -6.0d);

        AbstractDoubleVector expected = new DoubleVectorN(-4.0d, -6.0d, 6.0d, 12.0d);
        Assertions.assertArrayEquals(v1.straightProduct(v2).toArray(), v1.toArray(), DELTA);
    }

    //Test normalize()
    @Test void testNormalize_1() {
        AbstractDoubleVector v = new DoubleVectorN(-3.0d, 3.0d);
        v.normalize();

        Assertions.assertEquals(1.0d, v.norm2());
    }
    @Test void testNormalize_2() {
        AbstractDoubleVector v = new DoubleVectorN(2.0d, -3.0d, 2.0d);
        v.normalize();

        Assertions.assertEquals(1.0d, v.norm2());
    }
    @Test void testNormalize_3() {
        AbstractDoubleVector v = new DoubleVectorN(2.0d, 3.0d, -1.0d, -3.0d);
        v.normalize();

        Assertions.assertEquals(1.0d, v.norm2());
    }
    @Test void testNormalize_4() {
        AbstractDoubleVector v = new DoubleVectorN(0.0d);
        v.normalize();

        Assertions.assertEquals(0.0d, v.norm2());
    }
    @Test void testNormalize_5() {
        AbstractDoubleVector v = new DoubleVectorN(0.0d);
        v.normalize();

        Assertions.assertEquals(0.0d, v.norm2());
    }
    @Test void testNormalize_6() {
        AbstractDoubleVector v = new DoubleVectorN(0.0d);
        v.normalize();

        Assertions.assertEquals(0.0d, v.norm2());
    }

    //Test clone()
    @Test void testClone_1() {
        AbstractDoubleVector v = new DoubleVectorN( Math.random(), Math.random());
        Assertions.assertArrayEquals(v.toArray(), v.clone().toArray(), DELTA);
    }
    @Test void testClone_2() {
        AbstractDoubleVector v = new DoubleVectorN( Math.random(), Math.random(),  Math.random());
        Assertions.assertArrayEquals(v.toArray(), v.clone().toArray(), DELTA);
    }
    @Test void testClone_3() {
        AbstractDoubleVector v = new DoubleVectorN(Math.random(), Math.random(), Math.random(), Math.random());
        Assertions.assertArrayEquals(v.toArray(), v.clone().toArray(), DELTA);
    }

    //Test sort()
    @Test void testSort_1() {
        DoubleVectorN vector = new DoubleVectorN(0,1,2,3,4);
        int[] idx = vector.sort();
        int[] expected = new int[]{0, 1, 2, 3, 4};

        assertArrayEquals(expected, idx);
        assertArrayEquals(new double[]{0,1,2,3,4}, vector.toArray());
    }
    @Test void testSort_2() {
        DoubleVectorN vector = new DoubleVectorN(4,3,2,1,0);
        int[] idx = vector.sort();
        int[] expected = new int[]{4,3,2,1,0};

        assertArrayEquals(expected, idx);
        assertArrayEquals(new double[]{0,1,2,3,4}, vector.toArray());
    }
    @Test void testSort_3() {
        DoubleVectorN vector = new DoubleVectorN(4,1,3,0,2);
        int[] idx = vector.sort();
        int[] expected = new int[]{3,1,4,2,0};

        assertArrayEquals(expected, idx);
        assertArrayEquals(new double[]{0,1,2,3,4}, vector.toArray());
    }
}