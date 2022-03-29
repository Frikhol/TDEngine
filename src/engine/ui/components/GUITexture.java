package ui.components;

import core.loader.Loader;

import java.util.ArrayList;
import java.util.List;

public class GUITexture {
    private String name;
    private int textureID;
    private static List<GUITexture> guiTextures = new ArrayList<>();

    public GUITexture(String name) {
        this.name = name;
        this.textureID = Loader.loadTexture("gui/"+ name +".png").getID();
        guiTextures.add(this);
    }

    public GUITexture(String name,int id){
        this.name = name;
        this.textureID = id;
        guiTextures.add(this);
    }

    public static GUITexture findTexture(String name){
        for(GUITexture texture : guiTextures){
            if(texture.name.equals(name))
                return texture;
        }
        return new GUITexture(name);
    }

    public String getName() {
        return name;
    }

    public int getTextureID() {
        return textureID;
    }
}
