package com.alura.dyno.engine3d.scene;

import android.content.Context;
import android.opengl.GLES20;

import com.alura.dyno.R;
import com.alura.dyno.engine3d.draw.ShapeDrawer;
import com.alura.dyno.engine3d.draw.shapes.Quad;
import com.alura.dyno.engine3d.eventsystem.TreeEventDispatcher;
import com.alura.dyno.engine3d.eventsystem.events.OnDragEvent;
import com.alura.dyno.engine3d.eventsystem.events.OnRenderEvent;
import com.alura.dyno.engine3d.eventsystem.events.OnScaleEvent;
import com.alura.dyno.engine3d.eventsystem.events.OnTapEvent;
import com.alura.dyno.engine3d.eventsystem.events.OnUpdateEvent;
import com.alura.dyno.engine3d.eventsystem.events.OnViewChangedEvent;
import com.alura.dyno.engine3d.eventsystem.events.OnViewCreatedEvent;
import com.alura.dyno.engine3d.glyph.Camera;
import com.alura.dyno.engine3d.glyph.Glyph;
import com.alura.dyno.engine3d.input.IInputListener;
import com.alura.dyno.engine3d.input.InputDetector;
import com.alura.dyno.engine3d.render.Material;
import com.alura.dyno.engine3d.render.Texture;
import com.alura.dyno.engine3d.render.buffer.Mesh;
import com.alura.dyno.engine3d.render.shader.ShaderLoader;
import com.alura.dyno.engine3d.render.shader.ShaderType;
import com.alura.dyno.engine3d.render.shader.SimpleShader;
import com.alura.dyno.engine3d.script.CameraController;
import com.alura.dyno.engine3d.script.MeshRenderer;
import com.alura.dyno.engine3d.script.RotateScript;
import com.alura.dyno.math.graphics.Quaternion;
import com.alura.dyno.math.graphics.Vector3;

public class SceneController implements IInputListener, ISceneRendererListener {
    private static SceneView view;
    private static SceneModel model;
    private static SceneRenderer renderer;
    private static TreeEventDispatcher dispatcher;

    InputDetector detector;
    Context context;

    public SceneController(SceneView view, SceneModel model, Context context) {
        this.view = view;
        this.model = model;
        this.context = context;

        dispatcher = new TreeEventDispatcher(model.getRoot());
        detector = new InputDetector(context);
        renderer = view.getRenderer();

        view.setOnTouchListener(detector);
        renderer.setOnRenderListener(this);
        detector.setInputListener(this);
    }

    @Override public void onTap(OnTapEvent event) {
        dispatcher.sendDownTheTree(event);
    }
    @Override public void onScale(OnScaleEvent event) {
        dispatcher.sendDownTheTree(event);
    }
    @Override public void onDrag(OnDragEvent event) {
        dispatcher.sendDownTheTree(event);
    }
    @Override public void OnViewCreated(OnViewCreatedEvent event) {
        chechMaxTextureSlot();
        loadMaterial();
        loadShader();
        loadCamera();
        loadObjects();

        dispatcher.sendDownTheTree(event);
    }
    @Override public void OnViewChanged(OnViewChangedEvent event) {
        dispatcher.sendDownTheTree(event);
    }
    @Override public void OnRender(OnRenderEvent event) {
        dispatcher.sendDownTheTree(new OnUpdateEvent());
        dispatcher.sendDownTheTree(event);
    }

    private void chechMaxTextureSlot() {
        int[] maxTextureUnits = new int[1];
        GLES20.glGetIntegerv(GLES20.GL_MAX_TEXTURE_IMAGE_UNITS, maxTextureUnits, 0);
        Texture.setMaxTextureUnits(maxTextureUnits[0]);
    }

    private void loadShader() {
        ShaderLoader loader = new ShaderLoader(context);
        loader.loadFromRawResource(ShaderType.Vertex, R.raw.shaderv_object);
        loader.loadFromRawResource(ShaderType.Fragment, R.raw.shaderf_object);

        model.cacheShader("ObjShader", new SimpleShader(loader.getSources()));
    }
    private void loadMaterial() {
        Texture texture = new Texture(R.drawable.crate_0, context);
        model.cacheTexture("Box", texture);

        Material material = new Material("Box");
        material.setAlbedo(model.getTexture("Box"));
        model.cacheMaterial("Box", material);
    }
    private void loadCamera() {
        Camera camera = new Camera("MainCam", 0.01f, 100.0f, 50f);
        camera.transform().move(new Vector3(0.0f, 0.0f, 50.0f));
        camera.addLeaf(new CameraController("CameraController"));

        model.setMainCamera(camera);
        model.getRoot().addChild(camera);
    }
    private void loadObjects() {
        Glyph a = new Glyph("");
        a.transform().setPosition(new Vector3(0,0,-10));

        RotateScript rotate = new RotateScript("Rotate",
                Quaternion.fromAxisAndAngle(Vector3.backward(), 1.0f));

        Mesh mesh = new ShapeDrawer().addShape(new Quad(1.0f,1.0f))
                .asMesh();

        MeshRenderer renderer = new MeshRenderer("Quad");
        renderer.setMaterial(model.getMaterial("Box"));
        renderer.setShader(model.getShader("ObjShader"));
        renderer.setData(mesh);

        a.addLeaf(rotate);
        a.addLeaf(renderer);

        model.root.addChild(a);
    }

    public static SceneView getView() {
        return view;
    }
    public static SceneModel getModel() {
        return model;
    }
    public static SceneRenderer getRenderer() {
        return renderer;
    }
    public static TreeEventDispatcher getDispatcher() {
        return dispatcher;
    }

    
}
