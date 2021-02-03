package com.alura.dyno.maths.graphics;

import com.alura.dyno.maths.linalg.FloatVector;

public class Vector2F extends FloatVector<Vector2F> {
    public Vector2F()
    {
        super(2);
    }
    public Vector2F(float value)
    {
        super(2, value);
    }
    public Vector2F(float[] values)
    {
        super(values);
    }
    public Vector2F(float x, float y) {
        this(new float[]{x,y});
    }

    public Vector2F(Vector2F other)
    {
        super(other.toArray());
    }
    public Vector2F(Vector3F other)
    {
        this(other.x(), other.y());
    }
    public Vector2F(Vector4F other)
    {
        this(other.x(), other.y());
    }

    public float x() {
        return data.getQuick(0);
    }
    public float y() {
        return data.getQuick(1);
    }
    public void setValues(float x, float y) {
        setValues(new float[]{x, y});
    }

    public Vector2F multiply(GraphicMatrix m_lhs, float z, float w) {
        Vector4F extended = new Vector4F(this, z, w);
        extended.multiply(m_lhs);

        return new Vector2F(extended);
    }

    @Override public Vector2F clone() {
        return new Vector2F(this);
    }
}
