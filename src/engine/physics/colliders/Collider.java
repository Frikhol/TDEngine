package physics.colliders;

import core.entities.GameObject;
import org.joml.Matrix4f;
import org.joml.Quaternionf;
import org.joml.Vector3f;
import org.joml.Vector4f;
import tools.Maths;

import java.util.ArrayList;

public abstract class Collider {
    private Vector3f size;
    private Vector3f center;
    private Quaternionf rotation;

    public Collider(GameObject object) {
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

    private static Vector3f getLocalOptimalRotations(ArrayList<Vector3f> vertices) {
        return new Vector3f(
                Maths.leastSquares2f(Maths.toVector2fListYZ(vertices), 0, 90),
                Maths.leastSquares2f(Maths.toVector2fListXZ(vertices), 0, 90),
                Maths.leastSquares2f(Maths.toVector2fListXY(vertices), 0, 90));
    }

    public static Vector3f setLocalMaxSizes(ArrayList<Vector3f> vertices){
        Vector3f rotation = getLocalOptimalRotations(vertices);
        Matrix4f transformRotationMatrix = new Matrix4f();
        transformRotationMatrix.rotateX((float) Math.toRadians(rotation.x));
        transformRotationMatrix.rotateY((float) Math.toRadians(rotation.y));
        transformRotationMatrix.rotateZ((float) Math.toRadians(rotation.z));
        Vector3f size = new Vector3f().zero();
        for(Vector3f vertex : vertices){
            Vector4f localVertex4f = new Vector4f(vertex.x,vertex.y,vertex.z,1f).mul(new Matrix4f(transformRotationMatrix));
            if((size.x - Math.abs(localVertex4f.x)<0))
                size.x = Math.abs(localVertex4f.x);
            if((size.y - Math.abs(localVertex4f.y)<0))
                size.y = Math.abs(localVertex4f.y);
            if((size.z - Math.abs(localVertex4f.z)<0))
                size.z = Math.abs(localVertex4f.z);
        }
        return size;
    }

    public static Vector3f setWorldMaxSizes(ArrayList<Vector3f> vertices){
        Vector3f size = new Vector3f().zero();
        for(Vector3f vertex : vertices){
            if((size.x - Math.abs(vertex.x)<0))
                size.x = Math.abs(vertex.x);
            if((size.y - Math.abs(vertex.y)<0))
                size.y = Math.abs(vertex.y);
            if((size.z - Math.abs(vertex.z)<0))
                size.z = Math.abs(vertex.z);
        }
        return size;
    }


}
