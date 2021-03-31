package com.alura.dyno.engine3d.eventsystem.events;

import com.alura.dyno.engine3d.eventsystem.TreeEventType;
import com.alura.dyno.math.graphics.Vector2;

public class OnScaleEvent implements IEvent {
    private float scaleFactor;
    private Vector2 screenFocus;

    @Override
    public TreeEventType getType() {
        return TreeEventType.OnScale;
    }
    public OnScaleEvent(float scaleFactor, Vector2 screenFocus) {
        this.scaleFactor = scaleFactor;
        this.screenFocus = screenFocus;
    }

    public float getScaleFactor() {
        return scaleFactor;
    }
    public Vector2 getScreenFocus() {
        return screenFocus;
    }
}