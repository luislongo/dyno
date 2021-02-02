package com.alura.dyno.maths;

import org.jetbrains.annotations.NotNull;

import java.util.Arrays;

public class MatrixD {
    private int nrOfRows;
    private int nrOfCols;
    protected double[] x_ij;

    public MatrixD(MatrixD m) {
        nrOfRows = m.nrOfRows;
        nrOfCols = m.nrOfCols;

        x_ij = Arrays.copyOf(m.x_ij, m.count());
    }
    public MatrixD(int nrOfRows, int nrOfCols) {
        this(nrOfRows, nrOfCols, new double[nrOfCols*nrOfRows]);
    }
    public MatrixD(int nrOfRows, int nrOfCols, float value) {
        this(nrOfRows, nrOfCols);
        Arrays.fill(x_ij, value);
    }
    public MatrixD(int nrOfRows, int nrOfCols, double[] x_ij) {
        assert isDataDimensionValid(x_ij, nrOfRows, nrOfCols);

        this.nrOfRows = nrOfRows;
        this.nrOfCols = nrOfCols;
        this.x_ij = Arrays.copyOf(x_ij, x_ij.length);
    }

    public int rows() {
        return nrOfRows;
    }
    public int cols() {
        return nrOfCols;
    }
    public int count() {
        return x_ij.length;
    }
    public boolean isSquare() {
        return (nrOfRows == nrOfCols);
    }

    public MatrixD getDiagonal() {
        assert (isSquareMatrix(this));

        MatrixD diagonal = new MatrixD(1, cols());

        for(int dgn = 0; dgn < cols(); dgn++)
        {
            diagonal.x_ij[dgn] = getCell(dgn, dgn);
        }

        return diagonal;
    }

    public MatrixD getRange(int row0, int rowMax, int col0, int colMax) {
        assert (isSubMatrixDimensionValid(this, row0, rowMax, col0, colMax));

        int subRows = rowMax - row0 + 1;
        int subCols = colMax - col0 + 1;
        MatrixD sub = new MatrixD(subRows, subCols);

        for(int subRow = 0; subRow < sub.rows(); subRow++) {
            for(int subCol = 0; subCol < sub.cols(); subCol++) {
                int row = row0 + subRow;
                int col = col0 + subCol;

                sub.setCell(subRow, subCol, getCell(row, col));
            }
        }

        return sub;
    }
    public MatrixD setRange(MatrixD sub_m, int row0, int col0) {
        int rowMax = row0 + sub_m.rows() - 1;
        int colMax = col0 + sub_m.cols() - 1;

        assert (isSubMatrixDimensionValid(this, row0, rowMax, col0, colMax));

        for(int i = 0; i < sub_m.rows(); i++) {
            for(int j = 0; j < sub_m.cols(); j++) {
                setCell(row0 + i, col0 + j, sub_m.getCell(i,j));
            }
        }

        return this;
    }
    public MatrixD addRange(MatrixD sub_m, int row0, int col0) {
        int rowMax = row0 + sub_m.rows() - 1;
        int colMax = col0 + sub_m.cols() - 1;

        assert (isSubMatrixDimensionValid(this, row0, rowMax, col0, colMax));

        for(int sub_i = 0; sub_i < sub_m.rows(); sub_i++) {
            for(int sub_j = 0; sub_j < sub_m.cols(); sub_j++) {
                int i = row0 + sub_i;
                int j = col0 + sub_j;

                addToCell(i, j, sub_m.getCell(sub_i,sub_j));
            }
        }

        return this;
    }
    public MatrixD subRange(MatrixD sub_m, int row0, int col0) {
        int rowMax = row0 + sub_m.rows() - 1;
        int colMax = col0 + sub_m.cols() - 1;

        assert (isSubMatrixDimensionValid(this, row0, rowMax, col0, colMax));

        for(int sub_i = 0; sub_i < sub_m.rows(); sub_i++) {
            for(int sub_j = 0; sub_j < sub_m.cols(); sub_j++) {
                int i = row0 + sub_i;
                int j = col0 + sub_j;

                subFromCell(i, j, sub_m.getCell(sub_i,sub_j));
            }
        }

        return this;
    }
    public MatrixD multRange(double c, int row0, int rowMax, int col0, int colMax) {
        assert isSubMatrixDimensionValid(this, row0, rowMax, col0, colMax);

        for(int row = row0; row <= rowMax; row++) {
            for(int col = col0; col <= colMax; col++) {
                multiplyCell(row, col, c);
            }
        }

        return this;
    }
    public MatrixD divRange(double c, int row0, int rowMax, int col0, int colMax) {
        assert isSubMatrixDimensionValid(this, row0, rowMax, col0, colMax);

        return multRange(1.0f/c, row0, rowMax, col0, colMax);
    }

