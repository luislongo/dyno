package com.alura.dyno.engine3d.system;

import com.alura.dyno.engine3d.script.CameraController;

public class SceneMaster {
    private static CameraController mainCamera;

    public static CameraController getMainCamera() {
        return mainCamera;
    }

    public static void setMainCamera(CameraController mainCamera) {
        SceneMaster.mainCamera = mainCamera;
    }
}
