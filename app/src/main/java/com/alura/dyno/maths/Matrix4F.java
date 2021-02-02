package com.alura.dyno.maths;

import android.graphics.RectF;
import android.opengl.Matrix;

public class Matrix4F extends MatrixF {

    public Matrix4F() {
        super(4, 4);
    }
    public Matrix4F(Matrix4F origin) {
        super(origin);
    }

    public static Matrix4F invert(Matrix4F m) {
        Matrix4F result = new Matrix4F();
        Matrix.invertM(result.x_ij, 0, m.x_ij, 0);

        return result;
    }
    public static Matrix4F transpose(Matrix4F m) {
        Matrix4F result = new Matrix4F();
        Matrix.transposeM(result.x_ij, 0, m.x_ij, 0);

        return result;
    }
    public static Matrix4F multiply(Matrix4F m_lhs, Matrix4F m_rhs) {
        Matrix4F result = new Matrix4F();
        Matrix.multiplyMM(result.x_ij, 0, m_lhs.x_ij, 0, m_rhs.x_ij, 0);

        return result;
    }
    public static Matrix4F frustum(RectF rect, float near, float far) {
        Matrix4F m = new Matrix4F();
        Matrix.frustumM(m.x_ij, 0, rect.left, rect.right, rect.bottom, rect.top, near, far);

        return m;
    }
    public static Matrix4F orthogonal(RectF rect, float near, float far) {
        Matrix4F m = new Matrix4F();
        Matrix.orthoM(m.x_ij, 0, rect.left, rect.right, rect.bottom, rect.top, near, far);

        return m;
    }
    public static Matrix4F perspective(float fov, float aspect, float near, float far) {
        Matrix4F m = new Matrix4F();
        Matrix.perspectiveM(m.x_ij, 0, fov, aspect, near, far);

        return m;
    }
    public static Matrix4F lookAt(Vector3F eye, Vector3F center, Vector3F up) {
        Matrix4F m = new Matrix4F();
        Matrix.setLookAtM(m.x_ij, 0, eye.x(), eye.y(), eye.z(),
                center.x(), center.y(), center.z(),
                up.x(), up.y(), up.z());

        return m;
    }
    public static Matrix4F identity() {
        Matrix4F m = new Matrix4F();
        Matrix.setIdentityM(m.x_ij, 0);

        return m;
    }

    public void setIdentity()
    {
        Matrix.setIdentityM(x_ij, 0);
    }
    public void invert() {
       this.x_ij = invert(this).x_ij;
    }
    public void preMultiply(Matrix4F m_rhs) {
        this.x_ij = multiply(this, m_rhs).x_ij;
    }
    public void postMultiply(Matrix4F m_lhs) {
        this.x_ij = multiply(m_lhs, this).x_ij;
    }

    public void translate(Vector3F distance) {
        Matrix.translateM(x_ij, 0, distance.x(), distance.y(), distance.z());
    }
    public void rotate(float angle, Vector3F axis) {
        Matrix.rotateM(x_ij, 0, angle, axis.x(),axis.y(),axis.z());
    }
    public void rotate(Vector3F eulerAngles) {
        Matrix.setRotateEulerM(x_ij, 0, eulerAngles.x(), eulerAngles.y(), eulerAngles.z());
    }
    public void scale(Vector3F factor) {
        Matrix.scaleM(x_ij, 0, factor.x(), factor.y(), factor.z());
    }
}
