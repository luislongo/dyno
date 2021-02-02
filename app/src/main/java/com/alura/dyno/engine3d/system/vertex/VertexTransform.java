package com.alura.dyno.engine3d.system.vertex;

import com.alura.dyno.engine3d.utils.RGBAColor;
import com.alura.dyno.maths.Vector2F;
import com.alura.dyno.maths.Vector3F;

public interface VertexTransform {
    Vector3F transformPosition(Vector3F v);
    Vector3F transformNormal(Vector3F v);
    RGBAColor transformColor(RGBAColor c);
    Vector2F transformUVs(Vector2F v);
}