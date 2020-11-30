package com.alura.dyno.maths;

import java.util.Arrays;

public class VectorF {
    protected float[] x_i;

    public VectorF(int size) {
        x_i = new float[size];
    }
    public VectorF(int size, float value) {
        x_i = new float[size];
        Arrays.fill(x_i, value);
    }
    public VectorF(float[] x_i) {
        assert (isDataSizeCorrect(x_i.length));
        this.x_i = Arrays.copyOf(x_i, x_i.length);
    }
    public VectorF(float[] x_i, int offset, int size) {
        this.x_i = Arrays.copyOfRange(x_i, offset, offset + size);
    }

    /* TODO Split abstract class and concrete implementation. ...
        ... Must also split Matrix implementation in order    ...
        ... to keep things organized. */
    protected boolean isDataSizeCorrect(int size)
    {
        return true;
    }

    public void add(VectorF v_rhs) {
        this.x_i = VMath.add(this, v_rhs);
    }
    public void subtract(VectorF v_rhs) {
        this.x_i = VMath.subtract(this, v_rhs);
    }
    public void divide(float c) {
        this.x_i = VMath.divide(this, c);
    }
    public void multiply(float c) {
        this.x_i = VMath.multiply(this, c);
    }
    public void straightProduct(VectorF v_rhs) {
        this.x_i = VMath.straightProduct(this, v_rhs);
    }
    public void multiply(MatrixF m_lhs) {
        this.x_i = VMath.multiply(m_lhs, this);
    }
    public void normalize() { this.x_i = VMath.normalize(this);}

    public final float length() {
        float length = 0;

        for(int i = 0; i < count(); i++) {
            length += x_i[i] * x_i[i];
        }

        return (float)Math.sqrt(length);
    }
    public final int count() {
        return x_i.length;
    }
    public final float[] toArray() {
        return Arrays.copyOf(x_i, count());
    }

    protected final float getX_(int i) {
        return x_i[i];
    }
    protected final void setX_(int i, float value) {
        x_i[i] = value;
    }
    protected final void setValues(float[] x_i) {
        assert (isDataSizeCorrect(x_i.length));
        this.x_i = Arrays.copyOf(x_i, count());
    }

    @Override
    public final String toString() {
        StringBuffer buffer = new StringBuffer();

        buffer.append("[");
        for(int i = 0; i < count() - 1; i++)
        {
            buffer.append(x_i[i]);
            buffer.append(", ");
        }
        buffer.append(x_i[count() - 1]);
        buffer.append("]");

        return buffer.toString();
    }
}
