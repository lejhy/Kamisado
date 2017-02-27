import java.util.HashSet;
import java.util.Set;

/**
 * Filip Lejhanec
 * Fraser Steel */public class Kamisado {
/**
    * <pre>
    *           0..1     0..*
    * Kamisado ------------------------- Data
    *           kamisado        &gt;       data
    * </pre>
    */
   private Set<Data> data;
   
   public Set<Data> getData() {
      if (this.data == null) {
         this.data = new HashSet<Data>();
      }
      return this.data;
   }
   
   /**
    * <pre>
    *           0..*     0..*
    * Kamisado ------------------------> UI
    *           kamisado        &gt;       uI
    * </pre>
    */
   private Set<UI> uI;
   
   public Set<UI> getUI() {
      if (this.uI == null) {
         this.uI = new HashSet<UI>();
      }
      return this.uI;
   }
   
   /**
    * <pre>
    *           0..*     0..*
    * Kamisado ------------------------> Game
    *           kamisado        &gt;       game
    * </pre>
    */
   private Set<Game> game;
   
   public Set<Game> getGame() {
      if (this.game == null) {
         this.game = new HashSet<Game>();
      }
      return this.game;
   }
   
   public void newGame() {
      // TODO implement this operation
      throw new UnsupportedOperationException("not implemented");
   }
   
   public void saveGame() {
      // TODO implement this operation
      throw new UnsupportedOperationException("not implemented");
   }
   
   public void loadGame() {
      // TODO implement this operation
      throw new UnsupportedOperationException("not implemented");
   }
   
   public void makeMove(int x, int y) {
      // TODO implement this operation
      throw new UnsupportedOperationException("not implemented");
   }
   
   
   public void updateView() {
      // TODO implement this operation
      throw new UnsupportedOperationException("not implemented");
   }
   
   public void exit() {
      // TODO implement this operation
      throw new UnsupportedOperationException("not implemented");
   }
   

	public static void main(String[] args) {
		Game game = new Game();
		game.start();

	}

}
