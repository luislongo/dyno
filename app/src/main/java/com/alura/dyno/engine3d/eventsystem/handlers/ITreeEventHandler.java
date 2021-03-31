package com.alura.dyno.engine3d.eventsystem.handlers;

import com.alura.dyno.engine3d.eventsystem.events.IEvent;
import com.alura.dyno.engine3d.eventsystem.TreeEventType;

public interface ITreeEventHandler<DATA extends IEvent> {
    TreeEventType getType();
    void onExecute(DATA event);
}
