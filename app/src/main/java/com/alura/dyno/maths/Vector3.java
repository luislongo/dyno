package com.alura.dyno.maths;

public class Vector3 {
    private float x;
    private float y;
    private float z;
    private float length;

    public Vector3(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;

        updateLength();
    }

    public Vector3() {
        this.x = 0;
        this.y = 0;
        this.z = 0;

        updateLength();
    }

    public static Vector3 inverse(Vector3 v) {
        return new Vector3(-v.x, -v.y, -v.z);
    }

    public static Vector3 times(Vector3 v_lhs, float c) {
        return new Vector3(c * v_lhs.x, c * v_lhs.y, c * v_lhs.z);
    }

    public static Vector3 divide(Vector3 v_lhs, float c) {
        return new Vector3(v_lhs.x / c, v_lhs.y / c, v_lhs.z / c);
    }

    public static Vector3 minus(Vector3 v_lhs, Vector3 v_rhs) {
        return new Vector3(v_lhs.x - v_rhs.x, v_lhs.y - v_rhs.y, v_lhs.z - v_rhs.z);
    }

    public static Vector3 plus(Vector3 v_lhs, Vector3 v_rhs) {
        return new Vector3(v_lhs.x + v_rhs.x, v_lhs.y + v_rhs.y, v_lhs.z + v_rhs.z);
    }

    @Override
    public String toString() {
        return "(" + x + ", " + y + ", + " + z + ")";
    }

    public float[] toArray() {
        return new float[]{x, y, z};
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

    public float getZ() {
        return z;
    }

    public void setValues(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;

        updateLength();
    }

    private void updateLength() {
        length = (float) Math.sqrt(x * x + y * y + z * z);
    }
}
