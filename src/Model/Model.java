package Model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.io.*;
import java.util.ArrayList;

public class Model {

    public static ArrayList<Recipe> recipes;

    /*
        Function: readRecipeData
        Purpose: to read the recipes from the file
        Parameters: None
     */
    public static void readRecipeData() throws IOException {
        File file = new File("recipes.txt");
        FileReader fr = new FileReader(file);
        BufferedReader br = new BufferedReader(fr);
        recipes = new ArrayList<>();
        String line;

        //Continue reading from file until null line reached
        while((line = br.readLine()) != null){
            //Split ingredient line based on colon delimiter
            String[] ingredients = line.split(":");
            //Title is the first item
            String title = ingredients[0];
            String ingredientName;
            String qty;
            String measurement;
            //used to separate the ingredientName and Qty separated by a "-"
            String[] ingredientQtyCombo;
            Recipe tempRecipe = new Recipe(title);
            //Start at index 1 for each ingredient since title is at index 0
            for(int i = 1; i < ingredients.length; i++){
                //split ingredient name and quantity separated by dash
                ingredientQtyCombo = ingredients[i].split("-");
                ingredientName = ingredientQtyCombo[0];
                qty = ingredientQtyCombo[1];
                measurement = ingredientQtyCombo[2];

                //populate a temporary recipe object with this ingredient and quantity
                tempRecipe.addIngredient(ingredientName, qty, measurement);
            }
            //add this temp recipe to the ArrayList which is static and can be accessed by other controllers.
            recipes.add(tempRecipe);
        }
        br.close();
        fr.close();
    }

    /*
        Function: writeRecipeData
        Purpose: to write the recipes to the file
        Parameters: ArrayList<Model.Recipe> recipes
     */
    public static void writeRecipeData(ArrayList<Recipe> recipes) throws IOException {
        File file = new File("recipes.txt");
        FileWriter fw = new FileWriter(file);
        for(Recipe recipe: recipes){
            fw.write(recipe.getTitle());
            for(Ingredient ingredient: recipe.getIngredients()){
                fw.write(":" + ingredient.getIngredientName());
                fw.write("-" + ingredient.getIngredientAmount());
                fw.write("-" + ingredient.getMeasurement());
            }
            fw.write("\n");
        }
        fw.close();
    }

    /*
        Function: getRecipes
        Purpose: to get the Recipes
        Parameters: None
     */
    public static ArrayList<Recipe> getRecipes(){
        return recipes;
    }

    /*
    Function: getRecipes
    Purpose: to get an observable list of the Recipes
    Parameters: None
 */
    public static ObservableList<Recipe> getObservableRecipes(){
        return FXCollections.observableList(recipes);
    }
}
