package inputs;

import core.GameEngine;
import org.lwjgl.glfw.GLFWKeyCallback;


public class KeyInputHandler {

    private static int[] keys = new int[512];
    private static InputList inputList;

    public static GLFWKeyCallback keyCallback = new GLFWKeyCallback(){
        public void invoke(long window, int key, int scancode, int action, int mods) {
            inputList = GameEngine.getCurrentScene().getInputList();
            keys[key] = action;
            if (keys[key] == 1)
                inputList.keyPressed(key, 0);
        }
    };

    public static void getInputs(){
        if (inputList == null)
            return;
        for(int i = 0;i<512;i++) {
            if (keys[i] != 0)
                inputList.keyDown(i, 0);
            else
                inputList.keyReleased(i, 0);

        }
    }
}

