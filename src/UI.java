import java.util.Observable;
import java.util.Observer;
import java.util.Scanner;

public class UI implements Observer {
	private Scanner scanner;
	private Kamisado kamisado;
	private Game game;
	
	@Override
	public void update(Observable arg0, Object arg1) {
		System.out.println("notified");
		scanner.nextLine();
		switch((Value)arg1) {
		case MAIN_MENU:
			String[] mainOptions = {"New Game", "Load Game", "Display Score", "Exit"};
			mainMenu(mainOptions);
			switch(scanner.nextLine()){
			case "0":
				kamisado.input(Value.NEW_GAME, "");
				break;
			case "1":
				kamisado.input(Value.LOAD_MENU, "");
				break;
			default:
				break;
			}
			break;
		case LOAD_MENU:
			String[] loadData = kamisado.loadData();
			kamisado.input(Value.LOAD_MENU, loadMenu(loadData));
			break;
		case SCORE_MENU:
			String[] scoreData = kamisado.scoreData();
			kamisado.input(Value.SCORE_MENU, scoreMenu(scoreData));
			break;
		case GAME:
			displayBoard(game.getBoard());
			break;
		case NEXT_TURN:
			displayBoard(game.getBoard());
			break;
		case GAME_OVER:
			displayBoard(game.getBoard());
			endGame(game);
			break;
		default:
			break;
		}
	}

	public UI (Kamisado kamisado, Game game){
		scanner = new Scanner(System.in);
		this.kamisado = kamisado;
		this.game = game;
	}
   
   public String mainMenu(String[] choice){
	   for (int i = 0; i < choice.length; i++){
		   System.out.println("(" + i + ") - " + choice[i]);
	   }
	   return scanner.nextLine();
   }
   
   public String prompt(String string) {
	   System.out.print(string);
	   return scanner.nextLine();
   }
   
   public String displayBoard(Board board){
	   String[][] display = new String[8][8];
	   for(Tower tower: board.getTowers()){
		   String player;
		   if (tower.getPlayer() == false)
			   player = "B";
		   else 
			   player = "W";
		   
		   String color;
		   if (tower.getColor() == Color.orange)
			   color = "oran";
		   else
			   color = tower.getColor().toString();
		   display[tower.getPositionX()][tower.getPositionY()] = player + color;
	   }
	   System.out.println();
	   System.out.print("x:  ");
	   for (int x = 0; x < 8 ; x++){
		   System.out.print( x + 1);
		   if(x < 7){
			   System.out.print(":");
		   }
	   }
	   System.out.println();
	   System.out.println();
	   
	   for( int y = 0; y < 8; y++){
		   System.out.print("y" + (y+1) + ": ");
		   	 for(int x = 0; x < 8; x++){
		   		System.out.print(display[x][y]);
		   		System.out.print(board.getTiles()[x][y]);
		   		if (y < 8){
		   			System.out.print("\t|");
		   		}
		   	}
		   	System.out.println();
	   }
	   System.out.println();
	   return scanner.nextLine();
   }

   public void invalidInput(String input) {
	   System.out.println("This input is invalid: " + input);
   }
   
   public String endGame(Game game) {
	   System.out.println("Round: " + game.getRound() + "; Winner: " + game.getBoard().getLastPlayerValue());
	   System.out.println("Last move: " + game.getBoard().getLastMove().startX + game.getBoard().getLastMove().startY + game.getBoard().getLastMove().finishX + game.getBoard().getLastMove().finishY);
	   return scanner.nextLine();
   }
   
   public String displayScore(Scoreboard scoreboard){
	  for(int i=0; i< scoreboard.size();i++){
		  System.out.println("Name: " + scoreboard.getPlayerName(i));
		  System.out.println("Score: " + scoreboard.getScore(i));
	  }
	  return scanner.nextLine();
   }
   
   public String loadMenu(String[] games){
	   for(int i=0; i < games.length; i++) {
		   String game = games[i];
		   System.out.println(game);
	   }
	   return scanner.nextLine();
   }     
   
   public String scoreMenu(String[] scores){
	   for(int i=0; i < scores.length; i++) {
		   String score = scores[i];
		   System.out.println(score);
	   }
	   return scanner.nextLine();
   }  
   
   public void setGame(Game game) {
	    this.game = game;
   }
}
