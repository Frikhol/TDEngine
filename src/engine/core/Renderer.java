package core;

import entities.Camera;
import entities.GameObject;
import entities.Light;
import entities.Scene;
import entities.components.Model;
import entities.components.Texture;
import org.joml.Matrix4f;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;
import shaders.StaticShader;
import tools.Maths;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static display.GameDisplay.*;
import static org.lwjgl.glfw.GLFW.glfwGetWindowSize;

public class Renderer {

    private static final float FOV = 70;
    private static final float NEAR_PLANE = 0.1f;
    private static final float FAR_PLANE = 1000;
    private static Matrix4f projectionMatrix;
    private static StaticShader shader = new StaticShader();
    private static Map<Model, List<GameObject>> entities = new HashMap<Model, List<GameObject>>();

    public Renderer(){
        GL11.glEnable(GL11.GL_CULL_FACE);
        GL11.glCullFace(GL11.GL_BACK);
        createProjectionMatrix();
        shader.start();
        shader.loadProjectionMatrix(projectionMatrix);
        shader.stop();
    }

    /**
     * Сгрупированные модели и текстуры, рендер по каждому объекту
     * -функция отрисовки одна для каждого объекта
     * -модель и текстура одна для всех одинаковых объектов
     * -предзагрузка моделей
     */

    public static void render(){
        Light light = GameEngine.getCurrentScene().getLight();
        Camera camera = GameEngine.getCurrentScene().getCamera();
        shader.start();
        shader.loadLight(light);
        shader.loadViewMatrix(camera);
        processEntities();
        for(Model model: entities.keySet()){
            prepareTexturedModel(model);
            List<GameObject> batch = entities.get(model);
            for(GameObject gameObject :batch){
                prepareInstance((gameObject));
                GL11.glDrawElements(GL11.GL_TRIANGLES,model.getMesh().getVertexCount(),GL11.GL_UNSIGNED_INT,0);
            }
            unbindTexturedModel();
        }
        shader.stop();
        entities.clear();
    }

    private static void prepareTexturedModel(Model model){
        GL30.glBindVertexArray(model.getMesh().getVaoID());
        GL20.glEnableVertexAttribArray(0);
        GL20.glEnableVertexAttribArray(1);
        GL20.glEnableVertexAttribArray(2);
        Texture texture = model.getTexture();
        shader.loadShineVariables(texture.getShineDamper(),texture.getReflectivity());
        GL13.glActiveTexture(GL13.GL_TEXTURE0);
        GL11.glBindTexture(GL11.GL_TEXTURE_2D, texture.getID());
    }

    private static void unbindTexturedModel(){
        GL20.glDisableVertexAttribArray(0);
        GL20.glDisableVertexAttribArray(1);
        GL20.glDisableVertexAttribArray(2);
        GL30.glBindVertexArray(0);
    }

    private static void processEntities(){
        Scene scene = GameEngine.getCurrentScene();
        for(GameObject gameObject : scene.getGameObjectList()) {
            int prefabID;
            if((prefabID = scene.findModel(gameObject.getModelName())) == -1)
                break;
            Model prefabModel = scene.getPrefabs().get(prefabID);
            List<GameObject> batch = entities.get(prefabModel);
            if (batch != null) {
                batch.add(gameObject);
            } else {
                List<GameObject> newBatch = new ArrayList<GameObject>();
                newBatch.add(gameObject);
                entities.put(prefabModel, newBatch);
            }
        }
    }

    private static void prepareInstance(GameObject gameObject){
        Matrix4f transformationMatrix = Maths.createTransformationMatrix(gameObject.getTransform().getPosition(), gameObject.getTransform().getRotation(), gameObject.getTransform().getScale());
        shader.loadTransformationMatrix(transformationMatrix);
    }

    protected static void createProjectionMatrix(){
        glfwGetWindowSize(getDisplayID(),getDisplayWIDTH(),getDisplayHEIGHT());
        int width = getDisplayWIDTH()[0];
        int height = getDisplayHEIGHT()[0];
        float aspectRatio = (float)width/height;
        projectionMatrix = new Matrix4f();
        projectionMatrix.identity();
        //projectionMatrix.setOrtho2D(-width/2,width/2,-height/2,height/2);
        projectionMatrix.perspective(FOV, aspectRatio, NEAR_PLANE, FAR_PLANE);
    }

    public static void cleanUp(){
        shader.cleanUp();
    }
}
