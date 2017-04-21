package model;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.List;
import java.util.Observable;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Score extends Observable implements Serializable {

	private static final long serialVersionUID = 4941493498034387048L;
	protected int round;
	protected int points;
	protected transient IntegerProperty whitePoints;
	protected transient IntegerProperty blackPoints;

	public Score(int points) {
		round = 1;
		this.points = points;
		whitePoints = new SimpleIntegerProperty();
		whitePoints.set(0);
		blackPoints = new SimpleIntegerProperty();
		blackPoints.set(0);
	}

	public Score(Score score) {
		round = score.round;
		points = score.points;
		whitePoints = new SimpleIntegerProperty(score.whitePoints.get());
		blackPoints = new SimpleIntegerProperty(score.blackPoints.get());
	}

	public void updatePoints(List<Piece> pieces) {
		int whitePoints = 0;
		int blackPoints = 0;
		for (Piece piece : pieces) {
			if (piece.getPlayerPosition() == Value.BOTTOM) {
				whitePoints += piece.getPoints();
			} else {
				blackPoints += piece.getPoints();
			}
		}
		this.whitePoints.set(whitePoints);
		this.blackPoints.set(blackPoints);
	}

	public boolean hasNextRound() {
		if (whitePoints.get() < points && blackPoints.get() < points) {
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

	public IntegerProperty getWhitePoints() {
		return whitePoints;
	}

	public IntegerProperty getBlackPoints() {
		return blackPoints;
	}

	private void writeObject(ObjectOutputStream out) throws IOException {
		out.defaultWriteObject();
		out.writeInt(whitePoints.get());
		out.writeInt(blackPoints.get());
	}

	private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
		in.defaultReadObject();
		whitePoints = new SimpleIntegerProperty();
		whitePoints.set(in.readInt());
		blackPoints = new SimpleIntegerProperty();
		blackPoints.set(in.readInt());
	}
}
