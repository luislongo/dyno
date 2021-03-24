package com.alura.dyno.engine3d.eventsystem.handlers;

import com.alura.dyno.engine3d.eventsystem.IEvent;
import com.alura.dyno.engine3d.eventsystem.ITreeEventHandler;
import com.alura.dyno.engine3d.eventsystem.TreeEventType;
import com.alura.dyno.engine3d.eventsystem.events.OnScaleEvent;
import com.alura.dyno.math.graphics.Vector2;

public abstract class OnScaleEventHandler implements ITreeEventHandler<OnScaleEvent> {

    @Override public TreeEventType getType() {
        return TreeEventType.OnScale;
    }


}
