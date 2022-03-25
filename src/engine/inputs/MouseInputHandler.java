package inputs;

import core.GameEngine;
import org.lwjgl.glfw.GLFWMouseButtonCallback;


public class MouseInputHandler {

    private static int[] buttons = new int[8];
    private static InputList inputList;

    public static GLFWMouseButtonCallback mouseButtonCallback = new GLFWMouseButtonCallback(){
        public void invoke(long window, int button, int action, int mods) {
            inputList = GameEngine.getCurrentScene().getInputList();
            buttons[button] = action;
            if (buttons[button] == 1)
                inputList.mousePressed(button, 0);
        }
    };

    public static void getInputs(){
        if (inputList == null)
            return;
        for(int i = 0;i<8;i++) {
            if (buttons[i] != 0)
                inputList.mouseDown(i, 0);
            else
                inputList.mouseReleased(i, 0);
        }
    }
}
