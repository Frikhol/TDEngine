package game.initialisation;

import game.event.GameEvent;
import game.event.MaterialTest;

import java.util.ArrayList;
import java.util.List;

import static game.processor.GameEventProcessor.*;
import static core.GameEngine.*;
import static display.GameDisplay.*;
import static org.lwjgl.glfw.GLFW.glfwWindowShouldClose;

public class Initialise implements Runnable{
    private static List<GameEvent> gameEventList = new ArrayList<>();

    public static void main(String[] args) {
        gameEventList.add(new MaterialTest());
        new Initialise().run();
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
