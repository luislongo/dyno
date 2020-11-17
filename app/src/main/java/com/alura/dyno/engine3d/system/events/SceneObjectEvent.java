package com.alura.dyno.engine3d.system.events;

public abstract class SceneObjectEvent extends EngineEvent {
    public interface IOnParentChangedListener {
        void onNewParentSet();
    }

    public interface IOnParentTransformChangedListener {
        void onParentTransformChanged();
    }

    public static class OnNewParentSetEvent extends SceneObjectEvent {
        @Override
        EngineEventType getType() {
            return EngineEventType.OnNewParentSetEvent;
        }
    }

    public static class OnParentTransformChangedEvent extends SceneObjectEvent {
        @Override
        EngineEventType getType() {
            return EngineEventType.OnParentTransformChangedEvent;
        }
    }
}
