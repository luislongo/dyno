package com.alura.dyno.engine3d.events;

public interface ITreeEvent {
    public TreeEventType getType();
    public void onExecute();
}
