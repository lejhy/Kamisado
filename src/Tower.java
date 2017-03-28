
public class Tower extends Piece {
	
	public Tower(Value color, Value playerPosition, Position towerPosition) {
		super(color, playerPosition, towerPosition);
	}
	
	public Tower(Piece piece) {
		super(piece);
	}

	@Override
	public boolean makeMove(Position pos) {
		if (playerPosition == Value.BOTTOM) {
			if (isValidBottomStraight(pos) || isValidBottomDiagonal(pos)) {
				position.x = pos.x;
				position.y = pos.y;
				return true;
			}
		} else if (playerPosition == Value.TOP) {
			if (isValidTopStraight(pos) || isValidTopDiagonal(pos)) {
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
	
	private boolean isValidBottomStraight(Position pos) {
	   if (position.x == pos.x && position.y > pos.y) {
		   	for (int i = pos.y; i < position.y; i++) {
		   		for (Piece piece : pieces) {
		   			if (piece.getPosition().equals(pos))
		   				return false;
		   		}
		   	}
		   	return true;
	   } else {
		   return false;
	   }
   }
	
	private boolean isValidTopStraight(Position pos) {
	   if (position.x == pos.x && position.y < pos.y) {
		   	for (int i = pos.y; i > position.y; i--) {
		   		for (Piece piece : pieces) {
		   			if (piece.getPosition().equals(new Position(pos.x, i)))
		   				return false;
		   		}
	   		}
	   		return true;
	   	} else {
		   	return false;
	   	}
	}
	
	private boolean isValidBottomDiagonal(Position pos) {
		int xDiff = position.x - pos.x;
		int yDiff = position.y - pos.y;
		if (xDiff == yDiff && position.x > pos.x && position.y > pos.y){
	   		for (int i = xDiff; i > 0; i--) {
	   			for (Piece piece : pieces) {
		   			if (piece.getPosition().equals(new Position(position.x-i, position.y-i)))
		   				return false;
		   		}
		   	}
		   	return true;
		} else if (-xDiff == yDiff && position.x < pos.x && position.y > pos.y){
			for (int i = -xDiff; i > 0; i--) {
				for (Piece piece : pieces) {
		   			if (piece.getPosition().equals(new Position(position.x+i, position.y-i)))
		   				return false;
		   		}
		   	}
		   	return true;
		} else {
			return false;
		}   
	}
	
	private boolean isValidTopDiagonal(Position pos) {
		int xDiff = position.x - pos.x;
		int yDiff = position.y - pos.y;
		if (xDiff == yDiff && position.x < pos.x && position.y < pos.y){
	   		for (int i = -xDiff; i > 0; i--) {
	   			for (Piece piece : pieces) {
		   			if (piece.getPosition().equals(new Position(position.x+i, position.y+i)))
		   				return false;
		   		}
		   	}
		   	return true;
		} else if (-xDiff == yDiff && position.x > pos.x && position.y < pos.y){
			for (int i = xDiff; i > 0; i--) {
				for (Piece piece : pieces) {
		   			if (piece.getPosition().equals(new Position(position.x-i, position.y+i)))
		   				return false;
		   		}
		   	}
		   	return true;
		} else {
			return false;
		}	   
   }
}
