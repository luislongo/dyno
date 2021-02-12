package com.alura.dyno.engine3d.eventsystem.handlers;

import com.alura.dyno.engine3d.eventsystem.IEvent;
import com.alura.dyno.engine3d.eventsystem.ITreeEventHandler;
import com.alura.dyno.engine3d.eventsystem.TreeEventType;
import com.alura.dyno.engine3d.eventsystem.handlers.OnUpdateEventHandler.OnUpdateEvent;

public abstract class OnUpdateEventHandler implements ITreeEventHandler<OnUpdateEvent> {

    @Override
    public TreeEventType getType() {
        return TreeEventType.OnCreate;
    }

    public static class OnUpdateEvent implements IEvent {

        @Override
        public TreeEventType getType() {
            return TreeEventType.OnCreate;
        }
    }
}
