import java.util.ArrayList;
import java.util.List;

public class Board {
	private List<Move> previousMoves;
	private Color lastColor;
	private boolean lastPlayerValue;
	private List<Tower> towers;
	private Color[][] tiles;
	
	public List<Tower> getTowers() {
		return towers;
	}
	
	public Color[][] getTiles() {
		return tiles;
	}
	
	public Board () {
		initTiles();
		initTowers();
		lastPlayerValue = false;
		lastColor = null;
		previousMoves = new ArrayList<Move>();
	}
	
	public Board (Board board) {
		initTiles(board.tiles);
		initTowers(board.towers);
		this.lastPlayerValue = board.lastPlayerValue;
		this.lastColor = board.lastColor;
		previousMoves = new ArrayList<Move>();
	}
	
	private void initTiles() {
		Color[][] tiles = {
				{Color.orange,	Color.red, 		Color.green, 	Color.pink,		Color.yellow,	Color.blue, 	Color.purple,	Color.brown},
				{Color.blue,	Color.orange,	Color.pink, 	Color.purple, 	Color.red, 		Color.yellow, 	Color.brown, 	Color.green},
				{Color.purple,	Color.pink,		Color.orange, 	Color.blue,		Color.green, 	Color.brown, 	Color.yellow, 	Color.red},
				{Color.pink,	Color.green,	Color.red, 		Color.orange,	Color.brown,	Color.purple, 	Color.blue, 	Color.yellow},
				{Color.yellow, 	Color.blue,		Color.purple, 	Color.brown,	Color.orange,	Color.red,		Color.green,	Color.pink},
				{Color.red, 	Color.yellow,	Color.brown, 	Color.green,	Color.blue, 	Color.orange,	Color.pink, 	Color.purple},
				{Color.green, 	Color.brown,	Color.yellow,	Color.red, 		Color.purple,	Color.pink, 	Color.orange,	Color.blue},
				{Color.brown,	Color.purple,	Color.blue,		Color.yellow, 	Color.pink,		Color.green,	Color.red,		Color.orange}					 
		};
		this.tiles = tiles;
	}
	
	private void initTiles(Color[][] tiles) {
		this.tiles = new Color[8][8];
		for (int i = 0; i < 8; i++){
			for (int j = 0; j < 8; j++){
				this.tiles = tiles;
			}
		}
	}
	
	private void initTowers() {
		towers = new ArrayList<Tower>();
		for (int i = 0; i < 8; i++){
			towers.add(new Tower(tiles[i][0], false, i, 0));
		}
		for (int i = 0; i < 8; i++){
			towers.add(new Tower(tiles[i][7], true, i, 7));
		}
	}
	
	private void initTowers(List<Tower> towers) {
		this.towers = new ArrayList<Tower>();
		for (Tower tower: towers){
			this.towers.add(new Tower(tower));
		}
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
	
	public boolean getTowerPlayer (int i) {
		return towers.get(i).getPlayer();
	}
	
	public int getTowerPosX (int i) {
		return towers.get(i).getPositionX();
	}
	
	public int getTowerPosY (int i) {
		return towers.get(i).getPositionY();
	}
	
	public Tower getTower(boolean playerValue, Color color) {
		Tower tower = findTower(playerValue, color);
		if (tower == null) {
			return null;
		} else {
			return new Tower (tower);
		}
	}
	
	public Tower getTower(int x, int y){
		Tower tower = findTower(x, y);
		if (tower == null) {
			return null;
		} else {
			return new Tower (tower);
		}
	}
	
	public Tower findTower(boolean playerValue, Color color) {
		for (Tower tower: towers) {
			if (tower.getPlayer() == playerValue && tower.getColor() == color) {
				return tower;
			}
		}
		return null;
	}
	
	private Tower findTower(int x, int y) {
		for (Tower tower: towers){
		   	if (tower.getPositionX() == x && tower.getPositionY() == y) {
			   	return tower;
		   	}
	   	}
	   	return null;
	}
	
	public Color getLastColor () {
		return lastColor;
	}
	
	public boolean getLastPlayerValue () {
		return lastPlayerValue;
	}
	
	public Move getLastMove () {
		return new Move(previousMoves.get(previousMoves.size() - 1));
	}
   
   public void performMove(Move move) {
      Tower tower = findTower(move.startX, move.startY);
	  tower.setPositionX(move.finishX);
	  tower.setPositionY(move.finishY);
	  lastColor = tiles[move.finishX][move.finishY];
	  Move lastMove = new Move(move);
	  previousMoves.add(lastMove);
	  nextPlayer();
   }
   
   public void nextPlayer(){
	   if (lastPlayerValue){
		   lastPlayerValue = false;
	   } else {
		   lastPlayerValue = true;
	   }
   }
   
   public boolean undoLastMove() {
      // TODO implement this operation
      throw new UnsupportedOperationException("not implemented");
   }
}
