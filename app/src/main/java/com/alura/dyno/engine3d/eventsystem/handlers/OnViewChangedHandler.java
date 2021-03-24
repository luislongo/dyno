package com.alura.dyno.engine3d.eventsystem.handlers;

import com.alura.dyno.engine3d.eventsystem.IEvent;
import com.alura.dyno.engine3d.eventsystem.ITreeEventHandler;
import com.alura.dyno.engine3d.eventsystem.TreeEventType;
import com.alura.dyno.engine3d.eventsystem.events.OnViewChangedEvent;
import com.alura.dyno.math.graphics.Vector2;

public abstract class OnViewChangedHandler implements ITreeEventHandler<OnViewChangedEvent> {

    @Override
    public TreeEventType getType() {
        return TreeEventType.OnViewChanged;
    }

}
