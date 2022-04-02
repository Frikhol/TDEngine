package entities;

import effects.Effectible;
import org.joml.Matrix4f;
import org.joml.Vector2f;
import org.joml.Vector3f;
import org.joml.Vector4f;
import tools.Maths;
import tools.Time;

import java.util.ArrayList;

public class Tower extends GameObject implements Effectible {
    float range;
    float damage;
    float attackSpeed;
    float attackReload=0;
    float critDamage;
    float critChance;
    Vector3f gunPosition;
    Projectile projectile;
    protected Vector2f lookAt = null;
    //icon
    TargetChoice targetChoice = TargetChoice.FIRST;
    Tower(Vector3f position,float range,float damage,
          float attackSpeed,float critDamage,float critChance,Bullet bulletPrototype, Vector3f gunPosition){
        super();
        this.range = range;
        this.damage = damage;
        this.attackSpeed = attackSpeed;
        this.critDamage = critDamage;
        this.critChance = critChance;
        this.gunPosition = gunPosition;
        getTransform().setPosition(position);
        projectile = bulletPrototype;
    }

    protected void shoot(){
        if(attackReload>0) {
            attackReload -= Time.getDeltaTime();
            attackReload = Math.max(0,attackReload);
        }

        if(attackReload>0)
            return;
        attackReload+=1/attackSpeed;


        Projectile newProjectile = projectile.clone();
        setProjectileValues(newProjectile);
        //newBullet.getTransform().scale(this.getScale());
        newProjectile.Create();

    }
    protected void setProjectileValues(Projectile projectile){
        projectile.setDamage(damage*effectMultiplayer("damage"));
        projectile.getTransform().setPosition(new Vector3f(gunPosition.x, gunPosition.y, gunPosition.z));
        projectile.getTransform().scale(0.1f);
    }
    protected void rotateTower(float angle){
        getTransform().setRotation(new Vector3f(0,angle,0));
    }
    protected void aim(){
        Vector3f bulletPosition = new Vector3f(gunPosition.x, gunPosition.y, gunPosition.z);
        if(lookAt!=null) {
            Vector2f enemyV2f = lookAt;
            Vector2f myV2f = new Vector2f(this.getTransform().getPosition().x, this.getTransform().getPosition().z);
            Vector2f direction = new Vector2f(enemyV2f.x - myV2f.x, enemyV2f.y - myV2f.y).normalize();
            float signum = direction.y == 0 ? 1 : -(direction.y / Math.abs(direction.y));
            float angle = (float) (signum * (Math.acos(direction.x) * 180 / Math.PI));
            rotateTower(angle);
            Maths.createTransformationMatrix(
                    this.getPosition(),
                    new Vector3f(0, angle, 0),
                    this.getScale()
            ).transformPosition(bulletPosition);
        }
    }

    @Override
    public void Update() {
        super.Update();
        aim();
        shoot();
    }

    @Override
    public void Create() {
        super.Create();
        this.scale(0.5f);
    }

    @Override
    public void Destroy() {
        super.Destroy();
    }
    public static class  BaseMagicTower extends BulletTower{
        public BaseMagicTower(Vector3f position){
            super(position,
                    10,
                    10,
                    1,
                    100,
                    0,
                    new Bullet.BaseMagicBullet(15),
                    new Vector3f(1,1.5f,0)
            );
        }
    }
}
