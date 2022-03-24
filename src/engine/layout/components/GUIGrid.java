package layout.components;

import layout.GUI;
import layout.GUIObject;
import org.joml.Vector2f;

import java.util.ArrayList;
import java.util.List;

public class GUIGrid {

    private List<GUIObject> gridList = new ArrayList<>();

    public GUIGrid() {
    }

    public void add(GUIObject guiObject){
        if(gridList.size()==0) {
            gridList.add(guiObject);
            return;
        }
        Vector2f lastPosition = gridList.get(gridList.size()-1).getPosition();
        Vector2f lastScale = gridList.get(gridList.size()-1).getScale();
        guiObject.setPosition(lastPosition.add(0,lastScale.y));
        guiObject.setPosition(lastPosition);
    }
}
