package model;

import java.io.Serializable;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import javafx.beans.value.ChangeListener;

public abstract class Game extends Observable implements Serializable {

	private static final long serialVersionUID = -1675794642107456053L;
	protected Player player1;
	protected Player player2;
	protected Board board;
	protected boolean randomBoard;
	protected int turn;
	protected Score score;

	public abstract boolean nextTurn(Move move, Value playerType);

	protected abstract void gameOver();

	public abstract Game clone();

	public abstract void purge();

	public boolean nextRound(Value fillDirection) {
		if (isGameOver() && hasNextRound()) {
			if (fillDirection == Value.RIGHT)
				board.fillFromRight();
			else if (fillDirection == Value.LEFT)
				board.fillFromLeft();
			if (randomBoard)
				board.randomizeTiles();
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
		if (board.getLastPlayerPosition() == Value.BOTTOM) {
			return player2;
		} else {
			return player1;
		}
	}

	public Player getLastPlayer() {
		if (board.getLastPlayerPosition() == Value.BOTTOM) {
			return player1;
		} else {
			return player2;
		}
	}

	public Piece getValidPiece() {
		return board.getValidPiece();
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

	public Player getRoundWinner() {
		if (board.getWinnerPosition() == Value.BOTTOM) {
			return player1;
		} else {
			return player2;
		}
	}

	public Player getOverallWinner() {
		if (getPlayer1Points() > getPlayer2Points()) {
			return player1;
		} else if (getPlayer1Points() < getPlayer2Points()) {
			return player2;
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

	public Board getBoard() {
		return board;
	}

	public boolean undoLastMove() {
		if (getCurrentPlayer().getType() == Value.HUMAN && getLastPlayer().getType() != Value.HUMAN) {
			if (board.undoLastMove()) {
				return true;
			}
		}
		return false;
	}

	public Position getValidPiecePosition() {
		Piece piece = getValidPiece();
		if (piece != null) {
			return piece.getPosition();
		} else {
			return new Position(-1, -1);
		}
	}

	public boolean isValidPiecePosition(Position pos) {
		return board.isValidPiece(board.getPiece(pos));
	}

	public void addGameOverListener(ChangeListener<Boolean> listener) {
		board.getGameOverProperty().addListener(listener);
	}

	public void addBoardObserver(Observer observer) {
		board.addObserver(observer);
	}

	public String getPlayer1Name() {
		return player1.getName().get();
	}

	public String getPlayer2Name() {
		return player2.getName().get();
	}

	public void addPlayer1PointsListener(ChangeListener<Number> listener) {
		score.getPlayer1Points().addListener(listener);
	}

	public void addPlayer2PointsListener(ChangeListener<Number> listener) {
		score.getPlayer2Points().addListener(listener);
	}

	public List<Move> getValidMoves() {
		return board.getValidMoves();
	}

	public List<Move> getValidMoves(Position pos) {
		return board.getValidMoves(pos);
	}

	public Value getCurrentPlayerType() {
		return getCurrentPlayer().getType();
	}

	public Game(Player player1, Player player2, int points, boolean randomBoard) {
		this.player1 = player1;
		this.player2 = player2;
		this.randomBoard = randomBoard;
		board = new Board();
		turn = 0;
		score = new Score(points);
	}

	public Game(Game game) {
		this.player1 = new Player(game.getPlayer1());
		this.player2 = new Player(game.getPlayer2());
		this.randomBoard = game.randomBoard;
		board = new Board(game.getBoard());
		turn = game.getTurn();
		score = new Score(game.getScore());
	}
}