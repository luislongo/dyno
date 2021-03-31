package com.alura.dyno.engine3d.script;

import com.alura.dyno.engine3d.eventsystem.handlers.ITreeEventHandler;
import com.alura.dyno.engine3d.eventsystem.TreeEventType;
import com.alura.dyno.engine3d.glyph.Glyph;
import com.alura.dyno.engine3d.tree.TreeLeaf;

import java.util.HashMap;

public class Script<GLYPH extends Glyph> extends TreeLeaf<GLYPH> {
    private HashMap<TreeEventType, ITreeEventHandler> eventHandlers;
    private boolean isActive = true;

    public Script(String name) {
        super(name);
        this.eventHandlers = new HashMap<>();
    }
    @Override public GLYPH getParent() {
        return super.getParent();
    }

    public boolean isActive() {
        return isActive;
    }
    public void setActive(boolean isActive) {
        this. isActive = isActive;
    }
    public ITreeEventHandler getEventHandler(TreeEventType type) {
        if(eventHandlers.containsKey(type)) {
            return eventHandlers.get(type);
        } else {
            return null;
        }
    }
    protected void addEventHandler(ITreeEventHandler handler) {
        if(eventHandlers.containsKey(handler.getType())) {
            throw new RuntimeException("Two events with same type not allowed.");
        } else {
            eventHandlers.put(handler.getType(), handler);
        }
    }
    protected void removeEventHandler(TreeEventType type) {
        this.eventHandlers.remove(type);
    }
    protected HashMap<TreeEventType, ITreeEventHandler> getEventHandlers() {
        return eventHandlers;
    }
}
