import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.InvalidPropertiesFormatException;
import java.util.List;
import java.util.Properties;
import java.util.TreeSet;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
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