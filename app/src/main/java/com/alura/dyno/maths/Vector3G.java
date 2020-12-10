package com.alura.dyno.maths;

import static cern.jet.math.tfloat.FloatFunctions.minus;
import static cern.jet.math.tfloat.FloatFunctions.mult;
import static cern.jet.math.tfloat.FloatFunctions.plus;

public class Vector3G extends VectorG<Vector3G> {
    public Vector3G() {
        super(3);
    }
    public Vector3G(float[] floats) {
        super(3, floats);
    }
    public Vector3G(float x, float y, float z)
    {
        this(new float[]{x, y, z});
    }
    public Vector3G(Vector2G vec2, float z) {
        this(vec2.x(), vec2.y(), z);
    }
    public Vector3G(Vector3G v_other) {
        super(v_other);
    }
    public Vector3G(Vector4G vec4) {
        this(vec4.x(), vec4.y(), vec4.z());
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

    public void setX(float value) {
        setQuick(0, value);
    }
    public void setY(float value) {
        setQuick(1, value);
    }
    public void setZ(float value) {
        setQuick(2, value);
    }

    public void setValues(float x, float y, float z) {
        setValues(new float[]{x, y, z});
    }
    public void multiply(MatrixG m_left, float w) {
        Vector4G extVector = new Vector4G(this, w);
        m_left.zMult(extVector, extVector);

        this.setValues(extVector.x(), extVector.y(), extVector.z());
    }

    //..Static methods
    public static Vector3G plus(Vector3G v_left, Vector3G v_right) {
        Vector3G res = new Vector3G(v_left.toArray());
        res.assign(v_right, plus);

        return res;
    }
    public static Vector3G minus(Vector3G v_left, Vector3G v_right) {
        Vector3G res = new Vector3G(v_left.toArray());
        res.assign(v_right, minus);

        return res;
    }
    public static Vector3G multiply(Vector3G v_left, Vector3G v_right) {
        Vector3G res = new Vector3G(v_left.toArray());
        res.assign(v_right, mult);

        return res;
    }
    public static Vector3G multiply(MatrixG m_left, Vector3G v_right, float w) {
        Vector4G extVector = new Vector4G(v_right, w);
        m_left.zMult(extVector, extVector);

        return new Vector3G(extVector);
    }

}
