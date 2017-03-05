
public class ComputerEasy extends Player{	
	public Move getMove(Board board){
		return AI.MiniMaxAB(board, 5, value);
	}
	
	public ComputerEasy (boolean value) {
		super("ComputerEasy", value);
	}
}
