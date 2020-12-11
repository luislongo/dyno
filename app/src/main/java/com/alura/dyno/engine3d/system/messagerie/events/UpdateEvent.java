package com.alura.dyno.engine3d.system.messagerie.events;

import com.alura.dyno.engine3d.system.messagerie.EventType;
import com.alura.dyno.engine3d.system.messagerie.IEventListener;

public class UpdateEvent extends EngineEvent {
    private static final EventType type = EventType.OnUpdate;

    @Override
    public EventType getType() { return type; }

    public abstract static class OnUpdateListener implements IEventListener<UpdateEvent> {
        public abstract void onUpdate(UpdateEvent event);
        @Override public void onTrigger(UpdateEvent event) {
            onUpdate(event);
        }
        @Override public EventType getType() {
            return type;
        }
    }
}
