
public class Score {
	private int round;
	private int points;
	private int player1Points;
	private int player2Points;
	
	public Score(int points) {
		round = 0;
		this.points = points;
		player1Points = 0;
		player2Points = 0;
	}
	
	public Score(Score score) {
		round = score.round;
		points = score.points;
		player1Points = score.player1Points;
		player2Points = score.player2Points;
	}
	
	public boolean setWinnerPlayer1() {
		if (hasNextRound()) {
			player1Points++;
			round++;
			return true;
		} else {
			return false;
		}
	}
	
	public boolean setWinnerPlayer2() {
		if (hasNextRound()) {
			player2Points++;
			round++;
			return true;
		} else {
			return false;
		}
	}
	
	public boolean hasNextRound() {
		if (round < points) {
			return true;
		} else {
			return false;
		}
	}

	public int getRound() {
		return round;
	}


	public int getPoints() {
		return points;
	}

	public int getPlayer1Points() {
		return player1Points;
	}


	public int getPlayer2Points() {
		return player2Points;
	}
}
