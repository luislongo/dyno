package com.alura.dyno.maths;

import java.util.Arrays;

public class VectorF {
    protected float[] x_i;

    public VectorF(int size) {
        x_i = new float[size];
    }
    public VectorF(int size, float value)
    {
        Arrays.fill(x_i, value);
    }
    public VectorF(float[] x_i) {
        assert (isDataSizeCorrect(x_i.length));
        this.x_i = Arrays.copyOf(x_i, x_i.length);
    }

    /* TODO Split abstract class and concrete implementation. ...
        ... Must also split Matrix implementation in order    ...
        ... to keep things organized. */
    protected boolean isDataSizeCorrect(int size)
    {
        return true;
    }

    public void add(VectorF v_rhs) {
        this.x_i = VectorMath.add(this, v_rhs);
    }
    public void subtract(VectorF v_rhs) {
        this.x_i = VectorMath.subtract(this, v_rhs);
    }
    public void divide(float c) {
        this.x_i = VectorMath.divide(this, c);
    }
    public void multiply(float c) {
        this.x_i = VectorMath.multiply(this, c);
    }
    public void straightProduct(VectorF v_rhs) {
        this.x_i = VectorMath.straightProduct(this, v_rhs);
    }
    public void multiply(MatrixF m_lhs) {
        this.x_i = VectorMath.multiply(m_lhs, this);
    }

    public final float length() {
        float length = 0;

        for(int i = 0; i < nrOfCoords(); i++) {
            length += x_i[i] * x_i[i];
        }

        return (float)Math.sqrt(length);
    }
    public final int nrOfCoords() {
        return x_i.length;
    }
    public final float[] toArray() {
        return Arrays.copyOf(x_i, nrOfCoords());
    }

    protected final float getX_(int i) {
        return x_i[i];
    }
    protected final void setX_(int i, float value) {
        x_i[i] = value;
    }
    protected final void setValues(float[] x_i) {
        assert (isDataSizeCorrect(x_i.length));
        this.x_i = Arrays.copyOf(x_i, nrOfCoords());
    }

    @Override
    public final String toString() {
        StringBuffer buffer = new StringBuffer();

        buffer.append("[");
        for(int i = 0; i < nrOfCoords() - 1; i++)
        {
            buffer.append(x_i[i]);
            buffer.append(", ");
        }
        buffer.append(x_i[nrOfCoords() - 1]);
        buffer.append("]");

        return buffer.toString();
    }
}
