package com.alura.dyno.engine3d.scripting;

import com.alura.dyno.engine3d.components.Transform;
import com.alura.dyno.engine3d.tree.TreeNode;

public class Glyph extends TreeNode<Glyph, Script> {
    private Transform localTransform;
    private Transform globalTransform;

    public Glyph() {
        localTransform = new Transform();
    }

}
