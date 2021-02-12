package com.alura.dyno.engine3d.tree;
import java.util.LinkedList;

public abstract class TreeNode<NODE extends TreeNode, LEAF extends TreeLeaf> extends TreeLeaf<NODE> {
    LinkedList<LEAF> leaves;
    LinkedList<NODE> children;

    public TreeNode(String name) {
        super(name);

        leaves = new LinkedList<>();
        children = new LinkedList<>();

    }

    public void addChild(NODE child) {
        if(child.hasParent()) {
            child.parent.children.remove(child);
        }
        child.parent = this;
        children.add(child);
    }
    public void removeChild(NODE child) {
        if(children.contains(child)) {
            child.parent = null;
            children.remove(child);
        }
    }

    public void addLeaf(LEAF leaf) {
        if(leaf.hasParent()) {
            leaf.parent.children.remove(leaf);
        }
        leaf.parent = this;
        leaves.addLast(leaf);
    }
    public void removeLeaf(LEAF leaf) {
        if(leaves.contains(leaf)) {
            leaf.parent = null;
            leaves.remove(leaf);
        }
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
    public String getName() {return this.name;}
    public LinkedList<NODE> getChildren() {
        return children;
    }
    public LinkedList<NODE> getChildren(String name) {
        LinkedList<NODE> selected = new LinkedList<>();

        for(NODE child : children) {
            if(child.getName() == name) {
                selected.add(child);
            }
        }

        return selected;
    }
    public LinkedList<LEAF> getLeaves() { return leaves; }
    public <LEAFLING extends LEAF> LinkedList<LEAFLING> getLeaves(String name) {
        LinkedList<LEAFLING> selected = new LinkedList<>();

        for(LEAF leaf : leaves) {
            if(leaf.getName() == name) {
                selected.add((LEAFLING) leaf);
            }
        }

        return selected;
    }
}

