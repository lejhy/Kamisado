import java.util.Timer;
import java.util.TimerTask;

public class SpeedGame extends Game {
	
	public int timeLimit = 5000;
	private Timer timer;
	
	@Override
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
	   	resetTimer();
	   	round++;
	}
	
	protected void gameOver() {
		System.out.println("game over in speedGame");
   		gameOver = true;
   		cancelTimer();
   		change(Value.GAME_OVER);
	}
	
	public int getTimeLimit() {
		return timeLimit;
	}
	
	private void resetTimer() {
		timer.cancel();
   		timer = new Timer();
   		timer.schedule(new TimeIsUp(), timeLimit);
	}
	
	private void cancelTimer() {
		timer.cancel();
	}
	
	class TimeIsUp extends TimerTask{
		public void run() {
			gameOver();
		}
	}

	public SpeedGame(Player player1, Player player2) {
		super(player1, player2);
		timer = new Timer();
		timer.schedule(new TimeIsUp(), timeLimit);
	}
}
