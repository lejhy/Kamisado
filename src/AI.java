import java.util.List;
import java.util.Set;

public final class AI {
   public static Move MiniMaxAB(Board board, int depth, boolean playerValue){
	   int a = -100;
	   int b = 100;
	   int value;
	   Board newBoard;
	   Move bestMove = new Move(-1,-1,-1,-1);
	   List<Move> moves = GameLogic.getValidMoves(board);
	   
	   if (depth == 0) {
		   return bestMove;
	   } else {
		   for (Move move: moves){
			   newBoard = new Board(board);
			   newBoard.performMove(move);
			   if(GameLogic.isGameOver(newBoard)) {
				   return move;
			   } else {
				   if (playerValue){
					   value = MinAB(newBoard, depth - 1, a, b);
					   if (value < b) {
						   b = value;
						   bestMove = move;
					   }
				   } else {
					   value = MaxAB(newBoard, depth - 1, a, b);
					   if (value > a) {
						   a = value;
						   bestMove = move;
					   }
				   }
			   }
		   }
	   }
	   return bestMove;
   }
   
   public static int MinAB (Board board, int depth, int a, int b) {
	   if (depth == 0) {
		   return 0;
	   } else {
		   int value;
		   Board newBoard;
		   List<Move> moves = GameLogic.getValidMoves(board);
		   for (Move move: moves){
			   newBoard = new Board(board);
			   newBoard.performMove(move);
			   if(GameLogic.isGameOver(newBoard)) {
				   return -100*depth;
			   } else {
				   value = MaxAB(newBoard, depth - 1, a, b);
				   
				   if (value <= a) {
					   return a;
				   }
				   
				   if (value < b) {
					   b = value;
				   }
			   }
		   }
	   }
	   return b;
   }
   
   public static int MaxAB (Board board, int depth, int a, int b) {
	   if (depth == 0) {
		   return 0;
	   } else {
		   int value;
		   Board newBoard;
		   List<Move> moves = GameLogic.getValidMoves(board);
		   for (Move move: moves){
			   newBoard = new Board(board);
			   newBoard.performMove(move);
			   if(GameLogic.isGameOver(newBoard)) {
				   return 100*depth;
			   } else {
				   value = MinAB(newBoard, depth - 1, a, b);
				   
				   if (value >= b) {
					   return b;
				   }
				   
				   if (value > a) {
					   a = value;
				   }
			   }
		   }
	   }
	   return a;
   }
}
