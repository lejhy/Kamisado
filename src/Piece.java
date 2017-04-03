import java.io.Serializable;
import java.util.List;

public abstract class Piece implements Serializable{
	
	protected Value playerPosition;
	protected Value color;
	protected Position position;
	protected List<Piece> pieces;
	
	public abstract boolean makeMove(Position pos);
	public abstract Piece clone();
	   
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
   
	public Piece (Value color, Value playerPosition, Position towerPosition) {
		this.color = color;
		this.playerPosition = playerPosition;
		this.position = towerPosition;
	}
	
	public Piece(Piece tower){
		this.color = tower.getColor();
		this.playerPosition = tower.getPlayerPosition();
		this.position = tower.getPosition();
		this.pieces = tower.getPieces();
	}
}