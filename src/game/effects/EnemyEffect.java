package effects;

import entities.Enemy;

public abstract class EnemyEffect extends Effect{
    //название для визуальных эффектов
    //сила воздействия эффекта
    //time время действия (-1 для неограниченного)
    //tag (список внизу) для определения на какой парамертр аоздейтвовать
    //source источник - эффекты из одного источника и с одним тэгом не могут весеть на одном объекте (старый удаляятся)
    //enemy на кого вешаем эффект
    EnemyEffect(String name, float power, float time, String tag, String source, Enemy enemy) {
        super(name, power, time, tag,source, enemy);
    }

    public static class EffectSlowMotion extends EnemyEffect{
        EffectSlowMotion(float power, float time, String source, Enemy enemy) {
            super("slow", power, time, "move_speed",source, enemy);
        }
    }

    public static class EffectPoison extends EnemyEffect{
        EffectPoison(float power, float time, String source, Enemy enemy) {
            super("poison", power, time, "time_damage",source, enemy);
        }
    }
    public static class EffectFire extends EnemyEffect{
        EffectFire(float power, float time, String source, Enemy enemy) {
            super("fire", power, time, "time_damage",source, enemy);
        }
    }
    public static class EffectCrash extends EnemyEffect{
        EffectCrash(float power, float time, String source, Enemy enemy) {
            super("break_armor", power, time, "resist",source, enemy);
        }
    }

    public static class EffectArmorUp extends EnemyEffect{
        EffectArmorUp(float power, float time, String source, Enemy enemy) {
            super("armor_buff_general", power, -1, "resist",source, enemy);
        }
    }
    public static class EffectHealthUp extends EnemyEffect{
        EffectHealthUp(float power, String source, Enemy enemy) {
            super("health_buff_general", power, -1, "stamina",source, enemy);
        }
    }
    public static class EffectDamageUp extends EnemyEffect{
        EffectDamageUp(float power, String source, Enemy enemy) {
            super("damage_buff_general", power, -1, "damage",source, enemy);
        }
    }
    /////////////////////////tags///////////////////////////
    //move_speed    // замедление/ускорение/стан
    //time_damage   // урон(яд/ожог)
    //stamina       // запас здоровья
    //resist        // уменьшение/увеличение брони
    //

}
