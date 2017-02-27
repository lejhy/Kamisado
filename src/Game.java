import java.util.HashSet;
import java.util.Set;
import java.util.Scanner;

public class Game {
/**
    * <pre>
    *           0..1     0..*
    * Game ------------------------- Config
    *           game        &gt;       config
    * </pre>
    */
   private Set<Config> config;
   
   public Set<Config> getConfig() {
      if (this.config == null) {
         this.config = new HashSet<Config>();
      }
      return this.config;
   }
   
   /**
    * <pre>
    *           0..*     0..*
    * Game ------------------------> AI
    *           game        &gt;       aI
    * </pre>
    */
   private Set<AI> aI;
   
   public Set<AI> getAI() {
      if (this.aI == null) {
         this.aI = new HashSet<AI>();
      }
      return this.aI;
   }
   
   /**
    * <pre>
    *           0..*     0..1
    * Game ------------------------- Data
    *           game        &lt;       data
    * </pre>
    */
   private Data data;
   
   public void setData(Data value) {
      this.data = value;
   }
   
   public Data getData() {
      return this.data;
   }
   
   private int lastX;
   
   public void setLastX(int value) {
      this.lastX = value;
   }
   
   public int getLastX() {
      return this.lastX;
   }
   
   private int lastY;
   
   public void setLastY(int value) {
      this.lastY = value;
   }
   
   public int getLastY() {
      return this.lastY;
   }
   
   public void changeConfig() {
      // TODO implement this operation
      throw new UnsupportedOperationException("not implemented");
   }
   
   public void processMove(int x, int y) {
      // TODO implement this operation
      throw new UnsupportedOperationException("not implemented");
   }
   
   public void getScore() {
      // TODO implement this operation
      throw new UnsupportedOperationException("not implemented");
   }
   

/**
 * <pre>
 *           0..1     0..*
 * Game ------------------------- Board
 *           game        &gt;       board
 * </pre>
 */
private Set<Board> board;

public Set<Board> getBoard() {
   if (this.board == null) {
      this.board = new HashSet<Board>();
   }
   return this.board;
}

private int round;

public void setRound(int value) {
   this.round = value;
}

public int getRound() {
   return this.round;
}
	
	
	
	public Game(){
		player1 = new Player("1", "bottom");
		player2 = new Player("2", "top");
		board = new Board();
		gameOver = false;
		scanner = new Scanner(System.in);
	}

}