package com.alura.dyno.engine3d.script;

import com.alura.dyno.math.graphics.GraphicMatrix;
import com.alura.dyno.math.graphics.Vector3;
import com.alura.dyno.math.linalg.Algebra;

public class Transform extends Script {

    private Vector3 position;
    private Vector3 scale;
    private Vector3 rotation;

    private GraphicMatrix modelMatrix;
    private boolean isUpdated;

    public Transform(String name) {
        super(name);
        initializeVariables();
    }
    public Transform(String name, Transform origin) {
        super(name);
        this.copyValues(origin);
    }
    public Transform(String name, Vector3 position, Vector3 scale, Vector3 rotation) {
        super(name);
        initializeVariables();
    }
    private void initializeVariables() {
        this.position = new Vector3(0.0f);
        this.scale = new Vector3(0.0f);
        this.rotation = new Vector3(0.0f);
    }
    private void copyValues(Transform other) {
        this.position = other.position.clone();
        this.scale = other.scale.clone();
        this.rotation = other.rotation.clone();
    }

    public Vector3 getPosition() {
        return position.clone();
    }
    public Vector3 getScale() {
        return scale.clone();
    }
    public Vector3 getAngles() {
        return rotation.clone();
    }
    public GraphicMatrix getModelmatrix() {
        if (!isUpdated) {
            updateModelMatrix();
        }
        return modelMatrix;
    }
    private void updateModelMatrix() {
        modelMatrix = Algebra.graphicMatrixFactory().identity();
        modelMatrix.scale(scale).rotateEuler(rotation).translate(position);
        isUpdated = true;
    }

    public void setPosition(Vector3 newPosition) {
        this.position = newPosition;
        notifyChanged();
    }
    public void setScale(Vector3 newScale) {
        this.scale = newScale;
        notifyChanged();
    }
    public void setRotation(Vector3 eulerAngles) {
        this.rotation = rotation;
        notifyChanged();
    }

    public void move(Vector3 distance) {
        this.position.plus(distance);
        notifyChanged();
    }
    public void rotate(Vector3 eulerRotation) {
        this.rotation.plus(eulerRotation);
        notifyChanged();
    }
    public void scale(Vector3 multiplier) {
        this.scale.straightProduct(multiplier);
        notifyChanged();
    }

    public static Transform compose(String name, Transform t_lhs, Transform t_rhs) {
        GraphicMatrix modelMatrix_lhs = t_lhs.getModelmatrix();

        Vector3 composedPos = t_rhs.position.clone().multiply(modelMatrix_lhs, 1.0f);
        Vector3 composedScale = t_rhs.scale.clone().multiply(modelMatrix_lhs, 0.0f);
        Vector3 composedRotation = t_rhs.rotation.clone().multiply(modelMatrix_lhs, 0.0f);

        return new Transform(name, composedPos, composedScale, composedRotation);
    }

    private void notifyChanged() {
        isUpdated = false;

        if (getParent() != null) {
            //TODO Send event down the tree;
        }
    }
}