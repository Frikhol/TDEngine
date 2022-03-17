package layout;

import static display.GameDisplay.*;

import org.joml.Vector2f;
import java.util.LinkedList;
import java.util.List;

import static org.lwjgl.opengl.GL11.*;

public class GUI {

    private List<GUIObject> textureList;
    private static int polygonMode = GL_FILL;

    public GUI(){
        textureList = new LinkedList<>();
    }

    public GUI(List<GUIObject> textureList) {
        this.textureList = textureList;
    }

    public List<GUIObject> getTextureList() {
        return textureList;
    }

    public void add(GUIObject texture){
        textureList.add(texture);
    }

    public void remove(GUIObject texture){
        textureList.remove(texture);
    }

    public static Vector2f getProperPosition(int x, int y){
        return new Vector2f(2f*((float)x/(float)getDisplayWIDTH()[0])-1.0f,(-2f)*((float)y/(float)getDisplayHEIGHT()[0]) +1);
    }

    public static Vector2f getScreenPosition(float x, float y){
        return new Vector2f((x+1f)/2f,(y-1)/(-2f));
    }

    public static Vector2f getProperScale(int width,int height){
        return new Vector2f((float)width/(float)(getDisplayWIDTH()[0]),(float)height/(float)(getDisplayHEIGHT()[0]));
    }

    public static void changePolyMode(){
        polygonMode = polygonMode == GL_FILL? GL_LINE: GL_FILL;
        glPolygonMode(GL_FRONT_AND_BACK, polygonMode);
    }

}
