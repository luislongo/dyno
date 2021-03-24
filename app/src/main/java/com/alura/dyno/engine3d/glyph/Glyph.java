package com.alura.dyno.engine3d.glyph;

import com.alura.dyno.engine3d.script.Script;
import com.alura.dyno.engine3d.script.Transform;
import com.alura.dyno.engine3d.tree.TreeNode;

public class Glyph extends TreeNode<Glyph, Script> {
    private static final String TRANSFORMKEY = "TRANSFORM";
    private Transform transform;

    public Glyph(String name) {
        super(name);

        transform = new Transform(TRANSFORMKEY);
        this.addLeaf(transform);
    }
    public Transform transform() {
        return transform;
    }
}
