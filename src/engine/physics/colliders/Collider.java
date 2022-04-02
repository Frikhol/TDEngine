package physics.colliders;

import core.entities.GameObject;
import org.joml.Quaternionf;
import org.joml.Vector3f;
import org.joml.Vector4f;

import java.util.ArrayList;

public abstract class Collider {
    private Vector3f size;
    private Vector3f center;
    private Quaternionf rotation;
    
    public Collider(GameObject object){
        resize(object);
        this.center = new Vector3f().zero();
    }

    abstract void resize(GameObject object);

    public Vector3f getSize() {
        return size;
    }

    public Vector3f getCenter() {
        return center;
    }

    public void setCenter(Vector3f center) {
        this.center = center;
    }

    public Quaternionf getRotation() {
        return rotation;
    }

    Vector3f getLocalOptimalRotations(ArrayList<Vector3f> vertices){
        Vector3f up = new Vector3f(0f,1f,0f);
        Vector3f right = new Vector3f(1f,0f,0f);
        Vector3f forward = new Vector3f(0f,0f,1f);
        Vector3f rotationXYZ;
        //Вокруг оси Y



        return new Vector3f();
    }

    private float leastSquaresUp(ArrayList<Vector3f> vertices,float angle,float range){
        float optimalAngle = angle;
        if(range<1)
            return optimalAngle;
        float leftValue=0;
        float rightValue=0;
        float kLeft = (float) Math.tan(Math.toRadians(angle-range));
        float kRight = (float) Math.tan(Math.toRadians(angle+range));
        for(Vector3f vertex : vertices){
            leftValue+=(vertex.z - vertex.x/kLeft)*(vertex.z - vertex.x/kLeft);
            rightValue+=(vertex.z - vertex.x/kRight)*(vertex.z - vertex.x/kRight);
        }
        if(leftValue<rightValue)
            optimalAngle = leastSquaresUp(vertices,angle-range,range/2);
        else
            optimalAngle = leastSquaresUp(vertices,angle+range,range/2);
        return optimalAngle;
    }

}