    public double getCell(int i, int j) {
        return x_ij[getIndex(i,j)];
    }
    public MatrixD setCell(int i, int j, double value) {
        x_ij[getIndex(i,j)] = value;
        return this;
    }
    public MatrixD swapCells(int rowA, int colA, int rowB, int colB) {
        double tempValue = getCell(rowA, colA);
        setCell(rowA, colA, getCell(rowB, colB));
        setCell(rowB, colB, tempValue);

        return this;
    }
    public MatrixD addToCell(int i, int j, double value) {
        x_ij[getIndex(i,j)] += value;

        return this;
    }
    public MatrixD subFromCell(int i, int j, double value) {
        x_ij[getIndex(i,j)] -= value;

        return this;
    }
    public MatrixD multiplyCell(int i, int j, double value) {
        x_ij[getIndex(i,j)] *= value;

        return this;
    }
    public int getIndex(int i, int j) {
        return i * nrOfCols + j;
    }

    public MatrixD getRow(int row) {
        return getRange(row, row, 0, cols() - 1);
    }
    public MatrixD setRow(MatrixD rowData, int row) {
        assert isRowDimensionValid(this, rowData);
        setRange(rowData, row , 0);

        return this;
    }
    public MatrixD addRow(MatrixD rowData, int row) {
        assert isRowDimensionValid(this, rowData);
        addRange(rowData, row, 0);

        return this;
    }
    public MatrixD subRow(MatrixD rowData, int row) {
        assert isRowDimensionValid(this, rowData);
        subRange(rowData, row, 0);

        return this;
    }
    public MatrixD multRow(int row, double c) {
        assert isRowIndexValid(this, row);
        multRange(c, row, row, 0 , cols() - 1);

        return this;
    }
    public MatrixD divRow(int row, double c) {
        assert isRowIndexValid(this, row);
        divRange(c, row, row, 0 , cols() - 1);

        return this;
    }
    public MatrixD swapRows(int rowA, int rowB) {
        isRowIndexValid(this, rowA);
        isRowIndexValid(this, rowB);

        for(int j = 0; j < cols(); j++) {
            swapCells(rowA, j, rowB, j);
        }

        return this;
    }

    public MatrixD getCol(int col) {
        return getRange(0, rows() - 1, col, col);
    }
    public MatrixD setCol(MatrixD colData, int col) {
        assert isColDimensionValid(this, colData);
        setRange(colData, 0, col);

        return this;
    }
    public MatrixD addCol(MatrixD colData, int col) {
        assert isColDimensionValid(this, colData);
        addRange(colData, 0, col);

        return this;
    }
    public MatrixD subCol(MatrixD colData, int col) {

        assert isColDimensionValid(this, colData);
        subRange(colData, 0,  col);

        return this;
    }
    public MatrixD multCol(int col, double c) {

        assert isColIndexValid(this, col);
        multRange(c, 0, rows() - 1, col, col);

        return this;
    }
    public MatrixD divCol(int col, double c) {

        assert isColIndexValid(this, col);
        divRange(c, 0, rows() - 1, col, col);

        return this;
    }
    public MatrixD swapCols(int colA, int colB) {
        isColIndexValid(this, colA);
        isColIndexValid(this, colB);

        for(int row = 0; row < rows(); row++) {
            swapCells(row, colA, row, colB);
        }

        return this;
    }

