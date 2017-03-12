
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;

public class NewGameViewController extends Controller{

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField player1NameInput;

    @FXML
    private ToggleGroup player1Type;

    @FXML
    private ToggleGroup gameMode;

    @FXML
    private TextField player2NameInput;

    @FXML
    private ToggleGroup player2Type;

    @FXML
    void mainMenu(ActionEvent event) {

    }

    @FXML
    void newGame(ActionEvent event) {
    	Value player1TypeValue = getRBSelectionValue (player1Type);
    	Value player2TypeValue = getRBSelectionValue (player2Type);
    	Value gameModeValue = getRBSelectionValue (gameMode);
    	
    	String player1Name = player1NameInput.getText();
    	if (player1Name.equals("")){
    		player1Name = "Player 1";
    	}
    	
    	String player2Name = player2NameInput.getText();
    	if (player2Name.equals("")){
    		player2Name = "Player 2";
    	}
    	
    	Player player1 = new Player (player1Name, player1TypeValue);
    	Player player2 = new Player (player2Name, player2TypeValue);
    	
    	core.newGame(player1, player2, gameModeValue);
    }

    @FXML
    void initialize() {
        assert player1NameInput != null : "fx:id=\"player1NameInput\" was not injected: check your FXML file 'NewGameView.fxml'.";
        assert player1Type != null : "fx:id=\"player1Type\" was not injected: check your FXML file 'NewGameView.fxml'.";
        assert gameMode != null : "fx:id=\"gameMode\" was not injected: check your FXML file 'NewGameView.fxml'.";
        assert player2NameInput != null : "fx:id=\"player2NameInput\" was not injected: check your FXML file 'NewGameView.fxml'.";
        assert player2Type != null : "fx:id=\"player2Type\" was not injected: check your FXML file 'NewGameView.fxml'.";

    }
    
    public Value getRBSelectionValue (ToggleGroup toggleGroup) {
    	RadioButton radioButton = (RadioButton)toggleGroup.getSelectedToggle();
    	String string = radioButton.getText();
    	Value value;
    	switch (string) {
    	case "Human":
    		value = Value.HUMAN;
    		break;
    	case "Easy AI":
    		value = Value.EASY_AI;
    		break;
    	case "Hard AI":
    		value = Value.HARD_AI;
    		break;
    	case "Normal":
    		value = Value.NORMAL;
    		break;
    	case "Speed Mode":
    		value = Value.SPEED_MODE;
    		break;
		default:
			value = Value.DEFAULT;
    		break;
    	}
    	
    	return value;
    }
}