package Displays;

import java.util.ArrayList;
import java.util.Vector;

import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.collections.FXCollections;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ChoiceBox;
import Structs.*;

public class EnemySelection extends Stage {

    private Vector<Enemy> enemies;
    private Vector<Usable> loadout;
    private Hero hero;

    public EnemySelection() {
        HBox r = new HBox();


        ScrollPane EnemyList = new ScrollPane();



        ScrollPane LoadoutList = new ScrollPane();

        hero = new Hero(
            new Weapon(
                "", //Default Weapon Name
                new int[]{0}, //Default Weapon Attacks
                0), //Default Weapon TYPE ID
            "", //Default Name
            100, //Default HP
            0, //Default DEF
            0); //Default TYPE ID
        Node HeroDisplay = hero.getEditor();


        ArrayList<Class<Hero>> HeroTypes = new ArrayList<Class<Hero>>();
        HeroTypes.add(Hero.class);

        ArrayList<String> HeroTypeNames = new ArrayList<String>();
        HeroTypes.forEach(x -> HeroTypeNames.add(x.getSimpleName()));

        ChoiceBox<String> HeroType = new ChoiceBox<String>(FXCollections.observableArrayList(HeroTypeNames));
        HeroType.getSelectionModel().selectFirst();
        ((VBox)HeroDisplay).getChildren().add(HeroType);

        BorderPane EnemiesLoadout = new BorderPane();
        EnemiesLoadout.setCenter(EnemyList);
        EnemiesLoadout.setTop(new Text("Enemies!"));
        EnemiesLoadout.setMinWidth(900/3);
        BorderPane HeroLoadout = new BorderPane();
        HeroLoadout.setCenter(LoadoutList);
        HeroLoadout.setTop(new Text("Loadout!"));
        HeroLoadout.setMinWidth(900/3);
        BorderPane HeroStats = new BorderPane();
        HeroStats.setCenter(HeroDisplay);
        HeroStats.setTop(new Text("Hero!"));
        HeroStats.setMinWidth(900/3);

        r.getChildren().addAll(EnemiesLoadout,HeroLoadout,HeroStats);

        Scene sc = new Scene(r, 900, 700);
        this.setScene(sc);
        // Display Screen
        this.setResizable(false);
        this.show();
    }

    public void createEnemy() {

    }

    public void createUsable() {

    }
}
