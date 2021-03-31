package com.alura.dyno.engine3d.eventsystem.handlers;

import com.alura.dyno.engine3d.eventsystem.TreeEventType;
import com.alura.dyno.engine3d.eventsystem.events.OnTapEvent;

public abstract class OnTapEventHandler implements ITreeEventHandler<OnTapEvent> {

    @Override
    public TreeEventType getType() {
        return TreeEventType.OnTap;
    }


}
