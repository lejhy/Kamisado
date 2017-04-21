package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public abstract class Piece implements Serializable {

	private static final long serialVersionUID = -2217570651965017291L;
	protected int movementLimit = Integer.MAX_VALUE;
	protected Value playerPosition;
	protected Value color;
	protected Position position;
	protected List<Piece> pieces;

	public abstract boolean sumoPush(Position pos);
	public abstract List<Move> getSumoPushUndoMoves();
	public abstract boolean isValidTopSumoPush(Position pos);
	public abstract boolean isValidBottomSumoPush(Position pos);
	
	public abstract void promote();
	public abstract Piece clone();
	public abstract Piece clone(List<Piece> pieces);
	public abstract int getPoints();

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

	public Piece(Value color, Value playerPosition, Position towerPosition, List<Piece> pieces) {
		this.color = color;
		this.playerPosition = playerPosition;
		this.position = towerPosition;
		this.pieces = pieces;
	}

	public Piece(Piece tower, List<Piece> pieces) {
		this.color = tower.getColor();
		this.playerPosition = tower.getPlayerPosition();
		this.position = tower.getPosition();
		this.pieces = pieces;
	}

	public Piece(Piece tower) {
		this.color = tower.getColor();
		this.playerPosition = tower.getPlayerPosition();
		this.position = tower.getPosition();
		this.pieces = tower.getPieces();
	}
	
	public boolean makeMove(Position pos) {
		if (pos.equals(position) && isDeadlocked()) {
			return true;
		} else {
			Move move = new Move(this.position, pos);
			if (playerPosition == Value.BOTTOM) {
				if (isValidBottom(move)) {
					position.x = pos.x;
					position.y = pos.y;
					if (position.y == 0)
						promote();
					return true;
				}
			} else if (playerPosition == Value.TOP) {
				if (isValidTop(move)) {
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
	
	public boolean isDeadlocked() {
		if (playerPosition == Value.BOTTOM) {
			return isDeadLockedAtTop();
		} else {
			return isDeadLockedAtBottom();
		}
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
		if (move.start.x != move.finish.x || move.start.y != move.finish.y) {
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
		List<Move> moves = new ArrayList<Move>();
		moves.addAll(getValidBottomStraightMoves());
		moves.addAll(getValidBottomDiagonalMoves());
		if (isValidBottomSumoPush(new Position(position.x, position.y - 1))) {
			moves.add(new Move(position.x, position.y, position.x, position.y - 1));
		}
		if (moves.isEmpty() && isDeadLockedAtTop()) {
			moves.add(new Move(position, position));
		}
		return moves;
	}

	public List<Move> getValidBottomStraightMoves() {
		List<Move> moves = new ArrayList<Move>();
		int i = 1;
		Move move = new Move(position.x, position.y, position.x, position.y - i);
		while (isValidMove(move)) {
			moves.add(move);
			i++;
			move = new Move(position.x, position.y, position.x, position.y - i);
		}
		return moves;
	}

	public List<Move> getValidBottomDiagonalMoves() {
		List<Move> moves = new ArrayList<Move>();
		int i = 1;
		Move move = new Move(position.x, position.y, position.x + i, position.y - i);
		while (isValidMove(move)) {
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

	public List<Move> getValidTopMoves() {
		List<Move> moves = new ArrayList<Move>();
		moves.addAll(getValidTopStraightMoves());
		moves.addAll(getValidTopDiagonalMoves());
		if (isValidTopSumoPush(new Position(position.x, position.y + 1))) {
			moves.add(new Move(position.x, position.y, position.x, position.y + 1));
		}
		if (moves.isEmpty() && isDeadLockedAtBottom()) {
			moves.add(new Move(position, position));
		}
		return moves;
	}

	public List<Move> getValidTopStraightMoves() {
		List<Move> moves = new ArrayList<Move>();
		int i = 1;
		Move move = new Move(position.x, position.y, position.x, position.y + i);
		while (isValidMove(move)) {
			moves.add(move);
			i++;
			move = new Move(position.x, position.y, position.x, position.y + i);
		}
		return moves;
	}

	public List<Move> getValidTopDiagonalMoves() {
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
		if ((move.start.y - move.finish.y) <= movementLimit && (isValidBottomStraight(move) || isValidBottomDiagonal(move))) {
			return true;
		} else {
			return false;
		}
	}

	public boolean isValidTop(Move move) {
		if ((move.finish.y - move.start.y) <= movementLimit && (isValidTopStraight(move) || isValidTopDiagonal(move))) {
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
		if (xDiff == yDiff && move.start.x > move.finish.x && move.start.y > move.finish.y) {
			for (int i = xDiff; i > 0; i--) {
				for (Piece piece : pieces) {
					if (piece.getPosition().equals(new Position(move.start.x - i, move.start.y - i)))
						return false;
				}
			}
			return true;
		} else if (-xDiff == yDiff && move.start.x < move.finish.x && move.start.y > move.finish.y) {
			for (int i = -xDiff; i > 0; i--) {
				for (Piece piece : pieces) {
					if (piece.getPosition().equals(new Position(move.start.x + i, move.start.y - i)))
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
		if (xDiff == yDiff && move.start.x < move.finish.x && move.start.y < move.finish.y) {
			for (int i = -xDiff; i > 0; i--) {
				for (Piece piece : pieces) {
					if (piece.getPosition().equals(new Position(move.start.x + i, move.start.y + i)))
						return false;
				}
			}
			return true;
		} else if (-xDiff == yDiff && move.start.x > move.finish.x && move.start.y < move.finish.y) {
			for (int i = xDiff; i > 0; i--) {
				for (Piece piece : pieces) {
					if (piece.getPosition().equals(new Position(move.start.x - i, move.start.y + i)))
						return false;
				}
			}
			return true;
		} else {
			return false;
		}
	}
}
