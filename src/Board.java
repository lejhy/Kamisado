import java.util.HashSet;
import java.util.Set;


public class Board {
	private Set<Integer> previousMoves;
	private Color lastColor;
	private Set<Player> player;
	private Player lastPlayer;
	private Game game;
	private Set<Tower> tower;
	private Tower[] dragonTowers;
	private Color[][] tiles;
	   
	
   public void setPreviousMoves(Set<Integer> value) {
      this.previousMoves = value;
   }
   
   public Set<Integer> getPreviousMoves() {
      return this.previousMoves;
   }
   
  
   
   public void setLastColor(Color value) {
      this.lastColor = value;
   }
   
   public Color getLastColor() {
      return this.lastColor;
   }
   
 
   
   public Set<Player> getPlayer() {
      if (this.player == null) {
         this.player = new HashSet<Player>();
      }
      return this.player;
   }
   

   
   public void setLastPlayer(Player value) {
      this.lastPlayer = value;
   }
   
   public Player getLastPlayer() {
      return this.lastPlayer;
   }
   
/**
    * <pre>
    *           0..*     0..1
    * Board ------------------------- Game
    *           board        &lt;       game
    * </pre>
    */

   
   public void setGame(Game value) {
      this.game = value;
   }
   
   public Game getGame() {
      return this.game;
   }
   
   /**
    * <pre>
    *           0..1     0..*
    * Board ------------------------- Tower
    *           newClass1        &gt;       tower
    * </pre>
    */
   
   public Set<Tower> getTower() {
      if (this.tower == null) {
         this.tower = new HashSet<Tower>();
      }
      return this.tower;
   }
   
   
   
   public Tower[] getDragonTowers() {
      return this.dragonTowers;
   }
   
   public Set<Integer> getValidMoves(Tower tower) {
      // TODO implement this operation
      throw new UnsupportedOperationException("not implemented");
   }
   
   public boolean performMove(Player player) {
      // TODO implement this operation
      throw new UnsupportedOperationException("not implemented");
   }
   
   public boolean isWinningMove() {
      // TODO implement this operation
      throw new UnsupportedOperationException("not implemented");
   }
   
   public boolean undoLastMove() {
      // TODO implement this operation
      throw new UnsupportedOperationException("not implemented");
   }
   
	
	public Color[][] getTiles(){
		return tiles;
	}
}
