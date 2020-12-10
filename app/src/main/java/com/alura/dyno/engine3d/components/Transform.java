package com.alura.dyno.engine3d.components;

import com.alura.dyno.engine3d.system.events.SceneObjectEvent;
import com.alura.dyno.engine3d.system.events.TreeEventDispatcher;
import com.alura.dyno.maths.MatrixG;
import com.alura.dyno.maths.MatrixGFactory;
import com.alura.dyno.maths.Vector3G;

public class Transform extends MonoBehaviour {

    private Vector3G position;
    private Vector3G scale;
    private Vector3G eulerAngles;

    private MatrixG modelMatrix = new MatrixG();
    private MatrixG globalModelMatrix = new MatrixG();

    public Transform(TransformBuilder builder) {
        super(builder);

        this.position = builder.position;
        this.scale = builder.scale;
        this.eulerAngles = builder.eulerAngles;
        updateModelMatrix();
    }
    public Transform(Transform origin) {
        super(origin.name);
        this.copyValues(origin);
    }
    public void copyValues(Transform origin) {
        this.position = new Vector3G(origin.position);
        this.scale = new Vector3G(origin.scale);
        this.eulerAngles = new Vector3G(origin.eulerAngles);
        updateModelMatrix();
    }

    public static Transform compose(Transform t_lhs, Transform t_rhs) {
        Vector3G position = Vector3G.multiply(t_lhs.getModelmatrix(), Vector3G.minus(t_rhs.position, t_lhs.position), 0.0f);
        Vector3G eulerAngles = Vector3G.plus(t_lhs.eulerAngles, t_rhs.eulerAngles);
        Vector3G scale = Vector3G.multiply(t_lhs.scale, t_rhs.scale);

        Transform composed = TransformBuilder.builder("")
                .setPosition(position)
                .setEulerAngles(eulerAngles)
                .setScale(scale)
                .build();

        return composed;
    }

    public void move(Vector3G distance) {
        this.position.plus(distance);
        notifyChanged();
    }
    public void rotate(Vector3G eulerRotation) {
        this.eulerAngles.plus(eulerRotation);
        notifyChanged();
    }
    public void scale(Vector3G multiplier) {
        this.scale.multiply(multiplier);
        notifyChanged();
    }

    public Vector3G fromViewToModelSpace(Vector3G viewCoords) {
        Vector3G modelCoords = Vector3G.multiply(modelMatrix, viewCoords, 1.0f);
        return modelCoords;
    }
    public Vector3G fromModelToViewSpace(Vector3G modelCoords) {
        MatrixG invModelMatrix = getModelmatrix();
        invModelMatrix.invert();

        Vector3G viewCoords = Vector3G.multiply(invModelMatrix, modelCoords, 1.0f);
        return viewCoords;
    }

    public Vector3G getPosition() {
        return position;
    }
    public Vector3G getScale() {
        return scale;
    }
    public Vector3G getAngles() {
        return eulerAngles;
    }
    public MatrixG getModelmatrix() {
        return modelMatrix;
    }
    public void setPosition(Vector3G newPosition) {
        this.position = newPosition;
        notifyChanged();
    }
    public void setScale(Vector3G newScale) {
        this.scale = newScale;
        notifyChanged();
    }
    public void setEulerAngles(Vector3G eulerAngles) {
        this.eulerAngles = eulerAngles;
        notifyChanged();
    }

    public void updateModelMatrix() {
        modelMatrix = MatrixGFactory.identity();

        modelMatrix.scale(scale);
        modelMatrix.rotate(eulerAngles);
        modelMatrix.translate(position);
    }

    private void notifyChanged() {
        updateModelMatrix();

        if (getParent() != null) {
            TreeEventDispatcher.dispatchEvent(new SceneObjectEvent.OnParentTransformChangedEvent(), getParent());
        }
    }

    public static class TransformBuilder<T extends TransformBuilder<T>>
            extends MonoBehaviourBuilder<T> {
        private Vector3G position = new Vector3G(0.0f, 0.0f, 0.0f);
        private Vector3G scale = new Vector3G( 1.0f, 1.0f, 1.0f);
        private Vector3G eulerAngles = new Vector3G(0.0f, 0.0f, 0.0f);

        protected TransformBuilder(String name) {
            super(name);
        }

        public static TransformBuilder<?> builder(String name) {
            return new TransformBuilder<>(name);
        }

        public Transform build() {
            return new Transform(this);
        }

        public T setPosition(Vector3G position) {
            this.position = position;

            return (T) this;
        }

        public T setScale(Vector3G scale) {
            this.scale = scale;

            return (T) this;
        }

        public T setEulerAngles(Vector3G eulerAngles) {
            this.eulerAngles = eulerAngles;
            return (T) this;
        }

    }
}
