package Displays;

import javafx.scene.text.Text;
import javafx.scene.text.Font;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.geometry.Pos;
import javafx.scene.Scene;

public class Message extends Stage {
    public Message(String txt, int s) {
        this.setTitle(txt);
        Text t = new Text(txt);
        t.setFont(new Font("Arial",s));
        StackPane r = new StackPane(t);
        r.setAlignment(Pos.CENTER);
        Scene sc = new Scene(r, 600, 200);
        this.setScene(sc);
        // Display Screen
        this.show();
    }
}
