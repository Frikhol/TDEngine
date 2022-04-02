package ui.components;

import core.render.Loader;

import java.util.ArrayList;
import java.util.List;

public class GUITexture {
    private String name;
    private int textureID;
    private static List<GUITexture> guiTextures = new ArrayList<>();

    public GUITexture(String name) {
        this.name = name;
        this.textureID = Loader.loadTexture("gui/"+ name +".png");
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
