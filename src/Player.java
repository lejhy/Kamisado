import java.util.HashSet;
import java.util.Set;
public abstract class Player {
/**
 * <pre>
 *           2..2     0..1
 * Player ------------------------- Config
 *           player        &lt;       config
 * </pre>
 */
private Config config;

public void setConfig(Config value) {
   this.config = value;
}

public Config getConfig() {
   return this.config;
}


private int score;

public void setScore(int value) {
   this.score = value;
}

public int getScore() {
   return this.score;
}
	private String name;
	
	public void move(){
		dragonTowers[finishI][finishJ] = dragonTowers[startI][startJ];
		dragonTowers[startI][startJ] = "empty";
	}

	public String getName() {
		return name;
	}

	public void setName(String position) {
		this.name = position;
	}
}
