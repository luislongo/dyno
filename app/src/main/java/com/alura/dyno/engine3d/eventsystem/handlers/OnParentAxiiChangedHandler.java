package com.alura.dyno.engine3d.eventsystem.handlers;

import com.alura.dyno.engine3d.eventsystem.IEvent;
import com.alura.dyno.engine3d.eventsystem.ITreeEventHandler;
import com.alura.dyno.engine3d.eventsystem.TreeEventType;
import com.alura.dyno.engine3d.eventsystem.events.OnParentAxiiChangedEvent;
import com.alura.dyno.engine3d.tree.TreeNode;

public abstract class OnParentAxiiChangedHandler extends ITreeEventHandler<OnParentAxiiChangedEvent> {

    @Override public TreeEventType getType() {
        return TreeEventType.OnParentAxiiChanged;
    }

}
