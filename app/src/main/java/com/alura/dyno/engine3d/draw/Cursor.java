package com.alura.dyno.engine3d.draw;

import com.alura.dyno.math.graphics.Axii;
import com.alura.dyno.math.graphics.GraphicMatrix;

public class Cursor {
    public Axii axii;

    public Cursor() {
        axii = new Axii();
    }

    public GraphicMatrix getMatrix() {
        return axii.getModelMatrix();
    }


}
