package com.alura.dyno.engine3d.components;

import com.alura.dyno.engine3d.objects.SceneObject;
import com.alura.dyno.engine3d.system.events.ComponentEvent;

public abstract class MonoBehaviour implements
        ComponentEvent.IOnCreateEventListener,
        ComponentEvent.IOnUpdateEventListener,
        ComponentEvent.IOnDestroyEventListener,
        ComponentEvent.IOnParentChangedEventListener {
    protected String name;
    boolean isActive;
    private SceneObject parent;

    protected MonoBehaviour(MonoBehaviourBuilder builder) {
        this.name = builder.name;
    }
    protected MonoBehaviour(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }

    @Override public void onCreate(ComponentEvent.OnCreateEvent event) {

    }
    @Override public void onUpdate(ComponentEvent.OnUpdateEvent event) {

    }
    @Override public void onDestroy() {
        parent = null;
    }
    @Override public void onParentChanged(SceneObject newParent) {
        if (parent != null) {
            parent.removeComponent(this);
        }
        parent = newParent;
    }

    public SceneObject getParent() {
        return parent;
    }
    public boolean isActive() {
        return isActive;
    }
    public void setActive(boolean isActive) {
        this.isActive = isActive;
    }

    public static abstract class MonoBehaviourBuilder<T extends MonoBehaviourBuilder<T>> {
        protected String name;

        protected MonoBehaviourBuilder(String name) {
            this.name = name;
        }
    }
}
