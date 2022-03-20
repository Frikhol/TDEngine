package initialisation;

import core.GameEngine;
import entities.GameObject;
import core.Game;

import java.util.ArrayList;

public class INI {
    public static void main(String[] args) {
        new Game();




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
