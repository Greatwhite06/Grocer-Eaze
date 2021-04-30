package Controller;

import Model.*;
import javafx.collections.ObservableList;
import javafx.collections.FXCollections;
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
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.ResourceBundle;

public class OpenCookBookController implements Initializable {
    @FXML
    private Button clearbtn;

    @FXML
    private Button btnHome;

    @FXML
    private ImageView Home;

    @FXML
    AnchorPane mainPane;

    @FXML
    private TableView<Recipe> tblRecipes;

    @FXML
    private TableColumn<Recipe, String> colRecipeTitle;

    @FXML
    private TableColumn<Ingredient, String> colIngredient;

    @FXML
    private TableColumn<Ingredient, String> colUnit;

    @FXML
    private TableColumn<Ingredient, String> colQuantity;


    @FXML
    private TableView<Ingredient> tblPrint;

    boolean unitChange = false;

    @FXML
    public void goToMainMenu(ActionEvent event) throws IOException {
        mainPane = FXMLLoader.load(getClass().getResource("../View/MainMenu.fxml"));// pane you are GOING TO
        Scene scene = new Scene(mainPane);// pane you are GOING TO show
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();// pane you are ON
        window.setScene(scene);
        window.show();
    }

    @FXML
    void showIngredients(ActionEvent event) {
        tblPrint.getItems().clear();
        ObservableList<Recipe> list = tblRecipes.getSelectionModel().getSelectedItems();
        for (Recipe r : list) {
            tblPrint.getItems().addAll(r.getObservableIngredients());
        }

    }

    @FXML
    void clear(ActionEvent event) {
        tblPrint.getItems().clear();
    }

