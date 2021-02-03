package com.alura.dyno.maths;

public class Vector3F extends VectorF<Vector3F> {
    public Vector3F()
    {
        super(3);
    }
    public Vector3F(float value)
    {
        super(3, value);
    }
    public Vector3F(float[] values) {
        super(values);
    }
    public Vector3F(float x, float y, float z) {
        super(new float[]{x, y, z});
    }

    public Vector3F(Vector2F other, float z) {
        this(other.x(), other.y(), z);
    }
    public Vector3F(Vector3F origin)
    {
        super(origin.toArray());
    }
    public Vector3F(Vector4F origin) { this(origin.x(), origin.y(),origin.z());}

    public float x() {
        return data.getQuick(0);
    }
    public float y() {
        return data.getQuick(1);
    }
    public float z() {
        return data.getQuick(2);
    }
    public void setValues(float x, float y, float z) {
        setValues(new float[]{x, y, z});
    }

    public Vector3F multiply(Matrix4F m_lhs, float w) {
        Vector4F extended = new Vector4F(this, w);
        extended.multiply(m_lhs);

        return new Vector3F(extended);
    }

    @Override public Vector3F clone() {
        return new Vector3F(this);
    }
}
