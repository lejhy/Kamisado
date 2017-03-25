import java.util.Observable;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.KeyEvent;

public class LoadGameViewController extends Controller {

    @FXML
    private TableView<Game> loadGameTable;

    @FXML
    private TableColumn<Game, String> Player1Name;

    @FXML
    private TableColumn<Game, Integer> Player1Score;

    @FXML
    private TableColumn<Game, String> player2Name;

    @FXML
    private TableColumn<Game, Integer> Player2Score;

    @FXML
    void keyboardInput(KeyEvent event) {
    	switch(event.getCode()){
    	case L:
    		core.loadGame();
    		break;
    	case B:
    		core.mainMenu();
    		break;
		default:
			break;
    	}
    }

    @FXML
    void loadGame(ActionEvent event) {
    	core.loadGame();
    }

    @FXML
    void mainMenu(ActionEvent event) {
    	core.mainMenu();
    }
    
    public void setGameList(ObservableList<Game> gl) {
    	loadGameTable.setItems(gl);
    }

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		
	}

}
