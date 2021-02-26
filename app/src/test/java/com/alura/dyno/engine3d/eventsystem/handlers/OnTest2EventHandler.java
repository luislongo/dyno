package com.alura.dyno.engine3d.eventsystem.handlers;

import com.alura.dyno.engine3d.eventsystem.IEvent;
import com.alura.dyno.engine3d.eventsystem.ITreeEventHandler;
import com.alura.dyno.engine3d.eventsystem.TreeEventType;

public abstract class OnTest2EventHandler implements ITreeEventHandler<OnTest2EventHandler.OnTest2Event> {

@Override
public TreeEventType getType() {
        return TreeEventType.OnTest2;
        }

public static class OnTest2Event implements IEvent {
    public boolean hasBeenExecuted = false;
    public int count = 0;

    @Override
    public TreeEventType getType() {
        return TreeEventType.OnTest2;
    }
}
}
