package com.alura.dyno.engine3d.tree;

import com.alura.dyno.engine3d.events.ITreeEvent;
import com.alura.dyno.engine3d.events.TreeEventType;
import java.util.LinkedList;

public class TreeLeaf<NODE extends TreeNode> {
    private NODE parent;

    public TreeLeaf(NODE parent) {
        this.parent = parent;
    }

}
