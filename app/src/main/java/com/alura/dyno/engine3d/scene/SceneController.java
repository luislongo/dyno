package com.alura.dyno.engine3d.scene;

import android.content.Context;
import android.opengl.Matrix;

import com.alura.dyno.R;
import com.alura.dyno.engine3d.draw.ShapeDrawer;
import com.alura.dyno.engine3d.draw.shapes.Cube;
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
import com.alura.dyno.engine3d.render.buffer.Mesh;
import com.alura.dyno.engine3d.render.shader.ShaderLoader;
import com.alura.dyno.engine3d.render.shader.ShaderType;
import com.alura.dyno.engine3d.render.shader.SimpleShader;
import com.alura.dyno.engine3d.script.CameraController;
import com.alura.dyno.engine3d.script.MeshRenderer;
import com.alura.dyno.math.graphics.Vector3;

public class SceneController implements IInputListener, ISceneRendererListener {
    private static SceneView view;
    private static SceneModel model;
    private static SceneRenderer renderer;
    private static TreeEventDispatcher dispatcher;

    InputDetector detector;
    Context context;

    Glyph glyphA;

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
        dispatcher.sendDownTheTree(event);

        loadShader();
        loadCamera();
        loadMesh1();
    }

    private void loadShader() {
        ShaderLoader loader = new ShaderLoader(context);
        loader.loadFromRawResource(ShaderType.Vertex, R.raw.shaderv_object);
        loader.loadFromRawResource(ShaderType.Fragment, R.raw.shaderf_object);

        model.setShader(new SimpleShader(loader.getSources()));
    }
    private void loadCamera() {
        Camera camera = new Camera("MainCam", 0.01f, 100.0f, 250f);
        camera.transform().move(new Vector3(0.0f, 0.0f, 10.0f));
        camera.addLeaf(new CameraController("CameraController"));

        model.setMainCamera(camera);
        model.getRoot().addChild(camera);
    }
    private void loadMesh1() {
        ShapeDrawer drawer = new ShapeDrawer();
        drawer.addShape(new Cube(0.75f, 0.75f, 0.75f));

        glyphA = new Glyph("A");
        model.getRoot().addChild(glyphA);


        for(int i = 0; i < 5; i++) {
            Mesh mesh = drawer.asMesh();
            MeshRenderer renderer = new MeshRenderer("Mesh");
            renderer.setMesh(mesh);
            renderer.setShader((SimpleShader) model.getShader());
            renderer.invalidate();

            Glyph glyph = new Glyph("Cube " + i);
            glyph.addLeaf(renderer);
            glyph.transform().move(new Vector3(i, 0, 0));

            glyphA.addChild(glyph);
        }

    }

    @Override public void OnViewChanged(OnViewChangedEvent event) {
        dispatcher.sendDownTheTree(event);
    }

    float angle = 0.0f;
    @Override public void OnRender(OnRenderEvent event) {
        dispatcher.sendDownTheTree(new OnUpdateEvent());

        angle += 0.01f;
        glyphA.transform().setEuler(new Vector3(0,0,angle * 100));

        for(int i = 0; i < 5; i++) {
            Glyph glyph = glyphA.getChild("Cube " + i);
            glyph.transform().setEuler(new Vector3(0,angle * 100,0));
        }

        dispatcher.sendDownTheTree(event);
    }
    public static TreeEventDispatcher getDispatcher() {
        return dispatcher;
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
}
