package model;

import java.io.Serializable;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import javafx.beans.value.ChangeListener;

public abstract class Game extends Observable implements Serializable {

	private static final long serialVersionUID = -1675794642107456053L;
	protected Player white;
	protected Player black;
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
			return black;
		} else {
			return white;
		}
	}

	public Player getLastPlayer() {
		if (board.getLastPlayerPosition() == Value.BOTTOM) {
			return white;
		} else {
			return black;
		}
	}

	public Piece getValidPiece() {
		return board.getValidPiece();
	}

	public Player getWhite() {
		return white;
	}

	public Player getBlack() {
		return black;
	}

	public boolean isGameOver() {
		return board.isGameOver();
	}

	public Value getGameOverCause() {
		return board.getGameOverCause();
	}

	public Player getRoundWinner() {
		if (board.getWinnerPosition() == Value.BOTTOM) {
			return white;
		} else {
			return black;
		}
	}

	public Player getOverallWinner() {
		if (getWhitePoints() > getBlackPoints()) {
			return white;
		} else if (getWhitePoints() < getBlackPoints()) {
			return black;
		} else {
			return null;
		}
	}

	public int getWhitePoints() {
		return score.getWhitePoints().get();
	}

	public int getBlackPoints() {
		return score.getBlackPoints().get();
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

	public String getWhiteName() {
		return white.getName().get();
	}

	public String getBlackName() {
		return black.getName().get();
	}

	public void addWhitePointsListener(ChangeListener<Number> listener) {
		score.getWhitePoints().addListener(listener);
	}

	public void addBlackPointsListener(ChangeListener<Number> listener) {
		score.getBlackPoints().addListener(listener);
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

	public Game(Player white, Player black, int points, boolean randomBoard) {
		this.white = white;
		this.black = black;
		this.randomBoard = randomBoard;
		board = new Board(randomBoard);
		turn = 0;
		score = new Score(points);
	}

	public Game(Game game) {
		this.white = new Player(game.getWhite());
		this.black = new Player(game.getBlack());
		this.randomBoard = game.randomBoard;
		board = new Board(game.getBoard());
		turn = game.getTurn();
		score = new Score(game.getScore());
	}
}