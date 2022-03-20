package entities;

import effects.Effectible;
import org.joml.Vector2f;
import tools.Time;

import java.util.ArrayList;

public class Tower extends GameObject implements Effectible {
    float range;
    float damage;
    float attackSpeed;
    float attackReload=0;
    float critDamage;
    float critChance;
    Bullet bullet;
    //icon
    TargetChoice targetChoice = TargetChoice.FIRST;
    Tower(float range,float damage,float attackSpeed,float critDamage,float critChance,Bullet bulletPrototype){
        this.range = range;
        this.damage = damage;
        this.attackSpeed = attackSpeed;
        this.critDamage = critDamage;
        this.critChance = critChance;
        bullet = bulletPrototype;
    }


    private void shoot(){
        Enemy aimEnemy = aim();
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
        new Bullet(bullet,aimEnemy,damage*effectMultiplayer("damage")).Create();

    }
    private Enemy aim(){
        Vector2f towerPosition = new Vector2f(getTransform().getPosition().x,getTransform().getPosition().z);
        ArrayList<Enemy> enemyList = Enemy.getList();
        Enemy aimEnemy = null;
        for (int i=0;i<enemyList.size();i++){
            Enemy enemy = enemyList.get(i);
            float distance = enemy.getPosition().distance(towerPosition);
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
        shoot();
    }

    @Override
    public void Create() {
        super.Create();
    }

    @Override
    public void Destroy() {
        super.Destroy();
    }
    static class  BaseMagicTower extends Tower{
        BaseMagicTower(){
            super(20,5,1,100,0,new Bullet.BaseMagicBullet(30));

        }
    }
}
