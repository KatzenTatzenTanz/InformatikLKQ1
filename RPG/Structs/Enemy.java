package Structs;

import java.util.ArrayList;

import Displays.Inspect;
import Statics.TypeLists;
import Statics.TypeWeakness;
import javafx.scene.Node;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.geometry.Pos;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class Enemy implements Inspectable {
    private Weapon weapon;
    private String name;
    private int hp;
    private int def;
    private int type;

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
        this.type = clone.getType();
    }

    public Enemy(Weapon weapon, String name, int hp, int def, int type) {
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

    public Enemy type(int type) {
        setType(type);
        return this;
    }

    @Override
    public String toString() {
        return "{" +
            " weapon='" + getWeapon() + "'" +
            ", name='" + getName() + "'" +
            ", hp='" + getHp() + "'" +
            ", def='" + getDef() + "'" +
            ", type='" + getType() + "'" +
            "}";
    }

    @Override
    public Node toUI() {
        VBox r = new VBox();
        r.getChildren().add(new Text("Enemy: " + getName()));
        r.getChildren().add(new Text("HP: " + getHp()));
        r.getChildren().add(new Text("Def: " + getDef()));
        r.getChildren().add(new Text("type: " + TypeWeakness.getName(getType())));
        r.getChildren().add(new Text("Weapon: " + getWeapon().getName()));
        r.getChildren().get(r.getChildren().size()-1).setOnMouseClicked(event -> {
            new Inspect(getWeapon());           
        });
        return r;
    }

    @Override
    public Node getEditor() {
        Text NameTag = new Text("Name:");
        TextField NameInput = new TextField(this.name);
        NameInput.setOnKeyTyped(event -> {
            this.name = NameInput.getText();
        });
        NameInput.setPromptText("Name");
        HBox Name = new HBox(NameTag,NameInput);
        Name.setAlignment(Pos.CENTER);
        Name.setSpacing(16);

        Text HealthTag = new Text("Start HP:");
        TextField HealthInput = new TextField(Integer.toString(this.hp));
        HealthInput.setOnKeyTyped(event -> {
            if(!HealthInput.getText().matches("\\d*")) HealthInput.setText(HealthInput.getText().replaceAll("[^\\d]",""));
            if(HealthInput.getText().length() > 0) this.hp = Integer.parseInt(HealthInput.getText());
        });
        HealthInput.setPromptText("Start HP");
        HBox Health = new HBox(HealthTag,HealthInput);
        Health.setAlignment(Pos.CENTER);
        Health.setSpacing(16);
                      
        Text DefenseTag = new Text("Defense:");
        TextField DefenseInput = new TextField(Integer.toString(this.def));
        DefenseInput.setOnKeyTyped(event -> {
            if(!DefenseInput.getText().matches("\\d*")) DefenseInput.setText(DefenseInput.getText().replaceAll("[^\\d]",""));
            if(DefenseInput.getText().length() > 0) this.def = Integer.parseInt(DefenseInput.getText());
        });
        DefenseInput.setPromptText("Defense");
        HBox Defense = new HBox(DefenseTag,DefenseInput);
        Defense.setAlignment(Pos.CENTER);
        Defense.setSpacing(16);


        ArrayList<String> TypeTypes = new ArrayList<String>();

        //No fix for this as <Weapons> would result in an unnecessary cast, breaking the code
        for(String x : TypeWeakness.Types)
            TypeTypes.add(x);
        ChoiceBox<String> TypeType = new ChoiceBox<String>(FXCollections.observableArrayList(TypeTypes));
        TypeType.getSelectionModel().selectFirst();

        ArrayList<Class<Weapon>> WeaponTypes = new ArrayList<Class<Weapon>>();

        //No fix for this as <Weapons> would result in an unnecessary cast, breaking the code
        for(Class x : TypeLists.WeaponTypes)
            WeaponTypes.add(x);
        ArrayList<String> HeroTypeNames = new ArrayList<String>();
        WeaponTypes.forEach(x -> HeroTypeNames.add(x.getSimpleName()));

        ChoiceBox<String> WeaponType = new ChoiceBox<String>(FXCollections.observableArrayList(HeroTypeNames));
        WeaponType.getSelectionModel().select(this.type);

        Node Weapon = this.weapon.getEditor();

        VBox EnemyDisplay = new VBox(Name, Health, Defense, TypeType, Weapon, WeaponType);
        EnemyDisplay.setSpacing(16);


        WeaponType.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>(){
            Node Weap = Weapon;
            @Override
            public void changed(ObservableValue<? extends String> arg0, String arg1, String arg2) {
                try {
                    weapon = WeaponTypes.get(WeaponType.getSelectionModel().getSelectedIndex()).getConstructor().newInstance();
                    int index = EnemyDisplay.getChildren().indexOf(Weap);
                    EnemyDisplay.getChildren().set(index, weapon.getEditor());
                    Weap = EnemyDisplay.getChildren().get(index);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        TypeType.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>(){
            @Override
            public void changed(ObservableValue<? extends String> arg0, String arg1, String arg2) {
                type = TypeTypes.indexOf(arg0.getValue());
            }
        });

        return EnemyDisplay;
    }
}