package layout;

import layout.components.Color;
import layout.components.GUITexture;
import layout.objects.GUIButton;
import layout.objects.GUIPane;
import org.joml.Vector2f;
import org.joml.Vector4f;

import static core.GameEngine.getCurrentScene;

public class GUIObject extends Object {
    private GUITexture texture;
    private Vector2f position;
    private Vector2f scale;
    private Color color;

    public GUIObject(){}

    public GUIObject(Vector2f position, Vector2f scale) {
        this.texture = GUITexture.findTexture(this.getClass().getSimpleName());
        this.position = position;
        this.scale = scale;
        this.color = Color.white;
    }

    /*public GUIText getText() {
        return text;
    }

    public String getTextString() {
        return text.getTextString();
    }

    public void setTextString(String text) {
        if(this.text == null)
            this.text = new GUIText(text,1, TextMaster.getFonts().get("calibri"),GUI.getScreenPosition(this.position.x-scale.x,this.position.y),scale.x,true);
        else
            this.text.updateText(text);
    }

    public void setText(GUIText text) {
            this.text = text;
    }*/

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
        if(this instanceof GUIButton)
            ((GUIButton) this).resetLocation(GUI.getLocation(this.position,this.scale));
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
        if(this instanceof GUIButton)
            ((GUIButton) this).resetSize(GUI.getSize(this.scale));
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

    public void setVisible(boolean visible) {
        if(!visible){
            getCurrentScene().getCurrentGUI().remove(this);
        }
        else
            getCurrentScene().getCurrentGUI().add(this);
    }
}
