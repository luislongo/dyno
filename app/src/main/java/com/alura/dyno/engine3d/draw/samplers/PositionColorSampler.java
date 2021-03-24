package com.alura.dyno.engine3d.draw.samplers;

import com.alura.dyno.engine3d.utils.RGBAColor;
import com.alura.dyno.math.graphics.Vector3;

public class PositionColorSampler implements IColorSampler {
    public PositionColorSampler() {

    }

    @Override
    public RGBAColor sampleAt(Vector3 input) {
        input.normalize();
        return new RGBAColor(input.x()*input.x(),
                                input.y()*input.y(),
                input.z()*input.z(), 1.0f);
    }
}
