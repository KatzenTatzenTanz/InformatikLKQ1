package Displays;
import java.util.ArrayList;

import Statics.Enemies;
import Statics.Weapons;
import Structs.Enemy;
import Structs.Hero;
import Structs.Weapon;
import Utility.Comms;
import javafx.application.Platform;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.scene.layout.FlowPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class CharacterCreate extends Stage {

    public CharacterCreate() {
        // Window Setup
        this.setTitle("Erstelle einen Charakter");

        // Top -> Bottom GUI
        FlowPane r = new FlowPane();
        r.setAlignment(Pos.CENTER);
        r.setVgap(8);
        r.setOrientation(Orientation.VERTICAL);

        Text Header = new Text("KNIGHTS N' FIGHTS");
        Header.setFont(new Font("Arial",40));

        TextField NameInput = new TextField();
        NameInput.setAlignment(Pos.CENTER);
        NameInput.setPromptText("Name:");

        Button b = new Button("Erstellen!");
        b.setAlignment(Pos.CENTER);
        b.setOnAction(event -> {
            Hero h = new Hero(Weapons.WaterGun, NameInput.getText(), 100, 5, 0);
            ArrayList<Enemy> en = new ArrayList<Enemy>();
            en.add(new Enemy(Enemies.FireSpirit));
            en.add(new Enemy(Enemies.FireSpirit));
            en.add(new Enemy(Enemies.FireSpirit));
            en.add(new Enemy(Enemies.FireSpirit));
            en.add(new Enemy(Enemies.FireSpirit));
            ArrayList<Weapon> we = new ArrayList<Weapon>();
            we.add(new Weapon(Weapons.BattleAxe));
            we.add(new Weapon(Weapons.Bow));
            we.add(new Weapon(Weapons.Katana));
            we.add(new Weapon(Weapons.LongSword));
            we.add(new Weapon(Weapons.Wand));
            we.add(new Weapon(Weapons.WaterGun));
            Comms c = new Comms(true);
            c.setHero(h);
            c.setEnemies(en);
            c.setWeapons(we);
            try {
                new Display(c);
                new Utility(c);
                new Loadout(c);

                c.Command("say Hello! I will teach you how to play! Click here to continue the Text!");
                c.Command("say You have to defeat the Enemies before they Defeat you!");
                c.Command("say Click on anything to get more information about it!");
                c.Command("say Press the X on the Window on which you see the enemies to close the game!");

                this.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        r.getChildren().addAll(Header,NameInput,b);
        
        Scene sc = new Scene(r, 400, 200);
        this.setScene(sc);
        Platform.setImplicitExit(true);
        // Display Screen
        this.setResizable(false);
        this.show();
    }
    
}
