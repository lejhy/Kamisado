import java.util.HashSet;
import java.util.Set;
import java.util.Scanner;

public class Game {

   private Config config;
   private AI ai;
   private Board board;
   private int round;
   private boolean gameOver;
   private int selectedTowerPosX;
   private int selectedTowerPosY;
   
   public int processMove(int x, int y) {
	   if (!gameOver) {
	      if (selectedTowerPosX == -1){
	    	  if (board.isValidTower(x, y)){
	    		  selectedTowerPosX = x;
	    		  selectedTowerPosY = y;
	    		  return 0;
	    	  } else {
	    		  return -1;
	    	  }
	      } else {
	    	  if (board.isValidMove(selectedTowerPosX, selectedTowerPosY, x, y)) {
	    		  board.performMove(selectedTowerPosX, selectedTowerPosY, x, y);
	    		  round++;
	    		  if (board.isGameOver()){
	    			  gameOver = true;
	    			  return 1;
	    		  }
	    		  return 0;
	    	  } else {
	    		  return -2;
	    	  }
	      }
	   } else {
		   return -3;
	   }
   }
   
   public void changeConfig(Option option, String value) {
      switch (option) {
      case difficulty:
    	  switch (value) {
    	  case "easy":
    		  config.setDifficulty(0);
    		  break;
    	  case "hard":
    		  config.setDifficulty(1);
    		  break;
    	  }
    	  break;
      case speed:
    	  switch (value) {
    	  case "slow":
    		  config.setSpeed(0);
    		  break;
    	  case "fast":
    		  config.setSpeed(1);
    		  break;
    	  }
    	  break;
      case player1Name:
    	  config.setPlayer1Name(value);
    	  break;
      case player2Name:
    	  config.setPlayer2Name(value);
    	  break;
      }
   }
   
   public void getScore() {
      // TODO implement this operation
      throw new UnsupportedOperationException("not implemented");
   }
	
	public int getRound() {
	   return this.round;
	}
	
	public Color getTile(int x, int y) {
		return board.getTile(x, y);
	}
	
	public int getNumOfTowers () {
		return board.getNumOfTowers();
	}
	
	public Color getTowerColor (int i) {
		return board.getTowerColor(i);
	}
	
	public Color getTowerPlayerColor (int i) {
		return board.getTowerPlayerColor(i);
	}
	
	public int getTowerPosX (int i) {
		return board.getTowerPosX(i);
	}
	
	public int getTowerPosY (int i) {
		return board.getTowerPosY(i);
	}
	
	public Game(){
		config = new Config();
		board = new Board();
		gameOver = false;
		ai = null;
		round = 0;
		selectedTowerPosX = -1;
		selectedTowerPosY = -1;
	}
}