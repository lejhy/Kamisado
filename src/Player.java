import java.util.HashSet;
import java.util.Set;
public abstract class Player {

private Config config;
private int score;
private String name;

public void setConfig(Config value) {
   this.config = value;
}

public Config getConfig() {
   return this.config;
}




public void setScore(int value) {
   this.score = value;
}

public int getScore() {
   return this.score;
}
	
	
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
