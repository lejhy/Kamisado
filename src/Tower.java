
public class Tower extends Piece {
	
	public Tower(Value color, Value playerPosition, Position towerPosition) {
		super(color, playerPosition, towerPosition);
	}
	
	public Tower(Piece piece) {
		super(piece);
	}

	@Override
	public boolean makeMove(Position pos) {
		Move move = new Move(this.position, pos);
		if (playerPosition == Value.BOTTOM) {
			if (GameLogic.isValidBottomStraight(pieces, move) || GameLogic.isValidBottomDiagonal(pieces, move)) {
				position.x = pos.x;
				position.y = pos.y;
				return true;
			}
		} else if (playerPosition == Value.TOP) {
			if (GameLogic.isValidTopStraight(pieces, move) || GameLogic.isValidTopDiagonal(pieces, move)) {
				position.x = pos.x;
				position.y = pos.y;
				return true;
			}
		}
		return false;
	}
	
	@Override
	public Piece clone() {
		return new Tower(this);
	}
}
