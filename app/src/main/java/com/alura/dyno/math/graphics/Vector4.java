package com.alura.dyno.math.graphics;

import com.alura.dyno.math.linalg.AbstractFloatVector;
import com.alura.dyno.math.linalg.Algebra;

public class Vector4 extends AbstractFloatVector<Vector4> {
    public Vector4() {super(4);}
    public Vector4(float value) {super(4, value);}
    public Vector4(float[] values)
    {
        super(values);
    }
    public Vector4(float x, float y, float z, float w) {
        super(new float[]{x, y, z, w});
    }

    public Vector4(Vector2 origin, float z, float w)
    {
        this(origin.x(), origin.y(), z, w);
    }
    public Vector4(Vector3 origin, float w)
    {
        this(origin.x(), origin.y(), origin.z(), w);
    }
    public Vector4(Vector4 origin)
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

    public Vector4 multiply(GraphicMatrix m_lhs) {
        data.assign(Algebra.denseFloatAlgebra().mult(m_lhs.getData(), this.data));
        return this;
    }

    @Override public Vector4 clone() {
        return new Vector4(this);
    }

}

