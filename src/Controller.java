import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

public class Controller implements Observer{
	private Kamisado kamisado;
	private Game game;
	private Data data;
	
	@FXML
    private Canvas gameView;
	
	public Controller (Kamisado kamisado) {
		this.kamisado = kamisado;
		this.game = null;
		try {
			// try opening an existing file
			data = new Data("Kamisado.save");
		} catch (Exception e) {
			// create a new file
			data = new Data();
		}
	}
	
	@FXML
    void exit(Event event) {

    }

    @FXML
    void loadGame(Event event) {

    }

    @FXML
    void newGame(Event event) {
    	this.game = new Game(new Player("test", Value.HUMAN), new Player("test2", Value.HUMAN));
    	kamisado.displayGame();
    	event.consume();
    }

    @FXML
    void scene(ActionEvent event) {
    	
    }
    
    @FXML
    void updateGame(Event event) {
    	System.out.println("Updating the game view");
    	GraphicsContext gc = gameView.getGraphicsContext2D();
    	gc.setFill(Color.DARKCYAN);
    	gc.fillOval(110, 30, 50, 50);
    }
    
    @FXML
    void gameInput(Event event) {
    	if (event.getEventType() == MouseEvent.MOUSE_CLICKED) {
    		System.out.println("X: " + ((MouseEvent)event).getSceneX() + "Y: " + ((MouseEvent)event).getSceneY());
    	}
    }
    
    public void initGame () {
    	System.out.println("Init game view");
    	GraphicsContext gc = gameView.getGraphicsContext2D();
    	for (Value[] row: game.getBoard().getTiles()) {
    		for (Value tile: row) {
    			switch(tile){
    			case BLUE:
    			case
    			}
    		}
    	}
    }

	
	@Override
	public void update (Observable observable, Object argument) {
		// if game changed, check for current player AI
		// update the view as well
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
	
	public Game getGame() {
		return game;
	}
}