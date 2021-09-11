package Displays;
import Structs.Weapon;

import Utility.Comms;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
/**
 * index
 */
public class Loadout extends Stage {

    private Comms communicator;
    public Loadout(Comms arg0, double x, double y) {
        this(arg0);
        this.setX(x);
        this.setY(y);
    }
    
    public Loadout(Comms arg0) {
        super();
        communicator = arg0;
        communicator.setLoadout(this);
        // Window Setup
        this.setTitle("Inventory");
        VBox r = new VBox();
        r.setSpacing(24);
        for (Weapon we : communicator.getWeapons()) {
            r.getChildren().add(we.toUI());
        }
        r.setAlignment(Pos.TOP_LEFT);

        ScrollPane tr = new ScrollPane(r);
        tr.setHbarPolicy(ScrollBarPolicy.NEVER);

        Scene sc = new Scene(tr, 400, 700);
        this.setOnShown(event -> {
            this.setX(this.getX() - 450);
            this.setY(this.getY());
        });
        this.setScene(sc);
        this.setOnCloseRequest(event -> {
            new Loadout(communicator,this.getX(),this.getY());
        });
        // Display Screen
        this.setResizable(false);
        this.show();
    }
}