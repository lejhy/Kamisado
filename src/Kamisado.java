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
	private Stage window;
	public Scene mainMenu, newGame, loadGame, score, game;
	
	@Override
	public void start(Stage primaryStage) {
		this.window = primaryStage;
		controller = new Controller(this);
		load();
		this.window.setScene(mainMenu);
		this.window.show();
	}
	
	private void load () {
		try {
			FXMLLoader loader;
			loader = new FXMLLoader(getClass().getResource("MainMenu.fxml"));
			loader.setController(controller);
			mainMenu = new Scene(loader.load());
			loader = new FXMLLoader(getClass().getResource("NewGame.fxml"));
			loader.setController(controller);
			newGame = new Scene(loader.load());
			loader = new FXMLLoader(getClass().getResource("LoadGame.fxml"));
			loader.setController(controller);
			loadGame = new Scene(loader.load());
			loader = new FXMLLoader(getClass().getResource("Score.fxml"));
			loader.setController(controller);
			score = new Scene(loader.load());
			loader = new FXMLLoader(getClass().getResource("game.fxml"));
			loader.setController(controller);
			game = new Scene(loader.load());
		} catch (IOException e) {
			System.out.println("Error loading fxml files");
			System.err.println();
		}
	}
	
	public void displayMainMenu() {
		this.window.setScene(mainMenu);
	}
	
	public void displayNewGame() {
		this.window.setScene(newGame);
	}
	
	public void displayLoadGame() {
		this.window.setScene(loadGame);
	}
	
	public void displayScore() {
		this.window.setScene(score);
	}
	
	public void displayGame() {
		this.window.setScene(game);
	}

	public static void main(String[] args) {
		launch(args);
	}
}