package com.alura.dyno.engine3d.script;

import com.alura.dyno.engine3d.glyph.Glyph;
import com.alura.dyno.engine3d.eventsystem.ITreeEventHandler;
import com.alura.dyno.engine3d.eventsystem.TreeEventType;
import com.alura.dyno.engine3d.tree.TreeLeaf;

import java.util.HashMap;

public class Script<NODE extends Glyph> extends TreeLeaf<NODE> {
    private HashMap<TreeEventType, ITreeEventHandler> eventHandlers;

    public Script(String name) {
        super(name);
        this.eventHandlers = new HashMap<>();
    }

    protected void addEventHandler(ITreeEventHandler handler) {
        if(eventHandlers.containsKey(handler.getType())) {
            throw new RuntimeException("Two events with same type not allowed.");
        } else {
            eventHandlers.put(handler.getType(), handler);
        }
    }
    protected HashMap<TreeEventType, ITreeEventHandler> getEventHandlers() {
        return eventHandlers;
    }
    public ITreeEventHandler getEventHandler(TreeEventType type) {
        if(eventHandlers.containsKey(type)) {
            return eventHandlers.get(type);
        } else {
            return null;
        }
    }
}
