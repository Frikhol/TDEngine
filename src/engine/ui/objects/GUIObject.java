package ui.objects;

import org.joml.Vector2i;
import ui.components.Color;
import ui.components.GUITexture;
import org.joml.Vector2f;
import org.joml.Vector4f;

import static core.GameEngine.getCurrentScene;
import static core.display.GameDisplay.*;
import static core.display.GameDisplay.getDisplayHEIGHT;

public class GUIObject {
    private GUITexture texture;
    private Vector2i location = new Vector2i(10000,10000);
    private Vector2i size = new Vector2i(0,0);
    private Vector2f position = new Vector2f(10f,10f);
    private Vector2f scale = new Vector2f(0f,0f);
    private Color color = Color.white;
    private boolean pointed = false;

    public GUIObject(){
        setTexture(this.getClass().getSimpleName());
    }

    public GUIObject(int width,int height){
        this(0,0,width,height);
    }

    public GUIObject(int posX, int posY, int width, int height) {
        this(toPosition(posX,posY,width,height),toScale(width,height));
        this.location = new Vector2i(posX,posY);
        this.size = new Vector2i(width,height);
    }

    public GUIObject(Vector2f position, Vector2f scale) {
        this.texture = GUITexture.findTexture(this.getClass().getSimpleName());
        this.position = position;
        this.scale = scale;
        this.color = Color.white;
    }

    public GUITexture getTexture() {
        return texture;
    }

    public void setTexture(String texture) {
        this.texture = GUITexture.findTexture(texture);
    }

    public Vector2f getPosition() {
        return position;
    }

    public void setPosition(Vector2f position) {
        this.position = position;
        this.location = toLocation(position,scale);
    }

    public Vector2f getScale() {
        return scale;
    }

    public void setScale(Vector2f scale) {
        this.scale = scale;
        this.size = toSize(scale);
        this.location = toLocation(position,scale);
    }

    public Vector2i getLocation() {
        return location;
    }

    public void setLocation(Vector2i location) {
        this.location = location;
        this.position = toPosition(location.x,location.y,size.x,size.y);
    }

    public Vector2i getSize() {
        return size;
    }

    public void setSize(Vector2i size) {
        this.size = size;
        this.scale = toScale(size.x,size.y);
        this.position = toPosition(location.x,location.y,size.x,size.y);
    }

    public Color getColor() {
        return color;
    }

    public Vector4f getColorVec4() {
        return color.toVector4f();
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public void setPointed() {
        this.pointed = (getCursorX() > location.x && getCursorX() < location.x + size.x)
                && (getCursorY() > location.y && getCursorY() < location.y + size.y);
    }
    public void setUnPointed() {
        this.pointed = false;
    }

    public boolean isPointed() {
        return pointed;
    }

    public void setVisible(boolean visible) {
        if(!visible){
            getCurrentScene().getCurrentGUI().remove(this);
        }
        else
            getCurrentScene().getCurrentGUI().add(this);
    }

    public void reset(){
            this.location = toLocation(this.position, this.scale);
            this.size = toSize(this.scale);
    }

    private static Vector2f toPosition(int x, int y, int width, int height){
        return new Vector2f(
                ((((float)x+(float)width/2f)/(float)getDisplayWIDTH()[0])*2f-1f),
                ((1f-((float)y+(float)height/2f)/(float)getDisplayHEIGHT()[0])*2f-1f)
        );
    }

    private static Vector2i toLocation(Vector2f position,Vector2f scale){
        Vector2i size = toSize(scale);
        return new Vector2i(
                Math.round(((((position.x+1f)/2f)*(float)(getDisplayWIDTH()[0]))-(float)size.x/2f)),
                Math.round((((1.0f-((position.y+1.0f)/2.0f))*(float)(getDisplayHEIGHT()[0]))-(float)size.y/2.0f))
        );
    }

    private static Vector2f toScale(int width,int height){
        return new Vector2f((float)width/(float)(getDisplayWIDTH()[0]),(float)height/(float)(getDisplayHEIGHT()[0]));
    }

    private static Vector2i toSize(Vector2f scale){
        return new Vector2i(Math.round(scale.x*getDisplayWIDTH()[0]),Math.round(scale.y*getDisplayHEIGHT()[0]));
    }
}
