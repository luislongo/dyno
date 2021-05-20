package com.alura.dyno.math.linalg;

import org.jetbrains.annotations.NotNull;

import java.util.stream.DoubleStream;

import cern.colt.matrix.tdouble.DoubleMatrix1D;
import cern.colt.matrix.tdouble.DoubleMatrix2D;
import cern.colt.matrix.tdouble.algo.DenseDoubleAlgebra;
import cern.colt.matrix.tdouble.impl.DenseDoubleMatrix1D;
import cern.colt.matrix.tdouble.impl.DenseDoubleMatrix2D;

import static cern.jet.math.tdouble.DoubleFunctions.div;
import static cern.jet.math.tdouble.DoubleFunctions.minus;
import static cern.jet.math.tdouble.DoubleFunctions.mult;
import static cern.jet.math.tdouble.DoubleFunctions.plus;

public class DenseDoubleMatrix {
    protected DenseDoubleMatrix2D data;

    protected DenseDoubleMatrix(DenseDoubleMatrix2D data) {
        this.data = data;
    }
    public DenseDoubleMatrix(int rows, int cols) {
        data = new DenseDoubleMatrix2D(rows, cols);
        this.data = data;
    }
    public DenseDoubleMatrix(int rows, int cols, double value) {
        this(rows, cols);
        data.assign(value);
    }
    public DenseDoubleMatrix(int rows, int cols, double[] values) {
        this(rows, cols);
        data.assign(values);
    }
    public DenseDoubleMatrix(@NotNull double[][] values) {
        this(values.length, values[0].length);
        data.assign(values);
    }
    public DenseDoubleMatrix(@NotNull DenseDoubleMatrix other) {
        this(other.rows(), other.cols());
        this.data.assign(other.data);
    }

    public int rows() {
        return data.rows();
    }
    public int cols() {
        return data.columns();
    }

    // Matrix operations
    public DenseDoubleMatrix plus(@NotNull DenseDoubleMatrix m_rhs) {
        data.assign(m_rhs.data, plus);
        return this;
    }
    public DenseDoubleMatrix minus(@NotNull DenseDoubleMatrix m_rhs) {
        data.assign(m_rhs.data, minus);
        return this;
    }
    public DenseDoubleMatrix divide(double c) {
        checkDivisionByZero(c);

        data.assign(div(c));
        return this;
    }
    public DenseDoubleMatrix multiply(double value) {
        data.assign(mult(value));
        return this;
    }
    public DenseDoubleMatrix preMultiply(@NotNull DenseDoubleMatrix m_lhs) {
        DenseDoubleAlgebra fun = Algebra.denseDoubleAlgebra();
        DenseDoubleMatrix2D result = (DenseDoubleMatrix2D) fun.mult(m_lhs.data, this.data);
        data = result;

        return this;
    }
    public DenseDoubleMatrix postMultiply(@NotNull DenseDoubleMatrix m_rhs) {
        DenseDoubleAlgebra fun = Algebra.denseDoubleAlgebra();
        DenseDoubleMatrix2D result = (DenseDoubleMatrix2D) fun.mult(this.data, m_rhs.data);
        data = result;

        return this;
    }
    public DenseDoubleMatrix invert() {
        DenseDoubleAlgebra fun = Algebra.denseDoubleAlgebra();
        data.assign(fun.inverse(this.data));

        return this;
    }
    public DenseDoubleMatrix transpose() {
        DenseDoubleAlgebra fun = Algebra.denseDoubleAlgebra();
        DenseDoubleMatrix2D result = (DenseDoubleMatrix2D) fun.transpose(this.data);
        data = result;

        return this;
    }

