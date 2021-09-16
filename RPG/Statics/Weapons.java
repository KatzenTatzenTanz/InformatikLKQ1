package Statics;

import Structs.Weapon;

public class Weapons {
    public static final Weapon WaterGun = new Weapon("Pistol of Water",new int[]{50,20,20,0},1);
    public static final Weapon LongSword = new Weapon("Double sided Long Sword of Flames",new int[]{30,25,20,15},2);
    public static final Weapon Katana = new Weapon("Katana of the Elf Spirits",new int[]{60,10,5,0},3);
    public static final Weapon Bow = new Weapon("Bow of Light",new int[]{30,20,30},4);
    public static final Weapon Wand = new Weapon("Wand of the Dark Caverns",new int[]{40,30,30,10},5);
    public static final Weapon BattleAxe = new Weapon("BattleAxe of Storms",new int[]{40,0},6);

    /*  ENEMY WEAPONS  */

    public static final Weapon Fangs = new Weapon("Vampire Fangs",new int[]{10,5},0);
    public static final Weapon EnemyWand = new Weapon("Regular Wand of the Dark Sorcery",new int[]{10,5},0);
    public static final Weapon Claws = new Weapon("Werewolf Claws",new int[]{10,5},0);
    public static final Weapon Saber = new Weapon("Pirates Saber",new int[]{15,10},0);
    public static final Weapon Unarmed = new Weapon("No Weapon",new int[]{5,5},0);
    
}
