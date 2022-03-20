package entities.components;

public class Texture {

    private int textureID;

    private float shineDamper = 20;
    private float reflectivity = 0.3f;

    public Texture(int textureID){
        this.textureID = textureID;
    }

    public int getID(){
        return textureID;
    }

    public float getShineDamper() {
        return shineDamper;
    }

    public void setShineDamper(float shineDamper) {
        this.shineDamper = shineDamper;
    }

    public float getReflectivity() {
        return reflectivity;
    }

    public void setReflectivity(float reflectivity) {
        this.reflectivity = reflectivity;
    }
}
