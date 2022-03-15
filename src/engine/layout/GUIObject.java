package layout;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import core.Loader;
import org.joml.Vector2f;

public class GUIObject {
    private String textureName;
    @JsonIgnore
    private int textureID;
    @JsonIgnoreProperties({"finite"})
    private Vector2f position;
    @JsonIgnoreProperties({"finite"})
    private Vector2f scale;

    public GUIObject() {
    }

    public GUIObject(String textureName, Vector2f position, Vector2f scale) {
        this.textureName = textureName;
        this.textureID = Loader.loadTexture("gui/"+textureName+".png").getID();
        this.position = position;
        this.scale = scale;
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
