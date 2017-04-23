package controller;

import java.util.Observable;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.input.KeyEvent;

public class SettingsViewController extends ViewController {
	Soundtrack soundtrack = null;

	@FXML
	private Label currentlyPlaying;

	@FXML
	private Slider volume;

	@FXML
	private Label volumeLabel;

	@FXML
	void keyboardInput(KeyEvent event) {
		// nothing
	}

	@FXML
	void mainMenu(ActionEvent event) {
		core.mainMenu();
	}

	@FXML
	void nextSong(ActionEvent event) {
		soundtrack.nextTrack();
	}

	@FXML
	void previousSong(ActionEvent event) {
		soundtrack.previousTrack();
	}

	@FXML
	void initialize() {
		volume.valueProperty().addListener((observalbe, oldValue, newValue) -> {
			volumeLabel.setText("Volume: " + String.format("%.2f", newValue.doubleValue()));
			soundtrack.setVolume(newValue.doubleValue());
		});
	}

	public void setSoundtrack(Soundtrack soundtrack) {
		this.soundtrack = soundtrack;
		currentlyPlaying.setText(soundtrack.trackName());
		soundtrack.addTrackListener((observable, oldValue, newValue) -> {
			currentlyPlaying.setText(soundtrack.trackName());
		});
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub
	}
	
	@Override
	public void setCore(Core core) {
		this.core = core;
		setSoundtrack(core.getSoundtrack());
		core.setSettingsViewController(this);
	}
}
