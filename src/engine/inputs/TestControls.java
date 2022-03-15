package inputs;

import core.GameEngine;
import display.GameDisplay;
import entities.Camera;
import layout.GUI;

public class TestControls implements KeyList {
    @Override
    public void down(int key, int mods) {
        Camera camera = GameEngine.getCurrentScene().getCamera();
        if(key == KeyCode.GLFW_KEY_S)
            camera.getTransform().translateZ(.1f);
        if(key == KeyCode.GLFW_KEY_W)
            camera.getTransform().translateZ(-.1f);
        if(key == KeyCode.GLFW_KEY_A)
            camera.getTransform().translateX(-.1f);
        if(key == KeyCode.GLFW_KEY_D)
            camera.getTransform().translateX(.1f);
    }

    @Override
    public void pressed(int key, int mods) {
        if(key == KeyCode.GLFW_KEY_F1)
            GUI.changePolyMode();
    }

    @Override
    public void released(int key, int mods) {

    }

}