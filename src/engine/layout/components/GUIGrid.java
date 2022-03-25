package layout.components;

import layout.GUIObject;
import org.joml.Vector2f;

import java.util.ArrayList;
import java.util.List;

public class GUIGrid {

    private List<GUIObject> gridList = new ArrayList<>();

    public GUIGrid() {
    }

    public void add(GUIObject guiObject){
        Vector2f lastPosition = new Vector2f(gridList.get(gridList.size()-1).getPosition());
        Vector2f lastScale = gridList.get(gridList.size()-1).getScale();
        guiObject.setPosition(lastPosition.sub(0,lastScale.y+guiObject.getScale().y));
        gridList.add(guiObject);
    }

    public void remake(){
        for(int i = 1;i<gridList.size();i++){
            Vector2f lastPosition = new Vector2f(gridList.get(i-1).getPosition());
            Vector2f lastScale = gridList.get(i-1).getScale();
            gridList.get(i).setPosition(lastPosition.sub(0,lastScale.y+gridList.get(i).getScale().y));
        }
    }

    public List<GUIObject> getGridList() {
        return gridList;
    }
}
