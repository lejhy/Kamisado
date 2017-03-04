import java.util.HashSet;
import java.util.Set;

/**
 * Filip Lejhanec
 * Fraser Steel 
 */

public class Kamisado {
	private Data data;
	private UI ui;
	private Game game;
	
	public Kamisado(){
		this.data = new Data("Kamisado.data");
		this.ui = new UI();
		this.game = null;
	}
	
	public void run(){
		String[] menu = {"New Game", "Load Game", "Display Score", "Exit"};
		this.ui.menu(menu);
		String input = System.console().readLine();
		switch (input){
			case "0":
				newGame();
				break;
			case "1":
				loadGame();
				break;
			case "2":
				displayScore();
				break;
			case "3":
				exit();
				break;
		}
	}
	
   public void newGame() {
      this.game = new Game();
   }
   
   public void saveGame() {
      // TODO implement this operation
      throw new UnsupportedOperationException("not implemented");
   }
   
   public void loadGame() {
      // TODO implement this operation
      throw new UnsupportedOperationException("not implemented");
   }
   
   public void displayScore() {
	   // TODO implement this operation
	   throw new UnsupportedOperationException("not implemented");
   }
   
   public void makeMove(int x, int y) {
      // TODO implement this operation
      throw new UnsupportedOperationException("not implemented");
   }
   
   
   public void updateView() {
      // TODO implement this operation
      throw new UnsupportedOperationException("not implemented");
   }
   
   public void exit(Data data) {
	   data.saveDataToFile(fileName);
	   exit();
      throw new UnsupportedOperationException("not implemented");
   }
   

	public static void main(String[] args) {
		Kamisado kamisado = new Kamisado();
		kamisado.run();
	}
}
