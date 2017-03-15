import java.io.Serializable;

public class Move implements Serializable{
	public int startX;
	public int startY;
	public int finishX;
	public int finishY;
	
	public Move (int startX, int startY, int finishX, int finishY) {
		this.startX = startX;
		this.startY = startY;
		this.finishX = finishX;
		this.finishY = finishY;
	}
	
	public Move (Move move) {
		this.startX = move.startX;
		this.startY = move.startY;
		this.finishX = move.finishX;
		this.finishY = move.finishY;
	}
}
