package physics.colliders;

import core.entities.GameObject;
import org.joml.Vector3f;

import java.util.ArrayList;

public class BoxCollider extends Collider{

    public BoxCollider(GameObject gameObject){
        super(gameObject);
    }

    @Override
    void resize(GameObject object) {
        ArrayList<Vector3f> vertices = object.getModel().getMesh().getVertices();

    }
}
