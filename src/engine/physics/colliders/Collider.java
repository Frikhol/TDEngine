package physics.colliders;

import entities.components.Model;
import entities.components.Transform;
import org.joml.Vector3f;

public abstract class Collider {
    private Vector3f size;
    private Transform transform;
    private Model colliderModel;

    public Collider() {
        size = new Vector3f(0.0f);
    }

    public Vector3f getSize() {
        return size;
    }

    public void setSize(Vector3f size) {
        this.size = size;
    }

    public Model getColliderModel() {
        return colliderModel;
    }

    public void setColliderModel(Model colliderModel) {
        this.colliderModel = colliderModel;
    }

    public Transform getTransform() {
        return transform;
    }

    public void setTransform(Transform transform) {
        this.transform = transform;
    }

    public abstract void loadCollider();

    public abstract void resize(Vector3f vertex);

    public void scale(float scale){
        this.size.mul(scale);
    }
}
