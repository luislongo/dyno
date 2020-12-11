package com.alura.dyno.engine3d.system.messagerie.events;

import com.alura.dyno.engine3d.system.messagerie.EventType;
import com.alura.dyno.engine3d.system.messagerie.IEventListener;

public class ParentTransformChangedEvent extends EngineEvent {
    private static final EventType type = EventType.OnParentTransformChanged;

    @Override
    public EventType getType() { return type; }

    public abstract static class OnParentTransformChangedListener implements IEventListener<ParentTransformChangedEvent> {
        public abstract void onParentTransformChanged(ParentTransformChangedEvent event);
        @Override public void onTrigger(ParentTransformChangedEvent event) {
            onParentTransformChanged(event);
        }
        @Override public EventType getType() {
            return type;
        }
    }
}
