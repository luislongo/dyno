package com.alura.dyno.engine3d.components;

import com.alura.dyno.engine3d.system.events.ComponentEvent;
import com.alura.dyno.maths.MathExtra;
import com.alura.dyno.maths.MatrixG;
import com.alura.dyno.maths.MatrixGFactory;
import com.alura.dyno.maths.Vector2G;
import com.alura.dyno.maths.Vector3G;

public class Camera extends MonoBehaviour implements
        ComponentEvent.IOnDragEventListener,
        ComponentEvent.IOnScaleEventListener,
        ComponentEvent.IOnScreenSizeChangedEventListener {

    private MatrixG viewMatrix;
    private MatrixG projectionMatrix;

    private Vector2G screenSize;

    private float zNear;
    private float zFar;

    private float zoom;
    private float minZoom = 1;
    private float maxZoom = Float.MAX_VALUE;

    private Vector2G viewCenter;
    private Vector2G viewSize;
    private float viewLeft;
    private float viewRight;
    private float viewBottom;
    private float viewTop;

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

        viewCenter = new Vector2G();
        viewSize = new Vector2G();

        updateViewMatrix();
        updateProjectionMatrix();
    }
    @Override public void onDrag(ComponentEvent.OnDragEvent event) {
        Vector3G distance = new Vector3G(event.getViewDistance(), 0.0f);
        distance.setY(-distance.y());

        getParent().getLocalTransform().move(distance.multiply(-1.0f));

        updateViewMatrix();
    }
    @Override public void onScale(ComponentEvent.OnScaleEvent event) {
        float scaleFactor = event.getScaleFactor();
        zoom = MathExtra.clamp(zoom * scaleFactor, minZoom, maxZoom);

        updateProjectionMatrix();
        updateViewMatrix();
    }
    @Override public void onScreenSizeChanged(ComponentEvent.OnScreenSizeChangedEvent event) {
        this.screenSize.setValues(event.getWidth(), event.getHeight());
        updateProjectionMatrix();
    }
    private boolean isValidZoom(float scaleFactor) {
        return !((zoom == minZoom) || (zoom == maxZoom));
    }

    private void updateViewMatrix() {
        Transform parentGlobal = getParent().getGlobalTransform();

        Vector3G eye = parentGlobal.getPosition();
        Vector3G center = new Vector3G(0.0f, 0.0f, -1.0f).plus(eye);
        Vector3G up = new Vector3G(0.0f, 1.0f, 0.0f);

        viewMatrix = MatrixGFactory.lookAt(eye, center, up);
    }
    private void updateProjectionMatrix() {
        updateViewSpaceCamBounds();

        if (isViewRectValid()) {
            projectionMatrix = MatrixGFactory.orthogonal(viewLeft, viewRight, viewBottom, viewTop, zNear, zFar);
        }
    }
    private void updateViewSpaceCamBounds() {
        Vector3G position = getParent().getGlobalTransform().getPosition();

        viewLeft = position.x() - 0.5f * screenSize.x() / zoom;
        viewRight = position.x() + 0.5f * screenSize.x() / zoom;
        viewBottom = position.y() - 0.5f * screenSize.y() / zoom;
        viewTop = position.y() + 0.5f * screenSize.y() / zoom;

        viewCenter.setValues(0.5f * (viewRight + viewLeft), 0.5f * (viewTop + viewBottom));
        viewSize.setValues(0.5f * (viewRight - viewLeft), 0.5f * (viewTop - viewBottom));
    }
    private boolean isViewRectValid() {
        boolean hasHeight = (viewTop - viewBottom) > 0;
        boolean hasWidth = (viewRight - viewLeft) > 0;

        return hasHeight && hasWidth;
    }

    public float getZoom() {
        return zoom;
    }
    public MatrixG getProjectionMatrix() {
        updateProjectionMatrix();
        return projectionMatrix;
    }
    public MatrixG getViewMatrix() {
        updateViewMatrix();
        return viewMatrix;
    }
    public MatrixG getVPMatrix() {
        return MatrixG.multiply(projectionMatrix, viewMatrix);
    }

    public static float fromScreenToView(Camera cam, float screenDistance) {
        return screenDistance / cam.zoom;
    }
    public static Vector2G fromScreenToView(Camera cam, Vector2G screenCoords) {
        float normalizedX = 2.0f * (screenCoords.x() / cam.screenSize.x()) - 1.0f;
        float normalizedY = 1.0f - 2 * (screenCoords.y() / cam.screenSize.y());

        float viewX = cam.viewCenter.x() + 0.5f * normalizedX * cam.viewSize.x();
        float viewY = cam.viewCenter.y() + 0.5f * normalizedY * -cam.viewSize.y();

        return new Vector2G(viewX, viewY);
    }
    public static float fromViewToScreen(Camera cam, float viewDistance) {
        return viewDistance * cam.zoom;
    }
    public static Vector2G fromViewToScreen(Camera cam, Vector2G viewCoords) {
        float normalizedX = 2.0f * (viewCoords.x() - cam.viewCenter.x()) / cam.viewSize.x();
        float normalizedY = 2.0f * (viewCoords.y() - cam.viewCenter.y()) / -cam.viewSize.y();

        float screenX = 0.5f * (normalizedX + 1.0f) * cam.screenSize.x();
        float screenY = 0.5f * (1.0f - normalizedY) * cam.screenSize.y();

        return new Vector2G(screenX, screenY);
    }

    public static class CameraBuilder<T extends CameraBuilder<T>>
            extends MonoBehaviourBuilder<T> {
        protected Vector2G screenSize;

        protected float zNear = -100.0f;
        protected float zFar = 100.0f;

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
            this.screenSize = new Vector2G(screenWidth, screenHeight);
            return (T) this;
        }
        public T setScreenSize(Vector2G screenSize) {
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
