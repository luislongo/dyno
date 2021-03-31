package com.alura.dyno.engine3d.eventsystem.handlers;

import com.alura.dyno.engine3d.eventsystem.TreeEventType;
import com.alura.dyno.engine3d.eventsystem.events.OnViewChangedEvent;

public abstract class OnViewChangedHandler implements ITreeEventHandler<OnViewChangedEvent> {

    @Override
    public TreeEventType getType() {
        return TreeEventType.OnViewChanged;
    }

}
