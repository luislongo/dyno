package com.alura.dyno.maths;

public class GaussianSolver {
    private static MatrixD system;
    private static MathExtra.DeltaCompare compare = new MathExtra.DeltaCompare(0.000001f);

    public static MatrixD solve(MatrixD A, MatrixD b) {
        checkForInvalidDimensions(A, b);
        buildAugmentedMatrix(A, b);
        forwardSubstitute();
        backwardInsertion();

        return system.getCol(system.cols() - 1);
    }

    private static void buildAugmentedMatrix(MatrixD A, MatrixD b) {
        GaussianSolver.system = new MatrixD(A.rows(), A.cols() + 1);
        system.setRange(A, 0, 0);
        system.setCol(b, system.cols() - 1);
    }
    private static void forwardSubstitute() {
        for(int i = 0; i < system.rows(); i++) {
            swapLinesForNonZeroPivot(i);
            normalizePivotLine(i);
            forwardEliminate(i);
        }
    }
    private static int swapLinesForNonZeroPivot(int i) {
        int firstNonZeroPivot = i;

        while(compare.isZero(system.getCell(firstNonZeroPivot, i))) {
            firstNonZeroPivot += 1;
            checkForNonZeroPivotUnavailable(firstNonZeroPivot);
        }
        if(firstNonZeroPivot != i) {
            system.swapRows(i, firstNonZeroPivot);
        }

        return firstNonZeroPivot;
    }
    private static void normalizePivotLine(int i) {
        double pivotValue = system.getCell(i, i);
        system.divRow(i, pivotValue);
    }
    private static void forwardEliminate(int i) {
        MatrixD pivotRow = system.getRow(i);

        for(int k = i + 1; k < system.rows(); k++) {
            double multiplier = system.getCell(k, i);

            MatrixD sub = pivotRow.getRange(0,0, i, system.cols()-1);
            sub.mult(multiplier);

            system.subRange(sub, k, i);
        }
    }
    private static void backwardInsertion() {
        for(int i = system.rows() - 1; i >= 0; i--)
        {
            backwardEliminate(i);
        }
    }
    private static void backwardEliminate(int i) {
        MatrixD pivotRange = system.getRange(i, i, i, system.cols() - 1);

        for(int k = i - 1; k >= 0; k--) {
            double multiplier = system.getCell(k, i);
            MatrixD sub = MatrixD.mult(pivotRange, multiplier);

            system.subRange(sub, k, i);
        }
    }
    private static void checkForNonZeroPivotUnavailable(int firstNonZeroPivot) {
        if(firstNonZeroPivot >= system.rows())
        {
            throw new RuntimeException("ERROR::GAUSSIAN_SOLVER::NON ZERO PIVOT UNAVAILABLE");
        }
    }
    private static void checkForInvalidDimensions(MatrixD A, MatrixD b) {
        if(!A.isSquare())
        {
            throw new RuntimeException("ERROR::GAUSSIAN_SOLVER::EQUATION MATRIX MUST BE SQUARE");
        }
        if(A.rows() != b.rows())
        {
            throw new RuntimeException("ERROR::GAUSSIAN_SOLVER::NUMBER OF EQUATIONS DOES NOT MATCH RESULT LENGTH");
        }
    }

}
