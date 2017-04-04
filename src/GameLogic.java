import java.util.ArrayList;
import java.util.List;

public final class GameLogic {
	
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
		if (GameLogic.isDoubleDeadLock(board))
			return Value.DOUBLE_DEADLOCK;
   		else 
   			return Value.GAME_OVER;
	}
	
	public static boolean isDeadLock(Board board) {
		if (getValidMoves(board).isEmpty()) {
			return true;
		} else {
			return false;
		}
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
	
	public static List<Move> getValidMoves (Board board, Piece piece) {
		if (board.getLastPlayerPosition() == Value.BOTTOM) {
			// Last was Bottom, now it's black's turn
			return getValidTopMoves (board, piece);
			
		} else {
			// Last was black, now it's Bottom's turn
			return getValidBottomMoves (board, piece);			
		}
	}
	
	public static List<Move> getValidMoves (Board board) {
		if (board.getLastPlayerPosition() == Value.BOTTOM) {
			// Last was Bottom, now it's black's turn
			Piece piece = getValidTopPiece(board);
			return getValidTopMoves (board, piece);
		} else {
			// Last was black, now it's Bottom's turn
			Piece piece = getValidWhitePiece(board);
			return getValidBottomMoves (board, piece);
		}
	}
	
	public static List<Move> getValidMoves (Board board, Position pos) {
		Piece piece = board.getPiece(pos);
		if (isValidPiece(board, piece)) {
			if (board.getLastPlayerPosition() == Value.BOTTOM) {
				// Last was Bottom, now it's black's turn
				return getValidTopMoves (board, piece);
			} else {
				// Last was black, now it's Bottom's turn
				return getValidBottomMoves (board, piece);
			}
		} else {
			return new ArrayList<Move>();
		}
	}
	
	public static List<Move> getValidBottomMoves (Board board, Piece piece) {
		List <Move> moves = new ArrayList<Move>();
		moves.addAll(getValidWhiteStraightMoves(board, piece));
		moves.addAll(getValidWhiteDiagonalMoves(board, piece));
		return moves;
	}
	
	public static List<Move> getValidWhiteStraightMoves(Board board, Piece piece) {
		List <Move> moves = new ArrayList<Move>();
		int i = 1;
		Move move = new Move(piece.getPosition().x, piece.getPosition().y, piece.getPosition().x, piece.getPosition().y - i);
		while (isValidMove(board, move)) {
			moves.add(move);
			i++;
			move = new Move(piece.getPosition().x, piece.getPosition().y, piece.getPosition().x, piece.getPosition().y - i);
		}
		return moves;
	}
	
	public static List<Move> getValidWhiteDiagonalMoves(Board board, Piece piece) {
		List <Move> moves = new ArrayList<Move>();
		int i = 1;
		Move move = new Move(piece.getPosition().x, piece.getPosition().y, piece.getPosition().x + i, piece.getPosition().y - i);
		while (isValidMove(board, move)) {
			moves.add(move);
			i++;
			move = new Move(piece.getPosition().x, piece.getPosition().y, piece.getPosition().x + i, piece.getPosition().y - i);
		}
		i = 1;
		move = new Move(piece.getPosition().x, piece.getPosition().y, piece.getPosition().x - i, piece.getPosition().y - i);
		while (isValidMove(board, move)) {
			moves.add(move);
			i++;
			move = new Move(piece.getPosition().x, piece.getPosition().y, piece.getPosition().x - i, piece.getPosition().y - i);
		}
		return moves;
	}
	
	public static List<Move> getValidTopMoves (Board board, Piece piece) {
		List <Move> moves = new ArrayList<Move>();
		moves.addAll(getValidTopStraightMoves(board, piece));
		moves.addAll(getValidTopDiagonalMoves(board, piece));
		return moves;
	}
	
	public static List <Move> getValidTopStraightMoves(Board board, Piece piece) {
		List <Move> moves = new ArrayList<Move>();
		int i = 1;
		Move move = new Move(piece.getPosition().x, piece.getPosition().y, piece.getPosition().x, piece.getPosition().y + i);
		while (isValidMove(board, move)) {
			moves.add(move);
			i++;
			move = new Move(piece.getPosition().x, piece.getPosition().y, piece.getPosition().x, piece.getPosition().y + i);
		}
		return moves;
	}
	
	public static List <Move> getValidTopDiagonalMoves(Board board, Piece piece) {
		List<Move> moves = new ArrayList<Move>();
		int i = 1;
		Move move = new Move(piece.getPosition().x, piece.getPosition().y, piece.getPosition().x + i, piece.getPosition().y + i);
		while (isValidMove(board, move)) {
			moves.add(move);
			i++;
			move = new Move(piece.getPosition().x, piece.getPosition().y, piece.getPosition().x + i, piece.getPosition().y + i);
		}
		i = 1;
		move = new Move(piece.getPosition().x, piece.getPosition().y, piece.getPosition().x - i, piece.getPosition().y + i);
		while (isValidMove(board, move)) {
			moves.add(move);
			i++;
			move = new Move(piece.getPosition().x, piece.getPosition().y, piece.getPosition().x - i, piece.getPosition().y + i);
		}
		return moves;
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
	
	public static boolean isValidMove(Board board, Move move) {
		   Piece piece = board.getPiece(move.start);
		   if (isValidPiece(board, piece)) {
			   if (move.start.x != move.finish.x || move.start.y != move.finish.y){
				   if (move.finish.x >= 0 && move.finish.x < 8 && move.finish.y >= 0 && move.finish.y < 8) {
					   if (piece.getPlayerPosition() == Value.BOTTOM) {
						   return isValidBottom(board.getPieces(), move);
					   } else {
						   return isValidTop(board.getPieces(), move);
					   }
				   }
			   }
		   }
		   return false;
	   }
	
	public static boolean isValidBottom(List<Piece> pieces, Move move) {
		   if (isValidBottomStraight(pieces, move) || isValidBottomDiagonal(pieces, move)) {
			   	return true;
		   } else {
			   return false;
		   }
	   }
	
	public static boolean isValidTop(List<Piece> pieces, Move move) {
		   if (isValidTopStraight(pieces, move) || isValidTopDiagonal(pieces, move)) {
			   	return true;
		   } else {
			   return false;
		   }
	   }
	
	public static boolean isValidBottomStraight(List<Piece> pieces, Move move) {
		   if (move.start.x == move.finish.x && move.start.y > move.finish.y) {
			   	for (int i = move.finish.y; i < move.start.y; i++) {
			   		for (Piece piece : pieces) {
			   			if (piece.getPosition().equals(new Position(move.finish.x, i)))
			   				return false;
			   		}
			   	}
			   	return true;
		   } else {
			   return false;
		   }
	   }
		
		public static boolean isValidTopStraight(List<Piece> pieces, Move move) {
		   if (move.start.x == move.finish.x && move.start.y < move.finish.y) {
			   	for (int i = move.finish.y; i > move.start.y; i--) {
			   		for (Piece piece : pieces) {
			   			if (piece.getPosition().equals(new Position(move.finish.x, i)))
			   				return false;
			   		}
		   		}
		   		return true;
		   	} else {
			   	return false;
		   	}
		}
		
		public static boolean isValidBottomDiagonal(List<Piece> pieces, Move move) {
			int xDiff = move.start.x - move.finish.x;
			int yDiff = move.start.y - move.finish.y;
			if (xDiff == yDiff && move.start.x > move.finish.x && move.start.y > move.finish.y){
		   		for (int i = xDiff; i > 0; i--) {
		   			for (Piece piece : pieces) {
			   			if (piece.getPosition().equals(new Position(move.start.x-i, move.start.y-i)))
			   				return false;
			   		}
			   	}
			   	return true;
			} else if (-xDiff == yDiff && move.start.x < move.finish.x && move.start.y > move.finish.y){
				for (int i = -xDiff; i > 0; i--) {
					for (Piece piece : pieces) {
			   			if (piece.getPosition().equals(new Position(move.start.x+i, move.start.y-i)))
			   				return false;
			   		}
			   	}
			   	return true;
			} else {
				return false;
			}   
		}
		
		public static boolean isValidTopDiagonal(List<Piece> pieces, Move move) {
			int xDiff = move.start.x - move.finish.x;
			int yDiff = move.start.y - move.finish.y;
			if (xDiff == yDiff && move.start.x < move.finish.x && move.start.y < move.finish.y){
		   		for (int i = -xDiff; i > 0; i--) {
		   			for (Piece piece : pieces) {
			   			if (piece.getPosition().equals(new Position(move.start.x+i, move.start.y+i)))
			   				return false;
			   		}
			   	}
			   	return true;
			} else if (-xDiff == yDiff && move.start.x > move.finish.x && move.start.y < move.finish.y){
				for (int i = xDiff; i > 0; i--) {
					for (Piece piece : pieces) {
			   			if (piece.getPosition().equals(new Position(move.start.x-i, move.start.y+i)))
			   				return false;
			   		}
			   	}
			   	return true;
			} else {
				return false;
			}	
		}
}
