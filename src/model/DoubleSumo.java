package model;

import java.util.ArrayList;
import java.util.List;

public class DoubleSumo extends Piece {

	private static final long serialVersionUID = -4012374944609469355L;
	List<Move> lastSumoPushMoves = null;

	public DoubleSumo(Value color, Value playerPosition, Position towerPosition, List<Piece> pieces) {
		super(color, playerPosition, towerPosition, pieces);
		movementLimit = 3;
	}

	public DoubleSumo(Piece piece, List<Piece> pieces) {
		super(piece, pieces);
		movementLimit = 3;
	}

	public DoubleSumo(Piece piece) {
		super(piece);
		movementLimit = 3;
	}

	@Override
	public Piece clone() {
		return new DoubleSumo(this);
	}

	@Override
	public Piece clone(List<Piece> pieces) {
		return new DoubleSumo(this, pieces);
	}

	@Override
	public void promote() {
		pieces.remove(this);
		pieces.add(new TripleSumo(this));
	}

	@Override
	public boolean sumoPush(Position pos) {
		if (playerPosition == Value.BOTTOM) {
			if (isValidBottomSumoPush(pos)) {
				lastSumoPushMoves = new ArrayList<Move>();
				Move move = null;
				if (pieceExists(pos.x, pos.y - 1)) {
					move = new Move(pos.x, pos.y - 1, pos.x, pos.y - 2);
					moveOtherPiece(move);
					lastSumoPushMoves.add(move);
				}
				move = new Move(pos.x, pos.y, pos.x, pos.y - 1);
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
					move = new Move(pos.x, pos.y + 1, pos.x, pos.y + 2);
					moveOtherPiece(move);
					lastSumoPushMoves.add(move);
				}
				move = new Move(pos.x, pos.y, pos.x, pos.y + 1);
				moveOtherPiece(move);
				lastSumoPushMoves.add(move);
				position.x = pos.x;
				position.y = pos.y;
				return true;
			}
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

	private void moveOtherPiece(Move move) {
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
				if (pieceToPush != null && (pieceToPush instanceof Tower || pieceToPush instanceof Sumo)
						&& pieceToPush.getPlayerPosition() != this.getPlayerPosition()) {
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
					} else if (++i < 2) {
						continue;
					}
				}
				return false;
			}
		}
		return false;
	}

	public boolean isValidTopSumoPush(Position pos) {
		if (position.x == pos.x && position.y == (pos.y - 1) && pos.y > 0) {
			int i = 0;
			while (true) {
				Piece pieceToPush = null;
				for (Piece piece : pieces) {
					if (piece.getPosition().equals(new Position(pos.x, pos.y + i))) {
						pieceToPush = piece;
						break;
					}
				}
				if (pieceToPush != null && (pieceToPush instanceof Tower || pieceToPush instanceof Sumo
						&& pieceToPush.getPlayerPosition() != this.getPlayerPosition())) {
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
					} else if (++i < 2) {
						continue;
					}
				}
				return false;
			}
		}
		return false;
	}

	@Override
	public int getPoints() {
		return 3;
	}
}
