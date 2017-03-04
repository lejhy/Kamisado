import java.util.Set;
import java.util.HashSet;

public class Config {
   
   private int difficulty;
   private int speed;
   private Player player1;
   private Player player2;
   
   public void setDifficulty(int value) {
      this.difficulty = value;
   }
   
   public int getDifficulty() {
      return this.difficulty;
   }
   
   public void setSpeed(int value) {
      this.speed = value;
   }
   
   public int getSpeed() {
      return this.speed;
   }   
   
   public void setPlayer1Name(String name) {
      this.player1.setName(name);
   }
   
   public Player getPlayer1() {
      return this.player1;
   }
   
   public void setPlayer2Name(String name) {
	   this.player2.setName(name);
   }
   
   public Player getPlayer2() {
      return this.player2;
   }
}
