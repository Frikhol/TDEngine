package ui.shaders;

import org.joml.Matrix4f;
import org.joml.Vector4f;
import core.render.shaders.ShaderProgram;

public class GUIShader extends ShaderProgram {

    private static final String VERTEX_FILE = "src/engine/ui/shaders/GUIVertexShader.txt";
    private static final String FRAGMENT_FILE = "src/engine/ui/shaders/GUIFragmentShader.txt";

    private int location_transformationMatrix;
    private int location_color;

    public GUIShader() {
        super(VERTEX_FILE, FRAGMENT_FILE);
    }

    public void loadTransformation(Matrix4f matrix){
        super.loadMatrix(location_transformationMatrix, matrix);
    }

    @Override
    protected void getAllUniformLocations() {
        location_transformationMatrix = super.getUniformLocation("transformationMatrix");
        location_color = super.getUniformLocation("guiColor");
    }

    @Override
    protected void bindAttributes() {
        super.bindAttribute(0, "position");
    }

    public void loadColour(Vector4f color){
        super.load4DVector(location_color,color);
    }

}