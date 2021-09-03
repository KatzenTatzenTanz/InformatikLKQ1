import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
//mhm
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

    public void start(Stage s)
    {
        //Window Setup
        s.setTitle("HomeWork");

        //Top -> Bottom GUI
        FlowPane r = new FlowPane();
        r.setVgap(20);
        r.setOrientation(Orientation.VERTICAL);
        r.setAlignment(Pos.CENTER);

        //Buttons => Horizontal GUI
        FlowPane buttonarray = new FlowPane();
        buttonarray.setOrientation(Orientation.HORIZONTAL);
        buttonarray.setHgap(50);
        buttonarray.setAlignment(Pos.CENTER);

        //Declare IO
        input = new TextField("Enter a Number");
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
        Button b1 = new Button("a");
        b1.setMinWidth(100);
        b1.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                a();  //Create ClickHandler, Handler then redirects the action to a()
            }
        });
        buttonarray.getChildren().add(b1);
        Button b2 = new Button("b");
        b2.setMinWidth(100);
        b2.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                b(); //Create ClickHandler, Handler then redirects the action to b()
            }
        });
        buttonarray.getChildren().add(b2);
        Button b3 = new Button("c");
        b3.setMinWidth(100);
        b3.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                c(); //Create ClickHandler, Handler then redirects the action to c()
            }
        });
        buttonarray.getChildren().add(b3);
        
        //add Objects to Main GUI Flow
        r.getChildren().add(input);
        r.getChildren().add(output);
        r.getChildren().add(buttonarray);

        Scene sc = new Scene(r, 600, 400);
        s.setScene(sc);
        //Display Screen
        s.show();
    }

    void a() {
        //Convert Input to Integer, we know that the number will always be parsed and positive since only 0-9 is allowed
        int max = Integer.parseInt(input.getText());
        for(int i = 0; i <= max; ++i)
            //loop 0 -> max
            output.setText(output.getText() + i + " ");
        output.setText(output.getText() + "\n");
    }

    void b() {
        //Convert Input to Integer, we know that the number will always be parsed and positive since only 0-9 is allowed
        int max = Integer.parseInt(input.getText());
        for(int i = max; i >= 0; --i)
            //loop max -> 0
            output.setText(output.getText() + i + " ");
        output.setText(output.getText() + "\n");
    }

    void c() {
        //Convert Input to Integer, we know that the number will always be parsed and positive since only 0-9 is allowed
        int max = Integer.parseInt(input.getText());
        for(int x = 0; x <= max; ++x)
            //loop 0 -> max
            for(int y = x; y <= max; ++y)
                //loop x -> max since x*y == y*x
                output.setText(output.getText() + x + " * " + y + " = " + x*y + " ");
        output.setText(output.getText() + "\n");
    }
  
    public static void main(String args[]) {
        launch(args); //launch
    }
}