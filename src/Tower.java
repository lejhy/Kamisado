import java.util.HashSet;
import java.util.Set;

public class Tower {
private Player player;
   
   public void setPlayer(Player value) {
      this.player = value;
   }
   
   public Player getPlayer() {
      return this.player;
   }
   
   /**
    * <pre>
    *           0..*     0..*
    * Tower ------------------------> Player
    *           tower        &gt;       player1
    * </pre>
    */
   private Set<Player> player1;
   
   public Set<Player> getPlayer1() {
      if (this.player1 == null) {
         this.player1 = new HashSet<Player>();
      }
      return this.player1;
   }
   
   
   private String/*No type specified!*/ positionX;
   
   public void setPositionX(String/*No type specified!*/ value) {
      this.positionX = value;
   }
   
   public String/*No type specified!*/ getPositionX() {
      return this.positionX;
   }
   
   private String/*No type specified!*/ positionY;
   
   public void setPositionY(String/*No type specified!*/ value) {
      this.positionY = value;
   }
   
   public String/*No type specified!*/ getPositionY() {
      return this.positionY;
   }
   
   /**
    * <pre>
    *           0..*     0..1
    * Tower ------------------------- Board
    *           tower        &lt;       newClass1
    * </pre>
    */
   private Board newClass1;
   
   public void setNewClass1(Board value) {
      this.newClass1 = value;
   }
   
   public Board getNewClass1() {
      return this.newClass1;
   }
   
   public Tower (Color color, Player player) {
      // TODO implement this operation
      throw new UnsupportedOperationException("not implemented");
   }
   
   }
