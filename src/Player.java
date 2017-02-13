import java.util.HashSet;
import java.util.Set;
public class Player {
/**
 * <pre>
 *           0..1     0..*
 * Player ------------------------- Tower
 *           player        &gt;       tower
 * </pre>
 */
private Set<Tower> tower;

public Set<Tower> getTower() {
   if (this.tower == null) {
this.tower = new HashSet<Tower>();
   }
   return this.tower;
}

/**
 * <pre>
 *           0..*     0..1
 * Player ------------------------- Game
 *           player        &lt;       game
 * </pre>
 */
private Game game;

public void setGame(Game value) {
   this.game = value;
}

public Game getGame() {
   return this.game;
}

	private Tower[] dragonTowers;
	private Color color;
	private String name;

	public Player (Color color, String name){
		playing = false;
   		this.color = color;
   		this.name = name;
   		dragonTowers = new String[8][8];
   		for (int i = 0; i < 8; i++){
   			for (int j = 0; j < 8; j++){
   				dragonTowers[i][j] = "empty";
   			}
   		}
   		if (color == "1"){
   			dragonTowers[7][0] = "brown";
   			dragonTowers[7][1] = "green";
   			dragonTowers[7][2] = "red";
   			dragonTowers[7][3] = "yellow";
   			dragonTowers[7][4] = "pink";
   			dragonTowers[7][5] = "purple";
   			dragonTowers[7][6] = "blue";
   			dragonTowers[7][7] = "orange";
   		} else if (color == "2"){
   			dragonTowers[0][0] = "orange";
   			dragonTowers[0][1] = "blue";
   			dragonTowers[0][2] = "purple";
   			dragonTowers[0][3] = "pink";
   			dragonTowers[0][4] = "yellow";
   			dragonTowers[0][5] = "red";
   			dragonTowers[0][6] = "green";
   			dragonTowers[0][7] = "brown";
   		} else {
   			System.out.println("Wrong player colour");
   		}
   	}
	
	public void  render(){
		for (int i = 0; i < 8; i++){
			for (int j = 0; j < 8; j++){
				System.out.print(dragonTowers[i][j] + "\t");
			}
			System.out.print("\n");
		}
	}
	
	public Tower[] getDragonTowers(){
		return dragonTowers;
	}
	
	public String getDragonTowers(int i, int j){
		return dragonTowers[i][j];
	}
	
	public void move(int startI,int startJ, int finishI, int finishJ){
		dragonTowers[finishI][finishJ] = dragonTowers[startI][startJ];
		dragonTowers[startI][startJ] = "empty";
	}
	
	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public String getName() {
		return name;
	}

	public void setName(String position) {
		this.name = position;
	}
}
