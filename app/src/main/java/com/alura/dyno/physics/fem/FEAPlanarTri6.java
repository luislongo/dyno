package com.alura.dyno.physics.fem;

import com.alura.dyno.math.calculus.GaussianQuadrature;
import com.alura.dyno.math.calculus.MatrixFunction2D;
import com.alura.dyno.math.cartesian.Point;
import com.alura.dyno.math.cartesian.Triangle;
import com.alura.dyno.math.linalg.DoubleMatrix;
import com.alura.dyno.math.linalg.AbstractDoubleVector;
import com.alura.dyno.math.cartesian.Point2D;
import com.alura.dyno.math.linalg.DoubleVectorN;

public class FEAPlanarTri6 implements IStiffElement, IMassiveElement {
    Triangle triangle;
    GaussianQuadrature quadrature;
    double area;

    DoubleMatrix massMatrix;
    DoubleMatrix stiffnessMatrix;

    public FEAPlanarTri6(Point2D pointA, Point2D pointB, Point2D pointC) {
        triangle = new Triangle(pointA, pointB, pointC);
        area = triangle.getArea();
        quadrature = new GaussianQuadrature(3, this.triangle);
    }

    public DoubleMatrix calculateShapeMatrixAt(Point2D point) {
        double N1 = calculateN1At(point);
        double N2 = calculateN2At(point);
        double N3 = calculateN3At(point);
        double N4 = 4 * N1 * N2;
        double N5 = 4 * N2 * N3;
        double N6 = 4 * N3 * N1;

        return new DoubleMatrix(new double[][] {
                {N1, 0d, N2, 0d, N3, 0d, N4, 0d, N5, 0d, N6, 0d},
                {0d, N1, 0d, N2, 0d, N3, 0d, N4, 0d, N5, 0d, N6}
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

    @Override public DoubleMatrix getStiffness() {
        double mu = 0.2d;
        double E = 10000d;

        DoubleMatrix ctve = new DoubleMatrix(3, 3,
            1d - mu,      mu,                  0,
                 mu, 1d - mu,                  0,
                  0,       0,(1d - 2d * mu) / 2d );
        double coef = E / (1 + mu) / (1 - 2 * mu);
        ctve.multiply(coef);

        return quadrature.integrate(new MatrixFunction2D() {

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
    public DoubleMatrix calculateDerivativeMatrixAt(Point2D point) {
        double halfOverArea = 0.5d / area;
        double DN1dx = halfOverArea * (triangle.getB().y() - triangle.getC().y());
        double DN2dx = halfOverArea * (triangle.getC().y() - triangle.getA().y());
        double DN3dx = halfOverArea * (triangle.getA().y() - triangle.getB().y());
        double DN1dy = halfOverArea * (triangle.getB().x() - triangle.getC().x());
        double DN2dy = halfOverArea * (triangle.getC().x() - triangle.getA().x());
        double DN3dy = halfOverArea * (triangle.getA().x() - triangle.getB().x());

        double DN4dx = 4.0d * (DN1dx * calculateN2At(point) + DN2dx * calculateN1At(point));
        double DN5dx = 4.0d * (DN2dx * calculateN3At(point) + DN3dx * calculateN2At(point));
        double DN6dx = 4.0d * (DN3dx * calculateN1At(point) + DN1dx * calculateN3At(point));

        double DN4dy = 4.0d * (DN1dy * calculateN2At(point) + DN2dy * calculateN1At(point));
        double DN5dy = 4.0d * (DN2dy * calculateN3At(point) + DN3dy * calculateN2At(point));
        double DN6dy = 4.0d * (DN3dy * calculateN1At(point) + DN1dy * calculateN3At(point));

        return new DoubleMatrix(3,12,
                DN1dx,     0, DN2dx,     0, DN3dx,     0, DN4dx,     0, DN5dx,     0, DN6dx,     0,
                0, DN1dy,     0, DN2dy,     0, DN3dy,     0, DN4dy,     0, DN5dy,     0, DN6dy,
                DN1dy, DN1dx, DN2dy, DN2dx, DN3dy, DN3dx, DN4dy, DN4dx, DN5dy, DN5dx, DN6dy, DN6dx);
    }

}
