package entities;

import org.joml.Vector3f;
import tools.Maths;
import tools.Time;

public class Bullet extends GameObject{
    protected float speed;
    protected float damage;
    protected Enemy target;
    //texture

    public void setDamage(float damage) {
        this.damage = damage;
    }

    public void setTarget(Enemy target) {
        this.target = target;
    }

    Bullet(float speed /*, texture*/){
        super();
        this.speed = speed;
    }
    public Bullet clone() {
        Bullet clone = new Bullet(this.speed);
        clone.setDamage(this.damage);
        clone.setTarget(this.target);
        return clone;
    }
    Bullet(Vector3f position, Bullet original, Enemy target, float damage){
        super();
        this.speed = original.speed;
        this.target = target;
        this.damage = damage;
        getTransform().setPosition(position);
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
        Vector3f direction = new Vector3f(
                target.getTransform().getPosition().x-getTransform().getPosition().x,
                target.getTransform().getPosition().y-getTransform().getPosition().y,
                target.getTransform().getPosition().z-getTransform().getPosition().z
        ).normalize().mul(
                 speed*(float)Time.getDeltaTime()
        );
        getTransform().getPosition().add(direction);
    }
    boolean hit(){
        if(!target.isAlive())
            return true;
        return target.getTransform().getPosition().distance(getTransform().getPosition())<=0.1;
    }
    static class BaseMagicBullet extends Bullet{

        BaseMagicBullet(float speed) {
            super(speed);
        }

        @Override
        public Bullet clone() {
            BaseMagicBullet clone = new BaseMagicBullet(this.speed);
            clone.target = target;
            clone.damage = damage;
            return clone;
        }
    }
}
