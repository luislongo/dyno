package com.alura.dyno.engine3d.objects;

public class EmptyObject extends SceneObject {

    public EmptyObject(SceneObjectBuilder builder) {
        super(builder);
    }

    public static class EmptyObjectBuilder<T extends EmptyObjectBuilder<T>>
            extends SceneObjectBuilder<T> {

        public EmptyObjectBuilder(String name) {
            super(name);
        }

        public static EmptyObjectBuilder<?> builder(String name) {
            return new EmptyObjectBuilder<>(name);
        }

        public EmptyObject build() {
            return new EmptyObject(this);
        }
    }
}
