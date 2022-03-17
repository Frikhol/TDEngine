package font;

import core.Loader;
import font.components.FontType;
import font.components.TextMeshData;
import org.lwjgl.opengl.GL30;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TextMaster {

    private static Map<FontType, List<GUIText>> texts = new HashMap<FontType, List<GUIText>>();
    private static FontRenderer renderer;
    private static Map<String,FontType> fonts = new HashMap<String, FontType>();

    public static Map<String, FontType> getFonts() {
        return fonts;
    }

    public static void init(Loader theLoader){
        renderer = new FontRenderer();
        FontType font = new FontType(Loader.loadTexture("fonts/calibri.png").getID(),new File("Assets/fonts/calibri.fnt"));
        fonts.put("calibri",font);
    }

    public static void render(){
        renderer.render(texts);
    }

    public static void loadText(GUIText text){
        FontType font = text.getFont();
        TextMeshData data = font.loadText(text);
        int vao = Loader.loadToVAO(data.getVertexPositions(),data.getTextureCoords(),text.getMeshVboList());
        text.setMeshInfo(vao,data.getVertexCount());
        List<GUIText> textBatch = texts.get(font);
        if(textBatch == null){
            textBatch = new ArrayList<GUIText>();
            texts.put(font,textBatch);
        }
        textBatch.add(text);
    }

    public static void removeText(GUIText text){
        for(int vbo:text.getMeshVboList())
            GL30.glDeleteBuffers(vbo);
        GL30.glDeleteVertexArrays(text.getMesh());
        List<GUIText> textBatch = texts.get(text.getFont());
        textBatch.remove(text);
        if(textBatch.isEmpty()){
            texts.remove(text.getFont());
        }
    }

    public static void cleanUp(){
        renderer.cleanUp();
    }
}
