package com.alura.dyno.physics.fem;

import com.alura.dyno.math.linalg.DoubleMatrix;

public interface IMassiveElement {
    DoubleMatrix getMassMatrix();
}
