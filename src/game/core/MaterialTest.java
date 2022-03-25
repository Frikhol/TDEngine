package core;

import entities.GameObject;
import entities.Light;
import initialisation.GameProcess;
import layout.components.Action;
import layout.components.Color;
import layout.objects.GUIButton;
import layout.objects.GUIPane;
import org.joml.Vector3f;

import static core.GameEngine.*;

public class MaterialTest extends GameProcess {
    private GameObject redObject;
    private GameObject greenObject;
    private GameObject whiteObject;
    public void start() {
        whiteObject = new GameObject("Tower");
        whiteObject.Create();
        whiteObject.setPosition(new Vector3f(0f,0f,-25f));
        whiteObject.scale(1f);
        redObject = new GameObject("Red");
        redObject.Create();
        redObject.setPosition(new Vector3f(3f,1f,-25f));
        redObject.scale(0.2f);
        redObject.rotateY(90);
        redObject.getModel().getMaterial().setDiffuseValue(0.2f);
        redObject.getModel().getMaterial().setSpecularValue(0.8f);
        getCurrentScene().getLights().add(new Light(redObject.getPosition(),new Vector3f(0.2f,0,0),new Vector3f(1,0.01f,0.002f)));
        greenObject = new GameObject("Green");
        greenObject.Create();
        greenObject.setPosition(new Vector3f(-3f,0f,-25f));
        greenObject.scale(0.2f);
        greenObject.rotateY(90);
        GUIPane some1 = new GUIPane(100,100,100,300);
        getCurrentScene().getCurrentGUI().add(some1);
        GUIButton some2 = new GUIButton(100,50);
        some2.setColor(Color.white);
        GUIButton some3 = new GUIButton(200,50);
        some3.setColor(Color.white);
        GUIButton some4 = new GUIButton(100,50);
        some4.setColor(Color.white);
        getCurrentScene().getCurrentGUI().add(some1);
        some1.add(some2);
        some1.add(some3);
        some1.add(some4);
        some2.addAction(new Action() {
            @Override
            public void actionOnPressed() {

            }

            @Override
            public void actionOnReleased() {
                System.out.println("Action");
            }
        });
    }

    public void update() {
        whiteObject.rotateY(0.5f);
        redObject.rotateY(-0.3f);
        greenObject.rotateY(0.3f);
    }
}
