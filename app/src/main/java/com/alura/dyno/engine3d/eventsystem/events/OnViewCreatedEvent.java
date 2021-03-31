package com.alura.dyno.engine3d.eventsystem.events;

import com.alura.dyno.engine3d.eventsystem.TreeEventType;

public class OnViewCreatedEvent implements IEvent {

    @Override
    public TreeEventType getType() {
        return TreeEventType.OnViewCreated;
    }
}