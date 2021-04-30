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

    //used for defined measurements for the menu
    ObservableList<String> measurementList = FXCollections.observableArrayList(
            "tsp", "tbsp", "cup", "oz", "pt", "qt", "gal", "lb");

    @FXML
    AnchorPane mainPane;

    /*
         * Function: buttonAdd
         * Purpose: When this button is clicked, it will take the string data entered in the ingredient field and amount field
           and populate it in the table, showing the current ingredients you have.
         * Parameters: ActionEvent event
     */
    @FXML
    void buttonAdd(ActionEvent event) {
        if(txtfieldName.getText().isEmpty()){
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setContentText("Please Enter a recipe title");
            a.showAndWait();
            return;
        }
        //Checks for text fields being empty
        if(txtfieldIngredient.getText().isEmpty()){
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setContentText("Please enter an ingredient");
            a.showAndWait();
            return;
        }
        if(txtfieldQuantity.getText().isEmpty()) {
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setContentText("Please enter a quantity");
            a.showAndWait();
            return;
        }



        if(measurementBox.getValue().toString().equals("")) {
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setContentText("Please select a unit of measurement");
            a.showAndWait();
            return;
        }

        //make sure value entered is a number
        try{
            Double.parseDouble(txtfieldQuantity.getText());
            //Integer.parseInt(txtfieldQuantity.getText());
        } catch(NumberFormatException n){
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setContentText("Please enter a number for the quantity");
            a.showAndWait();
            return;
        }

        Ingredient ingredient = new Ingredient(txtfieldIngredient.getText(), txtfieldQuantity.getText(), measurementBox.getValue().toString());
        tableview.getItems().add(ingredient);
        txtfieldIngredient.clear();
        txtfieldQuantity.clear();
    }

    /*
         * Function: deleteRecipe
         * Purpose: When this button is clicked, it will take the string data entered in the ingredient field and amount field
           and populate it in the table, showing the current ingredients you have.
         * Parameters: ActionEvent event
     */
    @FXML
    void deleteRecipe(ActionEvent event) throws IOException {

        Alert a = new Alert(Alert.AlertType.CONFIRMATION);
        a.setTitle("Delete Recipe");
        a.setContentText("Are you sure you want to delete?");
        Optional<ButtonType> result = a.showAndWait();
        ButtonType button = result.orElse(ButtonType.CANCEL);

        if (button == ButtonType.OK) {
            Recipe r = tblRecipe.getSelectionModel().getSelectedItem();
            ArrayList<Recipe> recipes = Model.getRecipes();
            recipes.remove(r);
            Model.writeRecipeData(recipes); //update list in file
            updateRecipeTable();
        }
    }

    /*
         * Function: buttonDelete
         * Purpose: Once row is selected, clicking the button will remove the ingredient data from that row.
           Makes for easier recipe editing.
         * Parameters: ActionEvent actionEvent
     */
    @FXML
    public void buttonDelete(ActionEvent actionEvent) {
        ObservableList<Ingredient>  allIngredient, SingleIngredient;
        allIngredient = tableview.getItems();
        SingleIngredient = tableview.getSelectionModel().getSelectedItems();
        SingleIngredient.forEach(allIngredient::remove);
    }

    /*
       Function: changeIngredient
       Purpose: Allows modification of ingredient from within table
       Parameters TableColumn.CellEditevent (allows passing of cell object to modify text)
     */
    @FXML
    public void changeIngredient(TableColumn.CellEditEvent<Ingredient, String> ingredientStringCellEditEvent) {
        if(ingredientStringCellEditEvent.getNewValue().equals(""))
            return;
        Ingredient ingredient = tableview.getSelectionModel().getSelectedItem();
        ingredient.setIngredientName(ingredientStringCellEditEvent.getNewValue());
        tableview.getItems().remove(ingredient); //remove old
        tableview.getItems().add(ingredient); //set new
    }

    /*
       Function: changeAmount
       Purpose: Allows modification of ingredient from within table
       Parameters TableColumn.CellEditevent (allows passing of cell object to modify text)
     */
    @FXML
    public void changeAmount(TableColumn.CellEditEvent<Ingredient, String> ingredientStringCellEditEvent) {
        //make sure value entered is a number
        try{
            Double.parseDouble((ingredientStringCellEditEvent.getNewValue()));
        } catch(NumberFormatException n){
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setContentText("Please enter a number for the quantity");
            a.showAndWait();
            return;
        }
        if(ingredientStringCellEditEvent.getNewValue().equals(""))
            return;
        Ingredient ingredient = tableview.getSelectionModel().getSelectedItem();
        ingredient.setIngredientAmount(ingredientStringCellEditEvent.getNewValue());
        tableview.getItems().remove(ingredient); //remove old
        tableview.getItems().add(ingredient); //set new
    }

    /*
       Function: changeMeasurement
       Purpose: Allows modification of ingredient from within table
       Parameters TableColumn.CellEditevent (allows passing of cell object to modify text)
     */
    @FXML
    public void changeMeasurement(TableColumn.CellEditEvent<Ingredient, String> ingredientStringCellEditEvent) {
        if(ingredientStringCellEditEvent.getNewValue().equals(""))
            return;
        Ingredient ingredient = tableview.getSelectionModel().getSelectedItem();
        if(measurementList.contains(ingredientStringCellEditEvent.getNewValue())){
            ingredient.setIngredientMeasurement(ingredientStringCellEditEvent.getNewValue());
            tableview.getItems().remove(ingredient); //remove old
            tableview.getItems().add(ingredient); //set new
            return;
        }
        Alert a = new Alert(Alert.AlertType.ERROR);
        a.setContentText("Please type a valid of measurement");
        a.showAndWait();
    }

    /*
        Function: addRecipe
        Purpose: adds recipe from the input to the table and writes it to the model class too.
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

                Alert a = new Alert(Alert.AlertType.CONFIRMATION);
                a.setTitle("Add/Update Recipe Confirmation");
                a.setHeaderText("Recipe exists, would you like to update existing recipe?");
                Optional<ButtonType> result = a.showAndWait();

                //ok button, when user clicks ok the old recipe in the arraylist is removed and a new one is created
                //with all the data currently in the ingredient tableview
                if (result.get() == ButtonType.OK) {
                    tempList.remove(i); //user wishes to overwrite it, so remove the existing one
                }else {
                    return; //otherwise return and don't add
                }
            }
        }
        //Add recipe in the table.
        for (int i = 0; i < tableview.getItems().size(); i++) {
            tempRecipe.addIngredient(
                    tableview.getItems().get(i).getIngredientName(),
                    tableview.getItems().get(i).getIngredientAmount(),
                    tableview.getItems().get(i).getMeasurement());
        }
        tempList.add(tempRecipe);
        Model.writeRecipeData(tempList);
        txtfieldName.clear();
        tableview.getItems().clear();
        updateRecipeTable();
    }

    /*
        Function:  goToHomeMenu
        Purpose: to return to homescreen
        Parameters: ActionEvent event
     */
    @FXML
    public void goToHomeMenu(ActionEvent event) throws IOException {
        mainPane = FXMLLoader.load(getClass().getResource("../View/MainMenu.fxml"));// pane you are GOING TO
        Scene scene = new Scene(mainPane);// pane you are GOING TO show
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();// pane you are ON
        window.setScene(scene);
        window.show();
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

    /*
        Function: initialize
        Purpose: to initialize cell factories and the items in the upper right corner of menu to print from.
        Parameters: URL location, ResourceBundle resources (not used here)
    */
    @Override
    @SuppressWarnings( "deprecation" )
    public void initialize(URL location, ResourceBundle resources) {
        colRecipe.setCellValueFactory(new PropertyValueFactory<>("title"));
        colIngredient.setCellValueFactory(new PropertyValueFactory<>("IngredientName"));
        colAmount.setCellValueFactory(new PropertyValueFactory<>("IngredientAmount"));
        colMeasurement.setCellValueFactory(new PropertyValueFactory<>("Measurement"));

        colIngredient.impl_setReorderable(false);
        colAmount.impl_setReorderable(false);
        colMeasurement.impl_setReorderable(false);

        ObservableList<Recipe> list = FXCollections.observableList(Model.getRecipes());
        for(Recipe r: list){
            tblRecipe.getItems().add(r);
        }

        measurementBox.setValue("");
        measurementBox.setItems(measurementList);

        tableview.setEditable(true);
        colIngredient.setCellFactory(TextFieldTableCell.forTableColumn());
        colAmount.setCellFactory(TextFieldTableCell.forTableColumn());
        colMeasurement.setCellFactory(TextFieldTableCell.forTableColumn());
    }

    /*
        Function: updateRecipeTable
        Purpose: to refresh the recipe table after modification, adding, or deleting
        Parameters: None
     */
    private void updateRecipeTable(){
        ObservableList<Recipe> newList = FXCollections.observableList(Model.getRecipes());
        tblRecipe.getItems().clear();
        tblRecipe.getItems().addAll(newList);
    }
}
