package layout;

import static display.GameDisplay.*;

import org.joml.Vector2f;
import java.util.LinkedList;
import java.util.List;

import static org.lwjgl.opengl.GL11.*;

public class GUI {

    private List<GUIObject> guiList;
    private static int polygonMode = GL_FILL;

    public GUI(){
        guiList = new LinkedList<>();
    }

    public GUI(List<GUIObject> guiList) {
        this.guiList = guiList;
    }

    public List<GUIObject> getGuiList() {
        return guiList;
    }

    public void add(GUIObject guiObject){
        guiList.add(guiObject);
    }

    public void remove(GUIObject guiObject){
        guiList.remove(guiObject);
    }

    public static Vector2f getProperPosition(float x, float y){
        return new Vector2f(2f*(x)-1.0f,(-2f)*(y) +1);
    }

    public static Vector2f getScreenPosition(Vector2f pos){
        return new Vector2f((pos.x+1f)/2f,(pos.y-1)/(-2f));
    }

    public static Vector2f getProperScale(int width,int height){
        return new Vector2f((float)width/(float)(getDisplayWIDTH()[0]),(float)height/(float)(getDisplayHEIGHT()[0]));
    }

    public static void changePolyMode(){
        polygonMode = polygonMode == GL_FILL? GL_LINE: GL_FILL;
        glPolygonMode(GL_FRONT_AND_BACK, polygonMode);
    }

}
