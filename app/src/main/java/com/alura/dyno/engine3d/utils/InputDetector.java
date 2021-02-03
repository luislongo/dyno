package com.alura.dyno.engine3d.utils;

import android.content.Context;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;

import com.alura.dyno.engine3d.components.Camera;
import com.alura.dyno.engine3d.system.SceneMaster;
import com.alura.dyno.maths.graphics.Vector2F;
import com.alura.dyno.ui.StructureDrawGLSurface;

public class InputDetector implements StructureDrawGLSurface.StructureDrawSurfaceListener {

    Vector2F lastTouch;
    Vector2F currentTouch;
    int mActivePointerId;

    private GestureDetector gestureDetector;
    private ScaleGestureDetector scaleGestureDetector;

    public InputDetector(Context context) {
        scaleGestureDetector = new ScaleGestureDetector(context, new ScaleListener());
        gestureDetector = new GestureDetector(context, new SimpleGestureListener());

        lastTouch = new Vector2F();
        currentTouch = new Vector2F();
    }

    @Override
    public boolean onTouch(MotionEvent event) {
        final int action = event.getAction();

        gestureDetector.onTouchEvent(event);
        scaleGestureDetector.onTouchEvent(event);

        switch (action) {
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_POINTER_DOWN: {
                onPointerDownEvent(event);
                break;
            }
            case MotionEvent.ACTION_MOVE: {
                onMoveEvent(event);
                break;
            }
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL: {
                onCancelEvent();
                break;
            }
            case MotionEvent.ACTION_POINTER_UP: {
                onPointerUpEvent(event);
                break;
            }
        }
        return true;
    }
    private void onPointerUpEvent(MotionEvent event) {
        final int pointerIndex = event.getActionIndex();
        final int pointerId = event.getPointerId(pointerIndex);

        if (pointerId == mActivePointerId) {
            final int newPointerIndex = pointerIndex == 0 ? 1 : 0;
            lastTouch.setValues(event.getX(newPointerIndex),
                    event.getY(newPointerIndex));
            mActivePointerId = event.getPointerId(newPointerIndex);
        }
    }
    private void onCancelEvent() {
        mActivePointerId = MotionEvent.INVALID_POINTER_ID;
    }
    private void onPointerDownEvent(MotionEvent event) {
        final int pointerIndex = event.getActionIndex();

        lastTouch.setValues(event.getX(pointerIndex),
                event.getY(pointerIndex));

        mActivePointerId = event.getPointerId(0);
    }
    private void onMoveEvent(MotionEvent event) {
        final int pointerIndex = event.findPointerIndex(mActivePointerId);

        final Vector2F xy = new Vector2F(event.getX(), event.getY());
        final Vector2F dxdy = Vector2F.subtract(xy, lastTouch);

        currentTouch = Vector2F.add(lastTouch, dxdy);
        lastTouch = currentTouch;

        notifyOnDrag(dxdy);
    }

    private void notifyOnTap(Vector2F screenCoords) {
        ComponentEvent.OnTapEvent event = new ComponentEvent.OnTapEvent(screenCoords);
        TreeEventDispatcher.dispatcher.dispatchEvent(event);
    }
    private void notifyOnDrag(Vector2F distance) {
        final Vector2F viewDxDy = Vector2F.divide(distance, SceneMaster.getMainCamera().getZoom());
        final Vector2F viewCurrentTouch = Camera.fromScreenToView(SceneMaster.getMainCamera(), currentTouch);
        ComponentEvent.OnDragEvent event = new ComponentEvent.OnDragEvent(distance, viewDxDy, currentTouch, viewCurrentTouch);

        TreeEventDispatcher.dispatcher.dispatchEvent(event);
    }
    private void notifyOnScale(float scaleFactor, Vector2F focusPoint) {
        ComponentEvent.OnScaleEvent event = new ComponentEvent.OnScaleEvent(scaleFactor, focusPoint);

        TreeEventDispatcher.dispatcher.dispatchEvent(event);
    }

    private class ScaleListener extends ScaleGestureDetector.SimpleOnScaleGestureListener {

        @Override
        public boolean onScale(ScaleGestureDetector detector) {
            float scaleFactor = detector.getCurrentSpan() / detector.getPreviousSpan();
            Vector2F focusPoint = new Vector2F(detector.getFocusX(), detector.getFocusY());

            InputDetector.this.notifyOnScale(scaleFactor, focusPoint);
            return true;
        }
    }

    private class SimpleGestureListener implements GestureDetector.OnGestureListener {

        public boolean onSingleTapUp(MotionEvent e) {
            Vector2F tapPoint = new Vector2F(e.getX(), e.getY());
            notifyOnTap(tapPoint);
            return false;
        }

        @Override
        public void onLongPress(MotionEvent e) {

        }

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            return false;
        }

        @Override
        public boolean onDown(MotionEvent e) {
            return false;
        }

        @Override
        public void onShowPress(MotionEvent e) {
        }

        @Override
        public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
            return false;
        }
    }
}
