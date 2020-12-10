package com.alura.dyno.ui;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.util.AttributeSet;
import android.view.MotionEvent;

import com.alura.dyno.R;
import com.alura.dyno.engine3d.components.AdaptableGridRenderer;
import com.alura.dyno.engine3d.components.Camera;
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
import com.alura.dyno.engine3d.system.shaders.ShaderMaster;
import com.alura.dyno.engine3d.system.vertex.MeshBuffer;
import com.alura.dyno.engine3d.system.vertex.Vertex;
import com.alura.dyno.engine3d.utils.ColorPalette;
import com.alura.dyno.maths.Vector3G;

public class StructureDrawGLSurface extends GLSurfaceView implements ColorPalette {
    StructureDrawRenderer renderer;
    StructureDrawSurfaceListener listener;

    SceneObject root;
    Camera cam;
    Font font;
    private MeshRenderer mesh;
    private EmptyObject circle;

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
                .setPosition(new Vector3G(0.0f, 0.0f, 1.0f))
                .build();

        cameraHandle.setParent(root);
        cameraHandle.addComponent(cam);
        SceneMaster.setMainCamera(cam);
    }
    private void loadObjects() {
        root = EmptyObject.EmptyObjectBuilder.builder("Root")
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

        circle = EmptyObject.EmptyObjectBuilder.builder("Circle")
                .build();

        float radius = 20;

        for(int i = 0; i <= 20; i++) {
            float angle = 0.1f * (float) i * (float) Math.PI;
            float x = radius * (float) Math.cos(angle);
            float y = radius * (float) Math.sin(angle);

            MeshBuffer data = getData();
            EmptyObject meshHandle = EmptyObject.EmptyObjectBuilder.builder("Mesh " + i)
                    .setPosition(new Vector3G(x, y, 0.0f))
                    .build();
            mesh = MeshRenderer.MeshRendererBuilder.builder("Cube " + i, ShaderMaster.objectShader)
                    .setData(data)
                    .build();

            meshHandle.addComponent(mesh);
            meshHandle.setParent(circle);
        }

        circle.setParent(root);

        root.addComponent(grid);
        root.addComponent(text);
    }

    private MeshBuffer getData() {
        return new MeshBuffer()
                .addElement(new Vertex.VertexBuilder(-1, -1, 1).setColor(ColorPalette.ACQUA_GREEN).build(),
                            new Vertex.VertexBuilder(1, -1, 1).setColor(ColorPalette.ACQUA_GREEN).build(),
                            new Vertex.VertexBuilder(1, 1, 1).setColor(ColorPalette.ACQUA_GREEN).build())
                .addElement(new Vertex.VertexBuilder(-1, -1, 1).setColor(ColorPalette.ACQUA_GREEN).build(),
                        new Vertex.VertexBuilder(1, 1, 1).setColor(ColorPalette.ACQUA_GREEN).build(),
                        new Vertex.VertexBuilder(-1, 1, 1).setColor(ColorPalette.ACQUA_GREEN).build())
                .addElement(new Vertex.VertexBuilder(-1, -1, -1).setColor(ColorPalette.ACQUA_GREEN).build(),
                         new Vertex.VertexBuilder(1, -1, -1).setColor(ColorPalette.ACQUA_GREEN).build(),
                         new Vertex.VertexBuilder(1, 1, -1).setColor(ColorPalette.ACQUA_GREEN).build())
                .addElement(new Vertex.VertexBuilder(-1, -1, -1).setColor(ColorPalette.ACQUA_GREEN).build(),
                        new Vertex.VertexBuilder(1, 1, -1).setColor(ColorPalette.ACQUA_GREEN).build(),
                        new Vertex.VertexBuilder(-1, 1, -1).setColor(ColorPalette.ACQUA_GREEN).build())
                .addElement(new Vertex.VertexBuilder(1, -1, -1).setColor(ColorPalette.ACQUA_GREEN).build(),
                        new Vertex.VertexBuilder(1, 1, -1).setColor(ColorPalette.ACQUA_GREEN).build(),
                        new Vertex.VertexBuilder(1, 1, 1).setColor(ColorPalette.ACQUA_GREEN).build())
                .addElement(new Vertex.VertexBuilder(1, -1, -1).setColor(ColorPalette.ACQUA_GREEN).build(),
                        new Vertex.VertexBuilder(1, 1, 1).setColor(ColorPalette.ACQUA_GREEN).build(),
                        new Vertex.VertexBuilder(1, -1, 1).setColor(ColorPalette.ACQUA_GREEN).build())
                .addElement(new Vertex.VertexBuilder(-1, -1, -1).setColor(ColorPalette.ACQUA_GREEN).build(),
                        new Vertex.VertexBuilder(-1, 1, -1).setColor(ColorPalette.ACQUA_GREEN).build(),
                        new Vertex.VertexBuilder(-1, 1, 1).setColor(ColorPalette.ACQUA_GREEN).build())
                .addElement(new Vertex.VertexBuilder(-1, -1, -1).setColor(ColorPalette.ACQUA_GREEN).build(),
                        new Vertex.VertexBuilder(-1, 1, 1).setColor(ColorPalette.ACQUA_GREEN).build(),
                        new Vertex.VertexBuilder(-1, -1, 1).setColor(ColorPalette.ACQUA_GREEN).build())
                .addElement(new Vertex.VertexBuilder(-1, 1, -1).setColor(ColorPalette.ACQUA_GREEN).build(),
                        new Vertex.VertexBuilder(1, 1, -1).setColor(ColorPalette.ACQUA_GREEN).build(),
                        new Vertex.VertexBuilder(1, 1, 1).setColor(ColorPalette.ACQUA_GREEN).build())
                .addElement(new Vertex.VertexBuilder(-1, 1, -1).setColor(ColorPalette.ACQUA_GREEN).build(),
                        new Vertex.VertexBuilder(1, 1, 1).setColor(ColorPalette.ACQUA_GREEN).build(),
                        new Vertex.VertexBuilder(-1, 1, 1).setColor(ColorPalette.ACQUA_GREEN).build())
                .addElement(new Vertex.VertexBuilder(-1, -1, -1).setColor(ColorPalette.ACQUA_GREEN).build(),
                        new Vertex.VertexBuilder(1, -1, -1).setColor(ColorPalette.ACQUA_GREEN).build(),
                        new Vertex.VertexBuilder(1, -1, 1).setColor(ColorPalette.ACQUA_GREEN).build())
                .addElement(new Vertex.VertexBuilder(-1, -1, -1).setColor(ColorPalette.ACQUA_GREEN).build(),
                        new Vertex.VertexBuilder(1, -1, 1).setColor(ColorPalette.ACQUA_GREEN).build(),
                        new Vertex.VertexBuilder(-1, -1, 1).setColor(ColorPalette.ACQUA_GREEN).build());
    }

    private void loadShaders() {
        ShaderMaster.loadShaders(getContext());
    }
    private void loadFonts() {
        font = new FontLoader(getContext())
                .load(R.drawable.font_atlas_handwritten, R.raw.font_map_handwritten);
    }

    @Override public boolean onTouchEvent(MotionEvent event) {
        if (listener != null) {
            return listener.onTouch(event);
        } else {
            return false;
        }
    }

    public interface StructureDrawSurfaceListener {
        boolean onTouch(MotionEvent e);
    }

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
            circle.getLocalTransform().scale(new Vector3G(1.f,1.001f, 1.0f));
            for(SceneObject so : circle.getChildrenList())
            {
               so.getLocalTransform().move(new Vector3G(.05f,0.0f,0.0f));
            }

            TreeEventDispatcher.dispatcher.dispatchEvent(new ComponentEvent.OnUpdateEvent());
            TreeEventDispatcher.dispatcher.dispatchEvent(new ComponentEvent.OnRenderEvent());
        }
    }
}







