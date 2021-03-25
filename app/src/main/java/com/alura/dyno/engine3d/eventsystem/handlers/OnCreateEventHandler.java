package com.alura.dyno.engine3d.eventsystem.handlers;

import com.alura.dyno.engine3d.eventsystem.ITreeEventHandler;
import com.alura.dyno.engine3d.eventsystem.TreeEventType;
import com.alura.dyno.engine3d.eventsystem.events.OnCreateEvent;

public abstract class OnCreateEventHandler extends ITreeEventHandler<OnCreateEvent> {

    @Override public TreeEventType getType() {
        return TreeEventType.OnCreate;
    }

}
