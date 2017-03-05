import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.HashSet;

public class UI {
	private Scanner scanner;
	
	public UI (){
		scanner = new Scanner(System.in);
	}
   
   public String menu(String[] choice){
	   for (int i = 0; i < choice.length; i++){
		   System.out.println("(" + i + ") - " + choice[i]);
	   }
	   return scanner.nextLine();
   }
   
   public String prompt(String string) {
	   System.out.print(string);
	   return scanner.nextLine();
   }
   
   public void displayBoard(Color[][] tiles, List<Tower> towers){
	   
	   String[][] display = new String[8][8];
	   for(Tower tower: towers){
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
		   		System.out.print(tiles[x][y]);
		   		if (y < 8){
		   			System.out.print("\t|");
		   		}
		   	}
		   	System.out.println();
	   }
	   System.out.println();
   }

   public void invalidInput(String input) {
	   System.out.println("This input is invalid: " + input);
   }
   
   public String endGame(Game game) {
	   System.out.println("Round: " + game.getRound() + "; Winner: " + game.getBoard().getLastPlayer());
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
   
   public String loadMenu(List<Game> games){
	   for(int i=0; i < games.size(); i++) {
		   Game game = games.get(i);
		   System.out.println("Game: " + i + "; Round: " + game.getRound() + "; Difficulty: " + game.getDifficulty() + "; Speed: " + game.getSpeed());
		   System.out.println("Player1: " + game.getPlayer1Name());
		   System.out.println("Player2: " + game.getPlayer2Name());
	   }
	   return scanner.nextLine();
   }
   
      
}
