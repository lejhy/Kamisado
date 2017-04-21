package model;

public class NormalGame extends Game {

	private static final long serialVersionUID = 3032944226413214488L;

	public boolean nextTurn(Move move, Value type) {
		if (getCurrentPlayer().getType() == type) {
			if (board.makeMove(move)) {
				turn++;
				if (board.isGameOver()) {
					gameOver();
				}
				change();
				return true;
			}
		}
		return false;
	}

	protected void gameOver() {
		System.out.println("game over in normalGame");
		score.updatePoints(board.getPieces());
		change();
	}

	public NormalGame(Player player1, Player player2, int points, boolean randomBoard) {
		super(player1, player2, points, randomBoard);
	}

	public NormalGame(NormalGame game) {
		super((Game) game);
	}

	public Game clone() {
		Game game = new NormalGame(this);
		return game;
	}

	@Override
	public void purge() {
		this.deleteObservers();
		score.deleteObservers();
		board.deleteObservers();
	}
}