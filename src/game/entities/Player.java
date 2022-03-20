package entities;

public class Player {
    static private float health = 100;
    static private float gold = 100;

    static public void healing(float healHealth){
        health+=healHealth;
    }
    static public void damaged(float damage){
        health-=damage;
    }
    static public void earnings(float earnings){
        gold+=earnings;
    }
    static public void spend(float expenses){
        gold-=expenses;
    }
}
