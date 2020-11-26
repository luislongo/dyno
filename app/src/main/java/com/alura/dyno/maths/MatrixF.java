package com.alura.dyno.maths;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicBoolean;

public class MatrixF {
    private int nrOfRows;
    private int nrOfCols;
    protected float[] x_ij;

    public MatrixF(MatrixF m) {
        nrOfRows = m.nrOfRows;
        nrOfCols = m.nrOfCols;

        x_ij = Arrays.copyOf(m.x_ij, m.arrayLength());
    }
    public MatrixF(int nrOfRows, int nrOfCols) {
        this(new float[nrOfCols*nrOfRows], nrOfRows, nrOfCols);
    }
    public MatrixF(float[] x_ij, int nrOfRows, int nrOfCols) {
        checkForInvalidDataDimensions(x_ij, nrOfRows, nrOfCols);

        this.nrOfRows = nrOfRows;
        this.nrOfCols = nrOfCols;
        this.x_ij = Arrays.copyOf(x_ij, x_ij.length);
    }

    private int getIndex(int i, int j)
    {
        return i * nrOfCols + j;
    }
    private int arrayLength()
    {
        return x_ij.length;
    }
    public boolean isSquare()
    {
        return (nrOfRows == nrOfCols);
    }

    public float getX_(int i, int j)
    {
        return x_ij[getIndex(i,j)];
    }
    public MatrixF getLine(int i)
    {
        return getSubMatrix(i, i, 0, nrOfCols - 1);
    }
    public Vector getColumnVector(int j) {
        checkForInvalidColumnIndex(j);

        return new Vector(getSubMatrix(0, nrOfRows - 1, j, j).x_ij);
    }
    public void setX_(int i, int j, float value) {
        x_ij[getIndex(i,j)] = value;
    }
    public void setLine(int i, MatrixF line) {
        checkForInvalidLineIndex(i);
        checkForCorrectLineDimensions(line);

        for(int j = 0; j < nrOfCols; j++)
        {
            setX_(i, j, line.getX_(0, j));
        }
    }
    public void swapX_(int rowA, int colA, int rowB, int colB) {
        float tempValue = getX_(rowA, colA);
        setX_(rowA, colA, getX_(rowB, colB));
        setX_(rowB, colB, tempValue);
    }
    public void swapLines(int rowA, int rowB) {
        checkForInvalidLineIndex(rowA);
        checkForInvalidLineIndex(rowB);

        for(int j = 0; j < nrOfCols; j++)
        {
            swapX_(rowA, j, rowB, j);
        }
    }
    public int getNrOfRows() {
        return nrOfRows;
    }
    public int getNrOfCols() {
        return nrOfCols;
    }

    public void addToLine(int i, int value) {
        for(int j = 0; j < nrOfCols; j++)
        {
            addToX_(i, j, value);
        }
    }
    public void addToX_(int i, int j, float value) {
        x_ij[getIndex(i,j)] += value;
    }

    public void subtractFromX_(int i, int j, float value) {
        x_ij[getIndex(i,j)] -= value;
    }
    public void subtractFromLine(int i, int value) {
        for(int j = 0; j < nrOfCols; j++)
        {
            subtractFromX_(i, j, value);
        }
    }
    public void subtractFromLine(int i, MatrixF line) {
        checkForCorrectLineDimensions(line);

        for(int j = 0; j < nrOfCols; j++)
        {
            subtractFromX_(i, j, line.getX_(0, j));
        }
    }

    public void multiplyX_(int i, int j, float value) {
        x_ij[getIndex(i,j)] -= value;
    }
    public void multiplyLine(int i, float value) {
        for(int j = 0; j < nrOfCols; j++) {
            multiplyX_(i, j, value);
        }
    }
    public void multiplyColumn(int j , float value) {
        for(int i = 0; i < nrOfRows; i++) {
            multiplyX_(i, j, value);
        }
    }
    public void preMultiply(MatrixF m_rhs)
    {
        this.x_ij = multiplyMatrices(this, m_rhs).x_ij;
    }
    public void postMultiply(MatrixF m_lhs)
    {
        this.x_ij = multiplyMatrices(m_lhs,this).x_ij;
    }
    public static MatrixF multiplyMatrices(MatrixF m_lhs, MatrixF m_rhs) {
        checkForInvalidMultiplicationDimensions(m_lhs, m_rhs);

        MatrixF result = new MatrixF(m_lhs.nrOfRows, m_rhs.nrOfCols);

        for(int i = 0; i < m_lhs.nrOfRows; i++) {
            for(int j = 0; j < m_rhs.nrOfCols; j++) {
                for(int k = 0; k < m_lhs.nrOfCols; k++) {
                    float mult = m_lhs.getIndex(i, k) * m_rhs.getIndex(k, j);
                    result.addToX_(i, j, mult);
                }
            }
        }

        return result;
    }

