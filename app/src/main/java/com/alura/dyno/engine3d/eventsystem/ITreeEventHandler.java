package com.alura.dyno.engine3d.eventsystem;

public interface ITreeEventHandler<DATA extends IEvent> {
    public TreeEventType getType();
    public void onExecute(DATA event);
}
