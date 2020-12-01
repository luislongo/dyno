package com.alura.dyno.maths;

public class Vector3F extends VectorF {

    public Vector3F(float value)
    {
        super(3, value);
    }
    public Vector3F(float[] values) {
        super(values, 0, 3);
    }
    public Vector3F(Vector2F origin, float z) {
        super(new float[]{origin.x(), origin.y(), z});
    }
    public Vector3F(Vector3F origin)
    {
        super(origin.toArray());
    }
    public Vector3F(Vector4F origin) { this(origin.x(), origin.y(),origin.z());}
    public Vector3F(float x, float y, float z) {
        super(new float[]{x, y, z});
    }

    public float x() {
        return getX_(0);
    }
    public float y() {
        return getX_(1);
    }
    public float z() {
        return getX_(2);
    }
    public void setValues(float x, float y, float z) {
        setValues(new float[]{x, y, z});
    }

    public void multiply(Matrix4F m_lhs, float w)
    {
        this.x_i = multiply(m_lhs, this, 1.0f).x_i;
    }

    public static Vector3F add(Vector3F v_lhs,Vector3F v_rhs) {
        return new Vector3F(add(v_lhs,v_rhs));
    }
    public static Vector3F subtract(Vector3F v_lhs,Vector3F v_rhs) {
        return new Vector3F(subtract(v_lhs, v_rhs));
    }
    public static Vector3F multiply(Vector3F v_lhs, float c) {
        return new Vector3F(multiply(v_lhs, c));
    }
    public static Vector3F multiply(MatrixF m_lhs, Vector3F v_rhs) {
        return new Vector3F(multiply(m_lhs, v_rhs));
    }
    public static Vector3F multiply(Matrix4F m_lhs, Vector3F v_rhs, float w) {
        Vector4F as4F = new Vector4F(v_rhs, w);
        as4F.multiply(m_lhs);

        return new Vector3F(as4F);
    }
    public static Vector3F divide(Vector3F v_lhs, float c) {
        return new Vector3F(divide(v_lhs, c));
    }
    public static Vector3F straightProduct(Vector3F v_lhs, Vector3F v_rhs) {
        return new Vector3F(straightProduct(v_lhs, v_lhs));
    }

    @Override protected boolean isDataSizeCorrect(int size)
    {
        return size == 3;
    }
}
