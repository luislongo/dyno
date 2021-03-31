package com.alura.dyno.engine3d.eventsystem.handlers;

import com.alura.dyno.engine3d.eventsystem.TreeEventType;
import com.alura.dyno.engine3d.eventsystem.events.OnDragEvent;

public abstract class OnDragEventHandler implements ITreeEventHandler<OnDragEvent> {

    @Override public TreeEventType getType() {
        return TreeEventType.OnDrag;
    }

}
