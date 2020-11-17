package com.alura.dyno.ui;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.util.AttributeSet;
import android.view.MotionEvent;

import com.alura.dyno.R;
import com.alura.dyno.engine3d.components.Camera;
import com.alura.dyno.engine3d.components.GridRenderer;
import com.alura.dyno.engine3d.components.MeshRenderer;
import com.alura.dyno.engine3d.components.TextRenderer;
import com.alura.dyno.engine3d.objects.EmptyObject;
import com.alura.dyno.engine3d.objects.SceneObject;
import com.alura.dyno.engine3d.system.SceneMaster;
import com.alura.dyno.engine3d.system.events.ComponentEvent;
import com.alura.dyno.engine3d.system.events.ComponentEvent.OnScreenSizeChangedEvent;
import com.alura.dyno.engine3d.system.events.TreeEventDispatcher;
import com.alura.dyno.engine3d.system.fonts.Font;
import com.alura.dyno.engine3d.system.fonts.FontLoader;
import com.alura.dyno.engine3d.system.shaders.Shader;
import com.alura.dyno.engine3d.system.shaders.ShaderMaster;
import com.alura.dyno.engine3d.system.vertex.MeshBuffer;
import com.alura.dyno.engine3d.utils.RGBAColor;
import com.alura.dyno.engine3d.utils.TriangleFactory;

public class StructureDrawGLSurface extends GLSurfaceView {
    StructureDrawRenderer renderer;
    StructureDrawSurfaceListener listener;

    SceneObject root;
    Camera cam;
    Font font;

    public StructureDrawGLSurface(Context context) {
        super(context);
    }

    public StructureDrawGLSurface(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void setListener(StructureDrawSurfaceListener listener) {
        this.listener = listener;
    }

    private void loadCamera() {
        cam = Camera.CameraBuilder.builder("Main camera")
                .setScreenSize(getWidth(), getHeight())
                .setZoomConstraints(0.1f, 100000)
                .setZoom(100)
                .build();

        EmptyObject cameraHandle = new EmptyObject.EmptyObjectBuilder<>("Camera handle")
                .setPosition(0.0f, 0.0f, 1.0f)
                .build();

        cameraHandle.setParent(root);
        cameraHandle.addComponent(cam);
        SceneMaster.setMainCamera(cam);
    }

    public void setRenderer(Renderer renderer) {
        super.setRenderer(renderer);
        this.renderer = (StructureDrawRenderer) renderer;
        this.renderer.setOnRenderListener(new SimpleSurfaceRendererListener());
    }

    private void loadObjects() {
        root = EmptyObject.EmptyObjectBuilder.builder("Root")
                .setPosition(0.0f, 0.0f, -0.5f)
                .build();
        MeshBuffer quad = new MeshBuffer();
        quad.addVertex(new TriangleFactory.QuadBuilder(-1.0f, 1.0f, -1.0f, 1.0f)
                .setColorBounds(RGBAColor.ACQUA_GREEN,
                                RGBAColor.BLUE,
                                RGBAColor.MAGENTA,
                                RGBAColor.MIDNIGHT_BLUE)
                .setDepth(0.0f)
                .asVertex());
        MeshRenderer<Shader> renderer = MeshRenderer.MeshRendererBuilder.builder("Test", ShaderMaster.objectShader)
                .setData(quad)
                .build();

        root.addComponent(renderer);
    }

    private void loadShaders() {
        ShaderMaster.loadShaders(getContext());
    }

    private void loadFonts() {
        font = new FontLoader(getContext())
                .load(R.drawable.font_atlas_handwritten, R.raw.font_map_handwritten);
    }

    //2.3 Touch events
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (listener != null) {
            return listener.onTouch(event);
        } else {
            return false;
        }
    }

    public interface StructureDrawSurfaceListener {
        boolean onTouch(MotionEvent e);
    }

    //3. Classes
    public class SimpleSurfaceRendererListener implements StructureDrawRenderer.SurfaceRendererListener {
        @Override
        public void OnSurfaceCreated(StructureDrawRenderer renderer) {
            loadShaders();
            loadFonts();
            loadObjects();
            loadCamera();

            TreeEventDispatcher.dispatcher.setRoot(root);
            TreeEventDispatcher.dispatcher.dispatchEvent(new ComponentEvent.OnCreateEvent());
        }

        @Override
        public void OnSurfaceChanged(int width, int height) {
            OnScreenSizeChangedEvent event = new OnScreenSizeChangedEvent(width, height);
            TreeEventDispatcher.dispatcher.dispatchEvent(event);
        }

        @Override
        public void OnRender(StructureDrawRenderer renderer) {
            TreeEventDispatcher.dispatcher.dispatchEvent(new ComponentEvent.OnUpdateEvent());
            TreeEventDispatcher.dispatcher.dispatchEvent(new ComponentEvent.OnRenderEvent());
        }
    }
}







