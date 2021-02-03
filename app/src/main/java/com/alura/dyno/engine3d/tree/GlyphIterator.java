package com.alura.dyno.engine3d.tree;

import com.alura.dyno.engine3d.scripting.Glyph;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

public abstract class GlyphIterator<T> implements Iterator<T> {
    LinkedList<T> ordered;
    int curId;

    protected GlyphIterator() {
        ordered = new LinkedList<>();
        curId = 0;
    }

    @Override public boolean hasNext() {
        boolean isIteratorEmpty = (ordered.isEmpty());
        boolean isCurrentItemLast = (curId == ordered.size() - 1);

        return !(isIteratorEmpty || isCurrentItemLast);
    }
    @Override public T next() {
        if(hasNext()) {
            curId++;
            return ordered.get(curId);
        } else {
            throw new RuntimeException();
        }
    }
    public abstract void putValue(Glyph node);

    public void fromNodeUp(Glyph node) {
        ordered.clear();
        this.propagateUp(node);
    }
    public void fillNodeDown(Glyph node) {
        ordered.clear();
        this.propagateDown(node);
    }
    public void fillAllTree(Glyph node) {
        ordered.clear();
        this.propagateDown(node.getRoot());
    }
    private void propagateUp(Glyph node) {
        this.putValue(node);
        if(node.hasParent()) {
            propagateUp(node.getParent());
        }
    }
    private void propagateDown(Glyph node) {
        this.putValue(node);

        LinkedList<Glyph> children = node.getChildren();
        for(Glyph child : children) {
            this.propagateDown(child);
        }
    }

}
