package com.alura.dyno.engine3d.draw;

import com.alura.dyno.engine3d.utils.RGBAColor;
import com.alura.dyno.math.graphics.GraphicMatrix;
import com.alura.dyno.math.graphics.GraphicMatrixFactory;

public class Cursor {
    public GraphicMatrix preMatrix;


    public Cursor() {
        preMatrix = new GraphicMatrixFactory().identity();
    }
}
