package com.alura.dyno.math.calculus;

import com.alura.dyno.math.cartesian.Point2D;
import com.alura.dyno.math.cartesian.Triangle;
import com.alura.dyno.math.linalg.DoubleMatrix;
import com.alura.dyno.math.linalg.DoubleMatrixFactory;
import com.alura.dyno.math.linalg.DoubleVectorN;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GaussianQuadratureTest {
    private static double DELTA = 0.000000000001d;

    @Test void test() {
        Triangle domain = new Triangle(
                new Point2D(2,3),
                new Point2D( -1, 6),
                new Point2D( 4, 4));
        GaussianQuadrature quadrature = new GaussianQuadrature(6, domain);
        double value = quadrature.integrate(new ScalarFunction2D() {
            @Override
            public Double evaluateAt(Point2D point2D) {
                return Math.cos(point2D.x());
            }
        });

        assertEquals(-0.360128508473269d, value, DELTA);
    };
    @Test void test2() {
        Triangle domain = new Triangle(
                new Point2D(2,3),
                new Point2D( -1, 6),
                new Point2D( 4, 4));
        GaussianQuadrature quadrature = new GaussianQuadrature(20, domain);
        double value = quadrature.integrate(new ScalarFunction2D() {
            @Override
            public Double evaluateAt(Point2D point2D) {
                return Math.cos(point2D.x() + point2D.y());
            }
        });

        assertEquals(3.305935043261254d, value, DELTA);
    };
    @Test void test3() {
        Triangle domain = new Triangle(
                new Point2D(2,3),
                new Point2D( -1, 6),
                new Point2D( 4, 4));
        GaussianQuadrature quadrature = new GaussianQuadrature(6, domain);
        double value = quadrature.integrate(new ScalarFunction2D() {
            @Override
            public Double evaluateAt(Point2D point2D) {
                return point2D.x() *point2D.x();
            }
        });

        assertEquals(17.250000000000025, value, DELTA);
    };
    @Test void test4() {
        Triangle domain = new Triangle(
                new Point2D(3,3),
                new Point2D( -4, 6),
                new Point2D( 5, 2));
        GaussianQuadrature quadrature = new GaussianQuadrature(20, domain);
        double value = quadrature.integrate(new ScalarFunction2D() {
            @Override
            public Double evaluateAt(Point2D point2D) {
                return Math.cos(point2D.x()+point2D.y());
            }
        });

        assertEquals(0.110069462621288d, value, DELTA);
    };
    @Test void test5() {
        Triangle domain = new Triangle(
                new Point2D(0,0),
                new Point2D( 1, 0),
                new Point2D( 0, 1));
        GaussianQuadrature quadrature = new GaussianQuadrature(30, domain);
        DoubleMatrix actual = quadrature.integrate(new MatrixFunction2D() {
            @Override
            public DoubleMatrix evaluateAt(Point2D point2D) {
                return DoubleMatrix.identity(4);
            }
        });

        double area = domain.getArea();
        DoubleMatrix expected = DoubleMatrix.identity(4).multiply(area);

        assertArrayEquals(expected.toArray(), actual.toArray(), DELTA);
    };
    @Test void test6() {
        Triangle domain = new Triangle(
                new Point2D(0,0),
                new Point2D( 2, 0),
                new Point2D( 0, 2));
        GaussianQuadrature quadrature = new GaussianQuadrature(30, domain);
        DoubleMatrix actual = quadrature.integrate(new MatrixFunction2D() {
            @Override
            public DoubleMatrix evaluateAt(Point2D point2D) {
                return DoubleMatrix.identity(4).multiply(point2D.x() + point2D.y());
            }
        });

        double area = domain.getArea();
        DoubleMatrix expected = DoubleMatrix.identity(4).multiply(area);

        assertArrayEquals(expected.toArray(), actual.toArray(), DELTA);
    };

}