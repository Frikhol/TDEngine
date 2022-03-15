package entities.components;

import core.Loader;

public class Model {

    private String name;
    private Mesh mesh;
    private Texture texture;

    public Model() {
    }

    public Model(String name) {
        this.name = name;
        this.mesh = Loader.loadObjModel(name);
        this.texture = new Texture(Loader.loadTexture("models/"+name+".png").getID());
    }

    public Model(String name,Mesh mesh, Texture texture){
        this.name = name;
        this.mesh = mesh;
        this.texture = texture;
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

    public Texture getTexture() {
        return texture;
    }

    public void setMesh(Mesh mesh) {
        this.mesh = mesh;
    }

    public void setTexture(Texture texture) {
        this.texture = texture;
    }


}

