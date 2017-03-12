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
	   	resetTimer();
	   	round++;
	}
	
	protected void gameOver(Value cause) {
		System.out.println("game over in speedGame");
   		gameOver = true;
   		gameOverCause = cause;
   		cancelTimer();
   		change(cause);
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
			gameOver(Value.TIME_UP);
		}
	}

	public SpeedGame(Player player1, Player player2) {
		super(player1, player2);
		timer = new Timer();
		timer.schedule(new TimeIsUp(), timeLimit);
	}
}
