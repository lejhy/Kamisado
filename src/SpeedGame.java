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
		if (getCurrentPlayer().getType() == type){
		   	if (board.makeMove(move)) {
		   		resetTimer();
		   		turn++;
		   		if (board.isGameOver()) {
		   			gameOver();
		   		}
		   		return true;
		   	}
	   	}
   		return false;
   	}
	
	protected void gameOver() {
		System.out.println("game over in speedGame");
   		cancelTimer();
   		if (board.getWinnerPosition() == Value.BOTTOM){
   			score.setWinnerPlayer1();
   		} else {
   			score.setWinnerPlayer2();
   		}
   		change();
	}
	
	public int getTimeLimit() {
		return timeLimit;
	}
	
	private void resetTimer() {
		timer.cancel();
   		timer = new Timer();
   		timer.schedule(new TimeIsUp(), timeLimit);
   		change(Value.TIMER_RESET);
	}
	
	private void cancelTimer() {
		timer.cancel();
	}
	
	class TimeIsUp extends TimerTask{
		public void run() {
			Platform.runLater(new Runnable() {
				public void run() {
					board.setGameOver(Value.TIME_UP);
					gameOver();
				}
			});
		}
	}

	public SpeedGame(Player player1, Player player2, int time, int points, boolean randomBoard) {
		super(player1, player2, points, randomBoard);
		timeLimit = time;
		timer = new Timer();
	}
	
	public SpeedGame(SpeedGame game) {
		super((Game) game);
		timeLimit = game.getTimeLimit();
		timer = new Timer();
	}
	
	private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException{
		in.defaultReadObject();
		timer = new Timer();
	}

	protected Game clone() {
		Game game = new SpeedGame(this);
		return game;
	}
	
	@Override
	public void purge() {
		cancelTimer();
		change(Value.TIMER_HIDE);
		this.deleteObservers();
		score.deleteObservers();
		board.deleteObservers();
	}
}
