package com.alura.dyno.engine3d.eventsystem.events;

import com.alura.dyno.engine3d.eventsystem.IEvent;
import com.alura.dyno.engine3d.eventsystem.TreeEventType;
import com.alura.dyno.engine3d.tree.TreeNode;

public class OnParentAxiiChangedEvent implements IEvent {
    TreeNode callerNode;

    public OnParentAxiiChangedEvent(TreeNode callerNode) {
        this.callerNode = callerNode;
    }

    @Override public TreeEventType getType() {
        return TreeEventType.OnParentAxiiChanged;
    }

}