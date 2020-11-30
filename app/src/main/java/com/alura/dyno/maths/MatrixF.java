package com.alura.dyno.maths;

import org.jetbrains.annotations.NotNull;

import java.util.Arrays;

public class MatrixF {
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
        assert isDataDimensionValid(x_ij, nrOfRows, nrOfCols);

        this.nrOfRows = nrOfRows;
        this.nrOfCols = nrOfCols;
        this.x_ij = Arrays.copyOf(x_ij, x_ij.length);
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

    public float getCell(int i, int j) {
        return x_ij[getIndex(i,j)];
    }
    public void setCell(int i, int j, float value) {
        x_ij[getIndex(i,j)] = value;
    }
    public void swapCells(int rowA, int colA, int rowB, int colB) {
        float tempValue = getCell(rowA, colA);
        setCell(rowA, colA, getCell(rowB, colB));
        setCell(rowB, colB, tempValue);
    }
    public void addToCell(int i, int j, float value) {
        x_ij[getIndex(i,j)] += value;
    }
    public void subFromCell(int i, int j, float value) {
        x_ij[getIndex(i,j)] -= value;
    }
    public void multiplyCell(int i, int j, float value) {
        x_ij[getIndex(i,j)] *= value;
    }

    public MatrixF transpose() {
        MMath.transpose(this);

        int temp = nrOfRows;
        nrOfRows = nrOfCols;
        nrOfCols = temp;

        return this;
    }
    public MatrixF addTo(MatrixF m_rhs) {
        MMath.addTo(this, m_rhs);
        return this;
    }
    public MatrixF subtractFrom(MatrixF m_rhs) {
        MMath.subFrom(this, m_rhs);
        return this;
    }
    public MatrixF premultiply(MatrixF m_lhs) {
        MatrixF result = MMath.multiply(m_lhs, this);

        this.x_ij = result.x_ij;
        this.nrOfCols = result.cols();
        this.nrOfRows = result.rows();

        return this;
    }
    public MatrixF postMultiply(MatrixF m_rhs) {
        MatrixF result = MMath.multiply(this, m_rhs);

        this.x_ij = result.x_ij;
        this.nrOfCols = result.cols();
        this.nrOfRows = result.rows();

        return this;
    }
    public MatrixF multiply(float c) {
        MMath.multiply(this, c);
        return this;
    }

    public MatrixF getRange(int row0, int rowMax, int col0, int colMax) {
        return MMath.getRange(this, row0, rowMax, col0, colMax);
    }
    public MatrixF setRange(MatrixF sub_m, int row0, int col0) {
        MMath.setRange(this, sub_m, row0, col0);
        return this;
    }
    public MatrixF addRangeToMatrix(@NotNull MatrixF sub_m, int row0, int col0) {
        MMath.addToRange(this, sub_m, row0, col0);
        return this;
    }
    public MatrixF subtractFromRange(@NotNull MatrixF sub_m, int row0, int col0) {
        MMath.subFromRange(this, sub_m, row0, col0);
        return this;
    }
    public MatrixF multiplyRange(float c, int row0, int rowMax, int col0, int colMax) {
        MMath.multiplyRange(this, c, row0, rowMax, col0, colMax);
        return this;
    }
    public MatrixF divideRange(float c, int row0, int rowMax, int col0, int colMax) {
        MMath.divideRange(this, c, row0, rowMax, col0, colMax);
        return this;
    }

    public MatrixF getRow(int row)
    {
        return MMath.getRange(this, row, row, 0, cols());
    }
    public MatrixF setRow(@NotNull MatrixF rowData, int row) {
        MMath.setRange(this, rowData, row, 0);
        return this;
    }
    public MatrixF addToRow(@NotNull MatrixF rowData, int row) {
        MMath.addToRow(this, rowData, row);
        return this;
    }
    public MatrixF subtractFromRow( @NotNull MatrixF rowData, int row) {
        MMath.subFromRow(this, rowData, row);
        return this;
    }
    public MatrixF multiplyRow(int row, float c) {
        MMath.multiplyRow(this, row, c);
        return this;
    }
    public MatrixF swapLines(int rowA, int rowB) {
        MMath.swapLines(this, rowA, rowB);
        return this;
    }

    public MatrixF getCol(int col)
    {
        return MMath.getCol(this, col);
    }
    public MatrixF setCol(@NotNull MatrixF colData, int col) {
        MMath.setCol(this, colData, col);
        return this;
    }
    public MatrixF addToCol(@NotNull MatrixF colData, int col) {
        MMath.addToCol(this, colData, col);
        return this;
    }
    public MatrixF subFromCol(@NotNull MatrixF colData, int col) {
        MMath.subFromCol(this, colData, col);
        return this;
    }
    public MatrixF multiplyCol(int col, float c) {
        MMath.multiplyCol(this, col, c);
        return this;
    }
    public MatrixF getDiagonal() {
        return MMath.getDiagonal(this);
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

    private int getIndex(int i, int j) {
        return i * nrOfCols + j;
    }
    private static boolean isDataDimensionValid(@NotNull float[] x_ij, int rows, int cols) {
        return (x_ij.length == rows * cols);
    }

}
