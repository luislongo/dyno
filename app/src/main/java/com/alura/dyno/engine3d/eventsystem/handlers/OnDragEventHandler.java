package com.alura.dyno.engine3d.eventsystem.handlers;

import com.alura.dyno.engine3d.eventsystem.IEvent;
import com.alura.dyno.engine3d.eventsystem.ITreeEventHandler;
import com.alura.dyno.engine3d.eventsystem.TreeEventType;
import com.alura.dyno.engine3d.eventsystem.events.OnDragEvent;
import com.alura.dyno.math.graphics.Vector2;

public abstract class OnDragEventHandler implements ITreeEventHandler<OnDragEvent> {

    @Override public TreeEventType getType() {
        return TreeEventType.OnDrag;
    }

}
