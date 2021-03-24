package com.alura.dyno.engine3d.draw.samplers;

import com.alura.dyno.math.graphics.FloatVector;

public interface ISampler<IN, OUT> {
    OUT sampleAt(IN input);
}
