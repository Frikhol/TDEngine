package effects;

import java.util.ArrayList;

public interface Effectible {
    ArrayList<Effect> effectList = new ArrayList<>();
    default void addEffect(Effect effect){
        effectList.add(effect);
    }
    default void removeEffect(Effect effect){
        effectList.remove(effect);
    }
    default float effectMultiplayer(String tag){
        float multiplayer = 1.0f;
        for(Effect effect : effectList){
            if(effect.getTag().equals(tag)){
                multiplayer*=effect.getPower();
            }
        }
        return multiplayer;
    }
    default float percentEffectMultiplayer(String tag){
        float multiplayer = 1.0f;
        for(Effect effect : effectList){
            if(effect.getTag().equals(tag)){
                multiplayer*=(1.0f-effect.getPower());
            }
        }
        return (1.0f-multiplayer);
    }
    default float effectSumma(String tag){
        float summa = 0.0f;
        for(Effect effect : effectList){
            if(effect.getTag().equals(tag)){
                summa+=effect.getPower();
            }
        }
        return summa;
    }
}
