package Displays;

import Statics.Manager;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.input.KeyCode;
import javafx.scene.text.Text;

public class CONSOLE extends Stage {
    private Text console = new Text();
    private TextField input = new TextField();
    //only display console if needed
    public CONSOLE(boolean show) {
        this.setTitle("Console");
        BorderPane r = new BorderPane();
        ScrollPane sp = new ScrollPane(console);

        sp.setVbarPolicy(ScrollBarPolicy.AS_NEEDED);
        sp.setHbarPolicy(ScrollBarPolicy.NEVER);
        sp.setFitToWidth(true);

        input.setPromptText("Command:");
        input.setOnKeyPressed(event -> {
            //if enter pressed: send command to Comms
            if(event.getCode() == KeyCode.ENTER) {
                log(input.getText());
                Manager.C.command(input.getText());
            }
        });
        console.wrappingWidthProperty().bind(this.widthProperty());
        r.setCenter(sp);
        r.setBottom(input);

        Scene sc = new Scene(r, 600, 800);
        this.setScene(sc);
        // Display Screen
        if(show)
            this.show();
    }

    public void log(String s) {
        console.setText(console.getText() + s + '\n');
    }
}
