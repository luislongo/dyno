package com.alura.dyno.maths;

public class Vector2F extends Vector {

    public Vector2F()
    {
        super(2);
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

    public static Vector2F multiply(Matrix4F m_lhs, Vector2F v_rhs) {
        return new Vector2F(Vector4F.multiply(m_lhs, new Vector4F(v_rhs,0.0f, 1.0f)));
    }

    public void setValues(float x, float y) {
        setValues(new float[]{x, y});
    }
}
