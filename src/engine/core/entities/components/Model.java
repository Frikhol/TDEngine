package core.entities.components;

import core.render.Loader;

import static core.GameEngine.*;

public class Model {

    private String name;
    private Mesh mesh;
    private Material material;

    public Model(String name) {
        this.name = name;
        Model model;
        if((model = getCurrentScene().findModel(name))==null)
            this.mesh = Loader.loadObjModel(name);
        else
            this.mesh = model.getMesh();
        this.material = Material.findMaterial(name);
        getCurrentScene().getPrefabs().add(this);
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

