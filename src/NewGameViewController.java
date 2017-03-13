
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.KeyEvent;

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
    void keyboardInput(KeyEvent event) {
    	switch(event.getCode()){
    	case S:
    		if (!player1NameInput.isFocused() && !player2NameInput.isFocused())
    			newGame();
    		break;
    	case M:
    		RadioButton rb = (RadioButton)gameMode.getSelectedToggle();
    		if (!player1NameInput.isFocused() && !player2NameInput.isFocused())
    			rb.requestFocus();
    		break;
    	case DIGIT1:
    		if (!player2NameInput.isFocused())
    			player1NameInput.requestFocus();
    		break;
    	case DIGIT2:
    		if (!player1NameInput.isFocused())
    			player2NameInput.requestFocus();
    		break;
    	case B:
    		if (!player1NameInput.isFocused() && !player2NameInput.isFocused())
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
    void newGame(ActionEvent event) {
    	newGame();
    }

    @FXML
    void initialize() {
        assert player1NameInput != null : "fx:id=\"player1NameInput\" was not injected: check your FXML file 'NewGameView.fxml'.";
        assert player1Type != null : "fx:id=\"player1Type\" was not injected: check your FXML file 'NewGameView.fxml'.";
        assert gameMode != null : "fx:id=\"gameMode\" was not injected: check your FXML file 'NewGameView.fxml'.";
        assert player2NameInput != null : "fx:id=\"player2NameInput\" was not injected: check your FXML file 'NewGameView.fxml'.";
        assert player2Type != null : "fx:id=\"player2Type\" was not injected: check your FXML file 'NewGameView.fxml'.";

    }
    
    private void newGame() {
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