package com.alura.dyno.engine3d.eventsystem.handlers;

import com.alura.dyno.engine3d.eventsystem.IEvent;
import com.alura.dyno.engine3d.eventsystem.ITreeEventHandler;
import com.alura.dyno.engine3d.eventsystem.TreeEventType;
import com.alura.dyno.math.graphics.Vector2;

public abstract class OnTapEventHandler implements ITreeEventHandler <OnTapEventHandler.OnTapEvent> {

    @Override
    public TreeEventType getType() {
        return TreeEventType.OnTap;
    }

    public static class OnTapEvent implements IEvent {
        Vector2 screenCoords;

        public OnTapEvent(Vector2 screenCoords) {
            this.screenCoords = screenCoords;
        }

        @Override
        public TreeEventType getType() {
            return TreeEventType.OnTap;
        }
    }
}
