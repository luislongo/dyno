package com.alura.dyno.engine3d.scene;

import android.content.Context;
import android.opengl.GLES20;

import com.alura.dyno.R;
import com.alura.dyno.engine3d.draw.ShapeDrawer;
import com.alura.dyno.engine3d.draw.deformer.IDeformer;
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
import com.alura.dyno.engine3d.render.Texture;
import com.alura.dyno.engine3d.render.Vertex;
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
    Texture textureA;
    Texture textureB;

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
        chechMaxTextureSlot();
        loadTextures();
        loadShader();
        loadCamera();
        loadObjects();

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



        model.setShader(new SimpleShader(loader.getSources()));
    }
    private void loadTextures() {
        textureA = new Texture(R.drawable.grid_texture, context);
        textureB = new Texture(R.drawable.grid_texture, context);

    }
    private void loadCamera() {
        Camera camera = new Camera("MainCam", 0.01f, 100.0f, 50f);
        camera.transform().move(new Vector3(0.0f, 0.0f, 50.0f));
        camera.addLeaf(new CameraController("CameraController"));

        model.setMainCamera(camera);
        model.getRoot().addChild(camera);
    }
    private void loadObjects() {
        Glyph a= new Glyph("");
        a.transform().setPosition(new Vector3(0,0,-10));
        RotateScript scriptA = new RotateScript("Rotate",
                Quaternion.fromAxisAndAngle(new Vector3(0.1f,0.1f, 0.0f), 1.0f));
        a.addLeaf(scriptA);
        model.root.addChild(a);

        createTriad(a, 20, 0.8f, 2, 0.5f);
    }
    private void createTriad(Glyph parent, float radius, float vel, int n, float  factor) {
        if(n == 0) {
            return;
        }

        Glyph a = createRotatingCubaAt(new Vector3(radius, 0,0 ));
        Glyph b = createRotatingCubaAt(new Vector3(-radius, 0,0));
        Glyph c = createRotatingCubaAt(new Vector3(0, radius,0));
        Glyph d = createRotatingCubaAt(new Vector3(0, -radius,0));

        parent.addChild(a);
        parent.addChild(b);
        parent.addChild(c);
        parent.addChild(d);

        createTriad(a, radius * factor, vel/factor,n-1, factor);
        createTriad(b, radius * factor, vel/factor,n-1, factor);
        createTriad(c, radius * factor, vel/factor,n-1, factor);
        createTriad(d, radius * factor, vel/factor,n-1, factor);
    }
    int count = 0;
    private Glyph createRotatingCubaAt(Vector3 pos) {
        ShapeDrawer drawer = new ShapeDrawer();

        Quad quad = new Quad(2f,2f);
        drawer.addShape(quad);

        Mesh mesh = drawer.asMesh();
        MeshRenderer renderer = new MeshRenderer("Mesh");
        renderer.setTexture(textureA);
        renderer.setData(mesh);
        renderer.setShader((SimpleShader) model.getShader());
        renderer.invalidate();

        Glyph glyph = new Glyph("Cube");

        glyph.addLeaf(renderer);

        glyph.transform().move(pos);

        return glyph;
    }

    @Override public void OnViewChanged(OnViewChangedEvent event) {
        dispatcher.sendDownTheTree(event);
    }

    @Override public void OnRender(OnRenderEvent event) {
        dispatcher.sendDownTheTree(new OnUpdateEvent());
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
