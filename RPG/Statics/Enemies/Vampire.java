package Statics.Enemies;

import Structs.Enemy;
import Statics.Weapons.*;

public class Vampire extends Enemy { 
    public Vampire() { 
        super(new Fangs(), "Vampire", 100, 0, 4);
    }
}