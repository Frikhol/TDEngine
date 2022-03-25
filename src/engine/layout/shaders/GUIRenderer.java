package layout.shaders;

import layout.GUIObject;
import core.Loader;
import entities.components.Mesh;
import layout.objects.GUIPane;
import org.joml.Matrix4f;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;
import tools.Maths;

import java.util.List;

public class GUIRenderer {

    private final Mesh quad;
    private GUIShader shader;

    public  GUIRenderer(){
        float[] positions = {-1,1,-1,-1,1,1,1,-1};
        quad = Loader.loadToVAO(positions);
        shader = new GUIShader();
    }

    public void render(List<GUIObject> guiList){
        shader.start();
        GL30.glBindVertexArray(quad.getVaoID());
        GL20.glEnableVertexAttribArray(0);
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA,GL11.GL_ONE_MINUS_SRC_ALPHA);
        GL11.glDisable(GL11.GL_DEPTH_TEST);
        for(GUIObject gui: guiList)
            drawGUI(gui);
        GL11.glEnable(GL11.GL_DEPTH_TEST);
        GL11.glDisable(GL11.GL_BLEND);
        GL20.glDisableVertexAttribArray(0);
        GL30.glBindVertexArray(0);
        shader.stop();
    }

    private void drawGUI(GUIObject guiObject){
        GL13.glActiveTexture(GL13.GL_TEXTURE0);
        GL11.glBindTexture(GL11.GL_TEXTURE_2D,guiObject.getTexture().getTextureID());
        Matrix4f matrix = Maths.createTransformationMatrix(guiObject.getPosition(),guiObject.getScale());
        shader.loadTransformation(matrix);
        shader.loadColour(guiObject.getColorVec4());
        GL11.glDrawArrays(GL11.GL_TRIANGLE_STRIP, 0, quad.getVertexCount());
        if(guiObject instanceof GUIPane) {
            for(GUIObject child : ((GUIPane) guiObject).getGrid().getGridList())
                drawGUI(child);
        }
    }


    /*
    if(guiObject instanceof GUIPane) {
        guiList.addAll(((GUIPane) guiObject).getGrid().getGridList());
    }
    */

    public void cleanUp(){
        shader.cleanUp();
    }

}
