package Displays;

import Statics.Manager;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
/**
 * index
 */
public class Display extends Stage {
    private VBox r;

    public Display() {
        super();
        Manager.C.addWindow(this);

        Manager.C.setDisplay(this);
        // Window Setup
        this.setTitle("Fight!");

        r = createFightMainUI();

        update();
        
        Scene sc = new Scene(r, 700, 500);
        this.setOnShown(event -> {
            this.setX(this.getX() + 200);
            this.setY(this.getY() - 100);
        });
        this.setScene(sc);
        this.setOnCloseRequest(event -> {
            Manager.C.command("closeWindows");
            this.close();
        });
        this.focusedProperty().addListener(new ChangeListener<Boolean>() {

            @Override
            public void changed(ObservableValue<? extends Boolean> arg0, Boolean arg1, Boolean arg2) {
                if(!arg1 && arg2)
                    Manager.C.command("openWindows");
            }
            
        });
        // Display Screen
        this.setResizable(false);
        this.show();
    }

    public void update() {
        FlowPane Hero = getHeroUI(r);
        HBox Enemies = getEnemyHeadUI(r);
        getHeroHealth(Hero).setText(Integer.toString(Manager.C.getHero().getHp()));
        getHeroName(Hero).setText(Manager.C.getHero().getName());

        int enemysize = Manager.C.getEnemies().size();

        for(int i = Enemies.getChildren().size(); i < enemysize; ++i) {
            Enemies.getChildren().add(createEnemyUI());
            // protective layer since java gives an error if "int index = i"
            int cur = i;
            Enemies.getChildren().get(i).setOnMouseClicked(new EventHandler<MouseEvent>() {
                int index = cur;
                @Override
                public void handle(MouseEvent arg0) {
                    new Inspect(Manager.C.getEnemies().get(index));
                }
            });
        }
        for(int i = Enemies.getChildren().size(); i > enemysize; --i)
            Enemies.getChildren().remove(0);

        for(int i = 0; i < enemysize; ++i) {
            getEnemyHealth((FlowPane)Enemies.getChildren().get(i)).setText(Integer.toString(Manager.C.getEnemies().get(i).getHp()));
            getEnemyName((FlowPane)Enemies.getChildren().get(i)).setText(Manager.C.getEnemies().get(i).getName());
        }
    }
    //Create Display in which the Enemy UI and the Hero hook into
    public static VBox createFightMainUI() {
        VBox r = new VBox();
        r.setFillWidth(true);
        r.setAlignment(Pos.TOP_LEFT);

        HBox Enemies = new HBox();
        Enemies.setSpacing(24);
        Enemies.setAlignment(Pos.TOP_CENTER);
        Enemies.setPadding(new Insets(16));

        FlowPane HeroStats = new FlowPane(Orientation.VERTICAL);
        Text HeroName = new Text("SETNAME");
        HeroName.setFill(Color.WHITE);
        Text HeroHP = new Text("SETHP");
        HeroHP.setFill(Color.WHITE);

        HeroStats.getChildren().addAll(HeroName,HeroHP);
        HeroStats.setAlignment(Pos.BOTTOM_LEFT);
        HeroStats.setTranslateY(-16);
        HeroStats.setTranslateX(16);
        HeroStats.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent arg0) {
                new Inspect(Manager.C.getHero());
            }
        });
        r.getChildren().addAll(Enemies,HeroStats);
        Background b = new Background(new BackgroundFill(Color.BLACK,CornerRadii.EMPTY,Insets.EMPTY));
        r.setBackground(b);
        return r;
    }

    //Create Enemy UI where single Enemies Hook into
    public static FlowPane createEnemyUI() {

        FlowPane Enemy = new FlowPane(Orientation.VERTICAL);
        Enemy.setAlignment(Pos.TOP_LEFT);
        Text EnemyName = new Text("SETENAME");
        EnemyName.setFill(Color.WHITE);
        Text EnemyHp = new Text("SETEHP");
        EnemyHp.setFill(Color.WHITE);

        Enemy.getChildren().addAll(EnemyName,EnemyHp);
        return Enemy;
    }

    /* MAY CHANGE WITH CHANGES TO THE UI */
    public static FlowPane getHeroUI(VBox r) {
        return (FlowPane)r.getChildren().get(1);
    }
    public static Text getHeroName(FlowPane hero) {
        return (Text)hero.getChildren().get(0);
    }
    public static Text getHeroHealth(FlowPane hero) {
        return (Text)hero.getChildren().get(1);
    }

    public static HBox getEnemyHeadUI(VBox r) {
        return (HBox)r.getChildren().get(0);
    }
    public static Text getEnemyName(FlowPane enemy) {
        return (Text)enemy.getChildren().get(0);
    }
    public static Text getEnemyHealth(FlowPane enemy) {
        return (Text)enemy.getChildren().get(1);
    }

}