package com.alura.dyno.engine3d.vertex;

import com.alura.dyno.engine3d.utils.RGBAColor;
import com.alura.dyno.maths.graphics.Vector2;
import com.alura.dyno.maths.graphics.Vector3;

public interface VertexTransform {
    Vector3 transformPosition(Vector3 v);
    Vector3 transformNormal(Vector3 v);
    RGBAColor transformColor(RGBAColor c);
    Vector2 transformUVs(Vector2 v);
}
