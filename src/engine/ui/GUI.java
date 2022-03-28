package ui;

import ui.objects.GUIObject;
import ui.objects.GUIPane;

import java.util.LinkedList;
import java.util.List;

import static org.lwjgl.opengl.GL11.*;

public class GUI {

    private List<GUIObject> guiList;
    private static int polygonMode = GL_FILL;
    private static boolean colliderVisibility = false;

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
            if(((GUIPane) guiObject).getLayout()==null)
                return;
            for(GUIObject child : ((GUIPane) guiObject).getLayout().getGuiList()) {
                guiList.add(child);
                addChildes(child);
            }
        }
    }

    public void remove(GUIObject guiObject){
        guiList.remove(guiObject);
        removeChildes(guiObject);
    }

    private void removeChildes(GUIObject guiObject) {
        if(guiObject instanceof GUIPane) {
            if(((GUIPane) guiObject).getLayout()==null)
                return;
            for(GUIObject child : ((GUIPane) guiObject).getLayout().getGuiList()) {
                guiList.remove(child);
                removeChildes(child);
            }
        }
    }

    public static void changePolyMode(){
        polygonMode = polygonMode == GL_FILL? GL_LINE: GL_FILL;
        glPolygonMode(GL_FRONT_AND_BACK, polygonMode);
    }

    public static void changeColliderVisibility() {
        colliderVisibility = !colliderVisibility;
    }

    public static boolean isColliderVisibility() {
        return colliderVisibility;
    }

    public static int getPolygonMode() {
        return polygonMode;
    }
}
