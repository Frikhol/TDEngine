package entities.components;

import core.Loader;

import static core.GameEngine.*;

public class Model {

    private String name;
    private Mesh mesh;
    private Material material;

    public Model(String name) {
        this.name = name;
        this.mesh = Loader.loadObjModel(name);
        this.material = new Material(name,0.1f,1.0f,32,0.5f);
        getCurrentScene().getPrefabs().add(this);
    }

    public Model(String name,Mesh mesh, Material material){
        this.name = name;
        this.mesh = mesh;
        this.material = material;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Mesh getMesh() {
        return mesh;
    }

    public Material getMaterial() {
        return material;
    }

    public void setMaterial(Material material) {
        this.material = material;
    }

    public void setMesh(Mesh mesh) {
        this.mesh = mesh;
    }
}

