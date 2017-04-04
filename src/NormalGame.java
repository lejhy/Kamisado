
public class NormalGame extends Game {
	
	public boolean nextTurn(Move move, Value type) {
   		if (getCurrentPlayer().getType() == type){
		   	if (board.makeMove(move)) {
		   		turn++;
		   		if (board.isGameOver()) {
		   			gameOver();
		   		}
		   		change();
		   		return true;
		   	}
	   	}
   		return false;
   	}
	
	protected void gameOver() {
		System.out.println("game over in normalGame");
   		if (board.getWinnerPosition() == Value.BOTTOM){
   			score.setWinnerPlayer1();
   		} else {
   			score.setWinnerPlayer2();
   		}
   		change();
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
