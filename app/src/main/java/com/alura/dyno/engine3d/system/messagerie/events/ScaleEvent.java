package com.alura.dyno.engine3d.system.messagerie.events;

import com.alura.dyno.engine3d.system.messagerie.EventType;
import com.alura.dyno.engine3d.system.messagerie.IEventListener;

public class ScaleEvent extends EngineEvent {
    private static final EventType type = EventType.OnScale;

    @Override
    public EventType getType() { return type; }

    public abstract static class OnScaleListener implements IEventListener<ScaleEvent> {
        public abstract void onScale(ScaleEvent event);
        @Override public void onTrigger(ScaleEvent event) {
            onScale(event);
        }
        @Override public EventType getType() {
            return type;
        }
    }
}
