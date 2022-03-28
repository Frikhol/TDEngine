package physics.collision;

import entities.GameObject;
import org.joml.Vector3f;
import physics.colliders.Collider;

public class PointToBoxCollision {
    public static boolean check(Vector3f point, GameObject object){
        Collider box = object.getModel().getCollider();
        float minX = object.getPosition().x-box.getSize().x;
        float maxX = object.getPosition().x+box.getSize().x;
        float minY = object.getPosition().y-box.getSize().y;
        float maxY = object.getPosition().y+box.getSize().y;
        float minZ = object.getPosition().z-box.getSize().z;
        float maxZ = object.getPosition().z+box.getSize().z;
        return  (point.x >= minX && point.x <= maxX) &&
                (point.y >= minY && point.y <= maxY) &&
                (point.z >= minZ && point.z <= maxZ);
    }
}
