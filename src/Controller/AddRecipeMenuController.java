package Controller;
/*
CS 3443-003: Heena Rathore
Joseph Galvan: QRK815
Trey Jones: LVS888
Victor Danish: KUG872
Cameron Brumblay: BWO509
Wesley Jackson : ydh648
*/
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import java.io.*;
import java.net.URL;
import java.util.*;

import Model.*;

public class AddRecipeMenuController implements Initializable {

    @FXML
    public TableView<Ingredient> tableview;
    @FXML
    public TableColumn<Ingredient, String> colIngredient;
    @FXML
    public TableColumn<Ingredient, String> colAmount;
    @FXML
    public TableColumn<Ingredient, String> colMeasurement;
    @FXML
    public TextField txtfieldIngredient;
    @FXML
    public TextField txtfieldQuantity;
    @FXML
    public TextField txtfieldName;
    @FXML
    public ChoiceBox measurementBox;

    public TableView<Recipe> tblRecipe;

    public TableColumn<Recipe, String> colRecipe;

    ObservableList<String> measurementList = FXCollections.observableArrayList(
            "measurement", "tsp", "tbsp", "cup", "oz", "pt", "qt", "gal", "lb");

    @FXML
    AnchorPane mainPane;

    /*
     * Add Model.Ingredient Button
     * When this button is clicked, it will take the string data entered in the ingredient field and amount field
     * and populate it in the table, showing the current ingredients you have.
     */
    @FXML
    void buttonAdd(ActionEvent event) {
        if(measurementBox.getValue().toString() == "measurement") {
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setContentText("Please select a unit of measurement");
            a.showAndWait();
            return;
        }
        Ingredient ingredient = new Ingredient(txtfieldIngredient.getText(), txtfieldQuantity.getText(), measurementBox.getValue().toString());
        tableview.getItems().add(ingredient);
        txtfieldIngredient.clear();
        txtfieldQuantity.clear();
    }

    /*
     * Delete Model.Ingredient Button
     * Before clicking this button, you must select a row in the ingredient table.
     * Once row is selected, clicking the button will remove the ingredient data from that row.
     * Makes for easier recipe editing.
     */
    @FXML
    public void buttonDelete(ActionEvent actionEvent) {
        ObservableList<Ingredient>  allIngredient, SingleIngredient;
        allIngredient = tableview.getItems();
        SingleIngredient = tableview.getSelectionModel().getSelectedItems();
        SingleIngredient.forEach(allIngredient::remove);
    }

    /*
     * Allows users to directly select an ingredient cell from the table and edit it
     */
    @FXML
    public void changeIngredient(TableColumn.CellEditEvent<Ingredient, String> ingredientStringCellEditEvent) {
        Ingredient ingredient = tableview.getSelectionModel().getSelectedItem();
        ingredient.setIngredientName(ingredientStringCellEditEvent.getNewValue());
    }

    /*
     * Allows users to directly select an amount cell from the table and edit it
     */
    @FXML
    public void changeAmount(TableColumn.CellEditEvent<Ingredient, String> ingredientStringCellEditEvent) {
        Ingredient ingredient = tableview.getSelectionModel().getSelectedItem();
        ingredient.setIngredientAmount(ingredientStringCellEditEvent.getNewValue());
    }

