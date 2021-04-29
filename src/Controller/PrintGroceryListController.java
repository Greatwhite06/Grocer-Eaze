package Controller;/*
CS 3443-003: Heena Rathore
Joseph Galvan: QRK815
Trey Jones: LVS888
Victor Danish: KUG872
Cameron Brumblay: BWO509
*/

import javafx.collections.ObservableList;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import Model.*;

/*
    Class purpose: To provide functionality to the print menu to print the list of ingredients for each recipe selected
 */
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
    private TableColumn<Ingredient, String> colCheck;

    @FXML
    private TableColumn<Ingredient, String> colQuantity;

    @FXML
    private TableView<Ingredient> tblPrint;

    /*
        Function: update
        Purpose: to update the print grocery list table with the items that have been selected by the user
        Parameters: ActionEven event (unused here)
     */
    @FXML
    void update(ActionEvent event) {
        tblPrint.getItems().clear();
        ObservableList<Recipe> list;
        list = tblRecipes.getSelectionModel().getSelectedItems();
        ArrayList<Ingredient> ingredients = new ArrayList<>();
        ingredients.clear();

        for(Recipe r: list){
            ingredients.addAll(r.getIngredients());
        }

        ObservableList<Ingredient> observableIngredients = combineItems(ingredients);
        tblPrint.getItems().addAll(observableIngredients);
    }

    /*
        Function: goToMainMenu
        Purpose: To return to main menu screen
        Parameters: ActionEven event (to get the caller window)
     */
    @FXML
    public void goToMainMenu(ActionEvent event) throws IOException {
        mainPane = FXMLLoader.load(getClass().getResource("../View/MainMenu.fxml"));// pane you are GOING TO
        Scene scene = new Scene(mainPane);// pane you are GOING TO show
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();// pane you are ON
        window.setScene(scene);
        window.show();
    }


    /*
        Function: initialize
        Purpose: to initialize cell factories and the items in the upper right corner of menu to print from.
        Parameters: URL location, ResourceBundle resources (not used here)
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //Setup the Print table
        ArrayList<Recipe> recipes = Model.getRecipes();
        colIngredient.setCellValueFactory(new PropertyValueFactory<Ingredient, String>("IngredientName"));
        colQuantity.setCellValueFactory(new PropertyValueFactory<Ingredient, String>("IngredientAmount"));
        colCheck.setCellValueFactory(new PropertyValueFactory<Ingredient, String>("CheckBox"));
        colCheck.setStyle("-fx-alignment: CENTER");

        ObservableList<Recipe> list = FXCollections.observableList(recipes);
        colRecipeTitle.setCellValueFactory(new PropertyValueFactory<Recipe, String>("title"));
        tblRecipes.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        for(Recipe i: list){
            tblRecipes.getItems().add(i);
        }
    }

    private ObservableList<Ingredient> combineItems(ArrayList<Ingredient> origin){
        /*
        ArrayList<Model.Ingredient> combined = new ArrayList<>();
        int o;
        for(o = 0; o < origin.size(); o++){
            Model.Ingredient tempOrigin = origin.get(o);
            for(Model.Ingredient i: combined){
                Model.Ingredient tempCombined = i;
                if(tempOrigin.getIngredientName().equals(tempCombined.getIngredientName()) && tempOrigin.getMeasurement().equals(tempCombined.getMeasurement())){
                    int originAmount = Integer.parseInt(tempOrigin.getIngredientAmount());
                    int combinedAmount = Integer.parseInt(tempCombined.getIngredientAmount());
                    combined.get(combined.indexOf(i)).setIngredientAmount(Integer.toString(originAmount + combinedAmount));
                }else{
                    combined.add(tempOrigin);
                }
            }
        }
        return FXCollections.observableList(combined);
        */
        return FXCollections.observableList(origin); //DELETE
    }

}
