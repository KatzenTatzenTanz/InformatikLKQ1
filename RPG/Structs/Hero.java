package Structs;

import Displays.Inspect;
import Statics.TypeWeakness;
import javafx.scene.Node;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class Hero implements Inspectable {
    private Weapon weapon;
    private String name;
    private int hp;
    private int def;
    private int type;


    public int attack(Enemy e) {
        int dmg = Math.max(0,(int)(weapon.attack() * TypeWeakness.calculateStrength(e.getType(), weapon.getType())) - e.getDef());
        e.setHp(e.getHp()-dmg);
        return dmg;
    }





    public Hero(Weapon weapon, String name, int hp, int def, int type) {
        this.weapon = weapon;
        this.name = name;
        this.hp = hp;
        this.def = def;
        this.type = type;
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
        return this.type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public Hero weapon(Weapon weapon) {
        setWeapon(weapon);
        return this;
    }

    public Hero name(String name) {
        setName(name);
        return this;
    }

    public Hero hp(int hp) {
        setHp(hp);
        return this;
    }

    public Hero def(int def) {
        setDef(def);
        return this;
    }

    public Hero Type(int Type) {
        setType(Type);
        return this;
    }

    @Override
    public String toString() {
        return "{" +
            " name='" + getName() + "'" +
            ", hp='" + getHp() + "'" +
            ", def='" + getDef() + "'" +
            ", weapon='" + getWeapon() + "'" +
            ", Type='" + getType() + "'" +
            "}";
    }

    @Override
    public Node toUI() {
        VBox r = new VBox();
        r.getChildren().add(new Text("Heroic Name: " + getName()));
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