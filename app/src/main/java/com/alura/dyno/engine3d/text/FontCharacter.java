package com.alura.dyno.engine3d.text;

public class FontCharacter {
    public int id, x, y, left, right, bottom, top, xadvance;
    public float uvLeft, uvRight, uvBottom, uvTop;

    public FontCharacter(FontCharacterBuilder builder) {
        this.id = builder.id;
        this.x = builder.x;
        this.y = builder.y;
        this.left = builder.left;
        this.right = builder.right;
        this.bottom = builder.bottom;
        this.top = builder.top;
        this.xadvance = builder.xadvance;
        this.uvBottom = builder.uvBottom;
        this.uvTop = builder.uvTop;
        this.uvLeft = builder.uvLeft;
        this.uvRight = builder.uvRight;
    }

    public static class FontCharacterBuilder {
        private int id, x, y, width, height, xoffset, yoffset, xadvance;
        private int left, right, bottom, top;
        private float uvLeft, uvRight, uvBottom, uvTop;

        public FontCharacterBuilder() {
        }

        public FontCharacter build(int imageSize, int lineHeight, int base) {
            calculateQuadBounds(lineHeight, base);
            calculateUVQuadBounds(imageSize);

            return new FontCharacter(this);
        }

        private void calculateUVQuadBounds(int imageSize) {
            this.uvLeft = (float) (x) / imageSize;
            this.uvRight = (float) (x + width) / imageSize;
            this.uvTop = (float) (y) / imageSize;
            this.uvBottom = (float) (y + height) / imageSize;
        }

        private void calculateQuadBounds(int lineHeight, int base) {
            this.left = xoffset;
            this.right = xoffset + width;
            this.top = base - yoffset;
            this.bottom = base - yoffset - height;
        }

        public FontCharacterBuilder setId(int id) {
            this.id = id;
            return this;
        }

        public FontCharacterBuilder setX(int x) {
            this.x = x;

            return this;
        }

        public FontCharacterBuilder setY(int y) {
            this.y = y;

            return this;
        }

        public FontCharacterBuilder setWidth(int width) {
            this.width = width;
            return this;
        }

        public FontCharacterBuilder setHeight(int height) {
            this.height = height;
            return this;
        }

        public FontCharacterBuilder setXoffset(int xoffset) {
            this.xoffset = xoffset;
            return this;
        }

        public FontCharacterBuilder setYoffset(int yoffset) {
            this.yoffset = yoffset;
            return this;
        }

        public FontCharacterBuilder setXadvance(int xadvance) {
            this.xadvance = xadvance;
            return this;
        }
    }
}
