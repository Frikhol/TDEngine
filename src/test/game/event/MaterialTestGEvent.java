package game.event;

import core.entities.GameObject;
import core.entities.Light;
import org.joml.Vector3f;

import static core.GameEngine.getCurrentScene;

public class MaterialTestGEvent extends GameEvent {
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
        getCurrentScene().addLight(new Light(redObject.getPosition(),new Vector3f(0.2f,0,0),new Vector3f(1,0.01f,0.002f)));
        greenObject = new GameObject("Green");
        greenObject.Create();
        greenObject.setPosition(new Vector3f(-3f,0f,-25f));
        greenObject.scale(0.2f);
        greenObject.rotateY(90);
        redObject.getModel().getMaterial().setAmbientValue(0.3f);
        /*GUIPane some1 = new GUIPane(200,200,100,100);
        getCurrentScene().getCurrentGUI().add(some1);
        some1.setTexture("GUIObject");
        some1.setPadding(10,10);
        GUIButton some2 = new GUIButton("button1");
        GUIButton some3 = new GUIButton("button2");
        GUIButton some4 = new GUIButton("button3");
        some1.setLayout(new GUIGridLayout(1,3,some1));
        some1.add(some2,0,0,1,1);
        some1.add(some3,0,1,1,1);
        some1.add(some4,0,2,1,1);
        GUIPane newPane = some1.clone();
        newPane.setTexture("GUIObject");
        newPane.setColor(Color.cyan);
        some2.addAction(new Action() {
            @Override
            public void actionOnPressed() {
                System.out.println("Action");
                getCurrentScene().getCurrentGUI().add(newPane);
            }

            @Override
            public void actionOnReleased() {
                getCurrentScene().getCurrentGUI().remove(newPane);
            }
        });*/
    }

    @Override
    public boolean update() {
        //whiteObject.rotateY(0.5f);
        //redObject.rotateY(-0.3f);
        greenObject.rotateY(0.3f);
        return true;
    }
}
