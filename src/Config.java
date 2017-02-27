import java.util.Set;
import java.util.HashSet;

public class Config {
   /**
    * <pre>
    *           0..1     2..2
    * Config ------------------------- Player
    *           config        &gt;       player
    * </pre>
    */
   private Set<Player> player;
   
   public Set<Player> getPlayer() {
      if (this.player == null) {
         this.player = new HashSet<Player>();
      }
      return this.player;
   }
   
   /**
    * <pre>
    *           0..*     0..1
    * Config ------------------------- Game
    *           config        &lt;       game
    * </pre>
    */
   private Game game;
   
   public void setGame(Game value) {
      this.game = value;
   }
   
   public Game getGame() {
      return this.game;
   }
   
   private int difficulty;
   
   public void setDifficulty(int value) {
      this.difficulty = value;
   }
   
   public int getDifficulty() {
      return this.difficulty;
   }
   
   private int speed;
   
   public void setSpeed(int value) {
      this.speed = value;
   }
   
   public int getSpeed() {
      return this.speed;
   }
   
   private Player player1;
   
   public void setPlayer1(Player value) {
      this.player1 = value;
   }
   
   public Player getPlayer1() {
      return this.player1;
   }
   
   private Player player2;
   
   public void setPlayer2(Player value) {
      this.player2 = value;
   }
   
   public Player getPlayer2() {
      return this.player2;
   }
   
   }
