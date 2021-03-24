package com.alura.dyno.engine3d.eventsystem.events;

import com.alura.dyno.engine3d.eventsystem.IEvent;
import com.alura.dyno.engine3d.eventsystem.TreeEventType;

public class OnUpdateEvent implements IEvent {

    @Override
    public TreeEventType getType() {
        return TreeEventType.OnCreate;
    }
}

