package com.alura.dyno.maths;

import java.util.Arrays;

public class VectorD {
    protected double[] x_i;

    public VectorD(int size) {
        x_i = new double[size];
    }
    public VectorD(int size, double value) {
        x_i = new double[size];
        Arrays.fill(x_i, value);
    }
    public VectorD(double[] x_i) {
        assert (isDataSizeCorrect(x_i.length));
        this.x_i = Arrays.copyOf(x_i, x_i.length);
    }
    public VectorD(double[] x_i, int offset, int size) {
        this.x_i = Arrays.copyOfRange(x_i, offset, offset + size);
    }

    /* TODO Split abstract class and concrete implementation. ...
        ... Must also split Matrix implementation in order    ...
        ... to keep things organized. */
    protected boolean isDataSizeCorrect(int size)
    {
        return true;
    }

    public final double length() {
        double length = 0;

        for(int i = 0; i < count(); i++) {
            length += x_i[i] * x_i[i];
        }

        return (double)Math.sqrt(length);
    }
    public final int count() {
        return x_i.length;
    }
    public final double[] toArray() {
        return Arrays.copyOf(x_i, count());
    }

    protected final double getCell(int i) {
        return x_i[i];
    }
    protected final void setCell(int i, double value) {
        x_i[i] = value;
    }
    protected final void setValues(double[] x_i) {
        assert (isDataSizeCorrect(x_i.length));
        this.x_i = Arrays.copyOf(x_i, count());
    }

    public void add(VectorD v_rhs) {
        this.x_i = add(this, v_rhs);
    }
    public void subtract(VectorD v_rhs) {
        this.x_i = subtract(this, v_rhs);
    }
    public void divide(double c) {
        this.x_i = divide(this, c);
    }
    public void multiply(double c) {
        this.x_i = multiply(this, c);
    }
    public void straightProduct(VectorD v_rhs) {
        this.x_i = straightProduct(this, v_rhs);
    }
    public void multiply(MatrixF m_lhs) {
        this.x_i = multiply(m_lhs, this);
    }
    public void normalize() { this.x_i = normalize(this);}

    public static double[] add(VectorD v_lhs,VectorD v_rhs) {
        assert (isSameDimension(v_lhs, v_rhs));;

        double[] result = new double[v_lhs.count()];
        for(int i = 0; i < result.length; i++)
        {
            result[i] = v_lhs.x_i[i] + v_rhs.x_i[i] ;
        }

        return result;
    }
    public static double[] subtract(VectorD v_lhs,VectorD v_rhs) {
        assert (isSameDimension(v_lhs, v_rhs));;

        double[] result = new double[v_lhs.count()];
        for(int i = 0; i < result.length; i++)
        {
            result[i] = v_lhs.x_i[i] - v_rhs.x_i[i] ;
        }

        return result;
    }
    public static double[] multiply(VectorD v_lhs, double c) {
        double[] result = new double[v_lhs.count()];
        for(int i = 0; i < result.length; i++)
        {
            result[i] = v_lhs.x_i[i] * c;
        }

        return result;
    }
    public static double[] divide(VectorD v_lhs, double c) {
        double[] result = new double[v_lhs.count()];
        for(int i = 0; i < result.length; i++)
        {
            result[i] = v_lhs.x_i[i] / c;
        }

        return result;
    }
    public static double[] multiply(MatrixF m_lhs, VectorD v_rhs) {
        assert (isMultiplyDimension(m_lhs, v_rhs));

        double[] result = new double[v_rhs.count()];
        for(int i = 0; i < v_rhs.count(); i++) {
            double sum = 0;

            for(int j = 0; j < m_lhs.cols(); j++)
            {
                sum += m_lhs.getCell(i,j) * v_rhs.getCell(j);
            }

            result[i] = sum;
        }

        return result;
    }
    public static double[] straightProduct(VectorD v_lhs, VectorD v_rhs) {
        assert (isSameDimension(v_lhs, v_rhs));

        double[] result = new double[v_lhs.count()];
        for(int i = 0; i < v_rhs.count(); i++) {
            result[i] = v_lhs.x_i[i] * v_rhs.x_i[i];
        }

        return result;
    }
    public static double[] normalize(VectorD v_lhs) {
        double length = v_lhs.length();

        double[] result = new double[v_lhs.count()];

        if(length != 0.0f)
        {
            for(int i = 0; i < v_lhs.count(); i++)
            {
                result[i] = v_lhs.getCell(i) / length;
            }
        }

        return result;
    }
    public static double dotProduct(VectorD v_lhs, VectorD v_rhs) {
        assert (isSameDimension(v_lhs, v_rhs));

        double result = 0;
        for(int i = 0; i < v_lhs.count(); i++)
        {
            result += v_lhs.x_i[i] * v_rhs.x_i[i] ;
        }

        return result;
    }

    protected static boolean isMultiplyDimension(MatrixF m_lhs, VectorD v_rhs) {
        return m_lhs.cols() == v_rhs.count();
    }
    protected static boolean isSameDimension(VectorD v_lhs, VectorD v_rhs) {
        return  v_lhs.count() == v_rhs.count();
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
