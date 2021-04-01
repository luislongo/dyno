package com.alura.dyno.engine3d.glyph;

import com.alura.dyno.math.graphics.GraphicMatrix;
import com.alura.dyno.math.graphics.Vector2;
import com.alura.dyno.math.graphics.Vector3;
import com.alura.dyno.math.linalg.Algebra;

public class Camera extends Glyph implements ICamera {
    private GraphicMatrix viewMatrix;
    private GraphicMatrix projectionMatrix;
    private ProjectionType projectionType = ProjectionType.ORTHOGONAL;
    private Vector2 screenSize;

    private float near;
    private float far;

    private float zoom;

    public Camera(String name, float zNear, float zFar, float zoom) {
        super(name);

        this.near = zNear;
        this.far = zFar;
        this.zoom = zoom;
    }

    public GraphicMatrix getProjectionMatrix() {
        return projectionMatrix;
    }
    public GraphicMatrix getViewMatrix() {
        return viewMatrix;
    }
    public GraphicMatrix getVPMatrix() {
        return viewMatrix.clone().preMultiply(projectionMatrix);
    }
    public float getZoom() {
        return zoom;
    }
    public float getNear() {
        return near;
    }
    public float getFar() {
        return far;
    }
    public Vector2 getScreenSize() {
        return screenSize;
    }

    public void setScreenSize(Vector2 screenSize) {
        this.screenSize = screenSize;
        invalidate();
    }
    public void setNear(float near) {
        this.near = near;
        updateProjectionMatrix();
    }
    public void setFar(float far) {
        this.far = far;
        updateProjectionMatrix();
    }
    public void setZoom(float zoom) {
        this.zoom = zoom;
        invalidate();
    }

    public void invalidate() {
        updateViewMatrix();
        updateProjectionMatrix();
    }
    private void updateViewMatrix() {
        Vector3 eye = transform().getGlobalPosition();
        Vector3 center = eye.clone().plus(new Vector3(0.0f, 0.0f, -1.0f));
        Vector3 up = new Vector3(0.0f, 1.0f, 0.0f);

        viewMatrix = Algebra.graphicMatrixFactory().lookAt(eye, center, up);
    }
    private void updateProjectionMatrix() {
        switch(projectionType) {
            case ORTHOGONAL:
                calculateOrthogonalProjectionMatrix();
                break;
            case PERSPECTIVE:
                calculatePerspectiveProjectionMatrix();
                break;
        }
    }
    private void calculateOrthogonalProjectionMatrix() {
        if (screenSize.norm2() != 0) {
            Vector3 position = transform().getGlobalPosition();

            float left = position.x() - screenSize.x() / zoom ;
            float right = position.x() + screenSize.x() / zoom ;
            float bottom = position.y() - screenSize.y() / zoom;
            float top = position.y() + screenSize.y() / zoom;

            projectionMatrix = Algebra.graphicMatrixFactory()
                    .orthogonal(left, right, bottom, top, near, far);
        }
    }
    private void calculatePerspectiveProjectionMatrix() {
        float aspect = screenSize.x() / screenSize.y();

        projectionMatrix = Algebra.graphicMatrixFactory()
                .perspective(60, aspect, near, far);
    }

    /*public float fromScreenToView(float screenDistance) {
        return screenDistance / zoom;
    }
    public Vector2 fromScreenToView(Vector2 screenCoords) {
        float normalizedX = 2.0f * (screenCoords.x() / screenSize.x()) - 1.0f;
        float normalizedY = 1.0f - 2 * (screenCoords.y() / screenSize.y());

        float viewX = viewRect.centerX() + 0.5f * normalizedX * viewRect.width();
        float viewY = viewRect.centerY() + 0.5f * normalizedY * -viewRect.height();

        return new Vector2(viewX, viewY);
    }
    public float fromViewToScreen(float viewDistance) {
        return viewDistance * zoom;
    }
    public Vector2 fromViewToScreen(Vector2 viewCoords) {
        float normalizedX = 2.0f * (viewCoords.x() - viewRect.centerX()) / viewRect.width();
        float normalizedY = 2.0f * (viewCoords.y() - viewRect.centerY()) / -viewRect.height();

        float screenX = 0.5f * (normalizedX + 1.0f) * screenSize.x();
        float screenY = 0.5f * (1.0f - normalizedY) * screenSize.y();

        return new Vector2(screenX, screenY);
    }
*/
}
