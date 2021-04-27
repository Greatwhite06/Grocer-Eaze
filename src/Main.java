import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
/*
CS 3443-003: Heena Rathore
Joseph Galvan: QRK815
Trey Jones: LVS888
Victor Danish: KUG872
 */

public class Main extends Application {
    //Buttons already change the scenes
    @Override
    public void start(Stage primaryStage) throws Exception{

        AnchorPane root = FXMLLoader.load(getClass().getResource("MainMenu.fxml"));
        Scene scene = new Scene(root, 750, 500);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Grocer-Eaze: Main");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
