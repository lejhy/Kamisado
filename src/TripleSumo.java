import java.util.ArrayList;
import java.util.List;

public class TripleSumo extends Piece {
	
	List<Move> lastSumoPushMoves = null;

	public TripleSumo(Value color, Value playerPosition, Position towerPosition, List<Piece> pieces) {
		super(color, playerPosition, towerPosition, pieces);
	}
	
	public TripleSumo(Piece piece,List<Piece> pieces) {
		super(piece, pieces);
	} 
	
	public TripleSumo(Piece piece) {
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
					if (position.y == 0)
						promote();
					return true;
				}
			} else if (playerPosition == Value.TOP) {
				if (isValidTopStraight(move) || isValidTopDiagonal(move)) {
					position.x = pos.x;
					position.y = pos.y;
					if (position.y == 7)
						promote();
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
		return new TripleSumo(this);
	}
	
	@Override
	public Piece clone(List<Piece> pieces) {
		return new TripleSumo(this, pieces);
	}
	
	@Override
	public void promote() {
		pieces.remove(this);
		pieces.add(new QuadrupleSumo(this));
	}
	
	@Override
	public boolean sumoPush(Position pos) {
		if (playerPosition == Value.BOTTOM) {
			if (isValidBottomSumoPush(pos)) {
				lastSumoPushMoves = new ArrayList<Move>();
				Move move = null;
				if (pieceExists(pos.x, pos.y - 1)) {
					if (pieceExists(pos.x, pos.y - 2)) {
						move = new Move(pos.x, pos.y - 2, pos.x, pos.y - 3);
						moveOtherPiece(move);
						lastSumoPushMoves.add(move);
					}
					move = new Move (pos.x, pos.y - 1, pos.x, pos.y - 2);
					moveOtherPiece(move);
					lastSumoPushMoves.add(move);
				}
				move = new Move (pos.x, pos.y, pos.x, pos.y - 1);
				moveOtherPiece(move);
				lastSumoPushMoves.add(move);
				position.x = pos.x;
				position.y = pos.y;
				return true;
			}
		} else if (playerPosition == Value.TOP) {
			if (isValidTopSumoPush(pos)) {
				lastSumoPushMoves = new ArrayList<Move>();
				Move move = null;
				if (pieceExists(pos.x, pos.y + 1)) {
					if (pieceExists(pos.x, pos.y + 2)) {
						move = new Move(pos.x, pos.y + 2, pos.x, pos.y + 3);
						moveOtherPiece(move);
						lastSumoPushMoves.add(move);
					}
					move = new Move (pos.x, pos.y + 1, pos.x, pos.y + 2);
					moveOtherPiece(move);
					lastSumoPushMoves.add(move);
				}
				move = new Move (pos.x, pos.y, pos.x, pos.y + 1);
				moveOtherPiece(move);
				lastSumoPushMoves.add(move);
				position.x = pos.x;
				position.y = pos.y;
				return true;
			}
		}
		return false;
	}
	
	private boolean pieceExists(Position pos) {
		for (Piece piece : pieces) {
			if (piece.getPosition().equals(pos))
				return true;
		}
		return false;
	}
	
	private boolean pieceExists(int posX, int posY) {
		Position pos = new Position(posX, posY);
		for (Piece piece : pieces) {
			if (piece.getPosition().equals(pos))
				return true;
		}
		return false;
	}
	
	private void moveOtherPiece (Move move) {
		for (Piece piece : pieces) {
			if (piece.getPosition().equals(move.start)) {
				piece.setPosition(move.finish);
				break;
			}
		}
	}

	@Override
	public List<Move> getSumoPushUndoMoves() {
		return lastSumoPushMoves;
	}
	
	public boolean isValidBottomSumoPush(Position pos) {
		if (position.x == pos.x && position.y == (pos.y + 1) && pos.y > 0) {
			int i = 0;
			while (true) {
				Piece pieceToPush = null;
				for (Piece piece : pieces) {
					if (piece.getPosition().equals(new Position(pos.x, pos.y - i))) {
						pieceToPush = piece;
						break;
					}
				}
				if (pieceToPush != null && (pieceToPush instanceof Tower || pieceToPush instanceof Sumo || pieceToPush instanceof DoubleSumo) && pieceToPush.getPlayerPosition() != this.getPlayerPosition()) {
					Position spaceBehindTowerToPush = new Position(pos.x, pos.y - i - 1);
					pieceToPush = null;
					for (Piece piece : pieces) {
						if (piece.getPosition().equals(spaceBehindTowerToPush)) {
							pieceToPush = piece;
							break;
						}
					}
					if (pieceToPush == null && spaceBehindTowerToPush.y >= 0) {
						return true;
					} else if (++i < 3){
						continue;
					}
				}
				return false;
			}
		}
		return false;
	}
	
	public boolean isValidTopSumoPush(Position pos) {
		if (position.x == pos.x && position.y == (pos.y - 1) && pos.y < 7) {
			int i = 0;
			while (true) {
				Piece pieceToPush = null;
				for (Piece piece : pieces) {
					if (piece.getPosition().equals(new Position(pos.x, pos.y + i))) {
						pieceToPush = piece;
						break;
					}
				}
				if (pieceToPush != null && (pieceToPush instanceof Tower || pieceToPush instanceof Sumo || pieceToPush instanceof DoubleSumo) && pieceToPush.getPlayerPosition() != this.getPlayerPosition()) {
					Position spaceBehindTowerToPush = new Position(pos.x, pos.y + i + 1);
					pieceToPush = null;
					for (Piece piece : pieces) {
						if (piece.getPosition().equals(spaceBehindTowerToPush)) {
							pieceToPush = piece;
							break;
						}
					}
					if (pieceToPush == null && spaceBehindTowerToPush.y <= 7) {
						return true;
					} else if (++i < 3){
						continue;
					}
				}
				return false;
			}
		}
		return false;
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
			if (piece.getPosition().equals(topObstaclePos) && !isValidBottomSumoPush(topObstaclePos))
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
			if (piece.getPosition().equals(bottomObstaclePos) && !isValidTopSumoPush(bottomObstaclePos))
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
		if (isValidBottomSumoPush(new Position(position.x, position.y - 1))) {
			moves.add(new Move(position.x, position.y, position.x, position.y - 1));
		}
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
		if (isValidTopSumoPush(new Position(position.x, position.y + 1))) {
			moves.add(new Move(position.x, position.y, position.x, position.y + 1));
		}
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
	   if ((move.start.y - move.finish.y) <= 1 && (isValidBottomStraight(move) || isValidBottomDiagonal(move))) {
		   	return true;
	   } else {
		   return false;
	   }
	}
	
	public boolean isValidTop(Move move) {
	   if ((move.finish.y - move.start.y) <= 1 && (isValidTopStraight(move) || isValidTopDiagonal(move))) {
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