    public MatrixD add(MatrixD m_rhs) {
        assert isSameDimensions(this, m_rhs);

        for(int i = 0; i < count(); i++)
        {
            x_ij[i] += m_rhs.x_ij[i];
        }

        return this;
    }
    public MatrixD sub(MatrixD m_rhs) {
        assert isSameDimensions(this, m_rhs);

        for(int i = 0; i < count(); i++)
        {
            x_ij[i] -= m_rhs.x_ij[i];
        }

        return this;
    }
    public MatrixD mult(double c) {
        for(int i = 0; i < count(); i++)
        {
            x_ij[i] *= c;
        }
        return this;
    }
    public MatrixD div(double c) {
        assert (c != 0);

        this.mult(1.0f / c);
        return this;
    }
    public MatrixD preMult(MatrixD m_lhs) {
        MatrixD result = mult(m_lhs, this);

        this.x_ij = result.x_ij;
        this.nrOfCols = result.cols();
        this.nrOfRows = result.rows();

        return this;
    }
    public MatrixD postMult(MatrixD m_rhs) {
        MatrixD result = mult(this, m_rhs);

        this.x_ij = result.x_ij;
        this.nrOfCols = result.cols();
        this.nrOfRows = result.rows();

        return this;
    }
    public MatrixD transpose() {
        MatrixD transposed = transpose(this);

        this.nrOfRows = transposed.rows();
        this.nrOfCols = transposed.cols();
        this.x_ij = transposed.x_ij;

        return this;
    }

