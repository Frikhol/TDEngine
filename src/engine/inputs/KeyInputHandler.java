package inputs;

import core.GameEngine;
import org.lwjgl.glfw.GLFWKeyCallback;


public class KeyInputHandler {

    private static int[] keys = new int[512];
    private static KeyList keyList;

    public static GLFWKeyCallback keyCallback = new GLFWKeyCallback(){
        public void invoke(long window, int key, int scancode, int action, int mods) {
            keyList = GameEngine.getCurrentScene().getKeyList();
            keys[key] = action;
            if (keys[key] == 1)
                keyList.pressed(key, 0);
        }
    };

    public static void getInputs(){
        if (keyList == null)
            return;
        for(int i = 0;i<512;i++) {
            if (keys[i] != 0)
                keyList.down(i, 0);
            else
                keyList.released(i, 0);

        }
    }
}

