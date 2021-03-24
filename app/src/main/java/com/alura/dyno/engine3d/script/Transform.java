package com.alura.dyno.engine3d.script;

import com.alura.dyno.engine3d.eventsystem.events.OnParentAxiiChangedEvent;
import com.alura.dyno.engine3d.eventsystem.handlers.OnParentAxiiChangedHandler;
import com.alura.dyno.engine3d.scene.SceneController;
import com.alura.dyno.math.graphics.Axii;
import com.alura.dyno.math.graphics.Vector3;

public class Transform extends Script {
    private Axii localAxii;
    private Axii globalAxii;

    public Transform(String name) {
        super(name);

        localAxii = new Axii();
        globalAxii = new Axii();

        addEventHandler(new OnParentAxiiChanged());
    }

    public Axii getLocalAxii() {
        return localAxii.clone();
    }
    public Axii getGlobalAxii() {
        return globalAxii.clone();
    }
    public void setLocalAxii(Axii localAxii) {
        this.localAxii = localAxii.clone();
        updateGlobalAxii();
        notifyChildren();
    }

    private void updateGlobalAxii() {
        if(getParent().hasParent()) {
            globalAxii = Axii.compose(getParent().getParent().transform().globalAxii, localAxii);
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
        invalidate();
    }
    public void scalePlus(Vector3 delta) {
        localAxii.scalePlus(delta);
        invalidate();
    }
    public void scaleTimes(Vector3 delta) {
        localAxii.scaleTimes(delta);
        invalidate();
    }
    public void eulerPlus(Vector3 delta) {
        localAxii.eulerPlus(delta);
        invalidate();
    }

    public Vector3 getLocalPosition() {
        return localAxii.getPosition();
    }
    public Vector3 getGlobalPosition() {
        return globalAxii.getPosition();
    }
    public void setPosition(Vector3 position) {
        localAxii.setPosition(position);
        invalidate();
    }
    public Vector3 getLocalScale() {
        return localAxii.getScale();
    }
    public Vector3 getGlobalScale() {
        return globalAxii.getScale();
    }
    public void setScale(Vector3 scale) {
        localAxii.setScale(scale);
        invalidate();
    }
    public Vector3 getLocalEuler() {
            return localAxii.getEuler();
    }
    public Vector3 getGlobalEuler() {
        return globalAxii.getEuler();
    }
    public void setEuler(Vector3 euler) {
        localAxii.setEuler(euler);
        invalidate();
    }

    private void invalidate() {
        updateGlobalAxii();
        notifyChildren();
    }

    private class OnParentAxiiChanged extends OnParentAxiiChangedHandler {

        @Override public void onExecute(OnParentAxiiChangedEvent event) {
            updateGlobalAxii();
        }
    }
}