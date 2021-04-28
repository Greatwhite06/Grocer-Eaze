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
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
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
    private TableColumn<Ingredient, String> colIngredient;

    @FXML
    private TableColumn<Ingredient, String> colQuantity;

    @FXML
    private Button btnUpdate;

    @FXML
    private TableView<Ingredient> tblPrint;

    @FXML
    private Button btnHome;

    @FXML
    void update(ActionEvent event) {
        ObservableList<Recipe> list= tblRecipes.getSelectionModel().getSelectedItems();
        for(Recipe r: list){
            System.out.println(r.getTitle());
            tblPrint.getItems().addAll(r.getObservableIngredients());
        }
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
        //Setup the Print table
        colIngredient.setCellValueFactory(new PropertyValueFactory<Ingredient, String>("IngredientName"));
        colQuantity.setCellValueFactory(new PropertyValueFactory<Ingredient, String>("IngredientAmount"));

        ArrayList<Recipe> recipes = Model.getRecipes();
        ObservableList<Recipe> list = FXCollections.observableList(recipes);
        colRecipeTitle.setCellValueFactory(new PropertyValueFactory<Recipe, String>("title"));
        tblRecipes.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        for(Recipe i: list){
            tblRecipes.getItems().add(i);
        }
    }

}
