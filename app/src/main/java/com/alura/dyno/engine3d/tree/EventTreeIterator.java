package com.alura.dyno.engine3d.tree;

import com.alura.dyno.engine3d.events.ITreeEvent;
import com.alura.dyno.engine3d.events.TreeEventType;
import com.alura.dyno.engine3d.scripting.Glyph;
import com.alura.dyno.engine3d.scripting.Script;

import java.util.LinkedList;

public class EventTreeIterator extends GlyphIterator<ITreeEvent> {

    private TreeEventType type;

    public EventTreeIterator(TreeEventType type) {
        super();
        this.type = type;
    }

    @Override
    public void putValue(Glyph node) {
        LinkedList<Script> scripts = node.getLeaves();

        for(Script script : scripts) {
            ordered.addAll(script.getEvents(type));
        }
    }
}
