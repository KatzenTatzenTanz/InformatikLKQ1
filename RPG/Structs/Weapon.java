package Structs;

import Statics.TypeWeakness;
import javafx.scene.Node;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class Weapon implements Inspectable {
    private String name;
    private int[] damage;
    private int Type;

    int attack() {
        return damage[(int)(Math.random() * damage.length)];
    }


    public Weapon(Weapon clone) {
        this.name = clone.getName();
        this.damage = clone.getDamage();
        this.Type = clone.getType();
    }

    public Weapon(String name, int[] damage, int Type) {
        this.name = name;
        this.damage = damage;
        this.Type = Type;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int[] getDamage() {
        return this.damage;
    }

    public void setDamage(int[] damage) {
        this.damage = damage;
    }

    public int getType() {
        return this.Type;
    }

    public void setType(int Type) {
        this.Type = Type;
    }

    public Weapon name(String name) {
        setName(name);
        return this;
    }

    public Weapon damage(int[] damage) {
        setDamage(damage);
        return this;
    }

    public Weapon Type(int Type) {
        setType(Type);
        return this;
    }

    @Override
    public String toString() {
        return "{" +
            " name='" + getName() + "'" +
            ", damage='" + getDamage() + "'" +
            ", Type='" + getType() + "'" +
            "}";
    }

    @Override
    public Node toUI() {
        VBox r = new VBox();
        r.getChildren().add(new Text("Name: " + getName()));
        r.getChildren().add(new Text("Type: " + TypeWeakness.getName(getType())));
        String attackTypes = "[";
        for(int i : damage)
            attackTypes += i + ", ";
        attackTypes = attackTypes.substring(0, attackTypes.length()-2) + "]";
        r.getChildren().add(new Text("Damage: " + attackTypes));
        return r;
    }
}