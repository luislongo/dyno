package com.alura.dyno.maths

import org.junit.Test

class VectorMathTest {

    static VectorF generateRandomVectorF(int size) {
        VectorF v = new VectorF(size);
        fillRandomValues(v);
    }
    private static void fillRandomValues(VectorF v) {
        for (int i = 0; i < v.nrOfCoords(); i++) {
            v[i] = Math.random();
        }
    }

    @Test void testAdd() {
        VectorF v1 = generateRandomVectorF(3);
        VectorF v2 = generateRandomVectorF(3);
        VectorF v3 = VectorMathTest.add(v1, v2);

        assert(isAdditionCorrectForEveryComponent(v1, v2, v3));
    }
    private boolean isAdditionCorrectForEveryComponent(VectorF v1, VectorF v2, VectorF v3) {
        for(int i = 0; i < v3.nrOfCoords(); i++)
        {
            if(v3.getX_(i) != v1.getX_(i) + v2.getX_(i))
            {
                return false;
            }
        }

        return true;
    }

    void testSubtract() {
    }

    void testMultiply() {
    }

    void testDivide() {
    }

    void testDot() {
    }

    void testTestMultiply() {
    }

    void testStraightProduct() {
    }
}
