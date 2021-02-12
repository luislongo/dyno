package com.alura.dyno.engine3d.tree;

import com.alura.dyno.engine3d.eventsystem.ITreeEventHandler;
import com.alura.dyno.engine3d.eventsystem.TreeEventType;
import com.alura.dyno.engine3d.glyph.Glyph;
import com.alura.dyno.engine3d.script.Script;

import java.util.Iterator;
import java.util.LinkedList;

public class EventTreeIterator implements Iterator<ITreeEventHandler> {
    LinkedList<ITreeEventHandler> ordered;
    int curId;

    @Override public boolean hasNext() {
        boolean isIteratorEmpty = (ordered.isEmpty());
        boolean isCurrentItemLast = (curId == ordered.size() - 1);

        return !(isIteratorEmpty || isCurrentItemLast);
    }
    @Override public ITreeEventHandler next() {
        if(hasNext()) {
            ITreeEventHandler handler = this.ordered.get(curId);
            curId++;

            return handler;
        } else {
            throw new RuntimeException();
        }
    }

    protected EventTreeIterator() {
        super();
        ordered = new LinkedList<>();
        curId = 0;
    }

    public static EventTreeIterator fromNodeUp(Glyph node, TreeEventType type) {
        EventTreeIterator iterator = new EventTreeIterator();
        iterator.propagateUp(node, type);

        return iterator;
    }
    private void propagateUp(Glyph node, TreeEventType type) {
        this.putValue(node, type);
        if(node.hasParent()) {
            propagateUp(node.getParent(), type);
        }
    }

    public static EventTreeIterator fromNodeDown(Glyph node, TreeEventType type) {
        EventTreeIterator iterator = new EventTreeIterator();
        iterator.propagateDown(node, type);

        return iterator;
    }
    private void propagateDown(Glyph node, TreeEventType type) {
        this.putValue(node, type);

        LinkedList<Glyph> children = node.getChildren();
        for(Glyph child : children) {
            this.propagateDown(child, type);
        }
    }

    protected void putValue(Glyph node, TreeEventType type) {
        LinkedList<Script> scripts = node.getLeaves();
        for(Script script : scripts) {
            ITreeEventHandler handler = script.getEventHandler(type);
            if(!(handler == null)) {
                ordered.add(script.getEventHandler(type));
            }
        }
    }
}
