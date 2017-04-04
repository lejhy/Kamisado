import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Observable;
import java.util.Timer;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Score extends Observable implements Serializable{
	protected int round;
	protected int points;
	protected transient IntegerProperty player1Points;
	protected transient IntegerProperty player2Points;
	
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
		player1Points = new SimpleIntegerProperty(score.player1Points.get());
		player2Points = new SimpleIntegerProperty(score.player2Points.get());
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
	
	private void writeObject(ObjectOutputStream out) throws IOException {
		out.defaultWriteObject();
		out.writeInt(player1Points.get());
		out.writeInt(player2Points.get());
	}
	
	private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException{
		in.defaultReadObject();
		player1Points = new SimpleIntegerProperty();
		player1Points.set(in.readInt());
		player2Points = new SimpleIntegerProperty();
		player2Points.set(in.readInt());
	}
}
