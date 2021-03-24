package com.alura.dyno.math.graphics;

import com.alura.dyno.math.linalg.Algebra;

import cern.colt.matrix.tfloat.FloatFactory1D;
import cern.colt.matrix.tfloat.FloatMatrix1D;

public class GraphicMatrix extends FloatMatrix<GraphicMatrix> {

    public GraphicMatrix() {
        super(4, 4);
    }
    public GraphicMatrix(float value) {
        super(4, 4, value);
    }
    public GraphicMatrix(float[] values) {
        super(4, 4, values);
    }
    public GraphicMatrix(GraphicMatrix other) {
        super(other);
    }

    public GraphicMatrix translate(Vector3 delta) {
        this.preMultiply(Algebra.graphicMatrixFactory().translation(delta));
        return this;
    }
    public GraphicMatrix rotateEuler(Vector3 euler) {
        this.preMultiply(Algebra.graphicMatrixFactory().rotateEuler(euler));
        return this;
    }
    public GraphicMatrix rotate(Vector3 axis, float angle) {
        this.preMultiply(Algebra.graphicMatrixFactory().rotate(axis,angle));
        return this;
    }
    public GraphicMatrix scale(Vector3 delta) {
        this.preMultiply(Algebra.graphicMatrixFactory().scale(delta));
        return this;
    }

    public float[] toArray() {
        FloatMatrix1D row1 = data.viewRow(0);
        FloatMatrix1D row2 = data.viewRow(1);
        FloatMatrix1D row3 = data.viewRow(2);
        FloatMatrix1D row4 = data.viewRow(3);

        FloatMatrix1D as1D = FloatFactory1D.dense.make(new FloatMatrix1D[]
                {row1, row2, row3, row4});
        return as1D.toArray();
    }
    public float[] toTransposedArray() {
        FloatMatrix1D col0 = data.viewColumn(0);
        FloatMatrix1D col1 = data.viewColumn(1);
        FloatMatrix1D col2 = data.viewColumn(2);
        FloatMatrix1D col3 = data.viewColumn(3);

        FloatMatrix1D as1D = FloatFactory1D.dense.make(new FloatMatrix1D[]
                {col0, col1, col2, col3});
        return as1D.toArray();
    }
    @Override public GraphicMatrix clone() {
        return new GraphicMatrix(this);
    }
}
