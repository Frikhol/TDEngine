package core;

import entities.GameObject;
import initialisation.GameProcess;
import org.joml.Vector3f;

public class MaterialTest extends GameProcess {
    private GameObject redObject;
    private GameObject greenObject;
    private GameObject whiteObject;
    public void start() {
        whiteObject = new GameObject("White");
        whiteObject.Create();
        whiteObject.setPosition(new Vector3f(0f,0f,-25f));
        whiteObject.scale(0.2f);
        redObject = new GameObject("Red");
        redObject.Create();
        redObject.setPosition(new Vector3f(3f,0f,-25f));
        redObject.scale(0.2f);
        redObject.rotateY(90);
        greenObject = new GameObject("Green");
        greenObject.Create();
        greenObject.setPosition(new Vector3f(-3f,0f,-25f));
        greenObject.scale(0.2f);
        greenObject.rotateY(90);
    }

    public void update() {
        whiteObject.rotateY(0.5f);
    }
}
