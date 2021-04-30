package com.alura.dyno.engine3d.scene;

import com.alura.dyno.engine3d.tree.TreeLeaf;
import com.alura.dyno.engine3d.tree.TreeNode;

public interface ITreeChangedListener<NODE extends TreeNode, LEAF extends TreeLeaf> {
    void onTreeNodeChanged(NODE node);
    void onTreeLeafChanged(LEAF leaf);
}
