package app;

import java.io.FileNotFoundException;

import controller.Core;
import controller.FileData;
import controller.Soundtrack;
import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import view.View;

/**
 * Filip Lejhanec, Fraser Steel
 */

public class Kamisado extends Application {

	@Override
	public void start(Stage primaryStage) {
		
		View view = new View(primaryStage);
		FileData fileData = new FileData("Kamisado.config");
		Soundtrack soundtrack = null;
		try {
			soundtrack = new Soundtrack(getClass().getResourceAsStream("Playlist.txt"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			System.exit(0);
		}
		view.loadFXML("MainMenuView.fxml", "Kamisado.css");
		view.loadFXML("NewGameView.fxml", "Kamisado.css");
		view.loadFXML("LoadGameView.fxml", "Kamisado.css");
		view.loadFXML("ScoreView.fxml", "Kamisado.css");
		view.loadFXML("SettingsView.fxml", "Kamisado.css");
		view.loadFXML("GameView.fxml", "Kamisado.css");
		Core core = new Core(fileData, soundtrack, view);
		
		primaryStage.setResizable(false);
		primaryStage.setTitle("Kamisado");
		primaryStage.getIcons().add(new Image("img/icon.png"));
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}