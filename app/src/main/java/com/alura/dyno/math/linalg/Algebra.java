package com.alura.dyno.math.linalg;

import com.alura.dyno.math.graphics.GraphicMatrixBuilder;

import cern.colt.matrix.tdouble.algo.DenseDoubleAlgebra;
import cern.colt.matrix.tfloat.algo.DenseFloatAlgebra;

public class Algebra {
    private static DenseFloatAlgebra denseFloatAlgebra;
    private static DenseDoubleAlgebra denseDoubleAlgebra;
    private static DoubleMatrixFactory denseMatrixFactory;
    private static GraphicMatrixBuilder graphicMatrixBuilder;

    //Methods
    public static DenseFloatAlgebra denseFloatAlgebra() {
        if(denseFloatAlgebra == null) {
            denseFloatAlgebra = new DenseFloatAlgebra();
        }

        return denseFloatAlgebra;
    }
    public static DenseDoubleAlgebra denseDoubleAlgebra() {
        if(denseDoubleAlgebra == null) {
            denseDoubleAlgebra = new DenseDoubleAlgebra();
        }

        return denseDoubleAlgebra;
    }
    public static GraphicMatrixBuilder graphicMatrixBuilder() {
        if(graphicMatrixBuilder == null) {
            graphicMatrixBuilder = new GraphicMatrixBuilder();
        }

        return graphicMatrixBuilder;
    }
    public static DoubleMatrixFactory doubleMatrixFactory() {
        if(denseMatrixFactory == null) {
            denseMatrixFactory = new DoubleMatrixFactory();
        }

        return denseMatrixFactory;
    }
}
