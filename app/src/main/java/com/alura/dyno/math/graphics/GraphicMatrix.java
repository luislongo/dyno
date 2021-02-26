package com.alura.dyno.math.graphics;

import android.opengl.Matrix;

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
        this.data.setQuick(0, 3, this.data.getQuick(0,3) + delta.x());
        this.data.setQuick(1, 3, this.data.getQuick(1,3) + delta.y());
        this.data.setQuick(2, 3, this.data.getQuick(2,3) + delta.z());
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
        this.data.setQuick(0,0, this.data.getQuick(0,0) * delta.x());
        this.data.setQuick(1,1, this.data.getQuick(1,1) * delta.y());
        this.data.setQuick(2,2, this.data.getQuick(2,2) * delta.z());
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
    @Override public GraphicMatrix clone() {
        return new GraphicMatrix(this);
    }
}
