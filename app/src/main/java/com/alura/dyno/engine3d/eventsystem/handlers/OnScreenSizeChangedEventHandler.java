package com.alura.dyno.engine3d.eventsystem.handlers;

import com.alura.dyno.engine3d.eventsystem.IEvent;
import com.alura.dyno.engine3d.eventsystem.ITreeEventHandler;
import com.alura.dyno.engine3d.eventsystem.TreeEventType;
import com.alura.dyno.math.graphics.Vector2;

public abstract class OnScreenSizeChangedEventHandler implements ITreeEventHandler<OnScreenSizeChangedEventHandler.OnScreenSizeChangedEvent> {

    @Override
    public TreeEventType getType() {
        return TreeEventType.OnScreenSizeChanged;
    }

    public static class OnScreenSizeChangedEvent implements IEvent {
        Vector2 screenSize;

        public OnScreenSizeChangedEvent(Vector2 screenSize) {
            this.screenSize = screenSize;
        }
        @Override public TreeEventType getType() {
            return TreeEventType.OnScreenSizeChanged;
        }
        public Vector2 getScreenSize() {
            return screenSize;
        }
    }
}
