import java.io.Serializable;

public class Move implements Serializable{
	public Position start;
	public Position finish;
	
	public Move (int startX, int startY, int finishX, int finishY) {
		this.start = new Position (startX, startY);
		this.finish = new Position (finishX, finishY);
	}
	
	public Move (Move move) {
		this.start = new Position(move.start);
		this.finish = new Position(move.finish);
	}
}
