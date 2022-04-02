package core;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import core.render.Loader;
import core.render.Renderer;
import data.deserializers.GUIObjectDeserializer;
import data.deserializers.SceneDeserializer;
import data.serializers.GUIObjectSerializer;
import data.serializers.GameObjectSerializer;
import data.serializers.SceneSerializer;
import data.serializers.TransformSerializer;
import core.display.GameDisplay;
import physics.raycast.RayCast;
import core.entities.GameObject;
import core.entities.Scene;
import core.entities.components.Transform;
import ui.font.GUIText;
import ui.font.TextMaster;
import inputs.CursorInputHadler;
import inputs.MouseInputHandler;
import ui.objects.GUIObject;
import ui.shaders.GUIRenderer;
import inputs.KeyInputHandler;
import inputs.DefaultControls;
import org.joml.Vector2f;
import org.lwjgl.glfw.GLFWWindowSizeCallback;

import java.io.File;
import java.io.IOException;

import static core.display.GameDisplay.*;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL13.*;

public class GameEngine {

    private static Scene scene;
    private static GUIText text;
    private static GUIRenderer guiRenderer; //TODO make static
    RayCast picker;

    public static Scene getCurrentScene() {
        return scene;
    }
    public static void setCurrentScene(Scene scene) {
        GameEngine.scene = scene;
    }

    public static void startEngine(){
        createDisplay();
        TextMaster.init();
        loadScene("Assets/scenes/GameTestScene.json");
        new Renderer();
        guiRenderer = new GUIRenderer();
        preparePausePane();
        text = new GUIText("FPS: "+ GameDisplay.getFPS(),1,TextMaster.getFonts().get("calibri"),
                new Vector2f((float)(getDisplayWIDTH()[0]-(getDisplayWIDTH()[0]/16))/getDisplayWIDTH()[0],
                        (float)(getDisplayHEIGHT()[0]/64)/getDisplayHEIGHT()[0]),
                (float)(getDisplayWIDTH()[0]/16)/getDisplayWIDTH()[0],true);
        scene.setInputList(new DefaultControls());
        glfwSetKeyCallback(getDisplayID(), KeyInputHandler.keyCallback);
        glfwSetMouseButtonCallback(getDisplayID(), MouseInputHandler.mouseButtonCallback);
        glfwSetCursorPosCallback(getDisplayID(), CursorInputHadler.cursorPosCallback);
        checkWindowResize();
        new RayCast();
        /*GUIPane test = new GUIPane(0,0,500,500);
        test.setTexture("someTest",Renderer.getShadowMapTexture());
        getCurrentScene().getCurrentGUI().add(test);*/
    }

    public static void loop(){
        setFPSCup(144);
        countFPS();
        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
        glClearColor(0.74902f,  0.847059f, 0.847059f, 0.0f); //background's color
        Renderer.renderShadowMap();
        glActiveTexture(GL_TEXTURE1);
        glBindTexture(GL_TEXTURE_2D,Renderer.getShadowMapTexture());
        text.updateText("FPS: "+GameDisplay.getFPS());
        text.setColour(1,0.9f,0);
        render();
        glfwSwapBuffers(getDisplayID()); // Don't delete
        glfwPollEvents();
        KeyInputHandler.getInputs();
        MouseInputHandler.getInputs();
        CursorInputHadler.cursorInputs();
        RayCast.update();
    }

    private static void render(){
        Renderer.render();
        guiRenderer.render(scene.getCurrentGUI().getGuiList());
        TextMaster.render(text);
    }

    public static void stopEngine(){
        //saveScene("Assets/scenes/GameTestScene1.json");
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
                for(GUIObject guiObject : getCurrentScene().getCurrentGUI().getGuiList())
                    guiObject.reset();
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
        simpleModule.addDeserializer(GUIObject.class, new GUIObjectDeserializer());
        mapper.registerModule(simpleModule);

        try {
            scene = mapper.readValue(new File(loadFile), Scene.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}