import java.util.List;


public class Game {

   private Board board;
   private int round;
   private boolean gameOver;
   private int difficulty;
   private int speed;
   private Player player1;
   private Player player2;
   private Kamisado controller;
   
   public void nextTurn() {
	   if (!gameOver){
		   if (board.getLastPlayer() == player1.getValue()) {
			   board.performMove(player2.getMove());
		   } else {
			   board.performMove(player1.getMove());
		   }
		   round++;
	   }
	   if (GameLogic.isGameOver(board)) {
		   gameOver = true;
	   }
   }
   
   public boolean isGameOver() {
	   return gameOver;
   }
   
   public int getDifficulty(){
	   return difficulty;
   }
   
   public void setDifficulty(int difficulty){
	   this.difficulty = difficulty;
   }
   
   public int getSpeed(){
	   return speed;
   }
   
   public void setSpeed(int speed){
	   this.speed = speed;
   }
   
   public String getPlayer1Name() {
	   return player1.getName();
   }
   
   public void setPlayer1Name(String name) {
	   player1.setName(name);
   }
   
   public String getPlayer2Name() {
	   return player2.getName();
   }
   
   public void setPlayer2Name(String name) {
	   player2.setName(name);
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
		board = new Board();
		gameOver = false;
		round = 0;
		difficulty = 0;
		speed = 0;
		player1 = new ComputerEasy(true, board);
		player2 = new ComputerEasy(false, board);
	}
}