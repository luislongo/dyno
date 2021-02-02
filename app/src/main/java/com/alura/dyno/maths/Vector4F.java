package com.alura.dyno.maths;

import android.opengl.Matrix;

public class Vector4F extends VectorF<Vector4F> {
    public Vector4F(float[] values)
    {
        super(values, 0, 4);
    }
    public Vector4F(Vector2F origin, float z, float w)
    {
        this(origin.x(), origin.y(), z, w);
    }
    public Vector4F(Vector3F origin, float w)
    {
        this(origin.x(), origin.y(), origin.z(), w);
    }
    public Vector4F(Vector4F origin)
    {
        super(origin.toArray());
    }
    public Vector4F(float x, float y, float z, float w) {
        super(new float[]{x, y, z, w});
    }

    public float x() {
        return getX_(0);
    }
    public float y() {
        return getX_(1);
    }
    public float z() {
        return getX_(2);
    }
    public float w() {
        return getX_(3);
    }

    public void multiply(Matrix4F m_lhs)
    {
        Matrix.multiplyMV(x_i, 0, m_lhs.x_ij, 0, x_i, 0);
    }

    public static Vector4F add(Vector4F v_lhs,Vector4F v_rhs) {
        return new Vector4F(add(v_lhs,v_rhs));
    }
    public static Vector4F subtract(Vector4F v_lhs,Vector4F v_rhs) {
        return new Vector4F(subtract(v_lhs, v_rhs));
    }
    public static Vector4F multiply(Vector4F v_lhs, float c) {
        return new Vector4F(multiply(v_lhs, c));
    }
    public static Vector4F multiply(Matrix4F m_lhs, Vector4F v_rhs) {
        return new Vector4F(multiply(m_lhs, v_rhs));
    }
    public static Vector4F divide(Vector4F v_lhs, float c) {
        return new Vector4F(divide(v_lhs, c));
    }
    public static Vector4F straightProduct(Vector4F v_lhs, Vector4F v_rhs) {
        return new Vector4F(straightProduct(v_lhs, v_lhs));
    }

    public void setValues(float x, float y, float z, float w) {
        setValues(new float[]{x, y, z, w});
    }
    public static Vector4F ZERO() {
        return new Vector4F(0.0f, 0.0f, 0.0f, 0.0f);
    }

    @Override public Vector4F clone() {
        return new Vector4F(this);
    }
    @Override protected boolean isDataSizeCorrect(int size)
    {
        return size == 4;
    }

}

