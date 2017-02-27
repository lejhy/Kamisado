import java.util.Set;
import java.util.HashSet;

public class Data {
   /**
    * <pre>
    *           0..*     0..1
    * Data ------------------------- Kamisado
    *           data        &lt;       kamisado
    * </pre>
    */
   private Kamisado kamisado;
   
   public void setKamisado(Kamisado value) {
      this.kamisado = value;
   }
   
   public Kamisado getKamisado() {
      return this.kamisado;
   }
   
   /**
    * <pre>
    *           0..1     0..*
    * Data ------------------------- Game
    *           data        &gt;       game
    * </pre>
    */
   private Set<Game> game;
   
   public Set<Game> getGame() {
      if (this.game == null) {
         this.game = new HashSet<Game>();
      }
      return this.game;
   }
   
   /**
    * <pre>
    *           0..1     0..*
    * Data ------------------------- Score
    *           data        &gt;       score
    * </pre>
    */
   private Set<Score> score;
   
   public Set<Score> getScore() {
      if (this.score == null) {
         this.score = new HashSet<Score>();
      }
      return this.score;
   }
   
   public void getGame() {
      // TODO implement this operation
      throw new UnsupportedOperationException("not implemented");
   }
   
   public void addGame() {
      // TODO implement this operation
      throw new UnsupportedOperationException("not implemented");
   }
   
   public Data (String fileName) {
      // TODO implement this operation
      throw new UnsupportedOperationException("not implemented");
   }
   
   public void saveDataToFile(String fileName) {
      // TODO implement this operation
      throw new UnsupportedOperationException("not implemented");
   }
   
   public void removeGame() {
      // TODO implement this operation
      throw new UnsupportedOperationException("not implemented");
   }
   
   public void addScore(int score) {
      // TODO implement this operation
      throw new UnsupportedOperationException("not implemented");
   }
   
   public void getScore() {
      // TODO implement this operation
      throw new UnsupportedOperationException("not implemented");
   }
   
   public void resetScore() {
      // TODO implement this operation
      throw new UnsupportedOperationException("not implemented");
   }
   
   }
