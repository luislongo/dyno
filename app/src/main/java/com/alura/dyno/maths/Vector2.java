package com.alura.dyno.maths;

public class Vector2 {
    private float x;
    private float y;
    private float length;

    public Vector2(float x, float y) {
        this.x = x;
        this.y = y;

        updateLength();
    }

    public Vector2() {
        this.x = 0;
        this.y = 0;

        updateLength();
    }

    public static Vector2 inverse(Vector2 v) {
        return new Vector2(-v.x, -v.y);
    }

    public static Vector2 times(Vector2 v_lhs, float c) {
        return new Vector2(c * v_lhs.x, c * v_lhs.y);
    }

    public static Vector2 divide(Vector2 v_lhs, float c) {
        return new Vector2(v_lhs.x / c, v_lhs.y / c);
    }

    public static Vector2 minus(Vector2 v_lhs, Vector2 v_rhs) {
        return new Vector2(v_lhs.x - v_rhs.x, v_lhs.y - v_rhs.y);
    }

    public static Vector2 plus(Vector2 v_lhs, Vector2 v_rhs) {
        return new Vector2(v_lhs.x + v_rhs.x, v_lhs.y + v_rhs.y);
    }

    public float getLength() {
        return length;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public void setValues(float x, float y) {
        this.x = x;
        this.y = y;

        updateLength();
    }

    private void updateLength() {
        length = (float) Math.sqrt(x * x + y * y);
    }

    public float[] toArray() {
        return new float[]{x, y};
    }

    @Override
    public String toString() {
        return "(" + x + ", " + y + ")";
    }
}
