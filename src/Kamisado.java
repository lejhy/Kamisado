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
		case NEW_GAME:
			if (game == null) {
				newGame();
			}
			break;
		case LOAD_GAME:
			break;
		case EXIT:
			break;
		default:
			break;
		}
	}
	
   public void newGame() {
      this.game = new Game(new ComputerEasy(true), new ComputerEasy(false));
      ui.setGame(game);
      game.addObserver(ui);
      this.status = Value.GAME;
      setChanged();
      notifyObservers(Value.GAME);
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
	   String input = ui.prompt("Save current game? (y/n)");
	   if (input == "y" && game != null) {
		   data.addGame(game);
	   } 
	   data.saveDataToFile(fileName);
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
