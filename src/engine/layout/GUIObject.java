package layout;

import layout.components.Color;
import layout.components.GUITexture;
import layout.objects.GUIButton;
import layout.objects.GUIPane;
import org.joml.Vector2f;
import org.joml.Vector4f;

import static core.GameEngine.getCurrentScene;
import static display.GameDisplay.getCursorX;
import static display.GameDisplay.getCursorY;

public class GUIObject {
    private GUITexture texture;
    private Vector2f position;
    private Vector2f scale;
    private Color color;
    private boolean pointed = false;

    public GUIObject(){}

    public GUIObject(Vector2f position, Vector2f scale) {
        this.texture = GUITexture.findTexture(this.getClass().getSimpleName());
        this.position = position;
        this.scale = scale;
        this.color = Color.white;
    }

    public void setTexture(String texture) {
        this.texture = GUITexture.findTexture(texture);
    }

    public GUITexture getTexture() {
        return texture;
    }

    public Vector2f getPosition() {
        return position;
    }

    public void setPosition(Vector2f position) {
        this.position = position;
        if(this instanceof GUIPane)
            ((GUIPane) this).resetLocation(GUI.getLocation(this.position,this.scale));
        if(this instanceof GUIButton) {
            ((GUIButton) this).resetLocation(GUI.getLocation(this.position, this.scale));
            if(((GUIButton) this).getText()!=null)
                ((GUIButton) this).getText().relocate(GUI.getTextPosition(this.position,this.scale),this.scale.x);
        }
    }

    public void reset(){
        if(this instanceof GUIPane) {
            ((GUIPane) this).resetLocation(GUI.getLocation(this.position, this.scale));
            ((GUIPane) this).resetSize(GUI.getSize(this.scale));
        }
        if(this instanceof GUIButton) {
            ((GUIButton) this).resetLocation(GUI.getLocation(this.position, this.scale));
            ((GUIButton) this).resetSize(GUI.getSize(this.scale));
        }
    }

    public Vector2f getScale() {
        return scale;
    }

    public void setScale(Vector2f scale) {
        this.scale = scale;
        if(this instanceof GUIPane)
            ((GUIPane) this).resetSize(GUI.getSize(this.scale));
        if(this instanceof GUIButton) {
            ((GUIButton) this).resetSize(GUI.getSize(this.scale));
        }
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
        this.pointed = (getCursorX() > GUI.getLocation(position,scale).x && getCursorX() < (GUI.getLocation(position,scale).x + GUI.getSize(scale).x)
                && (getCursorY() > GUI.getLocation(position,scale).y && getCursorY() < (GUI.getLocation(position,scale).y + GUI.getSize(scale).y)));
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
}
