package effects;

import entities.Tower;

public abstract class TowerEffect extends Effect{

    TowerEffect(String name, float power, float time, String tag, String source, Tower tower) {
        super(name, power, time, tag, source, tower);
    }
}
