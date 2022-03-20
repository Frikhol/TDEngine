package entities;

import static core.GameEngine.*;
import entities.components.Model;
import entities.components.Transform;
import org.joml.Vector3f;

import java.util.ArrayList;
import java.util.List;

public class GameObject {

    private GameObject parent;
    private List<GameObject> childes = new ArrayList<GameObject>();
    private String modelName;
    private Transform transform;

    public GameObject() {
        String className  = this.getClass().getSimpleName();
        System.out.println(className);
        if(className.equals("GameObject"))
            return;
        this.transform = new Transform();
        Scene scene = getCurrentScene();
        if(scene.findModel(this.getModelName())==-1)
            scene.getPrefabs().add(new Model(className));
        modelName = className;
    }

    public GameObject(Model model){
        this.modelName = model.getName();
        this.transform = new Transform();
    }

    public GameObject(Model model,Transform transform){
        this.modelName = model.getName();
        this.transform = transform;
    }

    public GameObject(GameObject parent, Model model){
        this.parent = parent;
        this.modelName = model.getName();
        this.transform = new Transform();
        parent.addChild(this);
    }

    public GameObject(GameObject parent, Model model,Transform transform){
        this.parent = parent;
        this.modelName = model.getName();
        this.transform = transform;
        parent.addChild(this);
    }

    public void addChild(GameObject gameObject){
        childes.add(gameObject);
        gameObject.parent = this;
    }

    public void rotate(Vector3f rotation){
        transform.rotate(rotation);
        for(GameObject child: childes)
            child.getTransform().rotate(rotation);
    }

    public void rotateX(float x){
        transform.rotateX(x);
        for(GameObject child: childes)
            child.getTransform().rotateX(x);
    }

    public void rotateY(float y){
        transform.rotateX(y);
        for(GameObject child: childes)
            child.getTransform().rotateY(y);
    }

    public void rotateZ(float z){
        transform.rotateX(z);
        for(GameObject child: childes)
            child.getTransform().rotateZ(z);
    }

    public void translate(Vector3f translation){
        transform.translate(translation);
        for(GameObject child: childes)
            child.getTransform().translate(translation);
    }

    public void translateX(float x){
        transform.translateX(x);
        for(GameObject child: childes)
            child.getTransform().translateX(x);
    }

    public void translateY(float y){
        transform.translateY(y);
        for(GameObject child: childes)
            child.getTransform().translateY(y);
    }

    public void translateZ(float z){
        transform.translateZ(z);
        for(GameObject child: childes)
            child.getTransform().translateZ(z);
    }

    public void scale(float scale){
        transform.scale(scale);
        for(GameObject child: childes)
            child.getTransform().scale(scale);
    }

    public GameObject getParent() {
        return parent;
    }

    public void setParent(GameObject parent) {
        this.parent = parent;
    }

    public List<GameObject> getChildes() {
        return childes;
    }

    public void setChildes(List<GameObject> childes) {
        this.childes = childes;
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public Transform getTransform() {
        return transform;
    }

    public void setTransform(Transform transform) {
        this.transform = transform;
    }

    public void Create(){
        getCurrentScene().getGameObjectList().add(this);
    }
    public void Destroy(){
        getCurrentScene().getGameObjectList().remove(this);
    }
    public void Update(){

    }
}
