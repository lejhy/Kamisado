import java.util.Observable;

public class Game extends Observable{
	private Player player1;
	private Player player2;
	private Board board;
   	private boolean gameOver;
   	private int round;
   
   	public void nextTurn(Move move) {
   		if (!gameOver){
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
   
   	public boolean isGameOver() {
   		return gameOver;
   	}
	
	public int getRound() {
		return this.round;
	}
	
	public Board getBoard(){
		return board;
	}
	
	public Game(Player player1, Player player2){
		this.player1 = player1;
		this.player2 = player2;
		board = new Board();
		gameOver = false;
		round = 0;
	}
}