package com.alura.dyno.engine3d.components;

import android.opengl.Matrix;

import com.alura.dyno.engine3d.system.events.SceneObjectEvent;
import com.alura.dyno.engine3d.system.events.TreeEventDispatcher;
import com.alura.dyno.maths.Vector2;

public class Transform2D extends MonoBehaviour {

    private float x = 0.0f;
    private float y = 0.0f;
    private float z = 0.0f;

    private float sc_x = 1.0f;
    private float sc_y = 1.0f;

    private float angle = 0.0f;
    private float angleInRadians = 0.0f;

    private boolean isUpdated = false;
    private float[] modelMatrix = new float[16];

    public Transform2D(Transform2DBuilder builder) {
        super(builder);

        this.x = builder.x;
        this.y = builder.y;
        this.z = builder.z;

        this.sc_x = builder.sc_x;
        this.sc_y = builder.sc_y;

        this.angle = builder.angle;
        this.angleInRadians = (float) Math.toRadians(angle);
    }
    public Transform2D(String name) {
        super(name);
    }
    public Transform2D(Transform2D origin) {
        super(origin.name);
        this.copyValues(origin);
    }
    public void copyValues(Transform2D originTransform) {
        this.x = originTransform.x;
        this.y = originTransform.y;
        this.z = originTransform.z;

        this.sc_x = originTransform.sc_x;
        this.sc_y = originTransform.sc_y;

        this.angle = originTransform.angle;
        this.angleInRadians = originTransform.angleInRadians;
    }

    public static Transform2D multiply(Transform2D t_lhs, Transform2D t_rhs) {
        float newX = (float) (t_rhs.x * t_lhs.sc_x * Math.cos(t_lhs.angleInRadians) -
                t_rhs.y * t_lhs.sc_y * Math.sin(t_lhs.angleInRadians)) + t_lhs.x;
        float newY = (float) (t_rhs.x * t_lhs.sc_x * Math.sin(t_lhs.angleInRadians) +
                t_rhs.y * t_lhs.sc_y * Math.cos(t_lhs.angleInRadians)) + t_lhs.y;

        return new Transform2DBuilder("").setAngle(t_lhs.angle + t_rhs.angle).
                setScale(t_lhs.sc_x * t_rhs.sc_x, t_lhs.sc_y * t_rhs.sc_y).
                setPosition(newX, newY).build();
    }
    public Transform2D move(float dx, float dy) {
        this.x += dx;
        this.y += dy;

        notifyChanged();
        return this;
    }
    public Transform2D rotate(float dAngle) {
        this.angle += dAngle;
        this.angleInRadians = (float) Math.toRadians(angle);

        notifyChanged();
        return this;
    }

    public Vector2 fromViewToModelSpace(Vector2 viewCoords) {
        float translatedX = viewCoords.getX() - x;
        float translatedY = viewCoords.getY() - y;

        float rotatedX = (float) (translatedX * Math.cos(angleInRadians) + translatedY * Math.sin(angleInRadians));
        float rotatedY = (float) (-translatedX * Math.sin(angleInRadians) + translatedY * Math.cos(angleInRadians));

        float scaledX = rotatedX / sc_x;
        float scaledY = rotatedY / sc_y;

        return new Vector2(scaledX, scaledY);
    }
    public Vector2 fromModelToViewSpace(Vector2 modelCoords) {
        float scaledX = modelCoords.getX() * sc_x;
        float scaledY = modelCoords.getY() * sc_y;

        float rotatedX = (float) (scaledX * Math.cos(angleInRadians) - scaledY * Math.sin(angleInRadians));
        float rotatedY = (float) (scaledX * Math.sin(angleInRadians) + scaledY * Math.cos(angleInRadians));

        float translatedX = rotatedX + x;
        float translatedY = rotatedY + y;

        return new Vector2(translatedX, translatedY);
    }

    public Transform2D setScale(float sc_x, float sc_y) {
        this.sc_x = sc_x;
        this.sc_y = sc_y;

        notifyChanged();
        return this;
    }
    public Transform2D setPosition(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;

        notifyChanged();
        return this;
    }
    public Transform2D setPosition(float x, float y) {
        this.x = x;
        this.y = y;

        notifyChanged();
        return this;
    }
    public Transform2D setAngle(float angle) {
        this.angle = angle;
        this.angleInRadians = (float) Math.toRadians(angle);

        notifyChanged();
        return this;
    }

    public float[] getModelmatrix() {
        if (!isUpdated) {
            updateModelMatrix();
        }

        return modelMatrix;
    }
    public float[] getInvModelMatrix() {
        float[] invModel = getModelmatrix();
        Matrix.invertM(invModel, 0, invModel, 0);

        return invModel;
    }
    public Vector2 getPosition() {
        return new Vector2(x, y);
    }
    public float getX() {
        return x;
    }
    public float getY() {
        return y;
    }
    public Vector2 getScale() {
        return new Vector2(sc_x, sc_y);
    }
    public float getSc_x() {
        return sc_x;
    }
    public float getSc_y() {
        return sc_y;
    }
    public float getAngle() {
        return angle;
    }

    public void updateModelMatrix() {
        Matrix.setIdentityM(modelMatrix, 0);

        Matrix.translateM(modelMatrix, 0, x, y, z);
        Matrix.rotateM(modelMatrix, 0, angle, 0, 0, 1.0f);
        Matrix.scaleM(modelMatrix, 0, sc_x, sc_y, 1.0f);

    }
    private void notifyChanged() {
        if (getParent() != null) {
            TreeEventDispatcher.dispatchEvent(new SceneObjectEvent.OnParentTransformChangedEvent(), getParent());
        }
    }

    public static class Transform2DBuilder<T extends Transform2DBuilder<T>>
            extends MonoBehaviourBuilder<T> {
        private float x = 0.0f;
        private float y = 0.0f;
        private float z = 0.0f;
        private float sc_x = 1.0f;
        private float sc_y = 1.0f;
        private float angle = 0.0f;

        protected Transform2DBuilder(String name) {
            super(name);
        }

        public static Transform2DBuilder<?> builder(String name) {
            return new Transform2DBuilder<>(name);
        }

        public Transform2D build() {
            return new Transform2D(this);
        }

        public T setPosition(float x, float y) {
            this.x = x;
            this.y = y;

            return (T) this;
        }

        public T setPosition(float x, float y, float z) {
            this.x = x;
            this.y = y;
            this.z = z;

            return (T) this;
        }

        public T setDepth(float z) {
            this.z = z;

            return (T) this;
        }

        public T setScale(float sc_x, float sc_y) {
            this.sc_x = sc_x;
            this.sc_y = sc_y;

            return (T) this;
        }

        public T setAngle(float a) {
            this.angle = a;

            return (T) this;
        }

    }
}
