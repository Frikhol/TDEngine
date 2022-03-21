package entities;

import core.GameEngine;
import effects.Effectible;
import org.joml.Vector2f;
import org.joml.Vector3f;
import tools.Time;

import java.util.ArrayList;

public class Enemy extends GameObject implements Effectible {
    private float health;
    private float resist;
    private float positionOnTrack;
    private Track track;
    private float speed;
    private float damage =1;
    private static ArrayList<Enemy> aliveEnemy = new ArrayList<>();
    private boolean alive = false;

    public Enemy(float health, float positionOnTrack, Track track, float speed, float resist) {
        super();
        this.health = health;
        this.positionOnTrack = positionOnTrack;
        this.track = track;
        this.speed = speed;
        this.resist = resist;
        Vector2f v2=track.getPosition(positionOnTrack);
        this.setPosition(new Vector3f(v2.x,0,v2.y));

    }


    private void move(){
        positionOnTrack+=getSpeed()* Time.getDeltaTime();
        if((track.getPathLength()-positionOnTrack)*(track.getPathLength()-positionOnTrack)<=1.0){
            GameEngine.getCurrentScene().getGameObjectList().remove(this);
            Player.damaged(damage * effectMultiplayer("damage"));
        }
        Vector2f v2p=track.getPosition(positionOnTrack);
        float angle = track.getDirection(positionOnTrack);
        this.setPosition(new Vector3f(v2p.x,0,v2p.y));
        this.setRotation(new Vector3f(0,angle,0));
    }

    public void takeDamage(float dmg){
        if(dmg<=0.0f)
            return;
        health-=(1.0f-getResist())*dmg/effectMultiplayer("stamina");
    }

    protected void setDamage(float newDamage){
        damage +=newDamage;
    }

    public float getResist() {
        return resist*percentEffectMultiplayer("resist");
    }
    Vector2f getTrackPosition(){
        return track.getPosition(positionOnTrack);
    }
    public float getPositionOnTrack() {
        return positionOnTrack;
    }
    public float getSpeed(){
        return speed*effectMultiplayer("move_speed");
    }
    public float getHealth() {
        return health*effectMultiplayer("stamina");
    }
    public boolean isAlive(){
        return alive;
    }

    public static ArrayList<Enemy> getList(){
        return aliveEnemy;
    }

    @Override
    public void Update(){
        super.Update();
        move();
        takeDamage(effectSumma("time_damage")*(float)Time.getDeltaTime());
    }

    @Override
    public void Create(){
        super.Create();
        aliveEnemy.add(this);
        alive = true;
    }

    @Override
    public void Destroy(){
        super.Destroy();
        aliveEnemy.remove(this);
        alive = false;
    }

    public static class BaseEnemyTest extends Enemy{

        public BaseEnemyTest(Track track) {
            super(100, 0, track, 3f, 0);
            this.scale(0.1f);
        }
    }
}
