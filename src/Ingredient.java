import javafx.beans.property.SimpleStringProperty;
/*
 * Constructor Class
 * Only used for TableView data when writing a recipe
 */
public class Ingredient {
    private String name;
    private String amount;


    /* Constructor for defining ingredients
     */
    public Ingredient(String name, String amount) {
        this.name = name;
        this.amount = amount;
    }
    /* Empty constructor
     */
    public Ingredient(){

    }

    /*
        Function: getName
        Purpose: return the string name
     */
    public String getIngredientName() {
        return name;
    }

    /*
        Function: getAmount
        Purpose: return the string amount
     */
    public String getIngredientAmount(){
        return amount;
    }

    /*
        Function: getAmount
        Purpose: return the simpleStringProperty name
     */
    public SimpleStringProperty getSimpleName(){
        return new SimpleStringProperty(name);
    }

    /*
        Function: getSimpleAmount
        Purpose: return the SimpleStringProperty amount
    */
    public SimpleStringProperty getSimpleAmount(){
        return new SimpleStringProperty(amount);
    }
    /*
        Function: setIngredientName
        Purpose: set the name of this class to the parameter
        Parameters: String name
     */
    public void setIngredientName(String name) {
        this.name = name;
    }

    /*
        Function: setIngredientAmount
        Purpose: set the amount of this class to the parameter
        Parameters: String amount
     */
    public void setIngredientAmount(String amount) {
        this.amount = amount;
    }
}
