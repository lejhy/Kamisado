import java.util.Observable;

public class Game extends Observable{
	private Player player1;
	private Player player2;
	private Player currentPlayer;
	private Board board;
   	private boolean gameOver;
   	private int round;
   
   	public void nextTurn(Move move, Value type) {
   		if (!gameOver){
		   	if (GameLogic.isValidMove(board, move) && currentPlayer.getType() == type){
			   	board.performMove(move);
			   	round++;
			   	if (GameLogic.isGameOver(board)) {
			   		System.out.println("game over game");
			   		gameOver = true;
			   	} else {
				   	switchPlayer();
				   	if (GameLogic.isDeadLock(board)) {
				   		board.performMove(GameLogic.getZeroMove(board));
				   		round++;
				   		switchPlayer();
				   	}
			   	}
			   	setChanged();
			   	notifyObservers(Value.NEXT_TURN);
		  	}
	   	}
   	}
   	
   	public Player getCurrentPlayer() {
   		return new Player(currentPlayer);
   	}
   	
   	public Player getPlayer1() {
   		return player1;
   	}
   	
   	public Player getPlayer2() {
   		return player2;
   	}
   
   	private void switchPlayer() {
   		if (currentPlayer == player1) {
   			currentPlayer = player2;
   		} else {
   			currentPlayer = player1;
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
		currentPlayer = player1;
		board = new Board();
		gameOver = false;
		round = 0;
		System.out.println("newGameCreated");
	}
}