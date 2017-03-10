
public class NormalGame extends Game {
	
	public void nextTurn(Move move, Value type) {
   		if (!gameOver){
		   	if (GameLogic.isValidMove(board, move) && getCurrentPlayer().getType() == type){
		   		makeMove(move);
			   	if (GameLogic.isGameOver(board)) {
			   		gameOver();
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
	
	protected void gameOver() {
		System.out.println("game over in normalGame");
   		gameOver = true;
   		change(Value.GAME_OVER);
	}

	public NormalGame(Player player1, Player player2) {
		super(player1, player2);
	}

}
