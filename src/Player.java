import java.util.Observable;

public class Player extends Observable{
	protected int score;
	protected String name;
	
	public void setScore(int value) {
		this.score = value;
		hasChanged();
		notifyObservers();
	}
	
	public int getScore() {
		return this.score;
	}

	public String getName() {
		return name;
	}
	
	public Player(String name){
		this.name = name;
		this.score = 0;
	}
}
