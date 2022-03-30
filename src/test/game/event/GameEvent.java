package game.event;

import entities.GameObject;

import java.util.ArrayList;

/**
 * Simple game event abstract class
 * creates own GameObject list and pushing event on initialised
 */
public abstract class GameEvent {

    private ArrayList<GameObject> gameObjectList;

    /**
     * creates own GameObject list
     */
    public GameEvent(){
        this.gameObjectList = new ArrayList<>();
    }

    /**
     * used to prepare objects in event
     */
    public abstract void start();

    /**
     * used to update objects every frame in game loop
     */
    public abstract void update();

    /**
     * returns GameObject list using in this event
     *
     * @return GameObject list
     */
    public ArrayList<GameObject> getGameObjectList() {
        return gameObjectList;
    }
}
