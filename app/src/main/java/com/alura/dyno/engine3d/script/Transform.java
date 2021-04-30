package com.alura.dyno.engine3d.script;

import com.alura.dyno.engine3d.eventsystem.events.OnCreateEvent;
import com.alura.dyno.engine3d.eventsystem.events.OnParentAxiiChangedEvent;
import com.alura.dyno.engine3d.eventsystem.events.OnUpdateEvent;
import com.alura.dyno.engine3d.eventsystem.handlers.OnCreateEventHandler;
import com.alura.dyno.engine3d.eventsystem.handlers.OnParentAxiiChangedHandler;
import com.alura.dyno.engine3d.eventsystem.handlers.OnUpdateEventHandler;
import com.alura.dyno.engine3d.scene.SceneController;
import com.alura.dyno.math.graphics.Axii;
import com.alura.dyno.math.graphics.GraphicMatrix;
import com.alura.dyno.math.graphics.Quaternion;
import com.alura.dyno.math.graphics.Vector3;

public class Transform extends Script {
    private Axii localAxii;
    private Axii globalAxii;
    boolean hasToBeUpdated = false;

    public Transform(String name) {
        super(name);

        localAxii = new Axii();
        globalAxii = new Axii();

        addEventHandler(new FirstCreateGlobal());
        addEventHandler(new OnParentAxiiChanged());
        addEventHandler(new UpdateGlobalMatrix());
    }

    public GraphicMatrix getModelMatrix() {
        return globalAxii.getModelMatrix();
    }
    public void setLocalAxii(Axii localAxii) {
        this.localAxii = localAxii.clone();
        updateGlobalAxii();
        notifyChildren();
    }

    private void updateGlobalAxii() {
        if(getParent().hasParent()) {
            globalAxii = Axii.compose(getParent().getParent().getTransform().globalAxii, localAxii);
        } else {
            globalAxii = localAxii.clone();
        }
    }
    private void notifyChildren() {
        OnParentAxiiChangedEvent event = new OnParentAxiiChangedEvent(this.parent);
        SceneController.getDispatcher().sendDownTheChildrenOf(this.getParent(), event);
    }

    public void move(Vector3 delta) {
        localAxii.move(delta);
        notifyChildren();
    }
    public void scalePlus(Vector3 delta) {
        localAxii.scalePlus(delta);
        notifyChildren();
    }
    public void scaleTimes(Vector3 delta) {
        localAxii.scaleTimes(delta);
        notifyChildren();
    }
    public void eulerPlus(Vector3 delta) {
        localAxii.eulerPlus(delta);
        notifyChildren();
    }

    public void rotate(Quaternion q) {
        localAxii.rotate(q);
        notifyChildren();
    }

    public Vector3 getLocalPosition() {
        return localAxii.getPosition();
    }
    public Vector3 getGlobalPosition() {
        return globalAxii.getPosition();
    }
    public void setPosition(Vector3 position) {
        localAxii.setPosition(position);
        notifyChildren();
    }
    public Vector3 getLocalScale() {
        return localAxii.getScale();
    }
    public Vector3 getGlobalScale() {
        return globalAxii.getScale();
    }
    public void setScale(Vector3 scale) {
        localAxii.setScale(scale);
        notifyChildren();
    }
    public Vector3 getLocalEuler() {
            return localAxii.getEulerAngles();
    }
    public Vector3 getGlobalEuler() {
        return globalAxii.getEulerAngles();
    }
    public void setEuler(Vector3 euler) {
        localAxii.setEuler(euler);
        notifyChildren();
    }

    private class FirstCreateGlobal extends OnCreateEventHandler {

        @Override
        public void onExecute(OnCreateEvent event) {
            updateGlobalAxii();
        }
    }
    private class OnParentAxiiChanged extends OnParentAxiiChangedHandler {

        @Override public void onExecute(OnParentAxiiChangedEvent event) {
            hasToBeUpdated = true;
        }
    }
    private class UpdateGlobalMatrix extends OnUpdateEventHandler {

        @Override public void onExecute(OnUpdateEvent event) {
            updateGlobalAxii();
        }
    }
}