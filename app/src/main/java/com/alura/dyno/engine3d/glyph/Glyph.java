package com.alura.dyno.engine3d.glyph;

import com.alura.dyno.engine3d.script.Script;
import com.alura.dyno.engine3d.script.Transform;
import com.alura.dyno.engine3d.tree.TreeNode;

public class Glyph extends TreeNode<Glyph, Script> {
    private static final String LOCALTRANSFORMKEY = "LOCAL_TRANSFORM";
    private static final String GLOBALTRANSFORMKEY = "GLOBAL_TRANSFORM";
    private Transform localTransform;
    private Transform globalTransform;

    public Glyph(String name) {
        super(name);

        localTransform = new Transform(LOCALTRANSFORMKEY);
        globalTransform = new Transform(GLOBALTRANSFORMKEY);
    }

    public Transform localTransform() {
        return localTransform;
    }
    public Transform globalTransform() {
        return localTransform;
    }
}
