package core.entities.components;

import org.joml.Vector3f;
public class Transform {

    private Vector3f position;
    private Vector3f rotation;
    private float scale;

    public Transform() {
        this.position = new Vector3f(0.0f,0.0f,0.0f);
        this.rotation = new Vector3f(0.0f,0.0f,0.0f);
        this.scale = 1.0f;
    }

    public Transform(Vector3f position, Vector3f rotation, float scale) {
        this.position = new Vector3f(position);
        this.rotation = new Vector3f(rotation);
        this.scale = scale;
    }

    public Transform(Vector3f position, Vector3f rotation) {
        this.position = new Vector3f(position);
        this.rotation = new Vector3f(rotation);
        this.scale = 1.0f;
    }

    public void rotate(Vector3f rotation){
        if(Math.abs(this.rotation.x += rotation.x) > 360.0f)
            this.rotation.x = 0f;
        if(Math.abs(this.rotation.y += rotation.y) > 360.0f)
            this.rotation.y = 0f;
        if(Math.abs(this.rotation.z += rotation.z) > 360.0f)
            this.rotation.z = 0f;
    }

    public void rotateX(float x){
        if(Math.abs(this.rotation.x += x) > 360.0f)
            this.rotation.x = 0f;
    }

    public void rotateY(float y){
        if(Math.abs(this.rotation.y += y) > 360.0f)
            this.rotation.y = 0f;
    }

    public void rotateZ(float z){
        if(Math.abs(this.rotation.z += z) > 360.0f)
            this.rotation.z = 0f;
    }

    public void translate(Vector3f translation){
        this.position.x += translation.x;
        this.position.y += translation.y;
        this.position.z += translation.z;
    }

    public void translateX(float x){ this.position.x += x;}

    public void translateY(float y){ this.position.y += y;}

    public void translateZ(float z){ this.position.z += z;}

    public void scale(float scale){ this.scale *= scale;}

    public Vector3f getPosition() {
        return new Vector3f(position);
    }

    public void setPosition(Vector3f position) {
        this.position = new Vector3f(position);
    }

    public Vector3f getRotation() {
        return new Vector3f(rotation);
    }

    public void setRotation(Vector3f rotation) {
        this.rotation = new Vector3f(rotation);
    }

    public float getScale() {
        return scale;
    }

    public void setScale(float scale) {
        this.scale = scale;
    }


}

