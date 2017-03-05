import java.util.ArrayList;
import java.util.List;
import java.util.Set;


public class Board {
	private List<Move> previousMoves;
	private Color lastColor;
	private boolean lastPlayer;
	private List<Tower> towers;
	private Color[][] tiles;
	
	public Board () {
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
		
		towers = new ArrayList<Tower>();
		for (int i = 0; i < 8; i++){
			towers.add(new Tower(tiles[i][0], false, i, 0));
		}
		for (int i = 0; i < 8; i++){
			towers.add(new Tower(tiles[i][7], true, i, 7));
		}
		
		lastPlayer = false;
		lastColor = null;
		previousMoves = new ArrayList<Move>();
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
		
		this.lastPlayer = board.lastPlayer;
		this.lastColor = board.lastColor;
		previousMoves = new ArrayList<Move>();
	}
	
	public Color[][] getTiles() {
		return tiles;
	}
	
	public List<Tower> getTowers() {
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
	
	public boolean getTowerPlayer (int i) {
		return towers.get(i).getPlayer();
	}
	
	public Tower getTower (int i) {
		return towers.get(i);
	}
	
	public Tower getTower(boolean player, Color color) {
		for (Tower tower: towers){
		   if (tower.getColor() == color && tower.getPlayer() == player) {
			   return tower;
		   }
	   }
	   return null;
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
	
	public boolean getLastPlayer () {
		return lastPlayer;
	}
	
	public Move getLastMove () {
		return new Move(previousMoves.get(previousMoves.size() - 1));
	}
   
   public void performMove(Move move) {
      Tower tower = getTower(move.startX, move.startY);
	  tower.setPositionX(move.finishX);
	  tower.setPositionY(move.finishY);
	  lastColor = tiles[move.finishX][move.finishY];
	  Move lastMove = new Move(move);
	  previousMoves.add(lastMove);
	  nextPlayer();
   }
   
   public void nextPlayer(){
	   if (lastPlayer){
		   lastPlayer = false;
	   } else {
		   lastPlayer = true;
	   }
   }
   
   public boolean undoLastMove() {
      // TODO implement this operation
      throw new UnsupportedOperationException("not implemented");
   }
}
