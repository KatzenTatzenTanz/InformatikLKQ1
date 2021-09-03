import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
//mhm zu viele Imports
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class index extends Application {
    // i/o "stream"
    TextField input;
    Text output;
    Scene sc;
    Stage s;

    //HIDDEN NUMBER OOOOOOOOOOO
    int rand = (int)(Math.random()*10);

    public void start(Stage s)
    {
        //Window Setup
        s.setTitle("GUESS THE NUMBER!");

        //Top -> Bottom GUI
        FlowPane r = new FlowPane();
        r.setVgap(20);
        r.setOrientation(Orientation.VERTICAL);
        r.setAlignment(Pos.CENTER);

        //Declare IO
        input = new TextField("Enter a Number:");
        //Regex remove !0-9 ....
        input.setOnKeyTyped(event -> {
            if(!input.getText().matches("\\d*")) input.setText(input.getText().replaceAll("[^\\d]",""));
        });

        output = new Text("Output:\n");
        output.maxWidth(500);
        output.setWrappingWidth(500);
        output.maxHeight(300);
        output.minHeight(300);

        //Buttons
        Button b1 = new Button("Test");
        b1.setMinWidth(100);
        b1.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                fun();  //Create ClickHandler, Handler then redirects the action to a()
            }
        });
        
        //add Objects to Main GUI Flow
        r.getChildren().add(input);
        r.getChildren().add(output);
        r.getChildren().add(b1);

        sc = new Scene(r, 600, 400);
        s.setScene(sc);
        Platform.setImplicitExit(true);
        this.s = s;
        //Display Screen
        s.show();
    }

    void fun() {
        //Convert Input to Integer, we know that the number will always be parsed and positive since only 0-9 is allowed
        int i = Integer.parseInt(input.getText());
        if(i == rand)
            s.hide();
        else
            output.setText(output.getText() + i +
            " That was: " + 
            new String[] {
                "Weniger",
                "NICHT MOEGLICH ZU BEKOMMEN",
                "Mehr"
            }[(i-rand)/Math.abs(i-rand) + 1] + " als die Magische Zahl\n"); // Es Exestiert kein <=> operator in java, wir wissen das i != rand von daher wird es keine Fehler geben
    }
  
    public static void main(String args[]) {
        launch(args); //launch
    }
}