package com.alura.dyno.engine3d.eventsystem;

public abstract class ITreeEventHandler<DATA extends IEvent> {
    public boolean isActive = true;

    public abstract TreeEventType getType();
    public abstract void onExecute(DATA event);
}
