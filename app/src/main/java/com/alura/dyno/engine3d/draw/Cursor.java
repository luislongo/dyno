package com.alura.dyno.engine3d.draw;

import com.alura.dyno.engine3d.utils.RGBAColor;
import com.alura.dyno.math.graphics.Axii;
import com.alura.dyno.math.graphics.GraphicMatrix;
import com.alura.dyno.math.graphics.GraphicMatrixFactory;
import com.alura.dyno.math.graphics.Vector3;
import com.alura.dyno.math.linalg.Algebra;

public class Cursor {
    public Axii axii;

    public Cursor() {
        axii = new Axii();
    }

    public GraphicMatrix getMatrix() {
        return axii.getModelMatrix();
    }


}
