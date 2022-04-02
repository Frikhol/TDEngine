package entities;

import org.joml.Vector3f;
import static core.GameEngine.*;
import tools.Time;

public class Bullet extends Projectile{

    protected Enemy target;
    //texture



    public void setTarget(Enemy target) {
        this.target = target;
    }

    Bullet(float speed /*, texture*/){
        super(speed);
    }

    @Override
    public Projectile clone() {
        Bullet clone = new Bullet(this.speed);
        clone.setTarget(this.target);
        return clone;
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
    protected void makeDamage() {
        makeTargetDamage(target);
    }

    @Override
    protected boolean destroyCondition() {
        return hit();
    }


    @Override
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

    @Override
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
