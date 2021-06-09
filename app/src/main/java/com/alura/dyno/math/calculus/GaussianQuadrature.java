package com.alura.dyno.math.calculus;

import com.alura.dyno.engine3d.utils.Tuple;
import com.alura.dyno.math.MathExtra;
import com.alura.dyno.math.cartesian.Point2D;
import com.alura.dyno.math.cartesian.Point3D;
import com.alura.dyno.math.cartesian.Triangle;
import com.alura.dyno.math.linalg.Algebra;
import com.alura.dyno.math.linalg.DoubleMatrix;
import com.alura.dyno.math.linalg.DoubleVectorN;
import java.util.ArrayList;
import java.util.List;
import cern.colt.matrix.tdouble.algo.decomposition.DenseDoubleEigenvalueDecomposition;
import cern.jet.stat.tdouble.Gamma;

import static cern.jet.math.tdouble.DoubleFunctions.pow;

public class GaussianQuadrature {
    Point2D[] points;
    DoubleVectorN weights;
    int degree;
    private static Triangle normalised = new Triangle(
            new Point2D(-1, -1),
            new Point2D( 1, -1),
            new Point2D( -1, 1)
    );

    public GaussianQuadrature(int degree, Triangle domain) {
        this.degree = degree;
        Tuple<List<Point3D>, List<Double>> triData = quadTriangleProduct(degree, domain);
        quadTrianglePointsAndWeights(triData, domain);
    }
    public double integrate(ScalarFunction2D function) {
        DoubleVectorN values = new DoubleVectorN(points.length, 0d);

        for(int i = 0; i < values.length(); i++) {
            values.set(i, function.evaluateAt(points[i]));
        }

        return values.dotProduct(weights);
    }
    public DoubleMatrix integrate(MatrixFunction2D function) {
        DoubleVectorN values = new DoubleVectorN(points.length, 0d);

        DoubleMatrix m = function.evaluateAt(points[0]).multiply(weights.get(0));
        for(int i = 1; i < values.length(); i++) {
            m.plus(function.evaluateAt(points[i]).multiply(weights.get(i)));
        }

        return m;
    }

