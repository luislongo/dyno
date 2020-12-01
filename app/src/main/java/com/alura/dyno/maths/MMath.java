package com.alura.dyno.maths;

public class MMath {
    public static MatrixF transpose(MatrixF m) {
        float[] transposedData = new float[m.count()];
        MatrixF transposed = new MatrixF(m.cols(), m.rows());

        for(int i = 0; i < transposed.rows(); i++) {
            for(int j = 0; j < transposed.cols(); j++) {
                transposed.setCell(i, j, m.getCell(j,i));
            }
        }

        return transposed;
    }
    public static MatrixF add(MatrixF m_lhs, MatrixF m_rhs) {
        assert isSameDimensions(m_lhs, m_rhs);

        MatrixF result = new MatrixF(m_lhs);
        for(int i = 0; i < m_lhs.count(); i++)
        {
            result.x_ij[i] = m_lhs.x_ij[i] + m_rhs.x_ij[i];
        }

        return result;
    }
    public static MatrixF sub(MatrixF m_lhs, MatrixF m_rhs) {
        assert isSameDimensions(m_lhs, m_rhs);

        MatrixF result = new MatrixF(m_lhs);
        for(int i = 0; i < m_lhs.count(); i++)
        {
            result.x_ij[i] = m_lhs.x_ij[i] - m_rhs.x_ij[i];
        }

        return result;
    }
    public static MatrixF mult(MatrixF m, float c) {
        MatrixF result = new MatrixF(m.rows(), m.cols());
        for(int i = 0; i < m.rows(); i++) {
            for(int j = 0; j < m.cols(); j++) {
                result.setCell(i, j, m.getCell(i, j) * c);
            }
        }

        return result;
    }
    public static MatrixF mult(MatrixF m_lhs, MatrixF m_rhs) {
        assert(isMultiplicationDimension(m_lhs, m_rhs));

        MatrixF result = new MatrixF(m_lhs.rows(), m_rhs.cols());
        for(int row = 0; row < m_lhs.rows(); row++) {
            for(int col = 0; col < m_rhs.cols(); col++) {

                float sum = 0.0f;
                for(int pvt = 0; pvt < m_lhs.cols(); pvt++) {
                    sum += m_lhs.getCell(row, pvt) * m_rhs.getCell(pvt, col);
                }

                result.setCell(row, col, sum);
            }
        }

        return result;
    }
    public static MatrixF div(MatrixF m, float c) {
        assert  (c != 0.0f);

        MatrixF result = new MatrixF(m.rows(), m.cols());
        for(int i = 0; i < m.rows(); i++) {
            for(int j = 0; j < m.cols(); j++) {
                result.setCell(i, j, m.getCell(i, j) / c);
            }
        }

        return result;
    }

    public static boolean isSquareMatrix(MatrixF m) {
        return m.isSquare();
    }
    public static boolean isSameDimensions(MatrixF m_lhs, MatrixF m_rhs) {
        return (m_lhs.rows() == m_rhs.rows() &&
                m_lhs.cols() == m_rhs.cols());
    }
    public static boolean isMultiplicationDimension(MatrixF m_lhs, MatrixF m_rhs) {
        if(m_lhs.cols() != m_rhs.rows()) {
            return false;
        }
        return true;
    }
    public static boolean isRowIndexValid(MatrixF m, int row) {
        return (row >= 0) && (row < m.rows());
    }
    public static boolean isColIndexValid(MatrixF m, int col) {
        return (col >= 0) && (col < m.cols());
    }
    public static boolean isSubMatrixDimensionValid(MatrixF m, int iMin, int iMax, int jMin, int jMax) {
        boolean isVerticalSpanValid = iMin <= iMax;
        boolean isHorizontalSpanValid = jMin <= jMax;
        boolean isBoundValid = isBoundValid(m, iMin, iMax, jMin, jMax);

        return isVerticalSpanValid && isHorizontalSpanValid && isBoundValid;
    }
    public static boolean isBoundValid(MatrixF m, int iMin, int iMax, int jMin, int jMax) {
        boolean isIMinValid = isRowIndexValid(m, iMin);
        boolean isIMaxValid = isRowIndexValid(m, iMax);
        boolean isJMinValid = isColIndexValid(m, jMin);
        boolean isJMaxValid = isColIndexValid(m, jMax);

        return isIMinValid && isIMaxValid && isJMinValid && isJMaxValid;
    }
    public static boolean isRowDimensionValid(MatrixF m, MatrixF row) {
        boolean isSameNrOfCols = m.cols() == row.cols();
        boolean isOnlyOneRow = row.rows() == 1;

        return isSameNrOfCols && isOnlyOneRow;
    }
    public static boolean isColDimensionValid(MatrixF m, MatrixF col) {
        boolean isSameNrOfRows = m.rows() == col.rows();
        boolean isOnlyOneCol = col.cols() == 1;

        return isSameNrOfRows && isOnlyOneCol;
    }
}
