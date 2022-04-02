package physics.collision;

import core.entities.GameObject;
import org.joml.Vector3f;
import physics.colliders.Collider;

public abstract class PointCollision {

//TODO заменить на коллайдер
    public static boolean toBox(Vector3f point, GameObject object){
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
