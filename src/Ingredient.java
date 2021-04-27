import javafx.beans.property.SimpleStringProperty;

/*
 * Constructor Class
 * Only used for TableView data when writing a recipe
 */
public class Ingredient {
    private SimpleStringProperty ingredientName;
    private SimpleStringProperty ingredientAmount;

    /* Constructor for defining ingredients
     */
    public Ingredient(String ingredientName, String ingredientAmount) {
        this.ingredientName = new SimpleStringProperty(ingredientName);
        this.ingredientAmount = new SimpleStringProperty(ingredientAmount);
    }
    /* Empty constructor
     */
    public Ingredient(){

    }

    public String getIngredientName() {
        return ingredientName.get();
    }

    public void setIngredientName(String ingredientName) {
        this.ingredientName = new SimpleStringProperty(ingredientName);
    }

    public String getIngredientAmount() {
        return ingredientAmount.get();
    }

    public void setIngredientAmount(String ingredientAmount) {
        this.ingredientAmount = new SimpleStringProperty(ingredientAmount);
    }
}
