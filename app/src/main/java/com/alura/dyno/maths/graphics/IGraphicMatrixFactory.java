package com.alura.dyno.maths.graphics;

import android.graphics.RectF;

public interface IGraphicMatrixFactory {
    public GraphicMatrix frustum(RectF rect, float near, float far);
    public GraphicMatrix orthogonal(RectF rect, float near, float far);
    public GraphicMatrix perspective(float fov, float aspect, float near, float far);
    public GraphicMatrix lookAt(Vector3F eye, Vector3F center, Vector3F up);
    public GraphicMatrix identity();
}
