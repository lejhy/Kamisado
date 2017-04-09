import java.util.ArrayList;
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
				if (isValidBottomStraight(move) || isValidBottomDiagonal(move)) {
					position.x = pos.x;
					position.y = pos.y;
					return true;
				}
			} else if (playerPosition == Value.TOP) {
				if (isValidTopStraight(move) || isValidTopDiagonal(move)) {
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
			return isDeadLockedAtTop();
		} else {
			return isDeadLockedAtBottom();
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
	
	public boolean isDeadLockedAtTop() {
		boolean topObstacle = false;
		Position topObstaclePos = new Position(position.x, position.y - 1);
		
		boolean topRightObstacle = false;
		Position topRightObstaclePos = new Position(position.x + 1, position.y - 1);
		
		boolean topLeftObstacle = false;
		Position topLeftObstaclePos = new Position(position.x - 1, position.y - 1);
		
		if (topRightObstaclePos.x > 7)
			topRightObstacle = true;
		
		if (topLeftObstaclePos.x < 0)
			topLeftObstacle = true;
		
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
	
	public boolean isDeadLockedAtBottom() {
		boolean bottomObstacle = false;
		Position bottomObstaclePos = new Position(position.x, position.y + 1);
		
		boolean bottomRightObstacle = false;
		Position bottomRightObstaclePos = new Position(position.x + 1, position.y + 1);
		
		boolean bottomLeftObstacle = false;
		Position bottomLeftObstaclePos = new Position(position.x - 1, position.y + 1);
		
		if (bottomRightObstaclePos.x > 7)
			bottomRightObstacle = true;
		
		if (bottomLeftObstaclePos.x < 0)
			bottomLeftObstacle = true;
		
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
	
	public boolean isValidMove(Move move) {
	   if (move.start.x != move.finish.x || move.start.y != move.finish.y){
		   if (move.finish.x >= 0 && move.finish.x < 8 && move.finish.y >= 0 && move.finish.y < 8) {
			   if (getPlayerPosition() == Value.BOTTOM) {
				   return isValidBottom(move);
			   } else {
				   return isValidTop(move);
			   }
		   }
	   }
	   return false;
	}
	
	public List<Move> getValidMoves() {
		if (playerPosition == Value.BOTTOM) {
			return getValidBottomMoves();
		} else {
			return getValidTopMoves();
		}
	}
	
	public List<Move> getValidBottomMoves() {
		List <Move> moves = new ArrayList<Move>();
		moves.addAll(getValidWhiteStraightMoves());
		moves.addAll(getValidWhiteDiagonalMoves());
		return moves;
	}
	
	public List<Move> getValidWhiteStraightMoves() {
		List <Move> moves = new ArrayList<Move>();
		int i = 1;
		Move move = new Move(position.x, position.y, position.x, position.y - i);
		while (isValidMove(move)) {
			moves.add(move);
			i++;
			move = new Move(position.x, position.y, position.x, position.y - i);
		}
		return moves;
	}
	
	public List<Move> getValidWhiteDiagonalMoves() {
		List <Move> moves = new ArrayList<Move>();
		int i = 1;
		Move move = new Move(position.x, position.y, position.x + i, position.y - i);
		while (isValidMove( move)) {
			moves.add(move);
			i++;
			move = new Move(position.x, position.y, position.x + i, position.y - i);
		}
		i = 1;
		move = new Move(position.x, position.y, position.x - i, position.y - i);
		while (isValidMove(move)) {
			moves.add(move);
			i++;
			move = new Move(position.x, position.y, position.x - i, position.y - i);
		}
		return moves;
	}
	
	public List<Move> getValidTopMoves () {
		List <Move> moves = new ArrayList<Move>();
		moves.addAll(getValidTopStraightMoves());
		moves.addAll(getValidTopDiagonalMoves());
		return moves;
	}
	
	public List <Move> getValidTopStraightMoves() {
		List <Move> moves = new ArrayList<Move>();
		int i = 1;
		Move move = new Move(position.x, position.y, position.x, position.y + i);
		while (isValidMove(move)) {
			moves.add(move);
			i++;
			move = new Move(position.x, position.y, position.x, position.y + i);
		}
		return moves;
	}
	
	public List <Move> getValidTopDiagonalMoves() {
		List<Move> moves = new ArrayList<Move>();
		int i = 1;
		Move move = new Move(position.x, position.y, position.x + i, position.y + i);
		while (isValidMove(move)) {
			moves.add(move);
			i++;
			move = new Move(position.x, position.y, position.x + i, position.y + i);
		}
		i = 1;
		move = new Move(position.x, position.y, position.x - i, position.y + i);
		while (isValidMove(move)) {
			moves.add(move);
			i++;
			move = new Move(position.x, position.y, position.x - i, position.y + i);
		}
		return moves;
	}
	
	public boolean isValidBottom(Move move) {
	   if (isValidBottomStraight(move) || isValidBottomDiagonal(move)) {
		   	return true;
	   } else {
		   return false;
	   }
	}
	
	public boolean isValidTop(Move move) {
	   if (isValidTopStraight(move) || isValidTopDiagonal(move)) {
		   	return true;
	   } else {
		   return false;
	   }
	}
	
	public boolean isValidBottomStraight(Move move) {
		   if (move.start.x == move.finish.x && move.start.y > move.finish.y) {
			   	for (int i = move.finish.y; i < move.start.y; i++) {
			   		for (Piece piece : pieces) {
			   			if (piece.getPosition().equals(new Position(move.finish.x, i)))
			   				return false;
			   		}
			   	}
			   	return true;
		   } else {
			   return false;
		   }
	   }
	
	public boolean isValidTopStraight(Move move) {
		if (move.start.x == move.finish.x && move.start.y < move.finish.y) {
		   	for (int i = move.finish.y; i > move.start.y; i--) {
		   		for (Piece piece : pieces) {
		   			if (piece.getPosition().equals(new Position(move.finish.x, i)))
		   				return false;
		   		}
	   		}
	   		return true;
	   	} else {
		   	return false;
	   	}
	}
		
	public boolean isValidBottomDiagonal(Move move) {
		int xDiff = move.start.x - move.finish.x;
		int yDiff = move.start.y - move.finish.y;
		if (xDiff == yDiff && move.start.x > move.finish.x && move.start.y > move.finish.y){
	   		for (int i = xDiff; i > 0; i--) {
	   			for (Piece piece : pieces) {
		   			if (piece.getPosition().equals(new Position(move.start.x-i, move.start.y-i)))
		   				return false;
		   		}
		   	}
		   	return true;
		} else if (-xDiff == yDiff && move.start.x < move.finish.x && move.start.y > move.finish.y){
			for (int i = -xDiff; i > 0; i--) {
				for (Piece piece : pieces) {
		   			if (piece.getPosition().equals(new Position(move.start.x+i, move.start.y-i)))
		   				return false;
		   		}
		   	}
		   	return true;
		} else {
			return false;
		}   
	}
	
	public boolean isValidTopDiagonal(Move move) {
		int xDiff = move.start.x - move.finish.x;
		int yDiff = move.start.y - move.finish.y;
		if (xDiff == yDiff && move.start.x < move.finish.x && move.start.y < move.finish.y){
	   		for (int i = -xDiff; i > 0; i--) {
	   			for (Piece piece : pieces) {
		   			if (piece.getPosition().equals(new Position(move.start.x+i, move.start.y+i)))
		   				return false;
		   		}
		   	}
		   	return true;
		} else if (-xDiff == yDiff && move.start.x > move.finish.x && move.start.y < move.finish.y){
			for (int i = xDiff; i > 0; i--) {
				for (Piece piece : pieces) {
		   			if (piece.getPosition().equals(new Position(move.start.x-i, move.start.y+i)))
		   				return false;
		   		}
		   	}
		   	return true;
		} else {
			return false;
		}	
	}
}
