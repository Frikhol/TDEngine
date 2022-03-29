package initialisation;

import core.GameEngine;
import core.MaterialTest;
import entities.GameObject;
import core.Game;
import entities.Tower;
import org.joml.Vector3f;

import java.util.ArrayList;

public class INI {
    public static void main(String[] args) {
        new Game();
        //new MaterialTest();




        new GameProcess(){

            @Override
            public void start() {

            }

            @Override
            public void update() {
                ArrayList<GameObject> objList = GameEngine.getCurrentScene().getGameObjectList();
                for (int i=0;i<objList.size();i++){
                    objList.get(i).Update();
                }
            }
        };
    }
}
