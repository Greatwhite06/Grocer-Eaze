import java.io.*;
import java.util.ArrayList;

public class ReadWriteData {

    public static ArrayList<Recipe> recipes;

    public static void readRecipeData() throws IOException {
        File file = new File("recipes.txt");
        FileReader fr = new FileReader(file);
        BufferedReader br = new BufferedReader(fr);
        recipes = new ArrayList<>();
        String line;
        while((line = br.readLine()) != null){
            String[] ingredients = line.split(":");
            String title = ingredients[0];
            String ingredientName;
            String qty;
            String[] ingredientQtyCombo;
            Recipe temp = new Recipe(title);
            for(int i = 1; i < ingredients.length; i++){
                ingredientQtyCombo = ingredients[i].split("-");
                ingredientName = ingredientQtyCombo[0];
                qty = ingredientQtyCombo[1];
                temp.addIngredient(ingredientName, qty);
            }
            recipes.add(temp);
        }
    }

    public static void writeRecipeData(ArrayList<Recipe> recipes) throws IOException {
        File file = new File("recipes.txt");
        FileWriter fw = new FileWriter(file);
        for(Recipe recipe: recipes){
            fw.write(recipe.getTitle());
            for(String ingredient: recipe.getIngredients().keySet()){
                fw.write(":" + ingredient);
                fw.write("-" + recipe.getIngredients().get(ingredient));
            }
        }
        fw.write("\n"); //finish with a new line
        fw.close();
    }

    /*
        Getter for the static ArrayList of recipes for other controllers to use.
     */
    public static ArrayList<Recipe> getRecipes(){
        return recipes;
    }
}
