package core;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import data.deserializers.SceneDeserializer;
import data.serializers.GUIObjectSerializer;
import data.serializers.GameObjectSerializer;
import data.serializers.SceneSerializer;
import data.serializers.TransformSerializer;
import display.GameDisplay;
import entities.GameObject;
import entities.Scene;
import entities.components.Transform;
import font.GUIText;
import font.TextMaster;
import layout.GUIObject;
import layout.shaders.GUIRenderer;
import inputs.InputHandler;
import inputs.TestControls;
import org.joml.Vector2f;
import org.lwjgl.glfw.GLFWWindowSizeCallback;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import static display.GameDisplay.*;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;

public class GameEngine {

    private static Scene scene;
    private static GUIText text;
    private static GUIRenderer guiRenderer; // make static

    public static Scene getCurrentScene() {
        return scene;
    }

    public static void startEngine(){
        createDisplay();
        TextMaster.init(new Loader());
        loadScene("Assets/scenes/test1.json");
        new Renderer();
        guiRenderer = new GUIRenderer(new Loader());
        text = new GUIText("FPS: "+ GameDisplay.getFPS(),1,TextMaster.getFonts().get("calibri"),
                new Vector2f((float)(getDisplayWIDTH()[0]-(getDisplayWIDTH()[0]/16))/getDisplayWIDTH()[0],
                        (float)(getDisplayHEIGHT()[0]/64)/getDisplayHEIGHT()[0]),
                (float)(getDisplayWIDTH()[0]/16)/getDisplayWIDTH()[0],true);
        scene.setKeyList(new TestControls());
        glfwSetKeyCallback(getDisplayID(), InputHandler.keyCallback);
        checkWindowResize();
    }

    public static void loop(){
        setFPSCup(60);
        countFPS();
        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
        glClearColor(0.74902f,  0.847059f, 0.847059f, 0.0f); //background's color
        text.updateText("FPS: "+GameDisplay.getFPS());
        text.setColour(1,0.9f,0);
        render();
        glfwSwapBuffers(getDisplayID()); // Don't delete
        glfwPollEvents();
        InputHandler.getInputs();
    }

    private static void render(){
        Renderer.render();
        guiRenderer.render(scene.getCurrentGUI().getGuiList());
        TextMaster.render();
    }

    public static void stopEngine(){
        saveScene("Assets/scenes/test2.json");
        TextMaster.cleanUp();
        guiRenderer.cleanUp();
        Renderer.cleanUp();
        Loader.cleanUP();
        closeDisplay();
        glfwTerminate();
        glfwSetErrorCallback(null).free();
    }

    private static void checkWindowResize(){
        GLFWWindowSizeCallback sizeCallback = new GLFWWindowSizeCallback() {
            public void invoke(long window, int w, int h) {
                glViewport(0,0,w,h);
                Renderer.createProjectionMatrix();
            }
        };
        glfwSetWindowSizeCallback(getDisplayID(), sizeCallback);
    }

    public static void saveScene(String saveFile){
        ObjectMapper mapper = new ObjectMapper();
        SimpleModule simpleModule = new SimpleModule();

        simpleModule.addSerializer(Scene.class, new SceneSerializer());
        simpleModule.addSerializer(GameObject.class, new GameObjectSerializer());
        simpleModule.addSerializer(GUIObject.class, new GUIObjectSerializer());
        simpleModule.addSerializer(Transform.class, new TransformSerializer());
        mapper.registerModule(simpleModule);

        try {
            mapper.writeValue(new File(saveFile), scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void loadScene(String loadFile){
        ObjectMapper mapper = new ObjectMapper();
        SimpleModule simpleModule = new SimpleModule();

        simpleModule.addDeserializer(Scene.class, new SceneDeserializer());
        mapper.registerModule(simpleModule);

        try {
            scene = mapper.readValue(new File(loadFile), Scene.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}