package inputs;

import core.GameEngine;
import entities.Camera;
import initialisation.GameProcess;
import layout.GUI;
import layout.GUIObject;
import layout.objects.GUIButton;

import static core.GameEngine.*;
import static display.GameDisplay.*;

public class DefaultControls implements InputList {

    @Override
    public void keyDown(int key, int mods) {
        Camera camera = GameEngine.getCurrentScene().getCamera();
        if(key == KeyCode.GLFW_KEY_S)
            camera.getTransform().translateZ(.1f);
        if(key == KeyCode.GLFW_KEY_W)
            camera.getTransform().translateZ(-.1f);
        if(key == KeyCode.GLFW_KEY_A)
            camera.getTransform().translateX(-.1f);
        if(key == KeyCode.GLFW_KEY_D)
            camera.getTransform().translateX(.1f);
        if(key == KeyCode.GLFW_KEY_SPACE)
            camera.getTransform().translateY(.1f);
        if(key == KeyCode.GLFW_KEY_LEFT_CONTROL)
            camera.getTransform().translateY(-.1f);
    }

    @Override
    public void keyPressed(int key, int mods) {
        if(key == KeyCode.GLFW_KEY_F1)
            GUI.changePolyMode();
        if(key == KeyCode.GLFW_KEY_P) {
            GameProcess.setPause(!GameProcess.isPause());
            getPausePane().setVisible(GameProcess.isPause());
        }
    }

    @Override
    public void keyReleased(int key, int mods) {

    }

    @Override
    public void mouseDown(int button, int mods) {

    }

    @Override
    public void mousePressed(int button, int mods) {
        if(button == MouseCode.GLFW_MOUSE_BUTTON_1){
            for(int i = getCurrentScene().getCurrentGUI().getGuiList().size()-1;i>=0;i--){
                GUIObject obj = getCurrentScene().getCurrentGUI().getGuiList().get(i);
                if(obj.isPointed()){
                    if(obj instanceof GUIButton)
                        ((GUIButton) obj).pressed();
                    return;
                }
            }
        }
    }

    @Override
    public void mouseReleased(int button, int mods) {
        if(button == MouseCode.GLFW_MOUSE_BUTTON_1){
            for(int i = 0;i<getCurrentScene().getCurrentGUI().getGuiList().size();i++){
                GUIObject obj = getCurrentScene().getCurrentGUI().getGuiList().get(i);
                if(obj instanceof GUIButton)
                    ((GUIButton) obj).released();
            }
        }
    }
}