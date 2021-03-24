package com.alura.dyno.engine3d.tree;
import android.os.Build;

import androidx.annotation.RequiresApi;

import java.util.LinkedList;
import java.util.function.Predicate;

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
            leaf.parent.leaves.remove(leaf);
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

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void removeLeaves(final String name) {
        leaves.removeIf(new Predicate<LEAF>() {
            @Override
            public boolean test(LEAF leaf) {
                return (leaf.getName() == name);
            }
        });
    }

    public String getName() {return this.name;}

    public NODE getRoot() {
        NODE root = (NODE) this;
        while(root.hasParent()) {
            root = (NODE) root.parent;
        }
        return root;
    }
    public NODE getParent() {
        if(hasParent()) {
            return parent;
        } else {
            throw new RuntimeException();
        }
    }
    public NODE getChild(String name) {
        for(NODE child : children) {
            if(child.getName().equals(name)) {
                return child;
            }
        }

        return null;
    }
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
    public <LEAFLING extends LEAF> LEAFLING getLeaf(String name) {
        for(LEAF leaf : leaves) {
            if(leaf.getName() == name) {
                return (LEAFLING) leaf;
            }
        }

        return null;
    }
    public <LEAFLING extends LEAF> LinkedList<LEAFLING> getLeaves(String name) {
        LinkedList<LEAFLING> selected = new LinkedList<>();

        for(LEAF leaf : leaves) {
            if(leaf.getName() == name) {
                selected.add((LEAFLING) leaf);
            }
        }

        return selected;
    }

    public boolean hasChildren() {
        return children.size() > 0;
    }
}

