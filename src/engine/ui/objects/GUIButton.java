package ui.objects;

import ui.font.GUIText;
import ui.font.TextMaster;
import org.joml.Vector2f;
import ui.components.Action;
import org.joml.Vector2i;

import static core.display.GameDisplay.*;

public class GUIButton extends GUIObject{

    private Action action = new Action() {
        @Override
        public void actionOnPressed() {

        }

        @Override
        public void actionOnReleased() {

        }
    };

    private GUIText text = null;
    private boolean isPressed = false;
    private boolean isReleased = true;

    public GUIButton(){
        super();
    }

    public GUIButton(String text){
        super();
        this.text = new GUIText(text, 1, TextMaster.getFonts().get("calibri"), new Vector2f(-100,-100), 0f, true);
    }

    public GUIButton(int width,int height){
        super(width,height);
    }

    public GUIButton(int posX, int posY, int width, int height) {
        super(posX, posY, width, height);
    }

    public GUIText getText() {
        return text;
    }

    public void setText(GUIText text) {
        this.text = text;
        text.relocate(toTextPosition(getPosition(),getScale()),getScale().x);
    }

    public String getTextString() {
        return text.getTextString();
    }

    public void setTextString(String text) {
        setTextString(text,"calibri");
    }

    public void setTextString(String text,String font) {
        if(this.text == null)
            this.text = new GUIText(text, 1, TextMaster.getFonts().get(font), toTextPosition(getLocation(), getSize()), getScale().x, true);
        else
            this.text.updateText(text);
    }

    @Override
    public void setPosition(Vector2f position) {
        super.setPosition(position);
        if(text!=null)
            text.relocate(toTextPosition(getPosition(),getScale()),getScale().x);
    }

    @Override
    public void setScale(Vector2f scale) {
        super.setScale(scale);
        if(text!=null)
            text.relocate(toTextPosition(getPosition(),getScale()),getScale().x);
    }

    @Override
    public void setLocation(Vector2i location) {
        super.setLocation(location);
        if(text!=null)
            text.relocate(toTextPosition(getPosition(),getScale()),getScale().x);
    }

    @Override
    public void setSize(Vector2i size) {
        super.setSize(size);
        if(text!=null)
            text.relocate(toTextPosition(getPosition(),getScale()),getScale().x);
    }

    private static Vector2f toTextPosition(Vector2i location, Vector2i size){
        return new Vector2f((float)location.x/(float)getDisplayWIDTH()[0],((float)location.y+size.y/2f)/(float)getDisplayHEIGHT()[0]-0.015f);
    }

    private static Vector2f toTextPosition(Vector2f position,Vector2f scale){
        return new Vector2f((position.x+1f-scale.x)/2f,1f-(position.y+1f)/2f-0.015f);
    }

    public void setPointed(){
        super.setPointed();
        isCursorOn();
    }

    public void setUnPointed(){
        super.setUnPointed();
        isCursorOn();
    }

    private void isCursorOn(){
        if(!isPressed && isReleased) {
            if (isPointed())
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
