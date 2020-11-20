package com.alura.dyno.ui;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.util.AttributeSet;
import android.view.MotionEvent;

import com.alura.dyno.R;
import com.alura.dyno.engine3d.components.AdaptableGridRenderer;
import com.alura.dyno.engine3d.components.Camera;
import com.alura.dyno.engine3d.components.TextRenderer;
import com.alura.dyno.engine3d.objects.EmptyObject;
import com.alura.dyno.engine3d.objects.SceneObject;
import com.alura.dyno.engine3d.system.SceneMaster;
import com.alura.dyno.engine3d.system.events.ComponentEvent;
import com.alura.dyno.engine3d.system.events.ComponentEvent.OnScreenSizeChangedEvent;
import com.alura.dyno.engine3d.system.events.TreeEventDispatcher;
import com.alura.dyno.engine3d.system.fonts.Font;
import com.alura.dyno.engine3d.system.fonts.FontLoader;
import com.alura.dyno.engine3d.system.shaders.ShaderMaster;
import com.alura.dyno.engine3d.utils.ColorPalette;

import org.w3c.dom.Text;

public class StructureDrawGLSurface extends GLSurfaceView implements ColorPalette {
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
    public void setRenderer(Renderer renderer) {
        super.setRenderer(renderer);
        this.renderer = (StructureDrawRenderer) renderer;
        this.renderer.setOnRenderListener(new SimpleSurfaceRendererListener());
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
    private void loadObjects() {
        root = EmptyObject.EmptyObjectBuilder.builder("Root")
                .setPosition(0.0f, 0.0f, -0.5f)
                .build();

        AdaptableGridRenderer grid = AdaptableGridRenderer.AdaptableGridRendererBuilder
                .builder("Grid", ShaderMaster.gridShader, getContext())
                .setBackgroundColor(ColorPalette.MIDNIGHT_BLUE)
                .setLineColor(ColorPalette.WASHED_BLUE)
                .setMaxTexturePixelSize(600)
                .setMinTexturePixelSize(300)
                .setSpacing(1.0f)
                .setTexture(R.drawable.grid_texture)
                .build();

        TextRenderer text = TextRenderer.TextRendererBuilder.builder("Text", ShaderMaster.textShader, font)
                .setFontColor(ColorPalette.BLUE)
                .setFontSize(0.001f)
                .setText("AaBbCcDdEeFfGgHhIiJjKkLlMmNnOoPpQqRrSsTtUuVvWwXxYyZz01234567890")
                .build();

        //root.addComponent(grid);
        root.addComponent(text);
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







