package Displays;

import java.util.Vector;

import Statics.Manager;
import Structs.Enemy;
import Structs.Usable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
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
    private Vector<String> messages = new Vector<String>();
    private int position;
    private GridPane r;

    private Utility(Vector<String> messages, int po, double x, double y) {
        this();
        position = po;
        if(messages.size() > 0)
            addText(messages.get(0));
        else
            update();
        Manager.C.setUtility(this);
        this.messages = messages;
        this.setX(x);
        this.setY(y);
    }

    public Utility() {
        super();
        Manager.C.addWindow(this);
        Manager.C.setUtility(this);
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
            new Utility(messages, position, this.getX(), this.getY());
        });
        // Display Screen
        this.setResizable(false);
        this.show();
    }

    public void update() {
        if (messages.size() > 0)
            ((Text) r.getChildren().get(0)).setText(messages.get(position));
        else {
            if (Manager.C.isWin()) {
                new Message("--YOU WIN!--", 72);
                Manager.C.getDisplay().close();
                Manager.C.getLoadout().close();
                Manager.C.getUtility().close();
            } else if (Manager.C.isLoose()) {
                new Message("--YOU LOOSE!--", 64);
                Manager.C.getDisplay().close();
                Manager.C.getLoadout().close();
                Manager.C.getUtility().close();
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
                position++;
                update();
            });
            r.setOnKeyPressed(event -> {
                if(event.getCode() == KeyCode.ESCAPE) {
                    if(position > 0)
                        position--;
                    update();
                }
                if(event.getCode() == KeyCode.SPACE) {
                    position++;
                    update();
                }
            });
        }
        messages.add(Text);
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
        for(Enemy e : Manager.C.getEnemies()) {
            Button b = new Button(e.getName());
            b.setPrefHeight(100);
            int cur = i;
            b.setOnAction(new EventHandler<ActionEvent>() {
                int index = cur;
                @Override
                public void handle(ActionEvent arg0) {
                    Manager.C.command("att p " + index);
                }
            });
            r.add(b,i,0);
            ++i;
        }
    }

    public void equip() {
        r.getChildren().clear();
        int i = 0;
        for(Usable e : Manager.C.getInventory()) {
            Button b = new Button(e.getName());
            b.setPrefHeight(50);
            int cur = i;
            b.setOnAction(new EventHandler<ActionEvent>() {
                int index = cur;
                @Override
                public void handle(ActionEvent arg0) {
                    Manager.C.command("use " + index);
                }
            });
            r.add(b,i%(Manager.C.getInventory().size()/2),i/(Manager.C.getInventory().size()/2));
            ++i;
        }
    }
}