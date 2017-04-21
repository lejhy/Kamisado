package controller;

import java.net.URL;
import java.util.Observable;
import java.util.ResourceBundle;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import model.ScoreEntry;

public class ScoreViewController extends Controller {

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private TableView<ScoreEntry> scoreTable;

	@FXML
	private TableColumn<ScoreEntry, String> Player1Name;

	@FXML
	private TableColumn<ScoreEntry, Integer> Player1Score;

	@FXML
	private TableColumn<ScoreEntry, String> player2Name;

	@FXML
	private TableColumn<ScoreEntry, Integer> Player2Score;

	@FXML
	void keyboardInput(KeyEvent event) {
		switch (event.getCode()) {
		case B:
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
	void initialize() {
		assert scoreTable != null : "fx:id=\"scoreTable\" was not injected: check your FXML file 'ScoreView.fxml'.";
		Player1Name.setCellValueFactory(new PropertyValueFactory<ScoreEntry, String>("player1Name"));
		Player1Score.setCellValueFactory(new PropertyValueFactory<ScoreEntry, Integer>("player1Points"));
		player2Name.setCellValueFactory(new PropertyValueFactory<ScoreEntry, String>("player2Name"));
		Player2Score.setCellValueFactory(new PropertyValueFactory<ScoreEntry, Integer>("player2Points"));
	}

	public void setScoreList(ObservableList<ScoreEntry> sl) {
		scoreTable.setItems(sl);
	}

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub

	}
}
