package com.alura.dyno.maths.linalg;

import com.alura.dyno.maths.graphics.Vector2;
import com.alura.dyno.maths.graphics.Vector3;
import com.alura.dyno.maths.graphics.Vector4;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FloatVectorTest {

    private static final float DELTA = 0.00000001f;

    @Test
    void norm2() {
        Vector2 case1 = new Vector2(-1.0f, 0.0f);
        Vector3 case2 = new Vector3(-1.0f, 0.0f, 1.0f);
        Vector4 case3 = new Vector4(-1.0f, 0.0f, 1.0f, 2.0f);
        Vector2 case4 = new Vector2(-1.0f, -1.0f);
        Vector3 case5 = new Vector3(-1.0f, -1.0f, -1.0f);
        Vector4 case6 = new Vector4(-1.0f, -1.0f, -1.0f, -1.0f);
        Vector2 case7 = new Vector2( 0.0f, 0.0f);
        Vector3 case8 = new Vector3(0.0f, 0.0f, 0.0f);
        Vector4 case9 = new Vector4(0.0f, 0.0f, 0.0f, 0.0f);

        assertEquals(case1.norm2(), 1.0f, DELTA);
    }

    @Test
    void length() {
    }

    @Test
    void toArray() {
    }

    @Test
    void plus() {
    }

    @Test
    void minus() {
    }

    @Test
    void multiply() {
    }

    @Test
    void divide() {
    }

    @Test
    void straightProduct() {
    }

    @Test
    void testMultiply() {
    }

    @Test
    void normalize() {
    }

    @Test
    void testClone() {
    }

    @Test
    void testToString() {
    }
}