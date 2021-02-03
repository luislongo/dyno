package com.alura.dyno.engine3d.components;

import android.graphics.RectF;

import com.alura.dyno.math.MathExtra;
import com.alura.dyno.math.graphics.GraphicMatrix;
import com.alura.dyno.math.graphics.Vector2;
import com.alura.dyno.math.graphics.Vector3;

public class Camera extends MonoBehaviour implements
        ComponentEvent.IOnDragEventListener,
        ComponentEvent.IOnScaleEventListener,
        ComponentEvent.IOnScreenSizeChangedEventListener {

    private GraphicMatrix viewMatrix;
    private GraphicMatrix projectionMatrix;

    private Vector2 screenSize;

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
        Vector3 distance = new Vector3(event.getViewDistance(), 0.0f);
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
        Vector3 eye = getParent().getLocalTransform().getPosition();
        Vector3 center = new Vector3(0.0f, 0.0f, -1.0f);
        Vector3 up = new Vector3(0.0f, 1.0f, 0.0f);

        viewMatrix = GraphicMatrix.lookAt(eye, center, up);
    }
    private void updateProjectionMatrix() {
        updateViewSpaceCamBounds();

        if (isViewRectValid()) {
            projectionMatrix = GraphicMatrix.orthogonal(viewRect, zNear, zFar);
        }
    }
    private void updateViewSpaceCamBounds() {
        Vector3 position = getParent().getGlobalTransform().getPosition();

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
    public GraphicMatrix getProjectionMatrix() {
        updateProjectionMatrix();
        return projectionMatrix;
    }
    public GraphicMatrix getViewMatrix() {
        updateViewMatrix();
        return viewMatrix;
    }
    public GraphicMatrix getVPMatrix() {
        return GraphicMatrix.multiply(projectionMatrix, viewMatrix);
    }

    public static float fromScreenToView(Camera cam, float screenDistance) {
        return screenDistance / cam.zoom;
    }
    public static Vector2 fromScreenToView(Camera cam, Vector2 screenCoords) {
        float normalizedX = 2.0f * (screenCoords.x() / cam.screenSize.x()) - 1.0f;
        float normalizedY = 1.0f - 2 * (screenCoords.y() / cam.screenSize.y());

        float viewX = cam.viewRect.centerX() + 0.5f * normalizedX * cam.viewRect.width();
        float viewY = cam.viewRect.centerY() + 0.5f * normalizedY * -cam.viewRect.height();

        return new Vector2(viewX, viewY);
    }
    public static float fromViewToScreen(Camera cam, float viewDistance) {
        return viewDistance * cam.zoom;
    }
    public static Vector2 fromViewToScreen(Camera cam, Vector2 viewCoords) {
        float normalizedX = 2.0f * (viewCoords.x() - cam.viewRect.centerX()) / cam.viewRect.width();
        float normalizedY = 2.0f * (viewCoords.y() - cam.viewRect.centerY()) / -cam.viewRect.height();

        float screenX = 0.5f * (normalizedX + 1.0f) * cam.screenSize.x();
        float screenY = 0.5f * (1.0f - normalizedY) * cam.screenSize.y();

        return new Vector2(screenX, screenY);
    }

    public static class CameraBuilder<T extends CameraBuilder<T>>
            extends MonoBehaviourBuilder<T> {
        protected Vector2 screenSize;

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
            this.screenSize = new Vector2(screenWidth, screenHeight);
            return (T) this;
        }
        public T setScreenSize(Vector2 screenSize) {
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
