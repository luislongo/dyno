package com.alura.dyno.engine3d.script;

import com.alura.dyno.engine3d.eventsystem.events.OnDragEvent;
import com.alura.dyno.engine3d.eventsystem.events.OnScaleEvent;
import com.alura.dyno.engine3d.eventsystem.events.OnViewChangedEvent;
import com.alura.dyno.engine3d.eventsystem.handlers.OnDragEventHandler;
import com.alura.dyno.engine3d.eventsystem.handlers.OnScaleEventHandler;
import com.alura.dyno.engine3d.eventsystem.handlers.OnViewChangedHandler;
import com.alura.dyno.engine3d.glyph.Camera;
import com.alura.dyno.math.graphics.Vector3;

public class CameraController extends Script<Camera> {

    public CameraController(String name)
    {
        super(name);

        this.addEventHandler(new OnDrag());
        this.addEventHandler(new OnScale());
        this.addEventHandler(new OnViewChanged());
    }

    private class OnDrag extends OnDragEventHandler {

        @Override public void onExecute(OnDragEvent event) {
            Vector3 distance = new Vector3(event.getDeltaScreen().divide(parent.getZoom()), 0.0f);
            getParent().transform().move(distance);;
        }
    }
    private class OnScale extends OnScaleEventHandler {

        @Override public void onExecute(OnScaleEvent event) {
            float scaleFactor = event.getScaleFactor();
            parent.setZoom(parent.getZoom() * scaleFactor);
        }
    }
    private class OnViewChanged extends OnViewChangedHandler {

        @Override
        public void onExecute(OnViewChangedEvent event) {
            parent.setScreenSize(event.screenSize());
        }
    }
}
