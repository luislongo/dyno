package com.alura.dyno.maths;

import static cern.jet.math.tfloat.FloatFunctions.minus;
import static cern.jet.math.tfloat.FloatFunctions.mult;
import static cern.jet.math.tfloat.FloatFunctions.plus;

public class Vector2G extends VectorG<Vector2G> {
    public Vector2G() {
        super(2);
    }
    public Vector2G(float[] floats) {
        super(2);
        assign(floats);
    }
    public Vector2G(float x, float y)
    {
        this(new float[]{x, y});
    }
    public Vector2G(Vector2G v_other) {
        super(v_other);
    }
    public Vector2G(Vector3G vec3) {
        this(vec3.x(), vec3.y());
    }
    public Vector2G(Vector4G vec4) {
        this(vec4.x(), vec4.y());
    }

    public float x() {
        return getQuick(0);
    }
    public float y() {
        return getQuick(1);
    }

    public void setX(float value) {
        setQuick(0, value);
    }
    public void setY(float value) {
        setQuick(1, value);
    }

    public void setValues(float x, float y) {
        setQuick(0, x);
        setQuick(1, y);
    }
    public void multiply(MatrixG m_left,float z, float w) {
        Vector4G extVector = new Vector4G(this,z, w);
        m_left.zMult(extVector, extVector);

        this.setValues(extVector.x(), extVector.y());
    }

    //..Static methods
    public static Vector2G plus(Vector2G v_left, Vector2G v_right) {
        Vector2G res = new Vector2G(v_left.toArray());
        res.assign(v_right, plus);

        return res;
    }
    public static Vector2G minus(Vector2G v_left, Vector2G v_right) {
        Vector2G res = new Vector2G(v_left.toArray());
        res.assign(v_right, minus);

        return res;
    }
    public static Vector2G multiply(Vector2G v_left, Vector2G v_right) {
        Vector2G res = new Vector2G(v_left.toArray());
        res.assign(v_right, mult);

        return res;
    }
    public static Vector2G multiply(MatrixG m_left, Vector2G v_right, float z, float w) {
        Vector4G extVector = new Vector4G(v_right, z, w);
        m_left.zMult(extVector, extVector);

        return new Vector2G(extVector);
    }


}
