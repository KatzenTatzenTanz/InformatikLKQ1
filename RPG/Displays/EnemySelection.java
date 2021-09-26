package Displays;

import java.util.ArrayList;

import Statics.Manager;
import Statics.TypeLists;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Button;
import Structs.*;

public class EnemySelection extends Stage {

    private ArrayList<Enemy> enemies;
    private ArrayList<Usable> loadout;
    private Hero hero;
    private VBox EnemyListing = new VBox();
    private VBox LoadoutListing = new VBox();

    public EnemySelection() {
        enemies = new ArrayList<Enemy>();
        loadout = new ArrayList<Usable>();
        HBox r = new HBox();


        EnemyListing.setSpacing(16);
        ScrollPane EnemyList = new ScrollPane(EnemyListing);
        EnemyList.setFitToWidth(true);
        EnemyList.setVbarPolicy(ScrollBarPolicy.AS_NEEDED);
        EnemyList.setHbarPolicy(ScrollBarPolicy.NEVER);


        LoadoutListing.setSpacing(16);
        ScrollPane LoadoutList = new ScrollPane(LoadoutListing);
        LoadoutList.setFitToWidth(true);
        LoadoutList.setVbarPolicy(ScrollBarPolicy.AS_NEEDED);
        LoadoutList.setHbarPolicy(ScrollBarPolicy.NEVER);


        VBox HeroListing = new VBox();
        HeroListing.setSpacing(16);

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
        //No fix for this as <Weapons> would result in an unnecessary cast during compilation, breaking the code
        for(Class x : TypeLists.HeroTypes)
            HeroTypes.add(x);

        ArrayList<String> HeroTypeNames = new ArrayList<String>();
        HeroTypes.forEach(x -> HeroTypeNames.add(x.getSimpleName()));

        ChoiceBox<String> HeroType = new ChoiceBox<String>(FXCollections.observableArrayList(HeroTypeNames));
        HeroType.getSelectionModel().selectFirst();

        HeroType.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>(){
            @Override
            public void changed(ObservableValue<? extends String> arg0, String arg1, String arg2) {
                try {
                    hero = HeroTypes.get(HeroType.getSelectionModel().getSelectedIndex()).getConstructor().newInstance();
                    int index = 1;
                    HeroListing.getChildren().set(index, hero.getEditor());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });



        HeroListing.getChildren().addAll(HeroType,HeroDisplay);

        BorderPane EnemiesLoadout = new BorderPane();
        EnemiesLoadout.setCenter(EnemyList);
        EnemiesLoadout.setTop(new Text("Enemies!"));
        EnemiesLoadout.setMinWidth(900/3);

        ArrayList<Class<Enemy>> EnemyTypes = new ArrayList<Class<Enemy>>();
        //No fix for this as <Weapons> would result in an unnecessary cast during compilation, breaking the code
        for(Class x : TypeLists.EnemyTypes)
            EnemyTypes.add(x);
        MenuButton CreateEnemy = new MenuButton("Create new:");
        EnemyTypes.forEach(x -> {
            MenuItem MI = new MenuItem(x.getSimpleName());
            MI.setOnAction(event -> {
                createEnemy(EnemyTypes.get(CreateEnemy.getItems().indexOf(event.getSource())));
            });
            CreateEnemy.getItems().add(MI);
        });

        EnemiesLoadout.setBottom(CreateEnemy);
 


        BorderPane HeroLoadout = new BorderPane();
        HeroLoadout.setCenter(LoadoutList);
        HeroLoadout.setTop(new Text("Loadout!"));
        HeroLoadout.setMinWidth(900/3);

        ArrayList<Class<Usable>> ItemTypes = new ArrayList<Class<Usable>>();
        //No fix for this as <Weapons> would result in an unnecessary cast during compilation, breaking the code
        for(Class x : TypeLists.ItemTypes)
            ItemTypes.add(x);
        MenuButton CreateItem = new MenuButton("Create new:");
        ItemTypes.forEach(x -> {
            MenuItem MI = new MenuItem(x.getSimpleName());
            MI.setOnAction(event -> {
                createUsable(ItemTypes.get(CreateItem.getItems().indexOf(event.getSource())));
            });
            CreateItem.getItems().add(MI);
        });

        HeroLoadout.setBottom(CreateItem);



        BorderPane HeroStats = new BorderPane();
        HeroStats.setCenter(HeroListing);
        HeroStats.setTop(new Text("Hero!"));
        HeroStats.setMinWidth(900/3);

        Button begin = new Button("Begin!");
        begin.setOnAction(event -> {
            Manager.C.setHero(hero);
            Manager.C.setInventory(loadout);
            Manager.C.setEnemies(enemies);
            new Display();
            new Loadout();
            new Utility();
            Manager.C.command("say HI! I will show you how to play! Click here to continue the text!");
            Manager.C.command("say Defeat all the enemies to win!");
            Manager.C.command("say Click the X on the Screen with your and the enemies Health to end the game!");
            this.hide();
        });

        HeroStats.setBottom(begin);

        r.getChildren().addAll(EnemiesLoadout,HeroLoadout,HeroStats);

        Scene sc = new Scene(r, 900, 700);
        this.setScene(sc);
        this.setResizable(false);
        this.show();
    }

    public void createEnemy(Class<Enemy> Type) {
        ArrayList<Class<Enemy>> EnemyTypes = new ArrayList<Class<Enemy>>();
        //No fix for this as <Weapons> would result in an unnecessary cast during compilation, breaking the code
        for(Class x : TypeLists.EnemyTypes)
            EnemyTypes.add(x);
        ArrayList<String> EnemyTypeNames = new ArrayList<String>();
        EnemyTypes.forEach(x -> EnemyTypeNames.add(x.getSimpleName()));
        try {
            Enemy e = Type.getConstructor().newInstance();
            Node n = e.getEditor();
            ChoiceBox<String> EnemyNType = new ChoiceBox<String>(FXCollections.observableArrayList(EnemyTypeNames));
            EnemyNType.getSelectionModel().select(e.getClass().getSimpleName());
            EnemyNType.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>(){
                Node enemy = n;
                @Override
                public void changed(ObservableValue<? extends String> arg0, String arg1, String arg2) {
                    try {
                        int index = EnemyListing.getChildren().indexOf(enemy)/2;
                        enemies.set(index, EnemyTypes.get(EnemyNType.getSelectionModel().getSelectedIndex()).getConstructor().newInstance());
                        EnemyListing.getChildren().set(index*2+1, enemies.get(index).getEditor());
                        enemy = EnemyListing.getChildren().get(index*2+1);
                        if(index % 2 == 0)
                            enemy.setStyle("-fx-background-color: #00000011");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
            Button delete = new Button("Delete");
            delete.setOnAction(new EventHandler<ActionEvent>() {
                Node enemy = n;
                @Override
                public void handle(ActionEvent arg0) {
                    try {
                        int index = EnemyListing.getChildren().indexOf(enemy)/2;
                        EnemyListing.getChildren().remove(index*2);
                        EnemyListing.getChildren().remove(index*2);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });

            HBox data = new HBox(EnemyNType, delete);

            EnemyListing.getChildren().add(data);
            enemies.add(e);
            if(enemies.size() % 2 == 0)
                n.setStyle("-fx-background-color: #00000011");
            EnemyListing.getChildren().add(n);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void createUsable(Class<Usable> Type) {
        ArrayList<Class<Usable>> ItemTypes = new ArrayList<Class<Usable>>();
        //No fix for this as <Weapons> would result in an unnecessary cast during compilation, breaking the code
        for(Class x : TypeLists.ItemTypes)
            ItemTypes.add(x);
        ArrayList<String> ItemTypeNames = new ArrayList<String>();
        ItemTypes.forEach(x -> ItemTypeNames.add(x.getSimpleName()));
        try {
            Usable e = Type.getConstructor().newInstance();
            Node n = e.getEditor();
            ChoiceBox<String> ItemNType = new ChoiceBox<String>(FXCollections.observableArrayList(ItemTypeNames));
            ItemNType.getSelectionModel().select(e.getClass().getSimpleName());
            ItemNType.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>(){
                Node item = n;
                @Override
                public void changed(ObservableValue<? extends String> arg0, String arg1, String arg2) {
                    try {
                        int index = LoadoutListing.getChildren().indexOf(item)/2;
                        loadout.set(index, ItemTypes.get(ItemNType.getSelectionModel().getSelectedIndex()).getConstructor().newInstance());
                        LoadoutListing.getChildren().set(index*2+1, loadout.get(index).getEditor());
                        item = LoadoutListing.getChildren().get(index*2+1);
                        if(index % 2 == 0)
                            item.setStyle("-fx-background-color: #00000011");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
            Button delete = new Button("Delete");
            delete.setOnAction(new EventHandler<ActionEvent>() {
                Node item = n;
                @Override
                public void handle(ActionEvent arg0) {
                    try {
                        int index = LoadoutListing.getChildren().indexOf(item)/2;
                        LoadoutListing.getChildren().remove(index*2);
                        LoadoutListing.getChildren().remove(index*2);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });

            HBox data = new HBox(ItemNType, delete);

            LoadoutListing.getChildren().add(data);
            loadout.add(e);
            if(loadout.size() % 2 == 0)
                n.setStyle("-fx-background-color: #00000011");
            LoadoutListing.getChildren().add(n);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
