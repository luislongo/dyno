package com.alura.dyno.maths;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

class VectorFUnitTest {
    private static int VEC_SIZE = 10000;
    private static float PRECISION = 0.05f;
    private VectorF generateAtRandom(int nrOfCoords) {
        VectorF v = new VectorF(nrOfCoords);
        fillwithRandom(v);

        return v;
    }
    private void fillwithRandom(VectorF v) {
        for(int i = 0; i < v.count(); i++)
        {
            v.setX_(i, (float)Math.random());
        }
    }

    @Test void testAllZeroVectorLength() {
        VectorF allZeros = new VectorF(VEC_SIZE);
        assertEquals(allZeros.length() ,0.0f);
    }
    @Test void testAllOnesVectorLength() {
        VectorF v1 = new VectorF(VEC_SIZE, 1.0f);
        float length = v1.length();

        assertEquals(Math.sqrt(VEC_SIZE), length);
    }
    @Test void testAllMinusOnesVectorLength() {
        VectorF v1 = new VectorF(VEC_SIZE, -1.0f);
        float length = v1.length();

        assertEquals(Math.sqrt(VEC_SIZE), length);
    }
    @Test void testAddedVectorLength() {
        VectorF v1 = generateAtRandom(VEC_SIZE);
        VectorF v2 = generateAtRandom(VEC_SIZE);
        VectorF v3 = new VectorF(VectorF.add(v1, v2));

        float addLength = 0;
        for(int i = 0; i < VEC_SIZE; i++)
        {
            addLength += (v1.getX_(i) + v2.getX_(i)) * (v1.getX_(i) + v2.getX_(i));
        }
        addLength = (float) Math.sqrt(addLength);

        assertEquals(addLength, v3.length());
    }
    @Test void testSubtractedVectorLength() {
        VectorF v1 = generateAtRandom(VEC_SIZE);
        VectorF v2 = generateAtRandom(VEC_SIZE);
        VectorF v3 = new VectorF(VectorF.subtract(v1, v2));

        float addLength = 0;
        for(int i = 0; i < VEC_SIZE; i++)
        {
            addLength += (v1.getX_(i) - v2.getX_(i)) * (v1.getX_(i) - v2.getX_(i));
        }
        addLength = (float) Math.sqrt(addLength);

        assertEquals(addLength, v3.length());
    }
    @Test void testMultipliedByConstantVectorLength() {
        float c = (float) Math.random();
        VectorF v1 = generateAtRandom(VEC_SIZE);
        VectorF v2 = new VectorF(VectorF.multiply(v1, c));

        assertEquals(v1.length() * c, v2.length(), PRECISION);
    }
    @Test void testDividedByConstantVectorLength() {
        float c = (float) Math.random();
        VectorF v1 = generateAtRandom(VEC_SIZE);
        VectorF v2 = new VectorF(VectorF.divide(v1, c));

        assertEquals(v1.length() / c, v2.length(), PRECISION);
    }

    @Test void testNormalizeRandomVector() {
        VectorF v1 = generateAtRandom(VEC_SIZE);
        VectorF v2 = new VectorF(VectorF.normalize(v1));

        System.out.println("NORMALIZE ERROR :: " + Math.abs(v2.length() - 1.0f));
        assert (Math.abs(v2.length() - 1.0f) < PRECISION);
    }
    @Test void testNormalizeAllOnesVector() {
        VectorF v1 = new VectorF(VEC_SIZE, 1.0f);
        VectorF v2 = new VectorF(VectorF.normalize(v1));
        VectorF result = new VectorF(VEC_SIZE, 1.0f / (float) Math.sqrt(VEC_SIZE));

        assertArrayEquals (v2.x_i, result.x_i);
    }
    @Test void testNormalizeAllMinusOnesVector() {
        VectorF v1 = new VectorF(VEC_SIZE, -1.0f);
        VectorF v2 = new VectorF(VectorF.normalize(v1));
        VectorF result = new VectorF(VEC_SIZE, -1.0f / (float) Math.sqrt(VEC_SIZE));

        assertArrayEquals (v2.x_i, result.x_i);
    }
    @Test void testNormalizeAllZerosVector() {
        VectorF allZeros = new VectorF(VEC_SIZE);
        VectorF normalized = new VectorF(VectorF.normalize(allZeros));

        assertArrayEquals(allZeros.x_i, normalized.x_i);
    }

    @Test void testCorrectNrOfCoordinatesVectorF() {
        VectorF v = new VectorF(new float[VEC_SIZE]);
        assertEquals(VEC_SIZE, v.count());
    }
    @Test void testCorrectNrOfCoordinatesVector2F() {
        Vector2F v = new Vector2F(new float[VEC_SIZE]);
        assertEquals(2, v.count());
    }
    @Test void testCorrectNrOfCoordinatesVector3F() {
        Vector3F v = new Vector3F(new float[VEC_SIZE]);
        assertEquals(3, v.count());
    }
    @Test void testCorrectNrOfCoordinatesVector4F() {
        Vector4F v = new Vector4F(new float[VEC_SIZE]);
        assertEquals(4, v.count());
    }

