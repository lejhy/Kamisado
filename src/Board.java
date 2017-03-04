import java.util.ArrayList;
import java.util.List;
import java.util.Set;


public class Board {
	private List<Integer> previousMoves;
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
	
	public Board (Board board) {
		this.tiles = new Color[8][8];
		for (int i = 0; i < 8; i++){
			for (int j = 0; j < 8; j++){
				this.tiles = board.tiles;
			}
		}
		
		this.towers = new ArrayList<Tower>();
		for (Tower tower: board.towers){
			this.towers.add(new Tower(tower));
		}
		
		this.lastPlayerColor = board.lastPlayerColor;
		this.lastColor = board.lastColor;
	}
	
	// delete before finishing
	
	public Color[][] getTiles() {
		return tiles;
	}
	
	public List<Tower> getTowers() {
		return towers;
	}
	
	
	// delete before finishing
	
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
	
	public int getTower (int i) {
		return towers.get(i).getPositionX();
	}
	
	public Tower getTower(int x, int y){
	   for (Tower tower: towers){
		   if (tower.getPositionX() == x && tower.getPositionY() == y) {
			   return tower;
		   }
	   }
	   return null;
   }
	
	public int getTowerPosY (int i) {
		return towers.get(i).getPositionY();
	}
	
	public Color getLastColor () {
		return lastColor;
	}
	
	public Color getLastPlayerColor () {
		return lastPlayerColor;
	}
   
   public boolean performMove(int towerPosX, int towerPosY, int x, int y) {
      if (GameLogic.isValidMove(this, towerPosX, towerPosY, x, y)){
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
}
