import java.util.Observable;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;

public class LoadGameViewController extends Controller {

    @FXML
    private TableView<GameEntry> loadGameTable;

    @FXML
    private TableColumn<GameEntry, String> Player1Name;

    @FXML
    private TableColumn<GameEntry, Integer> Player1Score;

    @FXML
    private TableColumn<GameEntry, String> player2Name;

    @FXML
    private TableColumn<GameEntry, Integer> Player2Score;

    @FXML
    void keyboardInput(KeyEvent event) {
    	switch(event.getCode()){
    	case L:
    		core.loadGame(loadGameTable.getSelectionModel().getSelectedItem().getGame().clone());
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
    	core.loadGame(loadGameTable.getSelectionModel().getSelectedItem().getGame().clone());
    }

    @FXML
    void mainMenu(ActionEvent event) {
    	core.mainMenu();
    }
    
    @FXML
    void initialize() {
        assert loadGameTable != null : "fx:id=\"loadGameTable\" was not injected: check your FXML file 'LoadGameView.fxml'.";
        Player1Name.setCellValueFactory(new PropertyValueFactory<GameEntry, String>("player1Name"));
        Player1Score.setCellValueFactory(new PropertyValueFactory<GameEntry, Integer>("player1Points"));
        player2Name.setCellValueFactory(new PropertyValueFactory<GameEntry, String>("player2Name"));
        Player2Score.setCellValueFactory(new PropertyValueFactory<GameEntry, Integer>("player2Points"));
    }
    
    public void setGameList(ObservableList<GameEntry> gl) {
    	loadGameTable.setItems(gl);
    }

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		
	}

}
