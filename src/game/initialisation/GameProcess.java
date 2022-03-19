package initialisation;

import java.util.ArrayList;

import static core.GameEngine.*;
import static display.GameDisplay.getDisplayID;
import static org.lwjgl.glfw.GLFW.glfwWindowShouldClose;

public abstract class GameProcess{
    private static Thread st = null;
    private static ArrayList<GameProcess> list = new ArrayList<>();
    public GameProcess(){
        list.add(this);
        if(st==null){
            st = new Thread (){
                public void run() {
                    startEngine();
                    if(getCurrentScene() == null){
                        System.err.println("No loaded scene");
                        return;
                    }
                    for(GameProcess i : list){
                        i.start();
                    }
                    while(!glfwWindowShouldClose(getDisplayID())){
                        for(GameProcess i : list){
                            i.update();
                        }
                        loop();
                    }
                    stopEngine();
                }

            };
            st.start();
        }
    }
    public abstract void start();
    public abstract void update();
}