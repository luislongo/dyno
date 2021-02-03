package com.alura.dyno.engine3d.components;

import com.alura.dyno.engine3d.scripting.Script;
import com.alura.dyno.maths.graphics.GraphicMatrix;
import com.alura.dyno.maths.graphics.Vector3F;

public class Transform extends Script {

    private Vector3F position;
    private Vector3F scale;
    private Vector3F eulerAngles;

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
        this.position = new Vector3F(origin.position);
        this.scale = new Vector3F(origin.scale);
        this.eulerAngles = new Vector3F(origin.eulerAngles);
    }

    public static Transform multiply(Transform t_lhs, Transform t_rhs) {
        GraphicMatrix rot_lhs = GraphicMatrix.identity();
        rot_lhs.rotate(t_lhs.eulerAngles);

        Vector3F newPosition =
                Vector3F.add(t_lhs.position, Vector3F.multiply(rot_lhs, t_rhs.position, 0.0f));
        Vector3F newScale = Vector3F.add(t_lhs.scale, t_rhs.scale);
        Vector3F newRotation = Vector3F.add(t_lhs.eulerAngles, t_rhs.eulerAngles);

        return TransformBuilder.builder("")
                .setPosition(newPosition)
                .setScale(newScale)
                .setEulerAngles(newRotation)
                .build();
    }

    public void move(Vector3F distance) {
        this.position.plus(distance);

        notifyChanged();
    }
    public void rotate(Vector3F eulerRotation) {
        this.eulerAngles.plus(eulerRotation);

        notifyChanged();
    }
    public void scale(Vector3F multiplier) {
        this.scale = Vector3F.straightProduct(this.scale, multiplier);
        notifyChanged();
    }

    public Vector3F fromViewToModelSpace(Vector3F viewCoords) {
        Vector3F modelCoords = Vector3F.multiply(modelMatrix, viewCoords, 1.0f);
        return modelCoords;
    }
    public Vector3F fromModelToViewSpace(Vector3F modelCoords) {
        GraphicMatrix invModelMatrix = getModelmatrix();
        invModelMatrix.invert();

        Vector3F viewCoords = Vector3F.multiply(invModelMatrix, modelCoords, 1.0f);
        return viewCoords;
    }

    public Vector3F getPosition() {
        return position;
    }
    public Vector3F getScale() {
        return scale;
    }
    public Vector3F getAngles() {
        return eulerAngles;
    }
    public GraphicMatrix getModelmatrix() {
        if (!isUpdated) {
            updateModelMatrix();
        }
        return modelMatrix;
    }
    public void setPosition(Vector3F newPosition) {
        this.position = newPosition;
        notifyChanged();
    }
    public void setScale(Vector3F newScale) {
        this.scale = newScale;
        notifyChanged();
    }
    public void setEulerAngles(Vector3F eulerAngles) {
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
        private Vector3F position = new Vector3F(0.0f, 0.0f, 0.0f);
        private Vector3F scale = new Vector3F( 1.0f, 1.0f, 1.0f);
        private Vector3F eulerAngles = new Vector3F(0.0f, 0.0f, 0.0f);

        protected TransformBuilder() {
        }

        public Transform build() {
            return new Transform(this);
        }
        public TransformBuilder setPosition(Vector3F position) {
            this.position = position;
            return this;
        }
        public TransformBuilder setScale(Vector3F scale) {
            this.scale = scale;
            return this;
        }
        public TransformBuilder setEulerAngles(Vector3F eulerAngles) {
            this.eulerAngles = eulerAngles;
            return this;
        }

    }
}
