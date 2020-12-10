package com.alura.dyno.maths;

import android.opengl.Matrix;

public class MatrixGFactory {
    public static MatrixG identity() {
        float[] data = new float[16];
        Matrix.setIdentityM(data, 0);

        return new MatrixG(data);
    }
    public static MatrixG frustum(float left, float right, float bottom, float top, float near, float far) {
        float[] data = new float[16];
        Matrix.frustumM(data, 0, left, right, bottom, top, near, far);

        return new MatrixG(data);
    }
    public static MatrixG orthogonal(float left, float right, float bottom, float top, float near, float far) {
        float[] data = new float[16];
        Matrix.orthoM(data, 0, left, right, bottom, top, near, far);

        return new MatrixG(data);
    }
    public static MatrixG perspective(float fov, float aspect, float near, float far) {
        float[] data = new float[16];
        Matrix.perspectiveM(data, 0, fov, aspect, near, far);

        return new MatrixG(data);
    }
    public static MatrixG lookAt(Vector3G eye, Vector3G center, Vector3G up) {
        float[] data = new float[16];
        Matrix.setLookAtM(data, 0, eye.x(), eye.y(), eye.z(),
                center.x(), center.y(), center.z(),
                up.x(), up.y(), up.z());

        return new MatrixG(data);
    }

    public static MatrixG fromEulerAngles(Vector3G eulerAngles) {
        float[] data = new float[16];

        Matrix.setIdentityM(data,0);
        Matrix.rotateM(data, 0, eulerAngles.x(), 1.0f,0.0f,0.0f);
        Matrix.rotateM(data, 0, eulerAngles.y(), 0.0f, 1.0f, 0.0f);
        Matrix.rotateM(data, 0, eulerAngles.z(), 0.0f,0.0f,1.0f);

        return new MatrixG(data);
    }
    public static MatrixG fromScale(Vector3G scale) {
        float[] data = new float[16];

        Matrix.setIdentityM(data, 0);
        Matrix.scaleM(data, 0, scale.x(), scale.y(), scale.z());
        return new MatrixG(data);
    }
    public static MatrixG fromTranslation(Vector3G distance) {
        float[] data = new float[16];

        Matrix.setIdentityM(data, 0);
        Matrix.translateM(data, 0, distance.x(), distance.y(), distance.z());
        return new MatrixG(data);
    }
}
