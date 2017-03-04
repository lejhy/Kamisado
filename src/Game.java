import java.util.HashSet;
import java.util.Set;
import java.util.Scanner;

public class Game {

   private Config config;
   private AI ai;
   private Board board;
   private int round;
   private boolean gameOver;
   private Tower selectedTower;
   
   public int processMove(int x, int y) {
      if (selectedTower == null){
    	  if (board.isValidTower(x, y)){
    		  selectedTower = board.getTower(x, y);
    		  return 0;
    	  } else {
    		  return 1;
    	  }
      } else {
    	  if (board.isValidMove(selectedTower, x, y)) {
    		  board.performMove(selectedTower, x, y);
    		  round++;
    		  return 0;
    	  } else {
    		  return 2;
    	  }
      }
   }
   
   public void changeConfig() {
      // TODO implement this operation
      throw new UnsupportedOperationException("not implemented");
   }
   
   public void getScore() {
      // TODO implement this operation
      throw new UnsupportedOperationException("not implemented");
   }
	
	public int getRound() {
	   return this.round;
	}
	
	public Game(){
		config = new Config();
		board = new Board();
		gameOver = false;
		ai = null;
		round = 0;
		selectedTower = null;
	}
}