package inputs;
import static display.GameDisplay.*;
import static core.GameEngine.*;

import physics.raycast.RayCast;
import ui.objects.GUIObject;
import org.lwjgl.glfw.GLFWCursorPosCallback;

public class CursorInputHadler{

    public static GLFWCursorPosCallback cursorPosCallback = new GLFWCursorPosCallback() {
        public void invoke(long window, double x, double y) {
            setCursorX(x);
            setCursorY(y);
        }
    };

    public static void cursorInputs(){
        for (GUIObject obj: getCurrentScene().getCurrentGUI().getGuiList())
            obj.setUnPointed();
        for(int i = getCurrentScene().getCurrentGUI().getGuiList().size()-1;i>=0;i--){
            getCurrentScene().getCurrentGUI().getGuiList().get(i).setPointed();
            if(getCurrentScene().getCurrentGUI().getGuiList().get(i).isPointed()) {
                RayCast.setPointingInWorld(false);
                return;
            }
        }
        RayCast.setPointingInWorld(true);
    }


}
