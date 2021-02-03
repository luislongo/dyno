package com.alura.dyno.maths;

import android.opengl.Matrix;

public class Vector4F extends VectorF<Vector4F> {
    public Vector4F() {super(4);}
    public Vector4F(float value) {super(4, value);}
    public Vector4F(float[] values)
    {
        super(values);
    }
    public Vector4F(float x, float y, float z, float w) {
        super(new float[]{x, y, z, w});
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

    public float x() {
        return data.getQuick(0);
    }
    public float y() {
        return data.getQuick(1);
    }
    public float z() {
        return data.getQuick(2);
    }
    public float w() {
        return data.getQuick(3);
    }
    public void setValues(float x, float y, float z, float w) {
        setValues(new float[]{x, y, z, w});
    }

    public Vector4F multiply(Matrix4F m_lhs) {
        data.assign(fun.mult(m_lhs.getData(), this.data));
        return this;
    }

    @Override public Vector4F clone() {
        return new Vector4F(this);
    }

}

