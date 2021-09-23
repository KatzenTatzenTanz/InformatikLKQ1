package Statics.Enemies;

import Structs.Enemy;
import Statics.Weapons.*;

public class FireSpirit extends Enemy { 
    public FireSpirit() { 
        super(new Unarmed(), "Fire Spirit", 50, 10, 2);
    }
}