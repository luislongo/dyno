package com.alura.dyno.engine3d.system.messagerie.events;

import com.alura.dyno.engine3d.system.messagerie.EventType;
import com.alura.dyno.engine3d.system.messagerie.IEventListener;
import com.alura.dyno.maths.Vector2G;

public class ScreenSizeChangedEvent extends EngineEvent {
    private static final EventType type = EventType.OnScreenSizeChanged;

    @Override
    public EventType getType() { return type; }

    int width, height;

    public ScreenSizeChangedEvent(int width, int height) {
        this.width = width;
        this.height = height;
    }
    public Vector2G getSize() {
        return new Vector2G(width, height);
    }

    public abstract static class OnScreenSizeChangedListener implements IEventListener<ScreenSizeChangedEvent> {
        public abstract void onScreenSizeChanged(ScreenSizeChangedEvent event);
        @Override public void onTrigger(ScreenSizeChangedEvent event) {
            onScreenSizeChanged(event);
        }
        @Override public EventType getType() {
            return type;
        }

    }
}
