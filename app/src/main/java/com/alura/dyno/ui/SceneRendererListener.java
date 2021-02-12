package com.alura.dyno.ui;

public class SceneRendererListener {
    private final SceneView sceneView;

    public SceneRendererListener(SceneView sceneView) {
        this.sceneView = sceneView;
    }

    public class SimpleSceneListener implements StructureDrawRenderer.SurfaceRendererListener {
        @Override
        public void OnSurfaceCreated(StructureDrawRenderer renderer) {
            //TODO This should be handled by a script triggered by an onSceneReady event
            //     ... or something like that




        }

        @Override
        public void OnSurfaceChanged(int width, int height) {

        }

        @Override
        public void OnRender(StructureDrawRenderer renderer) {

        }
    }
}