package controller;

import java.net.URL;
import java.util.Observable;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.KeyEvent;
import model.Player;
import model.Value;

public class NewGameViewController extends ViewController {

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private TextField whitePlayerNameInput;

	@FXML
	private ToggleGroup whitePlayerType;

	@FXML
	private ToggleGroup gameMode;

	@FXML
	private CheckBox randomBoard;

	@FXML
	private TextField blackPlayerNameInput;

	@FXML
	private Label timeLabel;

	@FXML
	private Slider timeInput;

	@FXML
	private Label pointsLabel;

	@FXML
	private Slider pointsInput;

	@FXML
	private ToggleGroup blackPlayerType;

	@FXML
	void keyboardInput(KeyEvent event) {
		switch (event.getCode()) {
		case S:
			if (!whitePlayerNameInput.isFocused() && !blackPlayerNameInput.isFocused())
				newGame();
			break;
		case M:
			RadioButton rb = (RadioButton) gameMode.getSelectedToggle();
			if (!whitePlayerNameInput.isFocused() && !blackPlayerNameInput.isFocused())
				rb.requestFocus();
			break;
		case DIGIT1:
			if (!blackPlayerNameInput.isFocused())
				whitePlayerNameInput.requestFocus();
			break;
		case DIGIT2:
			if (!whitePlayerNameInput.isFocused())
				blackPlayerNameInput.requestFocus();
			break;
		case B:
			if (!whitePlayerNameInput.isFocused() && !blackPlayerNameInput.isFocused())
				core.mainMenu();
			break;
		default:
			break;
		}
	}

	@FXML
	void mainMenu(ActionEvent event) {
		core.mainMenu();
	}

	@FXML
	void newGame(ActionEvent event) {
		newGame();
	}

	@FXML
	void initialize() {
		assert whitePlayerNameInput != null : "fx:id=\"whitePlayerNameInput\" was not injected: check your FXML file 'NewGameView.fxml'.";
		assert whitePlayerType != null : "fx:id=\"whitePlayerType\" was not injected: check your FXML file 'NewGameView.fxml'.";
		assert gameMode != null : "fx:id=\"gameMode\" was not injected: check your FXML file 'NewGameView.fxml'.";
		assert blackPlayerNameInput != null : "fx:id=\"blackPlayerNameInput\" was not injected: check your FXML file 'NewGameView.fxml'.";
		assert blackPlayerType != null : "fx:id=\"blackPlayerType\" was not injected: check your FXML file 'NewGameView.fxml'.";
		pointsInput.valueProperty().addListener((observable, oldValue, newValue) -> {
			pointsLabel.setText("Points: " + String.valueOf(newValue.intValue()));
		});
		timeInput.valueProperty().addListener((observable, oldValue, newValue) -> {
			timeLabel.setText("Time: " + String.valueOf(newValue.intValue()) + "s");
		});
		gameMode.selectedToggleProperty().addListener((observable, oldValue, newValue) -> {
			if (((RadioButton) newValue).getText().equals("Speed Mode")) {
				timeLabel.setVisible(true);
				timeInput.setVisible(true);
			} else {
				timeLabel.setVisible(false);
				timeInput.setVisible(false);
			}
		});

	}

	private void newGame() {
		Value whiteTypeValue = getRBSelectionValue(whitePlayerType);
		Value blackTypeValue = getRBSelectionValue(blackPlayerType);
		Value gameModeValue = getRBSelectionValue(gameMode);
		boolean randomBoardValue = randomBoard.isSelected();
		int time = (int) timeInput.getValue() * 1000;
		int points = (int) pointsInput.getValue();

		String whitePlayerName = whitePlayerNameInput.getText();
		if (whitePlayerName.equals("")) {
			whitePlayerName = "White";
		}

		String blackPlayerName = blackPlayerNameInput.getText();
		if (blackPlayerName.equals("")) {
			blackPlayerName = "Black";
		}

		Player white = new Player(whitePlayerName, whiteTypeValue);
		Player black = new Player(blackPlayerName, blackTypeValue);

		core.newGame(white, black, time, points, randomBoardValue, gameModeValue);
	}

	public Value getRBSelectionValue(ToggleGroup toggleGroup) {
		RadioButton radioButton = (RadioButton) toggleGroup.getSelectedToggle();
		String string = radioButton.getText();
		Value value;
		switch (string) {
		case "Human":
			value = Value.HUMAN;
			break;
		case "Easy AI":
			value = Value.EASY_AI;
			break;
		case "Hard AI":
			value = Value.HARD_AI;
			break;
		case "Beginner AI":
			value = Value.BEGINNER_AI;
			break;
		case "Normal":
			value = Value.NORMAL;
			break;
		case "Speed Mode":
			value = Value.SPEED_MODE;
			break;
		default:
			value = Value.DEFAULT;
			break;
		}

		return value;
	}

	@Override
	public void update(Observable o, Object arg) {

	}
}