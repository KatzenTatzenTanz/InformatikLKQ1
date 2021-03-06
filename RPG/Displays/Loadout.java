package Displays;
import Statics.Manager;
import Structs.Usable;

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

    private VBox r = new VBox();
    public Loadout(double x, double y) {
        this();
        this.setX(x);
        this.setY(y);
    }
    
    public Loadout() {
        //create array of loadout...
        super();
        Manager.C.setLoadout(this);
        Manager.C.addWindow(this);
        // Window Setup
        this.setTitle("Inventory");
        r.setSpacing(24);
        update();
        ScrollPane tr = new ScrollPane(r);
        tr.setHbarPolicy(ScrollBarPolicy.NEVER);

        Scene sc = new Scene(tr, 400, 700);
        //moves the windows into positions where they will look nicer
        this.setOnShown(event -> {
            this.setX(this.getX() - 450);
            this.setY(this.getY());
        });
        //unclose window
        this.setOnCloseRequest(event -> {
            new Loadout(this.getX(),this.getY());
        });

        this.setScene(sc);
        this.setResizable(false);
        this.show();
    }


    public void update() {
        //update the entire array, happens very rarely
        r.getChildren().clear();
        for (Usable we : Manager.C.getInventory()) {
            r.getChildren().add(we.toUI());
        }
        r.setAlignment(Pos.TOP_LEFT);
    }
}