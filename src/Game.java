import java.util.Scanner;

public class Game {
	private Board board;
	private Player player1;
	private Player player2;
	private boolean gameOver;
	private Scanner scanner;
	private String finishingColor;
	private boolean firstMove;
	private int startI;
	private int startJ;
	private int finishI;
	private int finishJ;
	
	
	
	public Game(){
		player1 = new Player("white", "bottom");
		player2 = new Player("black", "top");
		board = new Board();
		gameOver = false;
		scanner = new Scanner(System.in);
	}
	
	public void start(){
		player1.setPlaying(true);
		firstMove = true;
		render();
		while (true){
			makeMove();
			if (gameOver) break;
			switchPositions();
		}
	}
	
	private void render(){
		for (int i = 0; i < 8; i++){
			for (int j = 0; j < 8; j++){
				if(player1.getDragonTowers(i, j) != "empty"){
					System.out.print("1" + player1.getDragonTowers(i, j) + "\t");
				} else if (player2.getDragonTowers(i, j) != "empty"){
					System.out.print("2" + player2.getDragonTowers(i, j) + "\t");
				} else {
					System.out.print(board.getTiles(i, j) + "\t");
				}
			}
			System.out.print("\r\n");
		}
	}
	
	private void makeMove(){
		System.out.println(playerPlaying().getColor() + " player's turn...");
		do {
			System.out.println("Please enter valid starting coordinates:");
			System.out.print("I: ");
			startI = scanner.nextInt();
			System.out.print("J: ");
			startJ = scanner.nextInt();
		} while(!isValidStartingMove());
		do {
			System.out.println("Please enter valid finishing coordinates:");
			System.out.print("I: ");
			finishI = scanner.nextInt();
			System.out.print("J: ");
			finishJ = scanner.nextInt();
		} while(!isValidFinishingMove());
		playerPlaying().move(startI, startJ, finishI, finishJ);
		render();
		firstMove = false;
		finishingColor = board.getTiles(finishI, finishJ);
	}
	
	private boolean isValidStartingMove(){
		if (firstMove == true && playerPlaying().getDragonTowers(startI, startJ) != "empty") {
			return true;
		} else if (playerPlaying().getDragonTowers(startI, startJ) == finishingColor){
			return true;
		} else{
			return false;
		}
	}
	
	private boolean isValidFinishingMove(){
		boolean clearDestination = false;
		boolean validDirection = false;
		boolean clearPath = false;
		if (playerPlaying().getDragonTowers(finishI, finishJ) == "empty"){
			if (playerWaiting().getDragonTowers(finishI, finishJ) == "empty"){
				clearDestination = true;
			}
		}
		if (playerPlaying().getPosition() == "bottom"){
			if (finishI < startI){
				if(finishJ == startJ){
					validDirection = true;
					clearPath = true;
					for (int i = startI-1; i >= finishI; i--){
						if(playerPlaying().getDragonTowers(i, startJ) != "empty"){
							clearPath = false;
						}
						if(playerWaiting().getDragonTowers(i, startJ) != "empty"){
							clearPath = false;
						}
					}
				}else if (finishI-startI == finishJ-startJ){
					validDirection = true;
					clearPath = true;
					for (int i = startI-1, j = startJ-1; i > finishI; i--, j--){
						if(playerPlaying().getDragonTowers(i, j) != "empty"){
							clearPath = false;
						}
						if(playerWaiting().getDragonTowers(i, j) != "empty"){
							clearPath = false;
						}
					}
				}else if (finishI-startI == startJ-finishJ){
					validDirection = true;
					clearPath = true;
					for (int i = startI-1, j = startJ+1; i > finishI; i--, j++){
						if(playerPlaying().getDragonTowers(i, j) != "empty"){
							clearPath = false;
						}
						if(playerWaiting().getDragonTowers(i, j) != "empty"){
							clearPath = false;
						}
					}
				}
			}
		} else if (playerPlaying().getPosition() == "top"){
			if (finishI > startI){
				if(finishJ == startJ){
					validDirection = true;
					clearPath = true;
					for (int i = startI+1; i <= finishI; i++){
						if(playerPlaying().getDragonTowers(i, startJ) != "empty"){
							clearPath = false;
						}
						if(playerWaiting().getDragonTowers(i, startJ) != "empty"){
							clearPath = false;
						}
					}
				}else if (finishI-startI == finishJ-startJ){
					validDirection = true;
					clearPath = true;
					for (int i = startI+1, j = startJ+1; i < finishI; i++, j++){
						if(playerPlaying().getDragonTowers(i, j) != "empty"){
							clearPath = false;
						}
						if(playerWaiting().getDragonTowers(i, j) != "empty"){
							clearPath = false;
						}
					}
				}else if (finishI-startI == startJ-finishJ){
					validDirection = true;
					clearPath = true;
					for (int i = startI+1, j = startJ-1; i < finishI; i++, j--){
						if(playerPlaying().getDragonTowers(i, j) != "empty"){
							clearPath = false;
						}
						if(playerWaiting().getDragonTowers(i, j) != "empty"){
							clearPath = false;
						}
					}
				}
			}
		}
		if (clearDestination && validDirection && clearPath){
			return true;
		} else {
			return false;
		}
	}
	
	Player playerPlaying(){
		if (player1.getPlaying() == true){
			return player1;
		} else {
			return player2;
		}
		
	}
	
	Player playerWaiting(){
		if (player1.getPlaying() == true){
			return player2;
		} else {
			return player1;
		}
		
	}
	
	void switchPositions(){
		if (player1.getPlaying() == true){
			player1.setPlaying(false);
			player2.setPlaying(true);
		} else{
			player1.setPlaying(true);
			player2.setPlaying(false);
		}
	} 

}