package physics.raycast;

import entities.GameObject;
import org.joml.Vector2f;
import org.joml.Vector3f;
import physics.colliders.Collider;
import physics.collision.PointCollision;

import static core.GameEngine.getCurrentScene;

public class RayCastHit {
    private static final float D = 10000f;

    public static GameObject getNearest(){
        Vector3f start = new Vector3f(getCurrentScene().getCamera().getTransform().getPosition());
        System.out.println(start.x+" "+start.y+" "+start.z);
        float minDistance = 10000f;
        GameObject bufObject = null;
        Vector3f near;
        for(GameObject gameObject : getCurrentScene().getGameObjectList())
            if((near = getBoxHitNearPoint(gameObject))!=null) {
                if(start.distance(near)<minDistance) {
                    minDistance = Math.min(start.distance(near), minDistance);
                    bufObject = gameObject;
                }
            }
        return bufObject;
    }

    //TODO заменить gameobject на BoxCollider
    public static boolean isBoxHit(GameObject object){
        Vector3f start = new Vector3f(getCurrentScene().getCamera().getTransform().getPosition());
        return ((getBoxHitFactors(object,start))!=null);
    }

    private static Vector3f getBoxHitNearPoint(GameObject object){
        Vector3f start = new Vector3f(getCurrentScene().getCamera().getTransform().getPosition());
        Vector3f end = getScaledRay(start);
        Vector2f factors;
        if((factors= getBoxHitFactors(object,start))==null)
            return null;
        float nFactor = factors.x;
        return new Vector3f(start.x+nFactor*(end.x-start.x),start.y+nFactor*(end.y-start.y),start.z+nFactor*(end.z-start.z));
    }

    private static Vector2f getBoxHitFactors(GameObject object, Vector3f start) {
        Collider box = object.getModel().getCollider();
        float minX = object.getPosition().x-box.getSize().x;
        float maxX = object.getPosition().x+box.getSize().x;
        float minY = object.getPosition().y-box.getSize().y;
        float maxY = object.getPosition().y+box.getSize().y;
        float minZ = object.getPosition().z-box.getSize().z;
        float maxZ = object.getPosition().z+box.getSize().z;
        Vector3f end = getScaledRay(start);
        if(PointCollision.toBox(start,object))
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
        return new Vector2f(nFactor,fFactor);
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
        return new Vector3f(RayCast.getCurrentRay().x*D, RayCast.getCurrentRay().y*D, RayCast.getCurrentRay().z*D).add(start);
    }
}
