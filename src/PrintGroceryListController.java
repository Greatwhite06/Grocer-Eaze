import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class PrintGroceryListController {

    @FXML
    private Label recipetitle;

    public void setRecipetitle(){
        this.recipetitle.setText("Grilled Cheese");
    }
}
