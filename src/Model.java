import java.io.*;
import java.util.ArrayList;

public class Model {

    public static ArrayList<Recipe> recipes;

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
            //used to separate the ingredientName and Qty separated by a "-"
            String[] ingredientQtyCombo;
            Recipe tempRecipe = new Recipe(title);
            //Start at index 1 for each ingredient since title is at index 0
            for(int i = 1; i < ingredients.length; i++){
                //split ingredient name and quantity separated by dash
                ingredientQtyCombo = ingredients[i].split("-");
                ingredientName = ingredientQtyCombo[0];
                qty = ingredientQtyCombo[1];
                //populate a temporary recipe object with this ingredient and quantity
                tempRecipe.addIngredient(ingredientName, qty);
            }
            //add this temp recipe to the ArrayList which is static and can be accessed by other controllers.
            recipes.add(tempRecipe);
        }
    }

    public static void writeRecipeData(ArrayList<Recipe> recipes) throws IOException {
        File file = new File("recipes.txt");
        FileWriter fw = new FileWriter(file);
        for(Recipe recipe: recipes){
            fw.write(recipe.getTitle());
            for(Ingredient ingredient: recipe.getIngredients()){
                fw.write(":" + ingredient.getIngredientName());
                fw.write("-" + ingredient.getIngredientAmount());
            }
            fw.write("\n");
        }
        fw.close();
    }

    /*
        Getter for the static ArrayList of recipes for other controllers to use.
     */
    public static ArrayList<Recipe> getRecipes(){
        return recipes;
    }
}
