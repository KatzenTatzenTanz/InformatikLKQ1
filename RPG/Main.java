import Displays.EnemySelection;

import javafx.application.Application;
import javafx.stage.Stage;
/**
 * index
 */
public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage arg0) {
        new EnemySelection();
    }
}