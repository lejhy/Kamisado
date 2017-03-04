public final class GameLogic {
	
	public static boolean isValidTower(Board board, int x, int y) {
		   Tower tower = board.getTower(x, y);
		   if (tower == null){
			   return false;
		   } else {
			   if (tower.getPlayerColor() == board.getLastPlayerColor()) {
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
	   
	   public static boolean isValidMove(Board board, int towerPosX, int towerPosY, int x, int y) {
		   System.out.println("Valid move");
	      if (isValidTower(board, towerPosX, towerPosY) == false) {
	    	  System.out.println("Valid move invalid tower");
	    	  return false;
	      } else if (towerPosX == x && towerPosY == y){
	    	  System.out.println("Valid move same pos");
	    	  return false;
	      } else{
	    	  System.out.println("Valid move picking player");
	    	  if (board.getLastPlayerColor() == Color.black) {
	    		  System.out.println("Valid move white");
	    		  return isValidWhite(board, towerPosX, towerPosY, x, y);
	    	  } else {
	    		  return isValidBlack(board, towerPosX, towerPosY, x, y);
	    	  }
	      }
	   }
	   
	   private static boolean isValidWhite(Board board, int towerPosX, int towerPosY, int x, int y) {
		   System.out.println("Valid white");
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
		   System.out.println("Valid white straight");
		   if (towerPosX == x && towerPosY > y) {
			   System.out.println("Valid white straight inside");
			   	for (int i = y; i < towerPosY; i++) {
			   		System.out.println("Valid white straight loop");
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
		   System.out.println("Valid white diagonal");
		   int xDiff = towerPosX - x;
		   int yDiff = towerPosY - y;
		   if (xDiff == yDiff && towerPosX > x && towerPosY > y){
			   System.out.println("Valid white diagonal +");
			   	for (int i = xDiff; i > 0; i--) {
			   		System.out.println("Valid white diagonal loop");
			   		if (board.getTower(towerPosX-i, towerPosY-i) != null){
			   			System.out.println("Valid white diagonal loop fail at" + towerPosX + towerPosY + i);
			   			return false;
			   		}
			   	}
			   	System.out.println("Valid white diagonal true");
			   	return true;
		   } else if (-xDiff == yDiff && towerPosX < x && towerPosY > y){
			   System.out.println("Valid white diagonal -");
			   for (int i = -xDiff; i > 0; i--) {
				   System.out.println("Valid white diagonal loop");
			   		if (board.getTower(towerPosX+i, towerPosY-i) != null){
			   			System.out.println("Valid white diagonal loop fail at" + towerPosX + towerPosY + i);
			   			return false;
			   		}
			   	}
			   	System.out.println("Valid white diagonal true");
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
