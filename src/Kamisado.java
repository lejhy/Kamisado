import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Filip Lejhanec
 * Fraser Steel 
 */


public class Kamisado extends Application{
	private Controller controller;
	private Stage stage;
	public Scene mainMenu, newGame, loadGame, score, game;
	
	@Override
	public void start(Stage primaryStage) {
		this.stage = primaryStage;
		this.stage.setResizable(false);
		controller = new Controller(this);
		load();
		this.stage.setScene(mainMenu);
		this.stage.show();
	}
	
	private void load () {
		try {
			FXMLLoader loader;
			loader = new FXMLLoader(getClass().getResource("MainMenu.fxml"));
			loader.setController(controller);
			mainMenu = new Scene(loader.load());
			mainMenu.getStylesheets().add("Kamisado.css");
			loader = new FXMLLoader(getClass().getResource("NewGame.fxml"));
			loader.setController(controller);
			newGame = new Scene(loader.load());
			newGame.getStylesheets().add("Kamisado.css");
			loader = new FXMLLoader(getClass().getResource("LoadGame.fxml"));
			loader.setController(controller);
			loadGame = new Scene(loader.load());
			loadGame.getStylesheets().add("Kamisado.css");
			loader = new FXMLLoader(getClass().getResource("Score.fxml"));
			loader.setController(controller);
			score = new Scene(loader.load());
			score.getStylesheets().add("Kamisado.css");
			loader = new FXMLLoader(getClass().getResource("game.fxml"));
			loader.setController(controller);
			game = new Scene(loader.load());
			game.getStylesheets().add("Kamisado.css");
		} catch (IOException e) {
			System.out.println("Error loading fxml files");
			System.err.println();
		}
	}
	
	public void displayMainMenu() {
		this.stage.setScene(mainMenu);
	}
	
	public void displayNewGame() {
		this.stage.setScene(newGame);
	}
	
	public void displayLoadGame() {
		this.stage.setScene(loadGame);
	}
	
	public void displayScore() {
		this.stage.setScene(score);
	}
	
	public void displayGame() {
		this.stage.setScene(game);
	}

	public static void main(String[] args) {
		launch(args);
	}
}