    @Test void testIsSameDimensionPass() {
        VectorF v1 = new VectorF(3);
        VectorF v2 = new VectorF(3);

        assertEquals(true, VectorF.isSameDimension(v1, v2));
    }
    @Test void testIsSameDimension() {
        VectorF v1 = new VectorF(3);
        VectorF v2 = new VectorF(2);

        assertEquals(false, VectorF.isSameDimension(v1, v2));
    }

    @Test void testComponentAdditionValues() {
        VectorF v1 = generateAtRandom(VEC_SIZE);
        VectorF v2 = generateAtRandom(VEC_SIZE);
        VectorF v3 = new VectorF(VectorF.add(v1,v2));

        assert (checkAdditionAtComponents(v1, v2, v3));
    }
    private boolean checkAdditionAtComponents(VectorF v1, VectorF v2, VectorF v3) {
        for (int i = 0; i < v1.length(); i++) {
            if (v3.getX_(i) != v1.getX_(i) + v2.getX_(i)) {
                return false;
            }
        }
        return true;
    }
    @Test void testComponentSubtractionValues() {
        VectorF v1 = generateAtRandom(VEC_SIZE);
        VectorF v2 = generateAtRandom(VEC_SIZE);
        VectorF v3 = new VectorF(VectorF.subtract(v1,v2));

        assert (checkSubtractionAtComponents(v1, v2, v3));
    }
    private boolean checkSubtractionAtComponents(VectorF v1, VectorF v2, VectorF v3) {
        for (int i = 0; i < v1.length(); i++) {
            if (v3.getX_(i) != v1.getX_(i) - v2.getX_(i)) {
                return false;
            }
        }
        return true;
    }
    @Test void testComponentMultiplyByConstant() {
        float c = (float) Math.random();
        VectorF v1 = generateAtRandom(VEC_SIZE);
        VectorF v2 = new VectorF(VectorF.multiply(v1, c));

        assert (checkConstantMultiplyAtComponents(v1, v2, c));
    }
    private boolean checkConstantMultiplyAtComponents(VectorF v1,VectorF  v2,float c) {
        for (int i = 0; i < v1.length(); i++) {
            if (v2.getX_(i) != v1.getX_(i) * c) {
                return false;
            }
        }
        return true;
    }
    @Test void testComponentDivideByConstant() {
        float c = (float) Math.random();
        VectorF v1 = generateAtRandom(VEC_SIZE);
        VectorF v2 = new VectorF(VectorF.divide(v1, c));

        assert (checkConstantDivideAtComponents(v1, v2, c));
    }
    private boolean checkConstantDivideAtComponents(VectorF v1,VectorF  v2,float c) {
        for (int i = 0; i < v1.length(); i++) {
            if (v2.getX_(i) != v1.getX_(i) / c) {
                return false;
            }
        }
        return true;
    }
    @Test void testDotProduct() {
        VectorF v1 = generateAtRandom(VEC_SIZE);
        VectorF v2 = generateAtRandom(VEC_SIZE);

        float dot = VectorF.dotProduct(v1, v2);
        float sum = calculateDotProduct(v1, v2);

        assertEquals(sum, dot);
    }
    private float calculateDotProduct(VectorF v1, VectorF  v2) {
        float sum = 0;
        for (int i = 0; i < v1.count(); i++) {
            sum += v1.getX_(i) * v2.getX_(i);
        }
        return sum;
    }
    @Test void testMultiplyByAllZeroMatrix() {
        MatrixF m_lhs = new MatrixF(VEC_SIZE, VEC_SIZE);
        VectorF v1 = generateAtRandom(VEC_SIZE);
        VectorF v2 = new VectorF(VectorF.multiply(m_lhs, v1));
        VectorF allZeros = new VectorF(VEC_SIZE);

        assertArrayEquals(v2.x_i, allZeros.x_i);
    }
    @Test void testStraightProduct() {
        VectorF v1 = generateAtRandom(3);
        VectorF v2 = generateAtRandom(3);
        VectorF v3 = new VectorF(VectorF.straightProduct(v1, v2));

        assert (checkStraightProductAtComponents(v1, v2, v3));
    }

    private boolean checkStraightProductAtComponents(VectorF v1,VectorF  v2, VectorF v3) {
        for (int i = 0; i < v1.length(); i++) {
            if (v3.getX_(i) != v1.getX_(i) * v2.getX_(i)) {
                return false;
            }
        }
        return true;
    }
}