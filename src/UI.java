import java.util.Set;
import java.util.HashSet;

public class UI {
   
   public void menu(String[] choice){
	   for (int i = 0; i < choice.length; i++){
		   System.out.println("(" + i + ") - " + choice[i]);
	   }
   }
   
   public void displayBoard(Color[][] tiles, int[][]){
	 
	   System.out.println();
	   System.out.print("col");
	   for (int col = 0; col < 7 ; col++){
		   System.out.print( col + 1);
		   if(col < 7){
			   System.out.print(":");
		   }
		   System.out.println();
		   System.out.println();
	   }
	   
	   for(int row = 0; row < 7; row++){
		   System.out.print("row" + (row+1) + ":");
	   	for( int col = 0; col < 7; col++){
	   		System.out.print(Color[row][collum]);
	   	}
   
	   	if (col < 7){
	   		System.out.print("|");
	   	}
	   	if(row < 7){
	   		System.out.println("\n			-----");
	   	}
	   	else 
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
