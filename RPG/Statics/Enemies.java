package Statics;

import Structs.Enemy;

public class Enemies {
    public static Enemy Vampire = new Enemy(Weapons.Fangs, "Vampire", 100, 0, 4);
    public static Enemy Mage = new Enemy(Weapons.EnemyWand, "Mage", 75, 2, 4);
    public static Enemy Pirate = new Enemy(Weapons.Saber, "Pirate", 200, 5, 1);
    public static Enemy WereWolf = new Enemy(Weapons.Claws, "Werewolf", 300, 5, 6);
    public static Enemy FireSpirit = new Enemy(Weapons.Unarmed, "Fire Spirit", 50, 10, 2);
} 
