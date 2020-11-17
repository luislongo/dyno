package com.alura.dyno.engine3d.components;

import android.graphics.RectF;
import android.opengl.Matrix;

import com.alura.dyno.engine3d.system.events.ComponentEvent;
import com.alura.dyno.maths.Vector2;

public class Camera extends MonoBehaviour implements
        ComponentEvent.IOnDragEventListener,
        ComponentEvent.IOnScaleEventListener,
        ComponentEvent.IOnScreenSizeChangedEventListener {

    private final float[] viewMatrix = new float[16];
    private final float[] projectionMatrix = new float[16];

    private float screenWidth;
    private float screenHeight;

    private float zoom;
    private float minZoom = 1;
    private float maxZoom = Float.MAX_VALUE;

    private float left;
    private float right;
    private float top;
    private float bottom;

    private float viewHeight;
    private float viewWidth;
    private float viewCenterX;
    private float viewCenterY;

    public Camera(CameraBuilder builder) {
        super(builder);

        this.screenWidth = builder.screenWidth;
        this.screenHeight = builder.screenHeight;

        this.zoom = builder.zoom;
        this.minZoom = builder.minZoom;
        this.maxZoom = builder.maxZoom;
    }

    @Override
    public void onCreate(ComponentEvent.OnCreateEvent event) {
        super.onCreate(event);

        updateViewMatrix();
        updateProjectionMatrix();
    }

    private void updateViewMatrix() {
        Matrix.setIdentityM(viewMatrix, 0);
        Matrix.setLookAtM(viewMatrix, 0, getParent().getGlobalTransform().getX(),
                getParent().getGlobalTransform().getY(), -1.0f, 0, 0,
                -2, 0, 1, 0);
    }

    public void updateViewSpaceCamBounds() {
        Transform2D global = getParent().getGlobalTransform();

        left = global.getX() - 0.5f * screenWidth / zoom;
        right = global.getX() + 0.5f * screenWidth / zoom;
        top = global.getY() + 0.5f * screenHeight / zoom;
        bottom = global.getY() - 0.5f * screenHeight / zoom;

        viewHeight = top - bottom;
        viewWidth = right - left;

        viewCenterX = 0.5f * (left + right);
        viewCenterY = 0.5f * (top + bottom);
    }

    private void updateProjectionMatrix() {
        updateViewSpaceCamBounds();

        if (top != bottom && left != right) { //Camera should only be called when view is set
            Matrix.setIdentityM(projectionMatrix, 0);
            Matrix.orthoM(projectionMatrix, 0, left, right,
                    bottom, top, -5.0f, 100.0f);
        }
    }

    @Override
    public void onDrag(ComponentEvent.OnDragEvent event) {
        dragView(event.getViewDistance());
    }

    @Override
    public void onScale(ComponentEvent.OnScaleEvent event) {
        scaleView(event.getScaleFactor());
    }

    @Override
    public void onScreenSizeChanged(ComponentEvent.OnScreenSizeChangedEvent event) {
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
        updateProjectionMatrix();
    }

    public void dragView(Vector2 distance) {
        getParent().getLocalTransform().move(distance.getX(), -distance.getY());
        Matrix.translateM(viewMatrix, 0, distance.getX(), -distance.getY(), 0.0f);
    }

    private void scaleView(float scaleFactor) {
        if ((zoom * scaleFactor) > maxZoom) {
            scaleFactor = 1.0f;
        }

        if ((zoom * scaleFactor) < minZoom) {
            scaleFactor = 1.0f;
        }

        zoom *= scaleFactor;

        Transform2D global = getParent().getGlobalTransform();

        Matrix.translateM(viewMatrix, 0, global.getX(), global.getY(), 0.0f);
        Matrix.scaleM(viewMatrix, 0, scaleFactor, scaleFactor, 1.0f);
        Matrix.translateM(viewMatrix, 0, -global.getX(), -global.getY(), 0.0f);
    }

    public void rotateView(float angle) {
        Matrix.rotateM(viewMatrix, 0, angle, 0, 0, 1.0f);
        getParent().getGlobalTransform().rotate(angle);
    }

    //2.4 Getters and Setters
    public float[] getProjectionMatrix() {
        return projectionMatrix;
    }

    public float[] getViewMatrix() {
        return viewMatrix;
    }

    public float[] getVPMatrix() {
        float[] vp = new float[16];
        Matrix.multiplyMM(vp, 0, projectionMatrix, 0, viewMatrix, 0);

        return vp;
    }

    public float getZoom() {
        return zoom;
    }

    public void setScreenSize(int screenWidth, int screenHeight) {

    }

    public void setZoomConstraints(float minZoom, float maxZoom) {
        this.maxZoom = maxZoom;
        this.minZoom = minZoom;
    }

    public RectF getViewSpaceCamBounds() {
        updateViewSpaceCamBounds();
        return new RectF(left, top, right, bottom);
    }

    //2.5 Screen transformations
    public float fromScreenToView(float screenDistance) {
        return screenDistance / zoom;
    }

    public float fromViewToScreen(float viewDistance) {
        return viewDistance * zoom;
    }

    public Vector2 fromScreenToView(Vector2 screenCoords) {
        float normalizedX = 2.0f * (screenCoords.getX() / screenWidth) - 1.0f;
        float normalizedY = 1.0f - 2 * (screenCoords.getY() / screenHeight);

        float viewX = viewCenterX + 0.5f * normalizedX * viewWidth;
        float viewY = viewCenterY + 0.5f * normalizedY * viewHeight;

        return new Vector2(viewX, viewY);
    }

    public Vector2 fromViewToScreen(Vector2 viewCoords) {
        float normalizedX = 2.0f * (viewCoords.getX() - viewCenterX) / viewWidth;
        float normalizedY = 2.0f * (viewCoords.getY() - viewCenterY) / viewHeight;

        float screenX = 0.5f * (normalizedX + 1.0f) * screenWidth;
        float screenY = 0.5f * (1.0f - normalizedY) * screenHeight;

        return new Vector2(screenX, screenY);
    }


    public static class CameraBuilder<T extends CameraBuilder<T>>
            extends MonoBehaviourBuilder<T> {
        protected float screenWidth;
        protected float screenHeight;
        protected float zoom = 1;
        protected float minZoom = 1;
        protected float maxZoom = Float.MAX_VALUE;

        public CameraBuilder(String name) {
            super(name);
        }

        public static CameraBuilder<?> builder(String name) {
            return new CameraBuilder<>(name);
        }

        public Camera build() {
            return new Camera(this);
        }

        public T setScreenSize(float screenWidth, float screenHeight) {
            this.screenWidth = screenWidth;
            this.screenHeight = screenHeight;

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

    }
}
