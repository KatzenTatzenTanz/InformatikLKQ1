package Statics;

import Statics.Weapons.*;
import Structs.*;

public class TypeLists {
    // appending <Weapon> here would result in an unnecessary cast, casting things such as Axe into Weapon
    public static final Class[] WeaponTypes = new Class[] {Weapon.class,Axe.class,Bow.class,Claws.class,EnemyWand.class,Fangs.class,Katana.class,LongSword.class,Saber.class,Unarmed.class,Wand.class,WaterGun.class};
}
