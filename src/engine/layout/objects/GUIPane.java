package layout.objects;

import layout.GUI;
import layout.GUIObject;
import org.joml.Vector2f;

public class GUIPane extends GUIObject {
    private Vector2f position;
    private Vector2f size;

    public GUIPane(int posX, int posY, int width, int height) {
        super("GUIPane", GUI.getProperPosition(posX,posY), GUI.getProperScale(width,height));
        this.position = new Vector2f(posX,posY);
        this.size = new Vector2f(width,height);
    }

    @Override
    public Vector2f getPosition() {
        return position;
    }

    @Override
    public void setPosition(Vector2f position) {
        this.position = position;
    }

    public Vector2f getSize() {
        return size;
    }

    public void setSize(Vector2f size) {
        this.size = size;
    }
}
