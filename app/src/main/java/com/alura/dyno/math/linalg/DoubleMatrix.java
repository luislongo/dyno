package com.alura.dyno.math.linalg;

import org.jetbrains.annotations.NotNull;

import cern.colt.matrix.tdouble.DoubleMatrix1D;
import cern.colt.matrix.tdouble.DoubleMatrix2D;
import cern.colt.matrix.tdouble.algo.DenseDoubleAlgebra;
import cern.colt.matrix.tdouble.impl.DenseDoubleMatrix1D;
import cern.colt.matrix.tdouble.impl.DenseDoubleMatrix2D;

import static cern.jet.math.tdouble.DoubleFunctions.div;
import static cern.jet.math.tdouble.DoubleFunctions.minus;
import static cern.jet.math.tdouble.DoubleFunctions.mult;
import static cern.jet.math.tdouble.DoubleFunctions.plus;

public class DoubleMatrix {
    protected DenseDoubleMatrix2D data;

    protected DoubleMatrix(DenseDoubleMatrix2D data) {
        this.data = data;
    }
    public DoubleMatrix(int rows, int cols) {
        data = new DenseDoubleMatrix2D(rows, cols);
        this.data = data;
    }
    public DoubleMatrix(int rows, int cols, double value) {
        this(rows, cols);
        data.assign(value);
    }
    public DoubleMatrix(int rows, int cols, double[] values) {
        this(rows, cols);
        data.assign(values);
    }
    public DoubleMatrix(@NotNull double[][] values) {
        this(values.length, values[0].length);
        data.assign(values);
    }
    public DoubleMatrix(@NotNull DoubleMatrix other) {
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
    public DoubleMatrix plus(@NotNull DoubleMatrix m_rhs) {
        data.assign(m_rhs.data, plus);
        return this;
    }
    public DoubleMatrix minus(@NotNull DoubleMatrix m_rhs) {
        data.assign(m_rhs.data, minus);
        return this;
    }
    public DoubleMatrix divide(double c) {
        checkDivisionByZero(c);

        data.assign(div(c));
        return this;
    }
    public DoubleMatrix multiply(double value) {
        data.assign(mult(value));
        return this;
    }
    public DoubleMatrix preMultiply(@NotNull DoubleMatrix m_lhs) {
        DenseDoubleAlgebra fun = Algebra.denseDoubleAlgebra();
        DenseDoubleMatrix2D result = (DenseDoubleMatrix2D) fun.mult(m_lhs.data, this.data);
        data = result;

        return this;
    }
    public DoubleMatrix postMultiply(@NotNull DoubleMatrix m_rhs) {
        DenseDoubleAlgebra fun = Algebra.denseDoubleAlgebra();
        DenseDoubleMatrix2D result = (DenseDoubleMatrix2D) fun.mult(this.data, m_rhs.data);
        data = result;

        return this;
    }
    public DoubleMatrix invert() {
        DenseDoubleAlgebra fun = Algebra.denseDoubleAlgebra();
        data.assign(fun.inverse(this.data));

        return this;
    }
    public DoubleMatrix transpose() {
        DenseDoubleAlgebra fun = Algebra.denseDoubleAlgebra();
        DenseDoubleMatrix2D result = (DenseDoubleMatrix2D) fun.transpose(this.data);
        data = result;

        return this;
    }

    // Static matrix operations (returns new Matrix)
    @NotNull


    // Getters
    public double getCell(int row, int col) {
        return data.get(row, col);
    }
    public DoubleMatrix getRow(int row) {
        return new DoubleMatrix(1, cols(), data.viewRow(row).toArray());
    }
    public DoubleMatrix getColumn(int column) {
        return new DoubleMatrix(rows(), 1, data.viewColumn(column).toArray());
    }
    public DoubleMatrix getRange(int rowA, int colA, int rowB, int colB) {
        int height = rowB - rowA + 1;
        int width = colB - colA + 1;

        if(height <= 0 || width <= 0) {
            throw new IllegalArgumentException("rowA > rowB || colA > colB");
        }

        return new DoubleMatrix(data.viewPart(rowA, colA, height, width).toArray());
    }

    // Setters
    public DoubleMatrix setCell(int row, int col, double c) {
        data.set(row, col, c);
        return this;
    }
    public DoubleMatrix setRow(int row, @NotNull DoubleMatrix rowData) {
        this.setRow(row, rowData.toArray());
        return this;
    }
    public DoubleMatrix setRow(int row, double[] rowData) {
        this.data.viewRow(row).assign(rowData);
        return this;
    }
    public DoubleMatrix setColumn(int column, @NotNull DoubleMatrix columnData) {
        this.setColumn(column, columnData.toArray());
        return this;
    }
    public DoubleMatrix setColumn(int column, double[] columnData) {
        this.data.viewColumn(column).assign(columnData);
        return this;
    }
    public DoubleMatrix setRange(int rowA, int colA, int rowB, int colB, double[][] rangeData) {
        this.data.viewPart(rowA, colA, rowB, colB).assign(rangeData);
        return this;
    }
    public DoubleMatrix setRange(int rowA, int colA, int rowB, int colB, DoubleMatrix rangeData) {
        int height = rowB - rowA + 1;
        int width = colB - colA + 1;

        if(height <= 0 || width <= 0) {
            throw new IllegalArgumentException("rowA > rowB || colA > colB");
        }

        this.setRange(rowA, colA, height, width, rangeData.toArray2D());
        return this;
    }

    // Plus operations on submatrix
    public DoubleMatrix plusCell(int row, int col, double c) {
        data.set(row, col, data.get(row, col) + c);
        return this;
    }
    public DoubleMatrix plusRow(int row, double[] rowData) {
        this.plusRow(row, new DenseDoubleMatrix1D(rowData));
        return this;
    }
    public DoubleMatrix plusRow(int row, @NotNull DoubleMatrix rowData) {
        this.plusRow(row, rowData.data.viewRow(0));
        return this;
    }
    private DoubleMatrix plusRow(int row, DoubleMatrix1D rowData) {
        this.data.viewRow(row).assign(rowData, plus);
        return this;
    }
    public DoubleMatrix plusColumn(int column, double[] colData) {
        this.plusColumn(column, new DenseDoubleMatrix1D(colData));
        return this;
    }
    public DoubleMatrix plusColumn(int column, @NotNull DoubleMatrix colData) {
        this.plusColumn(column, colData.data.viewRow(0));
        return this;
    }
    public DoubleMatrix plusColumn(int column, DoubleMatrix1D colData) {
        this.data.viewColumn(column).assign(colData, plus);
        return this;
    }
    public DoubleMatrix plusRange(int rowA, int colA, int rowB, int colB, double[][] rangeData) {
        this.plusRange(rowA, colA, rowB, colB, new DenseDoubleMatrix2D(rangeData));
        return this;
    }
    public DoubleMatrix plusRange(int rowA, int colA, int rowB, int colB, @NotNull DoubleMatrix rangeData) {
        this.plusRange(rowA, colA, rowB, colB, rangeData.toArray2D());
        return this;
    }
    private DoubleMatrix plusRange(int rowA, int colA, int rowB, int colB, DoubleMatrix2D rangeData) {
        int height = rowB - rowA + 1;
        int width = colB - colA + 1;

        if(height <= 0 || width <= 0) {
            throw new IllegalArgumentException("rowA > rowB || colA > colB");
        }

        this.data.viewPart(rowA, colA, height, width).assign(rangeData, plus);
        return this;
    }

    // Minus operations on submatrix
    public DoubleMatrix minusCell(int row, int col, double c) {
        data.set(row, col, data.get(row, col) - c);
        return this;
    }
    public DoubleMatrix minusRow(int row, double[] rowData) {
        this.minusRow(row, new DenseDoubleMatrix1D(rowData));
        return this;
    }
    public DoubleMatrix minusRow(int row, @NotNull DoubleMatrix rowData) {
        this.minusRow(row, rowData.data.viewRow(0));
        return this;
    }
    private DoubleMatrix minusRow(int row, DoubleMatrix1D rowData) {
        this.data.viewRow(row).assign(rowData, minus);
        return this;
    }
    public DoubleMatrix minusColumn(int column,  double[] columnData) {
        this.minusColumn(column, new DenseDoubleMatrix1D(columnData));
        return this;
    }
    public DoubleMatrix minusColumn(int column, @NotNull DoubleMatrix columnData) {
        this.minusColumn(column, columnData.data.viewRow(0));
        return this;
    }
    private DoubleMatrix minusColumn(int column, DoubleMatrix1D columnData) {
        this.data.viewColumn(column).assign(columnData, minus);
        return this;
    }
    public DoubleMatrix minusRange(int rowA, int colA, int rowB, int colB, double[][] rangeData) {
        this.minusRange(rowA, colA, rowB, colB, new DenseDoubleMatrix2D(rangeData));
        return this;
    }
    public DoubleMatrix minusRange(int rowA, int colA, int rowB, int colB, @NotNull DoubleMatrix rangeData) {
        this.minusRange(rowA, colA, rowB, colB, rangeData.toArray2D());
        return this;
    }
    private DoubleMatrix minusRange(int rowA, int colA, int rowB, int colB, DenseDoubleMatrix2D rangeData) {
        int height = rowB - rowA + 1;
        int width = colB - colA + 1;

        if(height <= 0 || width <= 0) {
            throw new IllegalArgumentException("rowA > rowB || colA > colB");
        }

        this.data.viewPart(rowA, colA, height, width).assign(rangeData, minus);
        return this;
    }

    // Times operations on submatrix
    public DoubleMatrix multCell(int row, int col, double c) {
        data.set(row, col, data.get(row, col) * c);
        return this;
    }
    public DoubleMatrix multRow(int row, double c) {
        data.viewRow(row).assign(mult(c));
        return this;
    }
    public DoubleMatrix multColumn(int column, double c) {
        data.viewColumn(column).assign(mult(c));
        return this;
    }
    public DoubleMatrix multRange(int rowA, int colA, int rowB, int colB, double c) {
        int height = rowB - rowA + 1;
        int width = colB - colA + 1;

        if(height <= 0 || width <= 0) {
            throw new IllegalArgumentException("rowA > rowB || colA > colB");
        }

        data.viewPart(rowA, colA, height, width).assign(mult(c));
        return this;
    }

    // Divs operations on submatrix
    public DoubleMatrix divCell(int row, int col, double c) {
        data.set(row, col, data.get(row, col) / c);
        return this;
    }
    public DoubleMatrix divRow(int row, double c) {
        checkDivisionByZero(c);
        data.viewRow(row).assign(div(c));
        return this;
    }
    public DoubleMatrix divColumn(int column, double c) {
        checkDivisionByZero(c);
        data.viewColumn(column).assign(div(c));
        return this;
    }
    public DoubleMatrix divRange(int rowA, int colA, int rowB, int colB, double c) {
        checkDivisionByZero(c);

        int height = rowB - rowA + 1;
        int width = colB - colA + 1;

        if(height <= 0 || width <= 0) {
            throw new IllegalArgumentException("rowA > rowB || colA > colB");
        }

        data.viewPart(rowA, colA, height, width).assign(div(c));
        return this;
    }
    private static void checkDivisionByZero(double c) {
        if (c==0) {
            throw new IllegalArgumentException("Divisor can't be zero.");
        }
    }

    // Swap operations
    public DoubleMatrix swapRows(int i, int j) {
        DoubleMatrix1D rowI = data.viewRow(i);
        DoubleMatrix1D rowJ = data.viewRow(j);
        rowI.swap(rowJ);

        return this;
    }
    public DoubleMatrix swapColumns(int i, int j) {
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

    @Override public DoubleMatrix clone() {
        return new DoubleMatrix(this);
    }

}