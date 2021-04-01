package com.alura.dyno.engine3d.render;

import com.alura.dyno.engine3d.render.attr.NormalAttribute;
import com.alura.dyno.engine3d.render.attr.ColorAttribute;
import com.alura.dyno.engine3d.render.attr.CustomAttribute;
import com.alura.dyno.engine3d.render.attr.IAttribute;
import com.alura.dyno.engine3d.render.attr.PositionAttribute;
import com.alura.dyno.engine3d.render.attr.UVAttribute;
import com.alura.dyno.engine3d.render.buffer.BufferLayout;
import com.alura.dyno.engine3d.utils.RGBAColor;
import com.alura.dyno.math.graphics.Vector2;
import com.alura.dyno.math.graphics.Vector3;

import org.junit.jupiter.api.Test;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BufferLayoutTest extends BufferLayout {
    @Test public void pushElementTest_1()  {
        pushAttribute(new PositionAttribute());
        assertEquals(12, getLayoutSize());
        assertEquals(3, attributes.lastElement().getCount());
        assertEquals("a_Position", attributes.lastElement().getName());

        pushAttribute(new ColorAttribute());
        assertEquals(28, getLayoutSize());
        assertEquals(4, attributes.lastElement().getCount());
        assertEquals("a_Color", attributes.lastElement().getName());

        pushAttribute(new NormalAttribute());
        assertEquals(40, getLayoutSize());
        assertEquals(3, attributes.lastElement().getCount());
        assertEquals("a_Normal", attributes.lastElement().getName());

        pushAttribute(new UVAttribute());
        assertEquals(48, getLayoutSize());
        assertEquals(2, attributes.lastElement().getCount());
        assertEquals("a_UVs", attributes.lastElement().getName());

    }
    @Test public void pushElementTest_2()  {
        CustomAttribute attr = new CustomAttribute("a_Test", 23, 3, false) {
            @Override
            public void layoutVertex(Vertex v, FloatBuffer buffer) {

            }
        };

        pushAttribute(attr);

        assertEquals(23, getLayoutSize());
        assertEquals("a_Test", attributes.lastElement().getName());
        assertEquals(3, attributes.lastElement().getCount());
        assertEquals(false, attributes.lastElement().doNormalize());
    }
    @Test public void popElementTest_1()   {
        pushAttribute(new PositionAttribute());
        pushAttribute(new ColorAttribute());
        pushAttribute(new NormalAttribute());
        pushAttribute(new UVAttribute());

        assertEquals(48, getLayoutSize());

        IAttribute element = popAttribute();
        assertEquals(40, getLayoutSize());
        assertEquals(2, element.getCount());
        assertEquals("a_UVs", element.getName());

        element = popAttribute();
        assertEquals(28, getLayoutSize());
        assertEquals(3, element.getCount());
        assertEquals("a_Normal", element.getName());

        element = popAttribute();
        assertEquals(12, getLayoutSize());
        assertEquals(4, element.getCount());
        assertEquals("a_Color", element.getName());

        element = popAttribute();
        assertEquals(0, getLayoutSize());
        assertEquals(3, element.getCount());
        assertEquals("a_Position", element.getName());
    }
    @Test public void layoutVertexTest_1() {
        Vertex v = new VertexBuilder()
                .setPosition(new Vector3(1.2f, 2.0f, 3.2f))
                .setColor(RGBAColor.ACQUA_GREEN)
                .setNormal(new Vector3(-1.2f, -2.0f, -3.2f))
                .setUVs(new Vector2(1.3f, 2.3f))
                .build();

        pushAttribute(new PositionAttribute());
        FloatBuffer buffer = ByteBuffer.allocateDirect(12).order(ByteOrder.nativeOrder())
                .asFloatBuffer();

        layoutVertex(v, buffer);

        assertEquals(1.2f, buffer.get(0));
        assertEquals(2.0f, buffer.get(1));
        assertEquals(3.2f, buffer.get(2));
    }
    @Test public void layoutVertexTest_2() {
        Vertex v = new VertexBuilder()
                .setPosition(new Vector3(1.2f, 2.0f, 3.2f))
                .setColor(RGBAColor.ACQUA_GREEN)
                .setNormal(new Vector3(-1.2f, -2.0f, -3.2f))
                .setUVs(new Vector2(1.3f, 2.3f))
                .build();

        pushAttribute(new ColorAttribute());
        FloatBuffer buffer = ByteBuffer.allocateDirect(16).order(ByteOrder.nativeOrder())
                .asFloatBuffer();

        layoutVertex(v, buffer);

        assertEquals(RGBAColor.ACQUA_GREEN.r(), buffer.get(0));
        assertEquals(RGBAColor.ACQUA_GREEN.g(), buffer.get(1));
        assertEquals(RGBAColor.ACQUA_GREEN.b(), buffer.get(2));
        assertEquals(RGBAColor.ACQUA_GREEN.a(), buffer.get(3));
    }
    @Test public void layoutVertexTest_3() {
        Vertex v = new VertexBuilder()
                .setPosition(new Vector3(1.2f, 2.0f, 3.2f))
                .setColor(RGBAColor.ACQUA_GREEN)
                .setNormal(new Vector3(-1.2f, -2.0f, -3.2f))
                .setUVs(new Vector2(1.3f, 2.3f))
                .build();

        pushAttribute(new NormalAttribute());
        FloatBuffer buffer = ByteBuffer.allocateDirect(12).order(ByteOrder.nativeOrder())
                .asFloatBuffer();

        layoutVertex(v, buffer);

        assertEquals(-1.2f, buffer.get(0));
        assertEquals(-2.0f, buffer.get(1));
        assertEquals(-3.2f, buffer.get(2));
    }
    @Test public void layoutVertexTest_4() {
        Vertex v = new VertexBuilder()
                .setPosition(new Vector3(1.2f, 2.0f, 3.2f))
                .setColor(RGBAColor.ACQUA_GREEN)
                .setNormal(new Vector3(-1.2f, -2.0f, -3.2f))
                .setUVs(new Vector2(1.3f, 2.3f))
                .build();

        pushAttribute(new UVAttribute());
        FloatBuffer buffer = ByteBuffer.allocateDirect(12).order(ByteOrder.nativeOrder())
                .asFloatBuffer();

        layoutVertex(v, buffer);

        assertEquals(1.3f, buffer.get(0));
        assertEquals(2.3f, buffer.get(1));
    }
    @Test public void layoutVertexTest_5() {
        Vertex v = new VertexBuilder()
                .setPosition(new Vector3(1.2f, 2.0f, 3.2f))
                .setColor(RGBAColor.ACQUA_GREEN)
                .setNormal(new Vector3(-1.2f, -2.0f, -3.2f))
                .setUVs(new Vector2(1.3f, 2.3f))
                .build();

        pushAttribute(new CustomAttribute("a_Test", 16, 4, false) {
            @Override
            public void layoutVertex(Vertex v, FloatBuffer buffer) {
                buffer.put(v.position.x()).put(v.color.r()).put(v.normal.x()).put(v.uvs.x());
            }
        });

        FloatBuffer buffer = ByteBuffer.allocateDirect(16).order(ByteOrder.nativeOrder())
                .asFloatBuffer();

        layoutVertex(v, buffer);

        assertEquals(1.2f, buffer.get(0));
        assertEquals(RGBAColor.ACQUA_GREEN.r(), buffer.get(1));
        assertEquals(-1.2f, buffer.get(2));
        assertEquals(1.3f, buffer.get(3));
    }
}