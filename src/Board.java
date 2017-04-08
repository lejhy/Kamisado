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
	private List<Move> previousMoves;
	
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
		this.previousMoves = new ArrayList<Move>();
	}
	
	private void initPreviousMoves(List<Move> moves) {
		this.previousMoves = new ArrayList<Move>();
		for (Move move: moves) {
			this.previousMoves.add(new Move(move));
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
	
	public Move getLastMove () {
		if (previousMoves.isEmpty())
			return null;
		else
			return new Move(previousMoves.get(previousMoves.size() - 1));
	}
	
	public void nextPlayer(){
		if (lastPlayerPosition == Value.TOP){
			lastPlayerPosition = Value.BOTTOM;
		} else {
			lastPlayerPosition = Value.TOP;
		}
	}
   
	public List<Move> getPreviousMoves() {
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
	   if (isValidPiece(piece)) {
	       if (piece.makeMove(new Position(move.finish))) {
	    	   lastColor = tiles[piece.getPosition().x][piece.getPosition().y];
	    	   Move lastMove = new Move(move);
	    	   previousMoves.add(lastMove);
	    	   nextPlayer();
	    	   if (evaluateGameOver()) {
	    		   setGameOver(evaluateGameOverCause());
	    	   }
	    	   setChanged();
	    	   notifyObservers();
	    	   return true;
	       }
	   }
       return false;
   }
   
   public void undoLastMove() {
	   if (previousMoves.isEmpty() == false) {
		   Move move = getLastMove();
		   previousMoves.remove(previousMoves.size()-1);	   
		   Piece piece = findPiece(move.finish);
		   piece.setPosition(new Position(move.start));
		   
		   move = getLastMove();
		   previousMoves.remove(previousMoves.size()-1);
		   piece = findPiece(move.finish);
		   piece.setPosition(new Position(move.start));
		   
		   if (previousMoves.isEmpty()) {
			   lastColor = null;
		   } else {
			   lastColor = piece.getColor();
		   }
		   setChanged();
		   notifyObservers();
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
			return true;
		} else if (isDoubleDeadLock()) {
			return true;
		}
		return false;
	}
	
	public Value evaluateGameOverCause() {
		if (isDoubleDeadLock())
			return Value.DOUBLE_DEADLOCK;
  		else 
  			return Value.GAME_OVER;
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
			int zeroToSeven = (int) (Math.random()*7);
			return getPiece(new Position(zeroToSeven, 7));
		} else {
			return  getPiece(Value.BOTTOM, getLastColor());
		}		
	}
	
	public Piece getValidTopPiece() {
		if (getLastColor() == null) {
			int zeroToSeven = (int) (Math.random()*7);
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