    /*
     * Add Model.Recipe Button
     * This will create and write a recipe to the .txt file
     * A temporary ArrayList of Recipes is created so only 1 recipe is added vs accessing the static recipes ArrayList
     * A for loop runs through the size of the table and adds the ingredients to the tempRecipe
     * Once populated the temp ArrayList written to the .txt file using the writeRecipeData function to ensure proper
     * formatting for reading back later.
     */
    @FXML
    void addRecipe(ActionEvent event) throws IOException {

        //import list of recipes
        ArrayList<Recipe> tempList = Model.getRecipes();
        //set title to user input from recipe name textbox
        String title = txtfieldName.getText();
        //creating a shell for the user input recipe
        Recipe tempRecipe = new Recipe(title);

        if(txtfieldName.getText().isEmpty()) {
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setContentText("Please enter a recipe name");
            a.showAndWait();
            return;
        }

        //looping through recipes to check for existence
        for(int i = 0; i < tempList.size(); i++ ) {
            if(tempList.get(i).getTitle().equals(title)) {
                System.out.println(title + " exists: " +tempList.get(i).getTitle().equals(title) );

                Alert a = new Alert(Alert.AlertType.CONFIRMATION);
                a.setTitle("Add/Update Recipe Confirmation");
                a.setHeaderText("This Recipe Already Exists!");
                a.setContentText("Saving will overwrite recipe contents. Continue?");
                Optional<ButtonType> result = a.showAndWait();

                //ok button, when user clicks ok the old recipe in the arraylist is removed and a new one is created
                //with all the data currently in the ingredient tableview
                if(result.get() == ButtonType.OK) {
                    System.out.println("you selected okay");
                    tempList.remove(i);
                    for (i = 0; i < tableview.getItems().size(); i++) {
                        tempRecipe.addIngredient(
                                tableview.getItems().get(i).getIngredientName(),
                                tableview.getItems().get(i).getIngredientAmount(),
                                tableview.getItems().get(i).getMeasurement());
                    }
                    tempList.add(tempRecipe);
                    Model.writeRecipeData(tempList);
                    break;
                }
                //cancel button, when user clicks the cancel button the addRecipe function is canceled.
                else if (result.get() == ButtonType.CANCEL) {
                    return;
                }
                //if recipe title does not exist in the arraylist a new recipe is added
                else {
                    for (i = 0; i < tableview.getItems().size(); i++) {
                        tempRecipe.addIngredient(
                                tableview.getItems().get(i).getIngredientName(),
                                tableview.getItems().get(i).getIngredientAmount(),
                                tableview.getItems().get(i).getMeasurement());
                    }
                    tempList.add(tempRecipe);
                    Model.writeRecipeData(tempList);
                }
            }
        }
        txtfieldName.clear();
        tableview.getItems().clear();
    }

    @FXML
    public void goToHomeMenu(ActionEvent event) throws IOException {
        mainPane = FXMLLoader.load(getClass().getResource("../View/MainMenu.fxml"));// pane you are GOING TO
        Scene scene = new Scene(mainPane);// pane you are GOING TO show
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();// pane you are ON
        window.setScene(scene);
        window.show();
    }

    /*
        Function: searchRecipe
        Purpose: to search through the arraylist of recipes
        Parameters: ActionEven event (unused here)
     */
    @FXML
    void searchRecipe(ActionEvent event) {

        tblRecipe.getItems().clear();

        if(txtfieldName.getText().isEmpty()) {
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setContentText("Please enter a recipe to search");
            a.showAndWait();
            return;
        }
        ObservableList<Recipe> recipes = Model.getObservableRecipes();
        for( Recipe r : recipes) {
            if( r.getTitle().toLowerCase().contains(txtfieldName.getText().toLowerCase()) ) {
                tblRecipe.getItems().add(r);
            }
            //Need to fix logic. Have to search entire list before displaying message
            /*else {
                Alert a = new Alert(Alert.AlertType.ERROR);
                a.setContentText("Could not find any matches");
                a.showAndWait();
                return;
            }*/
        }
        txtfieldName.clear();
    }

    /*
        Function: updateRecipe
        Purpose: to populate the ingredient table with corresponding data from the recipe table
                 this will allow users to then edit ingredients in the ingredient table before saving
        Parameters: ActionEven event
     */
    @FXML
    void updateRecipe(ActionEvent event)  {
        tableview.getItems().clear();
        ObservableList<Recipe> list = tblRecipe.getSelectionModel().getSelectedItems();
        for (Recipe r : list ) {
            txtfieldName.clear();
            txtfieldName.setText(r.getTitle());
            tableview.getItems().addAll(r.getObservableIngredients());
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        colRecipe.setCellValueFactory(new PropertyValueFactory<>("title"));
        colIngredient.setCellValueFactory(new PropertyValueFactory<>("IngredientName"));
        colAmount.setCellValueFactory(new PropertyValueFactory<>("IngredientAmount"));
        colMeasurement.setCellValueFactory(new PropertyValueFactory<>("Measurement"));
        //tableview.setItems(observableList);
        measurementBox.setValue("measurement");
        measurementBox.setItems(measurementList);

        tableview.setEditable(true);
        colIngredient.setCellFactory(TextFieldTableCell.forTableColumn());
        colAmount.setCellFactory(TextFieldTableCell.forTableColumn());
    }
}
