import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Observable;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;

public abstract class Game extends Observable implements Serializable{
	protected Player player1;
	protected Player player2;
	protected Board board;
	protected int turn;
	protected Score score;
   
   	public abstract boolean nextTurn(Move move, Value playerType);
   	protected abstract void makeMove(Move move);
   	protected abstract void setGameOver(Value cause);
   	protected abstract Game clone();
   	
   	public boolean nextRound() {
   		if (isGameOver() && hasNextRound()) {
   			board = new Board();
   			score.nextRound();
   			change();
   			return true;
   		} else {
   			return false;
   		}
   	}
   	
   	public boolean hasNextRound() {
   		return score.hasNextRound();
   	}
   	
   	public Score getScore() {
   		return score;
   	}
   	
   	public void change() {
   		setChanged();
	   	notifyObservers();
   	}
   	
   	public void change(Value value) {
   		setChanged();
	   	notifyObservers(value);
   	}
   	
   	public Player getCurrentPlayer() {
   		if (board.getLastPlayerValue()) {
   			return player2;
   		} else {
   			return player1;
   		}
   	}
   	
   	public Player getLastPlayer() {
   		if (board.getLastPlayerValue()) {
   			return player1;
   		} else {
   			return player2;
   		}
   	}
   	
   	public Player getPlayer1() {
   		return player1;
   	}
   	
   	public Player getPlayer2() {
   		return player2;
   	}
   	
   	public boolean isGameOver() {
   		return board.isGameOver();
   	}
   	
   	public Value getGameOverCause() {
   		return board.getGameOverCause();
   	}
   	
   	public Player getOverallWinner() {
   		if (getPlayer1Points() > getPlayer2Points()) {
   			return player1;
   		} else if (getPlayer1Points() < getPlayer2Points()) {
   			return player1;
   		} else {
   			return null;
   		}
   	}
   	
   	public int getPlayer1Points() {
   		return score.getPlayer1Points().get();
   	}
   	
   	public int getPlayer2Points() {
   		return score.getPlayer2Points().get();
   	}
	
	public int getRound() {
		return this.score.getRound();
	}
	
	public int getTurn() {
		return this.turn;
	}
	
	public Board getBoard(){
		return board;
	}
	
	public Game(Player player1, Player player2, int points){
		this.player1 = player1;
		this.player2 = player2;
		board = new Board();
		turn = 0;
		score = new Score(points);
	}
	
	public Game(Game game) {
		this.player1 = new Player(game.getPlayer1());
		this.player2 = new Player(game.getPlayer2());
		board = new Board(game.getBoard());
		turn = game.getTurn();
		score = new Score(game.getScore());
	}
}