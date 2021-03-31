package com.alura.dyno.engine3d.eventsystem.handlers;

import com.alura.dyno.engine3d.eventsystem.TreeEventType;
import com.alura.dyno.engine3d.eventsystem.events.OnViewCreatedEvent;

public abstract class OnViewCreatedHandler implements ITreeEventHandler<OnViewCreatedEvent> {

    @Override
    public TreeEventType getType() {
        return TreeEventType.OnViewCreated;
    }

}
