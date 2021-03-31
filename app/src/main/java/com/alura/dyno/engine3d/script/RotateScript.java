package com.alura.dyno.engine3d.script;

import com.alura.dyno.engine3d.eventsystem.events.OnUpdateEvent;
import com.alura.dyno.engine3d.eventsystem.handlers.OnUpdateEventHandler;
import com.alura.dyno.math.MathExtra;
import com.alura.dyno.math.graphics.Vector3;

public class RotateScript extends Script {
    Vector3 deltaEuler;

    public RotateScript(String name, Vector3 deltaEuler) {
        super(name);
        this.deltaEuler = deltaEuler;

        addEventHandler(new OnUpdate());
    }

    private class OnUpdate extends OnUpdateEventHandler {

        @Override
        public void onExecute(OnUpdateEvent event) {
            getParent().transform().eulerPlus(deltaEuler);
        }
    }
}
