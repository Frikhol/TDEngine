package physics.collision;

import entities.GameObject;
import org.joml.Vector3f;
import physics.MousePicker;
import physics.colliders.Collider;

import static core.GameEngine.getCurrentScene;

public class RayToBoxCollision {
    private static final float D = 1000f;

    public static boolean check(GameObject object){
        Collider box = object.getModel().getCollider();
        System.out.println("box size: x="+box.getSize().x+" y="+box.getSize().y+" z="+box.getSize().z);
        float minX = object.getPosition().x-box.getSize().x;
        float maxX = object.getPosition().x+box.getSize().x;
        float minY = object.getPosition().y-box.getSize().y;
        float maxY = object.getPosition().y+box.getSize().y;
        float minZ = object.getPosition().z-box.getSize().z;
        float maxZ = object.getPosition().z+box.getSize().z;
        Vector3f start = new Vector3f(getCurrentScene().getCamera().getTransform().getPosition());
        Vector3f end = getScaledRay(start);
        if(PointToBoxCollision.check(start,object))
            return false;
        if ((((start.x < minX)) && ((end.x < minX))) ||
                (((start.x > maxX)) && ((end.x > maxX))) ||
                (((start.y < minY)) && ((end.y < minY))) ||
                (((start.y > maxY)) && ((end.y > maxY))) ||
                (((start.z < minZ)) && ((end.z < minZ))) ||
                (((start.z > maxZ)) && ((end.z > maxZ))))
            return false;
        Vector3f near = new Vector3f(
                getNFactor(start.x,end.x,minX,maxX),
                getNFactor(start.y,end.y,minY,maxY),
                getNFactor(start.z,end.z,minZ,maxZ));
        Vector3f far = new Vector3f(
                getFFactor(start.x,end.x,minX,maxX),
                getFFactor(start.y,end.y,minY,maxY),
                getFFactor(start.z,end.z,minZ,maxZ));
        if((far.x<near.y)||(far.x<near.z)||(far.y<near.z))
            return false;
        return true;
    }

    public static GameObject getNearest(){
        Vector3f start = new Vector3f(getCurrentScene().getCamera().getTransform().getPosition());
        float minDistance = 1000f;
        GameObject bufObject = null;
        Vector3f near;
        Vector3f lastNear = new Vector3f(1);
        for(GameObject gameObject : getCurrentScene().getGameObjectList())
            if((near = getNearestPoint(gameObject,start))!=null) {
                if(start.distance(near)<minDistance) {
                    minDistance = Math.min(start.distance(near), minDistance);
                    bufObject = gameObject;
                    lastNear = near;
                }
            }
        //System.out.println("object pos: x="+bufObject.getPosition().x+" y="+bufObject.getPosition().y+" z="+bufObject.getPosition().z);
        //System.out.println("box size: x="+bufObject.getModel().getCollider().getSize().x+" y="+bufObject.getModel().getCollider().getSize().y+" z="+bufObject.getModel().getCollider().getSize().z);
        //System.out.println("near: x="+lastNear.x+" y="+lastNear.y+" z="+lastNear.z+" minDistance: "+minDistance);
        return bufObject;
    }

    private static Vector3f getNearestPoint(GameObject object,Vector3f start) {
        Collider box = object.getModel().getCollider();
        float minX = object.getPosition().x-box.getSize().x;
        float maxX = object.getPosition().x+box.getSize().x;
        float minY = object.getPosition().y-box.getSize().y;
        float maxY = object.getPosition().y+box.getSize().y;
        float minZ = object.getPosition().z-box.getSize().z;
        float maxZ = object.getPosition().z+box.getSize().z;
        Vector3f end = getScaledRay(start);
        if(PointToBoxCollision.check(start,object))
            return null;
        if ((((start.x < minX)) && ((end.x < minX))) ||
                (((start.x > maxX)) && ((end.x > maxX))) ||
                (((start.y < minY)) && ((end.y < minY))) ||
                (((start.y > maxY)) && ((end.y > maxY))) ||
                (((start.z < minZ)) && ((end.z < minZ))) ||
                (((start.z > maxZ)) && ((end.z > maxZ))))
            return null;
        Vector3f near = new Vector3f(
                getNFactor(start.x,end.x,minX,maxX),
                getNFactor(start.y,end.y,minY,maxY),
                getNFactor(start.z,end.z,minZ,maxZ));
        Vector3f far = new Vector3f(
                getFFactor(start.x,end.x,minX,maxX),
                getFFactor(start.y,end.y,minY,maxY),
                getFFactor(start.z,end.z,minZ,maxZ));
        if((far.x<near.y)||(far.x<near.z)||(far.y<near.z))
            return null;
        float nFactor = Math.max(Math.max(near.x,near.y),near.z);
        float fFactor = Math.min(Math.min(far.x,far.y),far.z);
        return new Vector3f(start.x+nFactor*(end.x-start.x),start.y+nFactor*(end.y-start.y),start.z+nFactor*(end.z-start.z));
    }

    private static float getFFactor(float v0, float v1, float min, float max) {
        if(v1>v0)
            return Math.abs(max-v0)/Math.abs(v1-v0);
        if (v1<v0)
            return  Math.abs(min-v0)/Math.abs(v1-v0);
        return v1;
    }

    private static float getNFactor(float v0, float v1, float min, float max) {
        if(v0<min)
            return Math.abs(min-v0)/Math.abs(v1-v0);
        else if(v0>min&&v0<max)
            return 0.0f;
        return Math.abs(max-v0)/Math.abs(v1-v0);
    }

    private static Vector3f getScaledRay(Vector3f start){
        return new Vector3f(MousePicker.getCurrentRay().x*D,MousePicker.getCurrentRay().y*D,MousePicker.getCurrentRay().z*D).add(start);
    }
}
