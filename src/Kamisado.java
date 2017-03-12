import javafx.application.Application;
import javafx.stage.Stage;

/**
 * Filip Lejhanec
 * Fraser Steel 
 */

public class Kamisado extends Application{
	
	@Override
	public void start(Stage primaryStage) {
		Core core = new Core();
		View view = new View(primaryStage);
		core.setView(view);
		core.setMainMenuViewController(view.loadFXML("fxml/MainMenuView.fxml", "Kamisado.css"));
		core.setNewGameViewController(view.loadFXML("fxml/NewGameView.fxml", "Kamisado.css"));
		core.setLoadGameViewController(view.loadFXML("fxml/LoadGameView.fxml", "Kamisado.css"));
		core.setScoreViewController(view.loadFXML("fxml/ScoreView.fxml", "Kamisado.css"));
		core.setGameViewController(view.loadFXML("fxml/GameView.fxml", "Kamisado.css"));
		
		primaryStage.setResizable(false);
		primaryStage.show();
	}
	
	
	

	public static void main(String[] args) {
		launch(args);
	}
}