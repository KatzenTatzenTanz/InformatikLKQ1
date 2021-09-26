package Structs;

import Statics.Manager;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class Consumable implements Usable {
    private String name;
    private int heal;

    public int getHeal() {
        return this.heal;
    }

    public void setHeal(int heal) {
        this.heal = heal;
    }

    @Override
    public Node toUI() {
        VBox r = new VBox();
        r.getChildren().add(new Text("Name: " + name));
        r.getChildren().add(new Text("Heal: " + heal));
        return r;
    }

    @Override
    public void use() {
        Manager.C.getHero().setHp(Manager.C.getHero().getHp() + heal);
        Manager.C.getInventory().remove(Manager.C.getInventory().indexOf(this));
        Manager.C.command("log " + Manager.C.getHero().getName() + " used " + this.name + " and healed " + this.heal);
        Manager.C.command("say " + Manager.C.getHero().getName() + " uses " + this.name + " and heals " + this.heal + ".");
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public Node getEditor() {
        Text Header = new Text(this.getClass().getSimpleName() + ":");
        Text NameTag = new Text("ConsumableName:");
        TextField NameInput = new TextField(this.name);
        NameInput.setOnKeyTyped(event -> {
            this.name = NameInput.getText();
        });
        NameInput.setPromptText("Name");
        HBox Name = new HBox(NameTag,NameInput);
        Name.setAlignment(Pos.CENTER);
        Name.setSpacing(16);

        Text HealTag = new Text("Heals:");
        TextField HealInput = new TextField(Integer.toString(this.heal));
        HealInput.setOnKeyTyped(event -> {
            if(!HealInput.getText().matches("\\d*")) HealInput.setText(HealInput.getText().replaceAll("[^\\d]",""));
            if(HealInput.getText().length() > 0) this.heal = Integer.parseInt(HealInput.getText());
        });
        HealInput.setPromptText("Start HP");
        HBox Heal = new HBox(HealTag,HealInput);
        Heal.setAlignment(Pos.CENTER);
        Heal.setSpacing(16);
        
        VBox WeaponDisplay = new VBox(Header,Name,Heal);
        WeaponDisplay.setSpacing(16);
        return WeaponDisplay;
    }
    
}
