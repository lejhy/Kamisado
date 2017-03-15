import java.io.Serializable;

public class Tower implements Serializable{
	
	private boolean playerValue;
	private Value color;
	private int positionX;
	private int positionY;    
	   
   public void setPositionX(int value) {
      this.positionX = value;
   }
  
   public void setPositionY(int value) {
      this.positionY = value;
   }
   
   public Value getColor() {
	   return color;
   }
   
   public boolean getPlayerValue() {
	   return playerValue;
   }
   
   public int getPositionY() {
      return positionY;
   }
   
   public int getPositionX() {
      return positionX;
   }
   
   public Tower (Value color, boolean playerValue, int positionX, int positionY) {
      this.color = color;
      this.playerValue = playerValue;
      this.positionX = positionX;
      this.positionY = positionY;
   }
   
   public Tower(Tower tower){
	   this.color = tower.getColor();
	   this.playerValue = tower.getPlayerValue();
	   this.positionX = tower.getPositionX();
	   this.positionY = tower.getPositionY();
   }
}
