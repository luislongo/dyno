package com.alura.dyno.math.graphics;

import android.graphics.RectF;
import android.opengl.Matrix;

public class AndroidGraphicMatrixFactory implements IGraphicMatrixFactory {
    public GraphicMatrix frustum(RectF rect, float near, float far) {
        float[] values = new float[16];
        Matrix.frustumM(values, 0, rect.left, rect.right, rect.bottom, rect.top, near, far);
        return new GraphicMatrix(values);
    }
    public GraphicMatrix orthogonal(RectF rect, float near, float far) {
        float[] values = new float[16];
        Matrix.orthoM(values, 0, rect.left, rect.right, rect.bottom, rect.top, near, far);
        return new GraphicMatrix(values);
    }
    public GraphicMatrix perspective(float fov, float aspect, float near, float far) {
        float[] values = new float[16];
        Matrix.perspectiveM(values, 0, fov, aspect, near, far);
        return new GraphicMatrix(values);
    }
    public GraphicMatrix lookAt(Vector3 eye, Vector3 center, Vector3 up) {
        float[] values = new float[16];
        Matrix.setLookAtM(values, 0,
                eye.x(), eye.y(), eye.z(),
                center.x(), center.y(), center.z(),
                up.x(), up.y(), up.z());
        return new GraphicMatrix(values);
    }
    public GraphicMatrix identity() {
        float[] values = new float[16];
        Matrix.setIdentityM(values, 0);
        return new GraphicMatrix(values);
    }

    public GraphicMatrix translation(Vector3 delta) {
        float[] values = new float[16];
        Matrix.setIdentityM(values, 0);
        Matrix.translateM(values, 0, delta.x(), delta.y(), delta.z());
        return new GraphicMatrix(values);
    }
    public GraphicMatrix rotation(Vector3 delta) {
        float[] values = new float[16];
        Matrix.setIdentityM(values, 0);
        Matrix.setRotateEulerM(values, 0, delta.x(), delta.y(), delta.z());
        return new GraphicMatrix(values);
    }
    public GraphicMatrix scale(Vector3 delta) {
        float[] values = new float[16];
        Matrix.setIdentityM(values, 0);
        Matrix.setRotateEulerM(values, 0, delta.x(), delta.y(), delta.z());
        return new GraphicMatrix(values);
    }
    public GraphicMatrix shear(Vector3 delta) {
        throw new RuntimeException();
    }
}
