package com.alura.dyno.engine3d.eventsystem.handlers;

import com.alura.dyno.engine3d.eventsystem.TreeEventType;
import com.alura.dyno.engine3d.eventsystem.events.OnUpdateEvent;

public abstract class OnUpdateEventHandler implements ITreeEventHandler<OnUpdateEvent> {

    @Override
    public TreeEventType getType() {
        return TreeEventType.OnUpdate;
    }


}
