package com.alura.dyno.engine3d.system.messagerie.events;

import com.alura.dyno.engine3d.system.messagerie.EventType;

public abstract class EngineEvent {
    public boolean isHandled = false;

    public abstract EventType getType();
}
