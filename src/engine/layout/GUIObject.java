package layout;


import core.Loader;
import font.GUIText;
import font.TextMaster;
import layout.components.Color;
import org.joml.Vector2f;
import org.joml.Vector4f;

public class GUIObject {
    private String guiType;
    private int textureID;
    private Vector2f position;
    private Vector2f scale;
    private Color color;
    private GUIText text;

    public GUIObject(String guiType, Vector2f position, Vector2f scale) {
        this.guiType = guiType;
        this.textureID = Loader.loadTexture("gui/"+ guiType +".png").getID();
        this.position = position;
        this.scale = scale;
        this.color = Color.white;
    }

    public GUIObject(String guiType, Vector2f position, Vector2f scale, Color color, GUIText text) {
        this.guiType = guiType;
        this.textureID = Loader.loadTexture("gui/"+ guiType +".png").getID();
        this.position = position;
        this.scale = scale;
        this.color = color;
        this.text = text;
    }

    public GUIText getText() {
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
        if(text!=null) {
            this.text = text;
            this.text.setColour(text.getColour().x, text.getColour().y, text.getColour().z);
        }
    }

    public Vector4f getColorVec4() {
        return color.toVector4f();
    }

    public Color getColor() {
        return color;
    }

    public void setColor(int R, int G, int B, int a) {
        this.color = new Color(R,G,B,a);
    }

    public String getGuiType() {
        return guiType;
    }

    public void setGuiType(String guiType) {
        this.guiType = guiType;
        this.textureID = Loader.loadTexture("gui/"+ guiType +".png").getID();
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
