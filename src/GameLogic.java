import java.util.ArrayList;
import java.util.List;

public final class GameLogic {
	
	public static boolean isGameOver(Board board) {
		boolean lastPlayerValue = board.getLastPlayerValue();
		Move lastMove = board.getLastMove();
		if (isWinningMove(lastPlayerValue, lastMove)) {
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
		if (isWinningMove(lastPlayerValue, lastMove)) {
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
		Tower tower = board.getTower(!board.getLastPlayerValue(), board.getLastColor());
		if (tower == null) {
			System.out.println("Error: Cannot determine deadlock on a new game");
			return new Move (-1,-1,-1,-1);
		} else {
			int x = tower.getPositionX();
			int y = tower.getPositionY();
			return new Move(x, y, x, y);
		}
	}
	
	public static List<Move> getValidMoves (Board board, Tower tower) {
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
			Tower tower = getValidBlackTower(board);
			return getValidBlackMoves (board, tower);
		} else {
			// Last was black, now it's white's turn
			Tower tower = getValidWhiteTower(board);
			return getValidWhiteMoves (board, tower);
		}
	}
	
	public static List<Move> getValidWhiteMoves (Board board, Tower tower) {
		List <Move> moves = new ArrayList<Move>();
		moves.addAll(getValidWhiteStraightMoves(board, tower));
		moves.addAll(getValidWhiteDiagonalMoves(board, tower));
		return moves;
	}
	
	public static List<Move> getValidWhiteStraightMoves(Board board, Tower tower) {
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
	
	public static List<Move> getValidWhiteDiagonalMoves(Board board, Tower tower) {
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
	
	public static List<Move> getValidBlackMoves (Board board, Tower tower) {
		List <Move> moves = new ArrayList<Move>();
		moves.addAll(getValidBlackStraightMoves(board, tower));
		moves.addAll(getValidBlackDiagonalMoves(board, tower));
		return moves;
	}
	
	public static List <Move> getValidBlackStraightMoves(Board board, Tower tower) {
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
	
	public static List <Move> getValidBlackDiagonalMoves(Board board, Tower tower) {
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
	
	public static Tower getValidWhiteTower(Board board) {
		if (board.getLastColor() == null) {
			int zeroToSeven = (int) (Math.random()*7);
			return board.getTower(zeroToSeven, 7);
		} else {
			return  board.getTower(true, board.getLastColor());
		}		
	}
	
	public static Tower getValidBlackTower(Board board) {
		if (board.getLastColor() == null) {
			int zeroToSeven = (int) (Math.random()*7);
			return board.getTower(zeroToSeven, 0);
		} else {
			return board.getTower(false, board.getLastColor());
		}
	}
	
	public static boolean isValidTower(Board board, int x, int y) {
		   Tower tower = board.getTower(x, y);
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
	  
	   public static boolean isValidMove(Board board, Move move) {
		   int towerPosX = move.startX;
		   int towerPosY = move.startY;
		   int x = move.finishX;
		   int y = move.finishY;
		   if (isValidTower(board, towerPosX, towerPosY)) {
			   if (towerPosX != x || towerPosY != y){
				   if (x >= 0 && x < 8 && y >= 0 && y < 8) {
					   if (board.getLastPlayerValue() == false) {
						   return isValidWhite(board, towerPosX, towerPosY, x, y);
					   } else {
						   return isValidBlack(board, towerPosX, towerPosY, x, y);
					   }
				   }
			   }
		   }
		   return false;
	   }
	   
	   private static boolean isValidWhite(Board board, int towerPosX, int towerPosY, int x, int y) {
		   if (isValidWhiteStraight(board, towerPosX, towerPosY, x, y)) {
			   return true;
		   } else if (isValidWhiteDiagonal(board, towerPosX, towerPosY, x, y)) {
			   return true;
		   }
		   return false;
	   }
	   
	   private static boolean isValidBlack(Board board, int towerPosX, int towerPosY, int x, int y) {
		   if (isValidBlackStraight(board, towerPosX, towerPosY, x, y)) {
			   return true;
		   } else if (isValidBlackDiagonal(board,  towerPosX, towerPosY, x, y)) {
			   return true;
		   }
		   return false;
	   }
	   
	   private static boolean isValidWhiteStraight(Board board, int towerPosX, int towerPosY, int x, int y) {
		   if (towerPosX == x && towerPosY > y) {
			   	for (int i = y; i < towerPosY; i++) {
			   		if (board.getTower(x, i) != null){
			   			return false;
			   		}
			   	}
			   	return true;
		   } else {
			   return false;
		   }
	   }
	   
	   private static boolean isValidBlackStraight(Board board, int towerPosX, int towerPosY, int x, int y) {
		   if (towerPosX == x && towerPosY < y) {
			   	for (int i = y; i > towerPosY; i--) {
			   		if (board.getTower(x, i) != null){
			   			return false;
			   		}
			   	}
			   	return true;
		   } else {
			   return false;
		   }
	   }
	   
	   private static boolean isValidWhiteDiagonal(Board board, int towerPosX, int towerPosY, int x, int y) {
		   int xDiff = towerPosX - x;
		   int yDiff = towerPosY - y;
		   if (xDiff == yDiff && towerPosX > x && towerPosY > y){
			   	for (int i = xDiff; i > 0; i--) {
			   		if (board.getTower(towerPosX-i, towerPosY-i) != null){
			   			return false;
			   		}
			   	}
			   	return true;
		   } else if (-xDiff == yDiff && towerPosX < x && towerPosY > y){
			   for (int i = -xDiff; i > 0; i--) {
			   		if (board.getTower(towerPosX+i, towerPosY-i) != null){
			   			return false;
			   		}
			   	}
			   	return true;
		   } else {
			   return false;
		   }   
	   }
	   
	   private static boolean isValidBlackDiagonal(Board board, int towerPosX, int towerPosY, int x, int y) {
		   int xDiff = towerPosX - x;
		   int yDiff = towerPosY - y;
		   if (xDiff == yDiff && towerPosX < x && towerPosY < y){
			   	for (int i = -xDiff; i > 0; i--) {
			   		if (board.getTower(towerPosX+i, towerPosY+i) != null){
			   			return false;
			   		}
			   	}
			   	return true;
		   } else if (-xDiff == yDiff && towerPosX > x && towerPosY < y){
			   for (int i = xDiff; i > 0; i--) {
			   		if (board.getTower(towerPosX-i, towerPosY+i) != null){
			   			return false;
			   		}
			   	}
			   	return true;
		   } else {
			   return false;
		   }   
	   }
}
