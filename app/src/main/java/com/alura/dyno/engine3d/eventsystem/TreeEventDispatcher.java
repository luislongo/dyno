package com.alura.dyno.engine3d.eventsystem;

import com.alura.dyno.engine3d.eventsystem.events.IEvent;
import com.alura.dyno.engine3d.eventsystem.handlers.ITreeEventHandler;
import com.alura.dyno.engine3d.glyph.Glyph;
import com.alura.dyno.engine3d.tree.EventTreeIterator;

public class TreeEventDispatcher {
    private Glyph root;

    public TreeEventDispatcher(Glyph root) {
        this.root = root;
    }

    public void sendDownTheTree(IEvent event) {
        TreeEventType type = event.getType();
        EventTreeIterator iterator = EventTreeIterator.fromNodeDown(root, type);

        while(iterator.hasNext()) {
            ITreeEventHandler handler = iterator.next();
            handler.onExecute(event);
        }
    }
    public void sendDownTheChildrenOf(Glyph parent, IEvent event) {
        TreeEventType type = event.getType();
        EventTreeIterator iterator = EventTreeIterator.fromNodeDown(parent, type);

        if(iterator.hasNext()) {
            iterator.next();

            while(iterator.hasNext()) {
                ITreeEventHandler handler = iterator.next();
                executeIfActive(handler, event);
            }
        }

    }
    public void executeIfActive(ITreeEventHandler handler, IEvent event) {
            handler.onExecute(event);

    }

}
