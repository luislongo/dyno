package com.alura.dyno.maths;

import static cern.jet.math.tfloat.FloatFunctions.minus;
import static cern.jet.math.tfloat.FloatFunctions.mult;

public class Vector4G extends VectorG<Vector4G> {
    public Vector4G() {
        super(4);
    }
    public Vector4G(float[] floats) {
        super(4, floats);
    }
    public Vector4G(float x, float y, float z, float w)
    {
        this(new float[]{x, y, z, w});
    }
    public Vector4G(Vector2G vec2, float z, float w) {
        this(vec2.x(), vec2.y(), z, w);
    }
    public Vector4G(Vector3G vec3, float w) {
        this(vec3.x(), vec3.y(), vec3.z(), w);
    }
    public Vector4G(Vector4G v_other) {
        super(v_other);
    }

    public float x() {
        return getQuick(0);
    }
    public float y() {
        return getQuick(1);
    }
    public float z() {
        return getQuick(2);
    }
    public float w() {
        return getQuick(2);
    }

    public void setX(float value) {
        setQuick(0, value);
    }
    public void setY(float value) {
        setQuick(1, value);
    }
    public void setZ(float value) {
        setQuick(2, value);
    }
    public void setW(float value) {
        setQuick(3, value);
    }

    public void setValues(float x, float y, float z, float w) {
        setValues(new float[]{x,y,z,w});
    }
    public void multiply(MatrixG m_left) {
        m_left.zMult(this, this);
    }

    //..Static methods
    public static Vector4G plus(Vector4G v_left, Vector4G v_right) {
        Vector4G res = new Vector4G(v_left.toArray());
        res.plus(v_right);

        return (Vector4G) res;
    }
    public static Vector4G minus(Vector4G v_left, Vector4G v_right) {
        Vector4G res = new Vector4G(v_left.toArray());
        res.assign(v_right, minus);

        return (Vector4G) res;
    }
    public static Vector4G multiply(Vector4G v_left, Vector4G v_right) {
        Vector4G res = new Vector4G(v_left.toArray());
        res.assign(v_right, mult);

        return (Vector4G) res;
    }
    public static Vector4G multiply(MatrixG m_left, Vector4G v_right) {
        Vector4G res = new Vector4G(v_right.toArray());
        res.multiply(m_left);

        return (Vector4G) res;
    }
}
