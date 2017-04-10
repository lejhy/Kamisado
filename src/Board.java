import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Timer;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;

public class Board extends Observable implements Serializable{
	
	private Value lastColor;
	private Value lastPlayerPosition;
	private transient BooleanProperty gameOver;
	private Value gameOverCause;
	private Value[][] tiles;
	private List<Piece> pieces;
	private List<List<Move>> previousMoves;
	
	public List<Piece> getPieces() {
		return pieces;
	}
	
	public Value[][] getTiles() {
		return tiles;
	}
	
	public Board () {
		initTiles();
		initPieces();
		initPreviousMoves();
		lastPlayerPosition = Value.TOP;
		lastColor = null;
		gameOver = new SimpleBooleanProperty(false);
		gameOverCause = Value.GAME_OVER;
	}
	
	public Board (Board board) {
		initTiles(board.getTiles());
		initPieces(board.getPieces());
		initPreviousMoves(board.getPreviousMoves());
		this.lastPlayerPosition = board.lastPlayerPosition;
		this.lastColor = board.lastColor;
		this.gameOver = new SimpleBooleanProperty(board.isGameOver());
		this.gameOverCause = board.getGameOverCause();
	}
	
	private void initPreviousMoves() {
		this.previousMoves = new ArrayList<List<Move>>();
	}
	
	private void initPreviousMoves(List<List<Move>> moves) {
		this.previousMoves = new ArrayList<List<Move>>();
		for (List<Move> list : moves){
			List<Move> listCopy = new ArrayList<Move>();
			this.previousMoves.add(listCopy);
			for (Move move: list) {
				listCopy.add(new Move(move));
			}
		}
	}
	
	private void initTiles() {
		Value[][] tiles = {
				{Value.ORANGE,	Value.RED, 		Value.GREEN, 	Value.PINK,		Value.YELLOW,	Value.BLUE, 	Value.PURPLE,	Value.BROWN},
				{Value.BLUE,	Value.ORANGE,	Value.PINK, 	Value.PURPLE, 	Value.RED, 		Value.YELLOW, 	Value.BROWN, 	Value.GREEN},
				{Value.PURPLE,	Value.PINK,		Value.ORANGE, 	Value.BLUE,		Value.GREEN, 	Value.BROWN, 	Value.YELLOW, 	Value.RED},
				{Value.PINK,	Value.GREEN,	Value.RED, 		Value.ORANGE,	Value.BROWN,	Value.PURPLE, 	Value.BLUE, 	Value.YELLOW},
				{Value.YELLOW, 	Value.BLUE,		Value.PURPLE, 	Value.BROWN,	Value.ORANGE,	Value.RED,		Value.GREEN,	Value.PINK},
				{Value.RED, 	Value.YELLOW,	Value.BROWN, 	Value.GREEN,	Value.BLUE, 	Value.ORANGE,	Value.PINK, 	Value.PURPLE},
				{Value.GREEN, 	Value.BROWN,	Value.YELLOW,	Value.RED, 		Value.PURPLE,	Value.PINK, 	Value.ORANGE,	Value.BLUE},
				{Value.BROWN,	Value.PURPLE,	Value.BLUE,		Value.YELLOW, 	Value.PINK,		Value.GREEN,	Value.RED,		Value.ORANGE}					 
		};
		this.tiles = tiles;
	}
	
	private void initTiles(Value[][] tiles) {
		this.tiles = new Value[8][8];
		for (int i = 0; i < 8; i++){
			for (int j = 0; j < 8; j++){
				this.tiles[i][j] = tiles[i][j];
			}
		}
	}
	
	private void initPieces() {
		pieces = new ArrayList<Piece>();
		for (int i = 0; i < 8; i++){
			pieces.add(new Tower(tiles[i][0], Value.TOP, new Position(i, 0), pieces));
		}
		for (int i = 0; i < 8; i++){
			pieces.add(new Tower(tiles[i][7], Value.BOTTOM, new Position(i, 7), pieces));
		}
	}
	
	private void initPieces(List<Piece> pieces) {
		this.pieces = new ArrayList<Piece>();
		for (Piece piece: pieces){
			this.pieces.add(piece.clone(this.pieces));
		}
	}
	
