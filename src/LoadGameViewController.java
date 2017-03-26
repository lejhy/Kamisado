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
    private TableColumn<GameEntry, String> player1Name;

    @FXML
    private TableColumn<GameEntry, Integer> player1Score;

    @FXML
    private TableColumn<GameEntry, String> player2Name;

    @FXML
    private TableColumn<GameEntry, Integer> player2Score;
    
    @FXML
    private TableColumn<GameEntry, Integer> points;
    
    @FXML
    private TableColumn<GameEntry, Integer> turn;

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
        player1Name.setCellValueFactory(new PropertyValueFactory<GameEntry, String>("player1Name"));
        player1Score.setCellValueFactory(new PropertyValueFactory<GameEntry, Integer>("player1Points"));
        player2Name.setCellValueFactory(new PropertyValueFactory<GameEntry, String>("player2Name"));
        player2Score.setCellValueFactory(new PropertyValueFactory<GameEntry, Integer>("player2Points"));
        points.setCellValueFactory(new PropertyValueFactory<GameEntry, Integer>("points"));
        turn.setCellValueFactory(new PropertyValueFactory<GameEntry, Integer>("turn"));
    }
    
    public void setGameList(ObservableList<GameEntry> gl) {
    	loadGameTable.setItems(gl);
    }

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		
	}

}
