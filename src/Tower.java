import java.util.HashSet;
import java.util.Set;

public class Tower {
	
private Set<Player> player1;	
private Player player;
private Board newClass1;
private int positionX;
private int positionY; 
   
   public void setPlayer(Player value) {
      this.player = value;
   }
   
   public Player getPlayer() {
      return this.player;
   }
   
     
   
   public Set<Player> getPlayer1() {
      if (this.player1 == null) {
         this.player1 = new HashSet<Player>();
      }
      return this.player1;
   }
  
   
   public void setPositionX(int value) {
      positionX = value;
   }
  
   public void setPositionY(int value) {
      positionY = value;
   }
   
   public int getPositionY() {
      return positionY;
   }
   
   public int getPositionX() {
      return positionX;
   }
 
   
   
   
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