	public void fillFromLeft() {
		List <Piece> topRow = new ArrayList<Piece>();
		for (int i = 0; i <= 7; i++) {
			for (int j = 0; j <= 7; j++) {
				Piece piece = findPiece(new Position(j, i));
				if (piece != null) {
					if (piece.getPlayerPosition() == Value.TOP){
						topRow.add(piece);
					}
				}
			}
		}
		List <Piece> bottomRow = new ArrayList<Piece>();
		for (int i = 7; i >= 0; i--) {
			for (int j = 7; j >= 0; j--) {
				Piece piece = findPiece(new Position(j, i));
				if (piece != null) {
					if (piece.getPlayerPosition() == Value.BOTTOM){
						bottomRow.add(piece);
					}
				}
			}
		}
		int i = 0;
		for (Piece piece : topRow) {
			piece.setPosition(new Position(i, 0));
			i++;
		}
		i = 7;
		for (Piece piece : bottomRow) {
			piece.setPosition(new Position(i, 7));
			i--;
		}
		initPreviousMoves();
		lastPlayerPosition = Value.TOP;
		lastColor = null;
		gameOver.set(false);
	}
	
	public void fillFromRight() {
		List <Piece> topRow = new ArrayList<Piece>();
		for (int i = 0; i <= 7; i++) {
			for (int j = 7; j >= 0; j--) {
				Piece piece = findPiece(new Position(j, i));
				if (piece != null) {
					if (piece.getPlayerPosition() == Value.TOP){
						topRow.add(piece);
					}
				}
			}
		}
		List <Piece> bottomRow = new ArrayList<Piece>();
		for (int i = 7; i >= 0; i--) {
			for (int j = 0; j <= 7; j++) {
				Piece piece = findPiece(new Position(j, i));
				if (piece != null) {
					if (piece.getPlayerPosition() == Value.BOTTOM){
						bottomRow.add(piece);
					}
				}
			}
		}
		int i = 7;
		for (Piece piece : topRow) {
			piece.setPosition(new Position(i, 0));
			i--;
		}
		i = 0;
		for (Piece piece : bottomRow) {
			piece.setPosition(new Position(i, 7));
			i++;
		}
		initPreviousMoves();
		lastPlayerPosition = Value.TOP;
		lastColor = null;
		gameOver.set(false);
	}
	
	public void randomizeTiles() {
		int indexRange = tiles[0].length;
		int randomIndex1;
		int randomIndex2;
		Value[] temp = new Value[8];
		
		for (int i = 0; i < 20; i++){
			randomIndex1 = Math.round((float)Math.random()*(indexRange - 1));
			randomIndex2 = Math.round((float)Math.random()*(indexRange - 1));
			temp = tiles[randomIndex1].clone();
			tiles[randomIndex1] = tiles[randomIndex2].clone();
			tiles[randomIndex2] = temp.clone();
		}
		for (int i = 0; i < 20; i++){
			randomIndex1 = Math.round((float)Math.random()*(indexRange - 1));
			randomIndex2 = Math.round((float)Math.random()*(indexRange - 1));
			
			for (int j = 0; j < indexRange; j++){
				temp[j] = tiles[j][randomIndex1];
				tiles[j][randomIndex1] = tiles[j][randomIndex2];
				tiles[j][randomIndex2] = temp[j];
			}
		}
	}
	
	public Value getTile(int x, int y) {
		return tiles[x][y];
	}
	
	public Value getTile(Position pos) {
		return tiles[pos.x][pos.y];
	}
	
	public int getNumOfPieces () {
		return pieces.size();
	}
	
	public Piece getPiece(Position pos){
		Piece piece = findPiece(pos);
		if (piece == null) {
			return null;
		} else {
			return piece.clone();
		}
	}
	
	public Piece getPiece(Value playerPosition, Value color){
		Piece piece = findPiece(playerPosition, color);
		if (piece == null) {
			return null;
		} else {
			return piece.clone();
		}
	}
	
	private Piece findPiece(Position pos) {
		for (Piece piece: pieces){
		   	if (piece.getPosition().equals(pos)) {
			   	return piece;
		   	}
	   	}
	   	return null;
	}
	
