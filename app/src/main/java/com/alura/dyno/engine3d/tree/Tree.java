package com.alura.dyno.engine3d.tree;

import com.alura.dyno.engine3d.scene.ITreeChangedListener;

public class Tree<NODE extends TreeNode, LEAF extends TreeLeaf> {
    private NODE root;
    private ITreeChangedListener<NODE, LEAF> listener;

    public Tree(NODE root) {
        this.root = root;
        root.tree = this;
    }
    public NODE getRoot() {
        return root;
    }
    public void setTreeChangedListener(ITreeChangedListener<NODE, LEAF> listener) {
        this.listener = listener;
    }
    public void notifyNodeChanged(NODE node) {
        if(listener != null) {
            listener.onTreeNodeChanged(node);
        }
    }
    public void notifyLeafChanged(LEAF leaf) {
        if(listener != null) {
            listener.onTreeLeafChanged(leaf);
        }
    }

}
