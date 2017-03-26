import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class GameEntry implements Serializable{
	private Game game;
	private transient StringProperty player1Name;
	private transient StringProperty player2Name;
	private transient IntegerProperty player1Points;
	private transient IntegerProperty player2Points;
	private transient IntegerProperty points;
	private transient IntegerProperty turn;
	
	public GameEntry (Game game) {
		this.game = game;
		this.player1Name = game.getPlayer1().getName();
		this.player2Name = game.getPlayer2().getName();
		this.player1Points = game.getScore().getPlayer1Points();
		this.player2Points = game.getScore().getPlayer2Points();
		this.points = new SimpleIntegerProperty(game.getScore().getPoints());
		this.turn = new SimpleIntegerProperty(game.getTurn());
	}
	
	public Game getGame() {
		return game;
	}
	
	public String getPlayer1Name() {
		return player1Name.get();
	}

	public String getPlayer2Name() {
		return player2Name.get();
	}

	public int getPlayer1Points() {
		return player1Points.get();
	}

	public int getPlayer2Points() {
		return player2Points.get();
	}
	
	public int getPoints() {
		return points.get();
	}
	
	public int getTurn() {
		return turn.get();
	}
	
	public void setGame(Game game) {
		this.game = game;
	}
	
	public void setPlayer1Name(String name) {
		player1Name.set(name);
	}
	
	public void setPlayer2Name(String name) {
		player2Name.set(name);
	}
	
	public void setplayer1Points(int points) {
		player1Points.set(points);
	}
	
	public void setplayer2Points(int points) {
		player2Points.set(points);
	}
	
	public void setPoints(int points) {
		this.points.set(points);
	}
	
	public void setTurn(int turn) {
		this.turn.set(turn);
	}
	
	private void writeObject(ObjectOutputStream out) throws IOException {
		out.defaultWriteObject();
		out.writeObject(player1Name.get());
		out.writeObject(player2Name.get());
		out.writeInt(player1Points.get());
		out.writeInt(player2Points.get());
		out.writeInt(points.get());
		out.writeInt(turn.get());
	}
	
	private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException{
		in.defaultReadObject();
		player1Name = new SimpleStringProperty();
		player1Name.set((String)in.readObject());
		player2Name = new SimpleStringProperty();
		player2Name.set((String)in.readObject());
		player1Points = new SimpleIntegerProperty();
		player1Points.set(in.readInt());
		player2Points = new SimpleIntegerProperty();
		player2Points.set(in.readInt());
		points = new SimpleIntegerProperty();
		points.set(in.readInt());
		turn = new SimpleIntegerProperty();
		turn.set(in.readInt());
	}
}
