package com.alura.dyno.math.cartesian;

import com.alura.dyno.math.linalg.DoubleMatrix;

public class Triangle {
    private Point2D a, b, c;
    private double area;

    public Triangle(Point2D a, Point2D b, Point2D c) {
        this.a = a;
        this.b = b;
        this.c = c;

        area = calculateArea();
    }
    private double calculateArea() {
        DoubleMatrix areaMatrix = new DoubleMatrix(3,3);
        areaMatrix.setRange(0,0,0,1, a);
        areaMatrix.setRange(1,0,1,1, b);
        areaMatrix.setRange(2,0,2,1, c);
        areaMatrix.setColumn(2, 1.0d, 1.0d ,1.0d);

        return  0.5d * Math.abs(areaMatrix.det());
    }

    public Point2D getA() {
        return a.clone();
    }
    public Point2D getB() {
        return b.clone();
    }
    public Point2D getC() {
        return c.clone();
    }
    public double getArea() {
        return area;
    }
    public Point3D toBarycentric(Point2D p) {
        Point2D v0 = b.clone().minus(a);
        Point2D v1 = c.clone().minus(a);
        Point2D v2 = p.clone().minus(a);

        double d00 = v0.dotProduct(v0);
        double d01 = v0.dotProduct(v1);
        double d11 = v1.dotProduct(v1);
        double d20 = v2.dotProduct(v0);
        double d21 = v2.dotProduct(v1);
        double denom = d00 * d11 - d01 * d01;
        double v = (d11 * d20 - d01 * d21) / denom;
        double w = (d00 * d21 - d01 * d20) / denom;
        double u = 1.0f - v - w;

        return new Point3D(v,u,w);
    }
    public Triangle clone(){
        return new Triangle(a,b,c);
    }
}
