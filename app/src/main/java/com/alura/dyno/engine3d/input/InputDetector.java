package com.alura.dyno.engine3d.input;

import android.content.Context;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;

import com.alura.dyno.engine3d.eventsystem.events.OnDragEvent;
import com.alura.dyno.engine3d.eventsystem.events.OnScaleEvent;
import com.alura.dyno.engine3d.eventsystem.events.OnTapEvent;
import com.alura.dyno.engine3d.eventsystem.handlers.OnScaleEventHandler;
import com.alura.dyno.engine3d.eventsystem.handlers.OnTapEventHandler;
import com.alura.dyno.math.graphics.Vector2;

public class InputDetector implements View.OnTouchListener {

    Vector2 lastTouch;
    Vector2 currentTouch;
    int mActivePointerId;

    private GestureDetector gestureDetector;
    private ScaleGestureDetector scaleGestureDetector;

    IInputListener listener;

    public InputDetector(Context context) {
        scaleGestureDetector = new ScaleGestureDetector(context, new ScaleListener());
        gestureDetector = new GestureDetector(context, new SimpleGestureListener());
        lastTouch = new Vector2();
        currentTouch = new Vector2();
    }

    @Override public boolean onTouch(View v, MotionEvent event) {
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
                onDrag(event);
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
    public void setInputListener(IInputListener listener) {
        this.listener = listener;
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
    private void onDrag(MotionEvent event) {
        final int pointerIndex = event.findPointerIndex(mActivePointerId);

        final Vector2 xy = new Vector2(event.getX(), event.getY());
        final Vector2 deltaScreen = xy.clone().minus(lastTouch);

        lastTouch = currentTouch;
        currentTouch = lastTouch.clone().plus(deltaScreen);

        notifyOnDrag(deltaScreen);
    }

    private void notifyOnDrag(Vector2 deltaScreen) {
        if(listener != null) {
            OnDragEvent engineEvent = new OnDragEvent(deltaScreen, currentTouch, lastTouch);
            listener.onDrag(engineEvent);
        }
    }
    private void notifyOnTap(Vector2 screenCoords) {
        if(listener != null) {
            OnTapEvent engineEvent = new OnTapEvent(screenCoords);
            listener.onTap(engineEvent);
        }
    }
    private void notifyOnScale(float scaleFactor, Vector2 focusPoint) {
        if(listener != null) {
            OnScaleEvent engineEvent = new OnScaleEvent(scaleFactor, focusPoint);
            listener.onScale(engineEvent);
        }
    }


    private class ScaleListener extends ScaleGestureDetector.SimpleOnScaleGestureListener {

        @Override
        public boolean onScale(ScaleGestureDetector detector) {
            float scaleFactor = detector.getCurrentSpan() / detector.getPreviousSpan();
            Vector2 focusPoint = new Vector2(detector.getFocusX(), detector.getFocusY());

            InputDetector.this.notifyOnScale(scaleFactor, focusPoint);
            return true;
        }
    }
    private class SimpleGestureListener implements GestureDetector.OnGestureListener {

        public boolean onSingleTapUp(MotionEvent e) {
            Vector2 tapPoint = new Vector2(e.getX(), e.getY());
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
