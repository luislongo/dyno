package com.alura.dyno.engine3d.system.messagerie.events;

import com.alura.dyno.engine3d.system.messagerie.EventType;
import com.alura.dyno.engine3d.system.messagerie.IEventListener;

public class NewParentSetEvent extends EngineEvent {
    private static final EventType type = EventType.OnNewParentSet;

    @Override
    public EventType getType() { return type; }

    public abstract static class OnNewParentSetListener implements IEventListener<NewParentSetEvent> {
        public abstract void onNewParentSet(NewParentSetEvent event);
        @Override public void onTrigger(NewParentSetEvent event) {
            onNewParentSet(event);
        }
        @Override public EventType getType() {
            return type;
        }
    }
}
