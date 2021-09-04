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

    //DATA
    ArrayList<Node> dataBTC = new ArrayList<Node>(0);
    ArrayList<Long[]> data = new ArrayList<Long[]>(0);

    //Parser
    SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");

    public void start(Stage s) {
        // Window Setup
        s.setTitle("Weather Collection");

        // Top -> Bottom GUI
        FlowPane r = new FlowPane();
        r.setVgap(8);
        r.setOrientation(Orientation.VERTICAL);

        //Overflow handle
        ScrollPane sp = new ScrollPane();
        sp.setHbarPolicy(ScrollBarPolicy.ALWAYS);
        sp.setVbarPolicy(ScrollBarPolicy.NEVER);
        sp.setFitToWidth(true);
        sp.setMaxWidth(1000);
        sp.setMinWidth(1000);

        //Main output display
        dataDisplay = new FlowPane(Orientation.VERTICAL);
        dataDisplay.setVgap(8);
        dataDisplay.setAlignment(Pos.TOP_LEFT);
        dataDisplay.setPrefWrapLength(550);
        sp.setContent(dataDisplay);

        // Text inputs LEFT -> RIGHT
        FlowPane inputs = new FlowPane(Orientation.HORIZONTAL);
        inputs.setAlignment(Pos.CENTER);

        inputDate = new TextField("1/1/1");
        inputDate.setPromptText("Date");
        inputDate.setOnKeyTyped(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent e) {
                hideNotNeeded(); // Create KeyHandler, Handler then redirects the action to hideNotNeeded() USED FOR SEARCH MAINLY
            }
        });

        inputTemp = new TextField("");
        inputTemp.setPromptText("Temp");
        //Regex remove !0-9 ....
        inputTemp.setOnKeyTyped(event -> {
            if(!inputTemp.getText().matches("\\d*")) inputTemp.setText(inputTemp.getText().replaceAll("[^\\d]",""));
        });
        
        inputs.getChildren().add(inputTemp);
        inputs.getChildren().add(inputDate);

        //Buttons LEFT -> RIGHT
        FlowPane buttons = new FlowPane(Orientation.HORIZONTAL);
        buttons.setAlignment(Pos.CENTER);

        Button b1 = new Button("Add");
        b1.setMinWidth(100);
        b1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                addWeather(); // Create ClickHandler, Handler then redirects the action to addWeather()
            }
        });

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
        //FILL THE TEXT IMEDIATELY, makes the UI look cleaner without actually being clean
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
            //Selective average
            try {
                long date = format.parse(inputDate.getText()).getTime();
                //(a0+a1...a(n-1)+a(n))/n
                for (int i = 0; i < data.size(); ++i)
                    if (data.get(i)[0] == date) {
                        result += data.get(i)[1];
                        max++;
                    }
            } catch (ParseException e) {
                average.setText("ERROR! please use format: " + format.toPattern());
            }
        } else {
            //(a0+a1...a(n-1)+a(n))/n
            for (int i = 0; i < data.size(); ++i) {
                result += data.get(i)[1];
                max++;
            }
        }
        if(max == 0) {
            // 0 / 0 == ERROR
            average.setText("NO DATA");
        } else {
            average.setText("The Average Temperature" + ((b2.isSelected())?"in the Specified Range is: ":" is: ") + (result / (double)max) + "*C");
        }
    }

    void hideNotNeeded() {
        if (b2.isSelected()) {
            try {
                long date = format.parse(inputDate.getText()).getTime();
                dataDisplay.getChildren().clear();
                //add the Nodes that fit the search
                for (int i = 0; i < data.size(); ++i) {
                    if (data.get(i)[0] == date)
                        dataDisplay.getChildren().add(dataBTC.get(i));
                }
                calculateAverage();
            } catch (ParseException e) {
                average.setText("ERROR! please use format: " + format.toPattern());
            }
        } else {
            dataDisplay.getChildren().clear();
            //add all Nodes
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
        Text date = new Text(format.format(new Date(data.get(i)[0])));
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
            //Add data to "data"
            data.add(new Long[] {
                format.parse(inputDate.getText()).getTime(),
                Long.parseLong(inputTemp.getText()) 
            });
            //Add data to display
            createDisplay(data.size() - 1);
        } catch (NumberFormatException | ParseException e) {
            average.setText("ERROR! please use format: " + format.toPattern());
        }
    }

    public static void main(String args[]) {
        launch(args); // launch
    }
}