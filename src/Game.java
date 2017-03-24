import java.io.Serializable;
import java.util.Observable;

public abstract class Game extends Observable implements Serializable{
	protected Player player1;
	protected Player player2;
	protected Board board;
	protected boolean gameOver;
	protected Value gameOverCause;
	protected int turn;
	protected Score score;
   
   	public abstract boolean nextTurn(Move move, Value playerType);
   	protected abstract void makeMove(Move move);
   	protected abstract void setGameOver(Value cause);
   	
   	public boolean nextRound() {
   		if (gameOver && score.hasNextRound()) {
   			board = new Board();
   			gameOver = false;
   			score.nextRound();
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
   		return gameOver;
   	}
   	
   	public Value getGameOverCause() {
   		return gameOverCause;
   	}
   	
   	public Player getWinner(){
   		if (gameOver) {
   			if (GameLogic.isDoubleDeadLock(board)){
   				return getCurrentPlayer();
   			} else {
   				return getLastPlayer();
   			}
   		}
   		else return null;
   	}
   	
   	public Player getOverallWinner() {
   		if (score.getPlayer1Points().intValue() > score.getPlayer2Points().intValue()) {
   			return player1;
   		} else if (score.getPlayer1Points().intValue() < score.getPlayer2Points().intValue()) {
   			return player1;
   		} else {
   			return null;
   		}
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
		gameOver = false;
		turn = 0;
		score = new Score(points);
		System.out.println("newGameCreated");
	}
}