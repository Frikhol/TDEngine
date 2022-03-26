package layout.objects;

import layout.GUI;
import layout.GUIObject;
import layout.components.GUIGrid;
import org.joml.Vector2f;
import org.joml.Vector2i;
import static display.GameDisplay.*;
import static core.GameEngine.*;

public class GUIPane extends GUIObject {
    private GUIGrid grid = new GUIGrid();
    private Vector2i location;
    private Vector2i size;

    public GUIPane(GUIPane old){
        this(old.location.x,old.location.y,old.size.x,old.size.y);
    }

    public GUIPane(int width,int height){
        this(0,0,width,height);
    }

    public GUIPane(int posX, int posY, int width, int height) {
        super(GUI.getProperPosition(posX,posY,width,height),GUI.getProperScale(width,height));
        this.location = new Vector2i(posX,posY);
        this.size = new Vector2i(width,height);
    }

    public void add(GUIObject guiObject){
        if(guiObject.getScale().x>this.getScale().x)
            this.setSize(new Vector2i((int)(guiObject.getScale().x*getDisplayWIDTH()[0]),this.getSize().y));
        if(grid.getGridList().size()==0) {
            guiObject.setPosition(new Vector2f(this.getPosition().x,this.getPosition().y+this.getScale().y-guiObject.getScale().y-(0.03f*this.getScale().y)));
            grid.getGridList().add(guiObject);
            if(getCurrentScene().getCurrentGUI().getGuiList().contains(this))
                getCurrentScene().getCurrentGUI().add(guiObject);
            return;
        }
        grid.add(guiObject);
        if(getCurrentScene().getCurrentGUI().getGuiList().contains(this))
            getCurrentScene().getCurrentGUI().add(guiObject);
        float sum = 0;
        for(GUIObject guiInGrid : grid.getGridList())
            sum+=guiInGrid.getScale().y;
        if(sum>this.getScale().y)
            this.setSize(new Vector2i(this.getSize().x, (int) ((sum+(0.03f*this.getScale().y))*getDisplayHEIGHT()[0])));
    }

    public GUIGrid getGrid() {
        return grid;
    }

    public Vector2i getLocation() {
        return location;
    }

    public void setLocation(Vector2i location) {
        setPosition(GUI.getProperPosition(location.x,location.y,size.x,size.y));
        resetLocation(location);
        remake();
    }

    public void resetLocation(Vector2i location) {
        this.location = location;
    }

    public Vector2i getSize() {
        return size;
    }

    public void setSize(Vector2i size) {
        setScale(GUI.getProperScale(size.x,size.y));
        setPosition(GUI.getProperPosition(location.x,location.y,size.x,size.y));
        resetSize(size);
        remake();
    }

    public void resetSize(Vector2i size) {
        this.size = size;
    }

    private void remake(){
        if(!grid.getGridList().isEmpty()){
            grid.getGridList().get(0).setPosition(new Vector2f(this.getPosition().x,this.getPosition().y+this.getScale().y-grid.getGridList().get(0).getScale().y-(0.03f*this.getScale().y)));
            grid.remake();
        }
    }
}
