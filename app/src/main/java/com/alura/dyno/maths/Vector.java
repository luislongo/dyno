package com.alura.dyno.maths;

import com.alura.dyno.R;

import java.util.Arrays;

public class Vector {
    protected float[] x_i;

    public Vector(int size) {
        x_i = new float[size];
    }
    public Vector(int size, float value)
    {
        Arrays.fill(x_i, value);
    }
    public Vector(float[] x_i) {
        this.x_i = Arrays.copyOf(x_i, x_i.length);
    }

    public void add(Vector v_rhs) {
        this.x_i = add(this, v_rhs).x_i;
    }
    public void subtract(Vector v_rhs) {
        this.x_i = subtract(this, v_rhs).x_i;
    }
    public void divide(float c) {
        this.x_i = divide(this, c).x_i;
    }
    public void multiply(float c) {
        this.x_i = multiply(this, c).x_i;
    }
    public void straightProduct(Vector v_rhs) {
        this.x_i = straightProduct(this, v_rhs).x_i;
    }
    public void multiply(MatrixF m_lhs) {
        this.x_i = multiply(m_lhs, this).x_i;
    }
    public float dot(Vector v_rhs) {
        return dot(this, v_rhs);
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

    public static <T extends Vector> T add(T v_lhs, T v_rhs) {
        assert (isSameDimension(v_lhs, v_rhs));;

        Vector result = new Vector(v_lhs.nrOfCoords());
        for(int i = 0; i < result.nrOfCoords(); i++)
        {
            result.x_i[i] = v_lhs.x_i[i] + v_rhs.x_i[i] ;
        }

        return (T) result;
    }
    public static <T extends Vector> T subtract(T v_lhs, T v_rhs) {
        assert (isSameDimension(v_lhs, v_rhs));;

        Vector result = new Vector(v_lhs.nrOfCoords());
        for(int i = 0; i < result.nrOfCoords(); i++)
        {
            result.x_i[i] = v_lhs.x_i[i] - v_rhs.x_i[i] ;
        }

        return (T) result;
    }
    public static <T extends Vector> T multiply(T v_lhs, float c) {
        Vector result = new Vector(v_lhs.nrOfCoords());
        for(int i = 0; i < result.nrOfCoords(); i++)
        {
            result.x_i[i] = v_lhs.x_i[i] * c ;
        }

        return (T) result;
    }
    public static <T extends Vector> T divide(T v_lhs, float c) {
        Vector result = new Vector(v_lhs.nrOfCoords());
        for(int i = 0; i < result.nrOfCoords(); i++)
        {
            result.x_i[i] = v_lhs.x_i[i] / c ;
        }

        return (T) result;
    }
    public static <T extends Vector> float dot(T v_lhs, T v_rhs) {
        assert (isSameDimension(v_lhs, v_rhs));

        float result = 0;
        for(int i = 0; i < v_lhs.nrOfCoords(); i++)
        {
            result += v_lhs.x_i[i] * v_rhs.x_i[i] ;
        }

        return result;
    }
    public static <T extends Vector> T multiply(MatrixF m_lhs, T v_rhs) {
        assert (isMultiplyDimension(m_lhs, v_rhs));

        Vector result = new Vector(v_rhs.nrOfCoords());
        for(int i = 0; i < v_rhs.nrOfCoords(); i++) {
            float sum = 0;

            for(int j = 0; j < m_lhs.getNrOfCols(); j++)
            {
                sum += m_lhs.getX_(i,j) * v_rhs.getX_(j);
            }

            result.setX_(i, sum);
        }

        return (T) result;
    }
    public static <T extends Vector> T straightProduct(T v_lhs, T v_rhs) {
        assert (isSameDimension(v_lhs, v_rhs));

        Vector result = new Vector(v_lhs.nrOfCoords());
        for(int i = 0; i < v_rhs.nrOfCoords(); i++) {
            result.setX_(i, v_lhs.x_i[i] * v_rhs.x_i[i]);
        }

        return (T) result;
    }

    protected final float getX_(int i) {
        return x_i[i];
    }
    protected final void setX_(int i, float value) {
        x_i[i] = value;
    }
    protected final void setValues(float[] x_i) {
        assert (isSameDimension(this, x_i));
        this.x_i = Arrays.copyOf(x_i, nrOfCoords());
    }

    private static boolean isMultiplyDimension(MatrixF m_lhs, Vector v_rhs) {
        return m_lhs.getNrOfCols() != v_rhs.nrOfCoords();
    }
    private static boolean isSameDimension(Vector v_lhs, Vector v_rhs) {
        return  v_lhs.nrOfCoords() == v_rhs.nrOfCoords();
    }
    private static boolean isSameDimension(Vector v_lhs, float[] v_rhs) {
        return  v_lhs.nrOfCoords() == v_rhs.length;
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
