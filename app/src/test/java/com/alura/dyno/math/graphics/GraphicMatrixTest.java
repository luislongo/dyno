package com.alura.dyno.math.graphics;

import com.alura.dyno.math.linalg.Algebra;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertLinesMatch;

public class GraphicMatrixTest {
    private final float DELTA = 0.00001f;
    private GraphicMatrix randomMatrix() {
        float[] data = new float[16];

        for(int i = 0; i < 16; i++){
            data[i] = (float) Math.random();
        }

        return new GraphicMatrix(data);
    }
    private Vector3 randomVec3() {
        float[] data = new float[3];

        for(int i = 0; i < 3; i++){
            data[i] = (float) Math.random();
        }

        return new Vector3(data);
    }

    @Test public void translateTest_1() {
        GraphicMatrix m = Algebra.graphicMatrixFactory().identity();
        m.translate(new Vector3(1.0f,0.0f,0.0f));

        float[] expected = new float[] {
                1.0f, 0.0f, 0.0f, 1.0f,
                0.0f, 1.0f, 0.0f, 0.0f,
                0.0f, 0.0f, 1.0f, 0.0f,
                0.0f, 0.0f, 0.0f, 1.0f
        };

        assertArrayEquals(expected, m.toArray(), DELTA);
    }
    @Test public void translateTest_2() {
        GraphicMatrix m = Algebra.graphicMatrixFactory().identity();
        m.translate(new Vector3(0.0f,1.0f,0.0f));

        float[] expected = new float[] {
                1.0f, 0.0f, 0.0f, 0.0f,
                0.0f, 1.0f, 0.0f, 1.0f,
                0.0f, 0.0f, 1.0f, 0.0f,
                0.0f, 0.0f, 0.0f, 1.0f
        };

        assertArrayEquals(expected, m.toArray(), DELTA);
    }
    @Test public void translateTest_3() {
        GraphicMatrix m = Algebra.graphicMatrixFactory().identity();
        m.translate(new Vector3(0.0f,0.0f,1.0f));

        float[] expected = new float[] {
                1.0f, 0.0f, 0.0f, 0.0f,
                0.0f, 1.0f, 0.0f, 0.0f,
                0.0f, 0.0f, 1.0f, 1.0f,
                0.0f, 0.0f, 0.0f, 1.0f
        };

        assertArrayEquals(expected, m.toArray(), DELTA);
    }
    @Test public void translateTest_4() {
        Vector3 v0 = randomVec3();
        Vector3 vDelta = randomVec3();
        GraphicMatrix m = Algebra.graphicMatrixFactory().translation(vDelta);

        Vector3 expected = v0.clone().plus(vDelta);
        Vector3 actual = v0.clone().multiply(m, 1.0f);

        assertArrayEquals(expected.toArray(), actual.toArray());
    }
    @Test public void translateTest_5() {
        Vector3 v0 = randomVec3();
        Vector3 vDelta = randomVec3();
        GraphicMatrix m = Algebra.graphicMatrixFactory().translation(vDelta);

        Vector3 expected = v0.clone();
        Vector3 actual = v0.clone().multiply(m, 0.0f);

        assertArrayEquals(expected.toArray(), actual.toArray());
    }

