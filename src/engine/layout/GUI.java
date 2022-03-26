package layout;

import static display.GameDisplay.*;

import layout.objects.GUIPane;
import org.joml.Vector2f;
import org.joml.Vector2i;

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
        addChildes(guiObject);
    }

    private void addChildes(GUIObject guiObject){
        if(guiObject instanceof GUIPane) {
            for(GUIObject child : ((GUIPane) guiObject).getGrid().getGridList()) {
                guiList.add(child);
                addChildes(child);
            }
        }
    }

    public void remove(GUIObject guiObject){
        guiList.remove(guiObject);
    }

    public static Vector2f getProperPosition(int x, int y, int width, int height){
        return new Vector2f(
                ((((float)x+(float)width/2f)/(float)getDisplayWIDTH()[0])*2f-1f),
                ((1f-((float)y+(float)height/2f)/(float)getDisplayHEIGHT()[0])*2f-1f)
        );
    }

    public static Vector2i getLocation(Vector2f position,Vector2f scale){
        Vector2i size = getSize(scale);
        return new Vector2i(
                (int)((((position.x+1f)/2f)*(float)(getDisplayWIDTH()[0]))-(float)size.x/2f),
                (int)(((1f-((position.y+1f)/2f))*(float)(getDisplayHEIGHT()[0]))-(float)size.y/2f)
        );
    }

    public static Vector2f getProperScale(int width,int height){
        return new Vector2f((float)width/(float)(getDisplayWIDTH()[0]),(float)height/(float)(getDisplayHEIGHT()[0]));
    }

    public static Vector2i getSize(Vector2f scale){
        return new Vector2i((int)(scale.x*getDisplayWIDTH()[0]),(int)(scale.y*getDisplayHEIGHT()[0]));
    }

    public static Vector2f getTextPosition(Vector2i location,Vector2i size){
        return new Vector2f((float)location.x/(float)getDisplayWIDTH()[0],((float)location.y+size.y/2f)/(float)getDisplayHEIGHT()[0]-0.015f);
    }

    public static Vector2f getTextPosition(Vector2f position,Vector2f scale){
        return new Vector2f((position.x+1f-scale.x)/2f,1f-(position.y+1f)/2f-0.015f);
    }

    public static void changePolyMode(){
        polygonMode = polygonMode == GL_FILL? GL_LINE: GL_FILL;
        glPolygonMode(GL_FRONT_AND_BACK, polygonMode);
    }

}
