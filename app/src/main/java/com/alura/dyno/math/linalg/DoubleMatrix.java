package com.alura.dyno.math.linalg;

import org.jetbrains.annotations.NotNull;

import java.util.Random;

import cern.colt.matrix.tdouble.DoubleMatrix1D;
import cern.colt.matrix.tdouble.DoubleMatrix2D;
import cern.colt.matrix.tdouble.DoubleMatrix2DProcedure;
import cern.colt.matrix.tdouble.algo.DenseDoubleAlgebra;
import cern.colt.matrix.tdouble.algo.decomposition.DenseDoubleEigenvalueDecomposition;
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
    public DoubleMatrix(int rows, int cols, double... values) {
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
    public DoubleMatrix(AbstractDoubleVector... rows) {
        this(rows.length, rows[0].length());

        for(int rowIndex = 0; rowIndex < rows.length; rowIndex++) {
            if(rows[rowIndex].length() != cols()) {
                throw new IllegalArgumentException("All vectors should be the same length");
            } else {
                setRow(rowIndex, rows[rowIndex]);
            }
        }
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
    public double det() {
        DenseDoubleAlgebra fun = Algebra.denseDoubleAlgebra();
        return fun.det(this.data);
    }

    // Static matrix operations
    public static DoubleMatrix plus(@NotNull DoubleMatrix m_lhs,@NotNull DoubleMatrix m_rhs) {
        return m_lhs.clone().plus(m_rhs);
    }
    public static DoubleMatrix minus(@NotNull DoubleMatrix m_lhs,@NotNull DoubleMatrix m_rhs) {
        return m_lhs.clone().minus(m_rhs);
    }
    public static DoubleMatrix divide(@NotNull DoubleMatrix m, double c) {
        checkDivisionByZero(c);
        return m.clone().divide(c);
    }
    public static DoubleMatrix multiply(@NotNull DoubleMatrix m, double c) {
        return m.clone().multiply(c);
    }
    public static DoubleMatrix multiply(@NotNull DoubleMatrix m_lhs,@NotNull DoubleMatrix m_rhs) {
        return m_lhs.clone().postMultiply(m_rhs);
    }
    public static DoubleMatrix invert(@NotNull DoubleMatrix m) {
        return m.clone().invert();
    }
    public static DoubleMatrix transpose(@NotNull DoubleMatrix m) {
        return m.clone().transpose();
    }

    // Getters
    public double getCell(int row, int col) {
        return data.get(row, col);
    }
    public DoubleVectorN getRow(int row) {
        return new DoubleVectorN(data.viewRow(row).toArray());
    }
    public DoubleVectorN getColumn(int column) {
        return new DoubleVectorN(data.viewColumn(column).toArray());
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
    public DoubleMatrix setRow(int row, @NotNull AbstractDoubleVector rowData) {
        this.data.viewRow(row).assign(rowData.data);
        return this;
    }
    public DoubleMatrix setRow(int row, double... rowData) {
        this.setRow(row, new DoubleVectorN(rowData));
        return this;
    }
    public DoubleMatrix setRow(int row, double rowData) {
        this.setRow(row, new DoubleVectorN(this.cols(), rowData));
        return this;
    }
    public DoubleMatrix setColumn(int column, @NotNull AbstractDoubleVector columnData) {
        this.data.viewColumn(column).assign(columnData.data);
        return this;
    }
    public DoubleMatrix setColumn(int column, double... columnData) {
        this.setColumn(column, new DoubleVectorN(columnData));
        return this;
    }
    public DoubleMatrix setColumn(int column, double columnData) {
        this.setColumn(column, new DoubleVectorN(rows(), columnData));
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
    public DoubleMatrix setRange(int rowA, int colA, int rowB, int colB, AbstractDoubleVector rangeData) {
        int height = rowB - rowA + 1;
        int width = colB - colA + 1;

        if(height <= 0 || width <= 0) {
            throw new IllegalArgumentException("rowA > rowB || colA > colB");
        }

        this.setRange(rowA, colA, height, width, new double[][]{rangeData.toArray()});
        return this;
    }

    // Plus operations on submatrix
    public DoubleMatrix plusCell(int row, int col, double c) {
        data.set(row, col, data.get(row, col) + c);
        return this;
    }
    public DoubleMatrix plusRow(int row, double... rowData) {
        this.plusRow(row, new DoubleVectorN(rowData));
        return this;
    }
    public DoubleMatrix plusRow(int row, AbstractDoubleVector rowData) {
        this.data.viewRow(row).assign(rowData.data, plus);
        return this;
    }
    public DoubleMatrix plusColumn(int column, double... colData) {
        this.plusColumn(column, new DoubleVectorN(colData));
        return this;
    }
    public DoubleMatrix plusColumn(int column, AbstractDoubleVector colData) {
        this.data.viewColumn(column).assign(colData.data, plus);
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
    public DoubleMatrix minusRow(int row, double... rowData) {
        this.minusRow(row, new DoubleVectorN(rowData));
        return this;
    }
    public DoubleMatrix minusRow(int row, AbstractDoubleVector rowData) {
        this.data.viewRow(row).assign(rowData.data, minus);
        return this;
    }
    public DoubleMatrix minusColumn(int column,  double... columnData) {
        this.minusColumn(column, new DoubleVectorN(columnData));
        return this;
    }
    private DoubleMatrix minusColumn(int column, AbstractDoubleVector columnData) {
        this.data.viewColumn(column).assign(columnData.data, minus);
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

    public DenseDoubleEigenvalueDecomposition getEigenValues() {
        return new DenseDoubleEigenvalueDecomposition(this.data);
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
    public static DoubleMatrix random(int rows, int cols) {
        DoubleMatrix m = new DoubleMatrix(rows, cols);

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                m.data.setQuick(row, col, (new Random()).nextDouble());
            }
        }

        return m;
    }
    public static DoubleMatrix identity(int size) {
        DoubleMatrix m = new DoubleMatrix(size, size);

        for(int i = 0; i < size; i++) {
            m.data.setQuick(i, i,1.0d);
        }

        return m;
    }

}