package com.alura.dyno.engine3d.text;

import com.alura.dyno.engine3d.draw.ShapeDrawer;
import com.alura.dyno.engine3d.draw.shapes.Quad;
import com.alura.dyno.engine3d.draw.shapes.Shape;
import com.alura.dyno.engine3d.render.Triangle;
import com.alura.dyno.engine3d.render.Vertex;
import com.alura.dyno.engine3d.render.VertexBuilder;
import com.alura.dyno.engine3d.render.buffer.Mesh;
import com.alura.dyno.engine3d.utils.RGBAColor;
import com.alura.dyno.math.graphics.Quaternion;
import com.alura.dyno.math.graphics.Vector2;
import com.alura.dyno.math.graphics.Vector3;

import java.util.Arrays;

public class SingleLineSimpleTextLayouter implements ITextLayouter<String> {
    @Override public Mesh layoutText(Font font, String text) {
        ShapeDrawer drawer = new ShapeDrawer();
        for (char ch : text.toCharArray()) {
            FontCharacter fontCh = font.find(ch);

            drawer.addShape(
                    new Quad.Builder()
                            .setBounds(fontCh.left, fontCh.right, fontCh.bottom, fontCh.top)
                            .setColor(RGBAColor.WHITE)
                            .setUVs(fontCh.uvLeft, fontCh.uvRight, fontCh.uvBottom, fontCh.uvTop)
                            .build()
            );

            drawer.cursor.axii.move(Vector3.right().multiply(fontCh.xadvance));
        }

        return drawer.asMesh();
    }
    private Vertex[] calculateCharQuad(FontCharacter fontCh, float cursorX) {
        float left = cursorX + fontCh.left;
        float right = cursorX + fontCh.right;
        float top = fontCh.top;
        float bottom = fontCh.bottom;

        Vertex v1 = new VertexBuilder()
                .setPosition(new Vector3(left, bottom, 0))
                .setNormal(new Vector3(0.0f, 0.0f, 1.0f))
                .setUVs(new Vector2(fontCh.uvLeft, fontCh.uvBottom))
                .setColor(new RGBAColor(1.0f,1.0f,1.0f,1.0f))
                .build();
        Vertex v2 = new VertexBuilder()
                .setPosition(new Vector3(right, bottom, 0))
                .setNormal(new Vector3(0.0f, 0.0f, 1.0f))
                .setUVs(new Vector2(fontCh.uvRight, fontCh.uvBottom))
                .setColor(new RGBAColor(1.0f,1.0f,1.0f,1.0f))
                .build();
        Vertex v3 = new VertexBuilder()
                .setPosition(new Vector3(right, top, 0))
                .setNormal(new Vector3(0.0f, 0.0f, 1.0f))
                .setUVs(new Vector2(fontCh.uvRight, fontCh.uvTop))
                .setColor(new RGBAColor(1.0f,1.0f,1.0f,1.0f))
                .build();
        Vertex v4 = new VertexBuilder()
                .setPosition(new Vector3(left, top, 0))
                .setNormal(new Vector3(0.0f, 0.0f, 1.0f))
                .setUVs(new Vector2(fontCh.uvLeft, fontCh.uvTop))
                .setColor(new RGBAColor(1.0f,1.0f,1.0f,1.0f))
                .build();

        return new Vertex[]{v1,v2,v3,v4};
    }
}
