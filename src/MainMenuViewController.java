
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class MainMenuViewController extends Controller{
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button resume;

    @FXML
    void resumeGame(ActionEvent event) {
    	core.resumeGame();
    }
    
    @FXML
    void newGameMenu(ActionEvent event) {
    	core.newGameMenu();
    }
    
    @FXML
    void loadGameMenu(ActionEvent event) {
    	core.loadGameMenu();
    }
    
    @FXML
    void score(ActionEvent event) {
    	core.scoreMenu();
    }
    
    @FXML
    void exit(ActionEvent event) {
    	core.exit();
    }

    @FXML
    void initialize() {
        assert resume != null : "fx:id=\"resume\" was not injected: check your FXML file 'MainMenuView.fxml'.";
    }
}