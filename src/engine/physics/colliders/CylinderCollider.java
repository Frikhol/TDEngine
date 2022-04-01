package physics.colliders;

import org.joml.Vector3f;

public class CylinderCollider extends Collider{

    public CylinderCollider(){
        super();
    }

    @Override
    public void loadCollider(){
        super.loadCollider();
    }

    @Override
    public void resize(Vector3f vertex) {
        float range = (float) Math.sqrt(vertex.x*vertex.x +vertex.z*vertex.z);
        if(range>getRange()){
            getSize().x = range;
            getSize().z = range;
        }
        if((getSize().y-Math.abs(vertex.y))<0)
            getSize().y = Math.abs(vertex.y);
    }

    float getRange(){
        return getSize().x;
    }
}
