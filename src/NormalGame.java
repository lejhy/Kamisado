
public class NormalGame extends Game {
	
	public boolean nextTurn(Move move, Value type) {
   		if (!gameOver){
		   	if (GameLogic.isValidMove(board, move) && getCurrentPlayer().getType() == type){
		   		makeMove(move);
			   	if (GameLogic.isGameOver(board)) {
			   		if (GameLogic.isDoubleDeadLock(board))
			   			setGameOver(Value.DOUBLE_DEADLOCK);
			   		else 
			   			setGameOver(Value.GAME_OVER);
			   	} else {
				   	if (GameLogic.isDeadLock(board)) {
				   		makeMove(GameLogic.getDeadLockMove(board));
				   	}
				   	change(Value.NEXT_TURN);
			   	}
			   	return true;
		  	}
	   	}
   		return false;
   	}
	
	protected void makeMove (Move move) {
		board.performMove(move);
	   	turn++;
	}
	
	protected void setGameOver(Value cause) {
		System.out.println("game over in normalGame");
   		gameOver = true;
   		gameOverCause = cause;
   		if (getWinner() == player1){
   			score.setWinnerPlayer1();
   		} else {
   			score.setWinnerPlayer2();
   		}
   		change(cause);
	}

	public NormalGame(Player player1, Player player2, int points) {
		super(player1, player2, points);
	}
}
