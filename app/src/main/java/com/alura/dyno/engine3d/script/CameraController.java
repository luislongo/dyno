package com.alura.dyno.engine3d.script;

import com.alura.dyno.engine3d.eventsystem.handlers.OnDragEventHandler;
import com.alura.dyno.engine3d.eventsystem.handlers.OnScaleEventHandler;
import com.alura.dyno.engine3d.eventsystem.handlers.OnScreenSizeChangedEventHandler;
import com.alura.dyno.engine3d.glyph.Camera;
import com.alura.dyno.math.MathExtra;
import com.alura.dyno.math.graphics.Vector3;

public class CameraController extends Script<Camera> {

    public CameraController(String name)
    {
        super(name);

        this.addEventHandler(new OnDrag());
        this.addEventHandler(new OnScale());
        this.addEventHandler(new OnScreenSizeChanged());
    }

    private class OnDrag extends OnDragEventHandler {

        @Override public void onExecute(OnDragEvent event) {
            Vector3 distance = new Vector3(event.getDeltaScreen().divide(parent.getZoom()), 0.0f);
            parent.localTransform().move(distance);

            parent.invalidate();
        }
    }
    private class OnScale extends OnScaleEventHandler {

        @Override public void onExecute(OnScaleEvent event) {
            float scaleFactor = event.getScaleFactor();
            parent.setZoom(parent.getZoom() * scaleFactor);
        }
    }
    private class OnScreenSizeChanged extends OnScreenSizeChangedEventHandler {

        @Override
        public void onExecute(OnScreenSizeChangedEvent event) {
            parent.setScreenSize(event.getScreenSize());
        }
    }
}
