import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.OutputStream;
import java.util.Timer;
import java.util.TimerTask;

import javafx.application.Platform;

public class SpeedGame extends Game {
	
	public int timeLimit;
	private transient Timer timer;
	
	@Override
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
	   	resetTimer();
	   	turn++;
	}
	
	protected void setGameOver(Value cause) {
		System.out.println("game over in speedGame");
   		gameOver = true;
   		gameOverCause = cause;
   		cancelTimer();
   		if (getWinner() == player1){
   			score.setWinnerPlayer1();
   		} else {
   			score.setWinnerPlayer2();
   		}
   		change(cause);
	}
	
	public int getTimeLimit() {
		return timeLimit;
	}
	
	private void resetTimer() {
		timer.cancel();
   		timer = new Timer();
   		timer.schedule(new TimeIsUp(), timeLimit);
   		change(Value.TIMER);
	}
	
	private void cancelTimer() {
		timer.cancel();
	}
	
	class TimeIsUp extends TimerTask{
		public void run() {
			Platform.runLater(new Runnable() {
				public void run() {
					setGameOver(Value.TIME_UP);
				}
			});
		}
	}

	public SpeedGame(Player player1, Player player2, int time, int points) {
		super(player1, player2, points);
		timeLimit = time;
		timer = new Timer();
	}
	
	private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException{
		in.defaultReadObject();
		timer = new Timer();
	}
}
