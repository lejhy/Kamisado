import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class Controller extends Observable implements Observer{
	private UI ui;
	private Game game;
	private Data data;
	private Value state;
	private Value subState;
	
	public Controller (UI ui, String fileName) {
		this.ui = ui;
		ui.addNewGameMainMenuHandler(newGameMainMenuHandler);
		this.game = null;
		try {
			// try opening an existing file
			data = new Data(fileName);
		} catch (Exception e) {
			// create a new file
			data = new Data();
		}
	}
	
	EventHandler<ActionEvent> newGameMainMenuHandler = new EventHandler<ActionEvent>(){
		@Override
		public void handle(ActionEvent e){
			ui.loadGame.setText("test");
		}
		
	};
	
	EventHandler<ActionEvent> loadGameMainMenuHandler = new EventHandler<ActionEvent>(){
		@Override
		public void handle(ActionEvent e){
			
		}
		
	};
	
	EventHandler<ActionEvent> scoreMainMenuHandler = new EventHandler<ActionEvent>(){
		@Override
		public void handle(ActionEvent e){
			
		}
		
	};
	
	EventHandler<ActionEvent> exitMainMenuHandler = new EventHandler<ActionEvent>(){
		@Override
		public void handle(ActionEvent e){
			
		}
		
	};
	
	private void change(){
		hasChanged();
		notifyObservers();
	}
	
	@Override
	public void update(Observable observable, Object argument) {
		// reroute update
		switch(state){
		case GAME:
			handleGame(observable, argument);
			break;
		case MAIN_MENU:
			handleMainMenu(observable, argument);
			break;
		case NEW_MENU:
			handleNewMenu(observable, argument);
			break;
		case LOAD_MENU:
			handleLoadMenu(observable, argument);
			break;
		case SCORE_MENU:
			handleScoreMenu(observable, argument);
			break;
		case EXIT:
			// exiting, do nothing
			break;
		default:
			break;
		}
	}
	
	private void handleGame(Observable observable, Object argument) {
		if (argument instanceof Move) {
			game.nextTurn((Move)argument);
		} else if (argument instanceof Value){
			switch((Value)argument){
			case MAIN_MENU:
				state = Value.NEW_MENU;
				change();
				break;
			default:
				break;
			}
		}
	}
	
	private void handleMainMenu(Observable observable, Object argument) {
		if (argument instanceof Value) {
			switch((Value)argument){
			case NEW_MENU:
				state = Value.NEW_MENU;
				change();
				break;
			case LOAD_MENU:
				state = Value.LOAD_MENU;
				change();
				break;
			case SCORE_MENU:
				state = Value.SCORE_MENU;
				change();
				break;
			case EXIT:
				state = Value.EXIT;
				change();
				exit();
				break;
			default:
				break;
			}	
		}
	}
	
	private void handleNewMenu(Observable observable, Object argument) {
		if (argument instanceof Value) {
			switch((Value)argument){
			case PLAYER_PLAYER:
				state = Value.PLAYER_PLAYER;
				change();
				break;
			case PLAYER_AI:
				state = Value.PLAYER_AI;
				change();
				break;
			case AI_AI:
				state = Value.AI_AI;
				change();
				break;
			case EXIT:
				state = Value.EXIT;
				change();
				exit();
				break;
			default:
				break;
			}	
		}
	}
	
	private void handlePlayerPlayer(Observable observable, Object argument) {
		try {
			String[] input = ((String) argument).split(" ");
			Player player1 = new Player(input[0]);
			Player player2 = new Player(input[1]);
			this.game = new Game(player1, player2);
		} catch (Exception e) {
			System.err.println("handlePlayerPlayer invalid input");
			e.printStackTrace();
		}
	}
	
	private void handlePlayerAI(Observable observable, Object argument) {
		
	}
	
	private void handleAIAI(Observable observable, Object argument) {
		
	}

	private void handleLoadMenu(Observable observable, Object argument) {
		
	}
	
	private void handleScoreMenu(Observable observable, Object argument) {
		
	}
	
	public void newGame(Players player1, Players player2) {

	}
   
	public void saveGame() {
		if (game != null){
			data.addGame(game);
		}
	}
	
	public void makeMove(int x, int y) {
		// TODO implement this operation
		throw new UnsupportedOperationException("not implemented");
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
	
	public Value getState() {
		return state;
	}
	
	public Value getSubState() {
		return subState;
	}
}