    public static MatrixD transpose(MatrixD m) {
        double[] transposedData = new double[m.count()];
        MatrixD transposed = new MatrixD(m.cols(), m.rows());

        for(int i = 0; i < transposed.rows(); i++) {
            for(int j = 0; j < transposed.cols(); j++) {
                transposed.setCell(i, j, m.getCell(j,i));
            }
        }

        return transposed;
    }
    public static MatrixD add(MatrixD m_lhs, MatrixD m_rhs) {
        assert isSameDimensions(m_lhs, m_rhs);

        MatrixD result = new MatrixD(m_lhs);
        for(int i = 0; i < m_lhs.count(); i++)
        {
            result.x_ij[i] = m_lhs.x_ij[i] + m_rhs.x_ij[i];
        }

        return result;
    }
    public static MatrixD sub(MatrixD m_lhs, MatrixD m_rhs) {
        assert isSameDimensions(m_lhs, m_rhs);

        MatrixD result = new MatrixD(m_lhs);
        for(int i = 0; i < m_lhs.count(); i++)
        {
            result.x_ij[i] = m_lhs.x_ij[i] - m_rhs.x_ij[i];
        }

        return result;
    }
    public static MatrixD mult(MatrixD m, double c) {
        MatrixD result = new MatrixD(m.rows(), m.cols());
        for(int i = 0; i < m.rows(); i++) {
            for(int j = 0; j < m.cols(); j++) {
                result.setCell(i, j, m.getCell(i, j) * c);
            }
        }

        return result;
    }
    public static MatrixD mult(MatrixD m_lhs, MatrixD m_rhs) {
        assert(isMultiplicationDimension(m_lhs, m_rhs));

        MatrixD result = new MatrixD(m_lhs.rows(), m_rhs.cols());
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
    public static MatrixD div(MatrixD m, double c) {
        assert  (c != 0.0f);

        MatrixD result = new MatrixD(m.rows(), m.cols());
        for(int i = 0; i < m.rows(); i++) {
            for(int j = 0; j < m.cols(); j++) {
                result.setCell(i, j, m.getCell(i, j) / c);
            }
        }

        return result;
    }

    public static boolean isSquareMatrix(MatrixD m) {
        return m.isSquare();
    }
    public static boolean isSameDimensions(MatrixD m_lhs, MatrixD m_rhs) {
        return (m_lhs.rows() == m_rhs.rows() &&
                m_lhs.cols() == m_rhs.cols());
    }
    public static boolean isMultiplicationDimension(MatrixD m_lhs, MatrixD m_rhs) {
        if(m_lhs.cols() != m_rhs.rows()) {
            return false;
        }
        return true;
    }
    public static boolean isRowIndexValid(MatrixD m, int row) {
        return (row >= 0) && (row < m.rows());
    }
    public static boolean isColIndexValid(MatrixD m, int col) {
        return (col >= 0) && (col < m.cols());
    }
    public static boolean isSubMatrixDimensionValid(MatrixD m, int iMin, int iMax, int jMin, int jMax) {
        boolean isVerticalSpanValid = iMin <= iMax;
        boolean isHorizontalSpanValid = jMin <= jMax;
        boolean isBoundValid = isBoundValid(m, iMin, iMax, jMin, jMax);

        return isVerticalSpanValid && isHorizontalSpanValid && isBoundValid;
    }
    public static boolean isBoundValid(MatrixD m, int iMin, int iMax, int jMin, int jMax) {
        boolean isIMinValid = isRowIndexValid(m, iMin);
        boolean isIMaxValid = isRowIndexValid(m, iMax);
        boolean isJMinValid = isColIndexValid(m, jMin);
        boolean isJMaxValid = isColIndexValid(m, jMax);

        return isIMinValid && isIMaxValid && isJMinValid && isJMaxValid;
    }
    public static boolean isRowDimensionValid(MatrixD m, MatrixD row) {
        boolean isSameNrOfCols = m.cols() == row.cols();
        boolean isOnlyOneRow = row.rows() == 1;

        return isSameNrOfCols && isOnlyOneRow;
    }
    public static boolean isColDimensionValid(MatrixD m, MatrixD col) {
        boolean isSameNrOfRows = m.rows() == col.rows();
        boolean isOnlyOneCol = col.cols() == 1;

        return isSameNrOfRows && isOnlyOneCol;
    }
    public static boolean isDataDimensionValid(@NotNull double[] x_ij, int rows, int cols) {
        return (x_ij.length == rows * cols);
    }

    public static MatrixD diagonal(int size, float value) {
        MatrixD m = new MatrixD(size, size);

        for(int i = 0; i < size; i++) {
            m.setCell(i,i, value);
        }

        return m;
    }
    public static MatrixD diagonal(VectorF diagData) {
        MatrixD m = new MatrixD(diagData.count(), diagData.count());

        for(int i = 0; i < m.rows(); i++) {
            m.setCell(i,i, diagData.getX_(i));
        }

        return m;
    }
    public static MatrixD identity(int size) {
        return diagonal(size, 1.0f);
    }
    public static MatrixD random(int rows, int cols) {
        MatrixD m = new MatrixD(rows, cols);
        for(int i = 0; i < m.count(); i++)
        {
            m.x_ij[i] = (float) Math.random();
        }

        return m;
    }
    public static MatrixD random(int rows, int cols, float minValue, float maxValue) {
        MatrixD m = new MatrixD(rows, cols);
        for(int i = 0; i < m.count(); i++)
        {
            m.x_ij[i] = MathExtra.map((float) Math.random(), 0, 1, minValue, maxValue);
        }

        return m;
    }

    @Override public String toString() {
        StringBuffer buffer = new StringBuffer();

        for(int i = 0; i < nrOfRows; i++)
        {
            buffer.append("[");
            for(int j = 0; j < nrOfCols - 1; j++)
            {
                buffer.append(getCell(i,j));
                buffer.append(", ");
            }

            buffer.append(getCell(i, nrOfCols - 1));
            buffer.append("] \n");
        }

        return buffer.toString();
    }
    public double[] toArray() {
        return Arrays.copyOf(x_ij, x_ij.length);
    }
}
