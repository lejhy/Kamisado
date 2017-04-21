package model;

import java.io.Serializable;

public class Position implements Serializable {

	private static final long serialVersionUID = 6917991963486288724L;
	public int x;
	public int y;

	public Position(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public Position(Position position) {
		this.x = position.x;
		this.y = position.y;
	}

	public boolean equals(Position other) {
		if (x == other.x && y == other.y) {
			return true;
		} else {
			return false;
		}
	}
}
