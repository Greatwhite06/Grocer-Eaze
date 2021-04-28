/*
CS 3443-003: Heena Rathore
Joseph Galvan: QRK815
Trey Jones: LVS888
Victor Danish: KUG872
Cameron Brumblay: BWO509
*/
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
import java.util.ArrayList;
import java.util.ResourceBundle;

public class AddRecipeMenuController implements Initializable {

    @FXML
    public TableView<Ingredient> tableview;
    @FXML
    public TableColumn<Ingredient, String> colIngredient;
    @FXML
    public TableColumn<Ingredient, String> colAmount;
    @FXML
    public TextField txtfieldIngredient;
    @FXML
    public TextField txtfieldQuantity;
    @FXML
    public TextField txtfieldName;

    @FXML
    AnchorPane mainPane;

    /*
     * Add Ingredient Button
     * When this button is clicked, it will take the string data entered in the ingredient field and amount field
     * and populate it in the table, showing the current ingredients you have.
     */
    @FXML
    void buttonAdd(ActionEvent event) {
        Ingredient ingredient = new Ingredient(txtfieldIngredient.getText(), txtfieldQuantity.getText());
        tableview.getItems().add(ingredient);
        txtfieldIngredient.clear();
        txtfieldQuantity.clear();
    }

    /*
     * Delete Ingredient Button
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
     * Add Recipe Button
     * This will create and write a recipe to the .txt file
     * The recipe will be appended to the end of the .txt file
     * A temporary ArrayList of Recipes is created so only 1 recipe is added vs accessing the static recipes ArrayList
     * A for loop runs through the size of the table and adds the ingredients to the tempRecipe
     * Once populated the temp ArrayList is appended to the .txt file using the writeRecipeData function to ensure proper
     * formatting for reading back later.
     */
    @FXML
    void addRecipe(ActionEvent event) throws IOException {

        ArrayList<Recipe> tempList = Model.getRecipes();

        String title = txtfieldName.getText();
        Recipe tempRecipe = new Recipe(title);

        for (int i = 0; i < tableview.getItems().size(); i++) {
            tempRecipe.addIngredient(
                    tableview.getItems().get(i).getIngredientName(),
                    tableview.getItems().get(i).getIngredientAmount());
        }
        tempList.add(tempRecipe);

        Model.writeRecipeData(tempList);

        txtfieldName.clear();
        tableview.getItems().clear();
    }

    @FXML
    public void goToHomeMenu(ActionEvent event) throws IOException {
        mainPane = FXMLLoader.load(getClass().getResource("MainMenu.fxml"));// pane you are GOING TO
        Scene scene = new Scene(mainPane);// pane you are GOING TO show
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();// pane you are ON
        window.setScene(scene);
        window.show();
    }

    //currently not implemented/used
    @FXML
    void searchRecipe(ActionEvent event) {

    }

    //currently not implemented/used
    @FXML
    void updateRecipe(ActionEvent event) {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        colIngredient.setCellValueFactory(new PropertyValueFactory<>("IngredientName"));
        colAmount.setCellValueFactory(new PropertyValueFactory<>("IngredientAmount"));
        //tableview.setItems(observableList);

        tableview.setEditable(true);
        colIngredient.setCellFactory(TextFieldTableCell.forTableColumn());
        colAmount.setCellFactory(TextFieldTableCell.forTableColumn());
    }

    /*ObservableList<Ingredient> observableList = FXCollections.observableArrayList(
            new Ingredient("Chocolate", "2 Bars")
    );*/
}
