import java.util.ArrayList;
import java.util.List;

public final class BoardLogic {
	
	public static boolean isGameOver(Board board) {
		Value lastPlayerPosition = board.getLastPlayerPosition();
		Move lastMove = board.getLastMove();
		if (lastMove != null && isWinningMove(lastPlayerPosition, lastMove)) {
			return true;
		} else if (isDoubleDeadLock(board)) {
			return true;
		}
		return false;
	}
	
	public static Value getGameOverCause(Board board) {
		if (BoardLogic.isDoubleDeadLock(board))
			return Value.DOUBLE_DEADLOCK;
   		else 
   			return Value.GAME_OVER;
	}
	
	public static boolean isDoubleDeadLock(Board board) {
		Piece piece = getValidPiece(board);
		if (piece.isDeadlocked()){
			Value nextPlayerPosition = board.getLastPlayerPosition();
			Value nextColor = board.getTile(piece.getPosition());
			piece = board.getPiece(nextPlayerPosition, nextColor);
			if (piece.isDeadlocked()) {
				return true;
			}
		}
		return false;
	}
	
	public static boolean isWinningMove(Value playerPosition, Move move) {
		if (playerPosition == Value.TOP && move.finish.y == 7){
			return true;
		} else if (playerPosition == Value.BOTTOM && move.finish.y == 0) {
			return true;
		}
		return false;
	}
	
	public static List<Move> getValidMoves (Board board) {
		Piece piece;
		if (board.getLastPlayerPosition() == Value.BOTTOM) {
			// Last was Bottom, now it's black's turn
			piece = getValidTopPiece(board);
		} else {
			// Last was black, now it's Bottom's turn
			piece = getValidWhitePiece(board);
		}
		return piece.getValidMoves();
	}
	
	public static List<Move> getValidMoves (Board board, Position pos) {
		Piece piece = board.getPiece(pos);
		if (isValidPiece(board, piece)) {
			return piece.getValidMoves();
		} else {
			return new ArrayList<Move>();
		}
	}
	
	public static boolean isValidMove(Board board, Move move) {
		   Piece piece = board.getPiece(move.start);
		   if (isValidPiece(board, piece)) {
			   return piece.isValidMove(move);
		   }
		   return false;
	   }
	
	public static Move getDeadLockMove (Board board) {
		Piece piece = board.getPiece(board.getCurrentPlayerPosition(), board.getLastColor());
		if (piece == null) {
			System.out.println("Error: Cannot determine deadlock on a new game");
			return new Move (-1,-1,-1,-1);
		} else {
			Position pos = piece.getPosition();
			return new Move(pos, pos);
		}
	}
	
	public static Piece getValidPiece(Board board) {
		if (board.getCurrentPlayerPosition() == Value.BOTTOM) {
			return getValidWhitePiece(board);
		} else {
			return getValidTopPiece(board);
		}
	}
	
	public static Piece getValidWhitePiece(Board board) {
		if (board.getLastColor() == null) {
			int zeroToSeven = (int) (Math.random()*7);
			return board.getPiece(new Position(zeroToSeven, 7));
		} else {
			return  board.getPiece(Value.BOTTOM, board.getLastColor());
		}		
	}
	
	public static Piece getValidTopPiece(Board board) {
		if (board.getLastColor() == null) {
			int zeroToSeven = (int) (Math.random()*7);
			return board.getPiece(new Position(zeroToSeven, 0));
		} else {
			return board.getPiece(Value.TOP, board.getLastColor());
		}
	}
	
	public static boolean isValidPiece(Board board, Piece piece) {
		   if (piece == null){
			   return false;
		   } else {
			   if (piece.getPlayerPosition() == board.getLastPlayerPosition()) {
				   return false;
			   } else {
				   if (piece.getColor() == board.getLastColor() || board.getLastColor() == null){
					   return true;
				   } else {
					   return false;
				   }
			   }
		   }
	   }
}
