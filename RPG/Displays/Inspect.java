package Displays;

import javafx.scene.text.Text;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import Structs.Inspectable;
import javafx.geometry.Pos;
import javafx.scene.Scene;

public class Inspect extends Stage {
    public Inspect(Object obj) {
        this.setTitle(obj.getClass().getSimpleName());
        StackPane r = new StackPane(new Text(obj.toString()));
        r.setAlignment(Pos.TOP_LEFT);
        Scene sc = new Scene(r, 700, 200);
        this.setScene(sc);
        // Display Screen
        this.show();
    }

    public Inspect(Inspectable ins) {
        this.setTitle(ins.getClass().getSimpleName());
        Scene sc = new Scene(new StackPane(ins.toUI()), 700, 200);
        this.setScene(sc);
        // Display Screen
        this.show();
    }
}
