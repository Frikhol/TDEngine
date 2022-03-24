package layout.objects;

import layout.GUI;
import layout.GUIObject;
import layout.components.GUIGrid;

import static core.GameEngine.getCurrentScene;

public class GUIPane extends GUIObject {
    private GUIGrid grid = new GUIGrid();

    public GUIPane(float posX, float posY, int width, int height) {
        super(GUI.getProperPosition(posX,posY), GUI.getProperScale(width,height));
    }

    public void add(GUIObject guiObject){
        grid.add(guiObject);
    }
}
