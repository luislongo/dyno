package com.alura.dyno.engine3d.eventsystem.handlers;

import com.alura.dyno.engine3d.eventsystem.IEvent;
import com.alura.dyno.engine3d.eventsystem.ITreeEventHandler;
import com.alura.dyno.engine3d.eventsystem.TreeEventType;
import com.alura.dyno.engine3d.eventsystem.events.OnTapEvent;
import com.alura.dyno.math.graphics.Vector2;

public abstract class OnTapEventHandler extends ITreeEventHandler <OnTapEvent> {

    @Override
    public TreeEventType getType() {
        return TreeEventType.OnTap;
    }


}
