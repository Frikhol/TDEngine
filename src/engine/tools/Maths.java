package tools;

import core.entities.Camera;
import org.joml.Matrix4f;
import org.joml.Vector2f;
import org.joml.Vector3f;

import java.util.ArrayList;
import java.util.List;

public class Maths {

    public static Matrix4f createTransformationMatrix(Vector2f translation, Vector2f scale){
        Matrix4f matrix = new Matrix4f();
        matrix.identity();
        matrix.translate(new Vector3f(translation.x,translation.y,.0f));
        matrix.scale(new Vector3f(scale.x,scale.y,1f));
        return matrix;
    }

    public static Matrix4f createTransformationMatrix(Vector3f translation, Vector3f rotation, float scale){
        Matrix4f matrix = new Matrix4f();
        matrix.identity();
        matrix.translate(translation);
        matrix.rotateX((float) Math.toRadians(rotation.x));
        matrix.rotateY((float) Math.toRadians(rotation.y));
        matrix.rotateZ((float) Math.toRadians(rotation.z));
        matrix.scale(scale);
        return matrix;
    }
    public static Matrix4f createViewMatrix(Camera camera){
        Matrix4f viewMatrix = new Matrix4f();
        viewMatrix.identity();
        viewMatrix.rotateX((float) Math.toRadians(camera.getPitch()));
        viewMatrix.rotateY((float) Math.toRadians(camera.getYaw()));
        Vector3f cameraPos = camera.getTransform().getPosition();
        Vector3f negativeCameraPos = new Vector3f(-cameraPos.x,-cameraPos.y,-cameraPos.z);
        viewMatrix.translate(negativeCameraPos);
        return viewMatrix;
    }

    /**
     * return optimal angle of approximation in current axes pane with least squares method
     * @param vertices - list of vertices in current axes plane
     * @param angle - start angle
     * @param range - start range
     * @return optimal angle of approximation in current axes plane
     */
    public static float leastSquares2f(ArrayList<Vector2f> vertices, float angle, float range){
        float optimalAngle = angle;
        if(range<0.1f) {
            return optimalAngle;
        }
        float leftValue=0;
        float rightValue=0;
        float kLeft = (float) Math.tan(Math.toRadians(angle-range));
        float kRight = (float) Math.tan(Math.toRadians(angle+range));
        for(Vector2f vertex : vertices){
            leftValue+=(vertex.y - vertex.x/kLeft)*(vertex.x - vertex.y/kLeft);
            rightValue+=(vertex.y - vertex.x/kRight)*(vertex.x - vertex.y/kRight);
        }
        if(leftValue<rightValue)
            optimalAngle = leastSquares2f(vertices,angle-range,range/2);
        else
            optimalAngle = leastSquares2f(vertices,angle+range,range/2);
        return optimalAngle;
    }


    /* TODO Fix
    public static float leastDistance2f(ArrayList<Vector2f> vertices,Vector3f normal,Vector3f direction, float angle,float range){
        float optimalAngle = angle;
        if(range<1f)
            return optimalAngle;
        float leftValue=0;
        float rightValue=0;
        Vector3f leftVector = new Vector3f(direction).rotateAxis((float) Math.toRadians(-range),normal.x,normal.y,normal.z);
        Vector3f rightVector = new Vector3f(direction).rotateAxis((float) Math.toRadians(+range),normal.x,normal.y,normal.z);
        for(Vector2f vertex : vertices){
            leftValue += (vertex.length()*Math.sin(new Vector2f(leftVector.x,leftVector.z).angle(vertex)));
            rightValue += (vertex.length()*Math.sin(new Vector2f(rightVector.x,rightVector.z).angle(vertex)));
        }
        if(leftValue<rightValue)
            optimalAngle = leastDistance2f(vertices,leftVector,normal,angle-range,range/2);
        else
            optimalAngle = leastDistance2f(vertices,rightVector,normal,angle+range,range/2);
        return optimalAngle;
    }*/

    public static ArrayList<Vector2f> toVector2fListXY(ArrayList<Vector3f> vector3fArrayList){
        ArrayList<Vector2f> vector2fArrayList = new ArrayList<>();
        for(Vector3f vector : vector3fArrayList)
            vector2fArrayList.add(new Vector2f(vector.x,vector.y));
        return vector2fArrayList;
    }

    public static ArrayList<Vector2f> toVector2fListXZ(ArrayList<Vector3f> vector3fArrayList){
        ArrayList<Vector2f> vector2fArrayList = new ArrayList<>();
        for(Vector3f vector : vector3fArrayList)
            vector2fArrayList.add(new Vector2f(vector.x,-vector.z));
        return vector2fArrayList;
    }

    public static ArrayList<Vector2f> toVector2fListYZ(ArrayList<Vector3f> vector3fArrayList){
        ArrayList<Vector2f> vector2fArrayList = new ArrayList<>();
        for(Vector3f vector : vector3fArrayList)
            vector2fArrayList.add(new Vector2f(vector.y,-vector.z));
        return vector2fArrayList;
    }
}
