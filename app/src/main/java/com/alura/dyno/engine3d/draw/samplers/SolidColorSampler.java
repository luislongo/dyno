package com.alura.dyno.engine3d.draw.samplers;

import com.alura.dyno.engine3d.draw.samplers.IColorSampler;
import com.alura.dyno.engine3d.utils.RGBAColor;
import com.alura.dyno.math.graphics.Vector3;

public class SolidColorSampler implements IColorSampler {
    private RGBAColor color;

    public SolidColorSampler(RGBAColor color) {
        this.color = color;
    }

    public RGBAColor getColor() {
        return color;
    }
    public void setColor(RGBAColor color) {
        this.color = color;
    }

    @Override
    public RGBAColor sampleAt(Vector3 input) {
        return color;
    }
}
