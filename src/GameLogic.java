import java.util.ArrayList;
import java.util.List;

public final class GameLogic {
	
	public static boolean isGameOver(Board board) {
		boolean lastPlayerValue = board.getLastPlayerValue();
		Move lastMove = board.getLastMove();
		if (lastMove != null && isWinningMove(lastPlayerValue, lastMove)) {
			return true;
		} else if (isDoubleDeadLock(board)) {
			return true;
		}
		return false;
	}
	
	public static boolean isDeadLock(Board board) {
		if (getValidMoves(board).isEmpty()) {
			return true;
		} else {
			return false;
		}
	}
	
	public static boolean isDoubleDeadLock(Board board) {
		boolean lastPlayerValue = board.getLastPlayerValue();
		Move lastMove = board.getLastMove();
		if (lastMove != null && isWinningMove(lastPlayerValue, lastMove)) {
			return false;
		} else if (isDeadLock(board)) {
			Board newBoard = new Board(board);
			newBoard.performMove(getDeadLockMove(board));
			if (isDeadLock(newBoard)) {
				return true;
			}
		}
		return false;
	}
	
	public static boolean isWinningMove(boolean playerValue, Move move) {
		if (playerValue == false && move.finishY == 7){
			return true;
		} else if (playerValue == true && move.finishY == 0) {
			return true;
		}
		return false;
	}
	
	public static Move getDeadLockMove (Board board) {
		Piece tower = board.getTower(!board.getLastPlayerValue(), board.getLastColor());
		if (tower == null) {
			System.out.println("Error: Cannot determine deadlock on a new game");
			return new Move (-1,-1,-1,-1);
		} else {
			int x = tower.getPositionX();
			int y = tower.getPositionY();
			return new Move(x, y, x, y);
		}
	}
	
	public static List<Move> getValidMoves (Board board, Piece tower) {
		if (board.getLastPlayerValue()) {
			// Last was white, now it's black's turn
			return getValidBlackMoves (board, tower);
			
		} else {
			// Last was black, now it's white's turn
			return getValidWhiteMoves (board, tower);			
		}
	}
	
	public static List<Move> getValidMoves (Board board) {
		if (board.getLastPlayerValue()) {
			// Last was white, now it's black's turn
			Piece tower = getValidBlackTower(board);
			return getValidBlackMoves (board, tower);
		} else {
			// Last was black, now it's white's turn
			Piece tower = getValidWhiteTower(board);
			return getValidWhiteMoves (board, tower);
		}
	}
	
	public static List<Move> getValidWhiteMoves (Board board, Piece tower) {
		List <Move> moves = new ArrayList<Move>();
		moves.addAll(getValidWhiteStraightMoves(board, tower));
		moves.addAll(getValidWhiteDiagonalMoves(board, tower));
		return moves;
	}
	
	public static List<Move> getValidWhiteStraightMoves(Board board, Piece tower) {
		List <Move> moves = new ArrayList<Move>();
		int i = 1;
		Move move = new Move(tower.getPositionX(), tower.getPositionY(), tower.getPositionX(), tower.getPositionY() - i);
		while (isValidMove(board, move)) {
			moves.add(move);
			i++;
			move = new Move(tower.getPositionX(), tower.getPositionY(), tower.getPositionX(), tower.getPositionY() - i);
		}
		return moves;
	}
	
	public static List<Move> getValidWhiteDiagonalMoves(Board board, Piece tower) {
		List <Move> moves = new ArrayList<Move>();
		int i = 1;
		Move move = new Move(tower.getPositionX(), tower.getPositionY(), tower.getPositionX() + i, tower.getPositionY() - i);
		while (isValidMove(board, move)) {
			moves.add(move);
			i++;
			move = new Move(tower.getPositionX(), tower.getPositionY(), tower.getPositionX() + i, tower.getPositionY() - i);
		}
		i = 1;
		move = new Move(tower.getPositionX(), tower.getPositionY(), tower.getPositionX() - i, tower.getPositionY() - i);
		while (isValidMove(board, move)) {
			moves.add(move);
			i++;
			move = new Move(tower.getPositionX(), tower.getPositionY(), tower.getPositionX() - i, tower.getPositionY() - i);
		}
		return moves;
	}
	
	public static List<Move> getValidBlackMoves (Board board, Piece tower) {
		List <Move> moves = new ArrayList<Move>();
		moves.addAll(getValidBlackStraightMoves(board, tower));
		moves.addAll(getValidBlackDiagonalMoves(board, tower));
		return moves;
	}
	
	public static List <Move> getValidBlackStraightMoves(Board board, Piece tower) {
		List <Move> moves = new ArrayList<Move>();
		int i = 1;
		Move move = new Move(tower.getPositionX(), tower.getPositionY(), tower.getPositionX(), tower.getPositionY() + i);
		while (isValidMove(board, move)) {
			moves.add(move);
			i++;
			move = new Move(tower.getPositionX(), tower.getPositionY(), tower.getPositionX(), tower.getPositionY() + i);
		}
		return moves;
	}
	
	public static List <Move> getValidBlackDiagonalMoves(Board board, Piece tower) {
		List<Move> moves = new ArrayList<Move>();
		int i = 1;
		Move move = new Move(tower.getPositionX(), tower.getPositionY(), tower.getPositionX() + i, tower.getPositionY() + i);
		while (isValidMove(board, move)) {
			moves.add(move);
			i++;
			move = new Move(tower.getPositionX(), tower.getPositionY(), tower.getPositionX() + i, tower.getPositionY() + i);
		}
		i = 1;
		move = new Move(tower.getPositionX(), tower.getPositionY(), tower.getPositionX() - i, tower.getPositionY() + i);
		while (isValidMove(board, move)) {
			moves.add(move);
			i++;
			move = new Move(tower.getPositionX(), tower.getPositionY(), tower.getPositionX() - i, tower.getPositionY() + i);
		}
		return moves;
	}
	
	public static Piece getValidTower(Board board) {
		if (!board.getLastPlayerValue()) {
			return getValidWhiteTower(board);
		} else {
			return getValidBlackTower(board);
		}
	}
	
	public static Piece getValidWhiteTower(Board board) {
		if (board.getLastColor() == null) {
			int zeroToSeven = (int) (Math.random()*7);
			return board.getTower(zeroToSeven, 7);
		} else {
			return  board.getTower(true, board.getLastColor());
		}		
	}
	
	public static Piece getValidBlackTower(Board board) {
		if (board.getLastColor() == null) {
			int zeroToSeven = (int) (Math.random()*7);
			return board.getTower(zeroToSeven, 0);
		} else {
			return board.getTower(false, board.getLastColor());
		}
	}
	
	public static boolean isValidTower(Board board, int x, int y) {
		   Piece tower = board.getTower(x, y);
		   if (tower == null){
			   return false;
		   } else {
			   if (tower.getPlayerValue() == board.getLastPlayerValue()) {
				   return false;
			   } else {
				   if (tower.getColor() == board.getLastColor() || board.getLastColor() == null){
					   return true;
				   } else {
					   return false;
				   }
			   }
		   }
	   }
}
