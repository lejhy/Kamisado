
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import javafx.scene.input.KeyEvent;

public class ScoreViewController extends Controller{

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableView<?> scoreTable;
    
    @FXML
    void keyboardInput(KeyEvent event) {
    	switch(event.getCode()){
    	case B:
    		core.mainMenu();
    		break;
		default:
			break;
    	}
    }

    @FXML
    void mainMenu(ActionEvent event) {
    	core.mainMenu();
    }

    @FXML
    void initialize() {
        assert scoreTable != null : "fx:id=\"scoreTable\" was not injected: check your FXML file 'ScoreView.fxml'.";

    }
}