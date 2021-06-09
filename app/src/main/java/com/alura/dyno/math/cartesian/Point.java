package com.alura.dyno.math.cartesian;

import com.alura.dyno.math.linalg.AbstractDoubleVector;

public abstract class Point<DIM extends Point> extends AbstractDoubleVector<DIM> {
    Point(double... values) {
        super(values);
    }
}