    /*
        Function: unitConversion
        Purpose: To return to main menu screen
        Parameters: ActionEven event (to get the caller window)
     */
    @FXML
    void unitConversion(ActionEvent event) throws IOException {
        ObservableList<Recipe> tableRecipes = tblRecipes.getSelectionModel().getSelectedItems(); //grab use selected recipes
        if(unitChange){ //boolean for customary-metric conversion
            Model.readRecipeData(); //re-read recipes from recipes.txt file
            tblPrint.getItems().clear(); //clear data already in the printed table
            for(Recipe r: Model.getRecipes()){ //cycle through recipes in recipes.txt file
                for(Recipe t: tableRecipes){ //cycle through user selected recipes
                    if (r.getTitle().equals(t.getTitle())) { //if recipe in file matches user selected recipe
                        tblPrint.getItems().addAll(r.getObservableIngredients()); //place all respective ingredients into the table
                    }
                }
            }
            unitChange = false; //reset boolean
        }else {

            ArrayList<Ingredient> switchIngredients = new ArrayList<>();//array list to hold
            for (Recipe r : tableRecipes) {//iterate through selected recipes
                switchIngredients.addAll(r.getIngredients());//add respective ingredients in array list
            }
            for (Ingredient item : switchIngredients) { // for each ingredient item
                double newAmount; //local variable for ingredient amount
                switch (item.getMeasurement()) { //pull measurement unit associated with each unit
                    case "tsp": //match customary measurement teaspoon
                        newAmount = Double.parseDouble(item.getIngredientAmount()) * 5.0; //conversion tsp-ml/ltr
                        if (newAmount > 1000.0) {//if eligible for next metric step-up
                            item.setIngredientAmount(Double.toString(newAmount / 1000.0));//set new amount
                            item.setIngredientMeasurement("ltr");//set new unit tag
                            break;
                        }
                        item.setIngredientAmount(Double.toString(newAmount));//set new amount
                        item.setIngredientMeasurement("ml");//set new unit tag
                        break;
                    case "tbsp": //match customary measurement tablespoon
                        newAmount = Double.parseDouble(item.getIngredientAmount()) * 15.0;//conversion tbsp-ml/ltr
                        if (newAmount > 1000.0) {//if eligible for next metric step-up
                            item.setIngredientAmount(Double.toString(newAmount / 1000.0));//set new amount
                            item.setIngredientMeasurement("ltr");//set new unit tag
                            break;
                        }
                        item.setIngredientAmount(Double.toString(newAmount));//set new amount
                        item.setIngredientMeasurement("ml");//set new unit tag
                        break;
                    case "cup": //match customary measurement cup
                        newAmount = Double.parseDouble(item.getIngredientAmount()) * 237.0;//conversion cup-ml/ltr
                        if (newAmount > 1000.0) {//if eligible for next metric step-up
                            item.setIngredientAmount(Double.toString(newAmount / 1000.0));//set new amount
                            item.setIngredientMeasurement("ltr");//set new unit tag
                            break;
                        }
                        item.setIngredientAmount(Double.toString(newAmount));//set new amount
                        item.setIngredientMeasurement("ml");//set new unit tag
                        break;
                    case "oz": //match customary measurement ounce
                        newAmount = Double.parseDouble(item.getIngredientAmount()) * 28.0;//conversion oz-g/kg
                        if (newAmount > 1000.0) {//if eligible for next metric step-up
                            item.setIngredientAmount(Double.toString(newAmount / 1000));//set new amount
                            item.setIngredientMeasurement("kg");//set new unit tag
                            break;
                        }
                        item.setIngredientAmount(Double.toString(newAmount));//set new amount
                        item.setIngredientMeasurement("g");//set new unit tag
                        break;
                    case "floz": //match customary measurement fluid ounce
                        newAmount = Double.parseDouble(item.getIngredientAmount()) * 30.0;//conversion floz-ml/ltr
                        if (newAmount > 1000.0) {//if eligible for next metric step-up
                            item.setIngredientAmount(Double.toString(newAmount / 1000.0));//set new amount
                            item.setIngredientMeasurement("ltr");//set new unit tag
                            break;
                        }
                        item.setIngredientAmount(Double.toString(newAmount));//set new amount
                        item.setIngredientMeasurement("ml");//set new unit tag
                        break;
                    case "pt": //match customary measurement pint
                        newAmount = Double.parseDouble(item.getIngredientAmount()) * 473.0;//conversion pt-ml/ltr
                        if (newAmount > 1000.0) {//if eligible for next metric step-up
                            item.setIngredientAmount(Double.toString(newAmount / 1000.0));//set new amount
                            item.setIngredientMeasurement("ltr");//set new unit tag
                            break;
                        }
                        item.setIngredientAmount(Double.toString(newAmount));//set new amount
                        item.setIngredientMeasurement("ml");//set new unit tag
                        break;
                    case "qt": //match customary measurement quart
                        newAmount = Double.parseDouble(item.getIngredientAmount()) * 950.0;//conversion qt-ml/ltr
                        if (newAmount > 1000.0) {//if eligible for next metric step-up
                            item.setIngredientAmount(Double.toString(newAmount / 1000.0));//set new amount
                            item.setIngredientMeasurement("ltr");//set new unit tag
                            break;
                        }
                        item.setIngredientAmount(Double.toString(newAmount));//set new amount
                        item.setIngredientMeasurement("ml");//set new unit tag
                        break;
                    case "gal": //match customary measurement gallon
                        newAmount = Double.parseDouble(item.getIngredientAmount()) * 3.8;//conversion gal-ltr
                        item.setIngredientAmount(Double.toString(newAmount)); //set new amount
                        item.setIngredientMeasurement("ltr"); //set new unit tag
                        break;
                    case "lb": //match customary measurement pound
                        newAmount = Double.parseDouble(item.getIngredientAmount()) * 454.0;//conversion lb-g/kg
                        if (newAmount > 1000.0) { //if eligible for next metric step-up
                            item.setIngredientAmount(Double.toString(newAmount / 1000.0));//set new amount
                            item.setIngredientMeasurement("kg");//set new unit tag
                            break;
                        }
                        item.setIngredientAmount(Double.toString(newAmount));//set new amount
                        item.setIngredientMeasurement("g");//set new unit tag
                        break;
                }

            }
            unitChange = true; //set boolean to true
            tblPrint.getItems().clear();//clear table
            tblPrint.getItems().addAll(switchIngredients);//add in new modified ingredients
        }
    }

    @Override
    @SuppressWarnings( "deprecation" )
    public void initialize(URL location, ResourceBundle resources) {
        //Setup the Print table
        colIngredient.setCellValueFactory(new PropertyValueFactory<Ingredient, String>("IngredientName"));//apply column value name
        colQuantity.setCellValueFactory(new PropertyValueFactory<Ingredient, String>("IngredientAmount"));//apply column value name
        colUnit.setCellValueFactory(new PropertyValueFactory<Ingredient, String>("measurement")); //apply column value name

        colIngredient.impl_setReorderable(false);//stop column movement
        colQuantity.impl_setReorderable(false);//stop column movement
        colUnit.impl_setReorderable(false);//stop coolumn movement

        ArrayList<Recipe> recipes = Model.getRecipes(); //read recipes from recipe.txt
        ObservableList<Recipe> list = FXCollections.observableList(recipes); //place reciples in the observable list
        colRecipeTitle.setCellValueFactory(new PropertyValueFactory<Recipe, String>("title"));
        tblRecipes.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);//allow multiple selections
        for (Recipe i : list) { //iterate through recipes
            tblRecipes.getItems().add(i);//add recipes to choose to the table
        }
        
    }

}
