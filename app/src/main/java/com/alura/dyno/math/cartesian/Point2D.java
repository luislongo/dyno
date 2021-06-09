package com.alura.dyno.math.cartesian;

import cern.colt.matrix.tdouble.DoubleMatrix1D;

public class Point2D extends Point<Point2D> {
    public Point2D() {
        super(0,0);
    }
    public Point2D(double x, double y) {
        super(x, y);
    }
    public Point2D(Point2D other) {
        super(other.data.toArray());
    }

    public static Point2D random() {
        double x = Math.random();
        double y = Math.random();

        return new Point2D(x, y);
    }
    public double x() {
        return this.get(0);
    }
    public double y() {
        return this.get(1);
    }

    @Override public Point2D clone() {
        return new Point2D(this);
    }
    @Override public Point2D like(DoubleMatrix1D data) {
        return new Point2D(
                data.get(0), data.get(1)
        );
    }
}
