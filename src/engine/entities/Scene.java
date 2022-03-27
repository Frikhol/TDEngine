package entities;

import entities.components.Model;
import ui.GUI;
import inputs.InputList;

import java.util.ArrayList;
import java.util.List;

public class Scene {

    private String name;
    private ArrayList<GameObject> gameObjectList = new ArrayList<GameObject>();
    private ArrayList<Model> prefabs = new ArrayList<Model>();
    private GUI currentGUI;
    private List<Light> lights = new ArrayList<>();
    private Camera camera;
    private InputList inputList = null;

    public Scene(){
    }

    public GUI getCurrentGUI() {
        return currentGUI;
    }

    public void setCurrentGUI(GUI currentGUI) {
        this.currentGUI = currentGUI;
    }

    public void setInputList(InputList inputList) {
        this.inputList = inputList;
    }

    public InputList getInputList(){
        return inputList;
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

    public void setGameObjectList(ArrayList<GameObject> gameObjectList) {
        this.gameObjectList = gameObjectList;
    }

    public void add(GameObject gameObject) {
        this.gameObjectList.add(gameObject);
    }

    public void remove(GameObject gameObject) {
        this.gameObjectList.remove(gameObject);
    }

    public List<Light> getLights() {
        return lights;
    }

    public void setLights(List<Light> lights) {
        this.lights = lights;
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

    public int findModel(Model model){
        for(int i = 0;i<prefabs.size();i++){
            if(prefabs.get(i).equals(model))
                return i;
        }
        return -1;
    }

    public Model findModel(String modelName){
        for(Model model:prefabs){
            if(model.getName().equals(modelName))
                return model;
        }
        return null;
    }


}