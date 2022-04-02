package entities;

public abstract class Projectile extends GameObject{
    protected float speed;
    protected float damage;
    public void setDamage(float damage) {
        this.damage = damage;
    }
    public Projectile(float speed){
        this.speed = speed;
    }
    public abstract Projectile clone();
    protected abstract void makeDamage();
    protected void makeTargetDamage(Enemy enemy){
        if(enemy.isAlive()) {
            enemy.takeDamage(damage);
            castEffects(enemy);
        }
    }
    private void castEffects(Enemy enemy){

    }
    protected abstract boolean destroyCondition();
    @Override
    public void Update() {
        super.Update();
        move();
        if (destroyCondition())
            Destroy();
        if(hit()){
            makeDamage();
        }

    }
    abstract void move();
    abstract boolean hit();
}
