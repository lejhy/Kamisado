import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.List;

public class Data {
   
   private List<Game> games;
   private Scoreboard scoreboard;
   @SuppressWarnings("unused")
private String fileName;
   private List<Player> players;
   
   public Data(String fileName){
	   this.fileName = fileName;
	   this.init();
   }
   
   public Data() {
	// TODO Auto-generated constructor stub
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
   
   public Player getPlayers(int i){
	   return players.get(i);
   }
   
   public List<Game> getGames() {
      return games;
   }
   
   public void saveDataToFile(String fileName, Game game, Scoreboard scoreboard) {
	   try {
//		  JFileChooser chooser = new JFileChooser();
//		  chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);;
//		  chooser.showSaveDialog(null);
//		  
//		  String path = chooser.getSelectedFile().getAbsolutePath();
//		  String filename = chooser.getSelectedFile().getName();
		   
		   FileOutputStream fileOut = new FileOutputStream(fileName);
		   ObjectOutputStream out = new ObjectOutputStream(fileOut);
		   out.writeObject(game);
		   out.writeObject(scoreboard);
		   out.close();
		   fileOut.close();
		   System.out.println("Data is save in: " + fileName);
	   
	   }catch(IOException i){
		   i.printStackTrace();
	   } 
	   
	   
//	   public void loadFile(fileName){
//			      game = null;
//			      scoreboard = null;
//			      try {
//			         FileInputStream fileIn = new FileInputStream(fileName);
//			         ObjectInputStream in = new ObjectInputStream(fileIn);
//			         game = (Game) in.readObject();
//			         scoreboard = (Scoreboard) in.readObject();
//			         in.close();
//			         fileIn.close();
//			      }catch(IOException i) {
//			         i.printStackTrace();
//			         return;
//			      }catch(ClassNotFoundException c) {
//			         System.out.println("File was not found");
//			         c.printStackTrace();
//						      }
//	   }
//	
	   // TODO implement this operation
      throw new UnsupportedOperationException("not implemented");
   }
   
   public void removeGame(Game game) {
      this.games = null;
      throw new UnsupportedOperationException("not implemented");
   }

public String getFileName() {
	// TODO Auto-generated method stub
	return null;
}



   
}