    @Test public void rotateTest_1() {
        GraphicMatrix rot = Algebra.graphicMatrixFactory().identity()
                .rotate(new Vector3(1.0f, 0.0f, 0.0f), 90.0f);

        Vector3 v1 = new Vector3(1.0f, 0.0f, 0.0f);
        float[] v1expected = new float[]{1.0f, 0.0f, 0.0f};
        v1.multiply(rot, 1.0f);
        assertArrayEquals(v1expected, v1.toArray(), DELTA);

        Vector3 v2 = new Vector3(0.0f, 1.0f, 0.0f);
        float[] v2expected = new float[]{0.0f, 0.0f, 1.0f};
        v2.multiply(rot, 1.0f);
        assertArrayEquals(v2expected, v2.toArray(), DELTA);

        Vector3 v3 = new Vector3(0.0f, 0.0f, 1.0f);
        float[] v3expected = new float[]{0.0f, -1.0f, 0.0f};
        v3.multiply(rot, 1.0f);
        assertArrayEquals(v3expected, v3.toArray(), DELTA);
    }
    @Test public void rotateTest_2() {
        GraphicMatrix rot = Algebra.graphicMatrixFactory().identity()
                .rotate(new Vector3(0.0f, 1.0f, 0.0f), 90.0f);

        Vector3 v1 = new Vector3(1.0f, 0.0f, 0.0f);
        float[] v1expected = new float[]{0.0f, 0.0f, -1.0f};
        v1.multiply(rot, 1.0f);
        assertArrayEquals(v1expected, v1.toArray(), DELTA);

        Vector3 v2 = new Vector3(0.0f, 1.0f, 0.0f);
        float[] v2expected = new float[]{0.0f, 1.0f, 0.0f};
        v2.multiply(rot, 1.0f);
        assertArrayEquals(v2expected, v2.toArray(), DELTA);

        Vector3 v3 = new Vector3(0.0f, 0.0f, 1.0f);
        float[] v3expected = new float[]{0.0f, -1.0f, 0.0f};
        v3.multiply(rot, 1.0f);
        assertArrayEquals(v1expected, v1.toArray(), DELTA);
    }
    @Test public void rotateTest_3() {
        GraphicMatrix rot = Algebra.graphicMatrixFactory().identity()
                .rotate(new Vector3(0.0f, 0.0f, 1.0f), 90.0f);

        Vector3 v1 = new Vector3(1.0f, 0.0f, 0.0f);
        float[] v1expected = new float[]{0.0f, 1.0f, 0.0f};
        v1.multiply(rot, 1.0f);
        assertArrayEquals(v1expected, v1.toArray(), DELTA);

        Vector3 v2 = new Vector3(0.0f, 1.0f, 0.0f);
        float[] v2expected = new float[]{-1.0f, 0.0f, 0.0f};
        v2.multiply(rot, 1.0f);
        assertArrayEquals(v2expected, v2.toArray(), DELTA);

        Vector3 v3 = new Vector3(0.0f, 0.0f, 1.0f);
        float[] v3expected = new float[]{0.0f, 0.0f, 1.0f};
        v3.multiply(rot, 1.0f);
        assertArrayEquals(v3expected, v3.toArray(), DELTA);
    }
    @Test public void rotateTest_4() {
        GraphicMatrix rot = Algebra.graphicMatrixFactory().identity()
                .rotate(new Vector3(1.0f, 0.0f, 0.0f), 180.0f);

        Vector3 v1 = new Vector3(1.0f, 0.0f, 0.0f);
        float[] v1expected = new float[]{1.0f, 0.0f, 0.0f};
        v1.multiply(rot, 1.0f);
        assertArrayEquals(v1expected, v1.toArray(), DELTA);

        Vector3 v2 = new Vector3(0.0f, 1.0f, 0.0f);
        float[] v2expected = new float[]{0.0f, -1.0f, 0.0f};
        v2.multiply(rot, 1.0f);
        assertArrayEquals(v2expected, v2.toArray(), DELTA);

        Vector3 v3 = new Vector3(0.0f, 0.0f, 1.0f);
        float[] v3expected = new float[]{0.0f, 0.0f, -1.0f};
        v3.multiply(rot, 1.0f);
        assertArrayEquals(v3expected, v3.toArray(), DELTA);
    }
    @Test public void rotateTest_5() {
        GraphicMatrix rot = Algebra.graphicMatrixFactory().identity()
                .rotate(new Vector3(0.0f, 1.0f, 0.0f), 180.0f);

        Vector3 v1 = new Vector3(1.0f, 0.0f, 0.0f);
        float[] v1expected = new float[]{-1.0f, 0.0f, 0.0f};
        v1.multiply(rot, 1.0f);
        assertArrayEquals(v1expected, v1.toArray(), DELTA);

        Vector3 v2 = new Vector3(0.0f, 1.0f, 0.0f);
        float[] v2expected = new float[]{0.0f, 1.0f, 0.0f};
        v2.multiply(rot, 1.0f);
        assertArrayEquals(v2expected, v2.toArray(), DELTA);

        Vector3 v3 = new Vector3(0.0f, 0.0f, 1.0f);
        float[] v3expected = new float[]{0.0f, 0.0f, -1.0f};
        v3.multiply(rot, 1.0f);
        assertArrayEquals(v1expected, v1.toArray(), DELTA);
    }
    @Test public void rotateTest_6() {
        GraphicMatrix rot = Algebra.graphicMatrixFactory().identity()
                .rotate(new Vector3(0.0f, 0.0f, 1.0f), 180.0f);

        Vector3 v1 = new Vector3(1.0f, 0.0f, 0.0f);
        float[] v1expected = new float[]{-1.0f, 0.0f, 0.0f};
        v1.multiply(rot, 1.0f);
        assertArrayEquals(v1expected, v1.toArray(), DELTA);

        Vector3 v2 = new Vector3(0.0f, 1.0f, 0.0f);
        float[] v2expected = new float[]{0.0f, -1.0f, 0.0f};
        v2.multiply(rot, 1.0f);
        assertArrayEquals(v2expected, v2.toArray(), DELTA);

        Vector3 v3 = new Vector3(0.0f, 0.0f, 1.0f);
        float[] v3expected = new float[]{0.0f, 0.0f, 1.0f};
        v3.multiply(rot, 1.0f);
        assertArrayEquals(v3expected, v3.toArray(), DELTA);
    }

