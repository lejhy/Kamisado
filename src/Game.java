import java.util.List;
import java.util.Observable;


public class Game extends Observable{

   private Board board;
   private Player player1;
   private Player player2;
   private boolean gameOver;
   private int round;
   
   public void nextTurn() {
	   if (!gameOver){
		   Move move = getNextMove();
		   if (GameLogic.isValidMove(board, move)){
			   board.performMove(move);
			   setChanged();
			   notifyObservers(Value.NEXT_TURN);
			   round++;
			   if (GameLogic.isGameOver(board)) {
				   gameOver = true;
				   setChanged();
				   notifyObservers(Value.GAME_OVER);
			   }
		   }
	   }
   }
   
   public Move getNextMove() {
	   if (board.getLastPlayerValue() == player1.getValue()) {
		   return player2.getMove(new Board(board));
	   } else {
		   return player1.getMove(new Board(board));
	   }
   }
   
   public boolean isGameOver() {
	   return gameOver;
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
	
	public Game(Player player1, Player player2){
		board = new Board();
		gameOver = false;
		round = 0;
		this.player1 = player1;
		this.player2 = player2;
	}
}