	private Piece findPiece(Value playerPosition, Value color) {
		for (Piece piece: pieces){
		   	if (piece.getPlayerPosition() == playerPosition && piece.getColor() == color) {
			   	return piece;
		   	}
	   	}
	   	return null;
	}
	
	public Value getWinnerPosition() {
		if (gameOverCause == Value.TIME_UP) 
			return getLastPlayerPosition();
		else if (gameOverCause == Value.DOUBLE_DEADLOCK)
			return getCurrentPlayerPosition();
		else
			return getLastPlayerPosition(); 
	}
	
	public Value getPlayerPosition(boolean playerValue) {
		if (playerValue == true)
			return Value.BOTTOM;
		else 
			return Value.TOP;
	}
	
	public Value getLastPlayerPosition() {
		return lastPlayerPosition;
	}
	
	public Value getCurrentPlayerPosition() {
		if (lastPlayerPosition == Value.BOTTOM)
			return Value.TOP;
		else 
			return Value.BOTTOM;
	}
	
	public Value getLastColor () {
		return lastColor;
	}
	
	public List<Move> getLastMoveList () {
		if (previousMoves.isEmpty())
			return null;
		else
			return previousMoves.get(previousMoves.size() - 1);
	}
	
	public Move getLastMove() {
		if (previousMoves.isEmpty())
			return null;
		else {
			List <Move> moves= previousMoves.get(previousMoves.size() - 1);
			return moves.get(moves.size() - 1);
		}
			
	}
	
	public void nextPlayer(){
		if (lastPlayerPosition == Value.TOP){
			lastPlayerPosition = Value.BOTTOM;
		} else {
			lastPlayerPosition = Value.TOP;
		}
	}
   
	public List<List<Move>> getPreviousMoves() {
		return previousMoves;
	}
   
	public boolean isGameOver() {
		return gameOver.get();
	}
	
	public BooleanProperty getGameOverProperty() {
		return gameOver;
	}
	
	public Value getGameOverCause() {
		return gameOverCause;
	}
	
	public void setGameOver(Value cause) {
		gameOver.set(true);
		gameOverCause = cause;
	}
	
	public boolean makeMove(Move move) {
	   Piece piece = findPiece(move.start);
	   if (!isGameOver() && isValidPiece(piece)) {
		   if (piece.makeMove(new Position(move.finish))) {
	    	   lastColor = tiles[piece.getPosition().x][piece.getPosition().y];
	    	   Move lastMove = new Move(move);
	    	   List<Move> lastMoveList = new ArrayList<Move>();
	    	   lastMoveList.add(lastMove);
	    	   previousMoves.add(lastMoveList);
	    	   nextPlayer();
	    	   evaluateGameOver();
	    	   setChanged();
	    	   notifyObservers();
	    	   return true;
	       } else if (piece.sumoPush(move.finish)) {
	    	   Move lastMove = new Move(move);
	    	   List<Move> pushMoves = piece.getSumoPushUndoMoves();
	    	   Position lastPushMoveFinish = pushMoves.get(0).finish;
	    	   lastColor = tiles[lastPushMoveFinish.x][lastPushMoveFinish.y];
	    	   List<Move> lastMoveList = new ArrayList<Move>();
	    	   lastMoveList.addAll(pushMoves);
	    	   lastMoveList.add(lastMove);
	    	   previousMoves.add(lastMoveList);
	    	   evaluateGameOver();
	    	   setChanged();
	    	   notifyObservers();
	    	   return true;
	       }
	   }
       return false;
   }
   
   public boolean undoLastMove() {
	   if (!isGameOver() && previousMoves.size() >= 2) {
		   List<Move> moves = null;
		   do {
			   moves = getLastMoveList();
			   previousMoves.remove(previousMoves.size()-1);
			   revertMoves(moves);	
		   } while (findPiece(moves.get(moves.size() - 1).start).getPlayerPosition() != getCurrentPlayerPosition() && !previousMoves.isEmpty());
		   
		   if (previousMoves.isEmpty()) {
			   lastColor = null;
		   } else {
			   Piece piece = findPiece(moves.get(moves.size() - 1).start);
			   lastColor = piece.getColor();
		   }
		   setChanged();
		   notifyObservers();
		   return true;
	   }
	   return false;
   }
   
