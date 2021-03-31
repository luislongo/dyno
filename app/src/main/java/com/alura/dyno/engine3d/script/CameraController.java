package com.alura.dyno.engine3d.script;

import android.opengl.Matrix;
import android.util.Log;

import com.alura.dyno.engine3d.eventsystem.events.OnDragEvent;
import com.alura.dyno.engine3d.eventsystem.events.OnScaleEvent;
import com.alura.dyno.engine3d.eventsystem.events.OnViewChangedEvent;
import com.alura.dyno.engine3d.eventsystem.handlers.OnDragEventHandler;
import com.alura.dyno.engine3d.eventsystem.handlers.OnScaleEventHandler;
import com.alura.dyno.engine3d.eventsystem.handlers.OnViewChangedHandler;
import com.alura.dyno.engine3d.glyph.Camera;
import com.alura.dyno.math.MathExtra;
import com.alura.dyno.math.graphics.Vector3;

public class CameraController extends Script<Camera> {

    float minZoom = 10.0f;
    float maxZoom = 5000.0f;

    public CameraController(String name)
    {
        super(name);

        this.addEventHandler(new OnDrag());
        this.addEventHandler(new OnScale());
        this.addEventHandler(new OnViewChanged());
    }

    public void setMinZoom(float minZoom) {
        this.minZoom = minZoom;
    }
    public void setMaxZoom(float maxZoom) {
        this.maxZoom = maxZoom;
    }
    public float getMinZoom() {
        return minZoom;
    }
    public float getMaxZoom() {
        return maxZoom;
    }

    private class OnDrag extends OnDragEventHandler {

        @Override public void onExecute(OnDragEvent event) {
            Vector3 distance = new Vector3(event.getDeltaScreen().divide(2 * parent.getZoom()), 0.0f);
            distance.straightProduct(new Vector3(-1.0f, 1.0f, 1.0f));

            getParent().transform().move(distance);
            getParent().invalidate();
        }
    }
    private class OnScale extends OnScaleEventHandler {

        @Override public void onExecute(OnScaleEvent event) {
            float scaleFactor = event.getScaleFactor();
            float newZoom = MathExtra.clamp(parent.getZoom() * scaleFactor, minZoom, maxZoom);

            parent.setZoom(newZoom);
        }
    }
    private class OnViewChanged extends OnViewChangedHandler {

        @Override
        public void onExecute(OnViewChangedEvent event) {
            parent.setScreenSize(event.screenSize());
        }
    }
}
