package com.alura.dyno.engine3d.system.messagerie.events;

import com.alura.dyno.engine3d.system.messagerie.EventType;
import com.alura.dyno.engine3d.system.messagerie.IEventListener;
import com.alura.dyno.maths.Vector2G;

public class DragEvent extends EngineEvent {
    private static final EventType type = EventType.OnDrag;

    @Override
    public EventType getType() { return type; }

    Vector2G screenDistance;
    Vector2G viewDistance;
    Vector2G screenCurrentTouch;
    Vector2G viewCurrentTouch;

    public DragEvent(Vector2G screenDistance, Vector2G viewDistance, Vector2G screenCurrentTouch,
                       Vector2G viewCurrentTouch) {
        this.screenDistance = screenDistance;
        this.viewDistance = viewDistance;
        this.screenCurrentTouch = screenCurrentTouch;
        this.viewCurrentTouch = viewCurrentTouch;
    }

    public Vector2G getScreenDistance() {
        return screenDistance;
    }
    public Vector2G getViewDistance() {
        return viewDistance;
    }
    public Vector2G getScreenCurrentTouch() {
        return screenCurrentTouch;
    }
    public Vector2G getViewCurrentTouch() {
        return viewCurrentTouch;
    }

    public abstract static class OnDragListener implements IEventListener<DragEvent> {
        public abstract void onDrag(DragEvent event);
        @Override public void onTrigger(DragEvent event) {
            onDrag(event);
        }
        @Override public EventType getType() {
            return type;
        }
    }
}
