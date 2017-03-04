import java.util.HashSet;
import java.util.Set;
public abstract class Player {

	private int score;
	private String name;
	
	public void setScore(int value) {
	   this.score = value;
	}
	
	public int getScore() {
	   return this.score;
	}

	public String getName() {
		return name;
	}

	public void setName(String position) {
		this.name = position;
	}
}
