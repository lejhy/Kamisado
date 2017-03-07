public class Tower {
	
	private boolean player;
	private Color color;
	private int positionX;
	private int positionY;    
	   
   public void setPositionX(int value) {
      this.positionX = value;
   }
  
   public void setPositionY(int value) {
      this.positionY = value;
   }
   
   public Color getColor() {
	   return color;
   }
   
   public boolean getPlayer() {
	   return player;
   }
   
   public int getPositionY() {
      return positionY;
   }
   
   public int getPositionX() {
      return positionX;
   }
   
   public Tower (Color color, boolean player, int positionX, int positionY) {
      this.color = color;
      this.player = player;
      this.positionX = positionX;
      this.positionY = positionY;
   }
   
   public Tower(Tower tower){
	   this.color = tower.getColor();
	   this.player = tower.getPlayer();
	   this.positionX = tower.getPositionX();
	   this.positionY = tower.getPositionY();
   }
}
