package com.alura.dyno.engine3d.glyph;

import com.alura.dyno.math.graphics.GraphicMatrix;

public interface ICamera {
    GraphicMatrix getProjectionMatrix();
    GraphicMatrix getViewMatrix();
    GraphicMatrix getVPMatrix();
}
