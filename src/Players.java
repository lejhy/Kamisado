import java.util.Observer;

public abstract class Players{
	Player player1;
	Player player2;
	Player lastPlayer;
	
	public boolean authoriseUserInput() {
		
		return true;
	}
	
	private void switchPlayers() {
		if (lastPlayer == player1) {
			lastPlayer = player2;
		} else {
			lastPlayer = player1;
		}
	}
}
