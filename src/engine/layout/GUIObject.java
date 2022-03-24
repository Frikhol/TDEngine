package layout;

import layout.components.GUITexture;
import org.joml.Vector2f;

public class GUIObject extends Object{
    private GUITexture texture;
    private Vector2f position;
    private Vector2f scale;

    public GUIObject(){}

    public GUIObject(Vector2f position, Vector2f scale) {
        this.texture = GUITexture.findTexture(this.getClass().getSimpleName());
        this.position = position;
        this.scale = scale;
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

    public GUITexture getTexture() {
        return texture;
    }

    public Vector2f getPosition() {
        return position;
    }

    public void setPosition(Vector2f position) {
        this.position = position;
    }

    public Vector2f getScale() {
        return scale;
    }

    public void setScale(Vector2f scale) {
        this.scale = scale;
    }
}
