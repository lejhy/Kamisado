
public class ComputerEasy extends Player{	
	Board board;
	
	public Move getMove(){
		return AI.MiniMaxAB(board, 3, value);
	}
	
	public ComputerEasy (boolean value, Board board) {
		super("ComputerEasy", value);
		this.board = board;
	}
}
