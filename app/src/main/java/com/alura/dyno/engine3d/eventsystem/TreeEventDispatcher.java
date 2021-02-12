package com.alura.dyno.engine3d.eventsystem;
import com.alura.dyno.engine3d.glyph.Glyph;
import com.alura.dyno.engine3d.tree.EventTreeIterator;

public class TreeEventDispatcher {
    private Glyph root;

    public TreeEventDispatcher(Glyph root) {
        this.root = root;
    }

    public void sendDownTheTree(IEvent IEvent) {
        TreeEventType type = IEvent.getType();
        EventTreeIterator iterator = EventTreeIterator.fromNodeDown(root, type);

        while(iterator.hasNext()) {
            ITreeEventHandler handler = iterator.next();
            handler.onExecute(IEvent);
        }
    }
}
