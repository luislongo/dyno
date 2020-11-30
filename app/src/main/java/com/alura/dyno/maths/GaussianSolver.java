package com.alura.dyno.maths;

public class GaussianSolver {
    private static MatrixF system;
    private static MatrixF result;

    public static void solve(MatrixF equations, VectorF result) {
        checkForInvalidDimensions(equations, result);
        buildAugmentedMatrix(equations, result);
        forwardSubstitute();
        backwardInsertion();

        GaussianSolver.result = MMath.getCol(system, system.cols() - 1);
    }

    private static void forwardSubstitute() {
        for(int i = 0; i < system.rows(); i++)
        {
            swapLinesForNonZeroPivot(i);
            normalizePivotLine(i);
            forwardEliminate(i);
        }
    }

    private static void buildAugmentedMatrix(MatrixF equations, VectorF result) {
        GaussianSolver.system = new MatrixF(equations.rows(), equations.cols() + 1);

        for(int i = 0; i < equations.rows(); i++)
        {
            for(int j = 0; j < equations.cols(); j++)
            {
                system.setCell(i, j, equations.getCell(i, j));
            }
            system.setCell(i, system.cols() - 1, result.getX_(i));
        }
    }
    private static void normalizePivotLine(int i) {
        float pivotValue = system.getCell(i,i);
        MMath.multiplyRow(system, 1, 1.0f / pivotValue);
    }
    private static int swapLinesForNonZeroPivot(int i) {
        int firstNonZeroPivot = i;
        while(system.getCell(firstNonZeroPivot, i) == 0.0f)
        {
            firstNonZeroPivot += 1;
            checkForNonZeroPivotUnavailable(firstNonZeroPivot);
        }
        if(firstNonZeroPivot != i) {
            MMath.swapLines(system, i, firstNonZeroPivot);
        }
        return firstNonZeroPivot;
    }
    private static void forwardEliminate(int i) {
        for(int k = i + 1; k < system.rows(); k++) {
            float multiplier = system.getCell(k, i);
            for (int j = i; j < system.cols(); j++)
            {
                float normalizedValue = multiplier * system.getCell(i,j);
                system.subFromCell(k, j, normalizedValue);
            }
        }
    }

    private static void backwardInsertion() {
        for(int i = system.rows() - 1; i >= 0; i--)
        {
            backwardEliminate(i);
        }
    }
    private static void backwardEliminate(int i) {
        for(int k = i - 1; k >= 0; k--) {
            float multiplier = system.getCell(k, i);
            for (int j = i; j < system.cols(); j++)
            {
                float normalizedValue = multiplier * system.getCell(i,j);
                system.subFromCell(k, j, normalizedValue);
            }
        }
    }


    private static void checkForNonZeroPivotUnavailable(int firstNonZeroPivot) {
        if(firstNonZeroPivot >= system.rows())
        {
            throw new RuntimeException("ERROR::GAUSSIAN_SOLVER::NON ZERO PIVOT UNAVAILABLE");
        }
    }


    private static void checkForInvalidDimensions(MatrixF equations, VectorF result) {
        if(!equations.isSquare())
        {
            throw new RuntimeException("ERROR::GAUSSIAN_SOLVER::EQUATION MATRIX MUST BE SQUARE");
        }
        if(equations.cols() != result.count())
        {
            throw new RuntimeException("ERROR::GAUSSIAN_SOLVER::NUMBER OF EQUATIONS DOES NOT MATCH RESULT LENGTH");
        }
    }

}
