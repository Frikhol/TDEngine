package game.processor;

import entities.GameObject;
import game.event.GameEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Double buffered main GameEventProcessor
 * used to start processing game events
 */

public class GameEventProcessor {

    private static boolean pause = false;

    private static Queue<GameEvent> first = new ConcurrentLinkedQueue<>();
    private static Queue<GameEvent> second = new ConcurrentLinkedQueue<>();

    /**
     * updating game events and their game objects
     */

    public static void processGameEvents(){
        if(!pause){
            swap();
            for(GameEvent event = second.poll(); event!=null; event = second.poll()){
               event.update();
               ArrayList<GameObject> objList = event.getGameObjectList();
               if(!objList.isEmpty()) {
                   for(int i = 0; i < objList.size(); i++) {
                       objList.get(i).Update();
                   }
               }
               first.add(event);
            }
        }
    }

    /**
     * swapping current GameEventProcessor buffers
     */

    private static void swap(){
        Queue<GameEvent> temp = first;
        first = second;
        second = temp;
    }

    /**
     * pushing all game events of game
     * @param gameEventList - game events list of game
     */
    public static void loadGameEvents(List<GameEvent> gameEventList){
        for(GameEvent gameEvent : gameEventList)
            pushGameEvent(gameEvent);
    }

    /**
     * used for first push GameEvent
     *
     * @param event - GameEvent to push to game.processor
     */

    public static void pushGameEvent(GameEvent event){
        event.start();
        first.add(event);
    }

    /**
     * returns true if there are already pushed some GameEvents
     *
     * @return - true if there are already pushed some GameEvents
     */

    public static boolean hasGameEvents(){
        return !(first.isEmpty() && second.isEmpty());
    }

    /**
     * returns true if paused
     *
     * @return true if game was paused
     */
    public static boolean isPause() {
        return pause;
    }

    /**
     * sets pause state(T|F)
     *
     * @param pause - boolean for game pause state
     */
    public static void setPause(boolean pause) {
        GameEventProcessor.pause = pause;
    }
}
