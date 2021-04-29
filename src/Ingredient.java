/*
CS 3443-003: Heena Rathore
Joseph Galvan: QRK815
Trey Jones: LVS888
Victor Danish: KUG872
Cameron Brumblay: BWO509
Wesley Jackson: ydh648
*/
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.CheckBox;

import java.util.ArrayList;

/*
 * Constructor Class
 * Only used for TableView data when writing a recipe
 */
public class Ingredient {
    private String name;
    private String amount;
    private CheckBox check;
    private String measurement;

    /* Constructor for defining ingredients
     */
    public Ingredient(String name, String amount /*,String measurement*/) {
        this.name = name;
        this.amount = amount;
        //this.measurement = measurement; //ADD once the box is created in add menu
        check = new CheckBox();
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
        Function: getCheckBox
        Purpose: return ingredient Checkbox
     */
    public CheckBox getCheckBox(){
        return this.check;
    }

    public String getMeasurement(){
        return this.measurement;
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
