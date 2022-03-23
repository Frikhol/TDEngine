package layout.objects;

import layout.GUI;
import layout.GUIObject;

public class GUIPane extends GUIObject {

    public GUIPane(){}

    public GUIPane(int posX, int posY, int width, int height) {
        super("GUIPane", GUI.getProperPosition(posX,posY), GUI.getProperScale(width,height));
        System.out.println("GUIPane deserialized");
    }
}
