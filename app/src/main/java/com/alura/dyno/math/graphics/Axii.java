package com.alura.dyno.math.graphics;

import com.alura.dyno.math.linalg.Algebra;

public class Axii {
    private Vector3 position;
    private Vector3 scale;
    private Vector3 euler;
    private boolean isUpdated;
    private GraphicMatrix modelMatrix;

    public Axii() {
        this.position = new Vector3(0.0f);
        this.scale = new Vector3(1.0f);
        this.euler = new Vector3(0.0f);
    }
    public Axii(Vector3 position) {
        this.position = position.clone();
        this.scale = new Vector3(1.0f);
        this.euler = new Vector3(0.0f);
    }
    public Axii(Vector3 position, Vector3 scale, Vector3 rotation) {
        this.position = position.clone();
        this.scale = scale.clone();
        this.euler = rotation.clone();
    }

    public GraphicMatrix getModelMatrix() {
        if (!isUpdated) {
            updateModelMatrix();
        }
        return modelMatrix;
    }
    private void updateModelMatrix() {
        modelMatrix = Algebra.graphicMatrixFactory().identity();
        modelMatrix.scale(scale).rotateEuler(euler).translate(position);
        isUpdated = true;
    }

    public void setPosition(Vector3 position) {
        this.position = position.clone();
        isUpdated = false;
    }
    public void setScale(Vector3 scale) {
        this.scale = scale.clone();
        isUpdated = false;
    }
    public void setEuler(Vector3 euler) {
        this.euler = euler.clone();
        isUpdated = false;
    }
    public Vector3 getPosition() {
        return position.clone();
    }
    public Vector3 getScale() {
        return scale.clone();
    }
    public Vector3 getEuler() {
        return euler.clone();
    }

    public void move(Vector3 delta) {
        position.plus(delta);
        isUpdated = false;
    }
    public void scalePlus(Vector3 delta) {
        scale.plus(delta);
        isUpdated = false;
    }
    public void scaleTimes(Vector3 delta) {
        scale.straightProduct(delta);
        isUpdated = false;
    }
    public void eulerPlus(Vector3 delta) {
        euler.plus(delta);
        isUpdated = false;
    }

    public Axii clone() {
        return new Axii(this.position, this.scale, this.euler);
    }
    public static Axii compose(Axii lhs, Axii rhs) {
        Vector3 composedPos = rhs.position.clone().multiply(lhs.getModelMatrix(), 1.0f);
        Vector3 composedScale = rhs.scale.clone().straightProduct(lhs.scale);
        Vector3 composedRotation = rhs.euler.clone().plus(lhs.euler);

        return new Axii(composedPos, composedScale, composedRotation);
    }

}
