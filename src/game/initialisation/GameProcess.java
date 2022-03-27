package initialisation;

import java.util.ArrayList;

import static core.GameEngine.*;
import static display.GameDisplay.*;
import static org.lwjgl.glfw.GLFW.glfwWindowShouldClose;

public abstract class GameProcess{
    private static boolean pause = false;

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
                        if(!pause)
                            for (GameProcess i : list) {
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

    public static boolean isPause() {
        return pause;
    }

    public static void setPause(boolean pause) {
        GameProcess.pause = pause;
    }
}
