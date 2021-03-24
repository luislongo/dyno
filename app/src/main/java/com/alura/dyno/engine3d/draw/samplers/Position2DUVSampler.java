package com.alura.dyno.engine3d.draw.samplers;

import com.alura.dyno.math.graphics.Vector2;
import com.alura.dyno.math.graphics.Vector3;

public class Position2DUVSampler implements IUVSampler {
    @Override
    public Vector2 sampleAt(Vector3 input) {
        return new Vector2(input.x(), input.y());
    }
}
