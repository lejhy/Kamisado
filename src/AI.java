import java.util.List;
public final class AI {
   public static Move MiniMaxAB(Board board, int depth){
	   int a = -100;
	   int b = 100;
	   int value = 0;
	   Board newBoard;
	   Move bestMove = new Move(-1,-1,-1,-1);
	   List<Move> moves = GameLogic.getValidMoves(board);
	   
	   if (depth == 0) {
		   return bestMove;
	   } else {
		   for (int i = 0; i < moves.size(); i++){
			   Move move = moves.get(i);
			   newBoard = new Board(board);
			   newBoard.performMove(move);
			   if(GameLogic.isGameOver(newBoard)) {
				   return move;
			   } else {
				   value = MinAB(newBoard, depth - 1, a, b);
				   if (value > a) {
					   a = value;
					   bestMove = move;
				   }
				  
			   }
		   }
	   }
	   System.out.println(a);
	   return bestMove;
   }
   
   public static int MinAB (Board board, int depth, int a, int b) {
	   if (depth == 0) {
		   return 0;
	   } else {
		   int value = 0;
		   Board newBoard;
		   List<Move> moves = GameLogic.getValidMoves(board);
		   if (moves.size() == 0) {
			   newBoard = new Board(board);
			   newBoard.performMove(GameLogic.getDeadLockMove(newBoard));
			   return MaxAB(newBoard, depth - 1, a, b);
		   } else {
			   for (int i = 0; i < moves.size(); i++){
				   Move move = moves.get(i);
				   newBoard = new Board(board);
				   newBoard.performMove(move);
				   if(GameLogic.isWinningMove(newBoard.getLastPlayerValue(), move)) {
					   return -10*depth;
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
	   }
	   return b;
   }
   
   public static int MaxAB (Board board, int depth, int a, int b) {
	   if (depth == 0) {
		   return 0;
	   } else {
		   int value = 0;
		   Board newBoard;
		   List<Move> moves = GameLogic.getValidMoves(board);
		   if (moves.size() == 0) {
			   newBoard = new Board(board);
			   newBoard.performMove(GameLogic.getDeadLockMove(newBoard));
			   return MinAB(newBoard, depth - 1, a, b);
		   } else {
			   for (int i = 0; i < moves.size(); i++){
				   Move move = moves.get(i);
				   newBoard = new Board(board);
				   newBoard.performMove(move);
				   if(GameLogic.isWinningMove(newBoard.getLastPlayerValue(), move)) {
					   return 10*depth;
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
	   }
	   return a;
   }
}
