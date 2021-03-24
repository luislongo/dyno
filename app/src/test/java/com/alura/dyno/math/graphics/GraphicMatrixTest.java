package com.alura.dyno.math.graphics;

import android.opengl.Matrix;

import com.alura.dyno.engine3d.render.GraphicObjectData;
import com.alura.dyno.math.linalg.Algebra;

import org.junit.jupiter.api.Test;

import java.security.AlgorithmParameterGenerator;
import java.util.ArrayList;

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
        GraphicMatrix m = randomMatrix();
        Vector3 v = randomVec3();

        float[] expected = m.toArray();
        expected[3] += v.x();
        expected[7] += v.y();
        expected[11] += v.z();

        m.translate(v);

        assertArrayEquals(expected, m.toArray());
    }
    @Test public void translateTest_5() {
        Vector3 v0 = randomVec3();
        Vector3 vDelta = randomVec3();
        GraphicMatrix m = Algebra.graphicMatrixFactory().translation(vDelta);

        Vector3 expected = v0.clone().plus(vDelta);
        Vector3 actual = v0.clone().multiply(m, 1.0f);

        assertArrayEquals(expected.toArray(), actual.toArray());
    }
    @Test public void translateTest_6() {
        Vector3 v0 = randomVec3();
        Vector3 vDelta = randomVec3();
        GraphicMatrix m = Algebra.graphicMatrixFactory().translation(vDelta);

        Vector3 expected = v0.clone();
        Vector3 actual = v0.clone().multiply(m, 0.0f);

        assertArrayEquals(expected.toArray(), actual.toArray());
    }

    @Test public void scaleTest_1() {
        GraphicMatrix m = randomMatrix();
        Vector3 v = randomVec3();

        float[] expected = m.toArray();
        expected[0] *= v.x();
        expected[5] *= v.y();
        expected[10] *= v.z();

        m.scale(v);

        assertArrayEquals(expected, m.toArray());
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

}
