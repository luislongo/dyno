package com.alura.dyno.physics.fem;

import com.alura.dyno.math.calculus.GaussianQuadrature;
import com.alura.dyno.math.calculus.MatrixFunction2D;
import com.alura.dyno.math.cartesian.Point2D;
import com.alura.dyno.math.cartesian.Triangle;
import com.alura.dyno.math.linalg.DoubleMatrix;

public class FEAPlanarTri3 implements IStiffElement, IMassiveElement {
    Triangle triangle;
    GaussianQuadrature quadrature;
    double area;

    DoubleMatrix massMatrix;
    DoubleMatrix stiffnessMatrix;

    public FEAPlanarTri3(Point2D pointA, Point2D pointB, Point2D pointC) {
        triangle = new Triangle(pointA, pointB, pointC);
        area = triangle.getArea();
        quadrature = new GaussianQuadrature(3, this.triangle);
    }

    @Override public DoubleMatrix getMassMatrix() {
        if(massMatrix == null) {
            calculateMassMatrix();
        }

        return massMatrix;
    }
    private void calculateMassMatrix() {
        massMatrix = quadrature.integrate(new MatrixFunction2D() {

            @Override public DoubleMatrix evaluateAt(Point2D point2D) {
                DoubleMatrix shape = calculateShapeMatrixAt(point2D);
                return DoubleMatrix.multiply(DoubleMatrix.transpose(shape), shape);
            }
        });
    }
    public DoubleMatrix calculateShapeMatrixAt(Point2D point) {
        double N1 = calculateN1At(point);
        double N2 = calculateN2At(point);
        double N3 = calculateN3At(point);

        return new DoubleMatrix(new double[][] {
                {N1, 0d, N2, 0d, N3, 0d},
                {0d, N1, 0d, N2, 0d, N3}
        });
    }
    private double calculateN1At(Point2D point) {
        Triangle subArea = new Triangle(point, triangle.getB(), triangle.getC());
        return subArea.getArea() / area;
    }
    private double calculateN2At(Point2D point) {
        Triangle subArea = new Triangle(point, triangle.getC(), triangle.getA());
        return subArea.getArea() / area;
    }
    private double calculateN3At(Point2D point) {
        Triangle subArea = new Triangle(point, triangle.getA(), triangle.getB());
        return subArea.getArea() / area;
    }

    @Override public DoubleMatrix getStiffness() {
       if(stiffnessMatrix == null) {
           calculateStiffnessMatrix();
       }

       return stiffnessMatrix;
    }
    private void calculateStiffnessMatrix() {
        double mu = 0.2d;
        double E = 10000d;

        DoubleMatrix ctve = new DoubleMatrix(3, 3,
                1d - mu,      mu,                  0,
                mu, 1d - mu,                  0,
                0,       0,(1d - 2d * mu) / 2d );
        double coef = E / (1 + mu) / (1 - 2 * mu);
        ctve.multiply(coef);

        stiffnessMatrix = quadrature.integrate(new MatrixFunction2D() {

            @Override
            public DoubleMatrix evaluateAt(Point2D point2D) {
                DoubleMatrix deriv = calculateDerivativeMatrixAt(point2D);
                return DoubleMatrix.multiply(
                        DoubleMatrix.transpose(deriv),
                        DoubleMatrix.multiply(
                                ctve, deriv
                        ));
            }
        });
    }
    private DoubleMatrix calculateDerivativeMatrixAt(Point2D point) {
        double halfOverArea = 0.5d / area;
        double DN1dx = halfOverArea * (triangle.getB().y() - triangle.getC().y());
        double DN2dx = halfOverArea * (triangle.getC().y() - triangle.getA().y());
        double DN3dx = halfOverArea * (triangle.getA().y() - triangle.getB().y());
        double DN1dy = halfOverArea * (triangle.getB().x() - triangle.getC().x());
        double DN2dy = halfOverArea * (triangle.getC().x() - triangle.getA().x());
        double DN3dy = halfOverArea * (triangle.getA().x() - triangle.getB().x());

        return new DoubleMatrix(3, 6,
                DN1dx,     0, DN2dx,     0, DN3dx,     0,
                    0, DN1dy,     0, DN2dy,     0, DN3dy,
                DN1dy, DN1dx, DN2dy, DN2dx, DN3dy, DN3dx);
    }

}
