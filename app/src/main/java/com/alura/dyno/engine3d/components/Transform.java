package com.alura.dyno.engine3d.components;

import com.alura.dyno.engine3d.scripting.Script;
import com.alura.dyno.math.graphics.GraphicMatrix;
import com.alura.dyno.math.graphics.Vector3;

public class Transform extends Script {

    private Vector3 position;
    private Vector3 scale;
    private Vector3 eulerAngles;

    public Transform(TransformBuilder builder) {
        super(builder);

        this.position = builder.position;
        this.scale = builder.scale;
        this.eulerAngles = builder.eulerAngles;
    }
    public Transform(Transform origin) {
        super(origin.name);
        this.copyValues(origin);
    }
    public void copyValues(Transform origin) {
        this.position = new Vector3(origin.position);
        this.scale = new Vector3(origin.scale);
        this.eulerAngles = new Vector3(origin.eulerAngles);
    }

    public static Transform multiply(Transform t_lhs, Transform t_rhs) {
        GraphicMatrix rot_lhs = GraphicMatrix.identity();
        rot_lhs.rotate(t_lhs.eulerAngles);

        Vector3 newPosition =
                Vector3.add(t_lhs.position, Vector3.multiply(rot_lhs, t_rhs.position, 0.0f));
        Vector3 newScale = Vector3.add(t_lhs.scale, t_rhs.scale);
        Vector3 newRotation = Vector3.add(t_lhs.eulerAngles, t_rhs.eulerAngles);

        return TransformBuilder.builder("")
                .setPosition(newPosition)
                .setScale(newScale)
                .setEulerAngles(newRotation)
                .build();
    }

    public void move(Vector3 distance) {
        this.position.plus(distance);

        notifyChanged();
    }
    public void rotate(Vector3 eulerRotation) {
        this.eulerAngles.plus(eulerRotation);

        notifyChanged();
    }
    public void scale(Vector3 multiplier) {
        this.scale = Vector3.straightProduct(this.scale, multiplier);
        notifyChanged();
    }

    public Vector3 fromViewToModelSpace(Vector3 viewCoords) {
        Vector3 modelCoords = Vector3.multiply(modelMatrix, viewCoords, 1.0f);
        return modelCoords;
    }
    public Vector3 fromModelToViewSpace(Vector3 modelCoords) {
        GraphicMatrix invModelMatrix = getModelmatrix();
        invModelMatrix.invert();

        Vector3 viewCoords = Vector3.multiply(invModelMatrix, modelCoords, 1.0f);
        return viewCoords;
    }

    public Vector3 getPosition() {
        return position;
    }
    public Vector3 getScale() {
        return scale;
    }
    public Vector3 getAngles() {
        return eulerAngles;
    }
    public GraphicMatrix getModelmatrix() {
        if (!isUpdated) {
            updateModelMatrix();
        }
        return modelMatrix;
    }
    public void setPosition(Vector3 newPosition) {
        this.position = newPosition;
        notifyChanged();
    }
    public void setScale(Vector3 newScale) {
        this.scale = newScale;
        notifyChanged();
    }
    public void setEulerAngles(Vector3 eulerAngles) {
        this.eulerAngles = eulerAngles;
        notifyChanged();
    }

    public void updateModelMatrix() {
        modelMatrix = GraphicMatrix.identity();

        modelMatrix.translate(position);
        modelMatrix.rotate(eulerAngles);
        modelMatrix.scale(scale);

        isUpdated = true;
    }

    private void notifyChanged() {
        isUpdated = false;

        if (getParent() != null) {
            TreeEventDispatcher.dispatchEvent(new SceneObjectEvent.OnParentTransformChangedEvent(), getParent());
        }
    }

    public static class TransformBuilder {
        private Vector3 position = new Vector3(0.0f, 0.0f, 0.0f);
        private Vector3 scale = new Vector3( 1.0f, 1.0f, 1.0f);
        private Vector3 eulerAngles = new Vector3(0.0f, 0.0f, 0.0f);

        protected TransformBuilder() {
        }

        public Transform build() {
            return new Transform(this);
        }
        public TransformBuilder setPosition(Vector3 position) {
            this.position = position;
            return this;
        }
        public TransformBuilder setScale(Vector3 scale) {
            this.scale = scale;
            return this;
        }
        public TransformBuilder setEulerAngles(Vector3 eulerAngles) {
            this.eulerAngles = eulerAngles;
            return this;
        }

    }
}
