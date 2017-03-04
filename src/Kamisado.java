import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Filip Lejhanec
 * Fraser Steel 
 */

public class Kamisado {
	private Data data;
	private UI ui;
	private Game game;
	private String fileName;
	private Status status;
	
	public Kamisado(){
		this.data = new Data(fileName);
		this.ui = new UI();
		this.game = null;
		this.status = Status.menu;
	}
	
	public void loop(){
		switch (status){
		case menu:
			String[] menu = {"New Game", "Load Game", "Display Score", "Exit"};
			String input = ui.menu(menu);;
			switch (input){
				case "0":
					newGame();
					status = Status.game;
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
			break;
		case game:
			Color[][] tiles = game.getBoard().getTiles();
			List<Tower> towers = game.getBoard().getTowers();
			ui.displayBoard(tiles, towers);
			int x = Integer.parseInt(ui.prompt("X: "));
			int y = Integer.parseInt(ui.prompt("Y: "));
			game.processMove(x, y);
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
   
   public void exit() {
	   data.saveDataToFile(fileName);
	   System.exit(0);
   }
   

	public static void main(String[] args) {
		Kamisado kamisado = new Kamisado();
		while(true){
			kamisado.loop();
		}
	}
}
