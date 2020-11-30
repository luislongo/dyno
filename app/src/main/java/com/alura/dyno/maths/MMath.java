package com.alura.dyno.maths;

public class MMath {
    public static MatrixF transpose(MatrixF m) {
        float[] newArray = new float[m.count()];
        int newNrOfRows = m.cols();
        int newNrOfCols = m.rows();

        for(int i = 0; i < m.rows(); i++) {
            for(int j = 0; j < m.cols(); j++) {
                newArray[j * newNrOfCols + i] = m.getCell(i,j);
            }
        }

        return new MatrixF(newNrOfRows, newNrOfCols, newArray);
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
    public static MatrixF subFromRange(MatrixF m_lhs, MatrixF m_rhs) {
        assert isSameDimensions(m_lhs, m_rhs);

        MatrixF result = new MatrixF(m_lhs);
        for(int i = 0; i < m_lhs.count(); i++)
        {
            result.x_ij[i] = m_lhs.x_ij[i] - m_rhs.x_ij[i];
        }

        return result;
    }
    public static MatrixF multiply(MatrixF m, float c) {
        MatrixF result = new MatrixF(m.rows(), m.cols());
        for(int i = 0; i < m.rows(); i++) {
            for(int j = 0; j < m.cols(); j++) {
                result.setCell(i, j, m.getCell(i, j) * c);
            }
        }

        return result;
    }
    public static MatrixF multiply(MatrixF m_lhs, MatrixF m_rhs) {
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
    public static MatrixF divide(MatrixF m, float c) {
        assert  (c != 0.0f);

        MatrixF result = new MatrixF(m.rows(), m.cols());
        for(int i = 0; i < m.rows(); i++) {
            for(int j = 0; j < m.cols(); j++) {
                result.setCell(i, j, m.getCell(i, j) / c);
            }
        }

        return result;
    }

    public static void addTo(MatrixF m_lhs, MatrixF m_rhs) {
        assert isSameDimensions(m_lhs, m_rhs);

        for(int i = 0; i < m_lhs.count(); i++)
        {
            m_lhs.x_ij[i] += m_rhs.x_ij[i];
        }
    }
    public static void subFrom(MatrixF m_lhs, MatrixF m_rhs) {
        assert isSameDimensions(m_lhs, m_rhs);

        for(int i = 0; i < m_lhs.count(); i++)
        {
            m_lhs.x_ij[i] -= m_rhs.x_ij[i];
        }
    }

    public static MatrixF getRange(MatrixF m, int row0, int rowMax, int col0, int colMax) {
        assert (isSubMatrixDimensionValid(m, row0, rowMax, col0, colMax));

        int subRows = rowMax - row0 + 1;
        int subCols = colMax - col0 + 1;
        MatrixF sub = new MatrixF(subRows, subCols);

        for(int subRow = 0; subRow < sub.rows(); subRow++) {
            for(int subCol = 0; subCol < sub.cols(); subCol++) {
                int row = row0 + subRow;
                int col = col0 + subCol;

                sub.setCell(subRow, subCol, m.getCell(row, col));
            }
        }

        return sub;
    }
    public static void setRange(MatrixF m, MatrixF sub_m, int row0, int col0) {
        int rowMax = row0 + sub_m.rows() - 1;
        int colMax = col0 + sub_m.cols() - 1;

        assert (isSubMatrixDimensionValid(m, row0, rowMax, col0, colMax));

        for(int i = 0; i < sub_m.rows(); i++) {
            for(int j = 0; j < sub_m.cols(); j++) {
                m.setCell(row0 + i, col0 + j, sub_m.getCell(i,j));
            }
        }
    }
    public static void addToRange(MatrixF m, MatrixF sub_m, int row0, int col0) {
        int rowMax = row0 + sub_m.rows() - 1;
        int colMax = col0 + sub_m.cols() - 1;

        assert (isSubMatrixDimensionValid(m, row0, rowMax, col0, colMax));

        for(int sub_i = 0; sub_i < sub_m.rows(); sub_i++) {
            for(int sub_j = 0; sub_j < sub_m.cols(); sub_j++) {
                int i = row0 + sub_i;
                int j = col0 + sub_j;

                m.addToCell(i, j, sub_m.getCell(sub_i,sub_j));
            }
        }
    }
    public static void subFromRange(MatrixF m, MatrixF sub_m, int row0, int col0) {
        int rowMax = row0 + sub_m.rows() - 1;
        int colMax = col0 + sub_m.cols() - 1;

        assert (isSubMatrixDimensionValid(m, row0, rowMax, col0, colMax));

        for(int sub_i = 0; sub_i < sub_m.rows(); sub_i++) {
            for(int sub_j = 0; sub_j < sub_m.cols(); sub_j++) {
                int i = row0 + sub_i;
                int j = col0 + sub_j;

                m.subFromCell(i, j, sub_m.getCell(sub_i,sub_j));
            }
        }
    }
    public static void multiplyRange(MatrixF m, float c, int row0, int rowMax, int col0, int colMax) {
        assert isSubMatrixDimensionValid(m, row0, rowMax, col0, colMax);

        for(int row = row0; row <= rowMax; row++) {
            for(int col = col0; col <= colMax; col++) {
                m.multiplyCell(row, col, c);
            }
        }
    }
    public static void divideRange(MatrixF m, float c, int row0, int rowMax, int col0, int colMax) {
        assert isSubMatrixDimensionValid(m, row0, rowMax, col0, colMax);

        for(int row = row0; row <= rowMax; row++) {
            for(int col = col0; col <= colMax; col++) {
                m.multiplyCell(row, col, 1.0f / c);
            }
        }
    }

    public static MatrixF getRow(MatrixF m, int row) {
        return getRange(m, row, row, 0, m.cols() - 1);
    }
    public static void setRow(MatrixF m, MatrixF rowData, int row) {

        assert isRowDimensionValid(m, rowData);

        setRange(m, rowData, row , 0);
    }
    public static void addToRow(MatrixF m, MatrixF rowData, int row) {

        assert isRowDimensionValid(m, rowData);

        addToRange(m, rowData, row, 0);
    }
    public static void subFromRow(MatrixF m, MatrixF rowData, int row) {

        assert isRowDimensionValid(m, rowData);

        subFromRange(m, rowData, row, 0);
    }
    public static void multiplyRow(MatrixF m, int row, float c) {

        assert isRowIndexValid(m, row);

        for(int col = 0; col < m.cols(); col++)
        {
            m.multiplyCell(row, col, c);
        }
    }
    public static void divideRow(MatrixF m, int row, float c) {
        assert isRowIndexValid(m, row);

        for(int col = 0; col < m.cols(); col++)
        {
            m.multiplyCell(row, col, 1.0f / c);
        }
    }
    public static void swapLines(MatrixF m, int rowA, int rowB) {
        isRowIndexValid(m, rowA);
        isRowIndexValid(m, rowB);

        for(int j = 0; j < m.cols(); j++) {
            m.swapCells(rowA, j, rowB, j);
        }
    }

    public static MatrixF getCol(MatrixF m, int col) {
        return getRange(m, 0, m.rows() - 1, col, col);
    }
    public static void setCol(MatrixF m, MatrixF colData, int col) {

        assert isColDimensionValid(m, colData);

        setRange(m, colData, 0, col);
    }
    public static void addToCol(MatrixF m, MatrixF colData, int col) {

        assert isColDimensionValid(m, colData);

        addToRange(m, colData, 0, col);
    }
    public static void subFromCol(MatrixF m, MatrixF colData, int col) {

        assert isColDimensionValid(m, colData);

        subFromRange(m, colData, 0,  col);
    }
    public static void multiplyCol(MatrixF m, int col, float c) {

        assert isColIndexValid(m, col);

        for(int row = 0; row < m.rows(); row++) {
            m.multiplyCell(row, col, c);
        }
    }
    public static void divideCol(MatrixF m, int col, float c) {

        assert isColIndexValid(m, col);

        for(int row = 0; row < m.rows(); row++) {
            m.multiplyCell(row, col, 1.0f / c);
        }
    }
    public static void swapCols(MatrixF m, int colA, int colB) {
        isColIndexValid(m, colA);
        isColIndexValid(m, colB);

        for(int row = 0; row < m.rows(); row++) {
            m.swapCells(row, colA, row, colB);
        }
    }

    public static MatrixF getDiagonal(MatrixF m) {
        assert (isSquareMatrix(m));

        MatrixF diagonal = new MatrixF(1, m.cols());

        for(int dgn = 0; dgn < m.cols(); dgn++)
        {
            diagonal.x_ij[dgn] = m.getCell(dgn, dgn);
        }

        return diagonal;
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
