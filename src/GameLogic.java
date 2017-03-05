import java.util.ArrayList;
import java.util.List;

public final class GameLogic {
	
	public static boolean isGameOver(Board board) {
      Move lastMove = board.getLastMove();
      if (board.getLastPlayer() == false && lastMove.finishY == 7){
    	  return true;
      } else if (board.getLastPlayer() == true && lastMove.finishY == 0) {
    	  return true;
      }
      return false;
   }
	
	public static List<Move> getValidMoves (Board board) {
		List<Move> moves = new ArrayList<Move>();
		Move move;
		Tower tower;
		if (board.getLastPlayer() == false) {
			// white
			// getTower
			if (board.getLastColor() == null) {
				tower = board.getTower(4, 7);
			} else {
				tower = board.getTower(true, board.getLastColor());
			}
			// getMoves
			// straight
			int i = 1;
			move = new Move(tower.getPositionX(), tower.getPositionY(), tower.getPositionX(), tower.getPositionY() - i);
			while (isValidMove(board, move)) {
				moves.add(move);
				i++;
				move = new Move(tower.getPositionX(), tower.getPositionY(), tower.getPositionX(), tower.getPositionY() - i);
			}
			// diagonal +
			i = 1;
			move = new Move(tower.getPositionX(), tower.getPositionY(), tower.getPositionX() + i, tower.getPositionY() - i);
			while (isValidMove(board, move)) {
				moves.add(move);
				i++;
				move = new Move(tower.getPositionX(), tower.getPositionY(), tower.getPositionX() + i, tower.getPositionY() - i);
			}
			// diagonal -
			i = 1;
			move = new Move(tower.getPositionX(), tower.getPositionY(), tower.getPositionX() - i, tower.getPositionY() - i);
			while (isValidMove(board, move)) {
				moves.add(move);
				i++;
				move = new Move(tower.getPositionX(), tower.getPositionY(), tower.getPositionX() - i, tower.getPositionY() - i);
			}
		} else {
			// black
			// getTower
			if (board.getLastColor() == null) {
				tower = board.getTower(4, 0);
			} else {
				tower = board.getTower(false, board.getLastColor());
			}
			// getMoves
			// straight
			int i = 1;
			move = new Move(tower.getPositionX(), tower.getPositionY(), tower.getPositionX(), tower.getPositionY() + i);
			while (isValidMove(board, move)) {
				moves.add(move);
				i++;
				move = new Move(tower.getPositionX(), tower.getPositionY(), tower.getPositionX(), tower.getPositionY() + i);
			}
			// diagonal +
			i = 1;
			move = new Move(tower.getPositionX(), tower.getPositionY(), tower.getPositionX() + i, tower.getPositionY() + i);
			while (isValidMove(board, move)) {
				moves.add(move);
				i++;
				move = new Move(tower.getPositionX(), tower.getPositionY(), tower.getPositionX() + i, tower.getPositionY() + i);
			}
			// diagonal -
			i = 1;
			move = new Move(tower.getPositionX(), tower.getPositionY(), tower.getPositionX() - i, tower.getPositionY() + i);
			while (isValidMove(board, move)) {
				moves.add(move);
				i++;
				move = new Move(tower.getPositionX(), tower.getPositionY(), tower.getPositionX() - i, tower.getPositionY() + i);
			}
		}
		
		return moves;
	}
	
	public static boolean isValidTower(Board board, int x, int y) {
		   Tower tower = board.getTower(x, y);
		   if (tower == null){
			   return false;
		   } else {
			   if (tower.getPlayer() == board.getLastPlayer()) {
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
	      if (isValidTower(board, towerPosX, towerPosY) == false) {
	    	  return false;
	      } else if (towerPosX == x && towerPosY == y){
	    	  return false;
	      } else if (x < 0 || x >= 8 || y < 0 || y >= 8) {
	    	  return false;
	      } else{
	    	  if (board.getLastPlayer() == false) {
	    		  return isValidWhite(board, towerPosX, towerPosY, x, y);
	    	  } else {
	    		  return isValidBlack(board, towerPosX, towerPosY, x, y);
	    	  }
	      }
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
