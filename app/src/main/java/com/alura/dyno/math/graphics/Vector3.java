package com.alura.dyno.math.graphics;

public class Vector3 extends FloatVector<Vector3> {
    public Vector3()
    {
        super(3);
    }
    public Vector3(float value)
    {
        super(3, value);
    }
    public Vector3(float[] values) {
        super(values);
    }
    public Vector3(float x, float y, float z) {
        super(new float[]{x, y, z});
    }

    public Vector3(Vector2 other, float z) {
        this(other.x(), other.y(), z);
    }
    public Vector3(Vector3 origin)
    {
        super(origin.toArray());
    }
    public Vector3(Vector4 origin) { this(origin.x(), origin.y(),origin.z());}

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

    public Vector3 multiply(GraphicMatrix m_lhs, float w) {
        Vector4 extended = new Vector4(this, w);
        extended.multiply(m_lhs);

        return new Vector3(extended);
    }

    @Override public Vector3 clone() {
        return new Vector3(this);
    }
}
