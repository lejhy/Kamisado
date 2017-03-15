import java.io.Serializable;
import java.util.Observable;

public class Player extends Observable implements Serializable{
	protected int score;
	protected String name;
	private Value type;
	
	public void setScore(int value) {
		this.score = value;
		hasChanged();
		notifyObservers();
	}
	
	public Value getType(){
		return type;
	}
	
	public int getScore() {
		return this.score;
	}

	public String getName() {
		return name;
	}
	
	public Player(String name, Value type){
		this.name = name;
		this.score = 0;
		this.type = type;
	}
	
	public Player(Player player) {
		this.name = player.name;
		this.score = player.score;
		this.type = player.getType();
	}
}