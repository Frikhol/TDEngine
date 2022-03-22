package entities.components;

import core.Loader;

public class Material {

    private String name;
    private Texture texture;
    private float ambientValue;
    private float diffuseValue;
    private float smoothness;
    private float specularValue;

    public Material(String name, float ambientValue, float diffuseValue, float smoothness, float specularValue){
        this.name = name;
        this.texture = new Texture(Loader.loadTexture("models/"+name+".png").getID());
        this.ambientValue = ambientValue;
        this.diffuseValue = diffuseValue;
        this.smoothness = smoothness;
        this.specularValue = specularValue;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Texture getTexture() {
        return texture;
    }

    public void setTexture(Texture texture) {
        this.texture = texture;
    }

    public float getAmbientValue() {
        return ambientValue;
    }

    public void setAmbientValue(float ambientValue) {
        this.ambientValue = ambientValue;
    }

    public float getDiffuseValue() {
        return diffuseValue;
    }

    public void setDiffuseValue(float diffuseValue) {
        this.diffuseValue = diffuseValue;
    }

    public float getSmoothness() {
        return smoothness;
    }

    public void setSmoothness(float smoothness) {
        this.smoothness = smoothness;
    }

    public float getSpecularValue() {
        return specularValue;
    }

    public void setSpecularValue(float specularValue) {
        this.specularValue = specularValue;
    }
}
