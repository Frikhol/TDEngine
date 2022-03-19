package gameCore;

import core.GameEngine;
import entities.Enemy;
import entities.Track;
import initialisation.GameProcess;
import org.joml.Vector2f;
import org.joml.Vector3f;
import tools.Time;

import static core.GameEngine.getCurrentScene;

public class Game extends GameProcess {
    Enemy enemy1;
    public void start(){
        Track track = new Track(
                new Vector2f(-20,-36.5f),
                new Vector2f(-12,-36.5f),
                new Vector2f(-12,-21.5f),
                new Vector2f(-4,-21.5f),
                new Vector2f(-4,-36.5f),
                new Vector2f(4,-36.5f),
                new Vector2f(4,-21.5f),
                new Vector2f(12,-21.5f),
                new Vector2f(12,-36.5f),
                new Vector2f(20,-36.5f)
                );
        enemy1 = new Enemy.BaseEnemyTest(track);
        GameEngine.getCurrentScene().getGameObjectList().add(enemy1);
    }
    public void update(){
        //getCurrentScene().getGameObjectList().get(0).rotate(new Vector3f(0,20f*(float) Time.getDeltaTime(),0));
        enemy1.tactFrame();
    }

}
