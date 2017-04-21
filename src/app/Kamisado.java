package app;

import controller.Core;
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
		Core core = new Core();
		View view = new View(primaryStage);
		core.setView(view);
		core.setMainMenuViewController(view.loadFXML("MainMenuView.fxml", "Kamisado.css"));
		core.setNewGameViewController(view.loadFXML("NewGameView.fxml", "Kamisado.css"));
		core.setLoadGameViewController(view.loadFXML("LoadGameView.fxml", "Kamisado.css"));
		core.setScoreViewController(view.loadFXML("ScoreView.fxml", "Kamisado.css"));
		core.setSettingsViewController(view.loadFXML("SettingsView.fxml", "Kamisado.css"));
		core.setGameViewController(view.loadFXML("GameView.fxml", "Kamisado.css"));

		primaryStage.setResizable(false);
		primaryStage.setTitle("Kamisado");
		primaryStage.getIcons().add(new Image("img/icon.png"));
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}