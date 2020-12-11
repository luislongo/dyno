package com.alura.dyno.engine3d.system.messagerie.events;

import com.alura.dyno.engine3d.system.messagerie.EventType;
import com.alura.dyno.engine3d.system.messagerie.IEventListener;

public class RenderEvent extends EngineEvent {
    private static final EventType type = EventType.OnRender;
    @Override
    public EventType getType() { return type; }

    public abstract static class OnRenderListener implements IEventListener<RenderEvent> {
        public abstract void onRender(RenderEvent event);

        @Override public void onTrigger(RenderEvent event) {
            onRender(event);
        }
        @Override public EventType getType() {
            return type;
        }
    }
}
