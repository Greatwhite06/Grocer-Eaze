import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
/*
CS 3443-003: Heena Rathore
Joseph Galvan: QRK815
Trey Jones: LVS888

 */

public class Main extends Application {
    //Buttons already change the scenes
    @Override
    public void start(Stage primaryStage) throws Exception{
        //adding multiple lines for each person's menu temporarily until we can switch menus with a button.

        //AnchorPane root = FXMLLoader.load(getClass().getResource("MainMenu.fxml"));
        AnchorPane root = FXMLLoader.load(getClass().getResource("PrintGroceryList.fxml"));
        //AnchorPane root = FXMLLoader.load(getClass().getResource("MainMenu.fxml"));

        Scene scene = new Scene(root, 750, 500);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Grocer-Eaze: Main");
        primaryStage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }
}
