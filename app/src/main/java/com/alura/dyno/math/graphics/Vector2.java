package com.alura.dyno.math.graphics;

public class Vector2 extends FloatVector<Vector2> {
    public Vector2()
    {
        super(2);
    }
    public Vector2(float value)
    {
        super(2, value);
    }
    public Vector2(float[] values)
    {
        super(values);
    }
    public Vector2(float x, float y) {
        this(new float[]{x,y});
    }

    public Vector2(Vector2 other)
    {
        super(other.toArray());
    }
    public Vector2(Vector3 other)
    {
        this(other.x(), other.y());
    }
    public Vector2(Vector4 other)
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

    public Vector2 multiply(GraphicMatrix m_lhs, float z, float w) {
        Vector4 extended = new Vector4(this, z, w);
        extended.multiply(m_lhs);

        return new Vector2(extended);
    }

    @Override public Vector2 clone() {
        return new Vector2(this);
    }
}
