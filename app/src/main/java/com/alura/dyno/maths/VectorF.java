package com.alura.dyno.maths;

import java.util.Arrays;

public abstract class VectorF<T extends VectorF> {
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

    public void add(VectorF v_rhs) {
        this.x_i = add(this, v_rhs);
    }
    public void subtract(VectorF v_rhs) {
        this.x_i = subtract(this, v_rhs);
    }
    public void divide(float c) {
        this.x_i = divide(this, c);
    }
    public void multiply(float c) {
        this.x_i = multiply(this, c);
    }
    public void straightProduct(VectorF v_rhs) {
        this.x_i = straightProduct(this, v_rhs);
    }
    public void multiply(MatrixF m_lhs) {
        this.x_i = multiply(m_lhs, this);
    }
    public void normalize() { this.x_i = normalize(this);}

    public static float[] add(VectorF v_lhs,VectorF v_rhs) {
        assert (isSameDimension(v_lhs, v_rhs));;

        float[] result = new float[v_lhs.count()];
        for(int i = 0; i < result.length; i++)
        {
            result[i] = v_lhs.x_i[i] + v_rhs.x_i[i] ;
        }

        return result;
    }
    public static float[] subtract(VectorF v_lhs,VectorF v_rhs) {
        assert (isSameDimension(v_lhs, v_rhs));;

        float[] result = new float[v_lhs.count()];
        for(int i = 0; i < result.length; i++)
        {
            result[i] = v_lhs.x_i[i] - v_rhs.x_i[i] ;
        }

        return result;
    }
    public static float[] multiply(VectorF v_lhs, float c) {
        float[] result = new float[v_lhs.count()];
        for(int i = 0; i < result.length; i++)
        {
            result[i] = v_lhs.x_i[i] * c;
        }

        return result;
    }
    public static float[] divide(VectorF v_lhs, float c) {
        float[] result = new float[v_lhs.count()];
        for(int i = 0; i < result.length; i++)
        {
            result[i] = v_lhs.x_i[i] / c;
        }

        return result;
    }
    public static float[] multiply(MatrixF m_lhs, VectorF v_rhs) {
        assert (isMultiplyDimension(m_lhs, v_rhs));

        float[] result = new float[v_rhs.count()];
        for(int i = 0; i < v_rhs.count(); i++) {
            float sum = 0;

            for(int j = 0; j < m_lhs.cols(); j++)
            {
                sum += m_lhs.getCell(i,j) * v_rhs.getX_(j);
            }

            result[i] = sum;
        }

        return result;
    }
    public static float[] straightProduct(VectorF v_lhs, VectorF v_rhs) {
        assert (isSameDimension(v_lhs, v_rhs));

        float[] result = new float[v_lhs.count()];
        for(int i = 0; i < v_rhs.count(); i++) {
            result[i] = v_lhs.x_i[i] * v_rhs.x_i[i];
        }

        return result;
    }
    public static float[] normalize(VectorF v_lhs) {
        float length = v_lhs.length();

        float[] result = new float[v_lhs.count()];

        if(length != 0.0f)
        {
            for(int i = 0; i < v_lhs.count(); i++)
            {
                result[i] = v_lhs.getX_(i) / length;
            }
        }

        return result;
    }
    public static float dotProduct(VectorF v_lhs, VectorF v_rhs) {
        assert (isSameDimension(v_lhs, v_rhs));

        float result = 0;
        for(int i = 0; i < v_lhs.count(); i++)
        {
            result += v_lhs.x_i[i] * v_rhs.x_i[i] ;
        }

        return result;
    }

    protected static boolean isMultiplyDimension(MatrixF m_lhs, VectorF v_rhs) {
        return m_lhs.cols() == v_rhs.count();
    }
    protected static boolean isSameDimension(VectorF v_lhs, VectorF v_rhs) {
        return  v_lhs.count() == v_rhs.count();
    }
    protected boolean isDataSizeCorrect(int size)
    {
        return true;
    }

    public abstract T clone();

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
