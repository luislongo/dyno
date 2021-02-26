package com.alura.dyno.math.linalg;

import com.alura.dyno.math.graphics.GraphicMatrixFactory;

import cern.colt.matrix.tfloat.algo.DenseFloatAlgebra;

public class Algebra {
    private static DenseFloatAlgebra denseFloat;
    private static GraphicMatrixFactory graphicMatrixFactory;

    public static DenseFloatAlgebra denseFloat() {
        if(denseFloat == null) {
            denseFloat = new DenseFloatAlgebra();
        }

        return denseFloat;
    }
    public static GraphicMatrixFactory graphicMatrixFactory() {
        if(graphicMatrixFactory == null) {
            graphicMatrixFactory = new GraphicMatrixFactory();
        }

        return graphicMatrixFactory;
    }
}
