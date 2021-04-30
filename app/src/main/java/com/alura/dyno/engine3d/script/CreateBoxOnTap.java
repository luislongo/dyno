package com.alura.dyno.engine3d.script;

import com.alura.dyno.engine3d.draw.ShapeDrawer;
import com.alura.dyno.engine3d.draw.shapes.Quad;
import com.alura.dyno.engine3d.eventsystem.events.OnTapEvent;
import com.alura.dyno.engine3d.eventsystem.handlers.OnTapEventHandler;
import com.alura.dyno.engine3d.glyph.Glyph;
import com.alura.dyno.engine3d.render.buffer.Mesh;
import com.alura.dyno.engine3d.scene.SceneController;
import com.alura.dyno.math.graphics.Quaternion;
import com.alura.dyno.math.graphics.Vector3;

public class CreateBoxOnTap extends Script {
    public CreateBoxOnTap(String name) {
        super(name);
        addEventHandler(new OnTap());
    }

    private class OnTap extends OnTapEventHandler {
        float size = 1.0f;
        @Override
        public void onExecute(OnTapEvent event) {
            Mesh mesh = new ShapeDrawer().addShape(new Quad(size, size))
                    .asMesh();
            size++;

            MeshRenderer renderer = new MeshRenderer("Quad");
            renderer.setMaterial(SceneController.getModel().getMaterial("Box"));
            renderer.setShader(SceneController.getModel().getShader("ObjShader"));
            renderer.setData(mesh);

            parent.addLeaf(renderer);
        }
    }
}
