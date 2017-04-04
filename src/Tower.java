import java.util.List;

public class Tower extends Piece {
	
	public Tower(Value color, Value playerPosition, Position towerPosition, List<Piece> pieces) {
		super(color, playerPosition, towerPosition, pieces);
	}
	
	public Tower(Piece piece,List<Piece> pieces) {
		super(piece, pieces);
	} 
	
	public Tower(Piece piece) {
		super(piece);
	}

	@Override
	public boolean makeMove(Position pos) {
		if (pos.equals(position) && isDeadlocked()) {
			return true;
		} else {
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
	}
	
	@Override
	public boolean isDeadlocked() {
		if (playerPosition == Value.BOTTOM) {
			return isDeadLockedAtTop(pieces, position);
		} else {
			return isDeadLockedAtBottom(pieces, position);
		}
	}
	
	@Override
	public Piece clone() {
		return new Tower(this);
	}
	
	@Override
	public Piece clone(List<Piece> pieces) {
		return new Tower(this, pieces);
	}
	
	public boolean isDeadLockedAtTop(List<Piece> pieces, Position pos) {
		boolean topObstacle = false;
		Position topObstaclePos = new Position(pos.x, pos.y - 1);
		
		boolean topRightObstacle = false;
		Position topRightObstaclePos = new Position(pos.x + 1, pos.y - 1);
		
		boolean topLeftObstacle = false;
		Position topLeftObstaclePos = new Position(pos.x - 1, pos.y - 1);
		
		if (topRightObstaclePos.x > 7)
			topRightObstacle = true;
		
		if (topLeftObstaclePos.x < 0)
			topRightObstacle = true;
		
		for (Piece piece : pieces) {
			if (piece.getPosition().equals(topObstaclePos))
				topObstacle = true;
			if (piece.getPosition().equals(topRightObstaclePos))
				topRightObstacle = true;
			if (piece.getPosition().equals(topLeftObstaclePos))
				topLeftObstacle = true;
		}
		
		if (topObstacle && topRightObstacle && topLeftObstacle) {
			return true;
		}
		return false;
	}
	
	public boolean isDeadLockedAtBottom(List<Piece> pieces, Position pos) {
		boolean bottomObstacle = false;
		Position bottomObstaclePos = new Position(pos.x, pos.y + 1);
		
		boolean bottomRightObstacle = false;
		Position bottomRightObstaclePos = new Position(pos.x - 1, pos.y + 1);
		
		boolean bottomLeftObstacle = false;
		Position bottomLeftObstaclePos = new Position(pos.x + 1, pos.y + 1);
		
		if (bottomRightObstaclePos.x > 7)
			bottomRightObstacle = true;
		
		if (bottomLeftObstaclePos.x > 0)
			bottomRightObstacle = true;
		
		for (Piece piece : pieces) {
			if (piece.getPosition().equals(bottomObstaclePos))
				bottomObstacle = true;
			if (piece.getPosition().equals(bottomRightObstaclePos))
				bottomRightObstacle = true;
			if (piece.getPosition().equals(bottomLeftObstaclePos))
				bottomLeftObstacle = true;
		}
		
		if (bottomObstacle && bottomRightObstacle && bottomLeftObstacle) {
			return true;
		}
		return false;
	}
}
