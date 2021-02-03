package com.alura.dyno.maths.graphics;

import android.graphics.RectF;

public interface IGraphicMatrixFactory {
    public GraphicMatrix frustum(RectF rect, float near, float far);
    public GraphicMatrix orthogonal(RectF rect, float near, float far);
    public GraphicMatrix perspective(float fov, float aspect, float near, float far);
    public GraphicMatrix lookAt(Vector3 eye, Vector3 center, Vector3 up);
    public GraphicMatrix identity();
}
