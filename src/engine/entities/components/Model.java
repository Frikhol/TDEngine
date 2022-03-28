package entities.components;

import core.loader.Loader;
import core.loader.LoadedMeshCollider;
import physics.colliders.Collider;

import static core.GameEngine.*;

public class Model {

    private String name;
    private Mesh mesh;
    private Material material;
    private Collider collider;

    public Model(String name) {
        this.name = name;
        LoadedMeshCollider loaded = Loader.loadObjModel(name);
        this.mesh = loaded.getMesh();
        this.collider = loaded.getCollider();
        this.material = new Material(name,0.1f,1.0f,32,0.5f);
        collider.loadCollider();
        getCurrentScene().getPrefabs().add(this);
    }

    public Model(String name,Mesh mesh,Material material){
        this.name = name;
        this.mesh = mesh;
        this.material = material;
        if(!getCurrentScene().getPrefabs().contains(this))
            getCurrentScene().getPrefabs().add(this);
    }

    public Collider getCollider() {
        return collider;
    }

    public void setCollider(String collider) {
        //TODO this.collider = new Collider(collider);
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

