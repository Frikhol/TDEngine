package core;

import entities.Enemy;
import entities.Tower;
import entities.Track;
import initialisation.GameProcess;
import org.joml.Vector2f;
import org.joml.Vector3f;

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
        enemy1.Create();
        new Tower.BaseMagicTower(new Vector3f(0,1.5f,-25)).Create();
    }
    public void update(){
    }

}
