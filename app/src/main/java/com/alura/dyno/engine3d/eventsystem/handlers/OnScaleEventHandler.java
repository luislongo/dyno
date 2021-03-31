package com.alura.dyno.engine3d.eventsystem.handlers;

import com.alura.dyno.engine3d.eventsystem.TreeEventType;
import com.alura.dyno.engine3d.eventsystem.events.OnScaleEvent;

public abstract class OnScaleEventHandler implements ITreeEventHandler<OnScaleEvent> {

    @Override public TreeEventType getType() {
        return TreeEventType.OnScale;
    }


}
