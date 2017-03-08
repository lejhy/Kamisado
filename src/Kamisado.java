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
	public Parent mainMenu, newGame, loadGame, score, game;
	
	@Override
	public void start(Stage primaryStage) {
		this.window = primaryStage;
		controller = new Controller(this);
		load();
		this.window.setScene(new Scene(mainMenu));
		this.window.show();
	}
	
	private void load () {
		try {
			FXMLLoader loader;
			loader = new FXMLLoader(getClass().getResource("MainMenu.fxml"));
			loader.setController(controller);
			mainMenu = loader.load();
			loader = new FXMLLoader(getClass().getResource("game.fxml"));
			loader.setController(controller);
			game = loader.load();
		} catch (IOException e) {
			System.out.println("Error loading fxml files");
			System.err.println();
		}
	}
	
	public void displayGame() {
		this.window.setScene(new Scene(game));
	}

	public static void main(String[] args) {
		launch(args);
	}
}