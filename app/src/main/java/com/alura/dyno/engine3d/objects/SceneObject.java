package com.alura.dyno.engine3d.objects;

import com.alura.dyno.engine3d.components.Transform2D;
import com.alura.dyno.engine3d.system.TreeNode;
import com.alura.dyno.engine3d.system.events.SceneObjectEvent;
import com.alura.dyno.engine3d.system.events.TreeEventDispatcher;

public abstract class SceneObject extends TreeNode<SceneObject>
        implements SceneObjectEvent.IOnParentTransformChangedListener {
    public static final String LOCAL_TRANSFORM_KEY = "LOCAL_TRANSFORM";
    public static final String GLOBAL_TRANSFORM_KEY = "GLOBAL_TRANSFORM";

    boolean isVisible = true;

    public SceneObject(SceneObjectBuilder builder) {
        super(builder);

        addTransformComponents(builder.localTransform);
    }

    private void addTransformComponents(Transform2D localTransform) {
        addComponent(localTransform);
        addComponent(new Transform2D(GLOBAL_TRANSFORM_KEY));

        updateGlobalTransform();
    }


    @Override
    public void setParent(SceneObject newParent) {
        super.setParent(newParent);

        SceneObjectEvent.OnNewParentSetEvent event = new SceneObjectEvent.OnNewParentSetEvent();
        TreeEventDispatcher.dispatchEvent(event, this);
    }

    public final Transform2D getLocalTransform() {
        return findComponentByName(Transform2D.class, LOCAL_TRANSFORM_KEY);
    }

    public final Transform2D getGlobalTransform() {
        return findComponentByName(Transform2D.class, GLOBAL_TRANSFORM_KEY);
    }

    @Override
    public void onNewParentSet() {
        super.onNewParentSet();
        updateGlobalTransform();
    }

    @Override
    public void onParentTransformChanged() {
        updateGlobalTransform();
    }

    private void updateGlobalTransform() {
        if (parent == null) {
            setGlobalEqualToLocal();
        } else {
            calculateGlobalFromLocal();
        }
    }

    private void setGlobalEqualToLocal() {
        Transform2D global = getGlobalTransform();
        global.copyValues(getLocalTransform());
    }

    private void calculateGlobalFromLocal() {
        Transform2D newGlobal = Transform2D.multiply(parent.getGlobalTransform(), getLocalTransform());
        getGlobalTransform().copyValues(newGlobal);
    }

    //Builder
    public abstract static class SceneObjectBuilder<T extends SceneObjectBuilder<T>>
            extends TreeNode.TreeNodeBuilder<T> {
        protected Transform2D localTransform = new Transform2D(LOCAL_TRANSFORM_KEY);

        public SceneObjectBuilder(String name) {
            super(name);
        }

        public T setPosition(float x, float y) {
            this.localTransform.setPosition(x, y);

            return (T) this;
        }

        public T setPosition(float x, float y, float z) {
            this.localTransform.setPosition(x, y, z);

            return (T) this;
        }

        public T setAngle(float angle) {
            this.localTransform.setAngle(angle);

            return (T) this;
        }

    }
}
