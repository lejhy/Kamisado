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

public class ScoreViewController extends ViewController {

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private TableView<ScoreEntry> scoreTable;

	@FXML
	private TableColumn<ScoreEntry, String> WhiteName;

	@FXML
	private TableColumn<ScoreEntry, Integer> WhiteScore;

	@FXML
	private TableColumn<ScoreEntry, String> BlackName;

	@FXML
	private TableColumn<ScoreEntry, Integer> BlackScore;

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
		WhiteName.setCellValueFactory(new PropertyValueFactory<ScoreEntry, String>("whiteName"));
		WhiteScore.setCellValueFactory(new PropertyValueFactory<ScoreEntry, Integer>("whitePoints"));
		BlackName.setCellValueFactory(new PropertyValueFactory<ScoreEntry, String>("blackName"));
		BlackScore.setCellValueFactory(new PropertyValueFactory<ScoreEntry, Integer>("blackPoints"));
	}

	public void setScoreList(ObservableList<ScoreEntry> sl) {
		scoreTable.setItems(sl);
	}

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub

	}
}
