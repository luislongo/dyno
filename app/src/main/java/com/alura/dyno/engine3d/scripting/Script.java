package com.alura.dyno.engine3d.scripting;

import com.alura.dyno.engine3d.events.ITreeEvent;
import com.alura.dyno.engine3d.events.TreeEventType;
import com.alura.dyno.engine3d.tree.TreeLeaf;

import java.util.LinkedList;

public class Script extends TreeLeaf<Glyph> {
    private LinkedList<ITreeEvent> events;

    public Script(Glyph parent) {
        super(parent);
        this.events = new LinkedList<>();
    }

    protected void addNewEvent(ITreeEvent event) {
        events.addLast(event);
    }
    protected LinkedList<ITreeEvent> getEvents() {
        return events;
    }
    public LinkedList<ITreeEvent> getEvents(TreeEventType type) {
        LinkedList<ITreeEvent> filteredEvents = new LinkedList<>();
        for(ITreeEvent event : events) {
            if(event.getType() == type) {
                filteredEvents.add(event);
            }
        }

        return filteredEvents;
    }
}
