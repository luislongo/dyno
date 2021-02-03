package com.alura.dyno.maths.graphics;

import com.alura.dyno.maths.linalg.FloatMatrix;

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

    @Override public GraphicMatrix clone() {
        return new GraphicMatrix(this);
    }
}
