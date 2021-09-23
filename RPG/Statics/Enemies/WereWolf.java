package Statics.Enemies;

import Structs.Enemy;
import Statics.Weapons.*;

public class WereWolf extends Enemy { 
    public WereWolf() { 
        super(new Claws(), "Werewolf", 300, 5, 6);
    }
}