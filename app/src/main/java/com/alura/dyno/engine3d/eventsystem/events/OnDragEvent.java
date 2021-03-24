package com.alura.dyno.engine3d.eventsystem.events;

import com.alura.dyno.engine3d.eventsystem.IEvent;
import com.alura.dyno.engine3d.eventsystem.TreeEventType;
import com.alura.dyno.math.graphics.Vector2;

public class OnDragEvent implements IEvent {
    Vector2 deltaScreen;
    Vector2 screenCurTouch;
    Vector2 screenLastTouch;

    @Override public TreeEventType getType() {
        return TreeEventType.OnDrag;
    }
    public OnDragEvent(Vector2 deltaScreen, Vector2 screenCurTouch, Vector2 screenLastTouch) {
        this.deltaScreen = deltaScreen;
        this.screenCurTouch = screenCurTouch;
        this.screenLastTouch = screenLastTouch;
    }
    public Vector2 getDeltaScreen() {
        return deltaScreen;
    }
    public Vector2 getScreenCurTouch() {
        return screenCurTouch;
    }
    public Vector2 getScreenLastTouch() {
        return screenLastTouch;
    }

}
