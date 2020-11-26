package com.alura.dyno.maths;

public class VectorMath {
    public static float[] add(VectorF v_lhs,VectorF v_rhs) {
        assert (isSameDimension(v_lhs, v_rhs));;

        float[] result = new float[v_lhs.nrOfCoords()];
        for(int i = 0; i < result.length; i++)
        {
            result[i] = v_lhs.x_i[i] + v_rhs.x_i[i] ;
        }

        return result;
    }
    public static float[] subtract(VectorF v_lhs,VectorF v_rhs) {
        assert (isSameDimension(v_lhs, v_rhs));;

        float[] result = new float[v_lhs.nrOfCoords()];
        for(int i = 0; i < result.length; i++)
        {
            result[i] = v_lhs.x_i[i] - v_rhs.x_i[i] ;
        }

        return result;
    }
    public static float[] multiply(VectorF v_lhs, float c) {
        float[] result = new float[v_lhs.nrOfCoords()];
        for(int i = 0; i < result.length; i++)
        {
            result[i] = v_lhs.x_i[i] * c;
        }

        return result;
    }
    public static float[] divide(VectorF v_lhs, float c) {
        float[] result = new float[v_lhs.nrOfCoords()];
        for(int i = 0; i < result.length; i++)
        {
            result[i] = v_lhs.x_i[i] / c;
        }

        return result;
    }
    public static float dot(VectorF v_lhs, VectorF v_rhs) {
        assert (isSameDimension(v_lhs, v_rhs));

        float result = 0;
        for(int i = 0; i < v_lhs.nrOfCoords(); i++)
        {
            result += v_lhs.x_i[i] * v_rhs.x_i[i] ;
        }

        return result;
    }
    public static float[] multiply(MatrixF m_lhs, VectorF v_rhs) {
        assert (isMultiplyDimension(m_lhs, v_rhs));

        float[] result = new float[v_rhs.nrOfCoords()];
        for(int i = 0; i < v_rhs.nrOfCoords(); i++) {
            float sum = 0;

            for(int j = 0; j < m_lhs.getNrOfCols(); j++)
            {
                sum += m_lhs.getX_(i,j) * v_rhs.getX_(j);
            }

            result[i] = sum;
        }

        return result;
    }
    public static float[] straightProduct(VectorF v_lhs, VectorF v_rhs) {
        assert (isSameDimension(v_lhs, v_rhs));

        float[] result = new float[v_lhs.nrOfCoords()];
        for(int i = 0; i < v_rhs.nrOfCoords(); i++) {
            result[i] = v_lhs.x_i[i] * v_rhs.x_i[i];
        }

        return result;
    }

    private static boolean isMultiplyDimension(MatrixF m_lhs, VectorF v_rhs) {
        return m_lhs.getNrOfCols() != v_rhs.nrOfCoords();
    }
    private static boolean isSameDimension(VectorF v_lhs, VectorF v_rhs) {
        return  v_lhs.nrOfCoords() == v_rhs.nrOfCoords();
    }
}
