package com.alura.dyno.engine3d.vertex;

import com.alura.dyno.engine3d.utils.RGBAColor;
import com.alura.dyno.maths.graphics.Vector2F;
import com.alura.dyno.maths.graphics.Vector3F;

public interface VertexTransform {
    Vector3F transformPosition(Vector3F v);
    Vector3F transformNormal(Vector3F v);
    RGBAColor transformColor(RGBAColor c);
    Vector2F transformUVs(Vector2F v);
}
