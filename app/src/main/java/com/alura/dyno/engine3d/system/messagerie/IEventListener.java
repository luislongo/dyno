package com.alura.dyno.engine3d.system.messagerie;

import com.alura.dyno.engine3d.system.messagerie.events.EngineEvent;

public interface IEventListener<T extends EngineEvent> {
    void onTrigger(T event);
    EventType getType();
}