    @Test public void rotateTest_7() {
        Quaternion q = Quaternion.fromAxisAndAngle(new Vector3(1.0f, 0.0f, 0.0f), 90.f);
        GraphicMatrix rotation = Algebra.graphicMatrixFactory().rotate(q);
        Vector3 v = new Vector3(0.0f,1.0f, 0.0f);

        v.multiply(rotation, 1.0f);
        assertArrayEquals(new float[]{0.0f, 0.0f, 1.0f}, v.toArray(), DELTA);

        v.multiply(rotation, 1.0f);
        assertArrayEquals(new float[]{0.0f, -1.0f, 0.0f}, v.toArray(), DELTA);

        v.multiply(rotation, 1.0f);
        assertArrayEquals(new float[]{0.0f, 0.0f, -1.0f}, v.toArray(), DELTA);

        v.multiply(rotation, 1.0f);
        assertArrayEquals(new float[]{0.0f, 1.0f, 0.0f}, v.toArray(), DELTA);
    }
    @Test public void rotateTest_8() {
        Quaternion q = Quaternion.fromAxisAndAngle(new Vector3(0.0f, 1.0f, 0.0f), 90.f);
        GraphicMatrix rotation = Algebra.graphicMatrixFactory().rotate(q);
        Vector3 v = new Vector3(1.0f,0.0f, 0.0f);

        v.multiply(rotation, 1.0f);
        assertArrayEquals(new float[]{0.0f, 0.0f, -1.0f}, v.toArray(), DELTA);

        v.multiply(rotation, 1.0f);
        assertArrayEquals(new float[]{-1.0f, 0.0f, 0.0f}, v.toArray(), DELTA);

        v.multiply(rotation, 1.0f);
        assertArrayEquals(new float[]{0.0f, 0.0f, 1.0f}, v.toArray(), DELTA);

        v.multiply(rotation, 1.0f);
        assertArrayEquals(new float[]{1.0f, 0.0f, 0.0f}, v.toArray(), DELTA);
    }
    @Test public void rotateTest_9() {
        Quaternion q = Quaternion.fromAxisAndAngle(new Vector3(1.0f, 0.0f, 0.0f), 90.f);
        GraphicMatrix rotation = Algebra.graphicMatrixFactory().rotate(q);

        Vector3 v = new Vector3(0.0f,1.0f, 0.0f);
        v.multiply(rotation, 1.0f);
        assertArrayEquals(new float[]{0.0f, 0.0f, 1.0f}, v.toArray(), DELTA);

        v.multiply(rotation, 1.0f);
        assertArrayEquals(new float[]{0.0f, -1.0f, 0.0f}, v.toArray(), DELTA);

        v.multiply(rotation, 1.0f);
        assertArrayEquals(new float[]{0.0f, 0.0f, -1.0f}, v.toArray(), DELTA);

        v.multiply(rotation, 1.0f);
        assertArrayEquals(new float[]{0.0f, 1.0f, 0.0f}, v.toArray(), DELTA);
    }
    @Test public void rotateTest_10() {
        Quaternion q = Quaternion.fromAxisAndAngle(new Vector3(0.0f, 0.0f, 1.0f), 90.f);
        GraphicMatrix rotation = Algebra.graphicMatrixFactory().rotate(q);

        Vector3 v = new Vector3(1.0f,0.0f, 0.0f);
        v.multiply(rotation, 1.0f);
        assertArrayEquals(new float[]{0.0f, 1.0f, 0.0f}, v.toArray(), DELTA);

        v.multiply(rotation, 1.0f);
        assertArrayEquals(new float[]{-1.0f, 0.0f, 0.0f}, v.toArray(), DELTA);

        v.multiply(rotation, 1.0f);
        assertArrayEquals(new float[]{0.0f, -1.0f, 0.0f}, v.toArray(), DELTA);

        v.multiply(rotation, 1.0f);
        assertArrayEquals(new float[]{1.0f, 0.0f, 0.0f}, v.toArray(), DELTA);
    }
    @Test public void rotateTest_11() {
        Quaternion q = Quaternion.fromAxisAndAngle(randomVec3(), 360.f);
        GraphicMatrix rotation = Algebra.graphicMatrixFactory().rotate(q);
        Vector3 v = randomVec3();

        Vector3 expected = v.clone();

        assertArrayEquals(expected.toArray(), v.multiply(rotation, 1.0f).toArray());
    }
    @Test public void rotateTest_12() {
        Quaternion q = Quaternion.fromAxisAndAngle(randomVec3(), 360.f);
        GraphicMatrix rotation = Algebra.graphicMatrixFactory().rotate(q);
        Vector3 v = randomVec3();

        Vector3 expected = v.clone();

        assertArrayEquals(expected.toArray(), v.multiply(rotation, 1.0f).toArray());
    }
    @Test public void rotateTest_13() {
        Quaternion q = Quaternion.fromAxisAndAngle(new Vector3(1.0f, 1.0f, 1.0f), 90.f);
        GraphicMatrix rotation = Algebra.graphicMatrixFactory().rotate(q);
        Vector3 v = new Vector3(1.0f,0.0f, 0.0f);

        v.multiply(rotation, 1.0f);
        assertArrayEquals(new float[]{0.333333f, 0.910684f, -0.244017f}, v.toArray(), DELTA);

        v.multiply(rotation, 1.0f);
        assertArrayEquals(new float[]{-0.333333f, 0.666667f, 0.666667f}, v.toArray(), DELTA);

        v.multiply(rotation, 1.0f);
        assertArrayEquals(new float[]{0.333333f, -0.244017f, 0.910684f}, v.toArray(), DELTA);

        v.multiply(rotation, 1.0f);
        assertArrayEquals(new float[]{1.0f,0.0f,0.0f}, v.toArray(), DELTA);
    }

}
