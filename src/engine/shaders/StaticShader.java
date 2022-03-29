package shaders;

import entities.Camera;
import entities.Light;
import org.joml.Matrix4f;
import org.joml.Vector3f;
import tools.Maths;

import java.util.List;

public class StaticShader extends ShaderProgram{

    private static final int MAX_LIGHTS = 4;

    private static final String VERTEX_FILE = "src/engine/shaders/vertexShader.txt";
    private static final String FRAGMENT_FILE = "src/engine/shaders/fragmentShader.txt";

    private int location_transformationMatrix;
    private int location_projectionMatrix;
    private int location_viewMatrix;
    private int location_lightPosition[];
    private int location_lightColour[];
    private int location_attenuation[];
    private int location_ambientValue;
    private int location_diffuseValue;
    private int location_smoothness;
    private int location_specularValue;
    private int location_toShadowMapSpace;
    private int location_shadowMap;

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
        location_ambientValue = super.getUniformLocation("ambientValue");
        location_diffuseValue = super.getUniformLocation("diffuseValue");
        location_smoothness = super.getUniformLocation("smoothness");
        location_specularValue = super.getUniformLocation("specularValue");
        location_toShadowMapSpace = super.getUniformLocation("toShadowMapSpace");
        location_shadowMap = super.getUniformLocation("shadowMap");

        location_lightPosition = new int[MAX_LIGHTS];
        location_lightColour = new int[MAX_LIGHTS];
        location_attenuation = new int[MAX_LIGHTS];
        for(int i =0;i<MAX_LIGHTS;i++){
            location_lightPosition[i] = super.getUniformLocation("lightPosition["+i+"]");
            location_lightColour[i] = super.getUniformLocation("lightColour["+i+"]");
            location_attenuation[i] = super.getUniformLocation("attenuation["+i+"]");
        }
    }

    public void connectTextureUnits(){
        super.loadInt(location_shadowMap,1);
    }

    public void loadToShadowMapSpaceMatrix(Matrix4f matrix){
        super.loadMatrix(location_toShadowMapSpace,matrix);
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

    public void loadLights(List<Light> lights){
        for(int i=0;i<MAX_LIGHTS;i++){
            if(i<lights.size()){
                super.loadVector(location_lightPosition[i],lights.get(i).getPosition());
                super.loadVector(location_lightColour[i],lights.get(i).getColour());
                super.loadVector(location_attenuation[i],lights.get(i).getAttenuation());
            }
            else{
                super.loadVector(location_lightPosition[i],new Vector3f().zero());
                super.loadVector(location_lightColour[i],new Vector3f().zero());
                super.loadVector(location_attenuation[i], new Vector3f(1,0,0));
            }
        }
    }

}

