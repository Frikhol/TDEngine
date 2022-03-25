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
        if(guiObject instanceof GUIPane) {
            guiList.addAll(((GUIPane) guiObject).getGrid().getGridList());
        }
    }

    public void remove(GUIObject guiObject){
        guiList.remove(guiObject);
    }

    public static Vector2f getProperPosition(int x, int y,int width,int height){
        int screenWidth = getDisplayWIDTH()[0]/2;
        int screenHeight = getDisplayHEIGHT()[0]/2;
        if(x<screenWidth){
            if(y<=screenHeight){//LEFT UP COORDINATE PANE
                return new Vector2f((-(1-(((float)x+(float)width/2)/(float)screenWidth))),(1-(((float)x+(float)height/2)/(float)screenHeight)));
            }
            //LEFT DOWN COORDINATE PANE
            return new Vector2f((-(1-(((float)x+(float)width/2)/(float)screenWidth))),(-((((float)y+(float)height/2)/(float)screenHeight)-1)));
        }
        else if(x>=screenWidth){
            if(y<=screenHeight){//RIGHT UP COORDINATE PANE
                return new Vector2f(((((float)x+(float)width/2)/(float)screenWidth)-1),(1-(((float)y+(float)height/2)/(float)screenHeight)));
            }
            //RIGHT DOWN COORDINATE PANE
            return new Vector2f(((((float)x+(float)width/2)/(float)screenWidth)-1),(-((((float)y+(float)height/2)/(float)screenHeight)-1)));
        }
        return null;
    }

    public static Vector2i getLocation(Vector2f position,Vector2f scale){
        return new Vector2i((int)(((position.x+1f)/2f-scale.x/2f)*getDisplayWIDTH()[0]),(int)(((position.y-1)/(-2f)-scale.y/2f)*getDisplayHEIGHT()[0]));
    }

    public static Vector2f getProperScale(int width,int height){
        return new Vector2f((float)width/(float)(getDisplayWIDTH()[0]),(float)height/(float)(getDisplayHEIGHT()[0]));
    }

    public static Vector2i getSize(Vector2f scale){
        return new Vector2i((int)(scale.x*getDisplayWIDTH()[0]),(int)(scale.y*getDisplayHEIGHT()[0]));
    }

    public static void changePolyMode(){
        polygonMode = polygonMode == GL_FILL? GL_LINE: GL_FILL;
        glPolygonMode(GL_FRONT_AND_BACK, polygonMode);
    }

}
