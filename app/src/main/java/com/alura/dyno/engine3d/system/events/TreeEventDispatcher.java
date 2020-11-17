package com.alura.dyno.engine3d.system.events;

import com.alura.dyno.engine3d.components.MonoBehaviour;
import com.alura.dyno.engine3d.objects.SceneObject;

public class TreeEventDispatcher {

    public final static TreeEventDispatcher dispatcher = new TreeEventDispatcher();
    SceneObject root;

    public TreeEventDispatcher() {
    }

    public static void dispatchEvent(SceneObjectEvent event, SceneObject parent) {
        filterAndDispatchToCurrentNode(event, parent);
        dispatchForChildren(event, parent);
    }

    private static void filterAndDispatchToCurrentNode(SceneObjectEvent event, SceneObject head) {
        switch (event.getType()) {
            case OnNewParentSetEvent:
                executeOnTreeNode((SceneObjectEvent.OnNewParentSetEvent) event, head);
                break;
            case OnParentTransformChangedEvent:
                executeOnTreeNode((SceneObjectEvent.OnParentTransformChangedEvent) event, head);
                break;
            default:
                throw new RuntimeException();
        }
    }

    private static void dispatchForChildren(SceneObjectEvent event, SceneObject parent) {
        for (SceneObject child : parent.getChildrenList()) {
            dispatchEvent(event, child);
        }
    }

    private static void executeOnTreeNode(SceneObjectEvent.OnNewParentSetEvent event, SceneObject treeNode) {
        treeNode.onNewParentSet();
    }

    private static void executeOnTreeNode(SceneObjectEvent.OnParentTransformChangedEvent event, SceneObject treeNode) {
        treeNode.onParentTransformChanged();
    }

    public void setRoot(SceneObject root) {
        this.root = root;
    }

    public void dispatchEvent(ComponentEvent event) {
        dispatchEvent(event, root);
    }

    public void dispatchEvent(ComponentEvent event, SceneObject head) {
        filterAndDispatchToComponents(event, head);
        dispatchForChildren(event, head);
    }

    private void filterAndDispatchToComponents(ComponentEvent event, SceneObject head) {
        switch (event.getType()) {
            case OnCreateEvent:
                executeOnComponents((ComponentEvent.OnCreateEvent) event, head);
                break;
            case OnUpdateEvent:
                executeOnComponents((ComponentEvent.OnUpdateEvent) event, head);
                break;
            case OnDestroyEvent:
                executeOnComponents((ComponentEvent.OnDestroyEvent) event, head);
                break;
            case OnRenderEvent:
                executeOnComponents((ComponentEvent.OnRenderEvent) event, head);
                break;
            case OnTapEvent:
                executeOnComponents((ComponentEvent.OnTapEvent) event, head);
                break;
            case OnDragEvent:
                executeOnComponents((ComponentEvent.OnDragEvent) event, head);
                break;
            case OnScaleEvent:
                executeOnComponents((ComponentEvent.OnScaleEvent) event, head);
                break;
            case OnScreenSizeChangedEvent:
                executeOnComponents((ComponentEvent.OnScreenSizeChangedEvent) event, head);
                break;
            default:
                throw new RuntimeException();
        }
    }

    private void dispatchForChildren(ComponentEvent event, SceneObject head) {
        for (SceneObject child : head.getChildrenList()) {
            dispatchEvent(event, child);
        }
    }

    private void executeOnComponents(ComponentEvent.OnCreateEvent event, SceneObject head) {
        for (MonoBehaviour mb : head.getComponentList().values()) {
            mb.onCreate(event);
        }
    }

    private void executeOnComponents(ComponentEvent.OnUpdateEvent event, SceneObject head) {
        for (MonoBehaviour mb : head.getComponentList().values()) {
            mb.onUpdate(event);
        }
    }

    private void executeOnComponents(ComponentEvent.OnDestroyEvent event, SceneObject head) {
        for (MonoBehaviour mb : head.getComponentList().values()) {
            mb.onDestroy();
        }
    }

    private void executeOnComponents(ComponentEvent.OnRenderEvent event, SceneObject head) {
        for (MonoBehaviour mb : head.getComponentList().values()) {
            if (ComponentEvent.IOnRenderEventListener.class.isAssignableFrom(mb.getClass())) {
                ((ComponentEvent.IOnRenderEventListener) mb).onRender();
            }
        }
    }

    private void executeOnComponents(ComponentEvent.OnTapEvent event, SceneObject head) {
        for (MonoBehaviour mb : head.getComponentList().values()) {
            if (ComponentEvent.IOnTapEventListener.class.isAssignableFrom(mb.getClass())) {
                ((ComponentEvent.IOnTapEventListener) mb).onTap(event);
            }
        }
    }

    private void executeOnComponents(ComponentEvent.OnDragEvent event, SceneObject head) {
        for (MonoBehaviour mb : head.getComponentList().values()) {
            if (ComponentEvent.IOnDragEventListener.class.isAssignableFrom(mb.getClass())) {
                ((ComponentEvent.IOnDragEventListener) mb).onDrag(event);
            }
        }
    }

    private void executeOnComponents(ComponentEvent.OnScaleEvent event, SceneObject head) {
        for (MonoBehaviour mb : head.getComponentList().values()) {
            if (ComponentEvent.IOnScaleEventListener.class.isAssignableFrom(mb.getClass())) {
                ((ComponentEvent.IOnScaleEventListener) mb).onScale(event);
            }
        }
    }

    private void executeOnComponents(ComponentEvent.OnScreenSizeChangedEvent event, SceneObject head) {
        for (MonoBehaviour mb : head.getComponentList().values()) {
            if (ComponentEvent.IOnScreenSizeChangedEventListener.class.isAssignableFrom(mb.getClass())) {
                ((ComponentEvent.IOnScreenSizeChangedEventListener) mb).onScreenSizeChanged(event);
            }
        }
    }

    public void dispatchEvent(SceneObjectEvent event) {
        dispatchEvent(event, root);
    }

}
