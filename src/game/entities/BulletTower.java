package entities;

import org.joml.Vector2f;
import org.joml.Vector3f;

import java.util.ArrayList;

public class BulletTower extends Tower{
    Enemy targetEnemy = null;
    BulletTower(Vector3f position, float range, float damage, float attackSpeed, float critDamage, float critChance, Bullet bulletPrototype, Vector3f gunPosition) {
        super(position, range, damage, attackSpeed, critDamage, critChance, bulletPrototype, gunPosition);
    }
    @Override
    protected void aim(){
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
        targetEnemy = aimEnemy;
        if(aimEnemy==null)
            lookAt = null;
        else
            lookAt = new Vector2f(aimEnemy.getPosition().x,aimEnemy.getPosition().z);
        super.aim();
    }

    @Override
    protected void setProjectileValues(Projectile projectile) {
        super.setProjectileValues(projectile);
        ((Bullet)projectile).setTarget(targetEnemy);
    }

    @Override
    protected void shoot() {
        if(targetEnemy == null){
            return;
        }
        super.shoot();
    }
}
