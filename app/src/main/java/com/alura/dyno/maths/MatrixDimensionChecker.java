package com.alura.dyno.maths;

import org.jetbrains.annotations.NotNull;

public class MatrixDimensionChecker {
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
    public static boolean isDataDimensionValid(@NotNull float[] x_ij, int rows, int cols) {
        return (x_ij.length == rows * cols);
    }
}
