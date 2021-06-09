package com.alura.dyno.math.cartesian;

import com.alura.dyno.physics.fem.FEAPlanarTri6;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TriangleTest {
    private static double DELTA = 0.00000000001d;

    @Test
    public void testgetArea_1() {
        Point2D pointA = new Point2D(0,0);
        Point2D pointB = new Point2D(1,0);
        Point2D pointC = new Point2D(0,1);
        Triangle triangle = new Triangle(pointA, pointB, pointC);

        assertEquals(0.5d, triangle.getArea());
    }
    @Test public void testgetArea_2() {
        Point2D pointA = new Point2D(0,0);
        Point2D pointB = new Point2D(2,0);
        Point2D pointC = new Point2D(0,1);
        Triangle triangle = new Triangle(pointA, pointB, pointC);

        assertEquals(1d, triangle.getArea());
    }
    @Test public void testgetArea_3() {
        Point2D pointA = new Point2D(0,0);
        Point2D pointB = new Point2D( 0,0);
        Point2D pointC = new Point2D(1,1);
        Triangle triangle = new Triangle(pointA, pointB, pointC);

        assertEquals(0d, triangle.getArea());
    }
    @Test public void testgetArea_4() {
        Point2D pointA = new Point2D(0.3,2.3);
        Point2D pointB = new Point2D( 4.7,-2.4);
        Point2D pointC = new Point2D(2.1,2.1);
        Triangle triangle = new Triangle(pointA, pointB, pointC);

        assertEquals(3.79d, triangle.getArea(), DELTA);
    }

}