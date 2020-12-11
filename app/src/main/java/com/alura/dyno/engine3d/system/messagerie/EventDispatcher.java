package com.alura.dyno.engine3d.system.messagerie;

import com.alura.dyno.engine3d.system.messagerie.events.EngineEvent;
import com.alura.dyno.engine3d.utils.Queue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//.. Currently events are blocking. It would be nice for this system to be async ...

public class EventDispatcher {
    protected static final Map<EventType, ArrayList<IEventListener>> subs = new HashMap<>();
    protected static final Queue<EngineEvent> eventQueue = new Queue<>();

    protected static void subscribe(IEventListener listener) {
        EventType key = listener.getType();

        if(!subs.containsKey(key)) {
            registerEventType(key);
        }

        subs.get(key).add(listener);
    }
    private static void registerEventType(EventType type) {
        subs.put(type, new ArrayList<IEventListener>());
    }

    public static void unsubscribe(IEventListener listener) {
        EventType key = listener.getType();
        if(subs.containsKey(key)) {
            subs.get(key).remove(listener);
        }
    }

    public static void enqueueEvent(EngineEvent event) {
        eventQueue.enqueue(event);

        //TODO This has to be asynchronous
        dispatchEvent(eventQueue.dequeue());
    }
    private static void dispatchEvent(EngineEvent event) {
        EventType type = event.getType();
        List<IEventListener> subToEvent = getSubscribedListeners(type);
        dispatchToSubscribed(event, subToEvent);
    }
    private static List<IEventListener> getSubscribedListeners(EventType type) {
        List<IEventListener> subToEvent = new ArrayList<>();
        if(subs.containsKey(type)) {
            subToEvent = subs.get(type);
        }
        return subToEvent;
    }
    private static void dispatchToSubscribed(EngineEvent event, List<IEventListener> subToEvent) {
        for(IEventListener listener : subToEvent) {
            listener.onTrigger(event);
        }
    }
}
