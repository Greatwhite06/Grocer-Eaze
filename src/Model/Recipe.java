package Model;/*CS 3443-003: Heena Rathore
Joseph Galvan: QRK815
Trey Jones: LVS888
Victor Danish: KUG872
Cameron Brumblay: BWO509
*/
import javafx.collections.ObservableList;
import javafx.collections.FXCollections;

import java.util.ArrayList;

public class Recipe {

    private String title; //holds recipe name
    private ArrayList<Ingredient> ingredientList; //holds each ingredient and quantity for the recipe

    /*
           Function: Model.Recipe
           Purpose: Constructor for a recipe class to set the title
           Parameters: String title
     */
    public Recipe(String title){
        this.title = title;
        ingredientList = new ArrayList<>();
    }

    /*
           Function: setIngredientList
           Purpose: to set the current ingredient list to the new one passed in
           Parameters: ArrayList<Model.Ingredient> ingredients
     */
    public void setIngredientList(ArrayList<Ingredient> ingredients){
        this.ingredientList = ingredients;
    }

    /*
           Function: addIngredients
           Purpose: to add an entire list of ingredients to current list
           Parameters: ArrayList<Model.Ingredient> ingredients
     */
    public void addIngredients(ArrayList<Ingredient> ingredients){
        this.ingredientList.addAll(ingredients);
    }

    /*
           Function: addIngredient
           Purpose: to add one ingredient at a time
           Parameters: String name, String quantity
     */
    public void addIngredient(String ingredientName, String ingredientQuantity, String ingredientMeasurement){
        ingredientList.add(new Ingredient(ingredientName, ingredientQuantity, ingredientMeasurement));
    }

    /*
           Function: removeIngredients
           Purpose: to remove an ingredient from the list
           Parameters: String ingredient
     */
    public void removeIngredient(String ingredient){
        for(Ingredient i: ingredientList){
            if (i.getIngredientName().equals(ingredient)) {
                ingredientList.remove(i);
                return;
            }
        }
        System.out.print("Model.Ingredient not found");
    }

    /*
        Function: Print Model.Recipe
        Purpose: To print out the recipe and each ingredient
        Parameters: None
     */
    public void printRecipe(){
        System.out.println("Model.Recipe: " + this.title);
        for(Ingredient i: ingredientList){
           System.out.println("\tModel.Ingredient: " + i.getIngredientName() + " Quantity: " + i.getIngredientAmount());
        }
    }

    /*
        Function: getTitle
        Purpose: Retrieve the title of the recipe
        Parameters: None
     */
    public String getTitle(){
        return this.title;
    }
    /*
        Function: getIngredients
        Purpose: To retrieve the ingredients of the recipe
        Parameters: None
     */
    public ArrayList<Ingredient> getIngredients(){
        return this.ingredientList;
    }

    /*
        Function: getObservableIngredients
        Purpose: To retrieve the observable list of ingredients of the recipe (for tableviews)
        Parameters: None
     */
    public ObservableList<Ingredient> getObservableIngredients(){
        return FXCollections.observableList(ingredientList);
    }
}
