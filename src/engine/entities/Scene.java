package entities;

import com.fasterxml.jackson.annotation.*;
import entities.components.Model;
import layout.GUI;
import inputs.KeyList;

import java.util.ArrayList;
import java.util.List;

public class Scene {

    private String name;
    private ArrayList<GameObject> gameObjectList = new ArrayList<GameObject>();
    @JsonIgnore private ArrayList<Model> prefabs = new ArrayList<Model>();
    private GUI currentGUI;
    private Light light;
    private Camera camera;
    @JsonIgnore
    private KeyList keyList = null;

    public Scene(){
    }

    public GUI getCurrentGUI() {
        return currentGUI;
    }

    public void setCurrentGUI(GUI currentGUI) {
        this.currentGUI = currentGUI;
    }

    public void setKeyList(KeyList keyList) {
        this.keyList = keyList;
    }

    public KeyList getKeyList(){
        return keyList;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<GameObject> getGameObjectList() {
        return gameObjectList;
    }

    public void setGameObjectList(List<GameObject> gameObjectList) {
        for(GameObject gameObject : gameObjectList){
            if(findModel(gameObject.getModelName())==-1)
                prefabs.add(new Model(gameObject.getModelName()));
            this.gameObjectList.add(gameObject);
            //рекурсивно добавлять child объекты каждого объекта если они есть
        }
    }

    public Light getLight() {
        return light;
    }

    public void setLight(Light light) {
        this.light = light;
    }

    public Camera getCamera() {
        return camera;
    }

    public void setCamera(Camera camera) {
        this.camera = camera;
    }

    public List<Model> getPrefabs() {
        return prefabs;
    }

    public int findModel(String modelName){
        for(int i = 0;i<prefabs.size();i++){
            if(prefabs.get(i).getName().equals(modelName))
                return i;
        }
        return -1;
    }
}