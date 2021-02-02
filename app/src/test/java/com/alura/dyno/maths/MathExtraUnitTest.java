package com.alura.dyno.maths;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class MathExtraUnitTest {
    @Test void testClamp() {
        float value = 2.0f;

        assertEquals(2.0f, MathExtra.clamp(value, 1.0f, 3.0f));
        assertEquals(2.5f, MathExtra.clamp(value, 2.5f, 3.0f));
        assertEquals(1.5f, MathExtra.clamp(value, 1.0f, 1.5f));
    }
    @Test void testMap() {
        assertEquals(10, MathExtra.map(0, 0, 1, 10, 100));
        assertEquals(100, MathExtra.map(1, 0, 1, 10, 100));
        assertEquals(10, MathExtra.map(1, 1, 2, 10, 100));
        assertEquals(100, MathExtra.map(2, 1, 2, 10, 100));
        assertEquals(50, MathExtra.map(1, 0, 2, 0, 100));
        assertEquals(25, MathExtra.map(0.5f, 0, 2, 0, 100));
        assertEquals(75, MathExtra.map(1.5f, 0, 2, 0, 100));


    }
}