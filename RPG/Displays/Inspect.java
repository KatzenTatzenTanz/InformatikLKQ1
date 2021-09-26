package Displays;

import javafx.scene.text.Text;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import Statics.Manager;
import Structs.Inspectable;
import javafx.geometry.Pos;
import javafx.scene.Scene;

public class Inspect extends Stage {
    //display the .toString() of an object
    public Inspect(Object obj) {
        this.setTitle(obj.getClass().getSimpleName());
        StackPane r = new StackPane(new Text(obj.toString()));
        r.setAlignment(Pos.TOP_LEFT);
        Scene sc = new Scene(r, 700, 200);
        this.setScene(sc);
        Manager.C.addWindow(this);
        // Display Screen
        this.show();
    }
    //get the UI of an object if it is possible
    public Inspect(Inspectable ins) {
        this.setTitle(ins.getClass().getSimpleName());
        Scene sc = new Scene(new StackPane(ins.toUI()), 700, 200);
        this.setScene(sc);
        Manager.C.addWindow(this);
        // Display Screen
        this.show();
    }
}
