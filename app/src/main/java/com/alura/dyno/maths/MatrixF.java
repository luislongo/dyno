package com.alura.dyno.maths;

import org.jetbrains.annotations.NotNull;

import java.util.Arrays;

public class MatrixF<T extends MatrixF> {
    private int nrOfRows;
    private int nrOfCols;
    protected float[] x_ij;

    public MatrixF(MatrixF m) {
        nrOfRows = m.nrOfRows;
        nrOfCols = m.nrOfCols;

        x_ij = Arrays.copyOf(m.x_ij, m.count());
    }
    public MatrixF(int nrOfRows, int nrOfCols) {
        this(nrOfRows, nrOfCols, new float[nrOfCols*nrOfRows]);
    }
    public MatrixF(int nrOfRows, int nrOfCols, float value) {
        this(nrOfRows, nrOfCols);
        Arrays.fill(x_ij, value);
    }
    public MatrixF(int nrOfRows, int nrOfCols, float[] x_ij) {
        if(!MatrixDimensionChecker.isDataDimensionValid(x_ij, nrOfRows, nrOfCols)) {
            throw new RuntimeException();
        }

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

    public T getDiagonal() {
        if(!MatrixDimensionChecker.isSquareMatrix(this)) {
            throw new RuntimeException();
        };

        for(int dgn = 0; dgn < cols(); dgn++)
        {
            diagonal.x_ij[dgn] = getCell(dgn, dgn);
        }

        return diagonal;
    }

    public T getRange(int row0, int rowMax, int col0, int colMax) {
        assert (isSubMatrixDimensionValid(this, row0, rowMax, col0, colMax));

        int subRows = rowMax - row0 + 1;
        int subCols = colMax - col0 + 1;
        MatrixF sub = new MatrixF(subRows, subCols);

        for(int subRow = 0; subRow < sub.rows(); subRow++) {
            for(int subCol = 0; subCol < sub.cols(); subCol++) {
                int row = row0 + subRow;
                int col = col0 + subCol;

                sub.setCell(subRow, subCol, getCell(row, col));
            }
        }

        return sub;
    }
    public T setRange(MatrixF sub_m, int row0, int col0) {
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
    public T addRange(MatrixF sub_m, int row0, int col0) {
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
    public T subRange(MatrixF sub_m, int row0, int col0) {
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
    public T multRange(float c, int row0, int rowMax, int col0, int colMax) {
        assert isSubMatrixDimensionValid(this, row0, rowMax, col0, colMax);

        for(int row = row0; row <= rowMax; row++) {
            for(int col = col0; col <= colMax; col++) {
                multiplyCell(row, col, c);
            }
        }

        return this;
    }
    public T divRange(float c, int row0, int rowMax, int col0, int colMax) {
        assert isSubMatrixDimensionValid(this, row0, rowMax, col0, colMax);

        return multRange(1.0f/c, row0, rowMax, col0, colMax);
    }

    public float getCell(int i, int j) {
        return x_ij[getIndex(i,j)];
    }
    public T setCell(int i, int j, float value) {
        x_ij[getIndex(i,j)] = value;
        return this;
    }
    public T swapCells(int rowA, int colA, int rowB, int colB) {
        float tempValue = getCell(rowA, colA);
        setCell(rowA, colA, getCell(rowB, colB));
        setCell(rowB, colB, tempValue);

        return this;
    }
    public T addToCell(int i, int j, float value) {
        x_ij[getIndex(i,j)] += value;

        return this;
    }
    public T subFromCell(int i, int j, float value) {
        x_ij[getIndex(i,j)] -= value;

        return this;
    }
    public T multiplyCell(int i, int j, float value) {
        x_ij[getIndex(i,j)] *= value;

        return this;
    }
    public int getIndex(int i, int j) {
        return i * nrOfCols + j;
    }

    public T getRow(int row) {
        return getRange(row, row, 0, cols() - 1);
    }
    public T setRow(MatrixF rowData, int row) {
        assert MatrixF.isRowDimensionValid(this, rowData);
        setRange(rowData, row , 0);

        return this;
    }
    public T addRow(MatrixF rowData, int row) {
        assert isRowDimensionValid(this, rowData);
        addRange(rowData, row, 0);

        return this;
    }
    public T subRow(MatrixF rowData, int row) {
        assert isRowDimensionValid(this, rowData);
        subRange(rowData, row, 0);

        return this;
    }
    public T multRow(int row, float c) {
        assert isRowIndexValid(this, row);
        multRange(c, row, row, 0 , cols() - 1);

        return this;
    }
    public T divRow(int row, float c) {
        assert isRowIndexValid(this, row);
        divRange(c, row, row, 0 , cols() - 1);

        return this;
    }
    public T swapRows(int rowA, int rowB) {
        isRowIndexValid(this, rowA);
        isRowIndexValid(this, rowB);

        for(int j = 0; j < cols(); j++) {
            swapCells(rowA, j, rowB, j);
        }

        return this;
    }

    public T getCol(int col) {
        return getRange(0, rows() - 1, col, col);
    }
    public T setCol(MatrixF colData, int col) {
        assert isColDimensionValid(this, colData);
        setRange(colData, 0, col);

        return this;
    }
    public T addCol(MatrixF colData, int col) {
        assert isColDimensionValid(this, colData);
        addRange(colData, 0, col);

        return this;
    }
    public T subCol(MatrixF colData, int col) {

        assert isColDimensionValid(this, colData);
        subRange(colData, 0,  col);

        return this;
    }
    public T multCol(int col, float c) {

        assert isColIndexValid(this, col);
        multRange(c, 0, rows() - 1, col, col);

        return this;
    }
    public T divCol(int col, float c) {

        assert isColIndexValid(this, col);
        divRange(c, 0, rows() - 1, col, col);

        return this;
    }
    public T swapCols(int colA, int colB) {
        isColIndexValid(this, colA);
        isColIndexValid(this, colB);

        for(int row = 0; row < rows(); row++) {
            swapCells(row, colA, row, colB);
        }

        return this;
    }

    public T add(MatrixF m_rhs) {
        assert isSameDimensions(this, m_rhs);

        for(int i = 0; i < count(); i++)
        {
            x_ij[i] += m_rhs.x_ij[i];
        }

        return this;
    }
    public T sub(MatrixF m_rhs) {
        assert isSameDimensions(this, m_rhs);

        for(int i = 0; i < count(); i++)
        {
            x_ij[i] -= m_rhs.x_ij[i];
        }

        return this;
    }
    public T mult(float c) {
        for(int i = 0; i < count(); i++)
        {
            x_ij[i] *= c;
        }
        return this;
    }
    public T div(float c) {
        assert (c != 0);

        this.mult(1.0f / c);
        return this;
    }
    public T preMult(MatrixF m_lhs) {
        MatrixF result = mult(m_lhs, this);

        this.x_ij = result.x_ij;
        this.nrOfCols = result.cols();
        this.nrOfRows = result.rows();

        return this;
    }
    public T postMult(MatrixF m_rhs) {
        MatrixF result = MMath.mult(this, m_rhs);

        this.x_ij = result.x_ij;
        this.nrOfCols = result.cols();
        this.nrOfRows = result.rows();

        return this;
    }
    public T transpose() {
        MatrixF transposed = MMath.transpose(this);

        this.nrOfRows = transposed.rows();
        this.nrOfCols = transposed.cols();
        this.x_ij = transposed.x_ij;

        return this;
    }

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

    public static MatrixF diagonal(int size, float value) {
        MatrixF m = new MatrixF(size, size);

        for(int i = 0; i < size; i++) {
            m.setCell(i,i, value);
        }

        return m;
    }
    public static MatrixF diagonal(VectorF diagData) {
        MatrixF m = new MatrixF(diagData.count(), diagData.count());

        for(int i = 0; i < m.rows(); i++) {
            m.setCell(i,i, diagData.getX_(i));
        }

        return m;
    }
    public static MatrixF identity(int size) {
        return diagonal(size, 1.0f);
    }
    public static MatrixF random(int rows, int cols) {
        MatrixF m = new MatrixF(rows, cols);
        for(int i = 0; i < m.count(); i++)
        {
            m.x_ij[i] = (float) Math.random();
        }

        return m;
    }
    public static MatrixF random(int rows, int cols, float minValue, float maxValue) {
        MatrixF m = new MatrixF(rows, cols);
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
    public float[] toArray() {
        return Arrays.copyOf(x_ij, x_ij.length);
    }

}
