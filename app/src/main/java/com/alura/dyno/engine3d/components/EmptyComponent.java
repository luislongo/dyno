package com.alura.dyno.engine3d.components;

public class EmptyComponent extends MonoBehaviour {

    protected EmptyComponent(MonoBehaviourBuilder builder) {
        super(builder);
    }

    public EmptyComponent(String name) {
        super(name);
    }
}
