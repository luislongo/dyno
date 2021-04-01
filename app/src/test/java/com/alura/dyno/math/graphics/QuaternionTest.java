package com.alura.dyno.math.graphics;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class QuaternionTest {

    private final float PRECISION = 0.00001f;

    @Test void testAxisAndAngle_1() {
        Vector3 axis = Vector3.right();
        float angle = 90.0f;

        Quaternion q = Quaternion.fromAxisAndAngle(axis, angle);
        float[] expected = new float[]{0.70710677f, 0.70710677f, 0, 0};

        assertArrayEquals(expected, q.toArray(), PRECISION);
    }
    @Test void testAxisAndAngle_2() {
        Vector3 axis = Vector3.up();
        float angle = 90.0f;

        Quaternion q = Quaternion.fromAxisAndAngle(axis, angle);
        float[] expected = new float[]{0.70710677f, 0, 0.70710677f, 0};

        assertArrayEquals(expected, q.toArray(), PRECISION);
    }
    @Test void testAxisAndAngle_3() {
        Vector3 axis = Vector3.backward();
        float angle = 90.0f;

        Quaternion q = Quaternion.fromAxisAndAngle(axis, angle);
        float[] expected = new float[]{0.70710677f, 0, 0, 0.70710677f};

        assertArrayEquals(expected, q.toArray(), PRECISION);
    }
    @Test void testAxisAndAngle_4() {
        Vector3 axis = Vector3.right();
        float angle = 180.0f;

        Quaternion q = Quaternion.fromAxisAndAngle(axis, angle);
        float[] expected = new float[]{0, 1.0f, 0, 0};

        assertArrayEquals(expected, q.toArray(), PRECISION);
    }
    @Test void testAxisAndAngle_5() {
        Vector3 axis = Vector3.up();
        float angle = 180.0f;

        Quaternion q = Quaternion.fromAxisAndAngle(axis, angle);
        float[] expected = new float[]{0, 0, 1.0f, 0};

        assertArrayEquals(expected, q.toArray(), PRECISION);
    }
    @Test void testAxisAndAngle_6() {
        Vector3 axis = Vector3.backward();
        float angle = 180.0f;

        Quaternion q = Quaternion.fromAxisAndAngle(axis, angle);
        float[] expected = new float[]{0, 0, 0, 1.0f};

        assertArrayEquals(expected, q.toArray(), PRECISION);
    }
    @Test void testAxisAndAngle_7() {
        Vector3 axis = Vector3.right();
        float angle = -90.0f;

        Quaternion q = Quaternion.fromAxisAndAngle(axis, angle);
        float[] expected = new float[]{0.70710677f, -0.70710677f, 0, 0};

        assertArrayEquals(expected, q.toArray(), PRECISION);
    }
    @Test void testAxisAndAngle_8() {
        Vector3 axis = Vector3.up();
        float angle = -90.0f;

        Quaternion q = Quaternion.fromAxisAndAngle(axis, angle);
        float[] expected = new float[]{0.70710677f, 0, -0.70710677f, 0};

        assertArrayEquals(expected, q.toArray(), PRECISION);
    }
    @Test void testAxisAndAngle_9() {
        Vector3 axis = Vector3.backward();
        float angle = -90.0f;

        Quaternion q = Quaternion.fromAxisAndAngle(axis, angle);
        float[] expected = new float[]{0.70710677f, 0, 0, -0.70710677f};

        assertArrayEquals(expected, q.toArray(), PRECISION);
    }
    @Test void testAxisAndAngle_10() {
        Vector3 axis = new Vector3(1.0f, 1.0f, 1.0f);
        float angle = 45.0f;

        Quaternion q = Quaternion.fromAxisAndAngle(axis, angle);
        float[] expected = new float[]{0.9238795f, 0.2209424f, 0.2209424f, 0.2209424f};

        assertArrayEquals(expected, q.toArray(), PRECISION);
    }
    @Test void testAxisAndAngle_11() {
        Vector3 axis = new Vector3(1.0f, 0.5f, 0.5f);
        float angle = 45.0f;

        Quaternion q = Quaternion.fromAxisAndAngle(axis, angle);
        float[] expected = new float[]{0.9238795f, 0.3124597f, 0.1562299f, 0.1562299f};

        assertArrayEquals(expected, q.toArray(), PRECISION);
    }
    @Test void testAxisAndAngle_12() {
        Vector3 axis = new Vector3(0.5f, 1.0f, 0.5f);
        float angle = 45.0f;

        Quaternion q = Quaternion.fromAxisAndAngle(axis, angle);
        float[] expected = new float[]{0.9238795f, 0.1562299f, 0.3124597f, 0.1562299f};

        assertArrayEquals(expected, q.toArray(), PRECISION);
    }
    @Test void testAxisAndAngle_13() {
        Vector3 axis = new Vector3(0.5f, 0.5f, 1.0f);
        float angle = 45.0f;

        Quaternion q = Quaternion.fromAxisAndAngle(axis, angle);
        float[] expected = new float[]{0.9238795f, 0.1562299f, 0.1562299f, 0.3124597f};

        assertArrayEquals(expected, q.toArray(), PRECISION);
    }

    @Test void testpreMultiply_1() {
        Quaternion q = Quaternion.fromAxisAndAngle(Vector3.right(), 90.0f);
        Quaternion actual = Quaternion.fromAxisAndAngle(Vector3.right(), 0.0f);

        actual.preMultiply(q);
        assertArrayEquals(new float[] {0.70710677f, 0.70710677f, 0, 0}, actual.toArray(), PRECISION);

        actual.preMultiply(q);
        assertArrayEquals(new float[] {0.0f, 1.0f, 0.0f, 0.0f}, actual.toArray(), PRECISION);

        actual.preMultiply(q);
        assertArrayEquals(new float[] {-0.70710677f, 0.70710677f, 0, 0}, actual.toArray(), PRECISION);

        actual.preMultiply(q);
        assertArrayEquals(new float[] {-1.0f, 0, 0, 0}, actual.toArray(), PRECISION);
    }
    @Test void testpreMultiply_2() {
        Quaternion q = Quaternion.fromAxisAndAngle(Vector3.up(), 90.0f);
        Quaternion actual = Quaternion.fromAxisAndAngle(Vector3.up(), 0.0f);

        actual.preMultiply(q);
        assertArrayEquals(new float[] {0.70710677f, 0, 0.70710677f, 0}, actual.toArray(), PRECISION);

        actual.preMultiply(q);
        assertArrayEquals(new float[] {0.0f, 0.0f, 1.0f, 0.0f}, actual.toArray(), PRECISION);

        actual.preMultiply(q);
        assertArrayEquals(new float[] {-0.70710677f, 0, 0.70710677f, 0}, actual.toArray(), PRECISION);

        actual.preMultiply(q);
        assertArrayEquals(new float[] {-1.0f, 0, 0, 0}, actual.toArray(), PRECISION);
    }
    @Test void testpreMultiply_3() {
        Quaternion q = Quaternion.fromAxisAndAngle(Vector3.backward(), 90.0f);
        Quaternion actual = Quaternion.fromAxisAndAngle(Vector3.backward(), 0.0f);

        actual.preMultiply(q);
        assertArrayEquals(new float[] {0.70710677f, 0, 0, 0.70710677f}, actual.toArray(), PRECISION);

        actual.preMultiply(q);
        assertArrayEquals(new float[] {0.0f, 0.0f, 0.0f, 1.0f}, actual.toArray(), PRECISION);

        actual.preMultiply(q);
        assertArrayEquals(new float[] {-0.70710677f, 0, 0, 0.70710677f}, actual.toArray(), PRECISION);

        actual.preMultiply(q);
        assertArrayEquals(new float[] {-1.0f, 0, 0, 0}, actual.toArray(), PRECISION);
    }
    @Test void testpreMultiply_4() {
        Quaternion q = Quaternion.fromAxisAndAngle(new Vector3(1.0f, 1.0f, 1.0f), 90.0f);
        Quaternion actual = Quaternion.fromAxisAndAngle(Vector3.backward(), 0.0f);

        actual.preMultiply(q);
        assertArrayEquals(new float[] {0.7071068f, 0.4082483f, 0.4082483f, 0.4082483f}, actual.toArray(), PRECISION);

        actual.preMultiply(q);
        assertArrayEquals(new float[] {0.0f, 0.5773503f, 0.5773503f, 0.5773503f}, actual.toArray(), PRECISION);

        actual.preMultiply(q);
        assertArrayEquals(new float[] {-0.7071068f, 0.4082483f, 0.4082483f, 0.4082483f}, actual.toArray(), PRECISION);

        actual.preMultiply(q);
        assertArrayEquals(new float[] {-1.0f, 0, 0, 0}, actual.toArray(), PRECISION);
    }
    @Test void testpreMultiply_5() {
        Quaternion q = Quaternion.fromAxisAndAngle(Vector3.right(), 90.0f);
        Quaternion invQ = Quaternion.fromAxisAndAngle(Vector3.right(), -90.0f);

        Quaternion actual = q.clone().preMultiply(invQ);

        assertArrayEquals(new float[]{1.0f, 0.0f, 0.0f, 0.0f}, actual.toArray(), PRECISION);
    }
    @Test void testpreMultiply_6() {
        Quaternion q = Quaternion.fromAxisAndAngle(Vector3.up(), 90.0f);
        Quaternion invQ = Quaternion.fromAxisAndAngle(Vector3.up(), -90.0f);

        Quaternion actual = q.clone().preMultiply(invQ);

        assertArrayEquals(new float[]{1.0f, 0.0f, 0.0f, 0.0f}, actual.toArray(), PRECISION);
    }
    @Test void testpreMultiply_7() {
        Quaternion q = Quaternion.fromAxisAndAngle(Vector3.backward(), 90.0f);
        Quaternion invQ = Quaternion.fromAxisAndAngle(Vector3.backward(), -90.0f);

        Quaternion actual = q.clone().preMultiply(invQ);

        assertArrayEquals(new float[]{1.0f, 0.0f, 0.0f, 0.0f}, actual.toArray(), PRECISION);
    }

    @Test void testfromEulerAngles_1() {
        Vector3 euler = new Vector3(90.0f, 0.0f, 0.0f);
        Quaternion actual = Quaternion.fromEulerAngles(euler);

        float[] expected = new float[]{0.7071068f, 0.7071068f, 0.0f, 0.0f};
        assertArrayEquals(expected, actual.toArray(), PRECISION);
    }
    @Test void testfromEulerAngles_2() {
        Vector3 euler = new Vector3(0.0f, 90.0f, 0.0f);
        Quaternion actual = Quaternion.fromEulerAngles(euler);

        float[] expected = new float[]{0.7071068f, 0.0f, 0.7071068f, 0.0f};
        assertArrayEquals(expected, actual.toArray(), PRECISION);
    }
    @Test void testfromEulerAngles_3() {
        Vector3 euler = new Vector3(0.0f, 0.0f, 90.0f);
        Quaternion actual = Quaternion.fromEulerAngles(euler);

        float[] expected = new float[]{0.7071068f, 0.0f, 0.0f, 0.7071068f};
        assertArrayEquals(expected, actual.toArray(), PRECISION);
    }
    @Test void testfromEulerAngles_4() {
        Vector3 euler = new Vector3(90.0f, 90.0f, 0.0f);
        Quaternion actual = Quaternion.fromEulerAngles(euler);

        float[] expected = new float[]{0.5f, 0.5f, 0.5f, -0.5f};
        assertArrayEquals(expected, actual.toArray(), PRECISION);
    }
    @Test void testfromEulerAngles_5() {
        Vector3 euler = new Vector3(90.0f, 0.0f, 90.0f);
        Quaternion actual = Quaternion.fromEulerAngles(euler);

        float[] expected = new float[]{0.5f, 0.5f, 0.5f, 0.5f};
        assertArrayEquals(expected, actual.toArray(), PRECISION);
    }
    @Test void testfromEulerAngles_6() {
        Vector3 euler = new Vector3(0.0f, 90.0f, 90.0f);
        Quaternion actual = Quaternion.fromEulerAngles(euler);

        float[] expected = new float[]{0.5f, -0.5f, 0.5f, 0.5f};
        assertArrayEquals(expected, actual.toArray(), PRECISION);
    }
    @Test void testfromEulerAngles_7() {
        Vector3 euler = new Vector3(45.0f, 90.0f, 30.0f);
        Quaternion actual = Quaternion.fromEulerAngles(euler);

        float[] expected = new float[]{0.7010574f, 0.092296f, 0.7010574f, -0.092296f};
        assertArrayEquals(expected, actual.toArray(), PRECISION);
    }
    @Test void testfromEulerAngles_8() {
        Vector3 euler = new Vector3(45.0f, 30.0f, 90.0f);
        Quaternion actual = Quaternion.fromEulerAngles(euler);

        float[] expected = new float[]{0.7010574f, 0.092296f, 0.4304593f, 0.5609855f};
        assertArrayEquals(expected, actual.toArray(), PRECISION);
    }
    @Test void testfromEulerAngles_9() {
        Vector3 euler = new Vector3(30.0f, 45.0f, 90.0f);
        Quaternion actual = Quaternion.fromEulerAngles(euler);

        float[] expected = new float[]{0.7010574f, -0.092296f, 0.4304593f, 0.5609855f};
        assertArrayEquals(expected, actual.toArray(), PRECISION);
    }
    @Test void testfromEulerAngles_10() {
        Vector3 euler = new Vector3(90.0f, 45.0f, 30.0f);
        Quaternion actual = Quaternion.fromEulerAngles(euler);

        float[] expected = new float[]{0.7010574f, 0.5609855f, 0.4304593f, -0.092296f};
        assertArrayEquals(expected, actual.toArray(), PRECISION);
    }
    @Test void testfromEulerAngles_11() {
        Vector3 euler = new Vector3(90.0f, 30.0f, 45.0f);
        Quaternion actual = Quaternion.fromEulerAngles(euler);

        float[] expected = new float[]{0.7010574f, 0.5609855f, 0.4304593f, 0.092296f};
        assertArrayEquals(expected, actual.toArray(), PRECISION);
    }
    @Test void testfromEulerAngles_12() {
        Vector3 euler = new Vector3(30.0f, 90.0f, 45.0f);
        Quaternion actual = Quaternion.fromEulerAngles(euler);

        float[] expected = new float[]{0.7010574f, -0.092296f, 0.7010574f, 0.092296f};
        assertArrayEquals(expected, actual.toArray(), PRECISION);
    }

    @Test void testFromToRotation_1() {
        Vector3 from = new Vector3(1.0f, 0.0f, 0.0f);
        Vector3 to = new Vector3(0.0f, 1.0f,0.0f);

        Quaternion actual = Quaternion.fromToRotation(from, to);
        float[] expected = new float[]{0.7071068f, 0, 0, 0.7071068f};

        assertArrayEquals(expected, actual.toArray());
    }
    @Test void testFromToRotation_2() {
        Vector3 from = new Vector3(1.0f, 0.0f, 0.0f);
        Vector3 to = new Vector3(0.0f, 0.0f,1.0f);

        Quaternion actual = Quaternion.fromToRotation(from, to);
        float[] expected = new float[]{0.7071068f, 0, -0.7071068f, 0};

        assertArrayEquals(expected, actual.toArray());
    }
    @Test void testFromToRotation_3() {
        Vector3 from = new Vector3(0.0f, 1.0f, 0.0f);
        Vector3 to = new Vector3(0.0f, 0.0f,1.0f);

        Quaternion actual = Quaternion.fromToRotation(from, to);
        float[] expected = new float[]{0.7071068f, 0.7071068f, 0, 0};

        assertArrayEquals(expected, actual.toArray());
    }

    @Test void testgetEulerAngles_1() {
        Quaternion q = Quaternion.fromAxisAndAngle(Vector3.right(), 90);
        Vector3 actual = q.getEulerAngles();

        float[] expected = new float[]{90.0f, 0.0f, 0.0f};
        assertArrayEquals(expected, actual.toArray(), PRECISION);
    }
    @Test void testgetEulerAngles_2() {
        Quaternion q = Quaternion.fromAxisAndAngle(Vector3.up(), 90);
        Vector3 actual = q.getEulerAngles();

        float[] expected = new float[]{0.0f, 90.0f, 0.0f};
        assertArrayEquals(expected, actual.toArray(), PRECISION);
    }
    @Test void testgetEulerAngles_3() {
        Quaternion q = Quaternion.fromAxisAndAngle(Vector3.backward(), 90);
        Vector3 actual = q.getEulerAngles();

        float[] expected = new float[]{0.0f, 0.0f, 90.0f};
        assertArrayEquals(expected, actual.toArray(), PRECISION);
    }
    @Test void testgetEulerAngles_4() {
        Quaternion q = Quaternion.fromAxisAndAngle(new Vector3(1,1,1), 90);
        Vector3 actual = q.getEulerAngles();

        float[] expected = new float[]{36.2060217f, 65.599996f, 36.2060217f};
        assertArrayEquals(expected, actual.toArray(), PRECISION);
    }

}