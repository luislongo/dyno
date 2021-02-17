package com.alura.dyno.math.graphics;

import android.graphics.RectF;

public class RawGraphicMatrixFactory implements IGraphicMatrixFactory {
    @Override public GraphicMatrix frustum(RectF rect, float near, float far) {
        return null;
    }
    @Override public GraphicMatrix orthogonal(RectF rect, float near, float far) {
        return null;
    }
    @Override public GraphicMatrix perspective(float fov, float aspect, float near, float far) {
        return null;
    }
    @Override public GraphicMatrix lookAt(Vector3 eye, Vector3 center, Vector3 up) {
        return null;
    }
    @Override public GraphicMatrix identity() {
        float[] data = new float[]{1.0f, 0.0f, 0.0f, 0.0f,
                                   0.0f, 1.0f, 0.0f, 0.0f,
                                   0.0f, 0.0f, 1.0f, 0.0f,
                                   0.0f, 0.0f, 0.0f, 1.0f};
        return new GraphicMatrix(data);
    }
    @Override public GraphicMatrix translation(Vector3 delta) {
        return null;
    }
    @Override public GraphicMatrix rotation(Vector3 delta) {
        return null;
    }
    @Override public GraphicMatrix scale(Vector3 delta) {
        return null;
    }
    @Override public GraphicMatrix shear(Vector3 delta) {
        return null;
    }
}
