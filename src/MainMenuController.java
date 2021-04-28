/*
CS 3443-003: Heena Rathore
Joseph Galvan: QRK815
Trey Jones: LVS888
Victor Danish: KUG872
Cameron Brumblay: BWO509
*/
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainMenuController implements Initializable {
    @FXML
    AnchorPane mainPane;

    @FXML
    public void goToPrintGroceryListMenu(ActionEvent event) throws IOException{
        mainPane = FXMLLoader.load(getClass().getResource("PrintGroceryList.fxml"));// pane you are GOING TO
        Scene scene = new Scene(mainPane);// pane you are GOING TO show
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();// pane you are ON
        window.setScene(scene);
        window.show();
    }

    @FXML
    public void goToAddRecipeMenu(ActionEvent event) throws IOException{
        mainPane = FXMLLoader.load(getClass().getResource("AddRecipeMenu.fxml"));// pane you are GOING TO
        Scene scene = new Scene(mainPane);// pane you are GOING TO show
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();// pane you are ON
        window.setScene(scene);
        window.show();
    }

    @FXML
    public void goToOpenCookBookMenu(ActionEvent event) throws IOException {
        mainPane = FXMLLoader.load(getClass().getResource("OpenCookBookMenu.fxml"));// pane you are GOING TO
        Scene scene = new Scene(mainPane);// pane you are GOING TO show
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();// pane you are ON
        window.setScene(scene);
        window.show();
    }
    /*
           Start off the application by reading in data from the file to the static Arraylist for retrieval
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            Model.readRecipeData();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
