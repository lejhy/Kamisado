package ai;

import java.util.List;

import model.Board;
import model.Move;
import model.Value;

public final class AI {

	// bottom player minimising, top player maximising
	public static Move MiniMaxAB(Board board, int depth) {
		int a = -10000;
		int b = 10000;
		int value = 0;
		Value currentPlayerPos = board.getCurrentPlayerPosition();
		// create a new local board for shallow copying
		board = new Board(board);
		Board newBoard;
		Move bestMove = new Move(-1, -1, -1, -1);
		List<Move> moves = board.getValidMoves();
		if (depth == 0) {
			return bestMove;
		} else {
			for (int i = 0; i < moves.size(); i++) {
				Move move = moves.get(i);
				newBoard = new Board(board, board.getTiles(), board.getPreviousMoves());
				newBoard.makeMove(move);
				if (newBoard.isWinningMove(newBoard.getLastPlayerPosition(), move)) {
					return move;
				} else {
					if (newBoard.getCurrentPlayerPosition() == Value.BOTTOM) {
						value = MinAB(newBoard, depth - 1, a, b);
					} else if (newBoard.getCurrentPlayerPosition() == Value.TOP) {
						value = MaxAB(newBoard, depth - 1, a, b);
					}
					if (currentPlayerPos == Value.TOP) {
						if (value > a) {
							a = value;
							bestMove = move;
						}
					} else if (currentPlayerPos == Value.BOTTOM) {
						if (value < b) {
							b = value;
							bestMove = move;
						}
					}
				}
			}
		}
		if (currentPlayerPos == Value.TOP) {
			System.out.println(a);
		} else if (currentPlayerPos == Value.BOTTOM) {
			System.out.println(b);
		}
		return bestMove;
	}

	public static int MinAB(Board board, int depth, int a, int b) {
		if (depth == 0) {
			return 0;
		} else {
			int value = 0;
			Board newBoard;
			List<Move> moves = board.getValidMoves();
			if (moves.size() == 0 && board.isGameOver() && board.getGameOverCause() == Value.DOUBLE_DEADLOCK) {
				return -10 * depth;
			} else {
				for (int i = 0; i < moves.size(); i++) {
					Move move = moves.get(i);
					newBoard = new Board(board, board.getTiles(), board.getPreviousMoves());
					newBoard.makeMove(move);
					if (newBoard.isWinningMove(newBoard.getLastPlayerPosition(), move)) {
						return -10 * depth;
					} else {
						if (newBoard.getCurrentPlayerPosition() == Value.BOTTOM) {
							value = MinAB(newBoard, depth - 1, a, b);
						} else if (newBoard.getCurrentPlayerPosition() == Value.TOP) {
							value = MaxAB(newBoard, depth - 1, a, b);
						}

						if (value <= a) {
							return a;
						}

						if (value < b) {
							b = value;
						}
					}
				}
			}
		}
		return b;
	}

	public static int MaxAB(Board board, int depth, int a, int b) {
		if (depth == 0) {
			return 0;
		} else {
			int value = 0;
			Board newBoard;
			List<Move> moves = board.getValidMoves();
			if (moves.size() == 0 && board.isGameOver() && board.getGameOverCause() == Value.DOUBLE_DEADLOCK) {
				return 10 * depth;
			} else {
				for (int i = 0; i < moves.size(); i++) {
					Move move = moves.get(i);
					newBoard = new Board(board, board.getTiles(), board.getPreviousMoves());
					newBoard.makeMove(move);
					if (newBoard.isWinningMove(newBoard.getLastPlayerPosition(), move)) {
						return 10 * depth;
					} else {
						if (newBoard.getCurrentPlayerPosition() == Value.BOTTOM) {
							value = MinAB(newBoard, depth - 1, a, b);
						} else if (newBoard.getCurrentPlayerPosition() == Value.TOP) {
							value = MaxAB(newBoard, depth - 1, a, b);
						}

						if (value >= b) {
							return b;
						}

						if (value > a) {
							a = value;
						}
					}
				}
			}
		}
		return a;
	}
}
