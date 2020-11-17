package com.alura.dyno.engine3d.system;

import com.alura.dyno.engine3d.components.Camera;

public class SceneMaster {
    private static Camera mainCamera;

    public static Camera getMainCamera() {
        return mainCamera;
    }

    public static void setMainCamera(Camera mainCamera) {
        SceneMaster.mainCamera = mainCamera;
    }
}
