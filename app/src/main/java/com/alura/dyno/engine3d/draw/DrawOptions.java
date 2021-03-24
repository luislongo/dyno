package com.alura.dyno.engine3d.draw;

import com.alura.dyno.engine3d.draw.samplers.IColorSampler;
import com.alura.dyno.engine3d.draw.samplers.IUVSampler;
import com.alura.dyno.engine3d.draw.samplers.Position2DUVSampler;
import com.alura.dyno.engine3d.draw.samplers.PositionColorSampler;
import com.alura.dyno.engine3d.draw.samplers.SolidColorSampler;
import com.alura.dyno.engine3d.utils.RGBAColor;
import com.alura.dyno.math.graphics.GraphicMatrix;
import com.alura.dyno.math.graphics.GraphicMatrixFactory;

public class DrawOptions {
    public GraphicMatrix preMatrix;
    public IColorSampler colorSampler;
    public IUVSampler uvSampler;

    public DrawOptions() {
        preMatrix = new GraphicMatrixFactory().identity();
        colorSampler = new SolidColorSampler(RGBAColor.ACQUA_GREEN);
        uvSampler = new Position2DUVSampler();
    }
}
