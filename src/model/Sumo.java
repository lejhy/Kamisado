package model;

import java.util.ArrayList;
import java.util.List;

public class Sumo extends Piece {

	private static final long serialVersionUID = -6417294214179385794L;

	public Sumo(Value color, Value playerPosition, Position towerPosition, List<Piece> pieces) {
		super(color, playerPosition, towerPosition, pieces);
		movementLimit = 5;
	}

	public Sumo(Piece piece, List<Piece> pieces) {
		super(piece, pieces);
		movementLimit = 5;
	}

	public Sumo(Piece piece) {
		super(piece);
		movementLimit = 5;
	}

	@Override
	public Piece clone() {
		return new Sumo(this);
	}

	@Override
	public Piece clone(List<Piece> pieces) {
		return new Sumo(this, pieces);
	}

	@Override
	public void promote() {
		pieces.remove(this);
		pieces.add(new DoubleSumo(this));
	}

	@Override
	public boolean sumoPush(Position pos) {
		if (playerPosition == Value.BOTTOM) {
			if (isValidBottomSumoPush(pos)) {
				for (Piece piece : pieces) {
					if (piece.getPosition().equals(pos)) {
						piece.setPosition(new Position(pos.x, pos.y - 1));
						break;
					}
				}
				position.x = pos.x;
				position.y = pos.y;
				return true;
			}
		} else if (playerPosition == Value.TOP) {
			if (isValidTopSumoPush(pos)) {
				for (Piece piece : pieces) {
					if (piece.getPosition().equals(pos)) {
						piece.setPosition(new Position(pos.x, pos.y + 1));
						break;
					}
				}
				position.x = pos.x;
				position.y = pos.y;
				return true;
			}
		}
		return false;
	}

	@Override
	public List<Move> getSumoPushUndoMoves() {
		List<Move> moves = new ArrayList<Move>();
		if (playerPosition == Value.BOTTOM) {
			moves.add(new Move(position.x, position.y, position.x, position.y - 1));
		} else {
			moves.add(new Move(position.x, position.y, position.x, position.y + 1));
		}
		return moves;
	}
	
	@Override
	public boolean isValidBottomSumoPush(Position pos) {
		if (position.x == pos.x && position.y == (pos.y + 1) && pos.y > 0) {
			Piece pieceToPush = null;
			for (Piece piece : pieces) {
				if (piece.getPosition().equals(pos)) {
					pieceToPush = piece;
					break;
				}
			}
			if (pieceToPush != null) {
				if (pieceToPush instanceof Tower && pieceToPush.getPlayerPosition() != this.getPlayerPosition()) {
					Position spaceBehindTowerToPush = new Position(pos.x, pos.y - 1);
					for (Piece piece : pieces) {
						if (piece.getPosition().equals(spaceBehindTowerToPush)) {
							return false;
						}
					}
					return true;
				}
			}
		}
		return false;
	}

	@Override
	public boolean isValidTopSumoPush(Position pos) {
		if (position.x == pos.x && position.y == (pos.y - 1) && pos.y < 7) {
			Piece pieceToPush = null;
			for (Piece piece : pieces) {
				if (piece.getPosition().equals(pos)) {
					pieceToPush = piece;
					break;
				}
			}
			if (pieceToPush != null) {
				if (pieceToPush instanceof Tower && pieceToPush.getPlayerPosition() != this.getPlayerPosition()) {
					Position spaceBehindTowerToPush = new Position(pos.x, pos.y + 1);
					for (Piece piece : pieces) {
						if (piece.getPosition().equals(spaceBehindTowerToPush)) {
							return false;
						}
					}
					return true;
				}
			}
		}
		return false;
	}

	public int getPoints() {
		return 1;
	}
}
