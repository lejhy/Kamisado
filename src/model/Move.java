package model;

import java.io.Serializable;

public class Move implements Serializable {

	private static final long serialVersionUID = 5933142240842935923L;
	public Position start;
	public Position finish;

	public Move(int startX, int startY, int finishX, int finishY) {
		this.start = new Position(startX, startY);
		this.finish = new Position(finishX, finishY);
	}

	public Move(Position startPos, Position finishPos) {
		this.start = new Position(startPos);
		this.finish = new Position(finishPos);
	}

	public Move(Move move) {
		this.start = new Position(move.start);
		this.finish = new Position(move.finish);
	}
}
