package com.alura.dyno.engine3d.components;

import android.graphics.RectF;

import com.alura.dyno.maths.MathExtra;
import com.alura.dyno.maths.Matrix4F;
import com.alura.dyno.maths.Vector2F;
import com.alura.dyno.maths.Vector3F;

public class Camera extends MonoBehaviour implements
        ComponentEvent.IOnDragEventListener,
        ComponentEvent.IOnScaleEventListener,
        ComponentEvent.IOnScreenSizeChangedEventListener {

    private Matrix4F viewMatrix;
    private Matrix4F projectionMatrix;

    private Vector2F screenSize;

    private float zNear;
    private float zFar;

    private float zoom;
    private float minZoom = 1;
    private float maxZoom = Float.MAX_VALUE;

    private RectF viewRect;

    public Camera(CameraBuilder builder) {
        super(builder);

        this.screenSize = builder.screenSize;

        this.zNear = builder.zNear;
        this.zFar = builder.zFar;

        this.zoom = builder.zoom;
        this.minZoom = builder.minZoom;
        this.maxZoom = builder.maxZoom;
    }

    @Override public void onCreate(ComponentEvent.OnCreateEvent event) {
        super.onCreate(event);

        viewRect = new RectF();

        updateViewMatrix();
        updateProjectionMatrix();
    }
    @Override public void onDrag(ComponentEvent.OnDragEvent event) {
        Vector3F distance = new Vector3F(event.getViewDistance(), 0.0f);
        getParent().getLocalTransform().move(distance);

        updateProjectionMatrix();
        updateViewMatrix();
    }
    @Override public void onScale(ComponentEvent.OnScaleEvent event) {
        float scaleFactor = event.getScaleFactor();
        zoom = MathExtra.clamp(zoom * scaleFactor, minZoom, maxZoom);

        updateProjectionMatrix();
    }
    @Override public void onScreenSizeChanged(ComponentEvent.OnScreenSizeChangedEvent event) {
        this.screenSize.setValues(event.getWidth(), event.getHeight());
        updateProjectionMatrix();
    }
    private boolean isValidZoom(float scaleFactor) {
        return !((zoom == minZoom) || (zoom == maxZoom));
    }

    private void updateViewMatrix() {
        Vector3F eye = getParent().getLocalTransform().getPosition();
        Vector3F center = new Vector3F(0.0f, 0.0f, -1.0f);
        Vector3F up = new Vector3F(0.0f, 1.0f, 0.0f);

        viewMatrix = Matrix4F.lookAt(eye, center, up);
    }
    private void updateProjectionMatrix() {
        updateViewSpaceCamBounds();

        if (isViewRectValid()) {
            projectionMatrix = Matrix4F.orthogonal(viewRect, zNear, zFar);
        }
    }
    private void updateViewSpaceCamBounds() {
        Vector3F position = getParent().getGlobalTransform().getPosition();

        viewRect.left = position.x() - 0.5f * screenSize.x() / zoom;
        viewRect.right = position.x() + 0.5f * screenSize.x() / zoom;
        viewRect.bottom = position.y() - 0.5f * screenSize.y() / zoom;
        viewRect.top = position.y() + 0.5f * screenSize.y() / zoom;
    }
    private boolean isViewRectValid()
    {
        boolean hasHeight = viewRect.height() < 0;
        boolean hasWidth = viewRect.width() > 0;
        boolean leftLessThanRight = viewRect.left < viewRect.right;
        boolean bottomLassThanTop = viewRect.bottom < viewRect.top;

        return hasHeight && hasWidth && leftLessThanRight && bottomLassThanTop;
    }

    public float getZoom() {
        return zoom;
    }
    public Matrix4F getProjectionMatrix() {
        updateProjectionMatrix();
        return projectionMatrix;
    }
    public Matrix4F getViewMatrix() {
        updateViewMatrix();
        return viewMatrix;
    }
    public Matrix4F getVPMatrix() {
        return Matrix4F.multiply(projectionMatrix, viewMatrix);
    }

    public static float fromScreenToView(Camera cam, float screenDistance) {
        return screenDistance / cam.zoom;
    }
    public static Vector2F fromScreenToView(Camera cam, Vector2F screenCoords) {
        float normalizedX = 2.0f * (screenCoords.x() / cam.screenSize.x()) - 1.0f;
        float normalizedY = 1.0f - 2 * (screenCoords.y() / cam.screenSize.y());

        float viewX = cam.viewRect.centerX() + 0.5f * normalizedX * cam.viewRect.width();
        float viewY = cam.viewRect.centerY() + 0.5f * normalizedY * -cam.viewRect.height();

        return new Vector2F(viewX, viewY);
    }
    public static float fromViewToScreen(Camera cam, float viewDistance) {
        return viewDistance * cam.zoom;
    }
    public static Vector2F fromViewToScreen(Camera cam, Vector2F viewCoords) {
        float normalizedX = 2.0f * (viewCoords.x() - cam.viewRect.centerX()) / cam.viewRect.width();
        float normalizedY = 2.0f * (viewCoords.y() - cam.viewRect.centerY()) / -cam.viewRect.height();

        float screenX = 0.5f * (normalizedX + 1.0f) * cam.screenSize.x();
        float screenY = 0.5f * (1.0f - normalizedY) * cam.screenSize.y();

        return new Vector2F(screenX, screenY);
    }

    public static class CameraBuilder<T extends CameraBuilder<T>>
            extends MonoBehaviourBuilder<T> {
        protected Vector2F screenSize;

        protected float zNear = 0.0f;
        protected float zFar = 1.0f;

        protected float zoom = 1;
        protected float minZoom = 1;
        protected float maxZoom = Float.MAX_VALUE;

        public CameraBuilder(String name) {
            super(name);
        }
        public Camera build() {
            return new Camera(this);
        }

        public T setScreenSize(float screenWidth, float screenHeight) {
            this.screenSize = new Vector2F(screenWidth, screenHeight);
            return (T) this;
        }
        public T setScreenSize(Vector2F screenSize) {
            return setScreenSize(screenSize.x(), screenSize.y());
        }
        public T setClippingPlanes(float zNear, float zFar) {
            this.zNear = zNear;
            this.zFar = zFar;

            return (T) this;
        }
        public T setZoomConstraints(float minZoom, float maxZoom) {
            this.maxZoom = maxZoom;
            this.minZoom = minZoom;

            return (T) this;
        }
        public T setZoom(float zoom) {
            this.zoom = zoom;

            return (T) this;
        }

        public static CameraBuilder<?> builder(String name) {
            return new CameraBuilder<>(name);
        }
    }
}
