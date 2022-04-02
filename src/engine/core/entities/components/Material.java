package core.entities.components;

import core.render.Loader;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Material {

    private String name;
    private int textureId;
    private float ambientValue;
    private float diffuseValue;
    private float smoothness;
    private float specularValue;
    private static List<Material> materialList = new ArrayList<>();
    private static Map<String,Integer> textures = new HashMap<String,Integer>();

    public Material(String name, float ambientValue, float diffuseValue, float smoothness, float specularValue){
        this.name = name;
        this.textureId = findTexture(name);
        this.ambientValue = ambientValue;
        this.diffuseValue = diffuseValue;
        this.smoothness = smoothness;
        this.specularValue = specularValue;
        materialList.add(this);
    }

    public static Material findMaterial(String name){
        for(Material material : materialList){
            if(material.name.equals(name))
                return material;
        }
        return new Material(name,0.1f,1.0f,32,0.5f);
    }

    public static int findTexture(String name){
        Integer texId;
        if((texId = textures.get(name))==null) {
            texId = Loader.loadTexture("models/"+name+".png");
            textures.put(name,texId);
        }
        return texId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTextureId() {
        return textureId;
    }

    public void setTexture(String name) {
        this.textureId = findTexture(name);
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
