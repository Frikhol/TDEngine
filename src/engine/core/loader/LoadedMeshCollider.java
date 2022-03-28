package core.loader;

import entities.components.Mesh;
import physics.colliders.Collider;

public class LoadedMeshCollider {
    private Mesh mesh;
    private Collider collider;

    public LoadedMeshCollider(Mesh mesh, Collider collider) {
        this.mesh = mesh;
        this.collider = collider;
    }

    public Mesh getMesh() {
        return mesh;
    }

    public void setMesh(Mesh mesh) {
        this.mesh = mesh;
    }

    public Collider getCollider() {
        return collider;
    }

    public void setCollider(Collider collider) {
        this.collider = collider;
    }
}
