package model;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class GameEntry implements Serializable {

	private static final long serialVersionUID = 7786195751904987114L;
	private Game game;
	private transient StringProperty whiteName;
	private transient StringProperty blackName;
	private transient IntegerProperty whitePoints;
	private transient IntegerProperty blackPoints;
	private transient IntegerProperty points;
	private transient IntegerProperty turn;
	private transient SimpleLongProperty time;

	public GameEntry(Game game, long time) {
		this.game = game;
		this.whiteName = game.getWhite().getName();
		this.blackName = game.getBlack().getName();
		this.whitePoints = game.getScore().getWhitePoints();
		this.blackPoints = game.getScore().getBlackPoints();
		this.points = new SimpleIntegerProperty(game.getScore().getPoints());
		this.turn = new SimpleIntegerProperty(game.getTurn());
		this.time = new SimpleLongProperty(time);
	}

	public Game getGame() {
		return game;
	}

	public String getWhiteName() {
		return whiteName.get();
	}

	public String getBlackName() {
		return blackName.get();
	}

	public int getWhitePoints() {
		return whitePoints.get();
	}

	public int getBlackPoints() {
		return blackPoints.get();
	}

	public int getPoints() {
		return points.get();
	}

	public int getTurn() {
		return turn.get();
	}

	public long getTime() {
		return time.get();
	}

	public void setGame(Game game) {
		this.game = game;
	}

	public void setWhiteName(String name) {
		whiteName.set(name);
	}

	public void setBlackName(String name) {
		blackName.set(name);
	}

	public void setwhitePoints(int points) {
		whitePoints.set(points);
	}

	public void setblackPoints(int points) {
		blackPoints.set(points);
	}

	public void setPoints(int points) {
		this.points.set(points);
	}

	public void setTurn(int turn) {
		this.turn.set(turn);
	}

	public void setTime(long time) {
		this.time.set(time);
	}

	private void writeObject(ObjectOutputStream out) throws IOException {
		out.defaultWriteObject();
		out.writeObject(whiteName.get());
		out.writeObject(blackName.get());
		out.writeInt(whitePoints.get());
		out.writeInt(blackPoints.get());
		out.writeInt(points.get());
		out.writeInt(turn.get());
		out.writeLong(time.get());
	}

	private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
		in.defaultReadObject();
		whiteName = new SimpleStringProperty();
		whiteName.set((String) in.readObject());
		blackName = new SimpleStringProperty();
		blackName.set((String) in.readObject());
		whitePoints = new SimpleIntegerProperty();
		whitePoints.set(in.readInt());
		blackPoints = new SimpleIntegerProperty();
		blackPoints.set(in.readInt());
		points = new SimpleIntegerProperty();
		points.set(in.readInt());
		turn = new SimpleIntegerProperty();
		turn.set(in.readInt());
		time = new SimpleLongProperty();
		time.set(in.readLong());
	}
}
