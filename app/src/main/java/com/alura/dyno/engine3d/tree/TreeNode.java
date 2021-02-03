package com.alura.dyno.engine3d.tree;
import java.util.LinkedList;

public abstract class TreeNode<NODE extends TreeNode, LEAF extends TreeLeaf> {
    LinkedList<LEAF> leaves;
    LinkedList<NODE> children;
    NODE parent;

    public TreeNode() {
        leaves = new LinkedList<>();
        children = new LinkedList<>();
    }

    public void addChild(NODE child) {
        child.parent.removeChild(this);
        child.parent = this;
        children.add(child);
    }
    public void removeChild(NODE child) {
        children.remove(child);
    }

    public void addLeaf(LEAF leaf) {
        leaves.addLast(leaf);
    }
    public void removeLeaf(LEAF leaf) {
        leaves.remove(leaf);
    }

    public boolean hasParent() {
        return parent != null;
    }
    public NODE getParent() {
        if(hasParent()) {
            return parent;
        } else {
            throw new RuntimeException();
        }
    }
    public NODE getRoot() {
        NODE root = (NODE) this;
        while(root.hasParent()) {
            root = getParent();
        }
        return root;
    }

    public LinkedList<NODE> getChildren() {
        return children;
    }
    public LinkedList<NODE> getBrothers() {
        if(hasParent()) {
            return this.parent.getChildren();
        } else {
            return new LinkedList<NODE>();
        }
    }
    public LinkedList<LEAF> getLeaves() {return leaves; }

}

