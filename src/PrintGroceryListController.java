import com.sun.prism.paint.Color;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class PrintGroceryListController implements Initializable {

    @FXML
    AnchorPane mainPane;

    @FXML
    private TableView<Recipe> tblRecipes;

    @FXML
    private TableColumn<Recipe, String> colRecipeTitle;

    @FXML
    private Button btnUpdate;

    @FXML
    private TableView<Recipe> tblPrint;

    @FXML
    private Button btnHome;

    @FXML
    void update(ActionEvent event) {

    }

    @FXML
    public void goToMainMenu(ActionEvent event) throws IOException {
        mainPane = FXMLLoader.load(getClass().getResource("MainMenu.fxml"));// pane you are GOING TO
        Scene scene = new Scene(mainPane);// pane you are GOING TO show
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();// pane you are ON
        window.setScene(scene);
        window.show();
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ArrayList<Recipe> recipes = Model.getRecipes();
        ObservableList<Recipe> list = FXCollections.observableList(recipes);

        //Test to make sure that the recipes ArrayList was populated correctly
        /*
        for(Recipe recipe: recipes){
            recipe.printRecipe();
            System.out.println("\n");
        }
        */
    }

}
