package com.alura.dyno.engine3d.components;

import com.alura.dyno.engine3d.system.events.ComponentEvent;
import com.alura.dyno.maths.Vector2;

public class CircularMovement extends MonoBehaviour {

    Vector2 origin;
    float radius;
    float phase;

    protected CircularMovement(CircularMovementBuilder builder) {
        super(builder);

        origin = builder.origin;
        radius = builder.radius;
    }

    @Override
    public void onUpdate(ComponentEvent.OnUpdateEvent event) {
        super.onUpdate(event);

        getParent().getLocalTransform().setPosition(origin.getX() + radius * (float) Math.cos(phase),
                origin.getY() + radius * (float) Math.sin(phase));
        phase += 5f;
    }

    public static class CircularMovementBuilder<T extends CircularMovementBuilder<T>>
            extends MonoBehaviourBuilder<T> {
        Vector2 origin;
        float radius;


        protected CircularMovementBuilder(String name) {
            super(name);
        }

        public static CircularMovementBuilder<?> builder(String name) {
            return new CircularMovementBuilder<>(name);
        }

        public CircularMovement build() {
            return new CircularMovement(this);
        }

        public T setOrigin(Vector2 origin) {
            this.origin = origin;

            return (T) this;
        }

        public T setRadius(float radius) {
            this.radius = radius;

            return (T) this;
        }
    }
}