   private void revertMoves(List<Move> moves) {
	   Piece piece = null;
	   for (int i = moves.size() - 1; i >= 0; i--) {
		   piece = findPiece(moves.get(i).finish);
		   if (piece != null)
			   piece.setPosition(moves.get(i).start);
	   }
   }
   
   private void writeObject(ObjectOutputStream out) throws IOException {
		out.defaultWriteObject();
		out.writeBoolean(gameOver.get());
	}
   
   private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException{
		in.defaultReadObject();
		gameOver = new SimpleBooleanProperty(in.readBoolean());
	}
   
   public boolean evaluateGameOver() {
		Value lastPlayerPosition = getLastPlayerPosition();
		Move lastMove = getLastMove();
		if (lastMove != null && isWinningMove(lastPlayerPosition, lastMove)) {
			setGameOver(Value.GAME_OVER);
			return true;
		} else if (isDoubleDeadLock()) {
			setGameOver(Value.DOUBLE_DEADLOCK);
			return true;
		}
		return false;
	}
	
	public boolean isDoubleDeadLock() {
		Piece piece = getValidPiece();
		if (piece.isDeadlocked()){
			Value nextPlayerPosition = getLastPlayerPosition();
			Value nextColor = getTile(piece.getPosition());
			piece = getPiece(nextPlayerPosition, nextColor);
			if (piece.isDeadlocked()) {
				return true;
			}
		}
		return false;
	}
	
	public boolean isWinningMove(Value playerPosition, Move move) {
		if (playerPosition == Value.TOP && move.finish.y == 7){
			return true;
		} else if (playerPosition == Value.BOTTOM && move.finish.y == 0) {
			return true;
		}
		return false;
	}
	
	public List<Move> getValidMoves () {
		Piece piece;
		if (getLastPlayerPosition() == Value.BOTTOM) {
			// Last was Bottom, now it's black's turn
			piece = getValidTopPiece();
		} else {
			// Last was black, now it's Bottom's turn
			piece = getValidWhitePiece();
		}
		return piece.getValidMoves();
	}
	
	public List<Move> getValidMoves (Position pos) {
		Piece piece = getPiece(pos);
		if (isValidPiece(piece)) {
			return piece.getValidMoves();
		} else {
			return new ArrayList<Move>();
		}
	}
	
	public boolean isValidMove(Move move) {
		   Piece piece = getPiece(move.start);
		   if (isValidPiece(piece)) {
			   return piece.isValidMove(move);
		   }
		   return false;
	   }
	
	public Move getDeadLockMove () {
		Piece piece = getPiece(getCurrentPlayerPosition(), getLastColor());
		if (piece == null) {
			System.out.println("Error: Cannot determine deadlock on a new game");
			return new Move (-1,-1,-1,-1);
		} else {
			Position pos = piece.getPosition();
			return new Move(pos, pos);
		}
	}
	
	public Piece getValidPiece() {
		if (getCurrentPlayerPosition() == Value.BOTTOM) {
			return getValidWhitePiece();
		} else {
			return getValidTopPiece();
		}
	}
	
	public Piece getValidWhitePiece() {
		if (getLastColor() == null) {
			int zeroToSeven = Math.round((float)Math.random()*7);
			return getPiece(new Position(zeroToSeven, 7));
		} else {
			return  getPiece(Value.BOTTOM, getLastColor());
		}		
	}
	
	public Piece getValidTopPiece() {
		if (getLastColor() == null) {
			int zeroToSeven = Math.round((float)Math.random()*7);
			return getPiece(new Position(zeroToSeven, 0));
		} else {
			return getPiece(Value.TOP, getLastColor());
		}
	}
	
	public boolean isValidPiece(Piece piece) {
		   if (piece == null){
			   return false;
		   } else {
			   if (piece.getPlayerPosition() == getLastPlayerPosition()) {
				   return false;
			   } else {
				   if (piece.getColor() == getLastColor() || getLastColor() == null){
					   return true;
				   } else {
					   return false;
				   }
			   }
		   }
	   }
}
