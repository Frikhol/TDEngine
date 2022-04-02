package core.entities;

import static core.GameEngine.*;
import core.entities.components.Model;
import core.entities.components.Transform;
import org.joml.Vector3f;
import physics.colliders.Collider;

import java.util.ArrayList;
import java.util.List;

public class GameObject {

    private String name;
    private Model model;
    private Collider collider;
    private Transform transform;
    private GameObject parent;
    private List<GameObject> childes = new ArrayList<GameObject>();

    public GameObject() {
        this.transform = new Transform();
    }

    public GameObject(String name){
        this.name = name;
        this.transform = new Transform();
    }

    public GameObject(String name, Model model, Transform transform){
        this.name = name;
        this.model = model;
        this.transform = transform;
    }

    public void Create() {
        getCurrentScene().add(this);
        if(this.name == null)
            this.name = this.getClass().getSimpleName();
        if(this.name.equals("GameObject"))
            return;
        if((this.model = getCurrentScene().findModel(this.name)) == null)
            this.model = new Model(this.name);
    }

    public void Destroy() {
        getCurrentScene().remove(this);
    }

    public void Update() {
    }

    public String getName() {
        return name;
    }

    public Model getModel() {
        return model;
    }

    public Transform getTransform() {
        return transform;
    }

    public GameObject getParent() {
        return parent;
    }

    public List<GameObject> getChildes() {
        return childes;
    }

    public Vector3f getPosition() {
        return transform.getPosition();
    }

    public Vector3f getRotation() {
        return transform.getRotation();
    }

    public float getScale() {
        return transform.getScale();
    }

    public void setPosition(Vector3f position){
        this.transform.setPosition(position);
    }

    public void setRotation(Vector3f rotation){
        this.transform.setRotation(rotation);
    }

    public void setScale(float scale){
        this.transform.setScale(scale);
    }

    public void addChild(GameObject gameObject){
        childes.add(gameObject);
        gameObject.parent = this;
    }

    public void rotate(Vector3f rotation){
        transform.rotate(rotation);
        for(GameObject child: childes)
            child.rotate(rotation);
    }

    public void rotateX(float x){
        transform.rotateX(x);
        for(GameObject child: childes)
            child.rotateX(x);
    }

    public void rotateY(float y){
        transform.rotateY(y);
        for(GameObject child: childes)
            child.rotateY(y);
    }

    public void rotateZ(float z){
        transform.rotateZ(z);
        for(GameObject child: childes)
            child.rotateZ(z);
    }

    public void translate(Vector3f translation){
        transform.translate(translation);
        for(GameObject child: childes)
            child.translate(translation);
    }

    public void translateX(float x){
        transform.translateX(x);
        for(GameObject child: childes)
            child.translateX(x);
    }

    public void translateY(float y){
        transform.translateY(y);
        for(GameObject child: childes)
            child.translateY(y);
    }

    public void translateZ(float z){
        transform.translateZ(z);
        for(GameObject child: childes)
            child.translateZ(z);
    }

    public void scale(float scale){
        transform.scale(scale);
        for(GameObject child: childes)
            child.scale(scale);
    }

}
