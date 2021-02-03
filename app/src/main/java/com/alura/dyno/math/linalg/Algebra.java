package com.alura.dyno.math.linalg;

import cern.colt.matrix.tfloat.algo.DenseFloatAlgebra;

public class Algebra {
    private static DenseFloatAlgebra denseFloat;

    public static DenseFloatAlgebra denseFloat() {
        if(denseFloat == null) {
            denseFloat = new DenseFloatAlgebra();
        }

        return denseFloat;
    }
}
