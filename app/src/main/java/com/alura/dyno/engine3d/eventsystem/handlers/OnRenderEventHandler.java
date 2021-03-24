package com.alura.dyno.engine3d.eventsystem.handlers;

import com.alura.dyno.engine3d.eventsystem.IEvent;
import com.alura.dyno.engine3d.eventsystem.ITreeEventHandler;
import com.alura.dyno.engine3d.eventsystem.TreeEventType;
import com.alura.dyno.engine3d.eventsystem.events.OnRenderEvent;

public abstract class OnRenderEventHandler implements ITreeEventHandler<OnRenderEvent> {

    @Override public TreeEventType getType() {
        return TreeEventType.OnRender;
    }

}
