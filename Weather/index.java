import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Node;
//mhm zu viele Imports
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class index extends Application {
    // i/o "stream"
    TextField inputTemp;
    TextField inputDate;
    ToggleButton b2;

    Text average;
    FlowPane dataDisplay;
    ArrayList<Node> dataBTC = new ArrayList<Node>(0);
    ArrayList<Long[]> data = new ArrayList<Long[]>(0);


    public void start(Stage s) {
        // Window Setup
        s.setTitle("Weather Collection");

        // Top -> Bottom GUI
        FlowPane r = new FlowPane();
        r.setVgap(8);
        r.setOrientation(Orientation.VERTICAL);

        ScrollPane sp = new ScrollPane();
        sp.setHbarPolicy(ScrollBarPolicy.ALWAYS);
        sp.setVbarPolicy(ScrollBarPolicy.NEVER);
        sp.setFitToWidth(true);
        sp.setMaxWidth(1000);
        sp.setMinWidth(1000);

        dataDisplay = new FlowPane(Orientation.VERTICAL);
        dataDisplay.setVgap(8);
        dataDisplay.setAlignment(Pos.TOP_LEFT);
        dataDisplay.setPrefWrapLength(550);
        sp.setContent(dataDisplay);


        FlowPane inputs = new FlowPane(Orientation.HORIZONTAL);
        inputs.setAlignment(Pos.TOP_LEFT);

        inputDate = new TextField("Date");
        inputDate.setOnKeyTyped(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent e) {
                hideNotNeeded(); // Create KeyHandler, Handler then redirects the action to hideNotNeeded()
            }
        });
        inputTemp = new TextField("Temp");
        
        inputs.getChildren().add(inputTemp);
        inputs.getChildren().add(inputDate);


        FlowPane buttons = new FlowPane(Orientation.HORIZONTAL);
        buttons.setAlignment(Pos.TOP_LEFT);
        // Buttons
        Button b1 = new Button("Add");
        b1.setMinWidth(100);
        b1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                addWeather(); // Create ClickHandler, Handler then redirects the action to addWeather()
            }
        });
        // Buttons
        b2 = new ToggleButton("Search");
        b2.setMinWidth(100);
        b2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                hideNotNeeded(); // Create ClickHandler, Handler then redirects the action to hideNotNeeded()
            }
        });
        buttons.getChildren().add(b1);
        buttons.getChildren().add(b2);

        average = new Text();
        calculateAverage();


        r.getChildren().add(sp);
        r.getChildren().add(inputs);
        r.getChildren().add(buttons);
        r.getChildren().add(average);



        Scene sc = new Scene(r, 1000, 700);
        s.setScene(sc);
        Platform.setImplicitExit(true);
        // Display Screen
        s.setResizable(false);
        s.show();
    }

    void calculateAverage() {
        long result = 0;
        int max = 0;
        if (b2.isSelected()) {
            try {
                long date = new SimpleDateFormat("dd/MM/yyyy").parse(inputDate.getText()).getTime();
                for (int i = 0; i < data.size(); ++i)
                    if (data.get(i)[0] == date) {
                        result += data.get(i)[1];
                        max++;
                    }
            } catch (ParseException e) {
                average.setText("ERROR! please use format: dd/MM/yyyy");
            }
        } else {
            for (int i = 0; i < data.size(); ++i) {
                result += data.get(i)[1];
                max++;
            }
        }
        if(max == 0) {
            average.setText("NO DATA");
        } else {
            average.setText("The Average Temperature" + ((b2.isSelected())?"in the Specified Range is: ":" is: ") + (result / (double)max) + "*C");
        }
    }

    void hideNotNeeded() {
        if (b2.isSelected()) {
            try {
                long date = new SimpleDateFormat("dd/MM/yyyy").parse(inputDate.getText()).getTime();
                dataDisplay.getChildren().clear();
                for (int i = 0; i < data.size(); ++i) {
                    if (data.get(i)[0] == date)
                        dataDisplay.getChildren().add(dataBTC.get(i));
                }
                calculateAverage();
            } catch (ParseException e) {
                average.setText("ERROR! please use format: dd/MM/yyyy");
            }
        } else {
            dataDisplay.getChildren().clear();
            for (int i = 0; i < dataBTC.size(); ++i) {
                dataDisplay.getChildren().add(dataBTC.get(i));
            }
            calculateAverage();
        }
    }

    void createDisplay(int i) {
        FlowPane newPane = new FlowPane(Orientation.HORIZONTAL);
        newPane.setAlignment(Pos.TOP_LEFT);
        newPane.setHgap(8);
        Text date = new Text((new Date(data.get(i)[0])).toString());
        Text temperature = new Text(data.get(i)[1] + "*C");
        Button deletethis = new Button("Delete");
        deletethis.setOnAction(new EventHandler<ActionEvent>() {
            FlowPane Parent = newPane;

            @Override
            public void handle(ActionEvent e) {
                int pos = dataBTC.indexOf(Parent);
                data.remove(pos);
                dataBTC.remove(pos);
                hideNotNeeded();
            }
        });
        newPane.getChildren().add(date);
        newPane.getChildren().add(temperature);
        newPane.getChildren().add(deletethis);
        dataBTC.add(newPane);
        hideNotNeeded();
    }

    void addWeather() {
        try {
            data.add(new Long[] { new SimpleDateFormat("dd/MM/yyyy").parse(inputDate.getText()).getTime(),
                    Long.parseLong(inputTemp.getText()) });
            createDisplay(data.size() - 1);
        } catch (NumberFormatException | ParseException e) {
            average.setText("ERROR! please use format: dd/MM/yyyy");
        }
    }

    public static void main(String args[]) {
        launch(args); // launch
    }
}