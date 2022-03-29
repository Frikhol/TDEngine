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
import physics.colliders.Collider;
import shaders.StaticShader;
import shadows.ShadowMapMasterRenderer;
import tools.Maths;
import ui.GUI;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static core.GameEngine.*;
import static display.GameDisplay.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.glfw.GLFW.glfwGetWindowSize;

public class Renderer {

    public static final float FOV = 70;
    public static final float NEAR_PLANE = 0.1f;
    public static final float FAR_PLANE = 1000;
    private static Matrix4f projectionMatrix;
    private static StaticShader shader = new StaticShader();
    private static Map<Model, List<GameObject>> entities = new HashMap<Model, List<GameObject>>();
    private static ShadowMapMasterRenderer shadowMapRenderer;

    public Renderer(){
        GL11.glEnable(GL11.GL_CULL_FACE);
        GL11.glCullFace(GL11.GL_BACK);
        createProjectionMatrix();
        shader.start();
        shader.loadProjectionMatrix(projectionMatrix);
        shader.stop();
        shadowMapRenderer = new ShadowMapMasterRenderer(getCurrentScene().getCamera());
    }

    /**
     * Сгрупированные модели и текстуры, рендер по каждому объекту
     * -функция отрисовки одна для каждого объекта
     * -модель и текстура одна для всех одинаковых объектов
     * -предзагрузка моделей
     */

    public static void render(){
        List<Light> lights = GameEngine.getCurrentScene().getLights();
        Camera camera = GameEngine.getCurrentScene().getCamera();
        shader.start();
        shader.loadLights(lights);
        shader.loadViewMatrix(camera);
        shader.connectTextureUnits();
        shader.loadToShadowMapSpaceMatrix(shadowMapRenderer.getToShadowMapSpaceMatrix());
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
        if(GUI.isColliderVisibility()) {
            for (Model model : entities.keySet()) {
                boolean changed = false;
                if (GUI.getPolygonMode() == GL_FILL) {
                    GUI.changePolyMode();
                    changed = true;
                }
                prepareTexturedModel(model.getCollider().getColliderModel());
                List<GameObject> batch = entities.get(model);
                for (GameObject gameObject : batch) {
                    prepareInstance((gameObject));
                    GL11.glDrawElements(GL11.GL_TRIANGLES, model.getMesh().getVertexCount(), GL11.GL_UNSIGNED_INT, 0);
                }
                if (changed)
                    GUI.changePolyMode();
                unbindTexturedModel();
            }
        }
        shader.stop();
        entities.clear();
    }

    private static void prepareTexturedModel(Model model){
        GL30.glBindVertexArray(model.getMesh().getVaoID());
        GL20.glEnableVertexAttribArray(0);
        GL20.glEnableVertexAttribArray(1);
        GL20.glEnableVertexAttribArray(2);
        Texture texture = model.getMaterial().getTexture();
        shader.loadMaterialVariables(
                model.getMaterial().getAmbientValue(),
                model.getMaterial().getDiffuseValue(),
                model.getMaterial().getSmoothness(),
                model.getMaterial().getSpecularValue()
                );
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
            if((prefabID = scene.findModel(gameObject.getModel())) == -1)
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
        Matrix4f transformationMatrix = Maths.createTransformationMatrix(gameObject.getPosition(), gameObject.getRotation(), gameObject.getScale());
        shader.loadTransformationMatrix(transformationMatrix);
    }

    protected static void createProjectionMatrix(){
        glfwGetWindowSize(getDisplayID(),getDisplayWIDTH(),getDisplayHEIGHT());
        int width = getDisplayWIDTH()[0];
        int height = getDisplayHEIGHT()[0];
        float aspectRatio = (float)width/height;
        projectionMatrix = new Matrix4f();
        projectionMatrix.identity();
        projectionMatrix.perspective(FOV, aspectRatio, NEAR_PLANE, FAR_PLANE);
    }

    public static void renderShadowMap(){
        processEntities();
        shadowMapRenderer.render(entities,getCurrentScene().getLights().get(0));
        entities.clear();
    }

    public static int getShadowMapTexture(){
        return shadowMapRenderer.getShadowMap();
    }

    public static void cleanUp() {
        shader.cleanUp();
        shadowMapRenderer.cleanUp();
    }

    public static Matrix4f getProjectionMatrix() {
        return projectionMatrix;
    }
}
