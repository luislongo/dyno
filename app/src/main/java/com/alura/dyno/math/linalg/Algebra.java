package com.alura.dyno.math.linalg;

import com.alura.dyno.math.graphics.IGraphicMatrixFactory;
import com.alura.dyno.math.graphics.RawGraphicMatrixFactory;

import cern.colt.matrix.tfloat.algo.DenseFloatAlgebra;

public class Algebra {
    private static DenseFloatAlgebra denseFloat;
    private static IGraphicMatrixFactory graphicMatrixFactory;

    public static DenseFloatAlgebra denseFloat() {
        if(denseFloat == null) {
            denseFloat = new DenseFloatAlgebra();
        }

        return denseFloat;
    }
    public static IGraphicMatrixFactory graphicMatrixFactory() {
        if(graphicMatrixFactory == null) {
            graphicMatrixFactory = new RawGraphicMatrixFactory();
        }

        return graphicMatrixFactory;
    }
}
