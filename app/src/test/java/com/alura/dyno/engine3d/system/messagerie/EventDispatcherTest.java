package com.alura.dyno.engine3d.system.messagerie;

import com.alura.dyno.engine3d.system.messagerie.events.CreateEvent;
import com.alura.dyno.engine3d.system.messagerie.events.CreateEvent.OnCreateListener;
import com.alura.dyno.engine3d.system.messagerie.events.DestroyEvent;
import com.alura.dyno.engine3d.system.messagerie.events.DestroyEvent.OnDestroyListener;
import com.alura.dyno.engine3d.system.messagerie.events.DragEvent;
import com.alura.dyno.engine3d.system.messagerie.events.DragEvent.OnDragListener;
import com.alura.dyno.engine3d.system.messagerie.events.NewParentSetEvent;
import com.alura.dyno.engine3d.system.messagerie.events.NewParentSetEvent.OnNewParentSetListener;
import com.alura.dyno.engine3d.system.messagerie.events.ParentTransformChangedEvent;
import com.alura.dyno.engine3d.system.messagerie.events.ParentTransformChangedEvent.OnParentTransformChangedListener;
import com.alura.dyno.engine3d.system.messagerie.events.RenderEvent;
import com.alura.dyno.engine3d.system.messagerie.events.RenderEvent.OnRenderListener;
import com.alura.dyno.engine3d.system.messagerie.events.ScaleEvent;
import com.alura.dyno.engine3d.system.messagerie.events.ScaleEvent.OnScaleListener;
import com.alura.dyno.engine3d.system.messagerie.events.ScreenSizeChangedEvent;
import com.alura.dyno.engine3d.system.messagerie.events.ScreenSizeChangedEvent.OnScreenSizeChangedListener;
import com.alura.dyno.engine3d.system.messagerie.events.TapEvent;
import com.alura.dyno.engine3d.system.messagerie.events.TapEvent.OnTapListener;
import com.alura.dyno.engine3d.system.messagerie.events.UpdateEvent;
import com.alura.dyno.engine3d.system.messagerie.events.UpdateEvent.OnUpdateListener;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class EventDispatcherTest extends EventDispatcher {
    private OnCreateListener onCreateListener;
    private OnUpdateListener onUpdateListener;
    private OnRenderListener onRenderListener;
    private OnDestroyListener onDestroyListener;
    private OnTapListener onTapListener;
    private OnDragListener onDragListener;
    private OnScaleListener onScaleListener;
    private OnScreenSizeChangedListener onScreenSizeChangedListener;
    private OnNewParentSetListener onNewParentSetListener;
    private OnParentTransformChangedListener onParentTransformChangedListener;

    @Test void testSubscribeAllTypesOfListeners() {
        setupOneOfEach();

        testSubscribeListener(onCreateListener);
        testSubscribeListener(onUpdateListener);
        testSubscribeListener(onRenderListener);
        testSubscribeListener(onDestroyListener);
        testSubscribeListener(onTapListener);
        testSubscribeListener(onDragListener);
        testSubscribeListener(onScaleListener);
        testSubscribeListener(onScreenSizeChangedListener);
        testSubscribeListener(onNewParentSetListener);
        testSubscribeListener(onParentTransformChangedListener);

    }
    private void testSubscribeListener(IEventListener listener) {
        IEventListener actual = subs.get(listener.getType()).get(0);
        assertEquals(listener, actual);
    }

    @Test void testDispatchEvent() {
        setupOneOfEach();

        enqueueEvent(new CreateEvent());
        enqueueEvent(new UpdateEvent());
        enqueueEvent(new DestroyEvent());
        enqueueEvent(new TapEvent(null));
        enqueueEvent(new DragEvent(null, null, null, null));
        enqueueEvent(new ScaleEvent());
        enqueueEvent(new CreateEvent());
        enqueueEvent(new ScreenSizeChangedEvent(0, 0));
        enqueueEvent(new NewParentSetEvent());
        enqueueEvent(new ParentTransformChangedEvent());
    }

    private void setupOneOfEach() {
        onCreateListener = new OnCreateListener() {
            @Override public void onCreate(CreateEvent event) { notifyListenerTriggered(getType(), event.getType()); }
        };
        subscribe(onCreateListener);

        onUpdateListener = new OnUpdateListener() {
            @Override public void onUpdate(UpdateEvent event) { notifyListenerTriggered(getType(), event.getType()); }
        };
        subscribe(onUpdateListener);

        onRenderListener = new OnRenderListener() {
            @Override public void onRender(RenderEvent event) {notifyListenerTriggered(getType(), event.getType()); }
        };
        subscribe(onRenderListener);

        onDestroyListener = new OnDestroyListener() {
            @Override public void onDestroy(DestroyEvent event) {notifyListenerTriggered(getType(), event.getType()); }
        };
        subscribe(onDestroyListener);

        onTapListener = new OnTapListener() {
            @Override public void onTap(TapEvent event) {notifyListenerTriggered(getType(), event.getType()); }
        };
        subscribe(onTapListener);

        onDragListener = new OnDragListener() {
            @Override public void onDrag(DragEvent event) {notifyListenerTriggered(getType(), event.getType()); }
        };
        subscribe(onDragListener);

        onScaleListener = new OnScaleListener() {
            @Override public void onScale(ScaleEvent event) {notifyListenerTriggered(getType(), event.getType()); }
        };
        subscribe(onScaleListener);

        onScreenSizeChangedListener = new OnScreenSizeChangedListener() {
            @Override public void onScreenSizeChanged(ScreenSizeChangedEvent event) {notifyListenerTriggered(getType(), event.getType()); }
        };
        subscribe(onScreenSizeChangedListener);

        onNewParentSetListener = new OnNewParentSetListener() {
            @Override public void onNewParentSet(NewParentSetEvent event) {notifyListenerTriggered(getType(), event.getType()); }
        };
        subscribe(onNewParentSetListener);

        onParentTransformChangedListener = new OnParentTransformChangedListener() {
            @Override public void onParentTransformChanged(ParentTransformChangedEvent event) {notifyListenerTriggered(getType(), event.getType()); }
        };
        subscribe(onParentTransformChangedListener);
    }
    private void notifyListenerTriggered(EventType listenerType, EventType eventType) {
        System.out.println("IEventListener @" + listenerType + " has been triggered by EngineEvent @" + eventType+";");
    }
}