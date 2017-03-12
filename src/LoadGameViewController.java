
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;

public class LoadGameViewController extends Controller{

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableView<?> loadGameTable;

    @FXML
    void loadGame(ActionEvent event) {

    }

    @FXML
    void mainMenu(ActionEvent event) {
    	core.mainMenu();
    }

    @FXML
    void initialize() {
        assert loadGameTable != null : "fx:id=\"loadGameTable\" was not injected: check your FXML file 'LoadGameView.fxml'.";

    }
}
