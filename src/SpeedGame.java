import java.util.Timer;
import java.util.TimerTask;

public class SpeedGame extends Game {
	
	private Timer timer;
	
	protected void switchPlayer() {
   		if (currentPlayer == player1) {
   			currentPlayer = player2;
   		} else {
   			currentPlayer = player1;
   		}
   		timer.cancel();
   		timer = new Timer();
   		timer.schedule(new TimeIsUp(), 5000);
   	}
	
	class TimeIsUp extends TimerTask{
		public void run() {
			gameOver = true;
			switchPlayer();
			change();
			timer.cancel();
		}
	}

	public SpeedGame(Player player1, Player player2) {
		super(player1, player2);
		timer = new Timer();
		timer.schedule(new TimeIsUp(), 5000);
	}

}
