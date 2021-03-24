package com.alura.dyno.engine3d.render;

import com.alura.dyno.engine3d.utils.RGBAColor;
import com.alura.dyno.math.graphics.FloatVector;
import com.alura.dyno.math.graphics.Vector2;
import com.alura.dyno.math.graphics.Vector3;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class VertexTest {
    @Test public void vertexBuilderTest_1() {
        Vertex vertex = new VertexBuilder().build();

        assertEquals(vertex.position, new Vector3(0.0f, 0.0f, 0.0f));
        assertEquals(vertex.color, RGBAColor.MAGENTA);
        assertEquals(vertex.normal, new Vector3(0.0f, 0.0f, 0.0f));
        assertEquals(vertex.uvs, new Vector2(0.0f, 0.0f));
    }
    @Test public void vertexBuilderTest_2() {
        Vertex vertex = new VertexBuilder()
                .setPosition(new Vector3(1.0f, 2.0f, 3.0f)).build();

        assertEquals(vertex.position, new Vector3(1.0f, 2.0f, 3.0f));
        assertEquals(vertex.color, RGBAColor.MAGENTA);
        assertEquals(vertex.normal, new Vector3(0.0f, 0.0f, 0.0f));
        assertEquals(vertex.uvs, new Vector2(0.0f, 0.0f));
    }
    @Test public void vertexBuilderTest_3() {
        Vertex vertex = new VertexBuilder()
                .setColor(new RGBAColor(1.0f, 2.0f, 3.0f, 4.0f)).build();

        assertEquals(vertex.position, new Vector3(0.0f, 0.0f, 0.0f));
        assertEquals(vertex.color, new RGBAColor(1.0f, 2.0f, 3.0f, 4.0f));
        assertEquals(vertex.normal, new Vector3(0.0f, 0.0f, 0.0f));
        assertEquals(vertex.uvs, new Vector2(0.0f, 0.0f));
    }
    @Test public void vertexBuilderTest_4() {
        Vertex vertex = new VertexBuilder()
                .setNormal(new Vector3(1.0f, 2.0f, 3.0f)).build();

        assertEquals(vertex.position, new Vector3(0.0f, 0.0f, 0.0f));
        assertEquals(vertex.color, RGBAColor.MAGENTA);
        assertEquals(vertex.normal, new Vector3(1.0f, 2.0f, 3.0f));
        assertEquals(vertex.uvs, new Vector2(0.0f, 0.0f));
    }
    @Test public void vertexBuilderTest_5() {
        Vertex vertex = new VertexBuilder()
                .setUVs(new Vector2(1.0f, 2.0f)).build();

        assertEquals(vertex.position, new Vector3(0.0f, 0.0f, 0.0f));
        assertEquals(vertex.color, RGBAColor.MAGENTA);
        assertEquals(vertex.normal, new Vector3(0.0f, 0.0f, 0.0f));
        assertEquals(vertex.uvs, new Vector2(1.0f, 2.0f));
    }
    @Test public void vertexBuilderTest_6() {
        Vertex vertex = new VertexBuilder()
                .setPosition(new Vector3(1.0f, 2.0f, 3.0f))
                .setColor(new RGBAColor(4.0f, 5.0f, 6.0f, 7.0f))
                .setNormal(new Vector3(8.0f, 9.0f, 10.0f))
                .setUVs(new Vector2(11.0f, 12.0f)).build();

        assertEquals(vertex.position,new Vector3(1.0f, 2.0f, 3.0f));
        assertEquals(vertex.color, new RGBAColor(4.0f, 5.0f, 6.0f, 7.0f));
        assertEquals(vertex.normal, new Vector3(8.0f, 9.0f, 10.0f));
        assertEquals(vertex.uvs, new Vector2(11.0f, 12.0f));
    }
    @Test public void vertexBYTESTest_7() {
        assertEquals(9 * Float.BYTES, Vertex.BYTES);
    }
}