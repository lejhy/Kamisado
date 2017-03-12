
public class NormalGame extends Game {
	
	public void nextTurn(Move move, Value type) {
   		if (!gameOver){
		   	if (GameLogic.isValidMove(board, move) && getCurrentPlayer().getType() == type){
		   		makeMove(move);
			   	if (GameLogic.isGameOver(board)) {
			   		if (GameLogic.isDoubleDeadLock(board))
			   			gameOver(Value.DOUBLE_DEADLOCK);
			   		else 
			   			gameOver(Value.GAME_OVER);
			   	} else {
				   	if (GameLogic.isDeadLock(board)) {
				   		makeMove(GameLogic.getDeadLockMove(board));
				   	}
				   	change(Value.NEXT_TURN);
			   	}
		  	}
	   	}
   	}
	
	protected void makeMove (Move move) {
		board.performMove(move);
	   	round++;
	}
	
	protected void gameOver(Value cause) {
		System.out.println("game over in normalGame");
   		gameOver = true;
   		gameOverCause = cause;
   		change(cause);
	}

	public NormalGame(Player player1, Player player2) {
		super(player1, player2);
	}
}
