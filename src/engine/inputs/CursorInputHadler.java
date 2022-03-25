package inputs;
import static display.GameDisplay.*;
import static core.GameEngine.*;

import layout.GUIObject;
import layout.objects.GUIButton;
import org.lwjgl.glfw.GLFWCursorPosCallback;

public class CursorInputHadler{

    public static GLFWCursorPosCallback cursorPosCallback = new GLFWCursorPosCallback() {
        public void invoke(long window, double x, double y) {
            setCursorX(x);
            setCursorY(y);
        }
    };

    public static void cursorInputs(){
        for(GUIObject guiObject : getCurrentScene().getCurrentGUI().getGuiList()){
            if(guiObject instanceof GUIButton)
                ((GUIButton) guiObject).cursorOn();
        }
    }


}
