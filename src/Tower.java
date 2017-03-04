import java.util.HashSet;
import java.util.Set;

public class Tower {
	
	private Color playerColor;
	private Color color;
	private int positionX;
	private int positionY;    
	   
   public void setPositionX(int value) {
      positionX = value;
   }
  
   public void setPositionY(int value) {
      positionY = value;
   }
   
   public Color getColor() {
	   return color;
   }
   
   public Color getPlayerColor() {
	   return playerColor;
   }
   
   public int getPositionY() {
      return positionY;
   }
   
   public int getPositionX() {
      return positionX;
   }
   
   public Tower (Color color, Color playerColor, int positionX, int positionY) {
      this.color = color;
      this.playerColor = playerColor;
      this.positionX = positionX;
      this.positionY = positionY;
   }
}
