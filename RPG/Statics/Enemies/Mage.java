package Statics.Enemies;

import Structs.Enemy;
import Statics.Weapons.*;

public class Mage extends Enemy { 
    public Mage() { 
        super(new EnemyWand(), "Mage", 75, 2, 4);
    }
}