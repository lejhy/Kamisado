import java.util.ArrayList;
import java.util.List;
import java.util.Set;


public class Board {
	private Set<Integer> previousMoves;
	private Color lastColor;
	private Color lastPlayerColor;
	private List<Tower> towers;
	private Color[][] tiles;
	
	public Board () {
		Color[][] tiles = {
				{Color.orange, 	Color.blue, 	Color.purple, 	Color.pink, 	Color.yellow, 	Color.red, 		Color.green, 	Color.brown},
				{Color.red, 	Color.orange,	Color.pink, 	Color.green, 	Color.blue, 	Color.yellow, 	Color.brown, 	Color.purple},
				{Color.green, 	Color.pink, 	Color.orange, 	Color.red, 		Color.purple, 	Color.brown, 	Color.yellow, 	Color.blue},
				{Color.pink, 	Color.purple, 	Color.blue, 	Color.orange, 	Color.brown, 	Color.green, 	Color.red, 		Color.yellow},
				{Color.yellow, 	Color.red, 		Color.green, 	Color.brown, 	Color.orange, 	Color.blue, 	Color.purple, 	Color.pink},
				{Color.blue, 	Color.yellow, 	Color.brown, 	Color.purple, 	Color.red, 		Color.orange, 	Color.pink, 	Color.green},
				{Color.purple, 	Color.brown, 	Color.yellow, 	Color.blue, 	Color.green, 	Color.pink, 	Color.orange, 	Color.red},
				{Color.brown, 	Color.green,	Color.red, 		Color.yellow,	Color.pink, 	Color.purple, 	Color.blue, 	Color.orange} 
		};
		this.tiles = tiles;
		
		towers = new ArrayList<Tower>();
		for (int i = 0; i < 8; i++){
			towers.add(new Tower(tiles[i][0], Color.black, i, 0));
		}
		for (int i = 0; i < 8; i++){
			towers.add(new Tower(tiles[i][7], Color.white, i, 7));
		}
		
		lastPlayerColor = Color.black;
		lastColor = null;
	}

	public Color[][] getTiles(){
		return tiles;
	}
	
	public List<Tower> getTowers(){
		return towers;
	}
	
	public Color getTile(int x, int y) {
		return tiles[x][y];
	}
	
	public int getNumOfTowers () {
		return towers.size();
	}
	
	public Color getTowerColor (int i) {
		return towers.get(i).getColor();
	}
	
	public Color getTowerPlayerColor (int i) {
		return towers.get(i).getPlayerColor();
	}
	
	public int getTowerPosX (int i) {
		return towers.get(i).getPositionX();
	}
	
	public int getTowerPosY (int i) {
		return towers.get(i).getPositionY();
	}
   
   public boolean isValidTower(int x, int y) {
	   Tower tower = getTower(x, y);
	   if (tower == null){
		   return false;
	   } else {
		   if (tower.getPlayerColor() == lastPlayerColor) {
			   return false;
		   } else {
			   if (tower.getColor() == lastColor || lastColor == null){
				   return true;
			   } else {
				   return false;
			   }
		   }
	   }
   }
   
   public boolean isValidMove(int towerPosX, int towerPosY, int x, int y) {
	   System.out.println("Valid move");
      if (isValidTower(towerPosX, towerPosY) == false) {
    	  System.out.println("Valid move invalid tower");
    	  return false;
      } else if (towerPosX == x && towerPosY == y){
    	  System.out.println("Valid move same pos");
    	  return false;
      } else{
    	  System.out.println("Valid move picking player");
    	  if (lastPlayerColor == Color.black) {
    		  System.out.println("Valid move black");
    		  return isValidWhite(towerPosX, towerPosY, x, y);
    	  } else {
    		  return isValidBlack(towerPosX, towerPosY, x, y);
    	  }
      }
   }
   
   private boolean isValidWhite(int towerPosX, int towerPosY, int x, int y) {
	   System.out.println("Valid white");
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
	   System.out.println("Valid white straight");
	   if (towerPosX == x && towerPosY > y) {
		   System.out.println("Valid white straight inside");
		   	for (int i = y; i < towerPosY; i++) {
		   		System.out.println("Valid white straight loop");
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
	   System.out.println("Valid white diagonal");
	   int xDiff = towerPosX - x;
	   int yDiff = towerPosY - y;
	   if (xDiff == yDiff && towerPosX > x && towerPosY > y){
		   System.out.println("Valid white diagonal +");
		   	for (int i = xDiff; i > 0; i--) {
		   		System.out.println("Valid white diagonal loop");
		   		if (getTower(towerPosX-i, towerPosY-i) != null){
		   			System.out.println("Valid white diagonal loop fail at" + towerPosX + towerPosY + i);
		   			return false;
		   		}
		   	}
		   	System.out.println("Valid white diagonal true");
		   	return true;
	   } else if (-xDiff == yDiff && towerPosX < x && towerPosY > y){
		   System.out.println("Valid white diagonal -");
		   for (int i = -xDiff; i > 0; i--) {
			   System.out.println("Valid white diagonal loop");
		   		if (getTower(towerPosX+i, towerPosY-i) != null){
		   			System.out.println("Valid white diagonal loop fail at" + towerPosX + towerPosY + i);
		   			return false;
		   		}
		   	}
		   	System.out.println("Valid white diagonal true");
		   	return true;
	   } else {
		   return false;
	   }   
   }
   
   private boolean isValidBlackStraight(int towerPosX, int towerPosY, int x, int y) {
	   if (towerPosX == x && towerPosY < y) {
		   	for (int i = y; i > towerPosY; i--) {
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
		   		if (getTower(towerPosX+i, towerPosY+i) != null){
		   			return false;
		   		}
		   	}
		   	return true;
	   } else if (-xDiff == yDiff && towerPosX > x && towerPosY < y){
		   for (int i = -xDiff; i > 0; i--) {
		   		if (getTower(towerPosX-i, towerPosY+i) != null){
		   			return false;
		   		}
		   	}
		   	return true;
	   } else {
		   return false;
	   }   
   }
   
   public boolean performMove(int towerPosX, int towerPosY, int x, int y) {
      if (isValidMove(towerPosX, towerPosY, x, y)){
    	  Tower tower = getTower(towerPosX, towerPosY);
    	  tower.setPositionX(x);
    	  tower.setPositionY(y);
    	  lastColor = tiles[x][y];
    	  nextPlayer();
    	  return true;
      } else {
    	  return false;
      }
   }
   
   public void nextPlayer(){
	   if (lastPlayerColor == Color.black){
		   lastPlayerColor = Color.white;
	   } else {
		   lastPlayerColor = Color.black;
	   }
   }
   
   public boolean isGameOver() {
      return false;
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
