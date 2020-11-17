package com.alura.dyno.engine3d.utils;

import com.alura.dyno.engine3d.system.vertex.Vertex;

public class TriangleFactory {

    public static class QuadBuilder {
        float left, right, bottom, top, depth;
        float uvLeft, uvRight, uvBottom, uvTop;
        RGBAColor colorLeftBottom = RGBAColor.MAGENTA;
        RGBAColor colorRightBottom = RGBAColor.MAGENTA;
        RGBAColor colorLeftTop = RGBAColor.MAGENTA;
        RGBAColor colorRightTop = RGBAColor.MAGENTA;

        public QuadBuilder(float left, float right, float bottom, float top) {
            setBounds(left, right, bottom, top);
        }

        private QuadBuilder setBounds(float left, float right, float bottom, float top) {
            this.left = left;
            this.right = right;
            this.bottom = bottom;
            this.top = top;

            return this;
        }

        public QuadBuilder setUVBounds(float uvLeft, float uvRight, float uvBottom, float uvTop) {
            this.uvLeft = uvLeft;
            this.uvRight = uvRight;
            this.uvBottom = uvBottom;
            this.uvTop = uvTop;

            return this;
        }

        public QuadBuilder setColorBounds(RGBAColor leftBottom, RGBAColor rightBottom, RGBAColor leftTop, RGBAColor rightTop) {
            this.colorLeftBottom = leftBottom;
            this.colorRightBottom = rightBottom;
            this.colorLeftTop = leftTop;
            this.colorRightTop = rightTop;

            return this;
        }

        public QuadBuilder setDepth(float depth) {
            this.depth = depth;

            return this;
        }

        public Vertex[] asVertex() {
            Vertex v1 = new Vertex.VertexBuilder(left, bottom, depth)
                    .setColor(colorLeftBottom)
                    .setUVs(uvLeft, uvBottom)
                    .build();
            Vertex v2 = new Vertex.VertexBuilder(right, top, depth)
                    .setColor(colorRightTop)
                    .setUVs(uvRight, uvTop)
                    .build();
            Vertex v3 = new Vertex.VertexBuilder(right, bottom, depth)
                    .setColor(colorRightBottom)
                    .setUVs(uvRight, uvBottom)
                    .build();
            Vertex v4 = new Vertex.VertexBuilder(left, top, depth)
                    .setColor(colorLeftTop)
                    .setUVs(uvLeft, uvTop)
                    .build();

            return new Vertex[]{v1, v2, v3,
                    v1, v2, v4};
        }
    }
}
