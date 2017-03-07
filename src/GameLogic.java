import java.util.ArrayList;
import java.util.List;

public final class GameLogic {
	
	public static boolean isGameOver(Board board) {
		boolean lastPlayervalue = board.getLastPlayerValue();
		Move lastMove = board.getLastMove();
		isWinningMove(lastPlayervalue, lastMove);
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
	
	public static List<Move> getValidMoves (Board board) {
		if (board.getLastPlayerValue() == false) {
			// Last was black, now it's white's turn
			return getValidWhiteMoves (board);
		} else {
			// Last was white, now it's black's turn
			return getValidBlackMoves (board);
		}
	}
	
	public static List<Move> getValidWhiteMoves (Board board) {
		List <Move> moves = new ArrayList<Move>();
		Move move;
		Tower tower = getValidWhiteTower(board);
		int i = 1;
		
		// straight
		move = new Move(tower.getPositionX(), tower.getPositionY(), tower.getPositionX(), tower.getPositionY() - i);
		while (isValidMove(board, move)) {
			moves.add(move);
			i++;
			move = new Move(tower.getPositionX(), tower.getPositionY(), tower.getPositionX(), tower.getPositionY() - i);
		}
		
		// diagonal
		i = 1;
		move = new Move(tower.getPositionX(), tower.getPositionY(), tower.getPositionX() + i, tower.getPositionY() - i);
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
	
	public static List<Move> getValidBlackMoves (Board board) {
		List <Move> moves = new ArrayList<Move>();
		Move move;
		Tower tower = getValidBlackTower(board);
		int i = 1;
		
		// straight
		move = new Move(tower.getPositionX(), tower.getPositionY(), tower.getPositionX(), tower.getPositionY() + i);
		while (isValidMove(board, move)) {
			moves.add(move);
			i++;
			move = new Move(tower.getPositionX(), tower.getPositionY(), tower.getPositionX(), tower.getPositionY() + i);
		}
		
		// diagonal
		i = 1;
		move = new Move(tower.getPositionX(), tower.getPositionY(), tower.getPositionX() + i, tower.getPositionY() + i);
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
			return board.getTower(true, board.getLastColor());
		}
	}
	
	public static boolean isValidTower(Board board, int x, int y) {
		   Tower tower = board.getTower(x, y);
		   if (tower == null){
			   return false;
		   } else {
			   if (tower.getPlayer() == board.getLastPlayerValue()) {
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
