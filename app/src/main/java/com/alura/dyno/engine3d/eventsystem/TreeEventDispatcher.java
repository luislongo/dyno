package com.alura.dyno.engine3d.eventsystem;

import com.alura.dyno.engine3d.eventsystem.events.IEvent;
import com.alura.dyno.engine3d.eventsystem.handlers.ITreeEventHandler;
import com.alura.dyno.engine3d.glyph.Glyph;
import com.alura.dyno.engine3d.tree.EventTreeIterator;

import java.util.HashMap;
import java.util.concurrent.SynchronousQueue;

public class TreeEventDispatcher {
    private Glyph root;
    private HashMap<TreeEventType, EventTreeIterator> cache;

    public TreeEventDispatcher(Glyph root) {
        this.root = root;
        cache = new HashMap<>();
    }

    public void sendDownTheTree(IEvent event) {
        EventTreeIterator iterator = getEventTreeIterator(event.getType());

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
    private EventTreeIterator getEventTreeIterator(TreeEventType type) {
        if(cache.containsKey(type)) {
            EventTreeIterator iterator =  cache.get(type);
            iterator.restart();

            return iterator;
        } else {
            EventTreeIterator iterator = EventTreeIterator.fromNodeDown(root, type);
            cache.put(type, iterator);

            return iterator;
        }
    }
    private void executeIfActive(ITreeEventHandler handler, IEvent event) {
            handler.onExecute(event);
    }

    public void invalidateCache() {
        cache.clear();
    }
    public void invalidateCache(TreeEventType handler) {
        cache.remove(handler);
    }
}
