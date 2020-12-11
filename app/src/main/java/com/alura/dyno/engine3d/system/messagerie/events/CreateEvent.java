package com.alura.dyno.engine3d.system.messagerie.events;

import com.alura.dyno.engine3d.system.messagerie.EventType;
import com.alura.dyno.engine3d.system.messagerie.IEventListener;

public class CreateEvent extends EngineEvent {
    private static final EventType type = EventType.OnCreate;

    @Override
    public EventType getType() { return type; }

    public abstract static class OnCreateListener implements IEventListener<CreateEvent> {
        public abstract void onCreate(CreateEvent event);
        @Override public void onTrigger(CreateEvent event) {
            onCreate(event);
        }
        @Override public EventType getType() {
            return type;
        }
    }
}
