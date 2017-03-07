import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class Controller extends Observable implements Observer{
	private UI ui;
	private Game game;
	private Data data;
	
	public Controller (UI ui, String fileName) {
		this.ui = ui;
		this.game = null;
		try {
			// try opening an existing file
			data = new Data(fileName);
		} catch (Exception e) {
			// create a new file
			data = new Data();
		}
	}
	
	@Override
	public void update (Observable observable, Object argument) {
		// if game changed, check for current player AI
	}
	
	public void towerSelected(Tower tower) {
		// highlight moves
	}
	
	public void makeMove(Move move, Value type) {
		this.game.nextTurn(move, type);
	}
	
	public void newGame(Player player1, Player player2){
		this.game = new Game(player1, player2);
	}
	
	public void saveGame() {
		if (game != null){
			data.addGame(game);
		}
	}
   
	public void exit() {
		data.saveDataToFile();
		System.exit(0);
	}	
   
	public String[] loadData () {
		String[] load = {"load"};
		return load;
	}	
   
	public String[] scoreData () {
		String[] score = {"score"};
		return score;
	}	
}