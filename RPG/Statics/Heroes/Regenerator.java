package Statics.Heroes;

import Statics.TypeWeakness;
import Structs.Enemy;
import Structs.Hero;

public class Regenerator extends Hero {
    public int attack(Enemy e) {
        //calculate Weakness of enemy to weapon and damage enemy based on that
        int dmg = Math.max(0,(int)(this.getWeapon().attack() * TypeWeakness.calculateStrength(e.getType(), this.getWeapon().getType())) - e.getDef());
        e.setHp(e.getHp()-dmg);
        this.setHp(this.getHp() + dmg / 4);
        return dmg;
    }
}