    private Tuple<List<Point3D>, List<Double>> quadTriangleProduct(int d, Triangle domain) {
        int n = (int) Math.ceil(0.5d * (d+1));
        QuadData Q1 = quadGaussJacobi(n, domain, 0,0);
        QuadData Q2 = quadGaussJacobi(n, domain, 1,0);

        List<Point2D> cartesianPoints = new ArrayList<>();
        List<Double> weights = new ArrayList<>();
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                cartesianPoints.add(new Point2D(Q1.first.get(i), Q2.first.get(j)));
                weights.add(Q1.second.get(i) * Q2.second.get(j) * 0.25d);
            }
        }

        List<Point3D> barycentrics = new ArrayList<>();
        for(Point2D cartesian : cartesianPoints) {
            Point2D p = new Point2D(0.5d * (1 + cartesian.x())*(1-cartesian.y())-1, cartesian.y());
            barycentrics.add(normalised.toBarycentric(p));
        }

        return new Tuple<>(barycentrics, weights);
    }
    private void quadTrianglePointsAndWeights(Tuple<List<Point3D>, List<Double>> triData, Triangle domain) {
        int size = triData.first.size();
        points = new Point2D[size];
        weights = new DoubleVectorN(size, 0d);

        for(int i = 0; i < triData.first.size(); i++) {
            double b3 = 1 - (triData.first.get(i).x()+triData.first.get(i).y());
            double x = domain.getA().x() * triData.first.get(i).x()
                    +  domain.getB().x() * triData.first.get(i).y()
                    +  domain.getC().x() * b3;
            double y = domain.getA().y() * triData.first.get(i).x()
                    +  domain.getB().y() * triData.first.get(i).y()
                    +  domain.getC().y() * b3;

            points[i] = new Point2D(x, y);
            weights.set(i, domain.getArea() * triData.second.get(i));
        }
    }
    private QuadData quadGaussJacobi(int n, Triangle domain, double alpha, double beta) {
        if((alpha == 0) && (beta == 0)) {
            return quadGaussLegendre(n, domain);
        } else {
            DoubleVectorN N = DoubleVectorN.intRange(1, n - 1);

            DoubleVectorN a = a_GJ(N, n, alpha, beta);
            DoubleVectorN b = b_GJ(N, n, alpha, beta);
            DoubleVectorN c = c_GJ(N, n, alpha, beta);

            DoubleVectorN A = A_GJ(a, b);
            DoubleVectorN B = B_GJ(a, c, n);
            DoubleMatrix J = J_GJ(A, B);

            QuadData pw = calculateEigenValues_1(J, alpha, beta);
            return pw;
        }
    }
    private DoubleVectorN a_GJ(DoubleVectorN N, int n, double alpha, double beta) {
        double ab = alpha + beta;

        DoubleVectorN a = new DoubleVectorN(n, 0);
        a.set(0, 0.5d * ab + 1);

        DoubleVectorN v1 = N.clone().multiply(2).plus(new DoubleVectorN(n-1, ab+1));
        DoubleVectorN v2 = N.clone().multiply(2).plus(new DoubleVectorN(n-1, ab+2));
        DoubleVectorN v3 = N.clone().plus(new DoubleVectorN(n-1, 1)).multiply(2);
        DoubleVectorN v4 = N.clone().plus(new DoubleVectorN(n-1, ab+1));

        a.setRange(1, n-1, v1.straightProduct(v2).straightDivision((v3.straightProduct(v4))));
        return a;
    }
    private DoubleVectorN b_GJ(DoubleVectorN N, int n, double alpha, double beta) {
        double ab = alpha + beta;
        double ab2 = alpha * alpha - beta * beta;

        DoubleVectorN b = new DoubleVectorN(n, 0);
        b.set(0, 0.5d * (alpha - beta));

        DoubleVectorN v1 = N.clone().multiply(2).plus(new DoubleVectorN(n-1, ab+1));
        DoubleVectorN v2 = N.clone().plus(new DoubleVectorN(n-1, 1)).multiply(2);
        DoubleVectorN v3 = N.clone().plus(new DoubleVectorN(n-1, ab+1));
        DoubleVectorN v4 = N.clone().multiply(2).plus(new DoubleVectorN(n-1, ab));

        b.setRange(1, n-1,
                v1.multiply(ab2).straightDivision(v2.straightProduct(v3).straightProduct(v4)));

        return b;
    }
    private DoubleVectorN c_GJ(DoubleVectorN N, int n, double alpha, double beta) {
        double ab = alpha + beta;
        DoubleVectorN c = new DoubleVectorN(n, 0);

        DoubleVectorN v1 = N.clone().plus(new DoubleVectorN(n-1, alpha));
        DoubleVectorN v2 = N.clone().plus(new DoubleVectorN(n-1, beta));
        DoubleVectorN v3 = N.clone().multiply(2).plus(new DoubleVectorN(n-1, ab+2));
        DoubleVectorN v4 = N.clone().plus(new DoubleVectorN(n-1, 1));
        DoubleVectorN v5 = N.clone().plus(new DoubleVectorN(n-1, ab+1));
        DoubleVectorN v6 = N.clone().multiply(2).plus(new DoubleVectorN(n-1, ab));

        c.setRange(1, n-1,
                v1.straightProduct(v2)
                  .straightProduct(v3)
                  .straightDivision(v4.straightProduct(v5)
                                      .straightProduct(v6)));

        return c;
    }
    private DoubleVectorN A_GJ(DoubleVectorN a, DoubleVectorN b) {
        return b.clone().multiply(-1.0d).straightDivision(a);
    }
    private DoubleVectorN B_GJ(DoubleVectorN a, DoubleVectorN c, int n) {
        DoubleVectorN B = c.getRange(1,n-1).straightDivision(a.getRange(0,n-2)
                                                              .straightProduct(a.getRange(1,n-1)));
        B.applyFunction(pow(0.5d));

        return B;
    }
    private DoubleMatrix J_GJ(DoubleVectorN A, DoubleVectorN B) {
        DoubleMatrix valueA = Algebra.doubleMatrixFactory().makeDiagonal(1, B);
        DoubleMatrix valueB = Algebra.doubleMatrixFactory().makeDiagonal(A);
        DoubleMatrix valueC = Algebra.doubleMatrixFactory().makeDiagonal(-1,B);

        return valueA.plus(valueB).plus(valueC);
    }
    private QuadData calculateEigenValues_1(DoubleMatrix J, double alpha, double beta) {
        DenseDoubleEigenvalueDecomposition eig = J.getEigenValues();
        DoubleVectorN points = new DoubleVectorN(eig.getRealEigenvalues().toArray());
        DoubleMatrix eigVec = new DoubleMatrix(eig.getV().toArray());

        int[] sortedIdx = points.sort();
        DoubleVectorN w1 = DoubleVectorN.reorder(eigVec.getRow(0), sortedIdx).applyFunction(pow(2));
        double w2 = Math.pow(2.d, alpha + beta + 1);
        double w3 = Math.exp(
                  Gamma.logGamma(alpha + 1)
                + Gamma.logGamma(beta + 1)
                - Gamma.logGamma(alpha + beta + 2));
        DoubleVectorN weights = w1.multiply(w2 * w3);

        QuadData tuple = new QuadData(points, weights);
        return tuple;
    }

    /*
    Q.Weights = (V(1,I).^2*2^(alpha+beta+1).*exp(gammaln(alpha+1)+...
    gammaln(beta+1)-gammaln(alpha+beta+2)))';
    */

    private QuadData quadGaussLegendre(int n, Triangle domain) {
        DoubleMatrix J = J_GL(A_GL(n), B_GL(n));
        QuadData pw = calculateEigenValues(J);

        return pw;
    }
    private DoubleMatrix J_GL(DoubleVectorN A, DoubleVectorN B) {
        DoubleMatrix valueA = Algebra.doubleMatrixFactory().makeDiagonal(1, B);
        DoubleMatrix valueB = Algebra.doubleMatrixFactory().makeDiagonal(A);
        DoubleMatrix valueC = Algebra.doubleMatrixFactory().makeDiagonal(-1,B);

        return valueA.plus(valueB).plus(valueC);
    }
    private DoubleVectorN A_GL(int n) {
        DoubleVectorN range = DoubleVectorN.intRange(0, n-1);

        DoubleVectorN lhs = b_GL(range).multiply(-1);
        DoubleVectorN rhs = a_GL(range);
        return lhs.straightDivision(rhs);
    }
    private DoubleVectorN B_GL(int n) {
        DoubleVectorN rangeA = DoubleVectorN.intRange(1, n-1);
        DoubleVectorN rangeB = DoubleVectorN.intRange(0, n-2);

        DoubleVectorN quad = c_GL(rangeA).straightDivision(a_GL(rangeB).straightProduct(a_GL(rangeA)));
        return quad.applyFunction(pow(0.5d));
    }
    private DoubleVectorN a_GL(DoubleVectorN n) {
        DoubleVectorN lhs = n.clone().multiply(2).plus(new DoubleVectorN(n.length(), 1));
        DoubleVectorN rhs = n.clone().plus(new DoubleVectorN(n.length(), 1));

        return lhs.straightDivision(rhs);
    }
    private DoubleVectorN b_GL(DoubleVectorN n) {
        return new DoubleVectorN(n.length(), 0.0d);
    }
    private DoubleVectorN c_GL(DoubleVectorN n) {
        DoubleVectorN lhs = n.clone();
        DoubleVectorN rhs = n.clone().plus(new DoubleVectorN(n.length(), 1.0d));

        return lhs.straightDivision(rhs);
    }
    private QuadData calculateEigenValues(DoubleMatrix j) {
        DenseDoubleEigenvalueDecomposition eig = j.getEigenValues();
        DoubleVectorN points = new DoubleVectorN(eig.getRealEigenvalues().toArray());
        DoubleMatrix eigVec = new DoubleMatrix(eig.getV().toArray());

        int[] sortedIdx = points.sort();
        DoubleVectorN weights = DoubleVectorN.reorder(eigVec.getRow(0), sortedIdx)
                .applyFunction(pow(2)).multiply(2.0d);

        QuadData tuple = new QuadData(points, weights);
        return tuple;
    }
    /*
    private QuadData transformPointsToDomain(QuadData pw, Triangle domain) {
        double A = 0.5d * (domain.getB().x() - domain.getA().x());
        double B = 0.5d * (domain.getB().x() + domain.getA().x());

        DoubleVectorN points = pw.first.clone();
        DoubleVectorN weights = pw.second.clone();

        points.multiply(A).plus(new DoubleVectorN(points.length(), B));
        weights.multiply(A);

        return new QuadData(points, weights);
    }
     */

    private static class QuadData extends Tuple<DoubleVectorN, DoubleVectorN> {
        public QuadData(DoubleVectorN points, DoubleVectorN weights) {
            super(points, weights);
        }
    }
}
