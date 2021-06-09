package com.alura.dyno.math.cartesian;

import cern.colt.matrix.tdouble.DoubleMatrix1D;

public class Point3D extends Point<Point3D> {
    public Point3D() {
        super(0,0,0);
    }
    public Point3D(double x, double y, double z) {
        super(x, y, z);
    }
    public Point3D(Point3D other) {
        super(other.data.toArray());
    }

    public double x() {
        return this.get(0);
    }
    public double y() {
        return this.get(1);
    }
    public double z() {
        return this.get(2);
    }

    @Override public Point3D clone() {
        return new Point3D(this);
    }
    @Override public Point3D like(DoubleMatrix1D data) {
        return new Point3D(
                data.get(0),data.get(1),data.get(2)
        );
    }
}
