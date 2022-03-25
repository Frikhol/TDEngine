package layout.objects;

import layout.GUI;
import layout.GUIObject;
import org.joml.Vector2i;
import static display.GameDisplay.*;

public class GUIButton extends GUIObject {
    private Vector2i location;
    private Vector2i size;

    public GUIButton(int width,int height){
        this(0,0,width,height);
    }

    public GUIButton(int posX, int posY, int width, int height) {
        super(GUI.getProperPosition(posX,posY,width,height),GUI.getProperScale(width,height));
        this.location = new Vector2i(posX,posY);
        this.size = new Vector2i(width,height);
    }

    public Vector2i getLocation() {
        return location;
    }

    public void setLocation(Vector2i location) {
        setPosition(GUI.getProperPosition(location.x,location.y,size.x,size.y));
        resetLocation(location);
    }

    public void resetLocation(Vector2i location) {
        this.location = location;
    }

    public Vector2i getSize() {
        return size;
    }

    public void setSize(Vector2i size) {
        setScale(GUI.getProperScale(size.x,size.y));
        setPosition(GUI.getProperPosition(location.x,location.y,size.x,size.y));
        resetSize(size);
    }

    public void resetSize(Vector2i size) {
        this.size = size;
    }

    public void cursorOn(){
        if((getCursorX()>=location.x && getCursorX()<=(location.x+size.x)&&(getCursorY()>=location.y && getCursorY()<=(location.y+size.y))))
            this.setTexture("GUIButtonON");
        else
            this.setTexture("GUIButton");
    }
}
