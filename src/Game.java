import java.util.Observable;

public abstract class Game extends Observable{
	protected Player player1;
	protected Player player2;
	protected Board board;
	protected boolean gameOver;
	protected int round;
   
   	public abstract void nextTurn(Move move, Value playerType);
   	protected abstract void makeMove(Move move);
   	protected abstract void gameOver();
   	
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
   			return new Player(player2);
   		} else {
   			return new Player(player1);
   		}
   	}
   	
   	public Player getLastPlayer() {
   		if (board.getLastPlayerValue()) {
   			return new Player(player1);
   		} else {
   			return new Player(player2);
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
	
	public int getRound() {
		return this.round;
	}
	
	public Board getBoard(){
		return board;
	}
	
	public Game(Player player1, Player player2){
		this.player1 = player1;
		this.player2 = player2;
		board = new Board();
		gameOver = false;
		round = 0;
		System.out.println("newGameCreated");
	}
}