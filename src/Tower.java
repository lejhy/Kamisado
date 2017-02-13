
public class Tower {
   private Color color;
   
   public void setColor(Color value) {
      this.color = value;
   }
   
   public Color getColor() {
      return this.color;
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
    * Tower ------------------------- Player
    *           tower        &lt;       player
    * </pre>
    */
   private Player player;
   
   public void setPlayer(Player value) {
      this.player = value;
   }
   
   public Player getPlayer() {
      return this.player;
   }
   
   public void render() {
      // TODO implement this operation
      throw new UnsupportedOperationException("not implemented");
   }
   
   }
