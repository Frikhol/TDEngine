package game.initialisation;

import game.event.GameEvent;
import game.event.MaterialTestGEvent;

import java.util.ArrayList;
import java.util.List;

import static game.event.processor.GameEventProcessor.*;
import static core.GameEngine.*;
import static display.GameDisplay.*;
import static org.lwjgl.glfw.GLFW.glfwWindowShouldClose;

/**
 * Starting the game loop
 */

public class Initialise implements Runnable{
    private static List<GameEvent> gameEventList = new ArrayList<>();
    static private Thread th = null;
    private Initialise(){

    }
    public static void main(String[] args) {
        gameEventList.add(new MaterialTestGEvent());
        th = new Thread(new Initialise());
        th.start();
    }

    @Override
    public void run() {
        startEngine();
        loadGameEvents(gameEventList);
        while(!glfwWindowShouldClose(getDisplayID())){
            processGameEvents();
            loop();
        }
        stopEngine();
    }
}
