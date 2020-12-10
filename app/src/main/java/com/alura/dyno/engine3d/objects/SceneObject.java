package com.alura.dyno.engine3d.objects;

import com.alura.dyno.engine3d.components.Transform;
import com.alura.dyno.engine3d.system.TreeNode;
import com.alura.dyno.engine3d.system.events.SceneObjectEvent;
import com.alura.dyno.engine3d.system.events.TreeEventDispatcher;
import com.alura.dyno.maths.Vector3G;

public abstract class SceneObject extends TreeNode<SceneObject>
        implements SceneObjectEvent.IOnParentTransformChangedListener {
    public static final String LOCAL_TRANSFORM_KEY = "LOCAL_TRANSFORM";
    public static final String GLOBAL_TRANSFORM_KEY = "GLOBAL_TRANSFORM";

    public SceneObject(SceneObjectBuilder builder) {
        super(builder);

        addTransformComponents(builder);
        updateGlobalTransform();
    }
    private void addTransformComponents(SceneObjectBuilder builder) {
        Transform localTransform = Transform.TransformBuilder.builder(LOCAL_TRANSFORM_KEY)
                .setPosition(builder.position)
                .setScale(builder.scale)
                .setEulerAngles(builder.eulerAngles)
                .build();
        Transform globalTransform = Transform.TransformBuilder.builder(GLOBAL_TRANSFORM_KEY)
                .build();

        addComponent(localTransform);
        addComponent(globalTransform);
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
    private void updateGlobalTransform() {
        if (hasParent()) {
            calculateGlobalFromLocal();
        } else {
            setGlobalEqualToLocal();
        }
    }
    private void calculateGlobalFromLocal() {
        Transform newGlobal = Transform.compose(parent.getGlobalTransform(), getLocalTransform());
        getGlobalTransform().copyValues(newGlobal);
    }
    private void setGlobalEqualToLocal() {
        getGlobalTransform().copyValues(getLocalTransform());
    }

    public final Transform getLocalTransform() {
        return getComponentByName(Transform.class, LOCAL_TRANSFORM_KEY);
    }
    public final Transform getGlobalTransform() {
        return getComponentByName(Transform.class, GLOBAL_TRANSFORM_KEY);
    }

    //...Builder
    public abstract static class SceneObjectBuilder<T extends SceneObjectBuilder<T>>
            extends TreeNode.TreeNodeBuilder<T> {
        Vector3G position = new Vector3G(0.0f, 0.0f, 0.0f);
        Vector3G eulerAngles = new Vector3G(0.0f, 0.0f, 0.0f);
        Vector3G scale = new Vector3G(1.0f, 1.0f, 1.0f);

        public SceneObjectBuilder(String name) {
            super(name);
        }

        public T setPosition(Vector3G position) {
            this.position = new Vector3G(position);
            return (T) this;
        }
        public T setScale(Vector3G scale) {
            this.scale = new Vector3G(scale);
            return (T) this;
        }
        public T setEulerAngle(Vector3G eulerAngles) {
            this.eulerAngles = new Vector3G(eulerAngles);
            return (T) this;
        }
    }
}