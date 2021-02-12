package com.alura.dyno.engine3d.eventsystem.handlers;

import com.alura.dyno.engine3d.eventsystem.IEvent;
import com.alura.dyno.engine3d.eventsystem.ITreeEventHandler;
import com.alura.dyno.engine3d.eventsystem.TreeEventType;
import com.alura.dyno.math.graphics.Vector2;

public abstract class OnCreateEventHandler implements ITreeEventHandler<OnCreateEventHandler.OnCreateEvent> {

    @Override public TreeEventType getType() {
        return TreeEventType.OnCreate;
    }

    public static class OnCreateEvent implements IEvent {

        @Override
        public TreeEventType getType() {
            return TreeEventType.OnDrag;
        }

        public OnCreateEvent() {
        }
    }

}
