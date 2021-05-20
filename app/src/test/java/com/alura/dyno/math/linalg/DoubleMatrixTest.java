package com.alura.dyno.math.linalg;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.*;

class DoubleMatrixTest {
    private static final double DELTA = 0.00000000000001d;

    // Test rows and columns
    @Test void testRowsAnsCols_1() {
        DenseDoubleMatrix m = new DenseDoubleMatrix(3,4);

        assertEquals(3, m.rows());
        assertEquals(4, m.cols());
    }
    @Test void testRowsAnsCols_2() {
        DenseDoubleMatrix m = new DenseDoubleMatrix(2,1);

        assertEquals(2, m.rows());
        assertEquals(1, m.cols());
    }

    //Test get methods
    @Test void testGetRow_1() {
        double[] rowA = new double[]{0, 3, 4, 5};
        double[] rowB = new double[]{1, 3, 2, 7};
        double[] rowC = new double[]{4, 7, 3, 2};
        double[] rowD = new double[]{5, 6, 5, 3};

        double[][] values = new double[][]{rowA, rowB, rowC, rowD};
        DenseDoubleMatrix m = new DenseDoubleMatrix(values);

        assertArrayEquals(rowA, m.getRow(0).toArray(), DELTA);
        assertArrayEquals(rowB, m.getRow(1).toArray(), DELTA);
        assertArrayEquals(rowC, m.getRow(2).toArray(), DELTA);
        assertArrayEquals(rowD, m.getRow(3).toArray(), DELTA);
    }
    @Test void testGetRow_2() {
        DenseDoubleMatrix m = Algebra.denseMatrixFactory().random(4, 5);

        assertThrows(IndexOutOfBoundsException.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                m.getRow(-1);
            }
        });
        assertThrows(IndexOutOfBoundsException.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                m.getRow(5);
            }
        });
    }
    @Test void testGetCol_1() {
        double[] colA = new double[]{0, 3, 4, 5};
        double[] colB = new double[]{1, 3, 2, 7};
        double[] colC = new double[]{4, 7, 3, 2};
        double[] colD = new double[]{5, 6, 5, 3};

        double[][] values = new double[][]{colA, colB, colC, colD};
        DenseDoubleMatrix m = new DenseDoubleMatrix(values);
        m.transpose();

        assertArrayEquals(colA, m.getColumn(0).toArray(), DELTA);
        assertArrayEquals(colB, m.getColumn(1).toArray(), DELTA);
        assertArrayEquals(colC, m.getColumn(2).toArray(), DELTA);
        assertArrayEquals(colD, m.getColumn(3).toArray(), DELTA);
    }
    @Test void testGetCol_2() {
        DenseDoubleMatrix m = Algebra.denseMatrixFactory().random(4, 5);

        assertThrows(IndexOutOfBoundsException.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                m.getColumn(-1);
            }
        });
        assertThrows(IndexOutOfBoundsException.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                m.getColumn(6);
            }
        });
    }
    @Test void testGetRange_1() {
        double[][] values = new double[][]{
                {0, 3, 4, 5},
                {1, 3, 2, 7},
                {4, 7, 3, 2},
                {5, 6, 5, 3}};
        DenseDoubleMatrix m = new DenseDoubleMatrix(values);

        double[][] expectedA = new double[][]{
                {0, 3},
                {1, 3}
        };
        assertArrayEquals(expectedA, m.getRange(0,0,1,1).toArray2D());
    }
    @Test void testGetRange_2() {
        double[][] values = new double[][]{
                {0, 3, 4, 5},
                {1, 3, 2, 7},
                {4, 7, 3, 2},
                {5, 6, 5, 3}};
        DenseDoubleMatrix m = new DenseDoubleMatrix(values);

        double[][] expectedB = new double[][]{
                {0, 3, 4},
                {1, 3, 2}
        };
        assertArrayEquals(expectedB, m.getRange(0,0,1,2).toArray2D());
    }
    @Test void testGetRange_3() {
        double[][] values = new double[][]{
                {0, 3, 4, 5},
                {1, 3, 2, 7},
                {4, 7, 3, 2},
                {5, 6, 5, 3}};
        DenseDoubleMatrix m = new DenseDoubleMatrix(values);

        double[][] expectedC = new double[][]{
                {0, 3},
                {1, 3},
                {4, 7}
        };
        assertArrayEquals(expectedC, m.getRange(0,0,2,1).toArray2D());
    }
    @Test void testGetRange_4() {
        double[][] values = new double[][]{
                {0, 3, 4, 5},
                {1, 3, 2, 7},
                {4, 7, 3, 2},
                {5, 6, 5, 3}};
        DenseDoubleMatrix m = new DenseDoubleMatrix(values);

        double[][] expectedD = new double[][]{
                {3, 2},
                {7, 3}
        };
        assertArrayEquals(expectedD, m.getRange(1,1,2,2).toArray2D());
    }
    @Test void testGetRange_5() {
        double[][] values = new double[][]{
                {0, 3, 4, 5},
                {1, 3, 2, 7},
                {4, 7, 3, 2},
                {5, 6, 5, 3}};
        DenseDoubleMatrix m = new DenseDoubleMatrix(values);

        double[][] expectedE = new double[][]{
                {3, 2, 7},
                {7, 3, 2},
                {6, 5, 3},
        };
        assertArrayEquals(expectedE, m.getRange(1,1,3,3).toArray2D());
    }
    @Test void testGetRange_6() {
        double[][] values = new double[][]{
                {0, 3, 4, 5},
                {1, 3, 2, 7},
                {4, 7, 3, 2},
                {5, 6, 5, 3}};
        DenseDoubleMatrix m = new DenseDoubleMatrix(values);

        assertThrows(IndexOutOfBoundsException.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                m.getRange(-1, 0, 1, 1);
            }
        });
    }
    @Test void testGetRange_7() {
        double[][] values = new double[][]{
                {0, 3, 4, 5},
                {1, 3, 2, 7},
                {4, 7, 3, 2},
                {5, 6, 5, 3}};
        DenseDoubleMatrix m = new DenseDoubleMatrix(values);

        assertThrows(IndexOutOfBoundsException.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                m.getRange(0, -1, 1, 1);
            }
        });
    }
    @Test void testGetRange_8() {
        double[][] values = new double[][]{
                {0, 3, 4, 5},
                {1, 3, 2, 7},
                {4, 7, 3, 2},
                {5, 6, 5, 3}};
        DenseDoubleMatrix m = new DenseDoubleMatrix(values);

        assertThrows(IndexOutOfBoundsException.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                m.getRange(0, 0, 4, 1);
            }
        });
    }
    @Test void testGetRange_9() {
        double[][] values = new double[][]{
                {0, 3, 4, 5},
                {1, 3, 2, 7},
                {4, 7, 3, 2},
                {5, 6, 5, 3}};
        DenseDoubleMatrix m = new DenseDoubleMatrix(values);

        assertThrows(IndexOutOfBoundsException.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                m.getRange(0, 0, 1, 4);
            }
        });
    }
    @Test void testGetRange_10() {
        double[][] values = new double[][]{
                {0, 3, 4, 5},
                {1, 3, 2, 7},
                {4, 7, 3, 2},
                {5, 6, 5, 3}};
        DenseDoubleMatrix m = new DenseDoubleMatrix(values);

        assertThrows(IllegalArgumentException.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                m.getRange(1, 0, 0, 3);
            }
        });
    }
    @Test void testGetRange_11() {
        double[][] values = new double[][]{
                {0, 3, 4, 5},
                {1, 3, 2, 7},
                {4, 7, 3, 2},
                {5, 6, 5, 3}};
        DenseDoubleMatrix m = new DenseDoubleMatrix(values);

        assertThrows(IllegalArgumentException.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                m.getRange(0, 2, 1, 1);
            }
        });
    }

    //Test set methods
    @Test void testSetRow_1() {
        double[] rowA = new double[]{0, 3, 4, 5};
        double[] rowB = new double[]{1, 3, 2, 7};
        double[] rowC = new double[]{4, 7, 3, 2};
        double[] rowD = new double[]{5, 6, 5, 3};
        double[] rowS = new double[]{0, 0, 1, 0};

        double[][] values = new double[][]{rowA, rowB, rowC, rowD};
        DenseDoubleMatrix m = new DenseDoubleMatrix(values);
        m.setRow(2, rowS);

        assertArrayEquals(rowA, m.getRow(0).toArray(), DELTA);
        assertArrayEquals(rowB, m.getRow(1).toArray(), DELTA);
        assertArrayEquals(rowS, m.getRow(2).toArray(), DELTA);
        assertArrayEquals(rowD, m.getRow(3).toArray(), DELTA);
    }
    @Test void testSetRow_2() {
        DenseDoubleMatrix m = Algebra.denseMatrixFactory().random(4, 5);
        double[] row = new double[5];

        assertThrows(IndexOutOfBoundsException.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                m.setRow(-1,row);
            }
        });
        assertThrows(IndexOutOfBoundsException.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                m.setRow(4,row);
            }
        });
    }
    @Test void testSetRow_3() {
        DenseDoubleMatrix m = Algebra.denseMatrixFactory().random(4, 5);

        double[] rowA = new double[6];
        double[] rowB = new double[4];

        assertThrows(IllegalArgumentException.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                m.setRow(0, rowA);
            }
        });
        assertThrows(IllegalArgumentException.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                m.setRow(0, rowB);
            }
        });
    }
    @Test void testSetCol_1() {
        double[] colA = new double[]{0, 3, 4, 5};
        double[] colB = new double[]{1, 3, 2, 7};
        double[] colC = new double[]{4, 7, 3, 2};
        double[] colD = new double[]{5, 6, 5, 3};
        double[] colS = new double[]{0, 0, 1, 0};

        double[][] values = new double[][]{colA, colB, colC, colD};
        DenseDoubleMatrix m = new DenseDoubleMatrix(values);
        m.transpose();
        m.setColumn(2, colS);

        assertArrayEquals(colA, m.getColumn(0).toArray(), DELTA);
        assertArrayEquals(colB, m.getColumn(1).toArray(), DELTA);
        assertArrayEquals(colS, m.getColumn(2).toArray(), DELTA);
        assertArrayEquals(colD, m.getColumn(3).toArray(), DELTA);
    }
    @Test void testSetCol_2() {
        DenseDoubleMatrix m = Algebra.denseMatrixFactory().random(4, 5);
        double[] colA = new double[5];

        assertThrows(IndexOutOfBoundsException.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                m.setColumn(-1, colA);
            }
        });
        assertThrows(IndexOutOfBoundsException.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                m.setColumn(6, colA);
            }
        });
    }
    @Test void testSetCol_3() {
        DenseDoubleMatrix m = Algebra.denseMatrixFactory().random(4, 5);

        double[] colA = new double[3];
        double[] colB = new double[5];

        assertThrows(IllegalArgumentException.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                m.setColumn(0, colA);
            }
        });
        assertThrows(IllegalArgumentException.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                m.setColumn(0, colB);
            }
        });
    }
    @Test void testSetRange_1() {
        DenseDoubleMatrix m = new DenseDoubleMatrix(new double[][]{
                {0, 3, 4, 5},
                {1, 3, 2, 7},
                {4, 7, 3, 2},
                {5, 6, 5, 3}});
        double[][] rangeS = new double[][]{
                {0, 1},
                {1, 0}};
        m.setRange(1, 1, 2, 2, rangeS);

        double[] expectedRowA = new double[] {0, 3, 4, 5};
        double[] expectedRowB = new double[] {1, 0, 1, 7};
        double[] expectedRowC = new double[] {4, 1, 0, 2};
        double[] expectedRowD = new double[] {5, 6, 5, 3};

        assertArrayEquals(expectedRowA, m.getRow(0).toArray(), DELTA);
        assertArrayEquals(expectedRowB, m.getRow(1).toArray(), DELTA);
        assertArrayEquals(expectedRowC, m.getRow(2).toArray(), DELTA);
        assertArrayEquals(expectedRowD, m.getRow(3).toArray(), DELTA);
    }
    @Test void testSetRange_2() {
        DenseDoubleMatrix m = Algebra.denseMatrixFactory().random(4, 4);
        double[][] rangeA = new double[3][3];

        assertThrows(IndexOutOfBoundsException.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                m.setRange(-1, 0, 1,2, rangeA);
            }
        });
        assertThrows(IndexOutOfBoundsException.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                m.setRange(0, -1, 2,1, rangeA);
            }
        });
        assertThrows(IndexOutOfBoundsException.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                m.setRange(2, 0, 4,2, rangeA);
            }
        });
        assertThrows(IndexOutOfBoundsException.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                m.setRange(0, 2, 2,4, rangeA);
            }
        });
    }
    @Test void testSetRange_3() {
        DenseDoubleMatrix m = Algebra.denseMatrixFactory().random(4, 4);
        double[][] rangeA = new double[3][3];

        assertThrows(IllegalArgumentException.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                m.setRange(2, 0, 0,2, rangeA);
            }
        });
        assertThrows(IllegalArgumentException.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                m.setRange(0, 2, 2,0, rangeA);
            }
        });
    }
    @Test void testSetRange_4() {
        DenseDoubleMatrix m = Algebra.denseMatrixFactory().random(4, 4);

        double[][] range = new double[2][2];

        assertThrows(IllegalArgumentException.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                m.setRange(0, 0, 2,1, range);
            }
        });
        assertThrows(IllegalArgumentException.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                m.setRange(0, 0, 1,2, range);
            }
        });
    }
    @Test void testSetRange_5() {
        DenseDoubleMatrix m = Algebra.denseMatrixFactory().random(4, 4);

        double[][] range = new double[3][3];

        assertThrows(IllegalArgumentException.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                m.setRange(0, 0, 1,2, range);
            }
        });
        assertThrows(IllegalArgumentException.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                m.setRange(0, 0, 2,1, range);
            }
        });
    }

    // Test addition
    @Test void testPlus_1() {
        DenseDoubleMatrix m_lhs = new DenseDoubleMatrix(new double[][]{
                { 1,  2,  3,  4},
                {-1, -2, -3, -4},
                { 4,  3,  2,  1},
                {-4, -3, -2, -1}});

        DenseDoubleMatrix m_rhs = new DenseDoubleMatrix(new double[][]{
                { 1,  2,  3,  4},
                {-1, -2, -3, -4},
                { 4,  3,  2,  1},
                {-4, -3, -2, -1}});

        DenseDoubleMatrix expected = new DenseDoubleMatrix(new double[][] {
                {  2,  4,  6,  8},
                { -2, -4, -6, -8},
                {  8,  6,  4,  2},
                { -8, -6, -4, -2}});
        DenseDoubleMatrix actual = m_lhs.clone().plus(m_rhs);
        assertArrayEquals(expected.data.toArray(), actual.data.toArray());
    }
    @Test void testPlus_2() {
        DenseDoubleMatrix m_A = new DenseDoubleMatrix(new double[][]{
                { 1,  2,  3,  4},
                {-1, -2, -3, -4},
                { 4,  3,  2,  1},
                {-4, -3, -2, -1}});

        DenseDoubleMatrix m_B = new DenseDoubleMatrix(new double[][]{
                { 1,  2,  3,  4},
                {-1, -2, -3, -4},
                { 4,  3,  2,  1}});

        DenseDoubleMatrix m_C = new DenseDoubleMatrix(new double[][]{
                { 1,  2,  3},
                {-1, -2, -3},
                { 4,  3,  2},
                {-4, -3, -2}});


        assertThrows(IllegalArgumentException.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                m_A.plus(m_B);
            }
        });
        assertThrows(IllegalArgumentException.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                m_A.plus(m_C);
            }
        });
    }
    @Test void testPlusRow_1() {
        double[] rowA = new double[]{0, 3, 4, 5};
        double[] rowB = new double[]{1, 3, 2, 7};
        double[] rowC = new double[]{4, 7, 3, 2};
        double[] rowD = new double[]{5, 6, 5, 3};
        double[] rowS = new double[]{2, -1, 1, -2};
        double[] expectedRowSum = new double[]{6, 6, 4, 0};

        double[][] values = new double[][]{rowA, rowB, rowC, rowD};
        DenseDoubleMatrix m = new DenseDoubleMatrix(values);
        m.plusRow(2, rowS);

        assertArrayEquals(rowA, m.getRow(0).toArray(), DELTA);
        assertArrayEquals(rowB, m.getRow(1).toArray(), DELTA);
        assertArrayEquals(expectedRowSum, m.getRow(2).toArray(), DELTA);
        assertArrayEquals(rowD, m.getRow(3).toArray(), DELTA);
    }
    @Test void testPlusRow_2() {
        DenseDoubleMatrix m = Algebra.denseMatrixFactory().random(4, 5);

        assertThrows(IndexOutOfBoundsException.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                m.getRow(-1);
            }
        });
        assertThrows(IndexOutOfBoundsException.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                m.getRow(5);
            }
        });
    }
    @Test void testPlusRow_3() {
        DenseDoubleMatrix m = Algebra.denseMatrixFactory().random(4, 5);

        double[] rowA = new double[6];
        double[] rowB = new double[4];

        assertThrows(IllegalArgumentException.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                m.plusRow(0, rowA);
            }
        });
        assertThrows(IllegalArgumentException.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                m.plusRow(0, rowB);
            }
        });
    }
    @Test void testPlusColumn_1() {
        double[] colA = new double[]{0, 3, 4, 5};
        double[] colB = new double[]{1, 3, 2, 7};
        double[] colC = new double[]{4, 7, 3, 2};
        double[] colD = new double[]{5, 6, 5, 3};
        double[] colS = new double[]{2, -1, 1, -2};
        double[] expectedColSum = new double[]{6, 6, 4, 0};

        double[][] values = new double[][]{colA, colB, colC, colD};
        DenseDoubleMatrix m = new DenseDoubleMatrix(values);
        m.transpose().plusColumn(2, colS);

        assertArrayEquals(colA, m.getColumn(0).toArray(), DELTA);
        assertArrayEquals(colB, m.getColumn(1).toArray(), DELTA);
        assertArrayEquals(expectedColSum, m.getColumn(2).toArray(), DELTA);
        assertArrayEquals(colD, m.getColumn(3).toArray(), DELTA);
    }
    @Test void testPlusColumn_2() {
        DenseDoubleMatrix m = Algebra.denseMatrixFactory().random(4, 5);
        double[] col = new double[4];

        assertThrows(IndexOutOfBoundsException.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                m.plusColumn(-1, col);
            }
        });
        assertThrows(IndexOutOfBoundsException.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                m.plusColumn(5, col);
            }
        });
    }
    @Test void testPlusColumn_3() {
        DenseDoubleMatrix m = Algebra.denseMatrixFactory().random(4, 5);

        double[] colA = new double[3];
        double[] colB = new double[5];

        assertThrows(IllegalArgumentException.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                m.plusColumn(0, colA);
            }
        });
        assertThrows(IllegalArgumentException.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                m.plusColumn(0, colB);
            }
        });
    }
    @Test void testPlusRange_1() {
        DenseDoubleMatrix m = new DenseDoubleMatrix(new double[][]{
                {0, 3, 4, 5},
                {1, 3, 2, 7},
                {4, 7, 3, 2},
                {5, 6, 5, 3}});
        double[][] rangeS = new double[][]{
                {-1,  1},
                { 1, -1}};
        m.plusRange(1, 1, 2, 2, rangeS);

        double[] expectedRowA = new double[] {0, 3, 4, 5};
        double[] expectedRowB = new double[] {1, 2, 3, 7};
        double[] expectedRowC = new double[] {4, 8, 2, 2};
        double[] expectedRowD = new double[] {5, 6, 5, 3};

        assertArrayEquals(expectedRowA, m.getRow(0).toArray(), DELTA);
        assertArrayEquals(expectedRowB, m.getRow(1).toArray(), DELTA);
        assertArrayEquals(expectedRowC, m.getRow(2).toArray(), DELTA);
        assertArrayEquals(expectedRowD, m.getRow(3).toArray(), DELTA);
    }
    @Test void testPlusRange_2() {
        DenseDoubleMatrix m = Algebra.denseMatrixFactory().random(4, 4);
        double[][] rangeA = new double[3][3];

        assertThrows(IndexOutOfBoundsException.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                m.plusRange(-1, 0, 1,2, rangeA);
            }
        });
        assertThrows(IndexOutOfBoundsException.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                m.plusRange(0, -1, 2,1, rangeA);
            }
        });
        assertThrows(IndexOutOfBoundsException.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                m.plusRange(2, 0, 4,2, rangeA);
            }
        });
        assertThrows(IndexOutOfBoundsException.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                m.plusRange(0, 2, 2,4, rangeA);
            }
        });
    }
    @Test void testPlusRange_3() {
        DenseDoubleMatrix m = Algebra.denseMatrixFactory().random(4, 4);
        double[][] rangeA = new double[3][3];

        assertThrows(IllegalArgumentException.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                m.plusRange(2, 0, 0,2, rangeA);
            }
        });
        assertThrows(IllegalArgumentException.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                m.plusRange(0, 2, 2,0, rangeA);
            }
        });
    }
    @Test void testPlusRange_4() {
        DenseDoubleMatrix m = Algebra.denseMatrixFactory().random(4, 4);

        double[][] range = new double[2][2];

        assertThrows(IllegalArgumentException.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                m.plusRange(0, 0, 2,1, range);
            }
        });
        assertThrows(IllegalArgumentException.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                m.plusRange(0, 0, 1,2, range);
            }
        });
    }
    @Test void testPlusRange_5() {
        DenseDoubleMatrix m = Algebra.denseMatrixFactory().random(4, 4);

        double[][] range = new double[3][3];

        assertThrows(IllegalArgumentException.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                m.plusRange(0, 0, 1,2, range);
            }
        });
        assertThrows(IllegalArgumentException.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                m.plusRange(0, 0, 2,1, range);
            }
        });
    }

    // Test subtraction
    @Test void testMinus_1() {
        DenseDoubleMatrix m_lhs = new DenseDoubleMatrix(new double[][]{
                { 1,  2,  3,  4},
                {-1, -2, -3, -4},
                { 4,  3,  2,  1},
                {-4, -3, -2, -1}});

        DenseDoubleMatrix m_rhs = new DenseDoubleMatrix(new double[][]{
                { 1,  2,  3,  4},
                {-1, -2, -3, -4},
                { 4,  3,  2,  1},
                {-4, -3, -2, -1}});

        DenseDoubleMatrix expected = new DenseDoubleMatrix(4, 4, 0);
        DenseDoubleMatrix actual = m_lhs.clone().minus(m_rhs);

        assertArrayEquals(expected.data.toArray(), actual.data.toArray());
    }
    @Test void testMinus_2() {
        DenseDoubleMatrix m_lhs = new DenseDoubleMatrix(new double[][]{
                { 1,  2,  3,  4},
                {-1, -2, -3, -4},
                { 4,  3,  2,  1},
                {-4, -3, -2, -1}});

        DenseDoubleMatrix m_rhs = new DenseDoubleMatrix(new double[][]{
                { 4,  3,  2,  1},
                {-4, -3, -2, -1},
                {-3, -2, -1,  0},
                {-1, -6, -3, -3}});

        DenseDoubleMatrix expected = new DenseDoubleMatrix(new double[][] {
                {-3, -1,  1,  3},
                { 3,  1, -1, -3},
                { 7,  5,  3,  1},
                {-3,  3,  1,  2}});
        DenseDoubleMatrix actual = m_lhs.clone().minus(m_rhs);
        assertArrayEquals(expected.data.toArray(), actual.data.toArray());
    }
    @Test void testMinus_3() {
        DenseDoubleMatrix m_A = new DenseDoubleMatrix(new double[][]{
                { 1,  2,  3,  4},
                {-1, -2, -3, -4},
                { 4,  3,  2,  1},
                {-4, -3, -2, -1}});

        DenseDoubleMatrix m_B = new DenseDoubleMatrix(new double[][]{
                { 1,  2,  3,  4},
                {-1, -2, -3, -4},
                { 4,  3,  2,  1}});

        DenseDoubleMatrix m_C = new DenseDoubleMatrix(new double[][]{
                { 1,  2,  3},
                {-1, -2, -3},
                { 4,  3,  2},
                {-4, -3, -2}});

        assertThrows(IllegalArgumentException.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                m_A.minus(m_B);
            }
        });
        assertThrows(IllegalArgumentException.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                m_A.minus(m_C);
            }
        });
    }
    @Test void testMinusRow_1() {
        double[] rowA = new double[]{0, 3, 4, 5};
        double[] rowB = new double[]{1, 3, 2, 7};
        double[] rowC = new double[]{4, 7, 3, 2};
        double[] rowD = new double[]{5, 6, 5, 3};
        double[] rowS = new double[]{2, -1, 1, -2};
        double[] expectedRowDiff = new double[]{2, 8, 2, 4};

        double[][] values = new double[][]{rowA, rowB, rowC, rowD};
        DenseDoubleMatrix m = new DenseDoubleMatrix(values);
        m.minusRow(2, rowS);

        assertArrayEquals(rowA, m.getRow(0).toArray(), DELTA);
        assertArrayEquals(rowB, m.getRow(1).toArray(), DELTA);
        assertArrayEquals(expectedRowDiff , m.getRow(2).toArray(), DELTA);
        assertArrayEquals(rowD, m.getRow(3).toArray(), DELTA);
    }
    @Test void testMinusRow_2() {
        DenseDoubleMatrix m = Algebra.denseMatrixFactory().random(4, 5);
        double[] row = new double[5];

        assertThrows(IndexOutOfBoundsException.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                m.minusRow(-1, row);
            }
        });
        assertThrows(IndexOutOfBoundsException.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                m.minusRow(5, row);
            }
        });
    }
    @Test void testMinusRow_3() {
        DenseDoubleMatrix m = Algebra.denseMatrixFactory().random(4, 5);

        double[] rowA = new double[6];
        double[] rowB = new double[4];

        assertThrows(IllegalArgumentException.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                m.minusRow(0, rowA);
            }
        });
        assertThrows(IllegalArgumentException.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                m.minusRow(0, rowB);
            }
        });
    }
    @Test void testMinusColumn_1() {
        double[] colA = new double[]{0, 3, 4, 5};
        double[] colB = new double[]{1, 3, 2, 7};
        double[] colC = new double[]{4, 7, 3, 2};
        double[] colD = new double[]{5, 6, 5, 3};
        double[] colS = new double[]{2, -1, 1, -2};
        double[] expectedColDiff = new double[]{2, 8, 2, 4};

        double[][] values = new double[][]{colA, colB, colC, colD};
        DenseDoubleMatrix m = new DenseDoubleMatrix(values);
        m.transpose().minusColumn(2, colS);

        assertArrayEquals(colA, m.getColumn(0).toArray(), DELTA);
        assertArrayEquals(colB, m.getColumn(1).toArray(), DELTA);
        assertArrayEquals(expectedColDiff, m.getColumn(2).toArray(), DELTA);
        assertArrayEquals(colD, m.getColumn(3).toArray(), DELTA);
    }
    @Test void testMinusColumn_2() {
        DenseDoubleMatrix m = Algebra.denseMatrixFactory().random(4, 5);
        double[] col = new double[4];

        assertThrows(IndexOutOfBoundsException.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                m.minusColumn(-1, col);
            }
        });
        assertThrows(IndexOutOfBoundsException.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                m.minusColumn(5, col);
            }
        });
    }
    @Test void testMinusColumn_3() {
        DenseDoubleMatrix m = Algebra.denseMatrixFactory().random(4, 5);

        double[] colA = new double[3];
        double[] colB = new double[5];

        assertThrows(IllegalArgumentException.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                m.minusColumn(0, colA);
            }
        });
        assertThrows(IllegalArgumentException.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                m.minusColumn(0, colB);
            }
        });
    }
    @Test void testMinusRange_1() {
        DenseDoubleMatrix m = new DenseDoubleMatrix(new double[][]{
                {0, 3, 4, 5},
                {1, 3, 2, 7},
                {4, 7, 3, 2},
                {5, 6, 5, 3}});
        double[][] rangeS = new double[][]{
                {-1,  1},
                { 1, -1}};
        m.minusRange(1, 1, 2, 2, rangeS);

        double[] expectedRowA = new double[] {0, 3, 4, 5};
        double[] expectedRowB = new double[] {1, 4, 1, 7};
        double[] expectedRowC = new double[] {4, 6, 4, 2};
        double[] expectedRowD = new double[] {5, 6, 5, 3};

        assertArrayEquals(expectedRowA, m.getRow(0).toArray(), DELTA);
        assertArrayEquals(expectedRowB, m.getRow(1).toArray(), DELTA);
        assertArrayEquals(expectedRowC, m.getRow(2).toArray(), DELTA);
        assertArrayEquals(expectedRowD, m.getRow(3).toArray(), DELTA);
    }
    @Test void testMinusRange_2() {
        DenseDoubleMatrix m = Algebra.denseMatrixFactory().random(4, 4);
        double[][] rangeA = new double[3][3];

        assertThrows(IndexOutOfBoundsException.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                m.minusRange(-1, 0, 1,2, rangeA);
            }
        });
        assertThrows(IndexOutOfBoundsException.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                m.minusRange(0, -1, 2,1, rangeA);
            }
        });
        assertThrows(IndexOutOfBoundsException.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                m.minusRange(2, 0, 4,2, rangeA);
            }
        });
        assertThrows(IndexOutOfBoundsException.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                m.minusRange(0, 2, 2,4, rangeA);
            }
        });
    }
    @Test void testMinusRange_3() {
        DenseDoubleMatrix m = Algebra.denseMatrixFactory().random(4, 4);
        double[][] rangeA = new double[3][3];

        assertThrows(IllegalArgumentException.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                m.minusRange(2, 0, 0,2, rangeA);
            }
        });
        assertThrows(IllegalArgumentException.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                m.minusRange(0, 2, 2,0, rangeA);
            }
        });
    }
    @Test void testMinusRange_4() {
        DenseDoubleMatrix m = Algebra.denseMatrixFactory().random(4, 4);

        double[][] range = new double[2][2];

        assertThrows(IllegalArgumentException.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                m.minusRange(0, 0, 2,1, range);
            }
        });
        assertThrows(IllegalArgumentException.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                m.minusRange(0, 0, 1,2, range);
            }
        });
    }
    @Test void testMinusRange_5() {
        DenseDoubleMatrix m = Algebra.denseMatrixFactory().random(4, 4);

        double[][] range = new double[3][3];

        assertThrows(IllegalArgumentException.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                m.minusRange(0, 0, 1,2, range);
            }
        });
        assertThrows(IllegalArgumentException.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                m.minusRange(0, 0, 2,1, range);
            }
        });
    }

    // Test division
    @Test void testDivide_1() {
        double c = 2.0d;
        DenseDoubleMatrix m_lhs  = new DenseDoubleMatrix(new double[][] {
                { 2,  4,  6,  8},
                {10, 12, 14, 16},
                {18, 20, 22, 24},
                {26, 28, 30, 32}
        });
        DenseDoubleMatrix expected = new DenseDoubleMatrix(new double[][]{
                {1, 2, 3, 4},
                {5, 6, 7, 8},
                {9, 10, 11, 12},
                {13, 14, 15, 16}
        });
        DenseDoubleMatrix actual = m_lhs.clone().divide(c);

        assertArrayEquals(expected.data.toArray(), actual.data.toArray());
    }
    @Test void testDivide_2() {
        double c = 3.0d;
        DenseDoubleMatrix m_lhs  = new DenseDoubleMatrix(new double[][] {
                { 2,  4,  6,  8},
                {10, 12, 14, 16},
                {18, 20, 22, 24},
                {26, 28, 30, 32}
        });
        DenseDoubleMatrix expected = new DenseDoubleMatrix(new double[][]{
                { 2d/3,  4d/3,  6d/3,  8d/3},
                {10d/3, 12d/3, 14d/3, 16d/3},
                {18d/3, 20d/3, 22d/3, 24d/3},
                {26d/3, 28d/3, 30d/3, 32d/3}
        });

        DenseDoubleMatrix actual = m_lhs.clone().divide(c);
        assertArrayEquals(expected.toArray(), actual.toArray(), DELTA);
    }
    @Test void testDivide_3() {
        double c = 0.0d;
        DenseDoubleMatrix m_lhs  = new DenseDoubleMatrix(new double[][] {
                { 2,  4,  6,  8},
                {10, 12, 14, 16},
                {18, 20, 22, 24},
                {26, 28, 30, 32}
        });

        assertThrows(IllegalArgumentException.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                DenseDoubleMatrix actual = m_lhs.clone().divide(c);
            }
        });
    }
    @Test void testDivideRow_1() {
        double c = 0.0d;
        DenseDoubleMatrix m_lhs  = new DenseDoubleMatrix(new double[][] {
                { 2,  4,  6,  8},
                {10, 12, 14, 16},
                {18, 20, 22, 24},
                {26, 28, 30, 32}
        });

        assertThrows(IllegalArgumentException.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                m_lhs.divRow(1, c);
            }
        });
    }
    @Test void testDivideRow_2() {
        double c = 1.0d;
        DenseDoubleMatrix m = Algebra.denseMatrixFactory().random(10,10);
        double[] expected = m.getRow(5).toArray();

        m.divRow(5, c);

        assertArrayEquals(expected, m.getRow(5).toArray(), DELTA);
    }
    @Test void testDivideRow_3() {
        DenseDoubleMatrix m = new DenseDoubleMatrix(new double[][]{
            { 3,  4, 4, 7},
            {-6, -4, 2, 8},
            { 2,  1, 4, 7},
            { 3,  1, 6, 5}
        });
        m.divRow(1, 2.0f);

        double[] expected = new double[]{-3, -2, 1, 4};
        assertArrayEquals(expected, m.getRow(1).toArray(), DELTA);
    }
    @Test void testDivideRow_4() {
        DenseDoubleMatrix m = new DenseDoubleMatrix(4,5);

        assertThrows(IndexOutOfBoundsException.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                m.divRow(-1, 1.0d);
            }
        });
        assertThrows(IndexOutOfBoundsException.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                m.divRow(4, 1.0d);
            }
        });
    }
    @Test void testDivideColumn_1() {
        double c = 0.0d;
        DenseDoubleMatrix m_lhs  = new DenseDoubleMatrix(new double[][] {
                { 2,  4,  6,  8},
                {10, 12, 14, 16},
                {18, 20, 22, 24},
                {26, 28, 30, 32}
        });

        assertThrows(IllegalArgumentException.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                m_lhs.divColumn(1, c);
            }
        });
    }
    @Test void testDivideColumn_2() {
        double c = 1.0d;
        DenseDoubleMatrix m = Algebra.denseMatrixFactory().random(10,10);
        double[] expected = m.getColumn(5).toArray();

        m.divColumn(5, c);

        assertArrayEquals(expected, m.getColumn(5).toArray(), DELTA);
    }
    @Test void testDivideColumn_3() {
        DenseDoubleMatrix m = new DenseDoubleMatrix(new double[][]{
                { 3,  4, 4, 7},
                {-6, -4, 2, 8},
                { 2,  1, 4, 7},
                { 3,  1, 6, 5}});
        m.divColumn(1, 2.0f);

        double[] expected = new double[]{2, -2, 0.5f, 0.5f};
        assertArrayEquals(expected, m.getColumn(1).toArray(), DELTA);
    }
    @Test void testDivideColumn_4() {
        DenseDoubleMatrix m = new DenseDoubleMatrix(4,5);

        assertThrows(IndexOutOfBoundsException.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                m.divColumn(-1, 1.0d);
            }
        });
        assertThrows(IndexOutOfBoundsException.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                m.divColumn(6, 1.0d);
            }
        });
    }
    @Test void testDivideRange_1() {
        double c = 0.0d;
        DenseDoubleMatrix m_lhs  = new DenseDoubleMatrix(new double[][] {
                { 2,  4,  6,  8},
                {10, 12, 14, 16},
                {18, 20, 22, 24},
                {26, 28, 30, 32}
        });

        assertThrows(IllegalArgumentException.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                m_lhs.divRange(0, 1, 0, 1, c);
            }
        });
    }
    @Test void testDivideRange_2() {
        double c = 1.0d;
        DenseDoubleMatrix m = Algebra.denseMatrixFactory().random(10,10);
        double[] expected = m.getRange(1, 2, 1, 2).toArray();

        m.divRange(1,2,1,2, c);

        assertArrayEquals(expected, m.getRange(1,2,1,2).toArray(), DELTA);
    }
    @Test void testDivideRange_3() {
        DenseDoubleMatrix m = new DenseDoubleMatrix(new double[][]{
                { 3,  4, 4, 7},
                {-6, -4, 2, 8},
                { 2,  1, 4, 7},
                { 3,  1, 6, 5}});
        m.divRange(1, 1, 2, 2, 2.0d);

        double[] expectedRowA = new double[]{3, 4, 4, 7};
        double[] expectedRowB = new double[]{-6, -2, 1, 8};
        double[] expectedRowC = new double[]{ 2, 0.5d, 2, 7};
        double[] expectedRowD = new double[]{ 3, 1, 6, 5};

        assertArrayEquals(expectedRowA, m.getRow(0).toArray(), DELTA);
        assertArrayEquals(expectedRowB, m.getRow(1).toArray(), DELTA);
        assertArrayEquals(expectedRowC, m.getRow(2).toArray(), DELTA);
        assertArrayEquals(expectedRowD, m.getRow(3).toArray(), DELTA);
    }
    @Test void testDivideRange_4() {
        DenseDoubleMatrix m = new DenseDoubleMatrix(4,5);
        double[][] range = new double[2][2];

        assertThrows(IndexOutOfBoundsException.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                m.divRange(-1, 0, 0, 1, 1.0d);
            }
        });
        assertThrows(IndexOutOfBoundsException.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                m.divRange(0, -1, 1, 0, 1.0d);
            }
        });
        assertThrows(IndexOutOfBoundsException.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                m.divRange(3, 0, 4, 1, 1.0d);
            }
        });
        assertThrows(IndexOutOfBoundsException.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                m.divRange(0, 4, 1, 5, 1.0d);
            }
        });

    }
    @Test void testDivideRange_5() {
        DenseDoubleMatrix m = new DenseDoubleMatrix(4,5);
        double[][] range = new double[2][2];

        assertThrows(IllegalArgumentException.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                m.divRange(1, 0, 0, 1, 1.0d);
            }
        });
        assertThrows(IllegalArgumentException.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                m.divRange(0, 1, 1, 0, 1.0d);
            }
        });

    }

    //Test multiplication
    @Test void testPreMultiply_1() {
        DenseDoubleMatrix m_lhs = new DenseDoubleMatrix(4, 4, 0.0d);
        DenseDoubleMatrix m_rhs = new DenseDoubleMatrix(new double[][] {
                {-7.0d, -6.0d, -5.0d, -4.0d},
                {-3.0d, -2.0d, -1.0d,  0.0d},
                { 1.0d,  2.0d,  3.0d,  4.0d},
                { 5.0d,  6.0d,  7.0d,  8.0d}
        });
        DenseDoubleMatrix expected = new DenseDoubleMatrix(4, 4, 0.0d);
        assertArrayEquals(expected.toArray(), m_rhs.preMultiply(m_lhs).toArray(), DELTA);
    }
    @Test void testPreMultiply_2() {
        DenseDoubleMatrix m1 = Algebra.denseMatrixFactory().identity(4);
        DenseDoubleMatrix m2 = new DenseDoubleMatrix(new double[][]{
                {-7.0d, -6.0d, -5.0d, -4.0d},
                {-3.0d, -2.0d, -1.0d,  0.0d},
                { 1.0d,  2.0d,  3.0d,  4.0d},
                { 5.0d,  6.0d,  7.0d,  8.0d}});

        DenseDoubleMatrix expected = m2.clone();
        DenseDoubleMatrix actual = m1.preMultiply(m2);

        assertArrayEquals(expected.toArray(), actual.toArray(), DELTA);
    }
    @Test void testPreMultiply_3() {
        DenseDoubleMatrix m1 = new DenseDoubleMatrix(new double[][]{
                {-3.0, -2.0, -1.0,  0.0},
                {-7.0, -6.0, -5.0, -4.0},
                { 1.0,  2.0,  3.0,  4.0},
                { 5.0,  6.0,  7.0,  8.0}});
        DenseDoubleMatrix m2 = new DenseDoubleMatrix(new double[][]{
                {-7.0, -6.0, -5.0, -4.0},
                {-3.0, -2.0, -1.0,  0.0},
                { 1.0,  2.0,  3.0,  4.0},
                { 5.0,  6.0,  7.0,  8.0}});

        DenseDoubleMatrix expected = new DenseDoubleMatrix(new double[][] {
                {  26.0d,  20.0d,  14.0d,   8.0d},
                {  42.0d,  20.0d,  -2.0d, -24.0d},
                {  10.0d,  20.0d,  30.0d,  40.0d},
                {  -6.0d,  20.0d,  46.0d,  72.0d}});
        DenseDoubleMatrix actual = m2.clone().preMultiply(m1);

        assertArrayEquals(expected.toArray(), actual.toArray(), DELTA);
    }
    @Test void testPreMultiply_4() {
        DenseDoubleMatrix m1 = new DenseDoubleMatrix(new double[][]{
                {-3.0, -2.0, -1.0,  0.0},
                {-7.0, -6.0, -5.0, -4.0},
                { 1.0,  2.0,  3.0,  4.0},
                { 5.0,  6.0,  7.0,  8.0}});
        DenseDoubleMatrix m2 = new DenseDoubleMatrix(new double[][]{
                {-7.0, -6.0, -5.0, -4.0},
                {-3.0, -2.0, -1.0,  0.0},
                { 1.0,  2.0,  3.0,  4.0},
                { 5.0,  6.0,  7.0,  8.0}});

        DenseDoubleMatrix expected = new DenseDoubleMatrix(new double[][] {
                {  26.0d,  20.0d,  14.0d,   8.0d},
                {  42.0d,  20.0d,  -2.0d, -24.0d},
                {  10.0d,  20.0d,  30.0d,  40.0d},
                {  -6.0d,  20.0d,  46.0d,  72.0d}});
        DenseDoubleMatrix actual = m2.clone().preMultiply(m1);

        assertArrayEquals(expected.toArray(), actual.toArray(), DELTA);
    }
    @Test void testPostMultiply_1() {
        DenseDoubleMatrix m1 = new DenseDoubleMatrix(4,4, 0.0d);
        DenseDoubleMatrix m2 = new DenseDoubleMatrix(new double[][]{
                {-7.0d, -6.0d, -5.0d, -4.0d},
                {-3.0d, -2.0d, -1.0d, 0.0d},
                { 1.0d,  2.0d,  3.0d, 4.0d},
                { 5.0d,  6.0d,  7.0d, 8.0d}});
        DenseDoubleMatrix expected = new DenseDoubleMatrix(4,4, 0.0d);
        assertArrayEquals(expected.toArray(), m1.postMultiply(m2).toArray(), DELTA);
    }
    @Test void testPostMultiply_2() {
        DenseDoubleMatrix m1 = Algebra.denseMatrixFactory().identity(4);
        DenseDoubleMatrix m2 = new DenseDoubleMatrix(new double[][]{
                {-7.0d, -6.0d, -5.0d, -4.0d},
                {-3.0d, -2.0d, -1.0d,  0.0d},
                { 1.0d,  2.0d,  3.0d,  4.0d},
                { 5.0d,  6.0d,  7.0d,  8.0d}});
        DenseDoubleMatrix expected = m2.clone();
        DenseDoubleMatrix actual = m1.postMultiply(m2);
        assertArrayEquals(expected.toArray(), actual.toArray(), DELTA);
    }
    @Test void testPostMultiply_3() {
        DenseDoubleMatrix m1 = new DenseDoubleMatrix(new double[][]{
                {-7.0d, -6.0d, -5.0d, -4.0d},
                {-3.0d, -2.0d, -1.0d,  0.0d},
                { 1.0d,  2.0d,  3.0d,  4.0d},
                { 5.0d,  6.0d,  7.0d,  8.0d}});

        DenseDoubleMatrix m2 = new DenseDoubleMatrix(new double[][]{
                {-7.0d, -6.0d, -5.0d, -4.0d},
                {-3.0d, -2.0d, -1.0d,  0.0d},
                { 1.0d,  2.0d,  3.0d,  4.0d},
                { 5.0d,  6.0d,  7.0d,  8.0d}});

        DenseDoubleMatrix expected = new DenseDoubleMatrix(new double[][] {
                { 42.0d, 20.0d, -2.0d, -24.0d},
                { 26.0d, 20.0d, 14.0d,   8.0d},
                { 10.0d, 20.0d, 30.0d,  40.0d},
                { -6.0d, 20.0d, 46.0d,  72.0d}});

        assertArrayEquals(expected.toArray(), m1.postMultiply(m2).toArray(), DELTA);
    }
    @Test void testPostMultiply_4() {
        DenseDoubleMatrix m1 = new DenseDoubleMatrix(new double[][]{
                {-7.0d, -6.0d, -5.0d, -4.0d},
                {-3.0d, -2.0d, -1.0d,  0.0d},
                { 1.0d,  2.0d,  3.0d,  4.0d},
                { 5.0d,  6.0d,  7.0d,  8.0d}});
        DenseDoubleMatrix m2 = new DenseDoubleMatrix(new double[][]{
                { 8.0d,  7.0d,  6.0d,  5.0d},
                { 4.0d,  3.0d,  2.0d,  1.0d},
                { 0.0d, -1.0d, -2.0d, -3.0d},
                {-4.0d, -5.0d, -6.0d, -7.0d}});

        DenseDoubleMatrix expected = new DenseDoubleMatrix(new double[][] {
                { -64.0d, -42.0d, -20.0d,   2.0d},
                { -32.0d, -26.0d, -20.0d, -14.0d},
                {   0.0d, -10.0d, -20.0d, -30.0d},
                {  32.0d,   6.0d, -20.0d, -46.0d}});

        assertArrayEquals(expected.toArray(), m1.postMultiply(m2).toArray(), DELTA);
    }
    @Test void testMultiplicationPrePostEquivalence_1() {
        DenseDoubleMatrix m1 = new DenseDoubleMatrix(new double[][]{
                {-7.0d, -6.0d, -5.0d, -4.0d},
                {-3.0d, -2.0d, -1.0d,  0.0d},
                { 1.0d,  2.0d,  3.0d,  4.0d},
                { 5.0d,  6.0d,  7.0d,  8.0d}});
        DenseDoubleMatrix m2 = new DenseDoubleMatrix(new double[][]{
                { 8.0d,  7.0d,  6.0d,  5.0d},
                { 4.0d,  3.0d,  2.0d,  1.0d},
                { 0.0d, -1.0d, -2.0d, -3.0d},
                {-4.0d, -5.0d, -6.0d, -7.0d}});

        assertArrayEquals(m1.clone().preMultiply(m2).toArray(), m2.clone().postMultiply(m1).toArray(), DELTA);
    }
    @Test void testMultiplicationTransposition_1() {
        DenseDoubleMatrix m1 = new DenseDoubleMatrix(new double[][] {
                {-7.0d, -6.0d, -5.0d, -4.0d},
                {-3.0d, -2.0d, -1.0d,  0.0d},
                { 1.0d,  2.0d,  3.0d,  4.0d},
                { 5.0d,  6.0d,  7.0d,  8.0d}});

        DenseDoubleMatrix m2 = new DenseDoubleMatrix(new double[][]{
                { 8.0d,  7.0d,  6.0d,  5.0d},
                { 4.0d,  3.0d,  2.0d,  1.0d},
                { 0.0d, -1.0d, -2.0d, -3.0d},
                {-4.0d, -5.0d, -6.0d, -7.0d}});

        DenseDoubleMatrix resultA = m1.clone().postMultiply(m2);
        DenseDoubleMatrix resultB = m2.clone().transpose()
                .postMultiply(m1.clone().transpose()).transpose();

        assertArrayEquals(resultA.toArray(), resultB.toArray(), DELTA);
    }
    @Test void testMultiplicationDistributive_1() {
        DenseDoubleMatrix m1 = Algebra.denseMatrixFactory().random(4,4);
        DenseDoubleMatrix m2 = Algebra.denseMatrixFactory().random(4,4);
        DenseDoubleMatrix m3 = Algebra.denseMatrixFactory().random(4,4);

        DenseDoubleMatrix resultA = m1.clone().postMultiply(m2.clone().plus(m3));
        DenseDoubleMatrix resultB = m1.clone().postMultiply(m2).plus(m1.clone().postMultiply(m3));

        assertArrayEquals(resultA.toArray(), resultB.toArray(), DELTA);
    }
    @Test void testMultiplicationAssociative_1() {
        DenseDoubleMatrix m1 = Algebra.denseMatrixFactory().random(4,4);
        DenseDoubleMatrix m2 = Algebra.denseMatrixFactory().random(4,4);
        DenseDoubleMatrix m3 = Algebra.denseMatrixFactory().random(4,4);

        DenseDoubleMatrix resultA = m1.clone().postMultiply(m2.clone().postMultiply(m3));
        DenseDoubleMatrix resultB = (m1.clone().postMultiply(m2)).postMultiply(m3);

        assertArrayEquals(resultA.toArray(), resultB.toArray(), DELTA);
    }
    @Test void testMultiplicationDimensions_1() {
        DenseDoubleMatrix m1 = Algebra.denseMatrixFactory().random(3,4);
        DenseDoubleMatrix m2 = Algebra.denseMatrixFactory().random(4,3);

        DenseDoubleMatrix result = m1.clone().postMultiply(m2);

        assertEquals(3, result.cols());
        assertEquals(3, result.rows());
    }
    @Test void testMultiplicationDimensions_2() {
        DenseDoubleMatrix m1 = Algebra.denseMatrixFactory().random(3,3);
        DenseDoubleMatrix m2 = Algebra.denseMatrixFactory().random(4,3);

        assertThrows(IllegalArgumentException.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                DenseDoubleMatrix result = m1.clone().postMultiply(m2);
            }
        });
    }
    @Test void testMultRow_1() {
        double c = 0.0d;
        DenseDoubleMatrix m = new DenseDoubleMatrix(new double[][] {
                { 2,  4,  6,  8},
                {10, 12, 14, 16},
                {18, 20, 22, 24},
                {26, 28, 30, 32}
        });
        m.multRow(2, 0.0d);

        double[] expected = new double[]{0,0,0,0};
        assertArrayEquals(expected, m.getRow(2).toArray(), DELTA);
    }
    @Test void testMultRow_2() {
        double c = 1.0d;
        DenseDoubleMatrix m = Algebra.denseMatrixFactory().random(10,10);
        double[] expected = m.getRow(5).toArray();

        m.multRow(5, c);

        assertArrayEquals(expected, m.getRow(5).toArray(), DELTA);
    }
    @Test void testMultRow_3() {
        DenseDoubleMatrix m = new DenseDoubleMatrix(new double[][]{
                { 3,  4, 4, 7},
                {-6, -4, 2, 8},
                { 2,  1, 4, 7},
                { 3,  1, 6, 5}
        });
        m.multRow(1, 2.0f);

        double[] expected = new double[]{-12, -8, 4, 16};
        assertArrayEquals(expected, m.getRow(1).toArray(), DELTA);
    }
    @Test void testMultRow_4() {
        DenseDoubleMatrix m = new DenseDoubleMatrix(4,5);

        assertThrows(IndexOutOfBoundsException.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                m.multRow(-1, 1.0d);
            }
        });
        assertThrows(IndexOutOfBoundsException.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                m.multRow(4, 1.0d);
            }
        });
    }
    @Test void testMultColumn_1() {
        DenseDoubleMatrix m  = new DenseDoubleMatrix(new double[][] {
                { 2,  4,  6,  8},
                {10, 12, 14, 16},
                {18, 20, 22, 24},
                {26, 28, 30, 32}
        });
        m.multColumn(2, 0.0d);

        double[] expected = new double[]{0,0,0,0};
        assertArrayEquals(expected, m.getColumn(2).toArray(), DELTA);
    }
    @Test void testMultColumn_2() {
        double c = 1.0d;
        DenseDoubleMatrix m = Algebra.denseMatrixFactory().random(10,10);
        double[] expected = m.getColumn(5).toArray();

        m.multColumn(5, c);

        assertArrayEquals(expected, m.getColumn(5).toArray(), DELTA);
    }
    @Test void testMultColumn_3() {
        DenseDoubleMatrix m = new DenseDoubleMatrix(new double[][]{
                { 3,  4, 4, 7},
                {-6, -4, 2, 8},
                { 2,  1, 4, 7},
                { 3,  1, 6, 5}});
        m.multColumn(1, 2.0f);

        double[] expected = new double[]{8, -8, 2, 2};
        assertArrayEquals(expected, m.getColumn(1).toArray(), DELTA);
    }
    @Test void testMultColumn_4() {
        DenseDoubleMatrix m = new DenseDoubleMatrix(4,5);

        assertThrows(IndexOutOfBoundsException.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                m.multColumn(-1, 1.0d);
            }
        });
        assertThrows(IndexOutOfBoundsException.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                m.multColumn(6, 1.0d);
            }
        });
    }
    @Test void testMultRange_1() {
        DenseDoubleMatrix m = new DenseDoubleMatrix(new double[][] {
                { 2,  4,  6,  8},
                {10, 12, 14, 16},
                {18, 20, 22, 24},
                {26, 28, 30, 32}
        });

        m.multRange(1, 1, 2, 2, 0.0d);
        double[] expected = new double[] {0,0,0,0};

        assertArrayEquals(expected, m.getRange(1,1,2,2).toArray(), DELTA);
    }
    @Test void testMultRange_2() {
        double c = 1.0d;
        DenseDoubleMatrix m = Algebra.denseMatrixFactory().random(10,10);
        double[] expected = m.getRange(1, 2, 1, 2).toArray();

        m.multRange(1,2,1,2, c);

        assertArrayEquals(expected, m.getRange(1,2,1,2).toArray(), DELTA);
    }
    @Test void testMultRange_3() {
        DenseDoubleMatrix m = new DenseDoubleMatrix(new double[][]{
                { 3,  4, 4, 7},
                {-6, -4, 2, 8},
                { 2,  1, 4, 7},
                { 3,  1, 6, 5}});
        m.multRange(1, 1, 2, 2, 2.0d);

        double[] expectedRowA = new double[]{3, 4, 4, 7};
        double[] expectedRowB = new double[]{-6, -8, 4, 8};
        double[] expectedRowC = new double[]{ 2, 2, 8, 7};
        double[] expectedRowD = new double[]{ 3, 1, 6, 5};

        assertArrayEquals(expectedRowA, m.getRow(0).toArray(), DELTA);
        assertArrayEquals(expectedRowB, m.getRow(1).toArray(), DELTA);
        assertArrayEquals(expectedRowC, m.getRow(2).toArray(), DELTA);
        assertArrayEquals(expectedRowD, m.getRow(3).toArray(), DELTA);
    }
    @Test void testMultRange_4() {
        DenseDoubleMatrix m = new DenseDoubleMatrix(4,5);
        double[][] range = new double[2][2];

        assertThrows(IndexOutOfBoundsException.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                m.multRange(-1, 0, 0, 1, 1.0d);
            }
        });
        assertThrows(IndexOutOfBoundsException.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                m.multRange(0, -1, 1, 0, 1.0d);
            }
        });
        assertThrows(IndexOutOfBoundsException.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                m.multRange(3, 0, 4, 1, 1.0d);
            }
        });
        assertThrows(IndexOutOfBoundsException.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                m.multRange(0, 4, 1, 5, 1.0d);
            }
        });

    }
    @Test void testMultRange_5() {
        DenseDoubleMatrix m = new DenseDoubleMatrix(4,5);
        double[][] range = new double[2][2];

        assertThrows(IllegalArgumentException.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                m.multRange(1, 0, 0, 1, 1.0d);
            }
        });
        assertThrows(IllegalArgumentException.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                m.multRange(0, 1, 1, 0, 1.0d);
            }
        });
    }

    // Test inversion
    @Test void testInvert_1() {
        DenseDoubleMatrix m = Algebra.denseMatrixFactory().random(4,4);
        DenseDoubleMatrix m_inv = m.clone().invert();

        DenseDoubleMatrix expected = Algebra.denseMatrixFactory().identity(4);
        DenseDoubleMatrix actual1 = m.clone().postMultiply(m_inv);
        DenseDoubleMatrix actual2 = m.clone().preMultiply(m_inv);

        assertArrayEquals(expected.toArray(), actual1.toArray(), DELTA);
        assertArrayEquals(expected.toArray(), actual2.toArray(), DELTA);
    }
    @Test void testInvert_2() {
        DenseDoubleMatrix m = new DenseDoubleMatrix(new double[][]{
                {-2.3d, -6.5d, -5.2d, -4.9d},
                {-3.2d,	-2.0d, -1.3d,  0.6d},
                { 1.3d,	 2.1d,	3.5d,  4.6d},
                { 5.9d,	 6.1d,	7.2d,  8.3d}});

        DenseDoubleMatrix actual = new DenseDoubleMatrix(m.invert());
        DenseDoubleMatrix expected = new DenseDoubleMatrix(new double[][] {
                { 0.1765241776451404d,  0.0319794251951382d, -0.460496224703263d,  0.357116078190257d},
                {-0.2812727286360200d,  0.3812412365877610d, -0.870792624862222d,  0.288995778565912d},
                { 0.0254935628753743d, -1.1241911417367100d,  2.084923556801910d, -1.059182556404510d},
                { 0.0591225715506833d,	0.6722802492370670d, -0.841287574887341d,  0.573042806691310d}});

        assertArrayEquals(expected.toArray(), actual.toArray(), DELTA);
    }
    @Test void testInvert_3() {
        DenseDoubleMatrix m = new DenseDoubleMatrix(new double[][]{
                {-3.3d,	-2.0d, -1.6d,  1.3d},
                {-7.2d,	-6.1d, -5.5d, -4.4d},
                { 1.1d,	 2.2d,	3.4d,  4.6d},
                { 5.0d,	 6.5d,	7.3d,  8.9d}});

        DenseDoubleMatrix actual = new DenseDoubleMatrix(m.invert());
        DenseDoubleMatrix expected = new DenseDoubleMatrix(new double[][] {
                { 0.629364425575059d, -1.123122742538500d,  1.194981306634560d,	-1.264812115835500},
                {-0.588239021608263d,  1.384006083264690d, -2.811418794753190d,  2.223243140485400},
                {-0.675685951460617d,  0.166719472783727d,	1.303656295545280d,	-0.492681072175402},
                { 0.630251568341677d, -0.516570559533616d,	0.312654457892402d,	-0.396679551359229}});

        assertArrayEquals(expected.toArray(), actual.toArray(), DELTA);
    }
    @Test void testInvert_4() {
        DenseDoubleMatrix m = new DenseDoubleMatrix(new double[][]{
                {-3.3d,	-2.0d, -1.6d,  1.3d},
                {-7.2d,	-6.1d, -5.5d, -4.4d},
                { 1.1d,	 2.2d,	3.4d,  4.6d}});

        assertThrows(IllegalArgumentException.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                m.invert();
            }
        });
    }

    // Test swap
    @Test void testSwapRow_1() {
        DenseDoubleMatrix m = Algebra.denseMatrixFactory().random(5,6);

        DenseDoubleMatrix rowA = m.getRow(2);
        DenseDoubleMatrix rowB = m.getRow(4);
        m.swapRows(2,4);

        assertArrayEquals(rowB.toArray(), m.data.viewRow(2).toArray(), DELTA);
        assertArrayEquals(rowA.toArray(), m.data.viewRow(4).toArray(), DELTA);
    }
    @Test void testSwapColumn_1() {
        DenseDoubleMatrix m = Algebra.denseMatrixFactory().random(5,6);

        DenseDoubleMatrix colA = m.getColumn(2);
        DenseDoubleMatrix colB = m.getColumn(4);
        m.swapColumns(2,4);

        assertArrayEquals(colB.toArray(), m.data.viewColumn(2).toArray(), DELTA);
        assertArrayEquals(colA.toArray(), m.data.viewColumn(4).toArray(), DELTA);
    }

    // Test transposition
    @Test void testTranspose_1() {
        DenseDoubleMatrix m1 = new DenseDoubleMatrix(new double[][]{
                {-3.0, -2.0, -1.0,  0.0},
                {-7.0, -6.0, -5.0, -4.0},
                { 1.0,  2.0,  3.0,  4.0},
                { 5.0,  6.0,  7.0,  8.0}});
        DenseDoubleMatrix m2 = m1.clone().transpose().transpose();

        assertArrayEquals(m1.toArray(), m2.toArray(), DELTA);
    }
    @Test void testTransposeDimensions_1() {
        DenseDoubleMatrix m1 = new DenseDoubleMatrix(new double[][]{
                {-3.0, -2.0, -1.0,  0.0},
                {-7.0, -6.0, -5.0, -4.0},
                { 1.0,  2.0,  3.0,  4.0}});
        m1.transpose();

        assertEquals(4, m1.rows(), DELTA);
        assertEquals(3, m1.cols(), DELTA);
    }
}