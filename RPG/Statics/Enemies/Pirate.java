package Statics.Enemies;

import Structs.Enemy;
import Statics.Weapons.*;

public class Pirate extends Enemy { 
    public Pirate() { 
        super(new Saber(), "Pirate", 200, 5, 1);
    }
}