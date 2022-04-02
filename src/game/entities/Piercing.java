package entities;

import org.joml.Vector3f;
import tools.Time;

import java.util.ArrayList;

public class Piercing extends Projectile{
    protected Vector3f direction;
    protected int targetCount=1;
    private ArrayList<Enemy> targetEnemy = new ArrayList<>();
    public void setDirection(Vector3f direction) {
        this.direction = direction;
    }
    Piercing (float speed){
        super(speed);
    }

    @Override
    public Projectile clone() {
        Piercing clone = new Piercing(speed);
        clone.setDirection(direction);
        return clone;
    }

    @Override
    protected void makeDamage() {
        for(int i=0;i<targetEnemy.size();i++){
            if(targetCount == 0)
                return;
            Enemy target = targetEnemy.get(i);
            makeTargetDamage(target);
        }
    }

    @Override
    protected boolean destroyCondition() {
        return targetCount==0;
    }

    @Override
    void move() {
        setPosition(getPosition().add(direction.mul(speed* Time.getDeltaTime())));
    }

    @Override
    boolean hit() {

        targetEnemy.clear();
        for (int i=0;i<Enemy.getList().size();i++){
            Enemy target = Enemy.getList().get(i);
            if(true /*если коснулся объекта*/){
                targetEnemy.add(target);
            }
        }
        return !targetEnemy.isEmpty();
    }
}
