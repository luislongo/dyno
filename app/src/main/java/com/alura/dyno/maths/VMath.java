package com.alura.dyno.maths;

public class VMath {
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
    public static float dotProduct(VectorF v_lhs, VectorF v_rhs) {
        assert (isSameDimension(v_lhs, v_rhs));

        float result = 0;
        for(int i = 0; i < v_lhs.count(); i++)
        {
            result += v_lhs.x_i[i] * v_rhs.x_i[i] ;
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

    protected static boolean isMultiplyDimension(MatrixF m_lhs, VectorF v_rhs) {
        return m_lhs.cols() == v_rhs.count();
    }
    protected static boolean isSameDimension(VectorF v_lhs, VectorF v_rhs) {
        return  v_lhs.count() == v_rhs.count();
    }
}
