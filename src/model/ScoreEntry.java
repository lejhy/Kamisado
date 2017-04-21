package model;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class ScoreEntry implements Serializable {

	private static final long serialVersionUID = 5292610156233973971L;
	private transient StringProperty whiteName;
	private transient StringProperty blackName;
	private transient IntegerProperty whitePoints;
	private transient IntegerProperty blackPoints;

	public ScoreEntry(String whiteName, String blackName, int whitePoints, int blackPoints) {
		this.whiteName = new SimpleStringProperty(whiteName);
		this.blackName = new SimpleStringProperty(blackName);
		this.whitePoints = new SimpleIntegerProperty(whitePoints);
		this.blackPoints = new SimpleIntegerProperty(blackPoints);
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

	private void writeObject(ObjectOutputStream out) throws IOException {
		out.defaultWriteObject();
		out.writeObject(whiteName.get());
		out.writeObject(blackName.get());
		out.writeInt(whitePoints.get());
		out.writeInt(blackPoints.get());
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
	}
}
