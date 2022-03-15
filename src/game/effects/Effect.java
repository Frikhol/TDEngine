package effects;

import tools.Time;

import java.util.ArrayList;

public abstract class Effect {
    static private ArrayList<Effect>list = new ArrayList<>();
    private boolean unlimited;
    private String name;
    private String tag;
    private String source;
    private float power;
    private float timer;
    private Effectible parent;
    Effect(String name,float power, float time, String tag,String source, Effectible effectible){
        effectible.addEffect(this);
        this.power = power;
        this.name = name;
        this.tag = tag;
        this.source = source;
        for(int i=0;i<effectible.effectList.size();i++){
            Effect effect = effectible.effectList.get(i);
            if(effect.tag.equals(tag) &&
                effect.source.equals(source)){
                effectible.effectList.remove(effect);
                break;
            }
        }
        if(time == -1){
            unlimited = true;
            timer = 1.0f;
        }else {
            parent = effectible;
            unlimited = false;
            this.timer = time;
            list.add(this);
        }

    }
    void tactFrame(){
        timer -= Time.getDeltaTime();
        if(timer<=0) {
            parent.removeEffect(this);
            list.remove(this);
        }
    }
    static void AllEffectsTactFrame(){
        for (Effect e :list)
            e.tactFrame();
    }
    public String getTag() {
        return tag;
    }

    public float getPower() {
        return power;
    }

    public String getName() {
        return name;
    }
}
