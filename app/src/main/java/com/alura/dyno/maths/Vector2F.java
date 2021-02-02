package com.alura.dyno.maths;

public class Vector2F extends VectorF<Vector2F> {

    public Vector2F()
    {
        super(2);
    }
    public Vector2F(float[] values)
    {
        super(values, 0, 2);
    }
    public Vector2F(Vector2F origin)
    {
        super(origin.toArray());
    }
    public Vector2F(Vector3F origin)
    {
        super(new float[]{origin.x(), origin.y()});
    }
    public Vector2F(Vector4F origin)
    {
        super(new float[]{origin.x(), origin.y()});
    }
    public Vector2F(float x, float y) {
        super(new float[]{x,y});
    }

    public float x() {
        return getX_(0);
    }
    public float y() {
        return getX_(1);
    }

    public static Vector2F add(Vector2F v_lhs,Vector2F v_rhs) {
        return new Vector2F(add(v_lhs,v_rhs));
    }
    public static Vector2F subtract(Vector2F v_lhs,Vector2F v_rhs) {
        return new Vector2F(subtract(v_lhs, v_rhs));
    }
    public static Vector2F multiply(Vector2F v_lhs, float c) {
        return new Vector2F(multiply(v_lhs, c));
    }
    public static Vector2F multiply(Matrix4F m_lhs, Vector2F v_rhs, float z, float w) {
        return new Vector2F(multiply(m_lhs, new Vector4F(v_rhs, z, w)));
    }
    public static Vector2F divide(Vector2F v_lhs, float c) {
        return new Vector2F(divide(v_lhs, c));
    }
    public static Vector2F straightProduct(Vector2F v_lhs, Vector2F v_rhs) {
        return new Vector2F(straightProduct(v_lhs, v_lhs));
    }

    public void setValues(float x, float y) {
        setValues(new float[]{x, y});
    }

    public static Vector2F ZERO() {
        return new Vector2F(0.0f, 0.0f);
    }
    @Override public Vector2F clone() {
        return new Vector2F(this);
    }
    @Override protected boolean isDataSizeCorrect(int size)
    {
        return size == 2;
    }

}
