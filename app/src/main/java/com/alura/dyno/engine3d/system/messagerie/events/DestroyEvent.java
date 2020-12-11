package com.alura.dyno.engine3d.system.messagerie.events;

import com.alura.dyno.engine3d.system.messagerie.EventType;
import com.alura.dyno.engine3d.system.messagerie.IEventListener;

public class DestroyEvent extends EngineEvent {
    private static final EventType type = EventType.OnDestroy;

    @Override
    public EventType getType() { return type; }

    public abstract static class OnDestroyListener implements IEventListener<DestroyEvent> {
        public abstract void onDestroy(DestroyEvent event);
        @Override public void onTrigger(DestroyEvent event) {
            onDestroy(event);
        }
        @Override public EventType getType() {
            return type;
        }
    }
}
