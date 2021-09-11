package Structs;

import Displays.Inspect;
import Statics.TypeWeakness;
import javafx.scene.Node;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class Enemy implements Inspectable {
    private Weapon weapon;
    private String name;
    private int hp;
    private int def;
    private int Type;

    public int attack(Hero h) {        
        int dmg = Math.max(0,(int)(weapon.attack() * TypeWeakness.calculateStrength(h.getType(), weapon.getType())) - h.getDef());
        h.setHp(h.getHp()-dmg);
        return dmg;
    }

    public Enemy(Enemy clone) {
        this.weapon = new Weapon(clone.getWeapon());
        this.name = clone.getName();
        this.hp = clone.getHp();
        this.def = clone.getDef();
        this.Type = clone.getType();
    }

    public Enemy(Weapon weapon, String name, int hp, int def, int Type) {
        this.weapon = weapon;
        this.name = name;
        this.hp = hp;
        this.def = def;
        this.Type = Type;
    }

    public Weapon getWeapon() {
        return this.weapon;
    }

    public void setWeapon(Weapon weapon) {
        this.weapon = weapon;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHp() {
        return this.hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getDef() {
        return this.def;
    }

    public void setDef(int def) {
        this.def = def;
    }

    public int getType() {
        return this.Type;
    }

    public void setType(int Type) {
        this.Type = Type;
    }

    public Enemy weapon(Weapon weapon) {
        setWeapon(weapon);
        return this;
    }

    public Enemy name(String name) {
        setName(name);
        return this;
    }

    public Enemy hp(int hp) {
        setHp(hp);
        return this;
    }

    public Enemy def(int def) {
        setDef(def);
        return this;
    }

    public Enemy Type(int Type) {
        setType(Type);
        return this;
    }

    @Override
    public String toString() {
        return "{" +
            " weapon='" + getWeapon() + "'" +
            ", name='" + getName() + "'" +
            ", hp='" + getHp() + "'" +
            ", def='" + getDef() + "'" +
            ", Type='" + getType() + "'" +
            "}";
    }

    @Override
    public Node toUI() {
        VBox r = new VBox();
        r.getChildren().add(new Text("Enemy: " + getName()));
        r.getChildren().add(new Text("HP: " + getHp()));
        r.getChildren().add(new Text("Def: " + getDef()));
        r.getChildren().add(new Text("Type: " + TypeWeakness.getName(getType())));
        r.getChildren().add(new Text("Weapon: " + getWeapon().getName()));
        r.getChildren().get(r.getChildren().size()-1).setOnMouseClicked(event -> {
            new Inspect(getWeapon());           
        });
        return r;
    }
}