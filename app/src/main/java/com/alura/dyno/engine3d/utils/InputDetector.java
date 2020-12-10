package com.alura.dyno.engine3d.utils;

import android.content.Context;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;

import com.alura.dyno.engine3d.components.Camera;
import com.alura.dyno.engine3d.system.SceneMaster;
import com.alura.dyno.engine3d.system.events.ComponentEvent;
import com.alura.dyno.engine3d.system.events.TreeEventDispatcher;
import com.alura.dyno.maths.Vector2G;
import com.alura.dyno.ui.StructureDrawGLSurface;

public class InputDetector implements StructureDrawGLSurface.StructureDrawSurfaceListener {

    Vector2G lastTouch;
    Vector2G currentTouch;
    int mActivePointerId;

    private GestureDetector gestureDetector;
    private ScaleGestureDetector scaleGestureDetector;

    public InputDetector(Context context) {
        scaleGestureDetector = new ScaleGestureDetector(context, new ScaleListener());
        gestureDetector = new GestureDetector(context, new SimpleGestureListener());

        lastTouch = new Vector2G();
        currentTouch = new Vector2G();
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

        final Vector2G xy = new Vector2G(event.getX(), event.getY());
        final Vector2G dxdy = Vector2G.minus(xy, lastTouch);

        currentTouch = Vector2G.plus(lastTouch, dxdy);
        lastTouch = currentTouch;

        notifyOnDrag(dxdy);
    }

    private void notifyOnTap(Vector2G screenCoords) {
        ComponentEvent.OnTapEvent event = new ComponentEvent.OnTapEvent(screenCoords);
        TreeEventDispatcher.dispatcher.dispatchEvent(event);
    }
    private void notifyOnDrag(Vector2G distance) {
        final Vector2G viewDxDy =  new Vector2G(distance).divide(SceneMaster.getMainCamera().getZoom());
        final Vector2G viewCurrentTouch = Camera.fromScreenToView(SceneMaster.getMainCamera(), currentTouch);
        ComponentEvent.OnDragEvent event = new ComponentEvent.OnDragEvent(distance, viewDxDy, currentTouch, viewCurrentTouch);

        TreeEventDispatcher.dispatcher.dispatchEvent(event);
    }
    private void notifyOnScale(float scaleFactor, Vector2G focusPoint) {
        ComponentEvent.OnScaleEvent event = new ComponentEvent.OnScaleEvent(scaleFactor, focusPoint);

        TreeEventDispatcher.dispatcher.dispatchEvent(event);
    }

    private class ScaleListener extends ScaleGestureDetector.SimpleOnScaleGestureListener {

        @Override
        public boolean onScale(ScaleGestureDetector detector) {
            float scaleFactor = detector.getCurrentSpan() / detector.getPreviousSpan();
            Vector2G focusPoint = new Vector2G(detector.getFocusX(), detector.getFocusY());

            InputDetector.this.notifyOnScale(scaleFactor, focusPoint);
            return true;
        }
    }

    private class SimpleGestureListener implements GestureDetector.OnGestureListener {

        public boolean onSingleTapUp(MotionEvent e) {
            Vector2G tapPoint = new Vector2G(e.getX(), e.getY());
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
