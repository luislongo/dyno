package com.alura.dyno.maths;

public class GaussianSolver {
    private static MatrixF system;
    private static MatrixF result;

    public static void solve(MatrixF equations, VectorF result) {
        checkForInvalidDimensions(equations, result);
        buildAugmentedMatrix(equations, result);
        forwardSubstitute();
        backwardInsertion();

        GaussianSolver.result = system.getSubMatrix(0, system.getNrOfRows() - 1,
                system.getNrOfCols() - 1,system.getNrOfCols() - 1);
    }

    private static void forwardSubstitute() {
        for(int i = 0; i < system.getNrOfRows(); i++)
        {
            swapLinesForNonZeroPivot(i);
            normalizePivotLine(i);
            forwardEliminate(i);
        }
    }

    private static void buildAugmentedMatrix(MatrixF equations, VectorF result) {
        GaussianSolver.system = new MatrixF(equations.getNrOfRows(), equations.getNrOfCols() + 1);

        for(int i = 0; i < equations.getNrOfRows(); i++)
        {
            for(int j = 0; j < equations.getNrOfCols(); j++)
            {
                system.setX_(i, j, equations.getX_(i, j));
            }
            system.setX_(i, system.getNrOfCols() - 1, result.getX_(i));
        }
    }
    private static void normalizePivotLine(int i) {
        float pivotValue = system.getX_(i,i);
        system.multiplyLine(i, 1.0f / pivotValue);
    }
    private static int swapLinesForNonZeroPivot(int i) {
        int firstNonZeroPivot = i;
        while(system.getX_(firstNonZeroPivot, i) == 0.0f)
        {
            firstNonZeroPivot += 1;
            checkForNonZeroPivotUnavailable(firstNonZeroPivot);
        }
        if(firstNonZeroPivot != i) {
            system.swapLines(i, firstNonZeroPivot);
        }
        return firstNonZeroPivot;
    }
    private static void forwardEliminate(int i) {
        for(int k = i + 1; k < system.getNrOfRows(); k++) {
            float multiplier = system.getX_(k, i);
            for (int j = i; j < system.getNrOfCols(); j++)
            {
                float normalizedValue = multiplier * system.getX_(i,j);
                system.subtractFromX_(k, j, normalizedValue);
            }
        }
    }

    private static void backwardInsertion() {
        for(int i = system.getNrOfRows() - 1; i >= 0; i--)
        {
            backwardEliminate(i);
        }
    }
    private static void backwardEliminate(int i) {
        for(int k = i - 1; k >= 0; k--) {
            float multiplier = system.getX_(k, i);
            for (int j = i; j < system.getNrOfCols(); j++)
            {
                float normalizedValue = multiplier * system.getX_(i,j);
                system.subtractFromX_(k, j, normalizedValue);
            }
        }
    }


    private static void checkForNonZeroPivotUnavailable(int firstNonZeroPivot) {
        if(firstNonZeroPivot >= system.getNrOfRows())
        {
            throw new RuntimeException("ERROR::GAUSSIAN_SOLVER::NON ZERO PIVOT UNAVAILABLE");
        }
    }


    private static void checkForInvalidDimensions(MatrixF equations, VectorF result) {
        if(!equations.isSquare())
        {
            throw new RuntimeException("ERROR::GAUSSIAN_SOLVER::EQUATION MATRIX MUST BE SQUARE");
        }
        if(equations.getNrOfCols() != result.nrOfCoords())
        {
            throw new RuntimeException("ERROR::GAUSSIAN_SOLVER::NUMBER OF EQUATIONS DOES NOT MATCH RESULT LENGTH");
        }
    }

}