    // Getters
    public double getCell(int row, int col) {
        return data.get(row, col);
    }
    public DenseDoubleMatrix getRow(int row) {
        return new DenseDoubleMatrix(1, cols(), data.viewRow(row).toArray());
    }
    public DenseDoubleMatrix getColumn(int column) {
        return new DenseDoubleMatrix(rows(), 1, data.viewColumn(column).toArray());
    }
    public DenseDoubleMatrix getRange(int rowA, int colA, int rowB, int colB) {
        int height = rowB - rowA + 1;
        int width = colB - colA + 1;

        if(height <= 0 || width <= 0) {
            throw new IllegalArgumentException("rowA > rowB || colA > colB");
        }

        return new DenseDoubleMatrix(data.viewPart(rowA, colA, height, width).toArray());
    }

    // Setters
    public void setCell(int row, int col, double c) {
        data.set(row, col, c);
    }
    public void setRow(int row, @NotNull DenseDoubleMatrix rowData) {
        this.setRow(row, rowData.toArray());
    }
    public void setRow(int row, double[] rowData) {
        this.data.viewRow(row).assign(rowData);
    }
    public void setColumn(int column, @NotNull DenseDoubleMatrix columnData) {
        this.setColumn(column, columnData.toArray());
    }
    public void setColumn(int column, double[] columnData) {
        this.data.viewColumn(column).assign(columnData);
    }
    public void setRange(int rowA, int colA, int rowB, int colB, double[][] rangeData) {
        this.data.viewPart(rowA, colA, rowB, colB).assign(rangeData);
    }
    public void setRange(int rowA, int colA, int rowB, int colB, DenseDoubleMatrix rangeData) {
        int height = rowB - rowA + 1;
        int width = colB - colA + 1;

        if(height <= 0 || width <= 0) {
            throw new IllegalArgumentException("rowA > rowB || colA > colB");
        }

        this.setRange(rowA, colA, height, width, rangeData.toArray2D());
    }

    // Plus operations on submatrix
    public void plusCell(int row, int col, double c) {
        data.set(row, col, data.get(row, col) + c);
    }
    public void plusRow(int row, double[] rowData) {
        this.plusRow(row, new DenseDoubleMatrix1D(rowData));
    }
    public void plusRow(int row, @NotNull DenseDoubleMatrix rowData) {
        this.plusRow(row, rowData.data.viewRow(0));
    }
    private void plusRow(int row, DoubleMatrix1D rowData) {
        this.data.viewRow(row).assign(rowData, plus);
    }
    public void plusColumn(int column, double[] colData) {
        this.plusColumn(column, new DenseDoubleMatrix1D(colData));
    }
    public void plusColumn(int column, @NotNull DenseDoubleMatrix colData) {
        this.plusColumn(column, colData.data.viewRow(0));
    }
    public void plusColumn(int column, DoubleMatrix1D colData) {
        this.data.viewColumn(column).assign(colData, plus);
    }
    public void plusRange(int rowA, int colA, int rowB, int colB, double[][] rangeData) {
        this.plusRange(rowA, colA, rowB, colB, new DenseDoubleMatrix2D(rangeData));
    }
    public void plusRange(int rowA, int colA, int rowB, int colB, @NotNull DenseDoubleMatrix rangeData) {
        this.plusRange(rowA, colA, rowB, colB, rangeData.toArray2D());
    }
    private void plusRange(int rowA, int colA, int rowB, int colB, DoubleMatrix2D rangeData) {
        int height = rowB - rowA + 1;
        int width = colB - colA + 1;

        if(height <= 0 || width <= 0) {
            throw new IllegalArgumentException("rowA > rowB || colA > colB");
        }

        this.data.viewPart(rowA, colA, height, width).assign(rangeData, plus);
    }

