package com.alura.dyno.physics.fem;

import com.alura.dyno.math.linalg.DoubleMatrix;
import com.alura.dyno.math.cartesian.Point2D;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;


class FEAPlanarTri6Test {
    private static final double DELTA = 0.0000000001d;

    @Test public void TRI3_testStiffness() {
        Point2D pointA = new Point2D(0,1);
        Point2D pointB = new Point2D(1,0);
        Point2D pointC = new Point2D(1,1);

        FEAPlanarTri3 tri3 = new FEAPlanarTri3(pointA, pointB, pointC);
        DoubleMatrix stiffness = tri3.getStiffness();
    }
    @Test public void TRI3_testMass() {
        Point2D pointA = Point2D.random();
        Point2D pointB = Point2D.random();
        Point2D pointC = Point2D.random();

        FEAPlanarTri3 tri3 = new FEAPlanarTri3(pointA, pointB, pointC);
        DoubleMatrix massMatrix = tri3.getMassMatrix();
    }

    @Test public void TRI6_testCalculateShapeMatrixAt_1() {
        Point2D pointA = Point2D.random();
        Point2D pointB = Point2D.random();
        Point2D pointC = Point2D.random();

        FEAPlanarTri6 tri6 = new FEAPlanarTri6(pointA, pointB, pointC);
        DoubleMatrix shapeMatrix = tri6.calculateShapeMatrixAt(pointA);

        DoubleMatrix expected = new DoubleMatrix(new double[][] {
                {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}
        });

        assertArrayEquals(expected.toArray2D(), shapeMatrix.toArray2D());
    }
    @Test public void TRI6_testCalculateShapeMatrixAt_2() {
        Point2D pointA = Point2D.random();
        Point2D pointB = Point2D.random();
        Point2D pointC = Point2D.random();

        FEAPlanarTri6 tri6 = new FEAPlanarTri6(pointA, pointB, pointC);
        DoubleMatrix shapeMatrix = tri6.calculateShapeMatrixAt(pointB);

        DoubleMatrix expected = new DoubleMatrix(new double[][] {
                {0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0}
        });

        assertArrayEquals(expected.toArray2D(), shapeMatrix.toArray2D());
    }
    @Test public void TRI6_testCalculateShapeMatrixAt_3() {
        Point2D pointA = Point2D.random();
        Point2D pointB = Point2D.random();
        Point2D pointC = Point2D.random();

        FEAPlanarTri6 tri6 = new FEAPlanarTri6(pointA, pointB, pointC);
        DoubleMatrix shapeMatrix = tri6.calculateShapeMatrixAt(pointC);

        DoubleMatrix expected = new DoubleMatrix(new double[][] {
                {0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0}
        });

        assertArrayEquals(expected.toArray2D(), shapeMatrix.toArray2D());
    }
    @Test public void TRI6_testCalculateShapeMatrixAt_4() {
        Point2D pointA = Point2D.random();
        Point2D pointB = Point2D.random();
        Point2D pointC = Point2D.random();
        Point2D pointD = pointA.clone().plus(pointB).multiply(0.5d);

        FEAPlanarTri6 tri6 = new FEAPlanarTri6(pointA, pointB, pointC);
        DoubleMatrix shapeMatrix = tri6.calculateShapeMatrixAt(pointD);

        DoubleMatrix expected = new DoubleMatrix(new double[][] {
                {0.5d,    0, 0.5d,    0, 0, 0, 1, 0, 0, 0, 0, 0},
                {   0, 0.5d,    0, 0.5d, 0, 0, 0, 1, 0, 0, 0, 0}
        });

        assertArrayEquals(expected.toArray(), shapeMatrix.toArray(), DELTA);
    }
    @Test public void TRI6_testCalculateShapeMatrixAt_5() {
        Point2D pointA = Point2D.random();
        Point2D pointB = Point2D.random();
        Point2D pointC = Point2D.random();
        Point2D pointE = pointB.clone().plus(pointC).multiply(0.5d);

        FEAPlanarTri6 tri6 = new FEAPlanarTri6(pointA, pointB, pointC);
        DoubleMatrix shapeMatrix = tri6.calculateShapeMatrixAt(pointE);

        DoubleMatrix expected = new DoubleMatrix(new double[][] {
                { 0, 0, 0.5d,    0, 0.5d,    0, 0, 0, 1, 0, 0, 0},
                { 0, 0,    0, 0.5d,    0, 0.5d, 0, 0, 0, 1, 0, 0}
        });

        assertArrayEquals(expected.toArray(), shapeMatrix.toArray(), DELTA);
    }
    @Test public void TRI6_testCalculateShapeMatrixAt_6() {
        Point2D pointA = Point2D.random();
        Point2D pointB = Point2D.random();
        Point2D pointC = Point2D.random();
        Point2D pointF = pointC.clone().plus(pointA).multiply(0.5d);

        FEAPlanarTri6 tri6 = new FEAPlanarTri6(pointA, pointB, pointC);
        DoubleMatrix shapeMatrix = tri6.calculateShapeMatrixAt(pointF);

        DoubleMatrix expected = new DoubleMatrix(new double[][] {
                { 0.5d,    0, 0, 0, 0.5d,    0, 0, 0, 0, 0, 1, 0},
                {    0, 0.5d, 0, 0,    0, 0.5d, 0, 0, 0, 0, 0, 1}
        });

        assertArrayEquals(expected.toArray(), shapeMatrix.toArray(), DELTA);
    }
    @Test public void TRI6_testStiffness() {
        Point2D pointA = new Point2D(0,1);
        Point2D pointB = new Point2D(1,0);
        Point2D pointC = new Point2D(1,1);

        FEAPlanarTri6 tri6 = new FEAPlanarTri6(pointA, pointB, pointC);
        DoubleMatrix stiffness = tri6.getStiffness();
    }
    @Test public void TRI6_testMass() {
        Point2D pointA = new Point2D(0,0);
        Point2D pointB = new Point2D(1,0);
        Point2D pointC = new Point2D(0,1);

        FEAPlanarTri6 tri6 = new FEAPlanarTri6(pointA, pointB, pointC);
        DoubleMatrix massMatrix = tri6.getMassMatrix();
    }
}