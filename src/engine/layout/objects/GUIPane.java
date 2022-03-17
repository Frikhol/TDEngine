package layout.objects;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import layout.GUI;
import layout.GUIObject;
import org.joml.Vector2f;

public class GUIPane extends GUIObject {
    @JsonIgnoreProperties({"finite"})
    private Vector2f position;
    @JsonIgnoreProperties({"finite"})
    private Vector2f scale;

    public GUIPane(int posX, int posY, int width, int height) {
        super("GUIPane", GUI.getProperPosition(posX,posY), GUI.getProperScale(width,height));
        this.position = super.getPosition();
        this.scale = super.getScale();
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
