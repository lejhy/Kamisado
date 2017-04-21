package controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Observable;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import model.GameEntry;

public class LoadGameViewController extends ViewController {

	@FXML
	private TableView<GameEntry> loadGameTable;

	@FXML
	private TableColumn<GameEntry, String> whiteName;

	@FXML
	private TableColumn<GameEntry, Integer> whiteScore;

	@FXML
	private TableColumn<GameEntry, String> blackName;

	@FXML
	private TableColumn<GameEntry, Integer> blackScore;

	@FXML
	private TableColumn<GameEntry, Integer> points;

	@FXML
	private TableColumn<GameEntry, Integer> turn;

	@FXML
	private TableColumn<GameEntry, String> time;

	@FXML
	void keyboardInput(KeyEvent event) {
		switch (event.getCode()) {
		case L:
			core.loadGame(loadGameTable.getSelectionModel().getSelectedItem().getGame().clone());
			break;
		case B:
			core.mainMenu();
			break;
		default:
			break;
		}
	}

	@FXML
	void loadGame(ActionEvent event) {
		core.loadGame(loadGameTable.getSelectionModel().getSelectedItem().getGame().clone());
	}

	@FXML
	void mainMenu(ActionEvent event) {
		core.mainMenu();
	}

	@FXML
	void initialize() {
		assert loadGameTable != null : "fx:id=\"loadGameTable\" was not injected: check your FXML file 'LoadGameView.fxml'.";
		whiteName.setCellValueFactory(new PropertyValueFactory<GameEntry, String>("whiteName"));
		whiteScore.setCellValueFactory(new PropertyValueFactory<GameEntry, Integer>("whitePoints"));
		blackName.setCellValueFactory(new PropertyValueFactory<GameEntry, String>("blackName"));
		blackScore.setCellValueFactory(new PropertyValueFactory<GameEntry, Integer>("blackPoints"));
		points.setCellValueFactory(new PropertyValueFactory<GameEntry, Integer>("points"));
		turn.setCellValueFactory(new PropertyValueFactory<GameEntry, Integer>("turn"));
		time.setCellValueFactory(gameEntry -> {
			SimpleStringProperty time = new SimpleStringProperty();
			DateFormat dateFormat = new SimpleDateFormat("d/M/yy h:mm:ss");
			time.setValue(dateFormat.format(gameEntry.getValue().getTime()));
			return time;
		});
	}

	public void setGameList(ObservableList<GameEntry> gl) {
		loadGameTable.setItems(gl);
	}

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub

	}

}
