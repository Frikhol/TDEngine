package inputs;
import static display.GameDisplay.*;
import static core.GameEngine.*;

import layout.GUIObject;
import layout.objects.GUIButton;
import layout.objects.GUIPane;
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
            checkCursor(guiObject);
        }
    }

    private static void checkCursor(GUIObject guiObject){
        if(guiObject instanceof GUIPane) {
            for (GUIObject gui : ((GUIPane) guiObject).getGrid().getGridList())
                checkCursor(gui);
            return;
        }
        if(guiObject instanceof GUIButton)
            ((GUIButton) guiObject).cursorOn();
    }
}
