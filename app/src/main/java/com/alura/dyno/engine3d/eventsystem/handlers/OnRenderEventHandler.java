package com.alura.dyno.engine3d.eventsystem.handlers;

import com.alura.dyno.engine3d.eventsystem.IEvent;
import com.alura.dyno.engine3d.eventsystem.ITreeEventHandler;
import com.alura.dyno.engine3d.eventsystem.TreeEventType;

public abstract class OnRenderEventHandler implements ITreeEventHandler<OnRenderEventHandler.OnRenderEvent> {

    @Override public TreeEventType getType() {
        return TreeEventType.OnRender;
    }

    public static class OnRenderEvent implements IEvent {

        @Override
        public TreeEventType getType() {
            return TreeEventType.OnRender;
        }
    }
}
