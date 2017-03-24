import java.io.Serializable;
import java.util.Observable;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Score extends Observable implements Serializable{
	private int round;
	protected int points;
	protected IntegerProperty player1Points;
	protected IntegerProperty player2Points;
	
	public Score(int points) {
		round = 1;
		this.points = points;
		player1Points = new SimpleIntegerProperty();
		player1Points.set(0);
		player2Points = new SimpleIntegerProperty();
		player2Points.set(0);
	}
	
	public Score(Score score) {
		round = score.round;
		points = score.points;
		player1Points = score.player1Points;
		player2Points = score.player2Points;
	}
	
	public void setWinnerPlayer1() {
		player1Points.set(player1Points.get() + 1);
	}
	
	public void setWinnerPlayer2() {
		player2Points.set(player2Points.get() + 1);
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

	public IntegerProperty getPlayer1Points() {
		return player1Points;
	}


	public IntegerProperty getPlayer2Points() {
		return player2Points;
	}
}
