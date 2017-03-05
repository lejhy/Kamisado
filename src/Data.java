import java.util.Set;
import java.util.HashSet;
import java.util.List;

public class Data {
   
   private List<Game> games;
   private Scoreboard scoreboard;
   private String fileName;
   
   public Data(String fileName){
	   this.fileName = fileName;
	   this.init();
   }
   
   private void init () {
	   
   }
   
   public Scoreboard getScore() {
      return scoreboard;
   } 
   
   public void addGame(Game game) {
      games.add(game);
   }
   
   public Game getGame(int i) {
      return games.get(i);
   }
   
   public List<Game> getGames() {
      return games;
   }
   
   public void saveDataToFile(String fileName) {
	// TODO implement this operation
      throw new UnsupportedOperationException("not implemented");
   }
   
   public void removeGame() {
      // TODO implement this operation
      throw new UnsupportedOperationException("not implemented");
   }
   
}
