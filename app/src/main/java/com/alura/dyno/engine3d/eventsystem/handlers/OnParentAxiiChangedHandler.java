package com.alura.dyno.engine3d.eventsystem.handlers;

import com.alura.dyno.engine3d.eventsystem.TreeEventType;
import com.alura.dyno.engine3d.eventsystem.events.OnParentAxiiChangedEvent;

public abstract class OnParentAxiiChangedHandler implements ITreeEventHandler<OnParentAxiiChangedEvent> {

    @Override public TreeEventType getType() {
        return TreeEventType.OnParentAxiiChanged;
    }

}
