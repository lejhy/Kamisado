import java.util.HashSet;
import java.util.Set;


public class Board {
	private Set<Integer> previousMoves;
	private Color lastColor;
	private Player lastPlayer;
	private Tower[] dragonTowers;
	private Color[][] tiles;
   
   public Tower getTower(int x, int y) {
	   // TODO implement this operation
	   throw new UnsupportedOperationException("not implemented");
   } 
   
   public boolean isValidTower(int x, int y) {
	   // TODO implement this operation
	   throw new UnsupportedOperationException("not implemented");
   }
   
   public boolean isValidMove(Tower tower, int x, int y) {
      // TODO implement this operation
      throw new UnsupportedOperationException("not implemented");
   }
   
   public boolean performMove(Tower tower, int x, int y) {
      // TODO implement this operation
      throw new UnsupportedOperationException("not implemented");
   }
   
   public boolean isGameOver() {
      // TODO implement this operation
      throw new UnsupportedOperationException("not implemented");
   }
   
   public boolean undoLastMove() {
      // TODO implement this operation
      throw new UnsupportedOperationException("not implemented");
   }
}
