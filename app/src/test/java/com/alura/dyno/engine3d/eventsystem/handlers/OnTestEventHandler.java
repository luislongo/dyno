package com.alura.dyno.engine3d.eventsystem.handlers;

import com.alura.dyno.engine3d.eventsystem.IEvent;
import com.alura.dyno.engine3d.eventsystem.ITreeEventHandler;
import com.alura.dyno.engine3d.eventsystem.TreeEventType;

public abstract class OnTestEventHandler implements ITreeEventHandler<OnTestEventHandler.OnTestEvent> {

@Override
public TreeEventType getType() {
        return TreeEventType.OnTest;
        }

public static class OnTestEvent implements IEvent {
    public boolean hasBeenExecuted = false;
    public int count = 0;

    @Override
    public TreeEventType getType() {
        return TreeEventType.OnTest;
    }
}
}
