import java.util.List;


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
	    	  if (GameLogic.isValidTower(board, x, y)){
	    		  System.out.println("Valid Tower");
	    		  selectedTowerPosX = x;
	    		  selectedTowerPosY = y;
	    		  return 0;
	    	  } else {
	    		  return -1;
	    	  }
	      } else {
	    	  if (GameLogic.isValidMove(board, selectedTowerPosX, selectedTowerPosY, x, y)) {
	    		  System.out.println("Valid Move game");
	    		  board.performMove(selectedTowerPosX, selectedTowerPosY, x, y);
	    		  selectedTowerPosX = -1;
	    		  selectedTowerPosY = -1;
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
	
	public Board getBoard(){
		return board;
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