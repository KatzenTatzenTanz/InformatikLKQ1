package Statics.ConsumableItems;

import Structs.Consumable;

public class Potion extends Consumable {
    public Potion() {
        this.setName("Potion of healing");
        this.setHeal(50);
    }
}
