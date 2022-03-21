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
    Bullet bullet;
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
        bullet = bulletPrototype;
    }

    private void shoot(Enemy aimEnemy, Vector3f bulletPosition){
        if(attackReload>0) {
            attackReload -= Time.getDeltaTime();
            attackReload = Math.max(0,attackReload);
        }
        if(aimEnemy == null){
            return;
        }
        if(attackReload>0)
            return;
        attackReload+=1/attackSpeed;


        Bullet newBullet = bullet.clone();
        newBullet.setTarget(aimEnemy);
        newBullet.setDamage(damage*effectMultiplayer("damage"));
        newBullet.getTransform().setPosition(bulletPosition);
        newBullet.getTransform().setScale(0.1f);
        //newBullet.getTransform().scale(this.getScale());
        newBullet.Create();

    }
    protected void rotateTower(float angle){
        getTransform().setRotation(new Vector3f(0,angle,0));
    }
    private Enemy aim(){
        Vector2f towerPosition = new Vector2f(getTransform().getPosition().x,getTransform().getPosition().z);
        ArrayList<Enemy> enemyList = Enemy.getList();
        Enemy aimEnemy = null;
        for (int i=0;i<enemyList.size();i++){
            Enemy enemy = enemyList.get(i);
            float distance = enemy.getTrackPosition().distance(towerPosition);
            if(distance<=range){
                if(aimEnemy == null) {
                    aimEnemy = enemy;
                    continue;
                }
                switch (targetChoice){
                    case FIRST:
                        if(enemy.getPositionOnTrack()>=aimEnemy.getPositionOnTrack())
                            aimEnemy=enemy;
                        break;
                    case LAST:
                        if(enemy.getPositionOnTrack()<aimEnemy.getPositionOnTrack())
                            aimEnemy=enemy;
                        break;
                    case LOW:
                        if(enemy.getHealth()<aimEnemy.getHealth())
                            aimEnemy=enemy;
                        break;
                    case TALL:
                        if(enemy.getHealth()>=aimEnemy.getHealth())
                            aimEnemy=enemy;
                        break;
                }
            }
        }
        return aimEnemy;
    }

    @Override
    public void Update() {
        super.Update();
        Enemy aimEnemy = aim();
        Vector3f bulletPosition = new Vector3f(gunPosition.x, gunPosition.y, gunPosition.z);
        if(aimEnemy!=null) {
            Vector2f enemyV2f = new Vector2f(aimEnemy.getTransform().getPosition().x, aimEnemy.getTransform().getPosition().z);
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
        shoot(aimEnemy,bulletPosition);
    }

    @Override
    public void Create() {
        super.Create();
    }

    @Override
    public void Destroy() {
        super.Destroy();
    }
    public static class  BaseMagicTower extends Tower{
        public BaseMagicTower(Vector3f position){
            super(position,
                    10,
                    5,
                    1,
                    100,
                    0,
                    new Bullet.BaseMagicBullet(5),
                    new Vector3f(1,1.5f,0)
            );
            this.scale(0.5f);

        }
    }
}
