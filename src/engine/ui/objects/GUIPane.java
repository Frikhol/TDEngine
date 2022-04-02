package ui.objects;

import ui.layout.GUIGridLayout;
import ui.layout.GUILayout;
import org.joml.Vector2i;
import static core.display.GameDisplay.*;
import static core.GameEngine.*;

public class GUIPane extends GUIObject {
    private GUILayout layout;
    private Vector2i padding = new Vector2i(0);

    public GUIPane() {
        super();
    }

    public GUIPane(int width,int height){
        super(width,height);
    }

    public GUIPane(int posX, int posY, int width, int height) {
        super(posX, posY, width, height);
    }

    @Override
    public GUIPane clone(){
        GUIPane clone = new GUIPane(this.getLocation().x,this.getLocation().y,this.getSize().x,this.getSize().y);
        if(this.getColor()!=null)
            clone.setColor(this.getColor());
        if(this.getPadding()!=null)
        clone.setPadding(this.getPadding().x,this.getPadding().y);
        if(this.getTexture()!=null)
        clone.setTexture(this.getTexture().getName());
        return clone;
    }

    public void add(GUIObject object,int cellX,int cellY,int countX,int countY){
        if(layout instanceof GUIGridLayout) {
            layout.add(object, cellX, cellY, countX, countY);
            if(!getCurrentScene().getCurrentGUI().getGuiList().contains(object))
                getCurrentScene().getCurrentGUI().getGuiList().add(object);
        }
    }

    public GUILayout getLayout() {
        return layout;
    }

    public void setLayout(GUILayout layout) {
        this.layout = layout;
    }

    public Vector2i getPadding() {
        return padding;
    }

    public void setPadding(int x,int y) {
        this.padding = new Vector2i(x,y);
    }

    public float properPaddingX(){
        return (float)padding.x/(float)getDisplayWIDTH()[0];
    }

    public float properPaddingY(){
        return (float)padding.y/(float)getDisplayHEIGHT()[0];
    }
}
