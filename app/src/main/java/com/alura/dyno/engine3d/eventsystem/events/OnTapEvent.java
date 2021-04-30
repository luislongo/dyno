package com.alura.dyno.engine3d.eventsystem.events;

import com.alura.dyno.engine3d.eventsystem.TreeEventType;
import com.alura.dyno.math.graphics.Vector2;
import com.alura.dyno.math.graphics.Vector3;

public class OnTapEvent implements IEvent {
    private Vector2 screenCoords;

    public OnTapEvent(Vector2 screenCoords) {
        this.screenCoords = screenCoords;
    }
    public Vector2 getScreenCoords() {
        return screenCoords;
    }

    @Override
    public TreeEventType getType() {
        return TreeEventType.OnTap;
    }
}

