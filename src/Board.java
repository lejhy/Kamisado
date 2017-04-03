import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;

public class Board extends Observable implements Serializable{
	
	private Value lastColor;
	private boolean lastPlayerValue;
	private BooleanProperty gameOver;
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
		lastPlayerValue = false;
		lastColor = null;
		gameOver = new SimpleBooleanProperty(false);
		gameOverCause = Value.GAME_OVER;
	}
	
	public Board (Board board) {
		initTiles(board.getTiles());
		initPieces(board.getPieces());
		initPreviousMoves(board.getPreviousMoves());
		this.lastPlayerValue = board.lastPlayerValue;
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
			pieces.add(new Tower(tiles[i][0], Value.TOP, new Position(i, 0)));
		}
		for (int i = 0; i < 8; i++){
			pieces.add(new Tower(tiles[i][7], Value.BOTTOM, new Position(i, 7)));
		}
	}
	
	private void initPieces(List<Piece> pieces) {
		this.pieces = new ArrayList<Piece>();
		for (Piece piece: pieces){
			this.pieces.add(piece.clone());
		}
	}
	
	public Value getTile(int x, int y) {
		return tiles[x][y];
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
	
	public Piece getPiece(boolean playervalue, Value color){
		Piece piece = findPiece(playervalue, color);
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
	
	private Piece findPiece(boolean playerValue, Value color) {
		for (Piece piece: pieces){
		   	if (piece.getPlayerPosition() == getPlayerPosition(playerValue) && piece.getColor() == color) {
			   	return piece;
		   	}
	   	}
	   	return null;
	}
	
	public Value getPlayerPosition(boolean playerValue) {
		if (playerValue == true)
			return Value.BOTTOM;
		else 
			return Value.TOP;
	}
	
	public Value getLastPlayerPosition() {
		return getPlayerPosition(getLastPlayerValue());
	}
	
	public Value getLastColor () {
		return lastColor;
	}
	
	public boolean getLastPlayerValue () {
		return lastPlayerValue;
	}
	
	public Move getLastMove () {
		if (previousMoves.isEmpty())
			return null;
		else
			return new Move(previousMoves.get(previousMoves.size() - 1));
	}
	
	public void nextPlayer(){
		if (lastPlayerValue){
		   lastPlayerValue = false;
		} else {
			lastPlayerValue = true;
		}
	}
   
	public List<Move> getPreviousMoves() {
		return previousMoves;
	}
   
	public boolean isGameOver() {
		return gameOver.get();
	}
	
	public Value getGameOverCause() {
		return gameOverCause;
	}
	
	public boolean makeMove(Move move) {
	   Piece piece = findPiece(move.start);
	   if (GameLogic.isValidPiece(this, piece)) {
	       if (piece.makeMove(new Position(move.finish))) {
	    	   lastColor = tiles[move.finish.x][move.finish.y];
	    	   Move lastMove = new Move(move);
	    	   previousMoves.add(lastMove);
	    	   nextPlayer();
	    	   if (GameLogic.isGameOver(this)) {
	    		   gameOver.set(true);
	    		   gameOverCause = GameLogic.getGameOverCause(this);
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
}
