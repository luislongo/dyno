package com.alura.dyno.math.graphics;

import com.alura.dyno.math.linalg.AbstractFloatVector;

import org.jetbrains.annotations.NotNull;

public class Vector3 extends AbstractFloatVector<Vector3> {
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
    public Vector3(@NotNull Vector4 origin) { this(origin.x(), origin.y(),origin.z());}
    @Override public Vector3 clone() {
        return new Vector3(this);
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
    public void setValues(float x, float y, float z) {
        setValues(new float[]{x, y, z});
    }

    public Vector3 multiply(GraphicMatrix m_lhs, float w) {
        Vector4 extended = new Vector4(this, w);
        extended.multiply(m_lhs);

        this.setValues(extended.x(), extended.y(), extended.z());
        return this;
    }
    public Vector3 cross(Vector3 v_rhs) {
        float newX = this.y() * v_rhs.z() - this.z() * v_rhs.y();
        float newY = this.z() * v_rhs.x() - this.x() * v_rhs.z();
        float newZ = this.x() * v_rhs.y() - this.y() * v_rhs.x();

        this.setValues(newX, newY, newZ);
        return this;
    }

    public static Vector3 left() {
        return new Vector3(-1.0f, 0.0f, 0.0f);
    }
    public static Vector3 right() {
        return new Vector3(1.0f, 0.0f, 0.0f);
    }
    public static Vector3 up() {
        return new Vector3(0.0f, 1.0f, 0.0f);
    }
    public static Vector3 down() {
        return new Vector3(0.0f, -1.0f, 0.0f);
    }
    public static Vector3 forward() {
        return new Vector3(0.0f, 0.0f, -1.0f);
    }
    public static Vector3 backward() {
        return new Vector3(0.0f, 0.0f, 1.0f);
    }
}
