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
