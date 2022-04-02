package inputs;

import core.GameEngine;
import core.entities.GameObject;
import core.entities.Camera;
//import initialisation.GameProcess;
import physics.raycast.RayCast;
//import physics.raycast.RayCastHit;
import ui.GUI;
import ui.objects.GUIObject;
import ui.objects.GUIButton;

import static core.GameEngine.*;
import static core.display.GameDisplay.*;

public class DefaultControls implements InputList {

    @Override
    public void keyDown(int key, int mods) {
        Camera camera = GameEngine.getCurrentScene().getCamera();
        if(key == KeyCode.GLFW_KEY_S){
            camera.getTransform().translateZ((float) (.1f * Math.cos(camera.getYaw()*Math.PI/180)));
            camera.getTransform().translateX((float) (-.1f * Math.sin(camera.getYaw()*Math.PI/180)));
        }
        if(key == KeyCode.GLFW_KEY_W) {
            camera.getTransform().translateZ((float) (-.1f * Math.cos(camera.getYaw() * Math.PI / 180)));
            camera.getTransform().translateX((float) (.1f * Math.sin(camera.getYaw() * Math.PI / 180)));
        }
        if(key == KeyCode.GLFW_KEY_A) {
            camera.getTransform().translateX((float) (-.1f * Math.cos(camera.getYaw()*Math.PI/180)));
            camera.getTransform().translateZ((float) (-.1f * Math.sin(camera.getYaw()*Math.PI/180)));
        }
        if(key == KeyCode.GLFW_KEY_D) {
            camera.getTransform().translateX((float) (.1f * Math.cos(camera.getYaw()*Math.PI/180)));
            camera.getTransform().translateZ((float) (.1f * Math.sin(camera.getYaw()*Math.PI/180)));
        }
        if(key == KeyCode.GLFW_KEY_SPACE)
            camera.getTransform().translateY(.1f);
        if(key == KeyCode.GLFW_KEY_LEFT_CONTROL)
            camera.getTransform().translateY(-.1f);
        if(key == KeyCode.GLFW_KEY_Q)
            camera.rotateY(-.3f);
        if(key == KeyCode.GLFW_KEY_E)
            camera.rotateY(.3f);
    }

    @Override
    public void keyPressed(int key, int mods) {
        if(key == KeyCode.GLFW_KEY_F1)
            GUI.changePolyMode();
        if(key == KeyCode.GLFW_KEY_F2)
            GUI.changeColliderVisibility();
        /*if(key == KeyCode.GLFW_KEY_P) {
            GameProcess.setPause(!GameProcess.isPause());
            getPausePane().setVisible(GameProcess.isPause());
        }*/
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
        /*if(button == MouseCode.GLFW_MOUSE_BUTTON_2){
            if(RayCast.isPointingInWorld()) {
                GameObject nearest = RayCastHit.getNearest();
                if (nearest != null)
                    System.out.println("pressed" + nearest.getName());
            }
        }*/


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