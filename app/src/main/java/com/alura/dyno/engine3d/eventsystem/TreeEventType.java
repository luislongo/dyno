package com.alura.dyno.engine3d.eventsystem;

public enum TreeEventType {
    OnCreate,
    OnUpdate,
    OnRender,
    OnDestroy,
    OnScreenSizeChanged,

    OnParentTransformChanged,
    OnChildAdded,
    OnChildRemoved,
    OnParentChanged,

    OnDrag,
    OnScale,
    OnTap;
}
