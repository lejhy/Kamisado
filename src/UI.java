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
	 
	   System.out.println();
	   System.out.print("x:  ");
	   for (int x = 0; x < 7 ; x++){
		   System.out.print( x + 1);
		   if(x < 7){
			   System.out.print(":");
		   }
	   }
	   System.out.println();
	   System.out.println();
	   
	   for( int y = 0; y < 7; y++){
		   System.out.print("y" + (y+1) + ": ");
		   	 for(int x = 0; x < 7; x++){
		   		System.out.print(tiles[x][y]);
		   		if (y < 7){
		   			System.out.print("|");
		   		}
		   	}
		   	System.out.println();
	   }
	   System.out.println();
	   
	   for(Tower tower: towers){
		   System.out.print(tower.getColor());
		   System.out.print(tower.getPlayerColor());
		   System.out.print(tower.getPositionX());
		   System.out.print(tower.getPositionY());
		   System.out.println();
	   }
   }


   
   public void displayScore(Scoreboard scoreboard){
	  for(int i=0; i< scoreboard.getScoreboardSize(scoreboard);i++){
		  System.out.println("Name: " + scoreboard.getPlayerName(i));
		  System.out.println("Score: " + scoreboard.getScore(i));
	  }
   }
   
      
}