    // Minus operations on submatrix
    public void minusCell(int row, int col, double c) {
        data.set(row, col, data.get(row, col) - c);
    }
    public void minusRow(int row, double[] rowData) {
        this.minusRow(row, new DenseDoubleMatrix1D(rowData));
    }
    public void minusRow(int row, @NotNull DenseDoubleMatrix rowData) {
        this.minusRow(row, rowData.data.viewRow(0));
    }
    private void minusRow(int row, DoubleMatrix1D rowData) {
        this.data.viewRow(row).assign(rowData, minus);
    }
    public void minusColumn(int column,  double[] columnData) {
        this.minusColumn(column, new DenseDoubleMatrix1D(columnData));
    }
    public void minusColumn(int column, @NotNull DenseDoubleMatrix columnData) {
        this.minusColumn(column, columnData.data.viewRow(0));
    }
    private void minusColumn(int column, DoubleMatrix1D columnData) {
        this.data.viewColumn(column).assign(columnData, minus);
    }
    public void minusRange(int rowA, int colA, int rowB, int colB, double[][] rangeData) {
        this.minusRange(rowA, colA, rowB, colB, new DenseDoubleMatrix2D(rangeData));
    }
    public void minusRange(int rowA, int colA, int rowB, int colB, @NotNull DenseDoubleMatrix rangeData) {
        this.minusRange(rowA, colA, rowB, colB, rangeData.toArray2D());
    }
    private void minusRange(int rowA, int colA, int rowB, int colB, DenseDoubleMatrix2D rangeData) {
        int height = rowB - rowA + 1;
        int width = colB - colA + 1;

        if(height <= 0 || width <= 0) {
            throw new IllegalArgumentException("rowA > rowB || colA > colB");
        }

        this.data.viewPart(rowA, colA, height, width).assign(rangeData, minus);
    }

    // Times operations on submatrix
    public void multCell(int row, int col, double c) {
        data.set(row, col, data.get(row, col) * c);
    }
    public void multRow(int row, double c) {
        data.viewRow(row).assign(mult(c));
    }
    public void multColumn(int column, double c) {
        data.viewColumn(column).assign(mult(c));
    }
    public void multRange(int rowA, int colA, int rowB, int colB, double c) {
        int height = rowB - rowA + 1;
        int width = colB - colA + 1;

        if(height <= 0 || width <= 0) {
            throw new IllegalArgumentException("rowA > rowB || colA > colB");
        }

        data.viewPart(rowA, colA, height, width).assign(mult(c));
    }

    // Divs operations on submatrix
    public void divCell(int row, int col, double c) {
        data.set(row, col, data.get(row, col) / c);
    }
    public void divRow(int row, double c) {
        checkDivisionByZero(c);
        data.viewRow(row).assign(div(c));
    }
    public void divColumn(int column, double c) {
        checkDivisionByZero(c);
        data.viewColumn(column).assign(div(c));
    }
    public void divRange(int rowA, int colA, int rowB, int colB, double c) {
        checkDivisionByZero(c);

        int height = rowB - rowA + 1;
        int width = colB - colA + 1;

        if(height <= 0 || width <= 0) {
            throw new IllegalArgumentException("rowA > rowB || colA > colB");
        }

        data.viewPart(rowA, colA, height, width).assign(div(c));
    }
    private void checkDivisionByZero(double c) {
        if (c==0) {
            throw new IllegalArgumentException("Divisor can't be zero.");
        }
    }

    // Swap operations
    public DenseDoubleMatrix swapRows(int i, int j) {
        DoubleMatrix1D rowI = data.viewRow(i);
        DoubleMatrix1D rowJ = data.viewRow(j);
        rowI.swap(rowJ);

        return this;
    }
    public DenseDoubleMatrix swapColumns(int i, int j) {
        DoubleMatrix1D colI = data.viewColumn(i);
        DoubleMatrix1D colJ = data.viewColumn(j);
        colI.swap(colJ);

        return this;
    }

    public double[] toArray() {
        return this.clone().transpose().data.vectorize().toArray();
    }
    public double[][] toArray2D() {
        return data.toArray();
    }

    @Override public DenseDoubleMatrix clone() {
        return new DenseDoubleMatrix(this);
    }

}