import java.io.Serializable;
import java.util.Observable;

public class Score extends Observable implements Serializable{
	private int round;
	private int points;
	private int player1Points;
	private int player2Points;
	
	public Score(int points) {
		round = 1;
		this.points = points;
		player1Points = 0;
		player2Points = 0;
	}
	
	public Score(Score score) {
		round = score.round;
		points = score.points;
		player1Points = score.player1Points;
		player2Points = score.player2Points;
	}
	
	public void setWinnerPlayer1() {
		player1Points++;
		setChanged();
		notifyObservers();
	}
	
	public void setWinnerPlayer2() {
		player2Points++;
		setChanged();
		notifyObservers();
	}
	
	public boolean hasNextRound() {
		if (round < points) {
			return true;
		} else {
			return false;
		}
	}
	
	public boolean nextRound() {
		if (hasNextRound()) {
			round++;
			setChanged();
			notifyObservers();
			return true;
		} else {
			return false;
		}
	}

	public int getRound() {
		return round;
	}


	public int getPoints() {
		return points;
	}

	public int getPlayer1Points() {
		return player1Points;
	}


	public int getPlayer2Points() {
		return player2Points;
	}
}
