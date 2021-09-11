package Displays;

import java.util.ArrayDeque;

import Structs.Enemy;
import Structs.Weapon;
import Utility.Comms;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * index
 */
public class Utility extends Stage {

    private Comms communicator;
    private ArrayDeque<String> messages = new ArrayDeque<String>();
    private GridPane r;

    private Utility(Comms arg0, ArrayDeque<String> messages, double x, double y) {
        this(arg0);
        if(messages.size() > 0)
            addText(messages.getFirst());
        else
            update();
        communicator.setUtility(this);
        this.messages = messages;
        this.setX(x);
        this.setY(y);
    }

    public Utility(Comms arg0) {
        super();

        communicator = arg0;
        communicator.setUtility(this);
        // Window Setup
        this.setTitle("Fite");

        StackPane tr = new StackPane();
        tr.setStyle("-fx-padding: 24");
        r = new GridPane();
        r.setStyle("-fx-border-color: grey; -fx-border-width: 8; -fx-border-radius: 24;");
        r.setAlignment(Pos.CENTER);
        tr.getChildren().add(r);
        Scene sc = new Scene(tr, 700, 200);
        this.setOnShown(event -> {
            this.setX(this.getX() + 200);
            this.setY(this.getY() + 450);
        });
        this.setScene(sc);
        this.setOnCloseRequest(event -> {
            new Utility(communicator, messages, this.getX(), this.getY());
        });
        // Display Screen
        this.setResizable(false);
        this.show();
    }

    public void update() {
        if (messages.size() > 0)
            ((Text) r.getChildren().get(0)).setText(messages.getFirst());
        else {
            if (communicator.isWin()) {
                new Message("--YOU WIN!--", 72);
                communicator.getDisplay().close();
                communicator.getLoadout().close();
                communicator.getUtility().close();
            } else if (communicator.isLoose()) {
                new Message("--YOU LOOSE!--", 64);
                communicator.getDisplay().close();
                communicator.getLoadout().close();
                communicator.getUtility().close();
            } else {
                r.setOnMouseClicked(event -> {
                });
                doThings();
            }
        }
    }

    public void addText(String Text) {
        if (messages.isEmpty()) {
            r.getChildren().clear();
            r.getChildren().add(new Text(Text));
            r.setOnMouseClicked(event -> {
                messages.removeFirst();
                update();
            });
        }
        messages.addLast(Text);
    }

    public void doThings() {
        r.getChildren().clear();
        r.add(new Button("Attack"),0,0);
        r.add(new Button("Inventory"),1,0);
        ((Button)r.getChildren().get(0)).setPrefHeight(100);
        ((Button)r.getChildren().get(1)).setPrefHeight(100);
        ((Button)r.getChildren().get(0)).setPrefWidth(300);
        ((Button)r.getChildren().get(1)).setPrefWidth(300);
        ((Button)r.getChildren().get(0)).setOnMouseClicked(event -> {attack();});
        ((Button)r.getChildren().get(1)).setOnMouseClicked(event -> {equip();});
    }

    public void attack() {
        r.getChildren().clear();
        int i = 0;
        for(Enemy e : communicator.getEnemies()) {
            Button b = new Button(e.getName());
            b.setPrefHeight(100);
            int cur = i;
            b.setOnAction(new EventHandler<ActionEvent>() {
                int index = cur;
                @Override
                public void handle(ActionEvent arg0) {
                    communicator.Command("att p " + index);
                }
            });
            r.add(b,i,0);
            ++i;
        }
    }

    public void equip() {
        r.getChildren().clear();
        int i = 0;
        for(Weapon e : communicator.getWeapons()) {
            Button b = new Button(e.getName());
            b.setPrefHeight(50);
            int cur = i;
            b.setOnAction(new EventHandler<ActionEvent>() {
                int index = cur;
                @Override
                public void handle(ActionEvent arg0) {
                    communicator.Command("use " + index);
                }
            });
            r.add(b,i%(communicator.getWeapons().size()/2),i/(communicator.getWeapons().size()/2));
            ++i;
        }
    }
}