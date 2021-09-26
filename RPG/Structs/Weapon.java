package Structs;

import java.util.ArrayList;

import Statics.Manager;
import Statics.TypeWeakness;
import javafx.collections.FXCollections;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class Weapon implements Usable {
    private String name;
    private int[] damage;
    private int type;

    public int attack() {
        return damage[(int)(Math.random() * damage.length)];
    }

    @Override
    public void use() {
        //replace Item in inventory with this item 
        // ## DO NOT CONFUSE WITH attack() ## 
        int i = Manager.C.getInventory().indexOf(this);
        Manager.C.getInventory().set(i,Manager.C.getHero().getWeapon());
        Manager.C.getHero().setWeapon(this);
        Manager.C.command("log " + Manager.C.getHero().getName() + " used " + this.name + " instead of " + Manager.C.getInventory().get(i).getName());
        Manager.C.command("log " + Manager.C.getHero().getName() + " uses " + this.name + " instead of " + Manager.C.getInventory().get(i).getName() + ".");
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    public Weapon() {
        this.name = "";
        this.damage = new int[]{0};
        this.type = 0;
    }

    public Weapon(Weapon clone) {
        this.name = clone.getName();
        this.damage = clone.getDamage();
        this.type = clone.getType();
    }

    public Weapon(String name, int[] damage, int type) {
        this.name = name;
        this.damage = damage;
        this.type = type;
    }

    public int[] getDamage() {
        return this.damage;
    }

    public void setDamage(int[] damage) {
        this.damage = damage;
    }

    public int getType() {
        return this.type;
    }

    public void setType(int type) {
        this.type = type;
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

    //important for inspecting the weapon by clicking on it
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

    //important to EnemySelect.java
    @Override
    public Node getEditor() {
        Text Header = new Text(this.getClass().getSimpleName() + ":");
        Text NameTag = new Text("WeaponName:");
        TextField NameInput = new TextField(this.name);
        //update name with every keystroke
        NameInput.setOnKeyTyped(event -> {
            this.name = NameInput.getText();
        });
        NameInput.setPromptText("Name");
        HBox Name = new HBox(NameTag,NameInput);
        Name.setAlignment(Pos.CENTER);
        Name.setSpacing(16);

        Text AttackTag = new Text("Damages:");
        String s = this.damage[0] + "";
        for(int i = 1; i < this.damage.length; ++i)
            s += ";" + this.damage[i];
        TextField Damages = new TextField(s);
        //update Attack Types separated by ;
        Damages.setOnKeyTyped(event -> {
            ArrayList<Integer> l = new ArrayList<Integer>();
            boolean replace = false;
            for(String str : Damages.getText().split(";")) {
                try {
                    //only allow numbers
                    if(!str.matches("\\d*")) {
                        str = str.replaceAll("[^\\d]","");
                        replace = true;
                    }
                    l.add(Integer.parseInt(str));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            this.damage = new int[l.size()];
            for(int i = 0; i < this.damage.length; ++i)
                this.damage[i] = l.get(i);
            if(replace) {
                String ns = this.damage[0] + "";
                for(int i = 1; i < this.damage.length; ++i)
                    ns += ";" + this.damage[i];
                Damages.setText(ns);
            }
        });
        Damages.setPromptText("Enter as: DMG1;DMG2");
        HBox Dmg = new HBox(AttackTag,Damages);
        Dmg.setAlignment(Pos.CENTER);
        Dmg.setSpacing(16);



        ArrayList<String> TypeTypes = new ArrayList<String>();

        //No fix for this as <Weapons> would result in an unnecessary cast, breaking the code
        for(String x : TypeWeakness.Types)
            TypeTypes.add(x);
        ChoiceBox<String> TypeType = new ChoiceBox<String>(FXCollections.observableArrayList(TypeTypes));
        TypeType.getSelectionModel().select(this.type);
        
        VBox WeaponDisplay = new VBox(Header,Name,Dmg,TypeType);
        WeaponDisplay.setSpacing(16);
        return WeaponDisplay;
    }
}