package com.alura.dyno.math.graphics;

import com.alura.dyno.math.linalg.Algebra;
import com.alura.dyno.math.linalg.AbstractFloatMatrix;
import cern.colt.matrix.tfloat.FloatFactory1D;
import cern.colt.matrix.tfloat.FloatMatrix1D;

public class GraphicMatrix extends AbstractFloatMatrix<GraphicMatrix> {

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
        this.preMultiply(Algebra.graphicMatrixBuilder().translation(delta));
        return this;
    }
    public GraphicMatrix rotateEuler(Vector3 euler) {
        this.preMultiply(Algebra.graphicMatrixBuilder().rotateEuler(euler));
        return this;
    }
    public GraphicMatrix rotate(Vector3 axis, float angle) {
        this.preMultiply(Algebra.graphicMatrixBuilder().rotate(axis,angle));
        return this;
    }
    public GraphicMatrix rotate(Quaternion q) {
        this.preMultiply(Algebra.graphicMatrixBuilder().rotate(q));
        return this;
    }
    public GraphicMatrix scale(Vector3 delta) {
        this.preMultiply(Algebra.graphicMatrixBuilder().scale(delta));
        return this;
    }

    public float[] toArray() {
        return this.clone().transpose().data.vectorize().toArray();
    }
    public float[] toTransposedArray() {
        return this.clone().data.vectorize().toArray();
    }
    @Override public GraphicMatrix clone() {
        return new GraphicMatrix(this);
    }
}
