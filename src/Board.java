import java.util.HashSet;
import java.util.Set;


public class Board {
	private Set<Integer> previousMoves;
	private Color lastColor;
	private Color lastPlayerColor;
	private Tower[] towers;
	private Color[][] tiles;

	public Color getTile(int x, int y) {
		return tiles[x][y];
	}
	
	public int getNumOfTowers () {
		return towers.length;
	}
	
	public Color getTowerColor (int i) {
		return towers[i].getColor();
	}
	
	public Color getTowerPlayerColor (int i) {
		return towers[i].getPlayerColor();
	}
	
	public int getTowerPosX (int i) {
		return towers[i].getPositionX();
	}
	
	public int getTowerPosY (int i) {
		return towers[i].getPositionY();
	}
   
   public boolean isValidTower(int x, int y) {
	   Tower tower = getTower(x, y);
	   if (tower == null){
		   return false;
	   } else {
		   if (tower.getPlayerColor() == lastPlayerColor) {
			   return false;
		   } else {
			   if (tower.getColor() == lastColor){
				   return true;
			   } else {
				   return false;
			   }
		   }
	   }
   }
   
   public boolean isValidMove(int towerPosX, int towerPosY, int x, int y) {
      if (isValidTower(towerPosX, towerPosY) == false) {
    	  return false;
      } else if (towerPosX == x && towerPosY == y){
    	  return false;
      } else{
    	  if (lastPlayerColor == Color.black) {
    		  return isValidWhite(towerPosX, towerPosY, x, y);
    	  } else {
    		  return isValidBlack(towerPosX, towerPosY, x, y);
    	  }
      }
   }
   
   private boolean isValidWhite(int towerPosX, int towerPosY, int x, int y) {
	   if (isValidWhiteStraight(towerPosX, towerPosY, x, y)) {
		   return true;
	   } else if (isValidWhiteDiagonal(towerPosX, towerPosY, x, y)) {
		   return true;
	   }
	   return false;
   }
   
   private boolean isValidBlack(int towerPosX, int towerPosY, int x, int y) {
	   if (isValidBlackStraight(towerPosX, towerPosY, x, y)) {
		   return true;
	   } else if (isValidBlackDiagonal( towerPosX, towerPosY, x, y)) {
		   return true;
	   }
	   return false;
   }
   
   private boolean isValidWhiteStraight(int towerPosX, int towerPosY, int x, int y) {
	   if (towerPosX == x && towerPosY > y) {
		   	for (int i = towerPosY; i > y; --i) {
		   		if (getTower(x, i) != null){
		   			return false;
		   		}
		   	}
		   	return true;
	   } else {
		   return false;
	   }
   }
   
   private boolean isValidWhiteDiagonal(int towerPosX, int towerPosY, int x, int y) {
	   int xDiff = towerPosX - x;
	   int yDiff = towerPosY - y;
	   if (xDiff == yDiff && towerPosX > x && towerPosY > y){
		   	for (int i = xDiff; i > 0; i--) {
		   		if (getTower(x+i, y+i) != null){
		   			return false;
		   		}
		   	}
		   	return true;
	   } else if (-xDiff == yDiff && towerPosX < x && towerPosY > y){
		   for (int i = -xDiff; i > 0; i--) {
		   		if (getTower(x-i, y+i) != null){
		   			return false;
		   		}
		   	}
		   	return true;
	   } else {
		   return false;
	   }   
   }
   
   private boolean isValidBlackStraight(int towerPosX, int towerPosY, int x, int y) {
	   if (towerPosX == x && towerPosY < y) {
		   	for (int i = towerPosY; i < y; ++i) {
		   		if (getTower(x, i) != null){
		   			return false;
		   		}
		   	}
		   	return true;
	   } else {
		   return false;
	   }
   }
   
   private boolean isValidBlackDiagonal(int towerPosX, int towerPosY, int x, int y) {
	   int xDiff = towerPosX - x;
	   int yDiff = towerPosY - y;
	   if (xDiff == yDiff && towerPosX < x && towerPosY < y){
		   	for (int i = xDiff; i > 0; i--) {
		   		if (getTower(x-i, y-i) != null){
		   			return false;
		   		}
		   	}
		   	return true;
	   } else if (-xDiff == yDiff && towerPosX > x && towerPosY < y){
		   for (int i = -xDiff; i > 0; i--) {
		   		if (getTower(x+i, y-i) != null){
		   			return false;
		   		}
		   	}
		   	return true;
	   } else {
		   return false;
	   }   
   }
   
   public boolean performMove(int towerPosX, int towerPosY, int x, int y) {
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
   
   private Tower getTower(int x, int y){
	   for (Tower tower: towers){
		   if (tower.getPositionX() == x && tower.getPositionY() == y) {
			   return tower;
		   }
	   }
	   return null;
   }
}
