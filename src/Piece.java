import java.io.Serializable;
import java.util.List;

public abstract class Piece implements Serializable{
	
	protected Value playerPosition;
	protected Value color;
	protected Position position;
	protected List<Piece> pieces;
	
	public abstract boolean makeMove(Position pos);
	public abstract boolean sumoPush(Position pos);
	public abstract List<Move> getSumoPushMoves();
	public abstract boolean isDeadlocked();
	public abstract boolean isValidMove(Move move);
	public abstract List<Move> getValidMoves();
	public abstract void promote();
	
	public abstract Piece clone();
	public abstract Piece clone(List<Piece> pieces);
	   
	public void setPosition(Position pos) {
		this.position = pos;
	}

	public Value getColor() {
		return color;
	}
	
	public Value getPlayerPosition() {
		return playerPosition;	
	}
   
	public Position getPosition() {
		return new Position(position);
	}	
   
	public List<Piece> getPieces() {
		return pieces;
	}
   
	public Piece (Value color, Value playerPosition, Position towerPosition, List<Piece> pieces) {
		this.color = color;
		this.playerPosition = playerPosition;
		this.position = towerPosition;
		this.pieces = pieces;
	}
	
	public Piece(Piece tower, List<Piece> pieces){
		this.color = tower.getColor();
		this.playerPosition = tower.getPlayerPosition();
		this.position = tower.getPosition();
		this.pieces = pieces;
	}
	
	public Piece(Piece tower){
		this.color = tower.getColor();
		this.playerPosition = tower.getPlayerPosition();
		this.position = tower.getPosition();
		this.pieces = tower.getPieces();
	}
}
