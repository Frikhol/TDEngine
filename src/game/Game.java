import org.joml.Vector3f;

import static core.GameEngine.*;
import static display.GameDisplay.getDisplayID;
import static org.lwjgl.glfw.GLFW.glfwWindowShouldClose;

public class Game implements Runnable{

    public static void main(String[] args) {
        new Thread(new Game()).start();
    }

    @Override
    public void run() {
        startEngine();

        if(getCurrentScene() == null){
            System.err.println("No loaded scene");
            return;
        }
        while(!glfwWindowShouldClose(getDisplayID())){
            update();
        }
        close();
    }

    private void update(){
        getCurrentScene().getGameObjectList().get(0).rotate(new Vector3f(0,0.5f,0));
        loop();
    }

    private void close(){
        stopEngine();
    }

}
