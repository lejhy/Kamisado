import java.util.Set;
import java.util.HashSet;

public class Data {
   
   private Kamisado kamisado;
   private Game game;
   private String score[];
   private String fileName;
   
   public Data(String fileName){
	   this.fileName = fileName;
	   
   }
   
   public void setKamisado(Kamisado value) {
      this.kamisado = value;
   }
   
   public Kamisado getKamisado() {
      return this.kamisado;
   }
   
  

   
   public Game getGame() {
      if (this.game == null) {
         this.game = new Game();
      }
      return this.game;
   }
   
   
   public int[] getScore() {
      if (this.score == null) {
         this.score = new HashSet<Score>();
      }
      return this.score;
   }
   
   
   
   public void addGame() {
      // TODO implement this operation
      throw new UnsupportedOperationException("not implemented");
   }
   
   
   
   public void saveDataToFile(String fileName) {
      
      throw new UnsupportedOperationException("not implemented");
   }
   
   public void removeGame() {
      // TODO implement this operation
      throw new UnsupportedOperationException("not implemented");
   }
   
  
   
   }
