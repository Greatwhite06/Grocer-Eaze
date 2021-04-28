import java.util.ArrayList;
import java.util.HashMap;

public class Recipe {

    private String title;
    /*
        String key is the ingredient name, second string is the amount
     */
    //private HashMap<String, String> ingredients;
    private ArrayList<Ingredient> ingredientList;

    /*
        Constructor for a recipe class
        Parameter: String title of the recipe
     */
    public Recipe(String title){
        this.title = title;
        ingredientList = new ArrayList<>();
        //ingredients = new HashMap<>();
    }

    /*
        Method for adding an entire hashmap of ingredients instead of adding one a time.
        Parameter: Hashmap of ingredients and quantities
     */
    /*
    public void setIngredientList(HashMap<String, String> ingredients){
        this.ingredients = ingredients;
    }
    */

    public void setIngredientList(ArrayList<Ingredient> ingredients){
        this.ingredientList = ingredients;
    }

    /*
        Add an entire list of ingredients
        Parameter: HashMap<String Ingredient_name, String Ingredient_qty>
     */
    public void addIngredients(ArrayList<Ingredient> ingredients){
        this.ingredientList.addAll(ingredients);
    }

    /*
        Add one ingredient at a time:
        Parameters: String ingredientName, String ingredientQuantity
     */
    public void addIngredient(String ingredientName, String ingredientQuantity){
        ingredientList.add(new Ingredient(ingredientName, ingredientQuantity));
    }

    /*
        Remove an ingredient from the recipe
        Parameter: String ingredient
    */
    public void removeIngredient(String ingredient){
        for(Ingredient i: ingredientList){
            if (i.getIngredientName().equals(ingredient)) {
                ingredientList.remove(i);
                return;
            }
        }
        System.out.print("Ingredient not found");
    }

    /*
        Print Recipe and ingredients
     */
    public void printRecipe(){
        System.out.println("Recipe: " + this.title);
        for(Ingredient i: ingredientList){
           System.out.println("\tIngredient: " + i.getIngredientName() + " Quantity: " + i.getIngredientAmount());
        }
    }

    /*
        Retrieve the title of the recipe
     */
    public String getTitle(){
        return this.title;
    }
    /*
        Retrieve the ingredients of the recipe
    */
    public ArrayList<Ingredient> getIngredients(){
        return this.ingredientList;
    }
}
