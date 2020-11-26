package com.alura.dyno.engine3d.system;

import com.alura.dyno.engine3d.components.MonoBehaviour;
import com.alura.dyno.engine3d.objects.SceneObject;
import com.alura.dyno.engine3d.system.events.SceneObjectEvent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public abstract class TreeNode<T extends TreeNode>
        implements SceneObjectEvent.IOnParentChangedListener {
    protected String name;
    protected T parent;
    protected List<T> childrenList;
    protected HashMap<String, MonoBehaviour> componentList;

    public TreeNode(TreeNodeBuilder builder) {
        this.name = builder.name;

        childrenList = new ArrayList<>();
        componentList = new HashMap<String, MonoBehaviour>();
    }

    public final String getName() {
        return name;
    }
    public final boolean hasParent() {return (parent != null);}
    public final List<T> getChildrenList() {
        return childrenList;
    }
    public final int getChildrenCount() {
        return childrenList.size();
    }
    public final HashMap<String, MonoBehaviour> getComponentList() {
        return componentList;
    }
    public final <V extends MonoBehaviour> V getComponentByName(Class<V> componentClass, String name) {
        if (componentList.containsKey(name)) {
            MonoBehaviour mb = componentList.get(name);
            if (mb.getClass().isAssignableFrom(componentClass)) {
                return componentClass.cast(mb);
            }
        }

        throw new RuntimeException();
    }

    protected final void addChild(T child) {
        this.childrenList.add(child);
    }
    protected final void removeChild(String name) {
        this.childrenList.remove(name);
    }

    public final void addComponent(MonoBehaviour mb) {
        mb.onParentChanged((SceneObject) this);
        componentList.put(mb.getName(), mb);
    }
    public final void removeComponent(String name) {
        if (componentList.containsKey(name)) {
            MonoBehaviour mb = componentList.get(name);
            mb.onDestroy();

            componentList.remove(name);
        }
    }
    public final void removeComponent(MonoBehaviour component) {
        componentList.remove(component);
    }

    @Override public void onNewParentSet() {

    }
    public void setParent(T newParent) {
        T oldParent = this.parent;
        unlinkFromOldParent();
        linkToNewParent(newParent);
    }
    private void unlinkFromOldParent() {
        if (this.parent != null) {
            parent.removeChild(name);
        }
    }
    private void linkToNewParent(T newParent) {
        this.parent = newParent;
        parent.addChild(this);
    }

    //...Builder
    public abstract static class TreeNodeBuilder<T extends TreeNodeBuilder<T>> {
        protected String name;

        public TreeNodeBuilder(String name) {
            this.name = name;
        }
    }
}
