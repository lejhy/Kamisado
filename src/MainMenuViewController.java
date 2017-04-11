
import java.net.URL;
import java.util.Observable;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.KeyEvent;

public class MainMenuViewController extends Controller{
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button resume;
    
    @FXML
    void keyboardInput(KeyEvent event) {
    	switch(event.getCode()){
    	case R:
    		core.resumeGame();
    		break;
    	case N:
    		core.newGameMenu();
    		break;
    	case L:
    		core.loadGameMenu();
    		break;
    	case T:
    		core.settingsMenu();
    		break;
    	case S:
    		core.scoreMenu();
    		break;
    	case E:
    		core.exit();
    		break;
		default:
			break;
    	}
    }

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
    void settings(ActionEvent event){
    	core.settingsMenu();
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
    
	@Override
	public void update(Observable o, Object arg) {
		if (o == core) {
			if (core.hasGame()) {
				resume.setDisable(false);
			} else {
				resume.setDisable(true);
			}
		}
	}
}