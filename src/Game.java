import java.util.HashSet;
import java.util.Set;
import java.util.Scanner;

public class Game {
/**
 * <pre>
 *           0..1     0..*
 * Game ------------------------- Player
 *           game        &gt;       player
 * </pre>
 */
private Set<Player> player;

public Set<Player> getPlayer() {
   if (this.player == null) {
this.player = new HashSet<Player>();
   }
   return this.player;
}

	private Board board;
	private Scanner scanner;
	private Color finishingColor;
	private int startX;
	private int startY;
	private int finishX;
	private int finishY;
	
	
	
	public Game(){
		player1 = new Player("1", "bottom");
		player2 = new Player("2", "top");
		board = new Board();
		gameOver = false;
		scanner = new Scanner(System.in);
	}
	
	public int start(){
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
   					System.out.print(board.getTile(i, j) + "\t");
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
   		finishingColor = board.getTile(finishI, finishJ);
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
   		if (playerPlaying().getName() == "bottom"){
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
   		} else if (playerPlaying().getName() == "top"){
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

}