package com.alura.dyno.engine3d.script;

import com.alura.dyno.engine3d.draw.ShapeDrawer;
import com.alura.dyno.engine3d.draw.shapes.Quad;
import com.alura.dyno.engine3d.eventsystem.events.OnTapEvent;
import com.alura.dyno.engine3d.eventsystem.handlers.OnTapEventHandler;
import com.alura.dyno.engine3d.glyph.Glyph;
import com.alura.dyno.engine3d.render.buffer.Mesh;
import com.alura.dyno.engine3d.scene.SceneController;
import com.alura.dyno.engine3d.text.SingleLineSimpleTextLayouter;
import com.alura.dyno.math.graphics.Quaternion;
import com.alura.dyno.math.graphics.Vector3;

public class CreateBoxOnTap extends Script {
    public CreateBoxOnTap(String name) {
        super(name);
        addEventHandler(new OnTap());
    }

    private class OnTap extends OnTapEventHandler {
        float pos = 0;
        @Override
        public void onExecute(OnTapEvent event) {
            Glyph text = new Glyph("Text Glyph " + pos);
            text.getTransform().setPosition(new Vector3(pos, pos,-10));

            TextRenderer renderer = new TextRenderer("Text Renderer",
                    SceneController.getModel().getFont("Font"),
                    new SingleLineSimpleTextLayouter());
            renderer.setMaterial(SceneController.getModel().getMaterial("FontMaterial"));
            renderer.setShader(SceneController.getModel().getShader("ObjShader"));
            renderer.setText("ABCDEFGHIJKLM");

            text.addLeaf(renderer);
            parent.addChild(text);
            pos += 10;
        }
    }
}
