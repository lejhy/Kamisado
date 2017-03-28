
public class NormalGame extends Game {
	
	public boolean nextTurn(Move move, Value type) {
   		if (!isGameOver() && getCurrentPlayer().getType() == type){
		   	
	   	}
   		return false;
   	}
	
	protected void makeMove (Move move) {
		board.performMove(move);
	   	turn++;
	}
	
	protected void setGameOver(Value cause) {
		System.out.println("game over in normalGame");
   		gameOver.set(true);
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
	
	public NormalGame(NormalGame game) {
		super((Game) game);
	}
	
	protected Game clone() {
		Game game = new NormalGame(this);
		return game;
	}
}
