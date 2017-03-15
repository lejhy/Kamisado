import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Board implements Serializable{
	private List<Move> previousMoves;
	private Value lastColor;
	private boolean lastPlayerValue;
	private List<Tower> towers;
	private Value[][] tiles;
	
	public List<Tower> getTowers() {
		return towers;
	}
	
	public Value[][] getTiles() {
		return tiles;
	}
	
	public Board () {
		initTiles();
		initTowers();
		initPreviousMoves();
		lastPlayerValue = false;
		lastColor = null;
	}
	
	public Board (Board board) {
		initTiles(board.getTiles());
		initTowers(board.getTowers());
		initPreviousMoves(board.getPreviousMoves());
		this.lastPlayerValue = board.lastPlayerValue;
		this.lastColor = board.lastColor;
	}
	
	private void initPreviousMoves() {
		this.previousMoves = new ArrayList<Move>();
	}
	
	private void initPreviousMoves(List<Move> moves) {
		this.previousMoves = new ArrayList<Move>();
		for (Move move: moves) {
			this.previousMoves.add(move);
		}
	}
	
	private void initTiles() {
		Value[][] tiles = {
				{Value.ORANGE,	Value.RED, 		Value.GREEN, 	Value.PINK,		Value.YELLOW,	Value.BLUE, 	Value.PURPLE,	Value.BROWN},
				{Value.BLUE,	Value.ORANGE,	Value.PINK, 	Value.PURPLE, 	Value.RED, 		Value.YELLOW, 	Value.BROWN, 	Value.GREEN},
				{Value.PURPLE,	Value.PINK,		Value.ORANGE, 	Value.BLUE,		Value.GREEN, 	Value.BROWN, 	Value.YELLOW, 	Value.RED},
				{Value.PINK,	Value.GREEN,	Value.RED, 		Value.ORANGE,	Value.BROWN,	Value.PURPLE, 	Value.BLUE, 	Value.YELLOW},
				{Value.YELLOW, 	Value.BLUE,		Value.PURPLE, 	Value.BROWN,	Value.ORANGE,	Value.RED,		Value.GREEN,	Value.PINK},
				{Value.RED, 	Value.YELLOW,	Value.BROWN, 	Value.GREEN,	Value.BLUE, 	Value.ORANGE,	Value.PINK, 	Value.PURPLE},
				{Value.GREEN, 	Value.BROWN,	Value.YELLOW,	Value.RED, 		Value.PURPLE,	Value.PINK, 	Value.ORANGE,	Value.BLUE},
				{Value.BROWN,	Value.PURPLE,	Value.BLUE,		Value.YELLOW, 	Value.PINK,		Value.GREEN,	Value.RED,		Value.ORANGE}					 
		};
		this.tiles = tiles;
	}
	
	private void initTiles(Value[][] tiles) {
		this.tiles = new Value[8][8];
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
	
	public Value getTile(int x, int y) {
		return tiles[x][y];
	}
	
	public int getNumOfTowers () {
		return towers.size();
	}
	
	public Value getTowerColor (int i) {
		return towers.get(i).getColor();
	}
	
	public boolean getTowerPlayer (int i) {
		return towers.get(i).getPlayerValue();
	}
	
	public int getTowerPosX (int i) {
		return towers.get(i).getPositionX();
	}
	
	public int getTowerPosY (int i) {
		return towers.get(i).getPositionY();
	}
	
	public Tower getTower(boolean playerValue, Value color) {
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
	
	public Tower findTower(boolean playerValue, Value color) {
		for (Tower tower: towers) {
			if (tower.getPlayerValue() == playerValue && tower.getColor() == color) {
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
	
	public Value getLastColor () {
		return lastColor;
	}
	
	public boolean getLastPlayerValue () {
		return lastPlayerValue;
	}
	
	public Move getLastMove () {
		if (previousMoves.isEmpty())
			return null;
		else
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
   
   public List<Move> getPreviousMoves() {
	   return previousMoves;
   }
   
   public void undoLastMove() {
	   Move move = getLastMove();
	   previousMoves.remove(previousMoves.size()-1);	   
	   Tower tower = findTower(move.finishX, move.finishY);
	   tower.setPositionX(move.startX);
	   tower.setPositionY(move.startY);
	   
	   move = getLastMove();
	   previousMoves.remove(previousMoves.size()-1);
	   tower = findTower(move.finishX, move.finishY);
	   tower.setPositionX(move.startX);
	   tower.setPositionY(move.startY);
	   
	   lastColor = tower.getColor();
   }
}
