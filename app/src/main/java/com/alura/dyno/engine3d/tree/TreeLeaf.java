package com.alura.dyno.engine3d.tree;

public class TreeLeaf<NODE extends TreeNode> {
    protected final String name;
    protected NODE parent;

    public TreeLeaf(String name) {
        this.name = name;
    }

    public String getName() {return this.name;}
    public NODE getParent() {
        return parent;
    }
    public boolean hasParent() {
        return parent != null;
    }}
