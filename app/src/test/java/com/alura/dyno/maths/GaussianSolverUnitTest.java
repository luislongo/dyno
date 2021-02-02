package com.alura.dyno.maths;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

class GaussianSolverUnitTest {
    @Test void testSolveCase0() {
        double[] aData = new double[]
                {1, 0,
                 0, 1};
        MatrixD A = new MatrixD(2, 2, aData);

        double[] bData = new double[]
                {1,
                 2};
        MatrixD b = new MatrixD(2, 1, bData);

        MatrixD result = GaussianSolver.solve(A, b);
        MatrixD proof = MatrixD.mult(A, result);

        assertArrayEquals(b.x_ij, proof.x_ij);
    }
    @Test void testSolveCase1() {
        double[] aData = new double[]
                {0, 1,
                        1, 0};
        MatrixD A = new MatrixD(2, 2, aData);

        double[] bData = new double[]
                {1,
                        2};
        MatrixD b = new MatrixD(2, 1, bData);

        MatrixD result = GaussianSolver.solve(A, b);
        MatrixD proof = MatrixD.mult(A, result);

        assertArrayEquals(b.x_ij, proof.x_ij);
    }
    @Test void testSolveCase3() {
        double[] aData = new double[]
                {2, 0,
                 0, 1};
        MatrixD A = new MatrixD(2, 2, aData);

        double[] bData = new double[]
                {1,
                 2};
        MatrixD b = new MatrixD(2, 1, bData);

        MatrixD result = GaussianSolver.solve(A, b);
        MatrixD proof = MatrixD.mult(A, result);

        assertArrayEquals(b.x_ij, proof.x_ij);
    }
    @Test void testSolveCase4() {
        double[] aData = new double[]
                {2, 0,
                 0, 3};
        MatrixD A = new MatrixD(2, 2, aData);

        double[] bData = new double[]
                {1,
                 2};
        MatrixD b = new MatrixD(2, 1, bData);

        MatrixD result = GaussianSolver.solve(A, b);
        MatrixD proof = MatrixD.mult(A, result);

        assertArrayEquals(b.x_ij, proof.x_ij);
    }
    @Test void testSolveCase5() {
        double[] aData = new double[]
                {2, 0,
                        1, 3};
        MatrixD A = new MatrixD(2, 2, aData);

        double[] bData = new double[]
                {1,
                        2};
        MatrixD b = new MatrixD(2, 1, bData);

        MatrixD result = GaussianSolver.solve(A, b);
        MatrixD proof = MatrixD.mult(A, result);

        assertArrayEquals(b.x_ij, proof.x_ij);
    }
    @Test void testSolveCase6() {
        double[] aData = new double[]
                {2, 1,
                 1, 3};
        MatrixD A = new MatrixD(2, 2, aData);

        double[] bData = new double[]
                {1,
                        2};
        MatrixD b = new MatrixD(2, 1, bData);

        MatrixD result = GaussianSolver.solve(A, b);
        MatrixD proof = MatrixD.mult(A, result);

        assertArrayEquals(b.x_ij, proof.x_ij);
    }
    @Test void testSolveCase7() {
        MatrixD A = MatrixD.random(5000, 5000, 10, 200);
        MatrixD b = MatrixD.random(5000, 1, 10, 200);

        MatrixD result = GaussianSolver.solve(A, b);
        MatrixD proof = MatrixD.mult(A, result);

        assertArrayEquals(b.x_ij, proof.x_ij,1);
    }

}