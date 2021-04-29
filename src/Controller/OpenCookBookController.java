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

    @FXML
    void unitConversion(ActionEvent event){
        if(unitChange){
            showIngredients(event);
            unitChange = false;
        }
        else {
            unitChange = true;
            ObservableList<Ingredient> temp = tblPrint.getItems();
            //tblPrint.getItems().clear();
            for (Ingredient item : temp) {
                double newAmount;
                switch (item.getMeasurement()) {
                    case "tsp":
                        newAmount = Double.parseDouble(item.getIngredientAmount()) * 5.0;
                        if (newAmount > 1000.0) {
                            item.setIngredientAmount(Double.toString(newAmount / 1000.0));
                            item.setIngredientMeasurement("ltr");
                            break;
                        }
                        item.setIngredientAmount(Double.toString(newAmount));
                        item.setIngredientMeasurement("ml");
                        break;
                    case "tbsp":
                        newAmount = Double.parseDouble(item.getIngredientAmount()) * 15.0;
                        if (newAmount > 1000.0) {
                            item.setIngredientAmount(Double.toString(newAmount / 1000.0));
                            item.setIngredientMeasurement("ltr");
                            break;
                        }
                        item.setIngredientAmount(Double.toString(newAmount));
                        item.setIngredientMeasurement("ml");
                        break;
                    case "cup":
                        newAmount = Double.parseDouble(item.getIngredientAmount()) * 237.0;
                        if (newAmount > 1000.0) {
                            item.setIngredientAmount(Double.toString(newAmount / 1000.0));
                            item.setIngredientMeasurement("ltr");
                            break;
                        }
                        item.setIngredientAmount(Double.toString(newAmount));
                        item.setIngredientMeasurement("ml");
                        break;
                    case "oz":
                        newAmount = Double.parseDouble(item.getIngredientAmount()) * 28.0;
                        if (newAmount > 1000.0) {
                            item.setIngredientAmount(Double.toString(newAmount / 1000));
                            item.setIngredientMeasurement("kg");
                            break;
                        }
                        item.setIngredientAmount(Double.toString(newAmount));
                        item.setIngredientMeasurement("g");
                        break;
                    case "floz":
                        newAmount = Double.parseDouble(item.getIngredientAmount()) * 30.0;
                        if (newAmount > 1000.0) {
                            item.setIngredientAmount(Double.toString(newAmount / 1000.0));
                            item.setIngredientMeasurement("ltr");
                            break;
                        }
                        item.setIngredientAmount(Double.toString(newAmount));
                        item.setIngredientMeasurement("ml");
                        break;
                    case "pt":
                        newAmount = Double.parseDouble(item.getIngredientAmount()) * 473.0;
                        if (newAmount > 1000.0) {
                            item.setIngredientAmount(Double.toString(newAmount / 1000.0));
                            item.setIngredientMeasurement("ltr");
                            break;
                        }
                        item.setIngredientAmount(Double.toString(newAmount));
                        item.setIngredientMeasurement("ml");
                        break;
                    case "qt":
                        newAmount = Double.parseDouble(item.getIngredientAmount()) * 950.0;
                        if (newAmount > 1000.0) {
                            item.setIngredientAmount(Double.toString(newAmount / 1000.0));
                            item.setIngredientMeasurement("ltr");
                            break;
                        }
                        item.setIngredientAmount(Double.toString(newAmount));
                        item.setIngredientMeasurement("ml");
                        break;
                    case "gal":
                        newAmount = Double.parseDouble(item.getIngredientAmount()) * 3.8;
                        item.setIngredientAmount(Double.toString(newAmount));
                        item.setIngredientMeasurement("ltr");
                        break;
                    case "lb":
                        newAmount = Double.parseDouble(item.getIngredientAmount()) * 454.0;
                        if (newAmount > 1000.0) {
                            item.setIngredientAmount(Double.toString(newAmount / 1000.0));
                            item.setIngredientMeasurement("kg");
                            break;
                        }
                        item.setIngredientAmount(Double.toString(newAmount));
                        item.setIngredientMeasurement("g");
                        break;
                }

            }
            for(Ingredient ing : temp){
                System.out.println("Name: " + ing.getIngredientName() + " Amount: " + ing.getIngredientAmount() + " Unit: " + ing.getMeasurement());
            }

        }


    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //Setup the Print table
        colIngredient.setCellValueFactory(new PropertyValueFactory<Ingredient, String>("IngredientName"));
        colQuantity.setCellValueFactory(new PropertyValueFactory<Ingredient, String>("IngredientAmount"));
        colUnit.setCellValueFactory(new PropertyValueFactory<Ingredient, String>("measurement"));

        colIngredient.impl_setReorderable(false);
        colQuantity.impl_setReorderable(false);
        colUnit.impl_setReorderable(false);

        ArrayList<Recipe> recipes = Model.getRecipes();
        ObservableList<Recipe> list = FXCollections.observableList(recipes);
        colRecipeTitle.setCellValueFactory(new PropertyValueFactory<Recipe, String>("title"));
        tblRecipes.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        for (Recipe i : list) {
            tblRecipes.getItems().add(i);
        }
        
    }

}
