package com.alura.dyno.engine3d.system.events;

import com.alura.dyno.engine3d.objects.SceneObject;
import com.alura.dyno.engine3d.system.SceneMaster;
import com.alura.dyno.maths.Vector2;

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
        Vector2 screenCoords;
        Vector2 modelCoords;
        Vector2 alignedCoords;

        public OnTapEvent(Vector2 screenCoords) {
            this.screenCoords = screenCoords;
            this.modelCoords = SceneMaster.getMainCamera().fromScreenToView(screenCoords);
        }

        public Vector2 getScreenCoords() {
            return screenCoords;
        }

        public Vector2 getViewCoords() {
            return modelCoords;
        }

        public Vector2 getAlignedCoords() {
            return alignedCoords;
        }

        @Override
        EngineEventType getType() {
            return EngineEventType.OnTapEvent;
        }
    }

    public static class OnDragEvent extends ComponentEvent {
        Vector2 screenDistance;
        Vector2 viewDistance;
        Vector2 screenCurrentTouch;
        Vector2 viewCurrentTouch;

        public OnDragEvent(Vector2 screenDistance, Vector2 viewDistance, Vector2 screenCurrentTouch,
                           Vector2 viewCurrentTouch) {
            this.screenDistance = screenDistance;
            this.viewDistance = viewDistance;
            this.screenCurrentTouch = screenCurrentTouch;
            this.viewCurrentTouch = viewCurrentTouch;
        }

        public Vector2 getScreenDistance() {
            return screenDistance;
        }

        public Vector2 getViewDistance() {
            return viewDistance;
        }

        public Vector2 getScreenCurrentTouch() {
            return screenCurrentTouch;
        }

        public Vector2 getViewCurrentTouch() {
            return viewCurrentTouch;
        }

        @Override
        EngineEventType getType() {
            return EngineEventType.OnDragEvent;
        }
    }

    public static class OnScaleEvent extends ComponentEvent {
        private float scaleFactor;
        private Vector2 focusPoint;

        public OnScaleEvent(float scaleFactor, Vector2 focusPoint) {
            this.scaleFactor = scaleFactor;
            this.focusPoint = focusPoint;
        }

        public float getScaleFactor() {
            return scaleFactor;
        }

        public Vector2 getFocusPoint() {
            return focusPoint;
        }

        @Override
        EngineEventType getType() {
            return EngineEventType.OnScaleEvent;
        }
    }
}
