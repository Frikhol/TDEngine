package layout.objects;

import font.GUIText;
import font.TextMaster;
import layout.GUI;
import layout.GUIObject;
import layout.components.Action;
import org.joml.Vector2i;

import static display.GameDisplay.*;

public class GUIButton extends GUIObject{

    private Action action = new Action() {
        @Override
        public void actionOnPressed() {

        }

        @Override
        public void actionOnReleased() {

        }
    };

    private Vector2i location;
    private Vector2i size;
    private GUIText text = null;
    private boolean isPressed = false;
    private boolean isReleased = true;

    public GUIButton(int width,int height){
        this(0,0,width,height);
    }

    public GUIButton(int posX, int posY, int width, int height) {
        super(GUI.getProperPosition(posX,posY,width,height),GUI.getProperScale(width,height));
        this.location = new Vector2i(posX,posY);
        this.size = new Vector2i(width,height);
    }

    public GUIText getText() {
        return text;
    }

    public String getTextString() {
        return text.getTextString();
    }

    public void setTextString(String text) {
        if(this.text == null)
            this.text = new GUIText(text, 1, TextMaster.getFonts().get("calibri"), GUI.getTextPosition(location, size), (float) size.x / (float) getDisplayWIDTH()[0], true);
        else
            this.text.updateText(text);
    }

    public void setText(GUIText text) {
            this.text = text;
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
        if(!isPressed && isReleased) {
            if ((getCursorX() >= location.x && getCursorX() <= (location.x + size.x) && (getCursorY() >= location.y && getCursorY() <= (location.y + size.y))))
                this.setTexture("GUIButtonON");
            else
                this.setTexture("GUIButton");
        }
    }

    public void setPressed(boolean pressed) {
        isPressed = pressed;
    }

    public void setReleased(boolean released) {
        isReleased = released;
    }

    public void pressed(){
        this.setTexture("GUIButtonPRESS");
        setPressed(true);
        setReleased(false);
        action.actionOnPressed();
    }

    public void released(){
        if(isPressed && !isReleased) {
            this.setTexture("GUIButton");
            action.actionOnReleased();
            setPressed(false);
            setReleased(true);
        }
    }

    public void addAction(Action action){
        this.action = action;
    }
}
