package layout.components;

import core.Loader;
import org.joml.Vector4f;

import java.util.ArrayList;
import java.util.List;

public class GUITexture {
    private String name;
    private int textureID;
    private Color color;
    private static List<GUITexture> guiTextures = new ArrayList<>();

    public GUITexture(String name) {
        this(name,Color.white);
    }

    public GUITexture(String name, Color color) {
        this.name = name;
        this.color = color;
        this.textureID = Loader.loadTexture("gui/"+ name +".png").getID();
        System.out.println(name+"\n"+textureID);
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

    public Color getColor() {
        return color;
    }

    public Vector4f getColorVec4() {
        return color.toVector4f();
    }

    public void setColor(Color color) {
        this.color = color;
    }
}
