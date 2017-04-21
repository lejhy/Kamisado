package model;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Observable;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Player extends Observable implements Serializable {

	private static final long serialVersionUID = -2569796179467261758L;
	protected int score;
	protected transient StringProperty name;
	private Value type;

	public void setScore(int value) {
		this.score = value;
		hasChanged();
		notifyObservers();
	}

	public Value getType() {
		return type;
	}

	public int getScore() {
		return this.score;
	}

	public StringProperty getName() {
		return name;
	}

	public Player(String name, Value type) {
		this.name = new SimpleStringProperty();
		this.name.set(name);
		this.score = 0;
		this.type = type;
	}

	public Player(Player player) {
		this.name = new SimpleStringProperty(player.name.get());
		this.score = player.score;
		this.type = player.getType();
	}

	private void writeObject(ObjectOutputStream out) throws IOException {
		out.defaultWriteObject();
		out.writeObject(name.get());
	}

	private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
		in.defaultReadObject();
		name = new SimpleStringProperty();
		name.set((String) in.readObject());
	}
}