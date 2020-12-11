package com.alura.dyno.engine3d.system.events;

import com.alura.dyno.engine3d.components.Camera;
import com.alura.dyno.engine3d.objects.SceneObject;
import com.alura.dyno.engine3d.system.SceneMaster;
import com.alura.dyno.maths.Vector2G;

public abstract class ComponentEvent extends EngineEvent {

    public interface IOnCreateEventListener {
        void onCreate(OnCreateEvent event);
    }

    public interface IOnUpdateEventListener {
        void onUpdate(OnUpdateEvent event);
    }

    public interface IOnDestroyEventListener {
        void onDestroy();
    }

    public interface IOnRenderEventListener {
        void onRender();
    }

    public interface IOnScreenSizeChangedEventListener {
        void onScreenSizeChanged(OnScreenSizeChangedEvent event);
    }

    public interface IOnParentChangedEventListener {
        void onParentChanged(SceneObject newParent);
    }

    public interface IOnTapEventListener {
        void onTap(OnTapEvent event);
    }

    public interface IOnDragEventListener {
        void onDrag(OnDragEvent event);
    }

    public interface IOnScaleEventListener {
        void onScale(OnScaleEvent event);
    }

    public static class OnCreateEvent extends ComponentEvent {

        @Override
        EngineEventType getType() {
            return EngineEventType.OnCreateEvent;
        }

    }
    public static class OnUpdateEvent extends ComponentEvent {

        @Override
        EngineEventType getType() {
            return EngineEventType.OnUpdateEvent;
        }
    }
    public static class OnDestroyEvent extends ComponentEvent {

        @Override
        EngineEventType getType() {
            return EngineEventType.OnDestroyEvent;
        }
    }
    public static class OnRenderEvent extends ComponentEvent {
        @Override
        EngineEventType getType() {
            return EngineEventType.OnRenderEvent;
        }
    }
    public static class OnScreenSizeChangedEvent extends ComponentEvent {
        int width, height;

        public OnScreenSizeChangedEvent(int width, int height) {
            this.width = width;
            this.height = height;
        }

        public int getWidth() {
            return width;
        }
        public int getHeight() {
            return height;
        }

        @Override
        EngineEventType getType() {
            return EngineEventType.OnScreenSizeChangedEvent;
        }
    }

    public static class OnParentChangedEvent extends ComponentEvent {

        @Override
        EngineEventType getType() {
            return null;
        }
    }

    public static class OnTapEvent extends ComponentEvent {
        Vector2G screenCoords;
        Vector2G modelCoords;
        Vector2G alignedCoords;

        public OnTapEvent(Vector2G screenCoords) {
            this.screenCoords = screenCoords;
            this.modelCoords = Camera.fromScreenToView(SceneMaster.getMainCamera(), screenCoords);
        }

        public Vector2G getScreenCoords() {
            return screenCoords;
        }

        public Vector2G getViewCoords() {
            return modelCoords;
        }

        public Vector2G getAlignedCoords() {
            return alignedCoords;
        }

        @Override
        EngineEventType getType() {
            return EngineEventType.OnTapEvent;
        }
    }

    public static class OnDragEvent extends ComponentEvent {
        Vector2G screenDistance;
        Vector2G viewDistance;
        Vector2G screenCurrentTouch;
        Vector2G viewCurrentTouch;

        public OnDragEvent(Vector2G screenDistance, Vector2G viewDistance, Vector2G screenCurrentTouch,
                           Vector2G viewCurrentTouch) {
            this.screenDistance = screenDistance;
            this.viewDistance = viewDistance;
            this.screenCurrentTouch = screenCurrentTouch;
            this.viewCurrentTouch = viewCurrentTouch;
        }

        public Vector2G getScreenDistance() {
            return screenDistance;
        }

        public Vector2G getViewDistance() {
            return viewDistance;
        }

        public Vector2G getScreenCurrentTouch() {
            return screenCurrentTouch;
        }

        public Vector2G getViewCurrentTouch() {
            return viewCurrentTouch;
        }

        @Override
        EngineEventType getType() {
            return EngineEventType.OnDragEvent;
        }
    }

    public static class OnScaleEvent extends ComponentEvent {
        private float scaleFactor;
        private Vector2G focusPoint;

        public OnScaleEvent(float scaleFactor, Vector2G focusPoint) {
            this.scaleFactor = scaleFactor;
            this.focusPoint = focusPoint;
        }

        public float getScaleFactor() {
            return scaleFactor;
        }

        public Vector2G getFocusPoint() {
            return focusPoint;
        }

        @Override
        EngineEventType getType() {
            return EngineEventType.OnScaleEvent;
        }
    }
}
