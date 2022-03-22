package shaders;

import entities.Camera;
import entities.Light;
import org.joml.Matrix4f;
import tools.Maths;

public class StaticShader extends ShaderProgram{

    private static final String VERTEX_FILE = "src/engine/shaders/vertexShader.txt";
    private static final String FRAGMENT_FILE = "src/engine/shaders/fragmentShader.txt";

    private int location_transformationMatrix;
    private int location_projectionMatrix;
    private int location_viewMatrix;
    private int location_lightPosition;
    private int location_lightColour;
    private int location_ambientValue;
    private int location_diffuseValue;
    private int location_smoothness;
    private int location_specularValue;

    public StaticShader(){
        super(VERTEX_FILE,FRAGMENT_FILE);
    }

    @Override
    protected void bindAttributes() {
        super.bindAttribute(0,"position");
        super.bindAttribute(1,"textureCoords");
        super.bindAttribute(2,"normal");
    }

    @Override
    protected void getAllUniformLocations() {
        location_transformationMatrix = super.getUniformLocation("transformationMatrix");
        location_projectionMatrix = super.getUniformLocation("projectionMatrix");
        location_viewMatrix = super.getUniformLocation("viewMatrix");
        location_lightPosition = super.getUniformLocation("lightPosition");
        location_lightColour = super.getUniformLocation("lightColour");
        location_ambientValue = super.getUniformLocation("ambientValue");
        location_diffuseValue = super.getUniformLocation("diffuseValue");
        location_smoothness = super.getUniformLocation("smoothness");
        location_specularValue = super.getUniformLocation("specularValue");
    }

    public void loadMaterialVariables(float ambientValue,float diffuseValue,float smoothness,float specularValue){
        super.loadFloat(location_ambientValue,ambientValue);
        super.loadFloat(location_diffuseValue,diffuseValue);
        super.loadFloat(location_smoothness,smoothness);
        super.loadFloat(location_specularValue,specularValue);
    }

    public void loadTransformationMatrix(Matrix4f matrix){
        super.loadMatrix(location_transformationMatrix, matrix);
    }

    public void loadProjectionMatrix(Matrix4f projection){
        super.loadMatrix(location_projectionMatrix, projection);
    }

    public void loadViewMatrix(Camera camera){
        Matrix4f viewMatrix = Maths.createViewMatrix(camera);
        super.loadMatrix(location_viewMatrix, viewMatrix);
    }

    public void loadLight(Light light){
        super.loadVector(location_lightPosition,light.getPosition());
        super.loadVector(location_lightColour,light.getColour());
    }

}

