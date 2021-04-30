package com.alura.dyno.engine3d.eventsystem;

import com.alura.dyno.engine3d.eventsystem.events.IEvent;
import com.alura.dyno.engine3d.eventsystem.handlers.ITreeEventHandler;
import com.alura.dyno.engine3d.glyph.Glyph;
import com.alura.dyno.engine3d.tree.EventTreeIterator;

import java.util.HashMap;

public class TreeEventDispatcher {
    private Glyph root;
    private HashMap<TreeEventType, EventTreeIterator> cache;

    public TreeEventDispatcher(Glyph root) {
        this.root = root;
        cache = new HashMap<>();
    }

    public void sendDownTheTree(IEvent event) {
        EventTreeIterator iterator = getEventTreeIterator(event);

        while(iterator.hasNext()) {
            ITreeEventHandler handler = iterator.next();
            handler.onExecute(event);
        }
    }
    private EventTreeIterator getEventTreeIterator(IEvent event) {
        if(cache.containsKey(event.getType())) {
            return cache.get(event.getType());
        } else {
            return EventTreeIterator.fromNodeDown(root, event.getType());
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
