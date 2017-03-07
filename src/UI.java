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
import javafx.geometry.Insets;
import javafx.geometry.Pos;
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

public class UI extends Application{
	private Properties properties;
	private Stage primaryStage;
	private Controller controller;
	
	public Button newGame = new Button("New Game");
	public Button loadGame = new Button("Load Game");
	public Button score = new Button("Score");
	public Button exit = new Button("Exit");
	
	
	@Override
	public void start(Stage primaryStage) {
		controller = new Controller(this, "Kamisado.save");
		
		this.primaryStage = primaryStage;
		
		VBox vbox = new VBox();
		vbox.setAlignment(Pos.CENTER);
		vbox.setPadding(new Insets(25, 25, 25, 25));

		Scene scene = new Scene(vbox, 800, 600);
		Text scenetitle = new Text("Main Menu");
		scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
		vbox.getChildren().add(scenetitle);

		List<Button> buttons = new ArrayList<Button>();
		buttons.add(newGame);
		buttons.add(loadGame);
		buttons.add(score);
		buttons.add(exit);
		vbox.getChildren().addAll(buttons);
		
		this.primaryStage.setScene(scene);
		this.primaryStage.show();
	}

	public void addNewGameMainMenuHandler(EventHandler<ActionEvent> handler) {
		newGame.setOnAction(handler);
	}
	
	public void addLoadGameMainMenuHandler(EventHandler handler) {
		
	}
	
	public void addScoreMainMenuHandler(EventHandler handler) {
		
	}
	
	public void addExitMainMenuHandler(EventHandler handler) {
		
	}

	public static void main(String[] args) {
		launch(args);
	}
}