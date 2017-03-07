import java.util.HashSet;
import java.util.List;
import java.util.Observable;
import java.util.Set;

/**
 * Filip Lejhanec
 * Fraser Steel 
 */

public class Kamisado extends Observable{
	private UI  ui;
	private Data data;
	private Game game;
	private String fileName;
	private Value status;
	
	public Kamisado(){
		this.data = new Data(fileName);
		this.game = null;
		this.ui = new UI (this, game);
		this.addObserver(ui);
		this.status = Value.MAIN_MENU;
		setChanged();
		notifyObservers(Value.MAIN_MENU);
	}
	
	public void loop (){
		if (status == Value.GAME && game != null && game.isGameOver() == false) {
			game.nextTurn();
		}
	}
	
	public void input (Value value, String string) {
		switch(value) {
		case NEW_TWO_PLAYERS:
			if (game == null) {
				new2PGame(value, string);
			}
			break;
		case NEW_PLAYER_EASY:
			if (game == null) {
				newPvEGame(value, string);
			}
			break;
		case NEW_EASY_EASY:
			if (game == null) {
				newEvEGame(value, string);
			}
			break;
		case LOAD_GAME:
			break;
		case EXIT:
			break;
		case BACK:
			break;
		default:
			break;
		}
	}
	
   public void newGame(Player player1, Player player2) {
      this.game = new Game(player1, player2);
      ui.setGame(game);
      game.addObserver(ui);
      this.status = Value.GAME;
      setChanged();
      notifyObservers(Value.GAME);
   }
   
   public void new2PGame(Value value, String string){
		Player player1;
		Player player2;
		String[] names = string.split(" ");
		if (names[0] != null) {
			player1 = new Human(names[0], true, ui);
		} else {
			player1 = new Human("player1", true, ui);
		}
		if (names[1] != null) {
			player2 = new Human(names[1], false, ui);
		} else {
			player2 = new Human("player2", false, ui);
		}
		newGame(player1, player2);
	}
	
	public void newPvEGame(Value value, String string){
		Player player1;
		Player player2;
		String[] names = string.split(" ");
		if (names[0] != null) {
			player1 = new Human(names[0], true, ui);
		} else {
			player1 = new Human("player1", true, ui);
		}
		player2 = new ComputerEasy(false);
		newGame(player1, player2);
	}
	
	public void newEvEGame(Value value, String string){
		Player player1;
		Player player2;
		player1 = new ComputerEasy(true);
		player2 = new ComputerEasy(false);
		newGame(player1, player2);
	}
   
   public void saveGame() {
	   if (game != null){
		   data.addGame(game);
		   data.saveDataToFile(fileName);
	   }
   }
   
   public void makeMove(int x, int y) {
      // TODO implement this operation
      throw new UnsupportedOperationException("not implemented");
   }
   
   public void exit() {
	   String input = ui.prompt("Save current game? (y/n)");
	   if (input == "y" && game != null) {
		   data.addGame(game);
	   } 
	   
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

	public static void main(String[] args) {
		Kamisado kamisado = new Kamisado();
		while(true) {
			kamisado.loop();
		}
	}
}
