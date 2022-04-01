package physics.colliders;

import entities.components.Material;
import entities.components.Model;
import org.joml.Vector2f;
import org.joml.Vector3f;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import static core.loader.Loader.loadToVAO;

public class BoxCollider extends Collider{

    public BoxCollider(){
        super();
    }

    @Override
    public void resize(Vector3f vertex) {
        if((getSize().x-Math.abs(vertex.x))<0)
            getSize().x = Math.abs(vertex.x);
        if((getSize().y-Math.abs(vertex.y))<0)
            getSize().y = Math.abs(vertex.y);
        if((getSize().z-Math.abs(vertex.z))<0)
            getSize().z = Math.abs(vertex.z);
    }

    @Override
    public void loadCollider(){
        super.loadCollider();
    }
}
