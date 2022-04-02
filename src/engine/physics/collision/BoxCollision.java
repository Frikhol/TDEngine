package physics.collision;

import entities.GameObject;
import org.joml.Matrix4f;
import org.joml.Vector3f;

public class BoxCollision {

    public boolean toCircle(GameObject circle,GameObject box){
        return true;
    }

    private Vector3f toLocalCoords(Vector3f worldCoords){
        Matrix4f localTransformationMatrix = new Matrix4f();
        //localTransformationMatrix.
        return null;
    }
}
