package game.initialisation;

import game.event.MaterialTest;

import static game.processor.GameEventProcessor.*;
import static core.GameEngine.*;
import static display.GameDisplay.*;
import static org.lwjgl.glfw.GLFW.glfwWindowShouldClose;

public class Initialise implements Runnable{
    public static void main(String[] args) {
        new Initialise().run();
    }

    @Override
    public void run() {
        startEngine();
        new MaterialTest();
        while(!glfwWindowShouldClose(getDisplayID())){
            processGameEvents();
            loop();
        }
        stopEngine();
    }
}