    public MatrixF getSubMatrix(int iMin, int iMax, int jMin, int jMax) {
        checkForInvalidSubmatrixDimensions(iMin, iMax, jMin, jMax);

        int subMatrixNrOfRows = iMax - iMin + 1;
        int subMatrixNrOfCols = jMax - jMin + 1;

        float[] subMatrixArray = new float[subMatrixNrOfRows * subMatrixNrOfRows];

        int subMatrixI = 0;
        int subMatrixJ = 0;
        for(int i = iMin; i <= iMax; i++)
        {
            for(int j = iMin; j <= jMax; j++)
            {
                subMatrixArray[subMatrixI * subMatrixNrOfCols + subMatrixJ] = getX_(i, j);

                subMatrixJ += 1;
            }

            subMatrixI += 1;
        }

        return new MatrixF(subMatrixArray, subMatrixNrOfRows, subMatrixNrOfCols);
    }

    private void checkForInvalidLineIndex(int i) {
        if(i < 0 || i >= nrOfRows)
        {
            throw new RuntimeException("ERROR::MATRIX::INVALID I INDEX");
        }
    }
    private void checkForInvalidColumnIndex(int j) {
        if(j < 0 || j >= nrOfCols)
        {
            throw new RuntimeException("ERROR::MATRIX::INVALID J INDEX");
        }
    }
    private void checkForInvalidDataDimensions(float[] x_ij, int rows, int cols) {
        if(x_ij.length != rows * cols)
        {
            throw new RuntimeException("ERROR::MATRIX::INVALID DATA DIMENSION");
        }
    }
    private void checkForSameDimensions(MatrixF m_rhs) {
        if(nrOfRows != m_rhs.nrOfRows || nrOfCols != m_rhs.nrOfCols)
        {
            throw new RuntimeException("ERROR::MATRIX::NUMBER OF LINES AND NUMBER OF COLUMNS MUST BE THE SAME");
        }
    }
    private void checkForInvalidSubmatrixDimensions(int iMin, int iMax, int jMin, int jMax) {
        if(iMin > iMax)
        {
            throw new RuntimeException("ERROR::MATRIX::IMIN > IMAX");
        }
        if(jMin > jMax)
        {
            throw new RuntimeException("ERROR::MATRIX::JMIN > JMAX");
        }

        checkForInvalidLineIndex(iMin);
        checkForInvalidLineIndex(iMax);
        checkForInvalidColumnIndex(jMin);
        checkForInvalidColumnIndex(jMax);
    }
    private void checkForCorrectLineDimensions(MatrixF line) {
        if(line.nrOfCols != nrOfCols)
        {
            throw new RuntimeException("ERROR::MATRIX::LINE MUST HAVE THE SAME NUMBER OF COLS");
        }
        if(line.nrOfRows > 1)
        {
            throw new RuntimeException("ERROR::MATRIX::LINE MUST HAVE ONE ROW");
        }
    }
    private static void checkForInvalidMultiplicationDimensions(MatrixF m_rhs, MatrixF m_lhs) {
        if(m_lhs.nrOfCols != m_rhs.nrOfRows)
        {
            throw new RuntimeException("ERROR::MATRIX::INVALID MULTIPLICATION DIMENSIONS");
        }
    }

    public void transpose() {
        float[] newArray = new float[arrayLength()];
        int newNrOfCols = nrOfRows;
        int newNrOfRows = nrOfCols;

        for(int i = 0; i < nrOfRows; i++)
        {
            for(int j = 0; j < nrOfCols; j++)
            {
                newArray[j*newNrOfCols + i] = getX_(i,j);
            }
        }

        this.x_ij = Arrays.copyOf(newArray, arrayLength());
        this.nrOfCols = newNrOfCols;
        this.nrOfRows = newNrOfRows;
    }
    public void plus(MatrixF m_rhs) {
        checkForSameDimensions(m_rhs);
        for(int i = 0; i < nrOfRows; i++)
        {
            for(int j = 0; j < nrOfCols; j++)
            {

            }
        }
    }

    public float[] toArray()
    {
        return Arrays.copyOf(x_ij, x_ij.length);
    }
    @Override public String toString() {
        StringBuffer buffer = new StringBuffer();

        for(int i = 0; i < nrOfRows; i++)
        {
            buffer.append("[");
            for(int j = 0; j < nrOfCols - 1; j++)
            {
                buffer.append(getX_(i,j));
                buffer.append(", ");
            }

            buffer.append(getX_(i, nrOfCols - 1));
            buffer.append("] \n");
        }

        return buffer.toString();
    }


}
