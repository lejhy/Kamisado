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
		this.status = Status.mainMenu;
	}
	
	public void loop(){
		String input;
		switch (status){
		case mainMenu:
			String[] menu = {"New Game", "Load Game", "Display Score", "Exit"};
			input = ui.menu(menu);;
			switch (input){
				case "0":
					newGame();
					status = Status.game;
					break;
				case "1":
					status = Status.loadMenu;
					break;
				case "2":
					status = Status.scoreMenu;
					break;
				case "3":
					exit();
					break;
			}
			break;
		case loadMenu:
			input = ui.loadMenu(data.getGames());
			switch (input){
			case "back":
				status = Status.mainMenu;
				break;
			default:
				game = data.getGame(Integer.parseInt(input));
				if (game != null) {
					status = Status.game;
				} else {
					ui.invalidInput(input);
				}
			}
			break;
		case scoreMenu:
			input = ui.displayScore(data.getScore());
			break;
		case game:
			Color[][] tiles = game.getBoard().getTiles();
			List<Tower> towers = game.getBoard().getTowers();
			ui.displayBoard(tiles, towers);
			if (game.isGameOver() == false){
				game.nextTurn();
			} else {
				input = ui.endGame(game);
			}
			break;
		}
	}
	
	public Move getMove() {
		int startX = Integer.parseInt(ui.prompt("StartX: "));
		int startY = Integer.parseInt(ui.prompt("StartY: "));
		int finishX = Integer.parseInt(ui.prompt("FinishX: "));
		int finishY = Integer.parseInt(ui.prompt("FinishY: "));
		return new Move(startX, startY, finishX, finishY);
	}
	
   public void newGame() {
      this.game = new Game();
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

	public static void main(String[] args) {
		Kamisado kamisado = new Kamisado();
		while(true){
			kamisado.loop();
		}
	}
}
