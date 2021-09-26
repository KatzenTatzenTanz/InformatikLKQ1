package Statics;

import Statics.Weapons.*;
import Statics.Enemies.*;
import Statics.Heroes.Regenerator;
import Statics.ConsumableItems.*;
import Structs.*;

public class TypeLists {
    // appending <Weapon> or any <Type> here would result in an unnecessary cast, casting things such as Axe into Weapon on compilation, in turn, breaking the code
    public static final Class[] WeaponTypes = new Class[] {Weapon.class,Axe.class,Bow.class,Claws.class,EnemyWand.class,Fangs.class,Katana.class,LongSword.class,Saber.class,Unarmed.class,Wand.class,WaterGun.class};
    public static final Class[] EnemyTypes = new Class[] {Enemy.class,FireSpirit.class,Mage.class,Pirate.class,Vampire.class,WereWolf.class};
    public static final Class[] ItemTypes = new Class[] {Weapon.class,Axe.class,Bow.class,Claws.class,EnemyWand.class,Fangs.class,Katana.class,LongSword.class,Saber.class,Unarmed.class,Wand.class,WaterGun.class,
                                                         Consumable.class,Potion.class};
    public static final Class[] HeroTypes = new Class[] {Hero.class, Regenerator.class};

}
