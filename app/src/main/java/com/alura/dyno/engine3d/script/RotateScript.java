package com.alura.dyno.engine3d.script;

import com.alura.dyno.engine3d.eventsystem.events.OnUpdateEvent;
import com.alura.dyno.engine3d.eventsystem.handlers.OnUpdateEventHandler;
import com.alura.dyno.math.MathExtra;
import com.alura.dyno.math.graphics.Quaternion;
import com.alura.dyno.math.graphics.Vector3;

public class RotateScript extends Script {
    Quaternion q;

    public RotateScript(String name, Quaternion q) {
        super(name);
        this.q = q;

        addEventHandler(new OnUpdate());
    }

    private class OnUpdate extends OnUpdateEventHandler {

        @Override
        public void onExecute(OnUpdateEvent event) {
            getParent().transform().rotate(q);
        }
    }
}
