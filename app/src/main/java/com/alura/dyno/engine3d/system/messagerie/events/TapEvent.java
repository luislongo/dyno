package com.alura.dyno.engine3d.system.messagerie.events;

import com.alura.dyno.engine3d.system.messagerie.EventType;
import com.alura.dyno.engine3d.system.messagerie.IEventListener;
import com.alura.dyno.maths.Vector2G;

public class TapEvent extends EngineEvent {
    private static final EventType type = EventType.OnTap;

    @Override public EventType getType() { return type; }

    Vector2G screenCoords;

    public TapEvent(Vector2G screenCoords) {
        this.screenCoords = screenCoords;
    }

    public Vector2G getScreenCoords() {
        return screenCoords;
    }

    public abstract static class OnTapListener implements IEventListener<TapEvent> {
        public abstract void onTap(TapEvent event);
        @Override public void onTrigger(TapEvent event) {
            onTap(event);
        }
        @Override public EventType getType() {
            return type;
        }
    }
}
