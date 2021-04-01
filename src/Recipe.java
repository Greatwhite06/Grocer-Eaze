import java.util.HashMap;

public class Recipe {

    private String title;
    private HashMap<String, String> ingredients;

    /*
        Constructor for a recipe class
        Parameter: String title of the recipe
     */
    public Recipe(String title){
        this.title = title;
        ingredients = new HashMap<>();
    }

    /*
        Method for adding an entire hashmap of ingredients instead of adding one a time.
        Parameter: Hashmap of ingredients and quantities
     */
    public void setIngredientList(HashMap<String, String> ingredients){
        this.ingredients = ingredients;
    }

    /*
        Add an entire list of ingredients
        Parameter: HashMap<String Ingredient_name, String Ingredient_qty>
     */
    public void addIngredients(HashMap<String, String> ingredientsList){
        for(String ingredient: ingredientsList.keySet()){
            this.ingredients.put(ingredient, ingredientsList.get(ingredient));
        }
    }

    /*
        Add one ingredient at a time:
        Parameters: String ingredientName, String ingredientQuantity
     */
    public void addIngredient(String ingredientName, String ingredientQuantity){
        ingredients.put(ingredientName, ingredientQuantity);
    }

    /*
        Remove an ingredient from the recipe
        Parameter: String ingredient
    */
    public void removeIngredient(String ingredient){
        if(ingredients.containsKey(ingredient)) {
            ingredients.remove(ingredient);
        }else{
            System.out.print("Ingredient not found");
        }
    }

    /*
        Print Recipe and ingredients
     */
    public void printRecipe(){
        System.out.println("Recipe: " + this.title);
        for(String ingredientName: ingredients.keySet()){
           System.out.println("\tIngredient: " + ingredientName + " Quantity: " + ingredients.get(ingredientName));
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
    public HashMap<String, String> getIngredients(){
        return this.ingredients;
    }
}
