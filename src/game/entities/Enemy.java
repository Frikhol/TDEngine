package entities;

import effects.Effectible;
import org.joml.Vector2f;
import org.joml.Vector3f;
import tools.Time;

public class Enemy extends GameObject implements Effectible {
    private float health;
    private float resist;
    private float positionOnTrack;
    private Track track;
    private float speed;

    public Enemy(float health, float positionOnTrack, Track track, float speed, float resist) {
        super();
        this.health = health;
        this.positionOnTrack = positionOnTrack;
        this.track = track;
        this.speed = speed;
        this.resist = resist;
        Vector2f v2=track.getPosition(positionOnTrack);
        this.getTransform().setPosition(new Vector3f(v2.x,0,v2.y));

    }
    public void tactFrame(){
        move();
        takeDamage(effectMultiplayer("time_damage")*(float)Time.getDeltaTime());
    }
    float getSpeed(){
        return speed*effectMultiplayer("move_speed");
    }
    private void move(){
        positionOnTrack+=getSpeed()* Time.getDeltaTime();
        Vector2f v2p=track.getPosition(positionOnTrack);
        float angle = track.getDirection(positionOnTrack);
        this.getTransform().setPosition(new Vector3f(v2p.x,0,v2p.y));
        this.getTransform().setRotation(new Vector3f(0,angle,0));
    }
    public float getHealth() {
        return health*effectMultiplayer("stamina");
    }
    void takeDamage(float dmg){
        if(dmg<=0.0f)
            return;
        health-=(1.0f-getResist())*dmg/effectMultiplayer("stamina");
    }

    public float getResist() {
        return resist*percentEffectMultiplayer("resist");
    }


    Vector2f getPosition(){
        return track.getPosition(positionOnTrack);
    }
    public static class BaseEnemyTest extends Enemy{

        public BaseEnemyTest(Track track) {
            super(100, 0, track, 3f, 0);
            this.getTransform().scale(0.1f);
        }
    }
}
