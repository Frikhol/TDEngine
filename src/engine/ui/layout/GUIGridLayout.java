package ui.layout;

import org.joml.Vector2i;
import ui.objects.GUIObject;
import ui.layout.components.GUIGridCell;
import ui.objects.GUIPane;

public class GUIGridLayout extends GUILayout{
    private GUIGridCell[][] cellMatrix;
    private int cellCountX;
    private int cellCountY;
    private int cellSizeX;
    private int cellSizeY;

    public GUIGridLayout(int cellCountX, int cellCountY, GUIPane pane){
        super();
        this.cellCountX = cellCountX;
        this.cellCountY = cellCountY;
        this.cellSizeX = (pane.getSize().x-2*(pane.getPadding().x)) / cellCountX;
        this.cellSizeY = (pane.getSize().y-2*(pane.getPadding().y)) / cellCountY;
        this.cellMatrix = new GUIGridCell[cellCountX][cellCountY];
        for(int i = 0;i<cellCountY;i++) {
            for (int j = 0; j < cellCountX; j++) {
                cellMatrix[j][i] = new GUIGridCell(new Vector2i(
                        pane.getLocation().x+pane.getPadding().x + (j * cellSizeX),
                        pane.getLocation().y+pane.getPadding().y + (i * cellSizeY)));
            }
        }
    }

    @Override
    public void add(GUIObject object,int cellX,int cellY,int countX,int countY){
        if(((cellX>cellCountX-1)||(cellX<0))||((cellY>cellCountY-1)||(cellY<0))||((countX>(cellCountX-cellX))||(countX<0))||((countY>(cellCountY-cellY))||(countY<0))) {
            System.err.println("Invalid cell params:\nTry to place to: [" + cellX + "][" + cellY + "] with size (" + countX + ";" + countY + ")");
            return;
        }
        if(collisionException(cellX,cellY,cellX+countX,cellY+countY)){
            System.err.println("Collision Exception:\nTry to place to: [" + cellX + "][" + cellY + "] with size (" + countX + ";" + countY + ")");
            return;
        }
        object.setLocation(new Vector2i(cellMatrix[cellX][cellY].getLocation()));
        object.setSize(new Vector2i(cellSizeX*countX,cellSizeY*countY));
        fillCells(cellX,cellY,cellX+countX-1,cellY+countY-1);
        super.add(object);
    }

    private Vector2i findEmpty(int fromX,int fromY){
        for(int i = fromY;i<cellCountY;i++) {
            for (int j = fromX; j < cellCountX; j++) {
                if(!cellMatrix[j][i].isFilled())
                    return new Vector2i(j,i);
            }
        }
        return null;
    }

    private boolean collisionException(int fromX,int fromY,int toX,int toY){
        for(int i = fromY;i<cellCountY;i++) {
            for (int j = fromX; j < cellCountX; j++) {
                if(cellMatrix[j][i].isFilled())
                    return true;
            }
        }
        return false;
    }

    private void fillCells(int fromX,int fromY,int toX,int toY){
        for(int i = 0;i<cellCountY;i++) {
            for (int j = 0; j < cellCountX; j++) {
                if(((j>=fromX)&&(j<=toX))&&((i>=fromY)&&(i<=toY)))
                    cellMatrix[j][i].setFilled(true);
            }
        }
    }

    private void printGrid(){
        for(int i = 0;i<cellCountY;i++) {
            for (int j = 0; j < cellCountX; j++) {
                System.err.print("[" + cellMatrix[j][i].getLocation().x + "][" + cellMatrix[j][i].getLocation().y + "]["+(cellMatrix[j][i].isFilled()?"f":"e")+"] ");
            }
            System.err.print("\n");
        }
    }

}
