import java.util.Set;
import java.util.HashSet;

public class Config {
   
   private Set<Player> player;
   private Game game;
   private int difficulty;
   private int speed;
   private Player player1;
   private Player player2;
   
   public Set<Player> getPlayer() {
      if (this.player == null) {
         this.player = new HashSet<Player>();
      }
      return this.player;
   }
   
    
   public void setGame(Game value) {
      this.game = value;
   }
   
   public Game getGame() {
      return this.game;
   }
   
 
   
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
   
   
   
   public void setPlayer1(Player value) {
      this.player1 = value;
   }
   
   public Player getPlayer1() {
      return this.player1;
   }
   
   
   
   public void setPlayer2(Player value) {
      this.player2 = value;
   }
   
   public Player getPlayer2() {
      return this.player2;
   }
   
   }
