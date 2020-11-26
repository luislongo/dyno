package com.alura.dyno.engine3d.objects;

import com.alura.dyno.engine3d.components.Transform;
import com.alura.dyno.engine3d.system.TreeNode;
import com.alura.dyno.engine3d.system.events.SceneObjectEvent;
import com.alura.dyno.engine3d.system.events.TreeEventDispatcher;
import com.alura.dyno.maths.Vector3F;

public abstract class SceneObject extends TreeNode<SceneObject>
        implements SceneObjectEvent.IOnParentTransformChangedListener {
    public static final String LOCAL_TRANSFORM_KEY = "LOCAL_TRANSFORM";
    public static final String GLOBAL_TRANSFORM_KEY = "GLOBAL_TRANSFORM";

    boolean isVisible = true;

    public SceneObject(SceneObjectBuilder builder) {
        super(builder);

        addTransformComponents(builder.localTransform);
    }
    private void addTransformComponents(Transform localTransform) {
        addComponent(localTransform);
        addComponent(new Transform(GLOBAL_TRANSFORM_KEY));

        updateGlobalTransform();
    }

    @Override public void setParent(SceneObject newParent) {
        super.setParent(newParent);

        SceneObjectEvent.OnNewParentSetEvent event = new SceneObjectEvent.OnNewParentSetEvent();
        TreeEventDispatcher.dispatchEvent(event, this);
    }
    @Override public void onNewParentSet() {
        super.onNewParentSet();
        updateGlobalTransform();
    }
    @Override public void onParentTransformChanged() {
        updateGlobalTransform();
    }

    public final Transform getLocalTransform() {
        return findComponentByName(Transform.class, LOCAL_TRANSFORM_KEY);
    }
    public final Transform getGlobalTransform() {
        return findComponentByName(Transform.class, GLOBAL_TRANSFORM_KEY);
    }
    private void updateGlobalTransform() {
        if (parent == null) {
            setGlobalEqualToLocal();
        } else {
            calculateGlobalFromLocal();
        }
    }
    private void setGlobalEqualToLocal() {
        Transform global = getGlobalTransform();
        global.copyValues(getLocalTransform());
    }
    private void calculateGlobalFromLocal() {
        Transform newGlobal = Transform.multiply(parent.getGlobalTransform(), getLocalTransform());
        getGlobalTransform().copyValues(newGlobal);
    }

    //Builder
    public abstract static class SceneObjectBuilder<T extends SceneObjectBuilder<T>>
            extends TreeNode.TreeNodeBuilder<T> {
        protected Transform localTransform = new Transform(LOCAL_TRANSFORM_KEY);

        public SceneObjectBuilder(String name) {
            super(name);
        }

        public T setPosition(Vector3F position) {
            this.localTransform.setPosition(position);
            return (T) this;
        }
        public T setScale(Vector3F scale) {
            this.localTransform.setScale(scale);
            return (T) this;
        }
        public T setAngle(Vector3F angle) {
            this.localTransform.setEulerAngles(angle);
            return (T) this;
        }
    }
}