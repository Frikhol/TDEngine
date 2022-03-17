package layout;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import core.Loader;
import font.GUIText;
import font.TextMaster;
import layout.components.Color;
import org.joml.Vector2f;
import org.joml.Vector4f;

public class GUIObject {
    private String textureName;
    @JsonIgnore
    private int textureID;
    @JsonIgnoreProperties({"finite"})
    private Vector2f position;
    @JsonIgnoreProperties({"finite"})
    private Vector2f scale;
    private Color color;
    private GUIText text;

    public GUIObject() {
    }

    public GUIObject(String textureName, Vector2f position, Vector2f scale) {
        this.textureName = textureName;
        this.textureID = Loader.loadTexture("gui/"+textureName+".png").getID();
        this.position = position;
        this.scale = scale;
        this.color = Color.white;
    }

    public GUIText getText() {
        return text;
    }
    @JsonIgnore
    public String getTextString() {
        return text.getTextString();
    }
    @JsonIgnore
    public void setTextString(String text) {
        if(this.text == null)
            this.text = new GUIText(text,1, TextMaster.getFonts().get("calibri"),GUI.getScreenPosition(this.position.x-scale.x,this.position.y),scale.x,true);
        else
            this.text.updateText(text);
    }

    @JsonProperty("text")
    public void setText(GUIText text) {
        if(text!=null) {
            this.text = new GUIText(text.getTextString(), text.getFontSize(), TextMaster.getFonts().get("calibri"), text.getPosition(), text.getMaxLineSize(), true);
            this.text.setColour(text.getColour().x, text.getColour().y, text.getColour().z);
        }
    }

    @JsonIgnore
    public Vector4f getColorVec4() {
        return color.toVector4f();
    }

    public Color getColor() {
        return color;
    }

    public void setColor(int R, int G, int B, int a) {
        this.color = new Color(R,G,B,a);
    }

    public String getTextureName() {
        return textureName;
    }

    public void setTextureName(String textureName) {
        this.textureName = textureName;
        this.textureID = Loader.loadTexture("gui/"+textureName+".png").getID();
    }

    public int getTextureID() {
        return textureID;
    }

    public void setTextureID(int textureID) {
        this.textureID = textureID;
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
