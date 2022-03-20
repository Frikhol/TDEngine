package entities;

import effects.Effect;
import org.joml.Vector3f;
import tools.Time;

import java.util.ArrayList;

public class Bullet extends GameObject{
    protected float speed;
    protected float damage;
    protected Enemy target;
    //texture

    Bullet(float speed /*, texture*/){
        speed = speed;
    }
    Bullet(Bullet original, Enemy target, float damage){
        this.speed = original.speed;
        this.target = target;
        this.damage = damage;
    }
    void castEffects(){

    }

    @Override
    public void Create() {
        super.Create();
    }

    @Override
    public void Destroy() {
        super.Destroy();
    }

    @Override
    public void Update() {
        super.Update();
        move();
        if(hit()){
            Destroy();
            if(target.isAlive()){
                castEffects();
                target.takeDamage(damage);

            }
        }
    }
    void move(){
        Vector3f direction = target.getTransform().getPosition().sub(
                getTransform().getPosition()
        ).normalize().mul(
                 speed*(float)Time.getDeltaTime()
        );
        getTransform().getPosition().add(direction);
    }
    boolean hit(){
        if(!target.isAlive())
            return true;
        return target.getTransform().getPosition().distance(getTransform().getPosition())<=1;
    }
    static class BaseMagicBullet extends Bullet{

        BaseMagicBullet(float speed) {
            super(speed);
        }
    }
}
