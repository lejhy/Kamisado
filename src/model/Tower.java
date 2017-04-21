package model;

import java.util.ArrayList;
import java.util.List;

public class Tower extends Piece {

	private static final long serialVersionUID = -3839484086936310615L;

	public Tower(Value color, Value playerPosition, Position towerPosition, List<Piece> pieces) {
		super(color, playerPosition, towerPosition, pieces);
	}

	public Tower(Piece piece, List<Piece> pieces) {
		super(piece, pieces);
	}

	public Tower(Piece piece) {
		super(piece);
	}

	@Override
	public Piece clone() {
		return new Tower(this);
	}

	@Override
	public Piece clone(List<Piece> pieces) {
		return new Tower(this, pieces);
	}

	@Override
	public void promote() {
		pieces.remove(this);
		pieces.add(new Sumo(this));
	}

	public boolean sumoPush(Position pos) {
		return false;
	}
	
	public boolean isValidBottomSumoPush(Position pos) {
		return false;
	}
	
	public boolean isValidTopSumoPush(Position pos) {
		return false;
	}

	public List<Move> getSumoPushUndoMoves() {
		return new ArrayList<Move>();
	}

	public int getPoints() {
		return 0;
	}
}
