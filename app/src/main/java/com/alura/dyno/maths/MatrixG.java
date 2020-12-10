package com.alura.dyno.maths;

import cern.colt.matrix.tfloat.FloatFactory1D;
import cern.colt.matrix.tfloat.FloatMatrix1D;
import cern.colt.matrix.tfloat.algo.DenseFloatAlgebra;
import cern.colt.matrix.tfloat.impl.DenseFloatMatrix2D;

public class MatrixG extends DenseFloatMatrix2D {
    public static final DenseFloatAlgebra fun = new DenseFloatAlgebra();

    public MatrixG() {
        super(new float[4][4]);
    }
    public MatrixG(float[] floats) {
        this();
        assign(floats);
    }
    public MatrixG(float[][] floats) {
        this();
        assign(floats);
    }

    public MatrixG translate(Vector3G deltaPos) {
        this.preMultiply(MatrixGFactory.fromTranslation(deltaPos));
        return this;
    }
    public MatrixG scale(Vector3G deltaScale) {
        this.preMultiply(MatrixGFactory.fromScale(deltaScale));

        return this;
    }
    public MatrixG rotate(Vector3G eulerAngles) {
        this.preMultiply(MatrixGFactory.fromEulerAngles(eulerAngles));
        return this;
    }

    //TODO Implement shear transformation
    public void shear(Vector3G angles) {
        throw new RuntimeException("Not implemented");
    }

    public MatrixG invert() {
        assign(fun.inverse(this));
        return this;
    }
    public MatrixG transpose() {
        assign(fun.transpose(this));
        return this;
    }
    public MatrixG preMultiply(MatrixG preMatrix) {
        assign(fun.mult(preMatrix, this));
        return this;
    }
    public MatrixG postMultiply(MatrixG postMatrix) {
        assign(fun.mult(this, postMatrix));
        return this;
    }

    public static MatrixG multiply(MatrixG m_left, MatrixG m_right) {
        MatrixG res = new MatrixG(m_left.toArray());
        res.postMultiply(m_right);

        return res;
    }

    public float[] to1DArray() {
        FloatMatrix1D v = FloatFactory1D.dense.make(new FloatMatrix1D[]{
                viewRow(0), viewRow(1), viewRow(2), viewRow(3)
                }
        );

        return v.toArray();
    }

}
