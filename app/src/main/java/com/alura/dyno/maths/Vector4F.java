package com.alura.dyno.maths;

import android.opengl.Matrix;

public class Vector4F extends Vector {

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
    public void setValues(float x, float y, float z, float w) {
        setValues(new float[]{x, y, z, w});
    }
}

